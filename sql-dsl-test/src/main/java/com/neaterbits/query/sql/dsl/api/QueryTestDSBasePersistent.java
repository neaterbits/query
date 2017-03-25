package com.neaterbits.query.sql.dsl.api;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import com.neaterbits.query.sql.dsl.api.TransactionalDataStore;

public abstract class QueryTestDSBasePersistent<CTX, ENTITIES, TRANSACTION>

		extends QueryTestDS {

	private final DataConfig dataConfig;
	private final TransactionalDataStore<CTX, ENTITIES, TRANSACTION> dataStore;

	protected QueryTestDSBasePersistent(DataConfig dataConfig, TransactionalDataStore<CTX, ENTITIES, TRANSACTION> dataStore) {
		
		if (dataConfig == null) {
			throw new IllegalArgumentException("dataConfig == null");
		}

		if (dataStore == null) {
			throw new IllegalArgumentException("dataStore == null");
		}

		this.dataConfig = dataConfig;
		this.dataStore = dataStore;
	}

	@Override
	public final QueryTestDSCheck store(Consumer<QueryTestDSBuilder> dsBuilder) {
	
		debug("Storing instances on " + dataStore);

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
				
				if (instance.getPK() == null) {
					// Did not have PK initally probably since generated, set now 
					//throw new IllegalStateException("pk == null");
					
					final Object pk = getPrimaryKey.apply(dataStore, instance.getInstance());
					
					instance.setPk(pk);
				}
			}

			dataStore.commitTransaction(transaction);
			ok = true;
			debug("Commited instances OK on " + dataStore);
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


	private class Checker extends BaseChecker implements QueryTestDSCheck {
		private final List<TestInstance> instances;

		public Checker(List<TestInstance> instances) {
			this.instances = instances;
		}
		
		@Override
		void execute(Consumer<DataConfig> testBuilder) {
			testBuilder.accept(dataConfig);
		}
		
		@Override
		List<?> executeSql(String sql) {
			
			ENTITIES entities = null;
			try {
				entities = dataStore.openEntities();

				return dataStore.executeSql(entities, sql);
			}
			finally {
				if (entities != null) {
					dataStore.closeEntities(entities);
				}
				
			}
		}

		@Override
		public void check(Consumer<DataConfig> testBuilder) {

			debug("Running checks on " + dataStore);
			
			final ENTITIES em = dataStore.openEntities();

			try {
				testBuilder.accept(dataConfig);

				debug("Checks completed without exceptions " + dataStore);
			}
			finally {
				safelyDeleteInstances(em);
			}
		}
		
		private void safelyDeleteInstances(ENTITIES em) {

			debug("Deleting instances on " + dataStore);

			try {
				final TRANSACTION transaction = dataStore.beginTransaction(em);

				for (TestInstance instance : instances) {

					dataStore.remove(em, instance.getInstance(), instance.getPK());
				}
				
				dataStore.commitTransaction(transaction);

				debug("Deletion completed without exceptions " + dataStore);
			}
			finally {
				dataStore.closeEntities(em);
			}
		}
	}
}
