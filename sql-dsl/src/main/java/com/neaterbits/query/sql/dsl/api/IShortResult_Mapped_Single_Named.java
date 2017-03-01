package com.neaterbits.query.sql.dsl.api;

public interface IShortResult_Mapped_Single_Named<MODEL, RESULT>
	extends ISharedSelectSourceBuilder<MODEL, RESULT>, // TODO: really select-source? needed for type-checking  

	ISQLLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT>
{

}
