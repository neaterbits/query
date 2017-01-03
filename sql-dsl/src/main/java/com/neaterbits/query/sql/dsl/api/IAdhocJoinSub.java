package com.neaterbits.query.sql.dsl.api;

public interface IAdhocJoinSub<MODEL, RESULT, LEFT, RIGHT>
			extends ISharedJoinConditionNamed<MODEL, RESULT, LEFT, RIGHT, IAdhocJoinSubOrCondition<MODEL, RESULT, LEFT, RIGHT>> {

}
