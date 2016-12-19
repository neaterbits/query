package com.neaterbits.query.sql.dsl.api.entity;

final class RelationField {

	private final Class<?> type;
	private final EntityAttribute attribute;
	
	RelationField(Class<?> type, EntityAttribute attribute) {
		
		if (type == null) {
			throw new IllegalArgumentException("type == null");
		}
		
		if (attribute == null) {
			throw new IllegalArgumentException("attribute == null");
		}
		
		this.type = type;
		this.attribute = attribute;
	}

	Class<?> getType() {
		return type;
	}


	EntityAttribute getAttribute() {
		return attribute;
	}
}

