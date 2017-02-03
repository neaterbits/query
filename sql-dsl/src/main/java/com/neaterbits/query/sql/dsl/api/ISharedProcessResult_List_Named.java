package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

public interface ISharedProcessResult_List_Named<MODEL, RESULT, RET> {

	<T, R> RET and(Function<T, R> function);
	
}
