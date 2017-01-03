package com.neaterbits.query.sql.dsl.api;

public interface ISharedJoinConditionNamed<
							MODEL, RESULT,
							LEFT, RIGHT,
							CONDITION extends ISharedJoinConditionNamed<MODEL, RESULT, LEFT, RIGHT, CONDITION>> {


	CONDITION on(CollectionFunction<LEFT, RIGHT>  joinCollection);


	CONDITION compare(IFunctionInteger<LEFT>  left, IFunctionInteger<RIGHT> right);
	
}
