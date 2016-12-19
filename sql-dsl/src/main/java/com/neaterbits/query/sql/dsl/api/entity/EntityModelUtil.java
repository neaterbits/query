package com.neaterbits.query.sql.dsl.api.entity;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class EntityModelUtil<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL extends Collection<ATTRIBUTE>> {
	
	private final EntityModel<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL> model;
	
	EntityModelUtil(EntityModel<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL> model) {
		this.model = model;
	}

	public List<EntityAttribute> getAttributes(MANAGED managed) {

		final COLL attributes = model.getAttributes(managed);

		final List<EntityAttribute> ret = new ArrayList<>();
		
		
		for (ATTRIBUTE jpaAttr : attributes) {
			final EntityAttribute entityAttribute = makeEntityAttribute(jpaAttr);
			
			if (entityAttribute == null) {
				throw new IllegalStateException("entityAttribute == null");
			}
			
		}
		
		// TODO Auto-generated method stub
		return null;
	}
	
	private EntityAttribute makeEntityAttribute(ATTRIBUTE jpaAttr) {

		final AttributeType attrType = model.getAttributeType(jpaAttr);
		
		if (model.isAssociation(jpaAttr)) {
			
		}
		else {
			
			
			// Just regular
			if (attrType.isCollection()) {
				throw new IllegalStateException("Non-association that is a collection: " + model.getAttributeName(jpaAttr));
			}
			
		}
		
		
		return null;
	}


	static List<JPARelation> findRelations(Class<?> entity1, Class<?> entity2) {
		
		final ArrayList<JPARelation> relations = new ArrayList<>();
		
//		findRelationFields(entity1, entity2, model, relations);
		
		return null;
	}

	private void findRelationFields(Class<?> entity1, Class<?> entity2, List<JPARelation> dst) {

		final MANAGED entity = model.getManaged(entity1);
		
		for (ATTRIBUTE attr : model.getAttributes(entity)) {

			final String attrName = model.getAttributeName(attr);
			
			System.out.println("Got attr of name " + attrName);

			
			if (!model.isAssociation(attr)) {
				// continue
				
				System.out.println("## found association attribute " + attrName);
			}
			
			final AttributeType attrType = model.getAttributeType(attr);

			if (attrType.isCollection()) {
				System.out.println("## found collection attribute " + attrName);

			}
			else {
				
				
				// One-to-one?
				System.out.println("## found singular attribute " + attrName);
			}
		}
	}
	/*
	
	private JPARelation getRelation(Class<?> fromClass, MANAGED fromEntity, ATTRIBUTE attr,  Class<?> toClass, MANAGED toEntity) {
		
		final JPARelation ret;
		
		final AttributeType attrType = model.getAttributeType(attr);
		
		if (attrType.isCollection()) {
			ret = getCollectionRelation(fromClass, fromEntity, attr, toClass, toEntity);
		}
		else {
			ret = getSingleRelation(fromClass, fromEntity, attr, toClass, toEntity);
		}

		return ret;
	}
	
	private JPARelation getCollectionRelation(Class<?> fromClass, MANAGED fromEntity, ATTRIBUTE attr,  Class<?> toClass, MANAGED toEntity) {

		// Look for annotation
		final AccessibleObject accessible = (AccessibleObject)model.getAttributeJavaMemeber(attr);
		
		Class<?> memberType = getCollectionAttributeGenericType(accessible);
		
		final OneToMany oneToMany = accessible.getDeclaredAnnotation(OneToMany.class);
		
		if (oneToMany == null) {
			throw new UnsupportedOperationException("No one-to-many for ");
		}

		// Check whether target is same as toClass
		
		if (memberType == null) {
			memberType = oneToMany.targetEntity();
			
			if (memberType == null) {
				throw new IllegalStateException("memberType neither specified with generics nor through members");
			}
		}

		final JPARelation ret;

		if (memberType.equals(toClass)) {
			// Relation from one to other
			final JPARelationField from = new JPARelationField(fromClass, fromEntity, attr);
			final JPARelationField to = new JPARelationField(toClass, toEntity, findToAttrOrForMappedByException(toEntity, oneToMany.mappedBy()));

			ret = new JPARelation(from, to);
		}
		else {
			ret = null;
		}


		return ret;
	}

	private static JPARelation getSingleRelation(Class<?> fromClass, EntityType<?> fromEntity, Attribute<?, ?> fromAttr,  Class<?> toClass, EntityType<?> toEntity) {

		// Look for annotation
		final Member member = fromAttr.getJavaMember();
		
		final AccessibleObject accessible = (AccessibleObject)fromAttr.getJavaMember();

		// Get member type for singular
		final Class<?> cl = getSingularType(accessible);

		final JPARelation ret;
		
		if (cl.equals(toClass)) {
		
			final OneToOne oneToOne;
			final ManyToOne manyToOne ;
	
			
			Attribute<?, ?> toAttr = null;
			
			if (null != (oneToOne = accessible.getDeclaredAnnotation(OneToOne.class))) {
				if (oneToOne.mappedBy() != null) {
					toAttr = findToAttrOrForMappedByException(toEntity, oneToOne.mappedBy());
				}
			}
			else if (null != (manyToOne = accessible.getDeclaredAnnotation(ManyToOne.class))) {
				// We are at the opposite-side
				final JoinColumn joinColumn = accessible.getDeclaredAnnotation(JoinColumn.class);
				
				// TODO: Support without JoinColumn?
				if (joinColumn == null) {
					throw new IllegalStateException("No join-column for ManyToOne attr");
				}
	
				//
				toAttr = findToAttrForManyToOneOrException(toEntity, joinColumn);
	
			}
			else {
				throw new IllegalStateException("Neither OnToOne or ManyToOne for singular association: " + fromAttr.getName());
			}

			// Relation from one to other
			final JPARelationField from = new JPARelationField(fromClass, fromEntity, fromAttr);
			final JPARelationField to = new JPARelationField(toClass, toEntity, toAttr);

			ret = new JPARelation(from, to);
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
	*/


	private static Class<?> getCollectionAttributeGenericType(AccessibleObject accessible) {
	
		final Type genericType;
		final Class<?> cl;
		
		if (accessible instanceof Field) {
			
			final Field f = (Field)accessible;
			
			genericType = f.getGenericType();
			cl = f.getType();
		}
		else if (accessible instanceof Method) {
			final Method m = (Method)accessible;
			
			if (m.getParameterTypes().length != 0) {
				throw new UnsupportedOperationException("Expected getter");
			}
			
			genericType = m.getGenericReturnType();
			cl = m.getReturnType();
		}
		else {
			throw new UnsupportedOperationException("Unsupported accessible type: " + accessible.getClass().getName());
		}
		
		if (!
				(    Collection.class.equals(cl)
			      || List.class.equals(cl)
			      || Set.class.equals(cl))				
		   ) {
			throw new UnsupportedOperationException("Not a supported collection class: " + cl);
		}
		
		
		final Class<?> ret;
		
		if (genericType instanceof ParameterizedType) {

			final ParameterizedType parameterizedType = (ParameterizedType)genericType;
			final Type[] typeArgs = parameterizedType.getActualTypeArguments();

			if (typeArgs.length != 1) {
				throw new UnsupportedOperationException("Expected exactly one type arg, got " + Arrays.toString(typeArgs));
			}

			ret = (Class<?>)typeArgs[0];
		}
		else {
			ret = null;
		}

		return ret;
	}


}
