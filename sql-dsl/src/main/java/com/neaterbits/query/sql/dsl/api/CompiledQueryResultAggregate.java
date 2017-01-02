package com.neaterbits.query.sql.dsl.api;

final class CompiledQueryResultAggregate extends CompiledQueryResult {

	private final CompiledFieldReference field;
	
	private final QueryResultAggregate original;
	
	CompiledQueryResultAggregate(QueryResultAggregate original, CompiledFieldReference field) {
		super(original);
		
		if (field == null) {
			throw new IllegalArgumentException("field == null");
		}

		this.field = field;
		this.original = original;
	}

	CompiledFieldReference getField() {
		return field;
	}

	@Override
	<T, R> R visit(CompiledQueryResultVisitor<T, R> visitor, T param) {
		return visitor.onAggregate(this, param);
	}

	final ENumericType getInputNumericType() {
		return original.getInputNumericType();
	}

	final ENumericType getOutputNumericType() {
		return original.getOutputNumericType();
	}

	final EAggregateFunction getAggregateFunction() {
		return original.getAggregateFunction();
	}
}
