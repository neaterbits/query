package com.neaterbits.query.sql.dsl.api;

public interface IClassicJoinConditionNamed<MODEL, RESULT, LEFT, RIGHT>
		extends IClassicJoinCondition,
				ISharedJoinConditionNamed<MODEL, RESULT, LEFT, RIGHT, IClassicJoinConditionNamed<MODEL, RESULT, LEFT, RIGHT>>,
				IClassicWhereClauseBuilderNamed<MODEL, RESULT> {
	
}