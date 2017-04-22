package com.neaterbits.query.sql.dsl.api;

import com.neaterbits.query.sql.dsl.api.entity.ScalarType;

public class Function_String_Substring extends Function_String {

	static final Function_String_Substring INSTANCE = new Function_String_Substring();
	
	private Function_String_Substring() {
		
	}

	@Override
	<T, R> R visit(FunctionVisitor<T, R> visitor, T param) {
		return visitor.onStringSubstring(this, param);
	}

	@Override
	Object applyTo(ScalarType type, Object value) {
		throw new UnsupportedOperationException("TODO - multiple values");
	}
}
