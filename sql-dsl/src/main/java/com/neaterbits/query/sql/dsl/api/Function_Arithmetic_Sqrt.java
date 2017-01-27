package com.neaterbits.query.sql.dsl.api;

final class Function_Arithmetic_Sqrt extends Function_Arithmetic {

	static final Function_Arithmetic_Sqrt INSTANCE = new Function_Arithmetic_Sqrt();
	
	private Function_Arithmetic_Sqrt() {
		
	}

	@Override
	<T, R> R visit(FunctionVisitor<T, R> visitor, T param) {
		return visitor.onArithmeticSqrt(this, param);
	}
}
