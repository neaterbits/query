package com.neaterbits.query.sql.dsl.api;

public interface ISharedResultOps_Numeric_Named<
	MODEL,
	RESULT,
	R extends Comparable<R>,
	RET extends ISharedSelectSourceBuilder<MODEL, RESULT>>

	extends ISharedResultOps_Comparable_Named<MODEL, RESULT, R, RET>,
			ISharedOperands_Numeric_Named<
				MODEL,
				RESULT,
				
				R,
				
				RET,
				
				ISharedResultOps_Numeric_Named<MODEL, RESULT, R, RET>
			
			>

{



}
