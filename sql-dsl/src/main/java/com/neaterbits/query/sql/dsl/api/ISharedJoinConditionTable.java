package com.neaterbits.query.sql.dsl.api;

public interface ISharedJoinConditionTable<
							MODEL, RESULT,
							LEFT, RIGHT,
							CONDITION extends ISharedJoinConditionTable<MODEL, RESULT, LEFT, RIGHT, CONDITION>> {


	CONDITION on(CollectionFunction<LEFT, RIGHT>  joinCollection);


	CONDITION compare(IFunctionInteger<LEFT>  left, IFunctionInteger<RIGHT> right);
	
}
