package com.neaterbits.query.sql.dsl.api;

import java.util.Collection;

import com.neaterbits.query.sql.dsl.api.QueryDataSourcePojoBase;
import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

public class QueryDataSourcePojoWithList extends QueryDataSourcePojoBase {

	private final Collection<?> instances;

	public QueryDataSourcePojoWithList(Collection<?> instances, QueryMetaModel queryMetaModel) {
		super(queryMetaModel);

		if (instances == null) {
			throw new IllegalArgumentException("instances == null");
		}

		this.instances = instances;
	}

	@Override
	<QUERY> DSPreparedQuery prepareSingleQuery(ExecutableQuery<QUERY> q, QUERY query) {
		return prepare(q, query);
	}

	@Override
	<QUERY> DSPreparedQuery prepareMultiQuery(ExecutableQuery<QUERY> q, QUERY query) {
		return prepare(q, query);
	}
	
	private <QUERY> PojoPreparedQuery<QUERY> prepare(ExecutableQuery<QUERY> q, QUERY query) {

		final ExecuteQueryPOJOs<QUERY> executeQueryPOJOs = new ExecuteQueryPOJOs<>(q);
		
		final ExecuteQueryPOJOsFromListInput input = new ExecuteQueryPOJOsFromListInput(instances, q.getSelectSourceClasses(query));

		return new PojoPreparedQuery<QUERY>(query, executeQueryPOJOs, input, getQueryMetaModel());
	}
}
