package com.neaterbits.query.sql.dsl.api;


public interface ISharedResultOps_Numeric_Undecided<
	MODEL,
	RESULT,
	R extends Comparable<R>,
	NAMED_RET extends ISharedFunction_After<MODEL, RESULT>,
	ALIAS_RET extends ISharedFunction_After<MODEL, RESULT>,
	
	NAMED_TYPE_RET extends ISharedFunction_After<MODEL, RESULT>>


	
	extends
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedResultMapperTo<MODEL, RESULT, R, RET>, // Can always do "to" instead of op
		ISharedOperands_Undecided<
				MODEL,
				RESULT,
				
				R,
				
				RET,
				
				ISharedOperands_Numeric_Named<MODEL, RESULT, R, NAMED_RET>,
				ISharedResultOps_Numeric_Alias<MODEL, RESULT, R, RET>
				
				> {
	
	
	
}
