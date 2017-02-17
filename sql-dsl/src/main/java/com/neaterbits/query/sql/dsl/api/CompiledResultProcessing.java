package com.neaterbits.query.sql.dsl.api;

final class CompiledResultProcessing {

	private final CompiledGroupBy groupBy;
	private final CompiledHaving  having;
	private final CompiledOrderBy orderBy;

	CompiledResultProcessing(CompiledGroupBy groupBy, CompiledHaving having, CompiledOrderBy orderBy) {
		
		if (groupBy == null && orderBy == null) {
			throw new IllegalArgumentException("No input");
		}
		
		if (having != null && groupBy == null) {
			throw new IllegalArgumentException("having without groupby");
		}
		
		this.groupBy = groupBy;
		this.having = having;
		this.orderBy = orderBy;
	}

	CompiledGroupBy getGroupBy() {
		return groupBy;
	}

	CompiledHaving getHaving() {
		return having;
	}

	CompiledOrderBy getOrderBy() {
		return orderBy;
	}
}
