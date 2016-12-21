package com.neaterbits.query.sql.dsl.api;

public interface IClassicJoinConditionAlias<MODEL, RESULT>
		extends IClassicJoinCondition,
				IClassicJoinClauseAlias<MODEL, RESULT>,
				IClassicWhereClauseBuilderAlias<MODEL, RESULT> {
	
	
	IClassicJoinConditionAlias<MODEL, RESULT> on(CollectionSupplier joinCollection);
	
	IClassicJoinConditionAlias<MODEL, RESULT> compare(IntegerSupplier left, IntegerSupplier right);

	IClassicJoinConditionAlias<MODEL, RESULT> compare(LongSupplier left, LongSupplier right);
}
