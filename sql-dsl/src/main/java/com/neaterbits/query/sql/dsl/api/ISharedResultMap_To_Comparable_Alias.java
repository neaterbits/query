package com.neaterbits.query.sql.dsl.api;

public interface ISharedResultMap_To_Comparable_Alias<
	MODEL,
	RESULT,
	R extends Comparable<R>,
	RET extends ISharedFunction_After<MODEL, RESULT>>
	
	extends
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedResultMap_To<MODEL, RESULT, R, RET> // Can always do "to" instead of op

{



}
