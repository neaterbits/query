package com.neaterbits.query.sql.dsl.api;

public interface CompiledExpressionVisitor<T, R> {

	R onList(CompiledExpressionList list, T param);
	
	R onField(CompiledFieldExpression field, T param);
	
	R onValue(CompiledValueExpression value, T param);

	R onFunction(CompiledFunctionExpression function, T param);
	
}
