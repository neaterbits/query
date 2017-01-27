package com.neaterbits.query.sql.dsl.api;

final class Function_Arithmetic_Abs extends Function_Arithmetic {

	static final Function_Arithmetic_Abs INSTANCE = new Function_Arithmetic_Abs();
	
	private Function_Arithmetic_Abs() {
		
	}

	@Override
	<T, R> R visit(FunctionVisitor<T, R> visitor, T param) {
		return visitor.onArithmeticAbs(this, param);
	}
}
