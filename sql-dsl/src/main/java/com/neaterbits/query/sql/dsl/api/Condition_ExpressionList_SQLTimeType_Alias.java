package com.neaterbits.query.sql.dsl.api;

final class Condition_ExpressionList_SQLTimeType_Alias<

	MODEL,
	RESULT,
	R, // extends Comparable<R>,
	
	RET extends ISharedLogical_Base<MODEL, RESULT>>

	extends Conditions_ExpressionList_Base<
		MODEL,
		RESULT,
		R,
		
		RET,
		
		ISharedLogical_Base<MODEL, RESULT>,
		RET,
		
		//ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, R, RET>,
		//ISharedCondition_OpsAndComp_String_Named<MODEL, RESULT, RET>, // String
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
		ISharedFunction_Next<MODEL, RESULT, RET>
	// TODO: gives error
		/*
		ISharedCondition_OpsAndComp_SQLTimeType_Alias<MODEL, RESULT, java.sql.Date, RET>,
		ISharedCondition_OpsAndComp_SQLTimeType_Alias<MODEL, RESULT, java.sql.Time, RET>,
		ISharedCondition_OpsAndComp_SQLTimeType_Alias<MODEL, RESULT, java.sql.Timestamp, RET>
		*/
		
	>
	
	implements 
		ISharedCondition_OpsAndComp_SQLTimeType_Alias<MODEL, RESULT, R, RET>
	
	

{
	Condition_ExpressionList_SQLTimeType_Alias(Expression expression) {
		super(expression, EFieldAccessType.NAMED);
	}
}
