package com.neaterbits.query.sql.dsl.api;

final class SourceReference {
	private final Class<?> javaType;
	private final String varName;
	
	SourceReference(Class<?> javaType, String varName) {
		
		if (javaType == null) {
			throw new IllegalArgumentException("javaType == null");
		}

		if (varName == null) {
			throw new IllegalArgumentException("varName == null");
		}

		this.javaType = javaType;
		this.varName = varName;
	}

	Class<?> getJavaType() {
		return javaType;
	}

	String getVarName() {
		return varName;
	}
}
