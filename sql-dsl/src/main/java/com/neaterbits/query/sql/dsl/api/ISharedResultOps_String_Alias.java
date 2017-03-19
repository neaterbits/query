package com.neaterbits.query.sql.dsl.api;

public interface ISharedResultOps_String_Alias<
	MODEL,
	RESULT,
	RET extends ISharedFunction_After<MODEL, RESULT>>
	
	extends
    	ISharedMapResultOps_Comparable_Alias<MODEL, RESULT, String, RET>,
    
		ISharedOperands_String_Alias<
				MODEL,
				RESULT,
				
				RET,
				
				ISharedResultOps_String_Alias<MODEL, RESULT, RET>
				>
	{

}
