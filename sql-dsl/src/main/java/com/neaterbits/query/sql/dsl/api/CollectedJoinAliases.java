package com.neaterbits.query.sql.dsl.api;

public class CollectedJoinAliases extends CollectedJoin {

	private final IAlias leftAlias;
	private final IAlias rightAlias;
	
	public CollectedJoinAliases(JoinType joinType, IAlias leftAlias, SupplierGetter leftGetter, IAlias rightAlias, SupplierGetter rightGetter) {
		super(joinType, leftAlias.getType(), leftGetter, rightAlias.getType(), rightGetter);

		this.leftAlias = leftAlias;
		this.rightAlias = rightAlias;
	}
}
