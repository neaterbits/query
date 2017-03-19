package com.neaterbits.query.sql.dsl.api;

public interface IShortLogical_WhereOrJoin_SingleResult_Entity_Named<MODEL, RESULT, JOIN_FROM>
	
	extends ISQLLogical_WhereOrJoin_SingleResult_Named_And_Function<MODEL, RESULT>,
			IShortJoin_Entity_Named<MODEL, RESULT, JOIN_FROM, IShortLogical_WhereOrJoin_SingleResult_Entity_Named<MODEL, RESULT, JOIN_FROM>> {

	// inner-join and left-join to same type
	
}
