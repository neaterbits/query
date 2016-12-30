package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Member;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.Attribute.PersistentAttributeType;
import javax.persistence.metamodel.BasicType;
import javax.persistence.metamodel.EmbeddableType;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.IdentifiableType;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.Type;

import com.neaterbits.query.sql.dsl.api.entity.AttributeType;
import com.neaterbits.query.sql.dsl.api.entity.ComplexType;
import com.neaterbits.query.sql.dsl.api.entity.EntityModel;
import com.neaterbits.query.sql.dsl.api.entity.EntityUtil;
import com.neaterbits.query.sql.dsl.api.entity.IdType;
import com.neaterbits.query.sql.dsl.api.entity.RelationType;
import com.neaterbits.query.sql.dsl.api.entity.ScalarType;

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
	public ComplexType getComplexType(ManagedType<?> managed) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public IdentifiableType<?> getIdentifiable(ManagedType<?> managed) {
		// TODO Auto-generated method stub
		return null;
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
				throw new IllegalStateException("oneToOne == null");
			}
			
			targetEntity = getAssociationTarget(attribute);
			
			if (oneToOne.mappedBy() != null) {
				final Attribute<?, ?> corresponding = metamodel.entity(targetEntity).getAttribute(oneToOne.mappedBy());
			
				// 	Either this or other side has columns
				final String [] joinColumns = getJoinColumnsReferenced((AccessibleObject)corresponding.getJavaMember());

				ret = joinColumns != null
						? joinColumns
						: getColumnAnnotationOrDefaultAsArray(attribute); 
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
			
			if (oneToMany.mappedBy() != null) {
				// Find the corresponding entity
				final Attribute<?, ?> corresponding = metamodel.entity(targetEntity).getAttribute(oneToMany.mappedBy());
				
				// Must look for JoinColumns on other side
				final String [] joinColumns = getJoinColumnsReferenced((AccessibleObject)corresponding.getJavaMember());
				
				ret = joinColumns != null
						? joinColumns
						: getColumnAnnotationOrDefaultAsArray(attribute); 
				
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
					: getColumnAnnotationOrDefaultAsArray(attribute); 
			break;

		case MANY_TO_MANY:
		case ELEMENT_COLLECTION:
		default:
			throw new UnsupportedOperationException("No persistent attribute type: " + persistentAttributeType);
		}
		
		if (ret == null) {
			throw new IllegalStateException("Unable to get column for attribute " + attribute);
		}
		
		return ret;
	}
	
	
	private static String [] getColumnAnnotationOrDefaultAsArray(Attribute<?, ?> attribute) {
		return new String [] { getColumnAnnotationOrDefault(attribute) };
	}

	private static String getColumnAnnotationOrDefault(Attribute<?, ?> attribute) {

		final AccessibleObject accessible = (AccessibleObject)attribute.getJavaMember();

		final String ret;
		
		final Column column = accessible.getAnnotation(Column.class);

		if (column != null) {
			ret = column.name();
		}
		else {
			ret = attribute.getName();
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
			
			if (oneToMany.mappedBy() != null) {
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
			
			// Loop over all ManyToOne attribues with target here
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
				if (src[i].referencedColumnName() != null) {
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
					if (src[i].name() == null) {
						throw new IllegalStateException("No at idx " + i);
					}
					
					ret[dstIdx ++] = src[i].name();
				}
			}
		}
		else if (null != (joinColumn = accessible.getAnnotation(JoinColumn.class))) {
			ret = joinColumn.referencedColumnName() != null
					? new String [] { joinColumn.referencedColumnName() }
					: null;
		}
		else {
			ret = null;
		}

		return ret;
	}

}

