package com.neaterbits.query.sql.dsl.api.standalone;

import java.util.function.Function;

public interface FuncTest {

	<T> void foo(Function<T, String> func);
	
}
