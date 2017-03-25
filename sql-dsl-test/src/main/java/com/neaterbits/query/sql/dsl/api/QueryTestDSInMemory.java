package com.neaterbits.query.sql.dsl.api;

import java.util.List;
import java.util.function.Consumer;

import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

public final class QueryTestDSInMemory extends QueryTestDS {

	private final QueryMetaModel queryMetaModel;
	
	public QueryTestDSInMemory(QueryMetaModel queryMetaModel) {
		this.queryMetaModel = queryMetaModel;
	}

	@Override
	public QueryTestDSCheck store(Consumer<QueryTestDSBuilder> dsBuilder) {

		final QueryTestDSBuilderInMemoryImpl b = new QueryTestDSBuilderInMemoryImpl();

		dsBuilder.accept(b);

		return new Checker(b.getInstances());
	}

	private class Checker extends BaseChecker implements QueryTestDSCheck {
		private final List<Object> instances;

		Checker(List<Object> instances) {
			this.instances = instances;
		}

		private PojoDataConfig getDataConfig() {
			final PojoDataConfig pojoDataConfig = new ListPojoDataConfig(instances, queryMetaModel);

			return pojoDataConfig;
		}
		
		
		@Override
		List<?> executeSql(String sql) {
			throw new UnsupportedOperationException("sql not suppported");
		}
		
		@Override
		public QueryTestDSCheck remove(Object... instances) {
			// Not removing instances since in-memory
			return this;
		}

		@Override
		void execute(Consumer<DataConfig> testBuilder) {
			testBuilder.accept(getDataConfig());
		}
		

		@Override
		public void check(Consumer<DataConfig> testBuilder) {
			testBuilder.accept(getDataConfig());
		}
	}
}
