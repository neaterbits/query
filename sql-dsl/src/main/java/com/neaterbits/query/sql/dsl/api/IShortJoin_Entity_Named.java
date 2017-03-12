package com.neaterbits.query.sql.dsl.api;

public interface IShortJoin_Entity_Named<
				MODEL,
				RESULT,
				JOIN_FROM,
				RET_TYPE extends IShortJoin_Entity_Named<MODEL, RESULT, JOIN_FROM, RET_TYPE>>

	extends IShortJoin_Named_Base<MODEL, RESULT, JOIN_FROM, RET_TYPE> {

}
