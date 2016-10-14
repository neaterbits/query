package com.neaterbits.gui.sql.dsl.api.standalone;

import java.util.function.Function;

public interface FuncTest {

	<T> void foo(Function<T, String> func);
	
}
