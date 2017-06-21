package com.neaterbits.query.sql.dsl.api;

public interface IShortResult_Mapped_Single_Undecided<MODEL, RESULT>

	extends
			ISharedSelectSourceBuilder<MODEL, RESULT>,
			
			// Named
			
			//ISharedResult_Mapped_Named_Base<MODEL, RESULT, IShortResult_Mapped_Single_Named<MODEL,RESULT>>,
			ISharedMap_Initial_Named<MODEL, RESULT, IShortResult_Mapped_Single_Named<MODEL,RESULT>>,
			ISQLLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT>,
			IShortJoin_Single_Named_Initial<MODEL, RESULT>,
			
			// Alias
			
			// ISharedResult_Mapped_Alias_Base<MODEL, RESULT, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedMap_Initial_Alias<MODEL, RESULT, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISQLLogical_WhereOrJoin_SingleResult_Alias<MODEL, RESULT>,
			//ISQLLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL, RESULT>,
			IShortJoin_Single_Alias_Initial<MODEL, RESULT> {

}
