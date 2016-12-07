package com.neaterbits.query.sql.dsl.api;

public class CollectedJoinAliases extends CollectedJoin {

	private final IAlias leftAlias;
	private final IAlias rightAlias;
	
	public CollectedJoinAliases(JoinType joinType, IAlias leftAlias, IAlias rightAlias) {
		super(joinType, leftAlias.getType(), rightAlias.getType());

		this.leftAlias = leftAlias;
		this.rightAlias = rightAlias;
	}
}
