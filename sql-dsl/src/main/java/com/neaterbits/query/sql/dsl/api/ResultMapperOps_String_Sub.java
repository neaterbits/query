package com.neaterbits.query.sql.dsl.api;

public class ResultMapperOps_String_Sub< 
		MODEL,
		RESULT,
		RET extends ISharedFunction_After<MODEL, RESULT>>
	
	extends ResultMapperOps_String<
			MODEL,
			RESULT,
			RET,
			ISharedSubOperandsBuilder_String_Next_Named<MODEL, RESULT, RET>
		>
	
	implements
		ISharedSubOperand_String_Ops_Named<MODEL, RESULT, RET>,
		ISharedSubOperandsBuilder_String_Next_Named<MODEL, RESULT, RET> {
	
	ResultMapperOps_String_Sub(Expression expression) {
		super(expression, true);
	}
}

