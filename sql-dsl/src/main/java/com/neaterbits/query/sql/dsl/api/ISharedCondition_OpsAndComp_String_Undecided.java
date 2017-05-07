package com.neaterbits.query.sql.dsl.api;

public interface ISharedCondition_OpsAndComp_String_Undecided<MODEL, RESULT, L extends ISharedLogical_Base<MODEL, RESULT>>

	extends ISharedCondition_Comp_String_Undecided<MODEL, RESULT, L>,
			ISharedOperands_String_Undecided_All<
				MODEL,
				RESULT,
				
				L,
				
				ISharedCondition_OpsAndComp_String_Named<MODEL, RESULT, L>,
				ISharedCondition_OpsAndComp_String_Alias<MODEL, RESULT, L>,
				ISharedCondition_OpsAndComp_String_Undecided<MODEL, RESULT, L>
			> {

}
