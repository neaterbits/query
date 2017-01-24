package com.neaterbits.query.sql.dsl.api;

final class ConditionValue_Literal_String extends ConditionValue_Literal_Base<String> {

	ConditionValue_Literal_String(String literal) {
		super(literal);
	}

	@Override
	EConditionValue getType() {
		return EConditionValue.SCALAR_LITERAL;
	}


	@Override
	<T, R> R visit(ConditionValueVisitor<T, R> visitor, T param) {
		return visitor.onLiteralString(this, param);
	}

	@Override
	public String toString() {
		return '"' + getLiteral() + '"';
	}
}
