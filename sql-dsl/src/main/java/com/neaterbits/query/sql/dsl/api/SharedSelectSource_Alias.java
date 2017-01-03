package com.neaterbits.query.sql.dsl.api;

final class SharedSelectSource_Alias extends SharedSelectSource {

	private final IAlias alias;

	SharedSelectSource_Alias(IAlias alias) {
		
		if (alias == null) {
			throw new IllegalArgumentException("alias == null");
		}
		
		this.alias = alias;
	}

	IAlias getAlias() {
		return alias;
	}

	@Override
	Class<?> getType() {
		return alias.getType();
	}
}
