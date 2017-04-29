package com.neaterbits.query.sql.dsl.api;

public interface ISharedResultMap_OpsAndTo_Comparable_Named<
	MODEL,
	RESULT,
	R extends Comparable<R>,
	RET extends ISharedFunction_After<MODEL, RESULT>>

	extends ISharedResultMap_To_Comparable_Named<MODEL, RESULT, R, RET>,
			ISharedOperands_Numeric_Named_All<
				MODEL,
				RESULT,
				
				RET,
				
				ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, R, RET>
			>

{

}
