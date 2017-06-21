package com.neaterbits.query.sql.dsl.api;


public interface ISharedMap_OpsAndTo_Comparable_Undecided<
	MODEL,
	RESULT,
	R extends Comparable<R>,
	RET extends ISharedFunction_After<MODEL, RESULT>>
	
	extends ISharedMap_To_Comparable_Named<MODEL, RESULT, R, RET>,
			ISharedOperands_Numeric_Undecided_All<
				MODEL,
				RESULT,
				
				RET,
				
				ISharedMap_OpsAndTo_Comparable_Named<MODEL, RESULT, R, RET>,
				ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, R, RET>,
				ISharedMap_OpsAndTo_Comparable_Undecided<MODEL, RESULT, R, RET>
			>

{

}
