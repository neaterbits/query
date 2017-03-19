package com.neaterbits.query.sql.dsl.api;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public interface QueryTestDSCheck {

	void check(Consumer<DataConfig> testBuilder);

	<T> void checkOne(SingleBuilt<T> query, T expected);
	
	<T> void checkOne(SingleBuilt<T> query, Function<ISharedPreparedQueryOps<T>, T> execute, T expected);

	<T> void checkListUnordered(MultiBuilt<T> query, @SuppressWarnings("unchecked") T ... expected);
	
	<T> void checkListOrdered(MultiBuilt<T> query, @SuppressWarnings("unchecked") T ... expected);
	
	<T> void checkListUnordered(MultiBuilt<T> query, Function<ISharedPreparedQueryOps<List<T>>, List<T>> execute, @SuppressWarnings("unchecked") T ... expected);
	
	<T> void checkListOrdered(MultiBuilt<T> query, Function<ISharedPreparedQueryOps<List<T>>, List<T>> execute, @SuppressWarnings("unchecked") T ... expected);
}
