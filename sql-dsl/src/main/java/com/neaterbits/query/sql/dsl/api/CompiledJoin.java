package com.neaterbits.query.sql.dsl.api;

public class CompiledJoin {

	private final CollectedJoin original;
	
	CompiledJoin(CollectedJoin original) {
		if (original == null) {
			throw new IllegalArgumentException("original == null");
		}
		
		this.original = original;
	}

	CollectedJoin getOriginal() {
		return original;
	}
	
}
