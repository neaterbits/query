package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import com.neaterbits.query.util.java8.Coll8;

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
		
		private List<TestInstance> toRemoveInstances;
		private List<Object> toRemove;

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
		public QueryTestDSCheck remove(Object... instances) {

			if (toRemove == null) {
				toRemove = new ArrayList<>();
			}
			
			for (Object o : instances) {
				
				if (o == null) {
					throw new IllegalArgumentException("o == null");
				}
				
				if (Coll8.has(this.instances, instance -> instance.getInstance() == o)) {
					throw new IllegalStateException("Already among instancens: " + o);
				}
				
				/*
				
				final TestInstance testInstance = Coll8.find(this.instances, instance -> instance.getInstance() == o);
				
				if (testInstance == null) {
					throw new IllegalStateException("No test instance matching " + o + " in " + this.instances);
				}
				*/
				
				//toRemoveInstances.add(testInstance);
				toRemove.add(o);
			}
			
			return this;
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

				if (toRemove != null) {
					for (Object o : toRemove) {
						debug("pre-removing instance " + o);

						dataStore.remove(em, o, dataStore.getPrimaryKey(o));
					}
				}
				
				if (toRemoveInstances != null) {
					for (TestInstance instance : toRemoveInstances) {
						debug("pre-removing testinstance " + instance.getInstance());

						dataStore.remove(em, instance.getInstance(), instance.getPK());
					}
				}
					

				for (TestInstance instance : instances) {
					
					if (toRemoveInstances != null && Coll8.has(toRemoveInstances, i -> i.getInstance() == instance.getInstance())) {
						// already removed
						debug("skipping pre-removed " + instance.getInstance());
						continue;
					}

					debug("removing " + instance.getInstance());
					
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
