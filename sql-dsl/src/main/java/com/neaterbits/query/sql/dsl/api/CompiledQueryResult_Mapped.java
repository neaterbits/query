package com.neaterbits.query.sql.dsl.api;

final class CompiledQueryResult_Mapped extends CompiledQueryResult {

	private final CompiledMappings mappings;
	
	public CompiledQueryResult_Mapped(CollectedQueryResult_Mapped original, CompiledMappings mappings) {
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
