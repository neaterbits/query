package com.neaterbits.query.sql.dsl.api;

final class ResultMapperOps_Numeric_Sub< 
	MODEL,
	RESULT,
	R extends Comparable<R>,
	
	RET extends ISharedFunction_After<MODEL, RESULT>>

	extends ResultMapperOps_Numeric<
		MODEL,
		RESULT,
		R,
		RET,
		ISharedSubOperandsBuilder_Numeric_Next_Named<MODEL, RESULT, R, RET>
		>

	implements
		ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, RET>,
		ISharedSubOperandsBuilder_Numeric_Next_Named<MODEL, RESULT, R, RET> {

	ResultMapperOps_Numeric_Sub(Expression expression) {
		super(expression, true);
	}
}

