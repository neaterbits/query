package com.neaterbits.query.sql.dsl.api.entity;

import java.lang.reflect.Member;

public interface IEntityAttribute {

	String getName();
	
	String [] getColumns();
	
	AttributeType getType();

	Class<?> getJavaType();
	
	Member getJavaMember();
	
	void set(Object instance, Object value);
}
