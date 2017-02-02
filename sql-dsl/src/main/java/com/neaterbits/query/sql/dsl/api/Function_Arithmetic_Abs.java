package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

import com.neaterbits.query.sql.dsl.api.entity.ScalarType;

final class Function_Arithmetic_Abs extends Function_Arithmetic {

	static final Function_Arithmetic_Abs INSTANCE = new Function_Arithmetic_Abs();
	
	private Function_Arithmetic_Abs() {
		
	}

	@Override
	<T, R> R visit(FunctionVisitor<T, R> visitor, T param) {
		return visitor.onArithmeticAbs(this, param);
	}

	@Override
	Object applyTo(ScalarType type, Object value) {
		
		final Object ret;
		
		switch (type) {
		case SHORT:
			ret = Math.abs((short)value);
			break;
			
		case INTEGER:
			ret = Math.abs((int)value);
			break;
			
		case LONG:
			ret = Math.abs((long)value);
			break;

		case DECIMAL:
			ret = ((BigDecimal)value).abs();
			break;
			
		default:
			throw new UnsupportedOperationException("Unknown datatype " + type);
			
		}
		
		return ret;
	}
	
}
