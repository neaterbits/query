package com.neaterbits.query.sql.dsl.api;

public interface ISharedResultMap_OpsAndTo_SQLTimeType_Alias<
		MODEL,
		RESULT,
		R, //  extends Comparable<R>,
		RET extends ISharedFunction_After<MODEL, RESULT>>
	
	extends ISharedMapResultOps_SQLTimeType_Alias<MODEL, RESULT, R, RET>,
			ISharedOperands_Numeric_Alias<
				MODEL,
				RESULT,
				
//				R,
				
				RET,
				
				ISharedResultMap_OpsAndTo_SQLTimeType_Alias<MODEL, RESULT, R, RET>
			>

{



}