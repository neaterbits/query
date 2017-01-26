package com.neaterbits.query.sql.dsl.api;

import java.util.List;

import javax.persistence.Query;

final class PreparedQuery_JPA_Complete_JPQL<QUERY> extends PreparedQuery_JPA_Complete<QUERY> {

	PreparedQuery_JPA_Complete_JPQL(QueryDataSourceJPA dataSource, ExecutableQuery<QUERY> queryAccess,
			QUERY query, QueryParametersDistinct distinctParams, Query jpaQuery) {
		super(dataSource, queryAccess, query, distinctParams, jpaQuery);
	}

	static void setParamUtil(Query jpaQuery, BaseParamImpl<?> param, int idx, Object value) {
		final String name = ConditionStringBuilder_JPQL.makeParamName(idx);

		if (param.isList()) {
			
			final List<?> listVal = (List<?>)value;
			
			jpaQuery.setParameter(name, listVal);
		}
		else {
			jpaQuery.setParameter(name, value);
		}
	}
	
	@Override
	void setParam(Query jpaQuery, BaseParamImpl<?> impl, int idx, Object value) {
		setParamUtil(jpaQuery, impl, idx, value);
	}
}
