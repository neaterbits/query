package com.neaterbits.query.sql.dsl.api;

final class Function_Aggregate extends FunctionBase {

	private final EAggregateFunction function;

	static final Function_Aggregate COUNT = new Function_Aggregate(EAggregateFunction.COUNT);
	static final Function_Aggregate MIN = new Function_Aggregate(EAggregateFunction.MIN);
	static final Function_Aggregate MAX = new Function_Aggregate(EAggregateFunction.MAX);
	static final Function_Aggregate SUM = new Function_Aggregate(EAggregateFunction.SUM);
	static final Function_Aggregate AVG = new Function_Aggregate(EAggregateFunction.AVG);
	
	private Function_Aggregate(EAggregateFunction function) {
		
		if (function == null) {
			throw new IllegalArgumentException("function == null");
		}

		this.function = function;
	}

	EAggregateFunction getFunction() {
		return function;
	}
	
	
}
