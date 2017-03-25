package com.neaterbits.query.sql.dsl.api;

final class CompiledQueryResult_Aggregate extends CompiledQueryResult {

	private final CompiledFieldReference field;
	
	private final QueryResultAggregate original;
	
	CompiledQueryResult_Aggregate(QueryResultAggregate original, CompiledFieldReference field) {
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

	final EAggregateType getInputNumericType() {
		return original.getInputNumericType();
	}

	final EAggregateType getOutputNumericType() {
		return original.getOutputNumericType();
	}

	final EAggregateFunction getAggregateFunction() {
		return original.getAggregateFunction();
	}
}
