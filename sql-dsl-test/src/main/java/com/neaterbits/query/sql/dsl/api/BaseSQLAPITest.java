package com.neaterbits.query.sql.dsl.api;

import java.util.List;
import java.util.function.Function;

import com.neaterbits.query.sql.dsl.api.MultiBuilt;
import com.neaterbits.query.sql.dsl.api.ISharedPreparedQueryOps;
import com.neaterbits.query.sql.dsl.api.SingleBuilt;

public abstract class BaseSQLAPITest {

	protected final <T> void checkSelectOneOrNull(DataConfig ds, T expected, SingleBuilt<T> query, Function<ISharedPreparedQueryOps<T>, T> execute) {
		Checks.checkSelectOneOrNull(ds, expected, query, execute);
	}

	protected final <T> void checkSelectListUnordered(DataConfig ds, MultiBuilt<T> query, Function<ISharedPreparedQueryOps<List<T>>, List<T>> execute, @SuppressWarnings("unchecked") T ... expected) {
		Checks.checkSelectListUnordered(ds, query, execute, expected);
	}

	protected final <T> void checkSelectListOrdered(DataConfig ds, MultiBuilt<T> query, Function<ISharedPreparedQueryOps<List<T>>, List<T>> execute, @SuppressWarnings("unchecked") T ... expected) {
		Checks.checkSelectListOrdered(ds, query, execute, expected);
	}
	
	private <T> List<T> checkSelectListCommon(DataConfig ds, MultiBuilt<T> query, Function<ISharedPreparedQueryOps<List<T>>, List<T>> execute, @SuppressWarnings("unchecked") T ... expected) {
		return Checks.checkSelectListCommon(ds, query, execute, expected);
	}
}
