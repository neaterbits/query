package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;

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
	
	@Override
	void walk(Consumer<PreparedQueryConditionComparison> consumer) {
		sub.walk(consumer);
	}
}
