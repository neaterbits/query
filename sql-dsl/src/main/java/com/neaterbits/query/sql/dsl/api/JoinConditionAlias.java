package com.neaterbits.query.sql.dsl.api;

public interface JoinConditionAlias<MODEL, RESULT>
		extends JoinCondition,
				JoinClauseAlias<MODEL, RESULT>,
				WhereClauseBuilderAlias<MODEL, RESULT> {
	
	
	JoinConditionAlias<MODEL, RESULT> on(CollectionSupplier joinCollection);
	
	JoinConditionAlias<MODEL, RESULT> compare(IntegerSupplier left, IntegerSupplier right);

	JoinConditionAlias<MODEL, RESULT> compare(LongSupplier left, LongSupplier right);
}
