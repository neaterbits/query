package com.neaterbits.query.sql.dsl.api;

final class FieldReference_Entity extends FieldReference {
	private final Class<?> javaType;

	FieldReference_Entity(Class<?> javaType, String varName, String columnName) {
		super(varName, columnName);

		if (javaType == null) {
			throw new IllegalArgumentException("javaType == null");
		}

		this.javaType = javaType;
	}

	public Class<?> getJavaType() {
		return javaType;
	}
}
