package com.neaterbits.query.sql.dsl.api;

import com.neaterbits.query.sql.dsl.api.entity.ScalarType;

public class Function_String_Length extends Function_String {

	static final Function_String_Length INSTANCE = new Function_String_Length();
	
	private Function_String_Length() {
		
	}

	@Override
	<T, R> R visit(FunctionVisitor<T, R> visitor, T param) {
		return visitor.onStringLength(this, param);
	}

	@Override
	Object applyTo(ScalarType type, Object value) {
		
		if (type != ScalarType.STRING) {
			throw new UnsupportedOperationException("Unknown datatype " + type);
		}
		
		return ((String)value).length();
	}
}
