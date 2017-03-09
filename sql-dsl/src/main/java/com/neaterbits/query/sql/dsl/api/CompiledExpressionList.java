package com.neaterbits.query.sql.dsl.api;

import java.util.List;

final class CompiledExpressionList extends CompiledExpression {
	
	private final ExpressionListBase<CompiledExpression> list;
	
	CompiledExpressionList(List<CompiledExpression> expressions, List<ArithmeticOperator> operators) {
		this.list = new ExpressionListBase<>(expressions, operators);
	}

	List<CompiledExpression> getExpressions() {
		return list.getExpressions();
	}

	List<ArithmeticOperator> getOperators() {
		return list.getOperators();
	}

	@Override
	<T, R> R visit(CompiledExpressionVisitor<T, R> visitor, T param) {
		return visitor.onList(this, param);
	}

}
