package com.neaterbits.query.sql.dsl.api;


public interface ISharedCondition_Equality_Alias<MODEL, RESULT, R, L extends ISharedLogical_Base<MODEL, RESULT>>
	extends ISharedCondition_Equality_All<MODEL, RESULT, R, L>{

	/*
	L isEqualTo(Supplier<R> other);

	L isNotEqualTo(Supplier<R> other);
	*/
	
	
}
