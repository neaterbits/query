package com.neaterbits.query.sql.dsl.api;

final class ResultMapper_ExpressionList_String_Named<
		MODEL,
		RESULT,
		AFTER extends ISharedFunction_After<MODEL, RESULT>
/*,
		NEXT extends ISharedFunction_Next<MODEL, RESULT, AFTER> */>

	extends ResultMapper_ExpressionList_Base<MODEL, RESULT, String, AFTER,
	
		ISharedResultOps_String_Named<MODEL, RESULT, AFTER>,
	
		//NEXT, NEXT, NEXT, NEXT, NEXT, NEXT, NEXT,
		
		ISharedFunction_Next<MODEL, RESULT, AFTER>,
		ISharedFunction_Next<MODEL, RESULT, AFTER>,
		
		ISharedFunction_Next<MODEL, RESULT, AFTER>,
		ISharedFunction_Next<MODEL, RESULT, AFTER>,
		ISharedFunction_Next<MODEL, RESULT, AFTER>,
		ISharedFunction_Next<MODEL, RESULT, AFTER>,
		ISharedFunction_Next<MODEL, RESULT, AFTER>,
		
		ISharedResultOps_String_Named<MODEL, RESULT, AFTER>> 


	implements ISharedResultOps_String_Named<MODEL, RESULT, AFTER> {

	ResultMapper_ExpressionList_String_Named(Expression expression, IMappingCollector<MODEL, RESULT> impl) {
		super(expression, impl);
	}
}
