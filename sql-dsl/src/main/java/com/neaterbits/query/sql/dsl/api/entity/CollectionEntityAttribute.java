package com.neaterbits.query.sql.dsl.api.entity;

import java.lang.reflect.Member;

public class CollectionEntityAttribute extends EntityAttribute {
	
	private final EntityCollectionType collectionType;
	
	public CollectionEntityAttribute(Member field, String name, String column, Class<?> type, EntityCollectionType collectionType) {
		super(field, name, column, type);
		
		
		this.collectionType = collectionType;
	}
}
