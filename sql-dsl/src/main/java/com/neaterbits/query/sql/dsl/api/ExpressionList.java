package com.neaterbits.query.sql.dsl.api;

import java.util.List;

final class ExpressionList extends Expression {

	private final ExpressionListBase<Expression> list;

	ExpressionList(List<Expression> expressions, List<Operator> operators) {
		this(expressions, operators, true);
	}

	ExpressionList(List<Expression> expressions, List<Operator> operators, boolean check) {
		this.list = new ExpressionListBase<>(expressions, operators, check);
	}

	List<Expression> getExpressions() {
		return list.getExpressions();
	}

	List<Operator> getOperators() {
		return list.getOperators();
	}
	
	@Override
	<T, R> R visit(ExpressionVisitor<T, R> visitor, T param) {
		return visitor.onList(this, param);
	}
}
