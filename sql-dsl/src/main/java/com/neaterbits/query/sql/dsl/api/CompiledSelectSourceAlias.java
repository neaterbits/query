package com.neaterbits.query.sql.dsl.api;

final class CompiledSelectSourceAlias extends CompiledSelectSource {

	private final IAlias alias;
	
	CompiledSelectSourceAlias(SelectSourceAliasesImpl selectSource, IAlias alias, String name) {
		super(selectSource, alias.getType(), name);
		
		this.alias = alias;
	}

	IAlias getAlias() {
		return alias;
	}
}
