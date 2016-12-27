package com.neaterbits.query.sql.dsl.api;

public interface IClassicJoinConditionTable<MODEL, RESULT, LEFT, RIGHT>
		extends IClassicJoinCondition,
				ISharedJoinConditionTable<MODEL, RESULT, LEFT, RIGHT, IClassicJoinConditionTable<MODEL, RESULT, LEFT, RIGHT>>,
				IClassicWhereClauseBuilderTable<MODEL, RESULT> {
	
}