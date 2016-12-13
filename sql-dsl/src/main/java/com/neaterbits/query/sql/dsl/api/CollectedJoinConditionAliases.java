package com.neaterbits.query.sql.dsl.api;

final class CollectedJoinConditionAliases extends CollectedJoinCondition {

	private final IAlias leftAlias;
	private final IAlias rightAlias;
	
	public CollectedJoinConditionAliases(JoinType joinType, IAlias leftAlias, SupplierGetter leftGetter, IAlias rightAlias, SupplierGetter rightGetter) {
		super(joinType, leftAlias.getType(), leftGetter, rightAlias.getType(), rightGetter);

		this.leftAlias = leftAlias;
		this.rightAlias = rightAlias;
	}

}
