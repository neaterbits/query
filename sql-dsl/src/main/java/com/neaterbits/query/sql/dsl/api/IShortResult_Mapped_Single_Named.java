package com.neaterbits.query.sql.dsl.api;

public interface IShortResult_Mapped_Single_Named<MODEL, RESULT>
	extends

		ISharedResult_Mapped_Named_Base<MODEL, RESULT, IShortResult_Mapped_Single_Named<MODEL,RESULT>>,
		ISQLLogical_WhereOrJoin_SingleResult_Named_And_Function<MODEL, RESULT>
{

	
}
