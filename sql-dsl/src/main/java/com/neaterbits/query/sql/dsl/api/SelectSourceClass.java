package com.neaterbits.query.sql.dsl.api;

final class SelectSourceClass extends SelectSource {

	private final Class<?> type;

	SelectSourceClass(Class<?> type) {
		
		if (type == null) {
			throw new IllegalArgumentException("type == null");
		}
		
		this.type = type;
	}

	Class<?> getType() {
		return type;
	}
}
