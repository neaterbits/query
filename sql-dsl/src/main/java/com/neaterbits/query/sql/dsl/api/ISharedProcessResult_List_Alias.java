package com.neaterbits.query.sql.dsl.api;

import java.util.function.Supplier;

public interface ISharedProcessResult_List_Alias<MODEL, RESULT, RET> {

	<R> RET and(Supplier<R> function);
	
}
