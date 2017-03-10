package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

final class Collector_Expression_List_Sub<

	MODEL,
	RESULT,
	R extends Comparable<R>,
	
	RET extends ISharedFunction_After<MODEL, RESULT>>

	extends Collector_ExpressionList<

		MODEL,
		RESULT,

		R,
		RET,

		ISharedSubOperandsBuilder_Numeric_Next_Named<MODEL, RESULT, R, RET>,
		ISharedSubOperandsBuilder_String_Next_Named<MODEL, RESULT, RET>,

		ISharedSubOperandsBuilder_Numeric_Next_Named<MODEL, RESULT, Long, RET>,
		ISharedSubOperandsBuilder_Numeric_Next_Named<MODEL, RESULT, Long, RET>,

		ISharedSubOperandsBuilder_Numeric_Next_Named<MODEL, RESULT, Short, RET>,
		ISharedSubOperandsBuilder_Numeric_Next_Named<MODEL, RESULT, Integer, RET>,
		ISharedSubOperandsBuilder_Numeric_Next_Named<MODEL, RESULT, Long, RET>,
		ISharedSubOperandsBuilder_Numeric_Next_Named<MODEL, RESULT, Double, RET>,
		ISharedSubOperandsBuilder_Numeric_Next_Named<MODEL, RESULT, BigDecimal, RET>,
		ISharedSubOperandsBuilder_String_Next_Named<MODEL, RESULT, RET>
	>

	implements
		ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, RET>

/*, Cannot implement at this level
		ISharedSubOperandsBuilder_Numeric_Next_Named<MODEL, RESULT, R, RET>,
		ISharedSubOperandsBuilder_String_Next_Named<MODEL, RESULT, RET> 
		*/
		{

	Collector_Expression_List_Sub(Expression expression) {
		super(expression);
	}
}

