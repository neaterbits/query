package com.neaterbits.query.sql.dsl.api;

public interface ISharedResultMap_OpsAndTo_String_Alias<
	MODEL,
	RESULT,
	RET extends ISharedFunction_After<MODEL, RESULT>>
	
	extends
    	ISharedResultMap_To_Comparable_Alias<MODEL, RESULT, String, RET>,
    
		ISharedOperands_String_Alias_All<
				MODEL,
				RESULT,
				
				RET,
				
				ISharedResultMap_OpsAndTo_String_Alias<MODEL, RESULT, RET>
				>
	{

}
