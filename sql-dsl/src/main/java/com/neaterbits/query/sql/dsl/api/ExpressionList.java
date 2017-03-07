package com.neaterbits.query.sql.dsl.api;

import java.util.List;

final class ExpressionList extends Expression {

	private final List<Expression> expressions;
	private final List<ArithmeticOperator> operators;
	
	public ExpressionList(List<Expression> expressions, List<ArithmeticOperator> operators) {

		if (expressions.size() < 2) {
			throw new IllegalArgumentException("Expression list with only 1 item");
		}
		
		if (operators.size() != expressions.size() - 1) {
			throw new IllegalArgumentException("Expected one less operator than expression");
		}
		
		this.expressions = expressions;
		this.operators = operators;
	}
	
	List<Expression> getExpressions() {
		return expressions;
	}

	List<ArithmeticOperator> getOperators() {
		return operators;
	}

	@Override
	<T, R> R visit(ExpressionVisitor<T, R> visitor, T param) {
		return visitor.onList(this, param);
	}
}
