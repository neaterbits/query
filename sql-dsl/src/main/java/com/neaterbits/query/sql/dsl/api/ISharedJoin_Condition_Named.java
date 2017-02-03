package com.neaterbits.query.sql.dsl.api;

public interface ISharedJoin_Condition_Named<
							MODEL, RESULT,
							LEFT, RIGHT,
							CONDITION extends ISharedJoin_Condition_Named<MODEL, RESULT, LEFT, RIGHT, CONDITION>> {


	CONDITION on(CollectionFunction<LEFT, RIGHT>  joinCollection);


	CONDITION compare(IFunctionInteger<LEFT>  left, IFunctionInteger<RIGHT> right);
	
}
