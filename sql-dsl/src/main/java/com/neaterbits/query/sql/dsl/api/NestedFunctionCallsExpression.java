package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

// A set of nested function calls, eg. sqrt().abs(foo::getBar)
final class NestedFunctionCallsExpression extends Expression {

	private final CollectedFunctions functions;
	private final FieldExpression field;

	NestedFunctionCallsExpression(CollectedFunctions functions, Function<?, ?> getter) {
		this(functions, new FieldExpression(getter));
	}
	
	NestedFunctionCallsExpression(CollectedFunctions functions, Supplier<?> getter) {
		this(functions, new FieldExpression(getter));
	}
	
	NestedFunctionCallsExpression(CollectedFunctions functions, FieldExpression field) {

		if (functions == null) {
			throw new IllegalArgumentException("functions == null");
		}

		this.functions = functions;
		this.field = field;
	}

	CollectedFunctions getFunctions() {
		return functions;
	}
	
	FieldExpression getField() {
		return field;
	}

	@Override
	<T, R> R visit(ExpressionVisitor<T, R> visitor, T param) {
		return visitor.onNestedFunctionCalls(this, param);
	}
}
