package com.neaterbits.query.sql.dsl.api;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public interface QueryTestDSCheck {

	<T> QueryTestDSCheck dump(Class<T> entity);

	<T> QueryTestDSCheck dump(String sql);
	
	QueryTestDSCheck remove(Object ... instances);
	
	void check(Consumer<DataConfig> testBuilder);
	
	

	<T> void checkOne(SingleBuilt<T> query, T expected);

	// When must lazy-initialize with ID
	<T> void checkOne(SingleBuilt<T> query, Supplier<T> expected);

	<T> void checkOne(SingleBuilt<T> query, Function<ISharedPreparedQueryOps<T>, T> execute, T expected);

	<T extends Comparable<T>> void checkAggregate(SingleBuilt<T> query, T expected);

	<T extends Comparable<T>> void checkAggregate(SingleBuilt<T> query, Function<ISharedPreparedQueryOps<T>, T> execute, T expected);
	
	<T> void checkListUnordered(MultiBuilt<T> query, @SuppressWarnings("unchecked") T ... expected);

	<T> void checkListUnordered(MultiBuilt<T> query, Supplier<List<T>> expected);

	<T> void checkListOrdered(MultiBuilt<T> query, @SuppressWarnings("unchecked") T ... expected);

	<T> void checkListOrdered(MultiBuilt<T> query, Supplier<List<T>> expected);
	
	<T> void checkListUnordered(MultiBuilt<T> query, Function<ISharedPreparedQueryOps<List<T>>, List<T>> execute, @SuppressWarnings("unchecked") T ... expected);
	
	<T> void checkListOrdered(MultiBuilt<T> query, Function<ISharedPreparedQueryOps<List<T>>, List<T>> execute, @SuppressWarnings("unchecked") T ... expected);
}
