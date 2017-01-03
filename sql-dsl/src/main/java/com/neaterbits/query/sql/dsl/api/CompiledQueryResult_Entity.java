package com.neaterbits.query.sql.dsl.api;

final class CompiledQueryResult_Entity extends CompiledQueryResult {

	private final CollectedQueryResult_Entity original;
	
	CompiledQueryResult_Entity(CollectedQueryResult_Entity original) {
		super(original);

		this.original = original;
	}

	@Override
	<T, R> R visit(CompiledQueryResultVisitor<T, R> visitor, T param) {
		return visitor.onEntity(this, param);
	}
	
	final SharedSelectSource getSelectSource() {
		return original.getSelectSource();
	}
}
