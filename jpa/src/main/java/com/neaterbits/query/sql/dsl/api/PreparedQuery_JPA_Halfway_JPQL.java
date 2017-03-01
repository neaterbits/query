package com.neaterbits.query.sql.dsl.api;

import javax.persistence.Query;

final class PreparedQuery_JPA_Halfway_JPQL<QUERY> extends PreparedQuery_JPA_Halfway<QUERY> {

	PreparedQuery_JPA_Halfway_JPQL(QueryDataSourceJPA dataSource, ExecutableQuery<QUERY> queryAccess,
			QUERY query, QueryParametersDistinct distinctParams, PreparedQueryBuilder base,
			PreparedQueryConditionsBuilderORM conditions) {
		super(dataSource, queryAccess, query, distinctParams, base, conditions);
	}

	@Override
	void setParam(Query jpaQuery, BaseParamImpl<?> param, int idx, Object value) {
		PreparedQuery_JPA_Complete_JPQL.setParamUtil(jpaQuery, param, idx, value);
	}
	
}
