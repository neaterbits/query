package com.neaterbits.query.sql.dsl.api;

final class CompiledQueryResultMapped extends CompiledQueryResult {

	private final CompiledMappings mappings;
	
	public CompiledQueryResultMapped(QueryResultMapped original, CompiledMappings mappings) {
		super(original);
		
		if (mappings == null) {
			throw new IllegalArgumentException("mappings == null");
		}

		this.mappings = mappings;
	}

	CompiledMappings getMappings() {
		return mappings;
	}

	@Override
	<T, R> R visit(CompiledQueryResultVisitor<T, R> visitor, T param) {
		return visitor.onMapped(this, param);
	}
}
