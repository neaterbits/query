package com.neaterbits.query.sql.dsl.api;

final class CollectedJoinConditionAliases extends CollectedJoinCondition {

	CollectedJoinConditionAliases(SupplierGetter leftGetter, SupplierGetter rightGetter) {
		super(leftGetter, rightGetter);
	}

}
