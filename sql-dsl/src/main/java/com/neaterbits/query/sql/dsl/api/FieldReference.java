package com.neaterbits.query.sql.dsl.api;

abstract class FieldReference {
	private final String columnName;
	
	FieldReference(String columnName) {
		
		if (columnName == null) {
			throw new IllegalArgumentException("columnName == null");
		}
		
		this.columnName = columnName;
	}


	public final String getColumnName() {
		return columnName;
	}
}
