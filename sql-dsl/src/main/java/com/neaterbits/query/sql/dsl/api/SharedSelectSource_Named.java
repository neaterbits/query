package com.neaterbits.query.sql.dsl.api;

final class SharedSelectSource_Named extends SharedSelectSource {

	private final Class<?> type;

	SharedSelectSource_Named(Class<?> type) {
		
		if (type == null) {
			throw new IllegalArgumentException("type == null");
		}
		
		this.type = type;
	}

	Class<?> getType() {
		return type;
	}
}
