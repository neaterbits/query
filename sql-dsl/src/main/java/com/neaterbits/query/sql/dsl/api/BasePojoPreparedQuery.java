package com.neaterbits.query.sql.dsl.api;

import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

abstract class BasePojoPreparedQuery<QUERY> extends PreparedQuery_DS<QueryDataSource_Pojo_Base> {

	private final QUERY query;
	private final ExecuteQueryPOJOs<QUERY> executor;
	private final ExecuteQueryPOJOsInput input;
	private final QueryMetaModel queryMetaModel;
	
	
	BasePojoPreparedQuery(QueryDataSource_Pojo_Base dataSource, PreparedQueryMetaData metaData, QUERY query, ExecuteQueryPOJOs<QUERY> executor, ExecuteQueryPOJOsInput input, QueryMetaModel queryMetaModel) {
		
		super(dataSource, metaData);
		
		if (query == null) {
			throw new IllegalArgumentException("query == null");
		}
		
		if (executor == null) {
			throw new IllegalArgumentException("executor == null");
		}
		
		if (input == null) {
			throw new IllegalArgumentException("input == null"); 
		}

		this.query = query;
		this.executor = executor;
		this.input = input;
		this.queryMetaModel = queryMetaModel;
	}

	@Override
	public Object execute(ParamValueResolver collectedParams) {
		return executor.execute(query, input, collectedParams, queryMetaModel);
	}
}
