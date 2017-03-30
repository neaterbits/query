package com.neaterbits.query.sql.dsl.api.entity;

public interface IEntityFields {

	Class<?> getJavaType();
	
	String getName();
	
	String getTableName();

	Iterable<IEntityAttribute> getAttributes();
}

