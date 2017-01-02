package com.neaterbits.query.sql.dsl.api;

final class CompiledQueryResultEntity extends CompiledQueryResult {

	private final QueryResultEntity original;
	
	CompiledQueryResultEntity(QueryResultEntity original) {
		super(original);

		this.original = original;
	}

	@Override
	<T, R> R visit(CompiledQueryResultVisitor<T, R> visitor, T param) {
		return visitor.onEntity(this, param);
	}
	
	final SelectSource getSelectSource() {
		return original.getSelectSource();
	}
}
