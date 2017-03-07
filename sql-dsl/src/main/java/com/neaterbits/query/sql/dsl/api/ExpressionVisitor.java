package com.neaterbits.query.sql.dsl.api;

public interface ExpressionVisitor<T, R> {
	
	R onList(ExpressionList list, T param);
	
	R onField(FieldExpression field, T param);

	R onFunction(FunctionBase function, T param);

	R onNestedFunctionCalls(NestedFunctionCallsExpression nested, T param);
}
