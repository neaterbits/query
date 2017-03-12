package com.neaterbits.query.sql.dsl.api;

final class CollectedJoinCondition_Comparison_Alias extends CollectedJoinCondition_Comparison {

	CollectedJoinCondition_Comparison_Alias(SupplierGetter leftGetter, SupplierGetter rightGetter, Collector_Joins subJoins) {
		super(leftGetter, rightGetter, subJoins);
	}
}
