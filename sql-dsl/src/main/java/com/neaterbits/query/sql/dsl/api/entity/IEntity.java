package com.neaterbits.query.sql.dsl.api.entity;

public interface IEntity {

	Class<?> getJavaType();
	
	String getName();
	
	String getTableName();
	
	Iterable<IEntityAttribute> getAttributes();
	
}
