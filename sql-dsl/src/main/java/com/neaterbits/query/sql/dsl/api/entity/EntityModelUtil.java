package com.neaterbits.query.sql.dsl.api.entity;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;

public class EntityModelUtil<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL extends Collection<ATTRIBUTE>> {

	private final EntityModel<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL> model;

	protected EntityModelUtil(EntityModel<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL> model) {

		if (model == null) {
			throw new IllegalArgumentException("model == null");
		}

		this.model = model;
	}

	public EntityModel<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL> getModel() {
		return model;
	}
	
	public IEntity getEntityInfo(Class<?> type) {
		
		if (type == null) {
			throw new IllegalArgumentException("type == null");
		}

		final MANAGED managed = model.getManaged(type);
		
		final IEntity ret;
		
		if (!type.equals(model.getJavaType(managed))) {
			throw new IllegalStateException("Type mismatch");
		}

		
		if (managed != null) {
			final boolean isBaseType = model.isBaseType(managed);
			
			final ESubClassing subClassing = isBaseType ? model.getSubClassing(managed) : ESubClassing.NONE;
		
			String singleTableTypeColumn = 
					
					subClassing == ESubClassing.SINGLE_TABLE
							? model.getSingleTableSubClassingColumn(managed)
							: null;
			
			ret = new EntityImpl(type, managed, subClassing, singleTableTypeColumn);
		}
		else {
			ret = null;
		}

		return ret;
	}

	List<EntityAttribute> getAttributes(MANAGED managed) {

		final COLL attributes = model.getAttributes(managed);

		final List<EntityAttribute> ret = new ArrayList<>();
		
		for (ATTRIBUTE jpaAttr : attributes) {
			final EntityAttribute entityAttribute = makeEntityAttribute(jpaAttr);
			
			if (entityAttribute == null) {
				throw new IllegalStateException("entityAttribute == null");
			}
			
			ret.add(entityAttribute);
		}
		
		return ret;
	}
	
	private BiConsumer<Object, Object> getSetter(Member member) {
	
		final BiConsumer<Object, Object> ret;
		
		
		if (member instanceof Field) {
			throw new UnsupportedOperationException("TODO - does not support fields");
		}
		else if (member instanceof Method) {
			final Method method = (Method)member;
			final String methodName = method.getName();
			
			final String suffix;
			
			
			if (methodName.startsWith("get")) {
				suffix = methodName.substring(3);
			}
			else if (methodName.startsWith("set")) {
				suffix = methodName.substring(2);
			}
			else {
				throw new IllegalStateException("Method name is not a getter: " + method);
			}
			
			Method setter;
			try {
				setter = method.getDeclaringClass().getMethod("set" + suffix, method.getReturnType());
			} catch (NoSuchMethodException | SecurityException ex) {
				throw new IllegalStateException("No setter for for getter " + method, ex);
			}

			ret = (instance, value) -> {
				try {
					setter.invoke(instance, value);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
					throw new IllegalStateException("Faield to invoke setter " + setter, ex);
				}
			};
		}
		else {
			throw new UnsupportedOperationException("Unknown member type " + member.getClass());
		}
		
		return ret;
	}
	
	
	private EntityAttribute makeEntityAttribute(ATTRIBUTE attr) {

		final AttributeType attrType = model.getAttributeType(attr);
		
		final EntityAttribute ret;

		final String name = model.getAttributeName(attr);
		final String [] columns = model.getAttributeColumns(attr);
		final Class<?> javaType = model.getAttributeJavaType(attr);
		final Member member = model.getAttributeJavaMember(attr);
		
		switch (attrType) {
		
		case SCALAR:
			ret = new ScalarAttribute(name, columns, attrType, javaType, member, getSetter(member));
			break;
			
		case EMBEDDED:
			ret = new EmbeddedAttribute(name, columns, attrType, javaType, member);
			break;
			
		case RELATION:
			final RelationType relationType = model.getRelationType(attr);

			switch (relationType) {
			
			case ONE_TO_ONE:
				ret = new OneToOneAttribute(name, columns, javaType, member);
				break;
			
			case ONE_TO_MANY:
				final Class<?> memberType = getCollectionAttributeGenericType(member);
				ret = new OneToManyAttribute(name, columns, javaType, member, memberType);
				break;

			case MANY_TO_ONE:
				ret = new ManyToOneAttribute(name, columns, javaType, member);
				break;

			default:
				throw new UnsupportedOperationException("Unknown relation type " + relationType);
			
			}
			break;

		default:
			throw new UnsupportedOperationException("Unknown attribute type " + attrType);
		}
		
		return ret;
	}

	public final List<Relation> findRelations(Class<?> entity1, Class<?> entity2) {
		
		final ArrayList<Relation> relations1 = new ArrayList<>();
		
		// Find relations in one direction
		findRelationFields(entity1, entity2, relations1);


		return relations1;
	}

	public final Relation findOneToManyRelation(Class<?> entity1, Class<?> entity2, Method getter) {
		final MANAGED managed1 = model.getManaged(entity1);

		Relation relation = null;
		
		for (ATTRIBUTE attr : model.getAttributes(managed1)) {

			final AttributeType attrType = model.getAttributeType(attr);
			
			if (   attrType == AttributeType.RELATION
				&& model.getAttributeJavaMember(attr).equals(getter)) {

				relation = getRelation(entity1, attr, entity2);
				break;
			}
		}

		return relation;
	}
	
