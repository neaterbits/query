package com.neaterbits.query.sql.dsl.api;

final class CollectedJoin_Named extends CollectedJoin {

	CollectedJoin_Named(EJoinType joinType, Class<?> leftType, Class<?> rightType) {
		super(joinType, leftType, rightType);
	}
}
