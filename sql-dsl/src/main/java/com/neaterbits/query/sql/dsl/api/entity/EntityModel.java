package com.neaterbits.query.sql.dsl.api.entity;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface EntityModel<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, ATTRIBUTE_COLL extends Collection<ATTRIBUTE>> {

	
	/**
	 * Get entity for particular type
	 * @param type
	 * @return
	 */
	

	Set<Class<?>> getAllManagedTypes(); 
	
	MANAGED getManaged(Class<?> type);
	
	Class<?> getJavaType(MANAGED managed);
	
	String getName(MANAGED managed);

	ATTRIBUTE_COLL getAttributes(MANAGED managed);

	ComplexType getComplexType(MANAGED managed);

	IDENTIFIABLE getIdentifiable(MANAGED managed);

	String getTableName(MANAGED managed);
	
	IdType getIdType(IDENTIFIABLE identifiable);

	EMBEDDED getEmbeddedIdType(IDENTIFIABLE identifiable);
	
	ScalarType getScalarIdType(IDENTIFIABLE identifiable);

	AttributeType getAttributeType(ATTRIBUTE attribute);

	RelationType getRelationType(ATTRIBUTE attribute);
	
	String getAttributeName(ATTRIBUTE attribute);

	String [] getAttributeColumns(ATTRIBUTE attribute);

	Class<?> getAttributeJavaType(ATTRIBUTE attribute);
	
	Member getAttributeJavaMember(ATTRIBUTE attribute);
	
	Class<?> getAssociationTarget(ATTRIBUTE attribute);
	
	ATTRIBUTE getAssociationTargetAttribute(ATTRIBUTE attribute);

	
	// TODO: can be removed or refactored to pass MANAGED?
	String getEntityFieldNameForGetter(Class<?> type, Method getter);
	
	String getColumnNameForGetter(Class<?> type, Method getter);
	
	boolean isBaseType(MANAGED managed);

	boolean isSubType(MANAGED managed);

	MANAGED getDirectSuperType(MANAGED managed);
	
	// Get sub-classing of a type, in case this is a base type
	ESubClassing getSubClassing(MANAGED managed);
	
	
	List<MANAGED> getDirectSubTypes(MANAGED managed);
	
	String getSingleTableSubClassingColumn(MANAGED managed);
	
	public default List<MANAGED> getAllLeafSubTypes(MANAGED managed) {
		
		final List<MANAGED> ret = new ArrayList<MANAGED>();
		
		if (!isBaseType(managed)) {
			throw new IllegalArgumentException("type is not a base type");
		}
		
		getAllLeafSubTypes(managed, ret);
		
		return ret;
	}
	
	public default void getAllLeafSubTypes(MANAGED managed, List<MANAGED> dst) {
		
		final List<MANAGED> directSub = getDirectSubTypes(managed);
		
		if (directSub == null || directSub.isEmpty()) {
			dst.add(managed);
		}
		else {
			// recurse
			for (MANAGED sub : directSub) {
				getAllLeafSubTypes(sub, dst);
			}
		}
	}
	
	
}
