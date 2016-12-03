package com.neaterbits.query.sql.dsl.api;

final class CompiledSelectSourceAlias extends CompiledSelectSource {

	private final IAlias alias;
	
	CompiledSelectSourceAlias(IAlias alias, String name) {
		super(alias.getType(), name);
		
		this.alias = alias;
	}

	IAlias getAlias() {
		return alias;
	}
}
