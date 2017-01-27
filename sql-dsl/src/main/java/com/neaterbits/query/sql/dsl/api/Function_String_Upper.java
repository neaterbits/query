package com.neaterbits.query.sql.dsl.api;

final class Function_String_Upper extends Function_String {

	static final Function_String_Upper INSTANCE = new Function_String_Upper();
	
	private Function_String_Upper() {
		
	}

	@Override
	<T, R> R visit(FunctionVisitor<T, R> visitor, T param) {
		return visitor.onStringUpper(this, param);
	}
}
