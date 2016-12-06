package com.neaterbits.query.sql.dsl.api;

final class CompiledQueryResultEntity extends CompiledQueryResult {

	CompiledQueryResultEntity(QueryResultEntity original) {
		super(original);
	}

	@Override
	<T, R> R visit(CompiledQueryResultVisitor<T, R> visitor, T param) {
		return visitor.onEntity(this, param);
	}
}
