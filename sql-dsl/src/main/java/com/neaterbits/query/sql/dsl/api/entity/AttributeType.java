package com.neaterbits.query.sql.dsl.api.entity;

public enum AttributeType {
	SCALAR(false),
	COMPLEX(false),
	COMPLEX_COLLECTION(true);
	
	private final boolean isCollection;

	private AttributeType(boolean isCollection) {
		this.isCollection = isCollection;
	}

	public boolean isCollection() {
		return isCollection;
	}
}
