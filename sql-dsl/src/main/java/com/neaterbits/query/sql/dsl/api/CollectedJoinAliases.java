package com.neaterbits.query.sql.dsl.api;

final class CollectedJoinAliases extends CollectedJoin {

	private final IAlias leftAlias;
	private final IAlias rightAlias;
	
	CollectedJoinAliases(EJoinType joinType, IAlias leftAlias, IAlias rightAlias) {
		super(joinType, leftAlias.getType(), rightAlias.getType());

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
