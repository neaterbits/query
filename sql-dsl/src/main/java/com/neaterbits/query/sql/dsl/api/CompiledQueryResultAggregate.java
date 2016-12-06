package com.neaterbits.query.sql.dsl.api;

final class CompiledQueryResultAggregate extends CompiledQueryResult {

	private final CompiledFieldReference field;
	
	CompiledQueryResultAggregate(QueryResultAggregate original, CompiledFieldReference field) {
		super(original);
		
		if (field == null) {
			throw new IllegalArgumentException("field == null");
		}

		this.field = field;
	}

	CompiledFieldReference getField() {
		return field;
	}

	@Override
	<T, R> R visit(CompiledQueryResultVisitor<T, R> visitor, T param) {
		return visitor.onAggregate(this, param);
	}
}
