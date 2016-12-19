package com.neaterbits.query.sql.dsl.api;

public interface JoinConditionTable<MODEL, RESULT, LEFT, RIGHT>
		extends JoinCondition,
				WhereClauseBuilderTable<MODEL, RESULT> {

	JoinConditionTable<MODEL, RESULT, LEFT, RIGHT>
		on(CollectionFunction<LEFT, RIGHT>  joinCollection);

	
	JoinConditionTable<MODEL, RESULT, LEFT, RIGHT>
			compare(IntegerFunction<LEFT>  left, IntegerFunction<RIGHT> right);
	
}