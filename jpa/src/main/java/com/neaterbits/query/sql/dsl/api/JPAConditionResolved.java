package com.neaterbits.query.sql.dsl.api;

final class JPAConditionResolved extends JPACondition {

	JPAConditionResolved(String prefix) {
		super(prefix);
	}

	@Override
	void resolve(StringBuilder sb, ParamValueResolver resolver) {
		sb.append(getPrefix());
	}
}
