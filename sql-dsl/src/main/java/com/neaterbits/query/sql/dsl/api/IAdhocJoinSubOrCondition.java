package com.neaterbits.query.sql.dsl.api;

/**
 * 
 * Returned by .compare() for join conditions like the below 
 * 
 *	.compare(Foo::getValue, Bar::getFooId)
 *	.where(Bar::getBaz).startsWith("Foo"))
 *
 * 
 * @param <LEFT> Foo in the example above
 * @param<RIGHT> Bar in the exmaple above
 *
 */

public interface IAdhocJoinSubOrCondition<MODEL, RESULT, LEFT, RIGHT>
		extends IAdhocJoinSub<MODEL, RESULT, LEFT, RIGHT>,

				IAdhocWhereOrJoin<MODEL, RESULT, RIGHT, IAdhocAndOrLogicalClauses<MODEL,RESULT, RIGHT>,
				IAdhocWhereOrJoinSub<MODEL,RESULT, RIGHT>> {
	
	

}
