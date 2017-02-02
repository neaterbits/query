package com.neaterbits.query.sql.dsl.api;

import com.neaterbits.query.sql.dsl.api.entity.ScalarType;

final class Function_Arithmetic_Sqrt extends Function_Arithmetic {

	static final Function_Arithmetic_Sqrt INSTANCE = new Function_Arithmetic_Sqrt();
	
	private Function_Arithmetic_Sqrt() {
	}

	@Override
	final <T, R> R visit(FunctionVisitor<T, R> visitor, T param) {
		return visitor.onArithmeticSqrt(this, param);
	}

	@Override
	Object applyTo(ScalarType type, Object value) {
		
		final Object ret;
		
		switch (type) {
		case SHORT:
			ret = (short)Math.sqrt((short)value);
			break;
			
		case INTEGER:
			ret = (int)Math.sqrt((int)value);
			break;
			
		case LONG:
			ret = (long)Math.sqrt((long)value);
			break;

			
		// TODO: BigDecimal
			
		default:
			throw new UnsupportedOperationException("Unknown datatype " + type);
			
		}
		
		return ret;
	}
}
