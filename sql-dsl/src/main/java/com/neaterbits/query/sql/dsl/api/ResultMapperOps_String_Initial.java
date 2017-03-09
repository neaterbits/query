package com.neaterbits.query.sql.dsl.api;

public class ResultMapperOps_String_Initial< 
		MODEL,
		RESULT,
		RET extends ISharedFunction_After<MODEL, RESULT>>
	
	extends ResultMapperOps_String<
		MODEL,
		RESULT,
		RET,
		ISharedResultOps_String_Named<MODEL, RESULT, RET>
		>
	
	implements ISharedResultOps_String_Named<MODEL, RESULT, RET>
	
	{
	
	// TODO go over constructor calls and use static utility methods? 
	ResultMapperOps_String_Initial(Expression expression, IMappingCollector<MODEL, RESULT> impl) {
		super(expression, impl);
	}
}
