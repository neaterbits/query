package com.neaterbits.query.sql.dsl.api;

public interface IShortResult_Mapped_Single_Alias<MODEL, RESULT>

	extends ISharedSelectSourceBuilder<MODEL, RESULT>, // TODO: really select-source? needed for type-checking
	
	ISQLLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL, RESULT>

	{

}