	private void findRelationFields(Class<?> entity1, Class<?> entity2, List<Relation> dst) {

		final MANAGED entity = model.getManaged(entity1);
		
		for (ATTRIBUTE attr : model.getAttributes(entity)) {

			final String attrName = model.getAttributeName(attr);
			
			System.out.println("Got attr of name " + attrName);

			final AttributeType attrType = model.getAttributeType(attr);
			
			if (attrType != AttributeType.RELATION) {
				continue;
			}

			final Relation relation = getRelation(entity1, attr, entity2);
			
			if (relation != null) {
				// Found relation
				dst.add(relation);
			}
		}
	}

	
	private Relation getRelation(Class<?> fromClass, ATTRIBUTE attr,  Class<?> toClass) {
		
		final Relation ret;
		
		// What kind of relation is this?
		final RelationType relationType = model.getRelationType(attr);

		switch (relationType) {
		case ONE_TO_ONE:
			ret = getSingleRelation(relationType, fromClass, attr, toClass);
			break;

		case MANY_TO_ONE:
			ret = getSingleRelation(relationType, fromClass, attr, toClass);
			break;

		case ONE_TO_MANY:
			ret = getCollectionRelation(relationType, fromClass, attr, toClass);
			break;

		case MANY_TO_MANY:
		default:
			throw new UnsupportedOperationException("Unsupported relation type " + relationType);
		}

		return ret;
	}

	private Relation getCollectionRelation(RelationType relationType, Class<?> fromEntityClass, ATTRIBUTE attr, Class<?> toClassMatch) {

		// Look for annotation
		
		final Relation ret;

		final Class<?> toEntityClass = model.getAssociationTarget(attr);
		
		if (toClassMatch == null || toEntityClass.equals(toClassMatch)) {
			final RelationField from = new RelationField(fromEntityClass, makeEntityAttribute(attr));
			
			final ATTRIBUTE targetAttribute = model.getAssociationTargetAttribute(attr);
			
			if (targetAttribute == null) {
				throw new IllegalStateException("No target attribute found for " + attr + "/" + model.getAttributeName(attr)+ " of " + fromEntityClass);
			}
			
			final RelationField to = new RelationField(toEntityClass, makeEntityAttribute(targetAttribute));

			ret = new Relation(relationType, from, to);
		}
		else {
			ret = null;
		}

		return ret;
	}

	private Relation getSingleRelation(RelationType relationType, Class<?> fromClass, ATTRIBUTE fromAttr,  Class<?> toClassMatch) {

		// Look for annotation
		final Member member = model.getAttributeJavaMember(fromAttr);
		
		final AccessibleObject accessible = (AccessibleObject)member;

		// Get member type for singular
		final Class<?> cl = getSingularType(accessible);

		final Relation ret;
		
		if (toClassMatch == null || cl.equals(toClassMatch)) {
			// Relation from one to other
			final RelationField from = new RelationField(fromClass, makeEntityAttribute(fromAttr));

			final ATTRIBUTE targetAttribute = model.getAssociationTargetAttribute(fromAttr); 
			final RelationField to = new RelationField(toClassMatch, makeEntityAttribute(targetAttribute));

			ret = new Relation(relationType, from, to);
		}
		else {
			// Some other entity
			ret = null;
		}

		return ret;
	}
	

	private static Class<?> getSingularType(AccessibleObject accessible) {
		
		final Class<?> cl;
		
		if (accessible instanceof Field) {
			cl = ((Field)accessible).getType();
			
		}
		else if (accessible instanceof Method) {
			cl = ((Method)accessible).getReturnType();
		}
		else {
			throw new UnsupportedOperationException("Unsupported accessible type: " + accessible.getClass().getName());
		}

		return cl;
	}


	private static Class<?> getCollectionAttributeGenericType(Member member) {
		return EntityUtil.getGenericCollectionMemberType((AccessibleObject)member);
	}

	private class EntityImpl implements IEntity {
		private final Class<?> javaType;
		private final MANAGED managed;
		private final ESubClassing subClassing;
		private final String singleTableSubclassingColumn;
		

		public EntityImpl(Class<?> javaType, MANAGED managed, ESubClassing subClassing, String singleTableSubclassingColumn) {
			
			if (javaType == null) {
				throw new IllegalArgumentException("javaType == null");
			}

			if (managed == null) {
				throw new IllegalArgumentException("managed == null");
			}

			if (subClassing == null) {
				throw new IllegalArgumentException("subClassing == null");
			}
			
			if (    subClassing == ESubClassing.SINGLE_TABLE
				&& (singleTableSubclassingColumn == null || singleTableSubclassingColumn.trim().isEmpty())) {
				throw new IllegalArgumentException("No single table column");
			}

			this.javaType = javaType;
			this.managed = managed;
			this.subClassing = subClassing;
			this.singleTableSubclassingColumn = singleTableSubclassingColumn;
		}
		
		@Override
		public String getName() {
			return model.getName(managed);
		}

		@Override
		public String getTableName() {
			return model.getTableName(managed);
		}

		@SuppressWarnings({"unchecked", "rawtypes"})
		@Override
		public Iterable<IEntityAttribute> getAttributes() {
			return (Collection)EntityModelUtil.this.getAttributes(managed);
		}

		@Override
		public Class<?> getJavaType() {
			return javaType;
		}

		@Override
		public boolean isBaseType() {
			return subClassing != ESubClassing.NONE;
		}

		@Override
		public ESubClassing getSubClassing() {
			return subClassing;
		}

		@Override
		public String getSingleTableSubclassingColumn() {
			return singleTableSubclassingColumn;
		}
		
		
	}
}
