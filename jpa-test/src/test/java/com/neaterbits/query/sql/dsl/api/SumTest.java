package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

@Deprecated
public interface SumTest {

	public default <T, R> String sum(Function<T, R> func) {
		return null;
	}
	
	public default <T> T sqrt(String s) {
		return null;
	}
	
	
}
