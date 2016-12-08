package com.neaterbits.query.sql.dsl.api;

final class CollectedJoinClasses extends CollectedJoin {

	CollectedJoinClasses(JoinType joinType, Class<?> leftType, FunctionGetter leftGetter, Class<?> rightType, FunctionGetter rightGetter) {
		super(joinType, leftType, leftGetter, rightType, rightGetter);
	}
}
