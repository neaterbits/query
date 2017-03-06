package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Aggregate_NoParam_Base<MODEL, RESULT, RET> {

	RET sum();
	
	RET min();
	
	RET max();
	
	RET avg();
	
	RET count();
}
