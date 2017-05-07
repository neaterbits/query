package com.neaterbits.query.sql.dsl.api;

final class Condition_ExpressionList_ByteArray_Named<

		MODEL,
		RESULT,
		R, // extends Comparable<R>,
		
		RET extends ISharedLogical_Base<MODEL, RESULT>>

	extends Conditions_ExpressionList_Base<
		MODEL,
		RESULT,
		R,
		
		RET,
		
		RET,
		ISharedLogical_Base<MODEL, RESULT>, // Alias
		ISharedLogical_Base<MODEL, RESULT>,
		
		//ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, R, RET>,
		//ISharedCondition_OpsAndComp_String_Named<MODEL, RESULT, RET>, // String
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		
		
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		
	
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>

	
	>
	
	implements 
		ISharedCondition_OpsAndComp_ByteArray_Named<MODEL, RESULT, RET>
	
	
	
	{
	
	
	Condition_ExpressionList_ByteArray_Named(Collector_Conditions_GroupBy<MODEL, RESULT, ?> clause, Expression expression) {
		super(clause, expression, EFieldAccessType.NAMED);
	}
}
