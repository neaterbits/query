package com.neaterbits.query.sql.dsl.api;

final class CompiledSelectSourceAlias extends CompiledSelectSource {

	private final IAlias alias;
	
	CompiledSelectSourceAlias(SelectSourceAliasesImpl selectSource, IAlias alias, String name, int idx) {
		super(selectSource, alias.getType(), name, idx);
		
		this.alias = alias;
	}

	IAlias getAlias() {
		return alias;
	}
}
