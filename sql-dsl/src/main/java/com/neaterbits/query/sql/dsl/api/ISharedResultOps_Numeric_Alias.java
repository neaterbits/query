package com.neaterbits.query.sql.dsl.api;

public interface ISharedResultOps_Numeric_Alias<
		MODEL,
		RESULT,
		R extends Comparable<R>,
		RET extends ISharedSelectSourceBuilder<MODEL, RESULT>>
		
		extends
			ISharedFunction_Next<MODEL, RESULT, RET>,
			ISharedResultMapperTo<MODEL, RESULT, R, RET>, // Can always do "to" instead of op
			ISharedOperands_Alias<
					MODEL,
					RESULT,
					
					R,
					
					RET,
					
					ISharedResultOps_Numeric_Alias<MODEL, RESULT, R, RET>
					
					> {



}
