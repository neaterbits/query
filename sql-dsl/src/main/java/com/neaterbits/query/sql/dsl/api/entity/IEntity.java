package com.neaterbits.query.sql.dsl.api.entity;

public interface IEntity {

	Class<?> getJavaType();
	
	String getName();
	
	String getTableName();
	
	boolean isBaseType();
	
	ESubClassing getSubClassing();

	// When single table subclassing, this keeps the column name so that we can figure the type for native-queries
	String getSingleTableSubclassingColumn();
	
	Iterable<IEntityAttribute> getAttributes();
	
}
