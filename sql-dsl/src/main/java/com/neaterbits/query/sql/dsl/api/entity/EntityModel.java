package com.neaterbits.query.sql.dsl.api.entity;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Collection;
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
	String getColumnNameForGetter(Class<?> type, Method getter);
	
}
