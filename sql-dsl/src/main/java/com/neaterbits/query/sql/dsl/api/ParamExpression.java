package com.neaterbits.query.sql.dsl.api;

final class ParamExpression extends Expression {

	private final Param<?> param;

	ParamExpression(Param<?> param) {
		
		if (param == null) {
			throw new IllegalArgumentException("param == null");
		}
		
		this.param = param;
	}
	
	Param<?> getParam() {
		return param;
	}

	@Override
	<T, R> R visit(ExpressionVisitor<T, R> visitor, T param) {
		return visitor.onParam(this, param);
	}
}
