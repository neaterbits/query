package com.neaterbits.query.sql.dsl.api;

public interface ISharedCondition_OpsAndComp_Comparable_Undecided<MODEL, RESULT, R extends Comparable<R>, L extends ISharedLogical_Base<MODEL, RESULT>> 

	extends ISharedCondition_Comp_Comparable_Undecided<MODEL, RESULT, R, L>,
			ISharedOperands_Numeric_Undecided_All<
				MODEL,
				RESULT,
				
				L,
				
				ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, R, L>,
				ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, R, L>,
				ISharedCondition_OpsAndComp_Comparable_Undecided<MODEL, RESULT, R, L>
			>
{

}
