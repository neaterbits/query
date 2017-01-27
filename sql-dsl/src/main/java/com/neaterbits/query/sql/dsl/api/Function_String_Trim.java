package com.neaterbits.query.sql.dsl.api;

final class Function_String_Trim extends Function_String {

	static final Function_String_Trim INSTANCE = new Function_String_Trim();
	
	private Function_String_Trim() {
		
	}

	@Override
	<T, R> R visit(FunctionVisitor<T, R> visitor, T param) {
		return visitor.onStringTrim(this, param);
	}
}
