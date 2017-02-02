package com.neaterbits.query.sql.dsl.api;

import com.neaterbits.query.sql.dsl.api.entity.ScalarType;

public class Function_String_Lower extends Function_String {

	static final Function_String_Lower INSTANCE = new Function_String_Lower();
	
	private Function_String_Lower() {
		
	}

	@Override
	<T, R> R visit(FunctionVisitor<T, R> visitor, T param) {
		return visitor.onStringLower(this, param);
	}

	@Override
	Object applyTo(ScalarType type, Object value) {
		
		if (type != ScalarType.STRING) {
			throw new UnsupportedOperationException("Unknown datatype " + type);
		}
		
		return ((String)value).toLowerCase();
	}
}
