package com.neaterbits.query.sql.dsl.api;

public interface IAdhocJoinSub<MODEL, RESULT, LEFT, RIGHT>
			extends ISharedJoin_Condition_Named<MODEL, RESULT, LEFT, RIGHT, IAdhocJoinSubOrCondition<MODEL, RESULT, LEFT, RIGHT>> {

}
