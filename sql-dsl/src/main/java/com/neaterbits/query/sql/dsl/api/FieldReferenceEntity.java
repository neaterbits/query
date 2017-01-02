package com.neaterbits.query.sql.dsl.api;

final class FieldReferenceEntity extends FieldReference {
	private final Class<?> javaType;

	FieldReferenceEntity(Class<?> javaType, String columnName) {
		super(columnName);

		if (javaType == null) {
			throw new IllegalArgumentException("javaType == null");
		}

		this.javaType = javaType;
	}

	public Class<?> getJavaType() {
		return javaType;
	}
}
