package com.neaterbits.query.sql.dsl.api.testhelper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import com.neaterbits.query.sql.dsl.api.QueryDataSource;
import com.neaterbits.query.sql.dsl.api.QueryDataSourcePojo;

public final class QueryTestDSInMemory extends QueryTestDS {

	
	@Override
	public QueryTestDSCheck store(Consumer<QueryTestDSBuilder> dsBuilder) {

		final QueryTestDSBuilderImpl<Void> b = new QueryTestDSBuilderImpl<Void>(null, null);

		dsBuilder.accept(b);
		
		return new Checker(b.getInstances());
	}
	
	private static <T, O> List<O> remap(List<T> list, Function<T, O> map) {
		final List<O> ret = new ArrayList<>(list.size());
		
		for (T t : list) {
			ret.add(map.apply(t));
		}

		return ret;
	}

	private class Checker implements QueryTestDSCheck {
		private final List<TestInstance> instances;

		Checker(List<TestInstance> instances) {
			this.instances = instances;
		}

		@Override
		public void check(Consumer<QueryDataSource> testBuilder) {

			final QueryDataSource dataSource = new QueryDataSourcePojo(remap(instances, i -> i.getInstance()));

			try {
				testBuilder.accept(dataSource);
			}
			catch (RuntimeException ex) {
				System.err.println("Got runtime exception in check: " + ex);
				ex.printStackTrace(System.err);
			}
			finally {
			}
		}
	}
}
