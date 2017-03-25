package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

abstract class BaseChecker implements QueryTestDSCheck {

	abstract void execute(Consumer<DataConfig> testBuilder);

	abstract List<?> executeSql(String sql);
	
	@Override
	public <T> QueryTestDSCheck dump(Class<T> entity) {
		
		// List all from database
		final List<T> list = new ArrayList<>();

		// a bit roundabout way, just use checker without any asserts 
		execute(ds -> {
			final List<T> entities = IShortSelect.list(entity).build().prepare(ds).execute();
			
			list.addAll(entities);
		});
		
		// Print entities
		System.out.println("%% Entities for " + entity.getName() + ":");
		for (int i = 0; i < list.size(); ++ i) {
			System.out.println("%% " + i + ": " + list.get(i));
		}
		
		return this;
	}
	
	@Override
	public <T> QueryTestDSCheck dump(String sql) {
		// Print entities
		System.out.println("%% Result for " + sql + ":");
		
		final List<?> list = executeSql(sql);
		
		for (int i = 0; i < list.size(); ++ i) {
			final Object row = list.get(i);
			
			final String s = row instanceof Object []
				? Arrays.toString((Object[])row)
			    : row.toString();
				
			System.out.println("%% " + i + ": " + s);
		}
		
		return this;
	}


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

	@Override
	public <T extends Comparable<T>> void checkAggregate(SingleBuilt<T> query, T expected) {
		
		checkOne(query, expected);
		
	}

	@Override
	public <T extends Comparable<T>> void checkAggregate(SingleBuilt<T> query,
			Function<ISharedPreparedQueryOps<T>, T> execute, T expected) {

		checkOne(query, execute, expected);
	}
}
