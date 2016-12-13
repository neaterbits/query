package com.neaterbits.query.sql.dsl.api;

final class CollectedJoinConditionClasses extends CollectedJoinCondition {
	CollectedJoinConditionClasses(JoinType joinType, Class<?> leftType, FunctionGetter leftGetter, Class<?> rightType, FunctionGetter rightGetter) {
		super(joinType, leftType, leftGetter, rightType, rightGetter);
	}

}
