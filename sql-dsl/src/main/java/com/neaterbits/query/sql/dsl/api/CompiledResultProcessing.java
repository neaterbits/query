package com.neaterbits.query.sql.dsl.api;

final class CompiledResultProcessing {

	private final CompiledResultFields groupBy;
	private final CompiledResultFields orderBy;

	CompiledResultProcessing(CompiledResultFields groupBy, CompiledResultFields orderBy) {
		
		if (groupBy == null && orderBy == null) {
			throw new IllegalArgumentException("No input");
		}
		
		this.groupBy = groupBy;
		this.orderBy = orderBy;
	}

	CompiledResultFields getGroupBy() {
		return groupBy;
	}

	CompiledResultFields getOrderBy() {
		return orderBy;
	}
}
