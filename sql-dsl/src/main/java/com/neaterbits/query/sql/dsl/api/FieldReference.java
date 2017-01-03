package com.neaterbits.query.sql.dsl.api;

abstract class FieldReference {
	private final String varName;
	private final String columnName;
	
	FieldReference(String varName, String columnName) {
		
		if (varName == null) {
			throw new IllegalArgumentException("varName == null");
		}

		if (columnName == null) {
			throw new IllegalArgumentException("columnName == null");
		}

		this.varName = varName;
		this.columnName = columnName;
	}

	public final String getVarName() {
		return varName;
	}

	public final String getColumnName() {
		return columnName;
	}
}
