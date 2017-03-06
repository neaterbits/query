package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Aggregate_NoParam_Base<MODEL, RESULT, RET, SUM_RET, COUNT_RET> {

	SUM_RET sum();
	
	RET min();
	
	RET max();
	
	RET avg();
	
	COUNT_RET count();
}
