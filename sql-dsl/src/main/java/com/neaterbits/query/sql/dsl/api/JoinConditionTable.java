package com.neaterbits.query.sql.dsl.api;

public interface JoinConditionTable<MODEL, RESULT, LEFT, RIGHT>
		extends IClassicJoinCondition,
				IClassicWhereClauseBuilderTable<MODEL, RESULT> {

	JoinConditionTable<MODEL, RESULT, LEFT, RIGHT>
		on(CollectionFunction<LEFT, RIGHT>  joinCollection);

	
	JoinConditionTable<MODEL, RESULT, LEFT, RIGHT>
			compare(IFunctionInteger<LEFT>  left, IFunctionInteger<RIGHT> right);
	
}