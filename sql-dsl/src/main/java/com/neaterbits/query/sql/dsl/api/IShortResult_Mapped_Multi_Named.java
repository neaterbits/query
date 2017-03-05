package com.neaterbits.query.sql.dsl.api;

public interface IShortResult_Mapped_Multi_Named<MODEL, RESULT>
	extends 
		ISharedResult_Mapped_Named_Base<MODEL, RESULT, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
		ISQLLogical_WhereOrJoin_MultiMapped_Named<MODEL, RESULT>

{

}
