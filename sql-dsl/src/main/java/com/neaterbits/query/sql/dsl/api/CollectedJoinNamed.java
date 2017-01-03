package com.neaterbits.query.sql.dsl.api;

final class CollectedJoinNamed extends CollectedJoin {

	CollectedJoinNamed(EJoinType joinType, Class<?> leftType, Class<?> rightType) {
		super(joinType, leftType, rightType);
	}
}
