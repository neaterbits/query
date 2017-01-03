package com.neaterbits.query.sql.dsl.api;

final class CollectedJoinConditionComparisonNamed extends CollectedJoinConditionComparison {

	CollectedJoinConditionComparisonNamed(FunctionGetter leftGetter, FunctionGetter rightGetter) {
		super(leftGetter, rightGetter);
	}
}
