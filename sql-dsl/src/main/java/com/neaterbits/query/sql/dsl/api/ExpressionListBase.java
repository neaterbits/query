package com.neaterbits.query.sql.dsl.api;

import java.util.List;

final class ExpressionListBase<T> {
	private final List<T> expressions;
	private final List<ArithmeticOperator> operators;
	
	ExpressionListBase(List<T> expressions, List<ArithmeticOperator> operators) {
		this(expressions, operators, true);
	}
	
	ExpressionListBase(List<T> expressions, List<ArithmeticOperator> operators, boolean check) {

		if (check) {
			if (expressions.size() < 2) {
				throw new IllegalArgumentException("Expression list with only 1 item");
			}
			
			if (operators.size() != expressions.size() - 1) {
				throw new IllegalArgumentException("Expected one less operator than expression");
			}
		}
		
		this.expressions = expressions;
		this.operators = operators;
	}
	
	List<T> getExpressions() {
		return expressions;
	}

	List<ArithmeticOperator> getOperators() {
		return operators;
	}
}
