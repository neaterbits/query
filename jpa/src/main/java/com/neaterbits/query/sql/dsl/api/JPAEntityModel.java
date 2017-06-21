package com.neaterbits.query.sql.dsl.api;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.Attribute.PersistentAttributeType;
import javax.persistence.metamodel.BasicType;
import javax.persistence.metamodel.EmbeddableType;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.IdentifiableType;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.Type;
import javax.persistence.metamodel.Type.PersistenceType;

import com.neaterbits.query.sql.dsl.api.entity.AttributeType;
import com.neaterbits.query.sql.dsl.api.entity.ComplexType;
import com.neaterbits.query.sql.dsl.api.entity.ESubClassing;
import com.neaterbits.query.sql.dsl.api.entity.ETemporalType;
import com.neaterbits.query.sql.dsl.api.entity.EntityModel;
import com.neaterbits.query.sql.dsl.api.entity.EntityUtil;
import com.neaterbits.query.sql.dsl.api.entity.IdType;
import com.neaterbits.query.sql.dsl.api.entity.RelationType;
import com.neaterbits.query.sql.dsl.api.entity.ScalarType;
import com.neaterbits.util.StringUtils;

public class JPAEntityModel implements EntityModel<
									ManagedType<?>,
									EmbeddableType<?>,
									IdentifiableType<?>,
									Attribute<?, ?>,
									Set<Attribute<?, ?>>

									> {

	private final Metamodel metamodel;
	
	JPAEntityModel(Metamodel metamodel) {
		if (metamodel == null) {
			throw new IllegalArgumentException("metamodel == null");
		}
		
		this.metamodel = metamodel;
	}


	@Override
	public Set<Class<?>> getAllManagedTypes() {
		
		final Set<EntityType<?>> entities = metamodel.getEntities();
		final Set<Class<?>> ret = new HashSet<>(entities.size());
		
		for (EntityType<?> entity : entities) {
			ret.add(entity.getJavaType());
		}

		return ret;
	}

	@Override
	public Attribute<?, ?> getIdAttribute(IdentifiableType<?> identifiable) {
		throw new UnsupportedOperationException("May have to return multiple");
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public Set<Attribute<?, ?>> getAttributes(ManagedType<?> entity) {
		return (Set)entity.getAttributes();
	}

	@Override
	public ManagedType<?> getManaged(Class<?> type) {
		return metamodel.managedType(type);
	}

	
	@Override
	public Class<?> getJavaType(ManagedType<?> managed) {
		return managed.getJavaType();
	}


	@Override
	public String getName(ManagedType<?> managed) {
		if (managed.getPersistenceType() != PersistenceType.ENTITY) {
			throw new IllegalArgumentException("Not an entity");
		}

		return ((EntityType<?>)managed).getName();
	}

	@Override
	public String getTableName(ManagedType<?> managed) {
		
		if (managed.getPersistenceType() != PersistenceType.ENTITY) {
			throw new IllegalArgumentException("Not an entity");
		}
		
		final Class<?> javaType = managed.getJavaType();
		
		final Table table = javaType.getDeclaredAnnotation(Table.class);
		
		String ret = null;
		
		if (table != null) {
			ret = table.name();
		}

		if (StringUtils.isBlank(ret)) {
			final Entity entity = javaType.getDeclaredAnnotation(Entity.class);
			
			String entityName = null;
			
			if (entity != null) {
				entityName = entity.name(); 
			}
			
			if (StringUtils.isBlank(entityName)) {
				entityName = javaType.getSimpleName();
			}

			
			ret = getDefaultTableNameFromAttr(entityName);
		}

		return ret;
	}

	@Override
	public ComplexType getComplexType(ManagedType<?> managed) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public IdentifiableType<?> getIdentifiable(ManagedType<?> managed) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public IdType getIdType(IdentifiableType<?> identifiable) {
		
		final IdType ret;
		
		final Type<?> idType = identifiable.getIdType();
		
		if (idType instanceof BasicType) {
			ret = IdType.SINGLE_SCALAR;
		}
		else if (idType instanceof EmbeddableType<?>) {
			ret = IdType.SINGLE_EMBEDDED;
		}
		else {
			throw new UnsupportedOperationException("Unknown ID type " + idType.getJavaType().getName());
		}

		return ret;
	}


	@Override
	public EmbeddableType<?> getEmbeddedIdType(IdentifiableType<?> identifiable) {
		return (EmbeddableType<?>)identifiable.getIdType();
	}



	@Override
	public ScalarType getScalarIdType(IdentifiableType<?> identifiable) {

		final BasicType<?> basicType = (BasicType<?>)identifiable.getIdType();

		final Class<?> javaType = basicType.getJavaType();
		
		final ScalarType ret = ScalarType.fromType(javaType);
		
		if (ret == null) {
			throw new IllegalStateException("No scalar type from java type " + javaType.getName());
		}
		
		return ret;
	}

	@Override
	public AttributeType getAttributeType(Attribute<?, ?> attribute) {

		final AttributeType ret;

		switch (attribute.getPersistentAttributeType()) {
		case BASIC:
			ret = AttributeType.SCALAR;
			break;

		case EMBEDDED:
			ret = AttributeType.EMBEDDED;
			break;

		case ONE_TO_ONE:
		case ONE_TO_MANY:
		case MANY_TO_ONE:
		case MANY_TO_MANY:
			ret = AttributeType.RELATION;
			break;

		case ELEMENT_COLLECTION:
		default:
			throw new IllegalStateException("Not yet supported: " + attribute.getPersistentAttributeType());
		}

		return ret;
	}
	

	@Override
	public RelationType getRelationType(Attribute<?, ?> attribute) {

		final RelationType ret;

		switch (attribute.getPersistentAttributeType()) {

		case ONE_TO_ONE:
			ret = RelationType.ONE_TO_ONE;
			break;

		case ONE_TO_MANY:
			ret = RelationType.ONE_TO_MANY;
			break;

		case MANY_TO_ONE:
			ret = RelationType.MANY_TO_ONE;
			break;

		case MANY_TO_MANY:
			ret = RelationType.MANY_TO_MANY;
			break;

		case EMBEDDED:
		case BASIC:
		case ELEMENT_COLLECTION:
		default:
			throw new IllegalStateException("Not an association: " + attribute.getPersistentAttributeType());
		}

		return ret;
	}


	@Override
	public String getAttributeName(Attribute<?, ?> attribute) {
		return attribute.getName();
	}
	

	@Override
	public String [] getAttributeColumns(Attribute<?, ?> attribute) {
		
		final PersistentAttributeType persistentAttributeType = attribute.getPersistentAttributeType();
		final AccessibleObject accessible = (AccessibleObject)attribute.getJavaMember();
		
		final String [] ret;
		Class<?> targetEntity;
		
		// Figure out column in this table
		switch (persistentAttributeType) {
		case BASIC:
			// Should always correspond to exactly one column
			ret = new String[] { getColumnAnnotationOrDefault(attribute) };
			break;
			
		case EMBEDDED:
			throw new IllegalStateException("Not getting columns for embedded attributes");

		case ONE_TO_ONE:
			final OneToOne oneToOne = accessible.getAnnotation(OneToOne.class);

			if (oneToOne == null) {
				throw new IllegalStateException("oneToOne == null - TODO can reach here");
			}
			
			targetEntity = getAssociationTarget(attribute);
			
			if (oneToOne.mappedBy() != null && !oneToOne.mappedBy().trim().isEmpty()) {
				final Attribute<?, ?> corresponding = metamodel.entity(targetEntity).getAttribute(oneToOne.mappedBy());
			
				// 	Either this or other side has columns
				final String [] joinColumns = getJoinColumnsReferenced((AccessibleObject)corresponding.getJavaMember());

				ret = joinColumns != null
						? joinColumns
						: // for join columns, looks like defaults to "<attribbutename>_ID" 
						  // getColumnAnnotationOrDefaultAsArray(attribute);
						new String [] { attribute.getName().toUpperCase() + "_ID" };
				
						
							
			}
			else {
				// default
				ret = getColumnAnnotationOrDefaultAsArray(attribute);
			}
			break;
			
		case ONE_TO_MANY:
			// May be joined on one or multiple
			final OneToMany oneToMany = accessible.getAnnotation(OneToMany.class);
			
			if (oneToMany == null) {
				throw new IllegalStateException("oneToMany == null");
			}

			targetEntity = getAssociationTarget(attribute);
			
			if (oneToMany.mappedBy() != null && !oneToMany.mappedBy().trim().isEmpty()) {
				// Find the corresponding entity
				final Attribute<?, ?> corresponding = metamodel.entity(targetEntity).getAttribute(oneToMany.mappedBy());
				
				// Must look for JoinColumns on other side
				final String [] joinColumns = getJoinColumnsReferenced((AccessibleObject)corresponding.getJavaMember());

				if (joinColumns != null) {
					ret = joinColumns;
				}
				else {
					// Default to match PK of this-instance
					
					ret = tryGetPrimaryKeyColumns(attribute.getDeclaringType());
					
					if (ret == null) {
						throw new IllegalStateException("Unable to get columns for ONE_TO_MANY attribute " + attribute);
					}
				}
			}
			else {
				// unidirectional
				ret = getColumnAnnotationOrDefaultAsArray(attribute);
			}
			break;
			
		case MANY_TO_ONE:
			// May be joined by one or multiple columns
			targetEntity = getAssociationTarget(attribute);
			
			final String [] joinColumns = getJoinColumns((AccessibleObject)attribute.getJavaMember());
			
			ret = joinColumns != null
					? joinColumns
							: // for join columns, looks like defaults to "<attribbutename>_ID" 
								  // getColumnAnnotationOrDefaultAsArray(attribute);
								new String [] { attribute.getName().toUpperCase() + "_ID" };
							
							
					// : getColumnAnnotationOrDefaultAsArray(attribute); 
			break;

		case MANY_TO_MANY:
		case ELEMENT_COLLECTION:
		default:
			throw new UnsupportedOperationException("No persistent attribute type: " + persistentAttributeType);
		}
		
		if (ret == null) {
			throw new IllegalStateException("Unable to get column for attribute " + attribute);
		}
		else {
			for (String c : ret) {
				if (StringUtils.isBlank(c)) {
					throw new IllegalStateException("Blank column for attribute " + attribute + ", attr type " + persistentAttributeType);
				}
			}
		}
		
		return ret;
	}
	
	private static String [] tryGetPrimaryKeyColumns(ManagedType<?> managed) {
		if (managed.getPersistenceType() != PersistenceType.ENTITY) {
			throw new IllegalArgumentException("Not an entity: " + managed);
		}
		
		final String [] ret;
		
		final EntityType<?> entity = (EntityType<?>)managed;

		if (!entity.hasSingleIdAttribute()) {
			throw new UnsupportedOperationException("TODO - support multi IDs");
		}
		
		Attribute<?, ?> idAttribute = null;

		for (Attribute<?, ?> attr : entity.getAttributes()) {

			if (attr instanceof SingularAttribute) {
				final SingularAttribute<?, ?> singular = (SingularAttribute<?, ?>)attr;

				if (singular.isId()) {
					idAttribute = singular;
					break;
				}
			}
		}

		ret = getColumnAnnotationOrDefaultAsArray(idAttribute);

		return ret;
	}
	
	
	
	
	
	private static String [] getColumnAnnotationOrDefaultAsArray(Attribute<?, ?> attribute) {
		return new String [] { getColumnAnnotationOrDefault(attribute) };
	}

	private static String getColumnAnnotationOrDefault(Attribute<?, ?> attribute) {

		final AccessibleObject accessible = (AccessibleObject)attribute.getJavaMember();

		String ret = null;
		
		final Column column = accessible.getAnnotation(Column.class);

		if (column != null) {
			ret = column.name();
		}

		if (StringUtils.isBlank(ret)) {
			final String attrName = attribute.getName();
			
			if (StringUtils.isBlank(attrName)) {
				throw new IllegalStateException("Blank attr name for" + attribute);
			}

			ret = getDefaultColumnNameFromAttr(attrName);
		}
		
		if (StringUtils.isBlank(ret)) {
			throw new IllegalStateException("Blank column for attribute " + attribute);
		}
		
		return ret;
	}
	

	@Override
	public Class<?> getAttributeJavaType(Attribute<?, ?> attribute) {
		return attribute.getJavaType();
	}



	@Override
	public Member getAttributeJavaMember(Attribute<?, ?> attribute) {
		return attribute.getJavaMember();
	}



	@Override
	public Class<?> getAssociationTarget(Attribute<?, ?> attribute) {
		
		Class<?> targetEntity;
		final AccessibleObject accessible = (AccessibleObject)attribute.getJavaMember();

		switch (attribute.getPersistentAttributeType()) {
		case ONE_TO_ONE:
			final OneToOne oneToOne = accessible.getAnnotation(OneToOne.class);

			if (oneToOne == null) {
				throw new IllegalStateException("oneToOne == null");
			}
			
			targetEntity = oneToOne.targetEntity();
			
			if (targetEntity == null) {
				targetEntity = attribute.getJavaType();
			}
			break;
			
		case ONE_TO_MANY:
			// May be joined on one or multiple
			final OneToMany oneToMany = accessible.getAnnotation(OneToMany.class);
			
			if (oneToMany == null) {
				throw new IllegalStateException("oneToMany == null");
			}

			targetEntity = EntityUtil.getGenericCollectionMemberType(accessible);
			
			if (targetEntity == null) {
				targetEntity = oneToMany.targetEntity();
				
				if (targetEntity == null) {
					throw new IllegalStateException("No target entity found");
				}
			}
			else {
				if (    oneToMany.targetEntity() != null
					 && !oneToMany.targetEntity().equals(void.class)
				     && !targetEntity.equals(oneToMany.targetEntity())) {
					throw new IllegalStateException("Mismatch in generic entity and JPA entity: " + targetEntity + " and " + oneToMany.targetEntity());
				}
			}
			break;
			
		case MANY_TO_ONE:
			// May be joined by one or multiple columns
			final ManyToOne manyToOne = accessible.getAnnotation(ManyToOne.class);
			
			targetEntity = manyToOne.targetEntity();
			
			if (targetEntity == null) {
				targetEntity = attribute.getJavaType();
			}
			
			break;

		case MANY_TO_MANY:
		default:
			throw new UnsupportedOperationException("Unknown target entity type " + attribute.getPersistentAttributeType());
		}
		
		return targetEntity;
	}


	@Override
	public Attribute<?, ?> getAssociationTargetAttribute(Attribute<?, ?> attribute) {
		// Get associated attribute
		Class<?> targetEntity;
		Attribute<?, ?> corresponding;

		final AccessibleObject accessible = (AccessibleObject)attribute.getJavaMember();

		switch (attribute.getPersistentAttributeType()) {
		case ONE_TO_ONE:
			final OneToOne oneToOne = accessible.getAnnotation(OneToOne.class);

			if (oneToOne == null) {
				throw new IllegalStateException("oneToOne == null");
			}

			targetEntity = getAssociationTarget(attribute);
			if (oneToOne.mappedBy() != null) {
				corresponding = metamodel.entity(targetEntity).getAttribute(oneToOne.mappedBy());
			}
			else {
				corresponding = null;
			}
			break;
			
		case ONE_TO_MANY:
			// May be joined on one or multiple
			final OneToMany oneToMany = accessible.getAnnotation(OneToMany.class);
			
			if (oneToMany == null) {
				throw new IllegalStateException("oneToMany == null");
			}
			
			targetEntity = getAssociationTarget(attribute);
			
			if (oneToMany.mappedBy() != null && !oneToMany.mappedBy().trim().isEmpty()) {
				corresponding = metamodel.entity(targetEntity).getAttribute(oneToMany.mappedBy());
			}
			else {
				corresponding = null;
			}
			break;
			
		case MANY_TO_ONE:
			// May be joined by one or multiple columns
			final ManyToOne manyToOne = accessible.getAnnotation(ManyToOne.class);
			corresponding = null;
			
			targetEntity = getAssociationTarget(attribute);
			
			// Loop over all ManyToOne attributes with target here
			corresponding = findOneToManyWithTarget(targetEntity, attribute.getDeclaringType().getJavaType());
			break;

		case MANY_TO_MANY:

		default:
			throw new UnsupportedOperationException("Unknown target entity type " + attribute.getPersistentAttributeType());
		}

		return corresponding;
	}
	
	private Attribute<?, ?> findOneToManyWithTarget(Class<?> oneToManyEntity, Class<?> targetEntity) {
		
		final ManagedType<?> managed = metamodel.managedType(oneToManyEntity);
		
		for (Attribute<?, ?> attr : managed.getAttributes()) {
			
			if (    attr.getPersistentAttributeType() == PersistentAttributeType.ONE_TO_MANY
			     && getAssociationTarget(attr).equals(targetEntity)) {
				return attr;
			}
		}
		
		return null;
	}


	static void findEntityField(Class<?> entity1, Class<?> entity2, Metamodel model) {

		final EntityType<?> entity = model.entity(entity1);
		
		for (Attribute<?, ?> attr : entity.getAttributes()) {
			
			System.out.println("Got attr of name " + attr.getName());

			if (attr.isAssociation()) {
				System.out.println("## found association attribute " + attr.getName());
			}

			if (attr.isCollection()) {
				System.out.println("## found collection attribute " + attr.getName());
			}
			else {
				System.out.println("## found singular attribute " + attr.getName());
			}
		}
		
	}
	
	private static String [] getJoinColumns(AccessibleObject accessible) {
		
		final String [] ret;
		
		final JoinColumns joinColumns = accessible.getAnnotation(JoinColumns.class);
		
		final JoinColumn joinColumn;
		
		if (joinColumns != null) {
			
			final JoinColumn[] src = joinColumns.value();
			
			ret = new String[src.length];
			
			for (int i = 0; i < src.length; ++ i) {
				if (src[i].name() == null) {
					throw new IllegalStateException("No at idx " + i);
				}
				
				ret[i] = src[i].name();
			}
			
		}
		else if (null != (joinColumn = accessible.getAnnotation(JoinColumn.class))) {
			ret = new String [] { joinColumn.name() };
		}
		else {
			ret = null;
		}

		return ret;
	}

	private static String [] getJoinColumnsReferenced(AccessibleObject accessible) {
		
		final String [] ret;
		
		final JoinColumns joinColumns = accessible.getAnnotation(JoinColumns.class);
		
		final JoinColumn joinColumn;
		
		if (joinColumns != null) {
			
			final JoinColumn[] src = joinColumns.value();
			
			int num = 0;
			for (int i = 0; i < src.length; ++ i) {
				if (!StringUtils.isBlank(src[i].referencedColumnName())) {
					++ num;
				}
			}
			
			if  (num == 0) {
				ret = null;
			}
			else {
			
				int dstIdx = 0;
				
				ret = new String[num];
				
				for (int i = 0; i < src.length; ++ i) {
					if (StringUtils.isBlank(src[i].name())) {
						throw new IllegalStateException("No at idx " + i);
					}
					
					ret[dstIdx ++] = src[i].name();
				}
			}
		}
		else if (null != (joinColumn = accessible.getAnnotation(JoinColumn.class))) {
			ret = StringUtils.isBlank(joinColumn.referencedColumnName())
					? null
					: new String [] { joinColumn.referencedColumnName() };
		}
		else {
			ret = null;
		}

		return ret;
	}

	private static String getDefaultColumnNameFromAttr(String attrName) {
		//return StringUtils.toLowerWithUnderscore(attrName);
		
		return attrName.toLowerCase();
	}
	
	private static String getDefaultTableNameFromAttr(String entityName) {
		// return StringUtils.toLowerWithUnderscore(entityName);
		
		return entityName.toLowerCase();
	}

	@Override
	public String getEntityFieldNameForGetter(Class<?> type, Method getter) {

		// Look up in entity manager
		final EntityType<?> entityType = metamodel.entity(type);

		if (entityType == null) {
			throw new IllegalStateException("No entity type for " + type);
		}

		final Attribute<?, ?> attr = findAttr(entityType, getter);
		
		if (attr == null) {
			throw new IllegalArgumentException("No attribute for getter " + getter);
		}
		
		return attr.getName();
	}
	


	@Override
	public String getColumnNameForGetter(Class<?> type, Method getter) {

		// Look up in entity manager
		
		final EntityType<?> entityType = metamodel.entity(type);

		if (entityType == null) {
			throw new IllegalStateException("No entity type for " + type);
		}

		final Attribute<?, ?> attr = findAttr(entityType, getter);
		
		if (attr == null) {
			throw new IllegalArgumentException("No attribute for getter " + getter);
		}
		
		final String ret;
		

		// TODO: better way to get this information?
		final Column column = getAnnotation(attr, Column.class);
		
		if (column != null && column.name() != null && !column.name().trim().isEmpty()) {
			ret = column.name().trim();
		}
		else {
			ret = attr.getName();
		}
				
		return ret;
	}
	
	@Override
	public ETemporalType getTemporalTypeForGetter(Class<?> type, Method getter) {
		final EntityType<?> entityType = metamodel.entity(type);

		if (entityType == null) {
			throw new IllegalStateException("No entity type for " + type);
		}

		final Attribute<?, ?> attr = findAttr(entityType, getter);

		if (attr == null) {
			throw new IllegalArgumentException("No attribute for getter " + getter);
		}

		
		// Get default and at the same type check that is a time based attribute
		final Class<?> javaType = attr.getJavaType();
		
		// Does it have a @Temporal annotation ?
		final ETemporalType defaultType;

		if (javaType.equals(java.util.Date.class)) {
			defaultType = ETemporalType.TIMESTAMP;
		}
		else if (javaType.equals(Calendar.class)) {
			defaultType = ETemporalType.TIMESTAMP;
		}
		else if (javaType.equals(java.sql.Date.class)) {
			defaultType = ETemporalType.DATE;
		}
		else if (javaType.equals(java.sql.Time.class)) {
			defaultType = ETemporalType.TIME;
		}
		else if (javaType.equals(java.sql.Timestamp.class)) {
			defaultType = ETemporalType.TIMESTAMP;
		}
		else {
			throw new IllegalStateException("Unknown temporal type: " + javaType.getName() + " in attr " + attr.getName() + " of type " + javaType.getName());
		}

		final Temporal temporal = getAnnotation(attr, Temporal.class);

		final ETemporalType temporalType;

		if (temporal == null) {
			temporalType = defaultType;
		}
		else {
			switch (temporal.value()) {
			case DATE:
				temporalType = ETemporalType.DATE;
				break;

			case TIME:
				temporalType = ETemporalType.TIME;
				break;

			case TIMESTAMP:
				temporalType = ETemporalType.TIMESTAMP;
				break;

			default:
				throw new IllegalStateException("Unknown temporal type " + temporal.value() + " for attribute " + attr.getName() + " of " + javaType.getName());
			}
		}
		
		return temporalType;
	}

	private static <T extends Annotation> T getAnnotation(Attribute<?, ?> attr, Class<T> annotationClass) {
		
		final T ret;
		
		if (attr.getJavaMember() instanceof AccessibleObject) {
			final AccessibleObject accesible = (AccessibleObject)attr.getJavaMember();
			
			ret = accesible.getDeclaredAnnotation(annotationClass);
		}
		else {
			throw new UnsupportedOperationException("Unknown member type " + attr.getJavaType().getClass());
		}

		return ret;
	}

	private static Attribute<?, ?> findAttr(EntityType<?> entityType, Method getterMethod) {
		Attribute<?, ?> found = null;
		
		// Find attribute
		for (Attribute<?, ?> attr : entityType.getAttributes()) {
			final Member m  = attr.getJavaMember();

			if (m instanceof Method) {
				final Method method = (Method)m;
				
				if (method.equals(getterMethod)) {
					found = attr;
				}
			}
			else {
				throw new UnsupportedOperationException("Does not support field members for now");
			}
		}

		return found;
	}




	@Override
	public boolean isBaseType(ManagedType<?> managed) {

		final boolean isBaseType;

		if (managed.getPersistenceType() == PersistenceType.MAPPED_SUPERCLASS) {
			isBaseType = true;;
		}
		else if (managed.getPersistenceType() == PersistenceType.ENTITY) {
			
			// May or may not be, depends on whether has inherited types
			isBaseType = ! getDirectSubTypes(managed).isEmpty();
		}
		else {
			isBaseType = false;
		}

		return isBaseType;
	}


	@Override
	public boolean isSubType(ManagedType<?> managed) {
		// Look at whether has super-type
		
		return ((IdentifiableType<?>)managed).getSupertype() != null;
	}

	@Override
	public ManagedType<?> getDirectSuperType(ManagedType<?> managed) {

		if (!isSubType(managed)) {
			throw new IllegalStateException("not a subtype");
		}
		
		return ((IdentifiableType<?>)managed).getSupertype();
	}


	@Override
	public ESubClassing getSubClassing(ManagedType<?> managed) {
		
		if (!isBaseType(managed)) {
			throw new IllegalArgumentException("only to be called for base types");
		}

		final ESubClassing ret;

		// What type of subclassing is used?
		switch (managed.getPersistenceType()) {
		case MAPPED_SUPERCLASS:
			ret = ESubClassing.MAPPED_SUPERCLASS;
			break;

		case ENTITY:
			// Must figure out by looking at inheritance strategy
			final Inheritance inheritance = managed.getJavaType().getDeclaredAnnotation(Inheritance.class);

			if (inheritance == null) {
				// throw new IllegalStateException("No subclassing found for " + managed.getJavaType()); // ret = ESubClassing.NONE;
				// looks like defaults to SINGLE_TABLE, at least in EclipseLink

				ret = ESubClassing.SINGLE_TABLE;
			}
			else {
				switch (inheritance.strategy()) {

				case JOINED:
					ret = ESubClassing.JOINED;
					break;

				case SINGLE_TABLE:
					ret = ESubClassing.SINGLE_TABLE;
					break;

				case TABLE_PER_CLASS:
					ret = ESubClassing.TABLE_PER_CLASS;
					break;

				default:
					throw new UnsupportedOperationException("Unknown inheritance type: " + inheritance.strategy());
				}
			}
			break;
			
		default:
			throw new IllegalStateException("Unknown persistence type " + managed.getPersistenceType());
		}
		
		
		return ret;
	}

	@Override
	public String getSingleTableSubClassingColumn(ManagedType<?> managed) {
		final String ret;
		
		if (managed.getClass().getName().startsWith("org.eclipse.persistence")) {
			ret = "DTYPE";
		}
		else {
			throw new UnsupportedOperationException("Unknown JPA provider");
		}

		return ret;
	}

	@Override
	public List<ManagedType<?>> getDirectSubTypes(ManagedType<?> managed) {

		final List<ManagedType<?>> ret;

		if (managed.getPersistenceType() == PersistenceType.ENTITY) {
			ret = new ArrayList<>();

			for (EntityType<?> entity : metamodel.getEntities()) {
				
				final IdentifiableType<?> superType = entity.getSupertype();
				
				if (superType != null && superType.equals(managed)) {
					ret.add(entity);
				}
			}
		}
		else {
			throw new IllegalStateException("Trying to get sub types of non-entity");
		}
		
		return ret;
	}
}

