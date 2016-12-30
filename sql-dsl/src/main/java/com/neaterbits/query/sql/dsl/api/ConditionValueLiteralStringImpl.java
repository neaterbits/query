package com.neaterbits.query.sql.dsl.api;

final class ConditionValueLiteralStringImpl extends BaseConditionValueLiteralImpl<String> {

	ConditionValueLiteralStringImpl(String literal) {
		super(literal);
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
