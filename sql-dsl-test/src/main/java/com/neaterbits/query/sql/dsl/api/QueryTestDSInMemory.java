package com.neaterbits.query.sql.dsl.api;

import java.util.List;
import java.util.function.Consumer;

import com.neaterbits.query.sql.dsl.api.QueryDataSource;
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

	private class Checker implements QueryTestDSCheck {
		private final List<Object> instances;

		Checker(List<Object> instances) {
			this.instances = instances;
		}

		@Override
		public void check(Consumer<QueryDataSource> testBuilder) {

			final QueryDataSource dataSource = new QueryDataSourcePojoWithList(instances, queryMetaModel);

			testBuilder.accept(dataSource);
		}
	}

	@Override
	public QueryDataSource getDataSource() {
		throw new UnsupportedOperationException("TODO - POJOs");
	}
}
