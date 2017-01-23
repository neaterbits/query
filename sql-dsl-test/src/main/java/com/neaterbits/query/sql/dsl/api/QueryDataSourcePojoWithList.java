package com.neaterbits.query.sql.dsl.api;

import java.util.Collection;

import com.neaterbits.query.sql.dsl.api.QueryDataSource_Pojo_Base;
import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

public class QueryDataSourcePojoWithList extends QueryDataSource_Pojo_Base {

	private final Collection<?> instances;

	public QueryDataSourcePojoWithList(Collection<?> instances, QueryMetaModel queryMetaModel) {
		super(queryMetaModel);

		if (instances == null) {
			throw new IllegalArgumentException("instances == null");
		}

		this.instances = instances;
	}

	@Override
	<QUERY> PreparedQuery_DS<QueryDataSource_Pojo_Base> prepareSingleQuery(ExecutableQuery<QUERY> q, QUERY query) {
		return prepare(q, query);
	}

	@Override
	<QUERY> PreparedQuery_DS<QueryDataSource_Pojo_Base> prepareMultiQuery(ExecutableQuery<QUERY> q, QUERY query) {
		return prepare(q, query);
	}
	
	private <QUERY> PojoPreparedQuery<QUERY> prepare(ExecutableQuery<QUERY> q, QUERY query) {

		final ExecuteQueryPOJOs<QUERY> executeQueryPOJOs = new ExecuteQueryPOJOs<>(q);
		
		final ExecuteQueryPOJOsFromListInput input = new ExecuteQueryPOJOsFromListInput(instances, q.getSelectSourceClasses(query));

		return new PojoPreparedQuery<QUERY>(this, query, executeQueryPOJOs, input, getQueryMetaModel());
	}
}
