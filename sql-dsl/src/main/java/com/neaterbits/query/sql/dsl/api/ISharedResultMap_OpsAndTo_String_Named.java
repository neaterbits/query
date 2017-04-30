package com.neaterbits.query.sql.dsl.api;

public interface ISharedResultMap_OpsAndTo_String_Named<
		MODEL,
		RESULT,
		RET extends ISharedFunction_After<MODEL, RESULT>>
		
		extends
		
		    ISharedResultMap_To_Comparable_Named<MODEL, RESULT, String, RET>,
		    
			ISharedOperands_String_Named_All<
					MODEL,
					RESULT,
					
					RET,
					
					ISharedResultMap_OpsAndTo_String_Named<MODEL, RESULT, RET>,
					ISharedResultMap_OpsAndTo_String_Named<MODEL, RESULT, RET>
			>
		
{



}
