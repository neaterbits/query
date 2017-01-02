package com.neaterbits.query.sql.dsl.api;

final class FieldReferenceAlias extends FieldReference {
	private final String varName;
	
	FieldReferenceAlias(String varName, String columnName) {
		super(columnName);

		if (varName == null) {
			throw new IllegalArgumentException("varName == null");
		}

		this.varName = varName;
	}

	public String getVarName() {
		return varName;
	}

}
