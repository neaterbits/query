package com.neaterbits.query.sql.dsl.api;

public interface IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>

	extends 
		ISharedMap_Initial_NoParam_Undecided2<
				MODEL,
				RESULT,
				IShortResult_Mapped_Multi_Named<MODEL, RESULT>,
				IShortResult_Mapped_Multi_Alias<MODEL, RESULT>,
				IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>>,

		ISQLLogical_WhereOrJoin_MultiMapped_Undecided_And_Function<
			MODEL,
			RESULT,
			
			Object, Object, IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>, // Really only in use in classic
		
			IShortJoin_Condition_MultiMapped_Alias<MODEL, RESULT>>,
			IShortJoin_MultiMapped_Alias_Initial<MODEL, RESULT>
	{

}
