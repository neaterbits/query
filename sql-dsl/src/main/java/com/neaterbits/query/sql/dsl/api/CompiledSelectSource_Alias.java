package com.neaterbits.query.sql.dsl.api;

final class CompiledSelectSource_Alias extends CompiledSelectSource {

	private final IAlias alias;
	
	CompiledSelectSource_Alias(CollectedSelectSource_Aliases selectSource, IAlias alias, String name, int idx) {
		super(selectSource, alias.getType(), name, idx);
		
		this.alias = alias;
	}

	IAlias getAlias() {
		return alias;
	}
}
