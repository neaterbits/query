package com.neaterbits.query.sql.dsl.api;

final class CollectedJoin_Alias extends CollectedJoin {

	private final IAlias leftAlias;
	private final IAlias rightAlias;
	
	CollectedJoin_Alias(EJoinType joinType, IAlias leftAlias, IAlias rightAlias, Collector_Joins subJoins) {
		super(joinType, leftAlias.getType(), rightAlias.getType(), subJoins);

		this.leftAlias = leftAlias;
		this.rightAlias = rightAlias;
	}

	IAlias getLeftAlias() {
		return leftAlias;
	}

	IAlias getRightAlias() {
		return rightAlias;
	}
}
