package com.neaterbits.query.sql.dsl.api;

import java.util.List;
import java.util.function.Function;

public abstract class BaseSQLAPITest extends CompilabilityTest {

	protected final <T> void checkSelectOneOrNull(DataConfig ds, T expected, SingleBuilt<T> query, Function<ISharedPreparedQuery_Ops<T>, T> execute) {
		Checks.checkSelectOneOrNull(ds, expected, query, execute);
	}

	protected final <T> void checkSelectListUnordered(DataConfig ds, MultiBuilt<T> query, Function<ISharedPreparedQuery_Ops<List<T>>, List<T>> execute, @SuppressWarnings("unchecked") T ... expected) {
		Checks.checkSelectListUnordered(ds, query, execute, expected);
	}

	protected final <T> void checkSelectListOrdered(DataConfig ds, MultiBuilt<T> query, Function<ISharedPreparedQuery_Ops<List<T>>, List<T>> execute, @SuppressWarnings("unchecked") T ... expected) {
		Checks.checkSelectListOrdered(ds, query, execute, expected);
	}
}
