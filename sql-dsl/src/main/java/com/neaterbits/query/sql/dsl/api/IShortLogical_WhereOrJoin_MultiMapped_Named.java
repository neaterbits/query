package com.neaterbits.query.sql.dsl.api;

public interface IShortLogical_WhereOrJoin_MultiMapped_Named<MODEL, RESULT, JOIN_TYPE>
	extends ISQLLogical_WhereOrJoin_MultiMapped_Named_And_Function<MODEL, RESULT>,
			IShortJoin_Typed_Named<MODEL, RESULT, JOIN_TYPE, IShortLogical_WhereOrJoin_MultiMapped_Named<MODEL, RESULT, JOIN_TYPE>>{

}
