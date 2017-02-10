package com.neaterbits.query.sql.dsl.api;

final class OrderByReference {
	
	private final FieldReference field;
	private final ESortOrder sortOrder;
	
	OrderByReference(FieldReference field, ESortOrder sortOrder) {

		if (field == null) {
			throw new IllegalArgumentException("field == null");
		}

		if (sortOrder == null) {
			throw new IllegalArgumentException("sortOrder == null");
		}

		this.field = field;
		this.sortOrder = sortOrder;
	}

	final FieldReference getField() {
		return field;
	}

	ESortOrder getSortOrder() {
		return sortOrder;
	}
}
