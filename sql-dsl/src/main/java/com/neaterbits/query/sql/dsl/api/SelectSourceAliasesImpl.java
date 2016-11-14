package com.neaterbits.query.sql.dsl.api;

final class SelectSourceAliasesImpl extends SelectSourceImpl {
	
	private final Alias<?> [] aliases;

	SelectSourceAliasesImpl(Alias<?> [] aliases) {
		
		if (aliases == null) {
			throw new IllegalArgumentException("aliases == null");
		}
		
		this.aliases = aliases;
	}

	Alias<?> [] getAliases() {
		return aliases;
	}
}
