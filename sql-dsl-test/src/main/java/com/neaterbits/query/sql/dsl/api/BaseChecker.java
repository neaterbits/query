package com.neaterbits.query.sql.dsl.api;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

abstract class BaseChecker implements QueryTestDSCheck {

	@Override
	public final <T> void checkOne(SingleBuilt<T> query, Function<ISharedPreparedQueryOps<T>, T> execute, T expected) {
		check(ds -> Checks.checkSelectOneOrNull(ds, expected, query, execute));
	}

	@Override
	public final <T> void checkListUnordered(MultiBuilt<T> query, @SuppressWarnings("unchecked") T... expected) {
		check(ds -> Checks.checkSelectListUnordered(ds, query, q -> q.execute(), expected));
	}
	
	@Override
	public final <T> void checkListUnordered(MultiBuilt<T> query, Supplier<List<T>> expected) {
		check(ds -> {
			Checks.checkSelectListUnordered(ds, query, q -> q.execute(), expected.get());
		});
	}
	

	@Override
	public final <T> void checkListOrdered(MultiBuilt<T> query, @SuppressWarnings("unchecked") T... expected) {
		check(ds -> Checks.checkSelectListOrdered(ds, query, q -> q.execute(), expected));
	}
	
	@Override
	public <T> void checkListOrdered(MultiBuilt<T> query, Supplier<List<T>> expected) {
		check(ds -> {
			Checks.checkSelectListOrdered(ds, query, q -> q.execute(), expected.get());
		});
	}

	@Override
	public final <T> void checkListUnordered(MultiBuilt<T> query, Function<ISharedPreparedQueryOps<List<T>>, List<T>> execute,
			@SuppressWarnings("unchecked") T... expected) {

		check(ds -> Checks.checkSelectListUnordered(ds, query, execute, expected));
	}

	@Override
	public final <T> void checkListOrdered(MultiBuilt<T> query, Function<ISharedPreparedQueryOps<List<T>>, List<T>> execute,
			@SuppressWarnings("unchecked") T... expected) {
		
		check(ds -> Checks.checkSelectListOrdered(ds, query, execute, expected));
	}

	@Override
	public final <T> void checkOne(SingleBuilt<T> query, T expected) {
		check(ds -> Checks.checkSelectOneOrNull(ds, expected, query, q -> q.execute()));
	}

	@Override
	public final <T> void checkOne(SingleBuilt<T> query, Supplier<T> expected) {
		check(ds -> Checks.checkSelectOneOrNull(ds, expected.get(), query, q -> q.execute()));
	}
}
