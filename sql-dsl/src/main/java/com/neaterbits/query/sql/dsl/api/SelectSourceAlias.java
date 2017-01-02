package com.neaterbits.query.sql.dsl.api;

final class SelectSourceAlias extends SelectSource {

	private final IAlias alias;

	SelectSourceAlias(IAlias alias) {
		
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
