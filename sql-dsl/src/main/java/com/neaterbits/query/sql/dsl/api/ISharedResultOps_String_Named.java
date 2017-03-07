package com.neaterbits.query.sql.dsl.api;

public interface ISharedResultOps_String_Named<
		MODEL,
		RESULT,
		RET extends ISharedSelectSourceBuilder<MODEL, RESULT>>
		
		extends ISharedResultOps_Comparable_Named<MODEL, RESULT, String, RET>,
			ISharedOperands_String_Named<
					MODEL,
					RESULT,
					
					RET,
					
					ISharedResultOps_String_Named<MODEL, RESULT, RET>
					
					>
		
{



}
