package com.neaterbits.query.sql.dsl.api;

public interface ISharedMap_OpsAndTo_Comparable_Alias<
	MODEL,
	RESULT,
	R extends Comparable<R>,
	RET extends ISharedFunction_After<MODEL, RESULT>>
	
	extends ISharedMap_To_Comparable_Alias<MODEL, RESULT, R, RET>,
			ISharedOperands_Numeric_Alias_All<
				MODEL,
				RESULT,
				
				RET,
				
				ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, R, RET>,
				ISharedMap_OpsAndTo_Comparable_Alias<MODEL, RESULT, R, RET>
			>
{

}
