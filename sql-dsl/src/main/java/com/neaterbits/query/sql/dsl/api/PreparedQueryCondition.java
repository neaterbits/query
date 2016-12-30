package com.neaterbits.query.sql.dsl.api;

import com.neaterbits.query.sql.dsl.api.PreparedQueryBuilder.FieldReference;

final class PreparedQueryCondition {

	private final FieldReference lhs;
	private final PreparedQueryConditionRHS rhs;
	
	PreparedQueryCondition(FieldReference lhs, PreparedQueryConditionRHS rhs) {
		
		if (lhs == null) {
			throw new IllegalArgumentException("lhs == null");
		}
		
		if (rhs == null) {
			throw new IllegalArgumentException("rhs == null");
		}
		
		this.lhs = lhs;
		this.rhs = rhs;
	}

	FieldReference getLhs() {
		return lhs;
	}

	PreparedQueryConditionRHS getRhs() {
		return rhs;
	}
}
