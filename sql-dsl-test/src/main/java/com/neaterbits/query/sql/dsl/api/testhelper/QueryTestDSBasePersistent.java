package com.neaterbits.query.sql.dsl.api.testhelper;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import com.neaterbits.query.sql.dsl.api.QueryDataSource;

public abstract class QueryTestDSBasePersistent<CTX, ENTITIES, TRANSACTION> extends QueryTestDS {

	private final CTX ctx;
	private final BiFunction<CTX, Object, Object> getPK;
	
	protected abstract ENTITIES openEntities(CTX ctx);
	
	protected abstract TRANSACTION beginTransaction(ENTITIES entities);

	protected abstract void persist(ENTITIES entities, Object instance);

	protected abstract void remove(ENTITIES entities, Object instance, Object pk);

	protected abstract void commitTransaction(TRANSACTION transaction);

	protected abstract boolean isTransactionActive(TRANSACTION transaction);

	protected abstract void rollbackTransaction(TRANSACTION transaction);
	
	protected abstract void closeEntities(ENTITIES entities);
	
	protected abstract QueryDataSource createDataSource(ENTITIES entities);

	protected QueryTestDSBasePersistent(CTX ctx, BiFunction<CTX, Object, Object> getPK) {

		this.ctx = ctx;
		this.getPK = getPK; 
	}

	@Override
	public final QueryTestDSCheck store(Consumer<QueryTestDSBuilder> dsBuilder) {

		final QueryTestDSBuilderImpl<CTX> b = new QueryTestDSBuilderImpl<CTX>(ctx, getPK);

		final ENTITIES em = openEntities(ctx);

		final List<TestInstance> instances = b.getInstances();
		
		boolean ok = false;

		final TRANSACTION transaction = beginTransaction(em);
		
		try {
			
			dsBuilder.accept(b);
			
			//dumpMetaModel(em.getMetamodel());
		
			for (TestInstance instance : instances) {
				persist(em, instance.getInstance());
			}

			commitTransaction(transaction);
			ok = true;
		}
		finally {
			
			try {
				if (!ok && isTransactionActive(transaction)) {
					rollbackTransaction(transaction);
				}
			}
			finally {
				closeEntities(em);
			}
		}

		return new Checker(ctx, instances);
	}


	private class Checker implements QueryTestDSCheck {
		private final CTX emf;
		private final List<TestInstance> instances;

		public Checker(CTX emf, List<TestInstance> instances) {
			this.emf = emf;
			this.instances = instances;
		}

		@Override
		public void check(Consumer<QueryDataSource> testBuilder) {

			final ENTITIES em = openEntities(emf);
			final QueryDataSource dataSource = createDataSource(em);

			try {
				testBuilder.accept(dataSource);
			}
			catch (RuntimeException ex) {
				System.err.println("Got runtime exception in check: " + ex);
				ex.printStackTrace(System.err);
			}
			finally {
				safelyDeleteInstances(em);
			}
		}
		
		private void safelyDeleteInstances(ENTITIES em) {
			try {
				final TRANSACTION transaction = beginTransaction(em);

				for (TestInstance instance : instances) {

					remove(em, instance.getInstance(), instance.getPK());
				}
				
				commitTransaction(transaction);
			}
			finally {
				closeEntities(em);
			}
		}
	}
	
}
