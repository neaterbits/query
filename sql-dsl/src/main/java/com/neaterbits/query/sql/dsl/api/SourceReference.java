package com.neaterbits.query.sql.dsl.api;

final class SourceReference {
	private final Class<?> javaType;
	private final String varName;
	private final EFieldAccessType fieldAccessType;
	
	SourceReference(Class<?> javaType, String varName, EFieldAccessType fieldAccessType) {
		
		if (javaType == null) {
			throw new IllegalArgumentException("javaType == null");
		}

		if (varName == null) {
			throw new IllegalArgumentException("varName == null");
		}

		if (fieldAccessType == null) {
			throw new IllegalArgumentException("fieldAccessType == null");
		}

		this.javaType = javaType;
		this.varName = varName;
		this.fieldAccessType = fieldAccessType;
	}

	Class<?> getJavaType() {
		return javaType;
	}

	String getVarName() {
		return varName;
	}

	EFieldAccessType getFieldAccessType() {
		return fieldAccessType;
	}
}
