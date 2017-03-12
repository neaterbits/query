package com.neaterbits.query.sql.dsl.api;

final class CollectedJoinCondition_Comparison_Named extends CollectedJoinCondition_Comparison {

	CollectedJoinCondition_Comparison_Named(FunctionGetter leftGetter, FunctionGetter rightGetter, Collector_Joins subJoins) {
		super(leftGetter, rightGetter, subJoins);
	}
}
