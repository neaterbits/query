package com.neaterbits.query.sql.dsl.api;

public interface IShortJoin_Typed_Named<
				MODEL,
				RESULT,
				JOIN_FROM,
				RET_TYPE extends IShortJoin_Typed_Named<MODEL, RESULT, JOIN_FROM, RET_TYPE>>

	extends IShortJoin_Named_Base<MODEL, RESULT, JOIN_FROM, RET_TYPE> {

}
