package com.neaterbits.query.sql.dsl.api.entity;

public interface ICollectionEntityAttribute extends IEntityAttribute {

	
	Class<?> getJavaMemberType();
	
	CollectionType getCollectionType();
	
}
