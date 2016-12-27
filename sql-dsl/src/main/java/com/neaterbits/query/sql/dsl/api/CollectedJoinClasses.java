package com.neaterbits.query.sql.dsl.api;

final class CollectedJoinClasses extends CollectedJoin {

	CollectedJoinClasses(EJoinType joinType, Class<?> leftType, Class<?> rightType) {
		super(joinType, leftType, rightType);
	}
}
