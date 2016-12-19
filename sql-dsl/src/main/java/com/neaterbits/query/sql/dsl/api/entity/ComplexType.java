package com.neaterbits.query.sql.dsl.api.entity;

public enum ComplexType {
	
	ENTITY(true),
	EMBEDDED(false),
	MAPPED_SUPERCLASS(true);
	
	private final boolean isIdentifiable;

	private ComplexType(boolean isIdentifiable) {
		this.isIdentifiable = isIdentifiable;
	}
	
}

