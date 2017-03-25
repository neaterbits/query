package com.neaterbits.query.sql.dsl.api;

public interface IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_TYPE>
	extends 
			ISQLLogical_WhereOrJoin_SingleResult_Named_And_Function<MODEL, RESULT>,
			IShortJoin_Typed_Named<MODEL, RESULT, JOIN_TYPE, IShortLogical_WhereOrJoin_SingleResult_Named<MODEL, RESULT, JOIN_TYPE>> 
		{

	// inner-join and left-join to same type
	
}
