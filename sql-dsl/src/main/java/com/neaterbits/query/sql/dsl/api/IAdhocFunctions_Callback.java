package com.neaterbits.query.sql.dsl.api;

interface IAdhocFunctions_Callback<MODEL, RESULT, RET extends ISharedLogical_Base<MODEL,RESULT>>
		extends ISharedFunctions_Callback_Named<MODEL, RESULT, RET, AdhocFunctions<MODEL, RESULT, ?, ?, ?, ?, ?>> {

}
