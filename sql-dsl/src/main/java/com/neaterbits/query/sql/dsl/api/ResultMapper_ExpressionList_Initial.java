package com.neaterbits.query.sql.dsl.api;

public class ResultMapper_ExpressionList_Initial< 
		MODEL,
		RESULT,
		RET extends ISharedFunction_After<MODEL, RESULT>>
	
	extends ResultMapper_ExpressionList_Base<
		MODEL,
		RESULT,
		String,
		RET,

		ISharedResultOps_String_Named<MODEL, RESULT, RET>,

		
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		
		ISharedResultOps_String_Named<MODEL, RESULT, RET>


		>
	
	 {
	
	// TODO go over constructor calls and use static utility methods? 
	ResultMapper_ExpressionList_Initial(IMappingCollector<MODEL, RESULT> impl) {
		super(impl);
	}
}
