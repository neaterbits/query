package com.neaterbits.query.sql.dsl.api;

public interface ISharedResultMap_OpsAndTo_SQLTimeType_Named<
		MODEL,
		RESULT,
		R, // extends Comparable<R>,
		RET extends ISharedFunction_After<MODEL, RESULT>>
		
		extends ISharedResultMap_To_SQLTimeType_Named<MODEL, RESULT, R, RET>
/*
,
				ISharedOperands_Numeric_Named<
					MODEL,
					RESULT,
					
					RET,
					
					ISharedResultMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, R, RET>
				>
*/
		{

}
