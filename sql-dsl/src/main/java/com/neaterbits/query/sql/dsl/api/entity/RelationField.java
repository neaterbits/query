package com.neaterbits.query.sql.dsl.api.entity;

public final class RelationField {

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

	public Class<?> getEntityType() {
		return type;
	}


	public IEntityAttribute getAttribute() {
		return attribute;
	}
}

