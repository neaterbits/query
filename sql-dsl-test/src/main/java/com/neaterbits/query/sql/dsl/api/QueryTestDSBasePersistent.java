package com.neaterbits.query.sql.dsl.api;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import com.neaterbits.query.sql.dsl.api.QueryDataSource;
import com.neaterbits.query.sql.dsl.api.TransactionalDataStore;

public abstract class QueryTestDSBasePersistent<CTX, ENTITIES, TRANSACTION>

		extends QueryTestDS {

	private final TransactionalDataStore<CTX, ENTITIES, TRANSACTION> dataStore;

	protected QueryTestDSBasePersistent(TransactionalDataStore<CTX, ENTITIES, TRANSACTION> dataStore) {
		if (dataStore == null) {
			throw new IllegalArgumentException("dataStore == null");
		}

		this.dataStore = dataStore;
	}

	@Override
	public final QueryTestDSCheck store(Consumer<QueryTestDSBuilder> dsBuilder) {

		final BiFunction<TransactionalDataStore<CTX, ENTITIES, TRANSACTION>, Object, Object> getPrimaryKey
				= (dataStore, instance) -> dataStore.getPrimaryKey(instance);
		
		final QueryTestDSBuilderPersistentImpl<TransactionalDataStore<CTX, ENTITIES, TRANSACTION>> b = 
				new QueryTestDSBuilderPersistentImpl<>(dataStore, getPrimaryKey);

		final ENTITIES em = dataStore.openEntities();

		final List<TestInstance> instances = b.getInstances();
		
		boolean ok = false;

		final TRANSACTION transaction = dataStore.beginTransaction(em);
		
		try {
			
			dsBuilder.accept(b);
			
			//dumpMetaModel(em.getMetamodel());
		
			for (TestInstance instance : instances) {
				dataStore.persist(em, instance.getInstance());
			}

			dataStore.commitTransaction(transaction);
			ok = true;
		}
		finally {
			
			try {
				if (!ok && dataStore.isTransactionActive(transaction)) {
					dataStore.rollbackTransaction(transaction);
				}
			}
			finally {
				dataStore.closeEntities(em);
			}
		}

		return new Checker(instances);
	}


	private class Checker implements QueryTestDSCheck {
		private final List<TestInstance> instances;

		public Checker(List<TestInstance> instances) {
			this.instances = instances;
		}

		@Override
		public void check(Consumer<QueryDataSource> testBuilder) {

			final ENTITIES em = dataStore.openEntities();
			final QueryDataSource dataSource = dataStore.createDataSource(em);

			try {
				testBuilder.accept(dataSource);
			}
			finally {
				safelyDeleteInstances(em);
			}
		}
		
		private void safelyDeleteInstances(ENTITIES em) {
			try {
				final TRANSACTION transaction = dataStore.beginTransaction(em);

				for (TestInstance instance : instances) {

					dataStore.remove(em, instance.getInstance(), instance.getPK());
				}
				
				dataStore.commitTransaction(transaction);
			}
			finally {
				dataStore.closeEntities(em);
			}
		}
	}
}
