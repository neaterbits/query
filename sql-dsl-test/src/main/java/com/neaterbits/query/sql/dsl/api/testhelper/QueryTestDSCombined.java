package com.neaterbits.query.sql.dsl.api.testhelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.neaterbits.query.sql.dsl.api.QueryDataSource;

public class QueryTestDSCombined extends QueryTestDS {

	private final List<Supplier<QueryTestDSStore>> suppliers; 
	
	@SafeVarargs
	public QueryTestDSCombined(Supplier<QueryTestDSStore> ... suppliers) {
		
		if (suppliers.length == 0) {
			throw new IllegalArgumentException("no suppliers");
		}
		
		this.suppliers = Arrays.asList(suppliers);
	}
	
	@Override
	public QueryTestDSCheck store(Consumer<QueryTestDSBuilder> dsBuilder) {
		
		final List<QueryTestDSCheck> checks = new ArrayList<>(suppliers.size());
		
		for (Supplier<QueryTestDSStore> supplier : suppliers) {

			final QueryTestDSStore store = supplier.get();
			final QueryTestDSCheck check = store.store(dsBuilder);

			if (check == null) {
				throw new IllegalStateException("check == null");
			}

			checks.add(check);
		}
		
		
		return new Checker(checks);
	}
	
	private class Checker implements QueryTestDSCheck {
		private final List<QueryTestDSCheck> checks;
		
		Checker(List<QueryTestDSCheck> storeToCheckMap) {
			this.checks = storeToCheckMap;
		}
		
		@Override
		public void check(Consumer<QueryDataSource> testBuilder) {
			for (QueryTestDSCheck dsCheck : checks) {
				dsCheck.check(testBuilder);
			}
		}
	}
}