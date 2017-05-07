package com.neaterbits.query.sql.dsl.api;

public interface ISharedCondition_OpsAndComp_String_Alias<MODEL, RESULT, L extends ISharedLogical_Base<MODEL, RESULT>>
	
	extends ISharedCondition_Comp_String_Alias<MODEL, RESULT, L>,
			ISharedOperands_String_Alias_All<
				MODEL,
				RESULT,
				
				L,
				
				ISharedCondition_OpsAndComp_String_Alias<MODEL, RESULT, L>,
				ISharedCondition_OpsAndComp_String_Alias<MODEL, RESULT, L>
			> {

}
