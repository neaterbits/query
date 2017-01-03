package com.neaterbits.query.sql.dsl.api;

final class SelectSourceNamed extends SelectSource {

	private final Class<?> type;

	SelectSourceNamed(Class<?> type) {
		
		if (type == null) {
			throw new IllegalArgumentException("type == null");
		}
		
		this.type = type;
	}

	Class<?> getType() {
		return type;
	}
}
