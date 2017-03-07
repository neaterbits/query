package com.neaterbits.query.sql.dsl.api;

// A set of nested function calls, eg. sqrt().abs(foo::getBar)
final class NestedFunctionCallsExpression extends Expression {

	private final CollectedFunctions functions;

	NestedFunctionCallsExpression(CollectedFunctions functions) {

		if (functions == null) {
			throw new IllegalArgumentException("functions == null");
		}

		this.functions = functions;
	}

	CollectedFunctions getFunctions() {
		return functions;
	}

	@Override
	<T, R> R visit(ExpressionVisitor<T, R> visitor, T param) {
		return visitor.onNestedFunctionCalls(this, param);
	}
}
