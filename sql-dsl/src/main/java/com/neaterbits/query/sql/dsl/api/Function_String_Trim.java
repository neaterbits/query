package com.neaterbits.query.sql.dsl.api;

import com.neaterbits.query.sql.dsl.api.entity.ScalarType;

final class Function_String_Trim extends Function_String {

	static final Function_String_Trim INSTANCE = new Function_String_Trim();
	
	private Function_String_Trim() {
		
	}

	@Override
	<T, R> R visit(FunctionVisitor<T, R> visitor, T param) {
		return visitor.onStringTrim(this, param);
	}

	@Override
	Object applyTo(ScalarType type, Object value) {
		
		if (type != ScalarType.STRING) {
			throw new UnsupportedOperationException("Unknown datatype " + type);
		}
		
		return ((String)value).trim();
	}
}
