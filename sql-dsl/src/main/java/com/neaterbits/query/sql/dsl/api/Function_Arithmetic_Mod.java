package com.neaterbits.query.sql.dsl.api;

import com.neaterbits.query.sql.dsl.api.entity.ScalarType;

public class Function_Arithmetic_Mod extends Function_Arithmetic {

	static final Function_Arithmetic_Mod INSTANCE = new Function_Arithmetic_Mod();
	
	private Function_Arithmetic_Mod() {
		
	}

	@Override
	<T, R> R visit(FunctionVisitor<T, R> visitor, T param) {
		return visitor.onArithmeticMod(this, param);
	}

	@Override
	Object applyTo(ScalarType type, Object value) {
		throw new UnsupportedOperationException("TODO - multiple params");
	}
}