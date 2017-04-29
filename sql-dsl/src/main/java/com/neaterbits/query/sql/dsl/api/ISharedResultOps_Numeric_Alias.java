package com.neaterbits.query.sql.dsl.api;

@Deprecated // in use?
public interface ISharedResultOps_Numeric_Alias<
		MODEL,
		RESULT,
		R extends Comparable<R>,
		RET extends ISharedFunction_After<MODEL, RESULT>>
		
		extends
			ISharedFunction_Next<MODEL, RESULT, RET>,
			ISharedResultMapperTo<MODEL, RESULT, R, RET>, // Can always do "to" instead of op
			ISharedOperands_Numeric_Alias<
					MODEL,
					RESULT,
					
					RET,
					
					ISharedResultOps_Numeric_Alias<MODEL, RESULT, R, RET>
					
					> {



}
