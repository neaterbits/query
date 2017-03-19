package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;


public class QueryTestDSCombined extends QueryTestDS {

	private final List<Supplier<QueryTestDSStore>> suppliers; 

	public QueryTestDSCombined() {
		suppliers = new ArrayList<>();
	}

	public QueryTestDSCombined add(QueryTestDSStore store) {
		add(() -> store);
		
		return this;
	}
	
	public QueryTestDSCombined add(Supplier<QueryTestDSStore> supplier) {

		if (supplier == null) {
			throw new IllegalArgumentException("supplier == null");
		}

		suppliers.add(supplier);
		
		return this;
	}
	
	@SafeVarargs
	public QueryTestDSCombined(Supplier<QueryTestDSStore> ... suppliers) {
		
		if (suppliers.length == 0) {
			throw new IllegalArgumentException("no suppliers");
		}
		
		this.suppliers = Arrays.asList(suppliers);
	}
	
	@Override
	public QueryTestDSCheck store(Consumer<QueryTestDSBuilder> dsBuilder) {

		// Nothing to do as we must store and check in same loop
		// since we have to delete previous run before running next
		/*
		
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
		*/

		final List<QueryTestDSStore> stores = new ArrayList<>(suppliers.size());
		
		for (Supplier<QueryTestDSStore> supplier : suppliers) {

			final QueryTestDSStore store = supplier.get();
			
			if (store == null) {
				throw new IllegalStateException("store == null");
			}
			
			stores.add(store);
		}
		
		return new Checker(dsBuilder, stores);
	}
	
	/*
	private class Checker implements QueryTestDSCheck {
		private final List<QueryTestDSCheck> checks;
		
		Checker(List<QueryTestDSCheck> storeToCheckMap) {
			this.checks = storeToCheckMap;
		}
		
		@Override
		public void check(Consumer<DataConfig> testBuilder) {
			for (QueryTestDSCheck dsCheck : checks) {
				dsCheck.check(testBuilder);
			}
		}
	}
	*/

	private class Checker extends BaseChecker implements QueryTestDSCheck {
		private final Consumer<QueryTestDSBuilder> dsBuilder;
		private final List<QueryTestDSStore> stores;
		
		Checker(Consumer<QueryTestDSBuilder> dsBuilder, List<QueryTestDSStore> stores) {
			this.dsBuilder = dsBuilder;
			this.stores = stores;
		}
		
		@Override
		public void check(Consumer<DataConfig> testBuilder) {
			

			for (QueryTestDSStore store : stores) {
				final QueryTestDSCheck dsCheck = store.store(dsBuilder);

				dsCheck.check(testBuilder);
			}
		}
	}
}
