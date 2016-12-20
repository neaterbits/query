package com.neaterbits.query.sql.dsl.api.entity;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EntityModelUtil<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL extends Collection<ATTRIBUTE>> {
	
	private final EntityModel<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL> model;
	
	protected EntityModelUtil(EntityModel<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL> model) {
		this.model = model;
	}

	List<EntityAttribute> getAttributes(MANAGED managed) {

		final COLL attributes = model.getAttributes(managed);

		final List<EntityAttribute> ret = new ArrayList<>();
		
		for (ATTRIBUTE jpaAttr : attributes) {
			final EntityAttribute entityAttribute = makeEntityAttribute(jpaAttr);
			
			if (entityAttribute == null) {
				throw new IllegalStateException("entityAttribute == null");
			}
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
			ret = new ScalarAttribute(name, columns, attrType, javaType, member);
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

}