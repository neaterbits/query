package com.neaterbits.query.sql.dsl.api;


@Deprecated // no need for named / alias since returnes expressions
interface ISharedCollector_Functions_Callback_Named<MODEL, RESULT, RET extends ISharedFunction_After<MODEL, RESULT>>
	extends ISharedFunctions_Callback_Named<MODEL, RESULT, RET>{

}
