package com.neaterbits.query.sql.dsl.api;

public interface IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>

	extends 
		ISharedResult_Mapped_Undecided_Base<
				MODEL,
				RESULT,
				IShortResult_Mapped_Multi_Named<MODEL, RESULT>,
				IShortResult_Mapped_Multi_Alias<MODEL, RESULT>,
				IShortResult_Mapped_Multi_Undecided<MODEL, RESULT>>,

		ISQLLogical_WhereOrJoin_MultiMapped_Undecided_And_Function<MODEL, RESULT, IShortJoin_Condition_MultiMapped_Undecided<MODEL, RESULT>>/* ,
		TODO: IShortJoin_MultiMapped_Alias_Initial<MODEL, RESULT> */
	{

}
