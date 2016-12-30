package com.neaterbits.query.sql.dsl.api;

final class PreparedQueryConditionNested extends PreparedQueryCondition {

	private final PreparedQueryConditionsBuilder sub;

	PreparedQueryConditionNested(PreparedQueryConditionsBuilder sub) {
		
		if (sub == null) {
			throw new IllegalArgumentException("sub == null");
		}

		this.sub = sub;
	}

	PreparedQueryConditionsBuilder getSub() {
		return sub;
	}

	@Override
	boolean isUnresolved() {
		return false;
	}
}
