package com.neaterbits.query.sql.dsl.api;

final class CompiledResultProcessing {

	private final CompiledGroupBy groupBy;
	private final CompiledOrderBy orderBy;

	CompiledResultProcessing(CompiledGroupBy groupBy, CompiledOrderBy orderBy) {
		
		if (groupBy == null && orderBy == null) {
			throw new IllegalArgumentException("No input");
		}
		
		this.groupBy = groupBy;
		this.orderBy = orderBy;
	}

	CompiledGroupBy getGroupBy() {
		return groupBy;
	}

	CompiledOrderBy getOrderBy() {
		return orderBy;
	}
}
