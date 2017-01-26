package com.neaterbits.query.sql.dsl.api;


import javax.persistence.Query;

final class PreparedQuery_JPA_Complete_Native<QUERY> extends PreparedQuery_JPA_Complete<QUERY> {

	PreparedQuery_JPA_Complete_Native(QueryDataSourceJPA dataSource, ExecutableQuery<QUERY> queryAccess,
			QUERY query, QueryParametersDistinct distinctParams, Query jpaQuery) {
		super(dataSource, queryAccess, query, distinctParams, jpaQuery);
	}

	
	static void setParamUtil(Query jpaQuery, BaseParamImpl<?> param, int idx, Object value) {

		final int queryParamIdx = ConditionStringBuilder_Native.getParamIdx(idx);

		jpaQuery.setParameter(queryParamIdx, value);
	}
	
	@Override
	void setParam(Query jpaQuery, BaseParamImpl<?> param, int idx, Object value) {
		
		setParamUtil(jpaQuery, param, idx, value);
		
	}
}
