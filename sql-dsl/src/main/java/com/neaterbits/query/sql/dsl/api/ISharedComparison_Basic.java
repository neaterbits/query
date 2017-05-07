package com.neaterbits.query.sql.dsl.api;

public interface ISharedComparison_Basic<MODEL, RESULT, R, L extends ISharedLogical_Base<MODEL, RESULT>> 
	extends ISharedComparison_Base<MODEL, RESULT, R, L> {

	L isNull();
	
	L isNotNull();
	
}
