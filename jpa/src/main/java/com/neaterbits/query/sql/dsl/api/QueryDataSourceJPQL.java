package com.neaterbits.query.sql.dsl.api;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public final class QueryDataSourceJPQL extends QueryDataSourceJPA {

	public QueryDataSourceJPQL(EntityManager entityManager) {
		super(entityManager);
	}
	
	@Override
	PreparedQueryBuilder createBuilder() {
		return new PreparedQueryBuilderJPQL<>(getEntityModelUtil(), em);
	}
	
	@Override
	final <QUERY> PreparedQuery_DB<QUERY, Query> makeHalfwayPreparedQuery(ExecutableQuery<QUERY> queryAccess, QUERY query,
			QueryParametersDistinct distinctParams, PreparedQueryBuilder base, PreparedQueryConditionsBuilder conditions) {
		
		return new PreparedQuery_JPA_Halfway_JPQL<QUERY>(this, queryAccess, query, distinctParams, base, (PreparedQueryConditionsBuilderORM)conditions);
	}
	

	@Override
	<QUERY> PreparedQuery_DB<QUERY, javax.persistence.Query> makeCompletePreparedQuery(ExecutableQuery<QUERY> q, QUERY query, QueryParametersDistinct distinctParams, PreparedQueryBuilder sb) {
		final String jpql = sb.toString();
		
		System.out.println("## jpql:\n" + jpql);
		
		final javax.persistence.Query jpaQuery = createJPAQuery(jpql);

		return new PreparedQuery_JPA_Complete_JPQL<QUERY>(
				this,
				q,
				query,
				distinctParams,
				jpaQuery);
	}
	

	@Override
	Query createJPAQuery(String queryString) {
		return em.createQuery(queryString);
	}

	@Override
	<QUERY> Object mapSingleEntity(ExecutableQuery<QUERY> q, QUERY query, Object input) {
		final Class<?> entityResultType = q.getResultJavaType(query);
		
		if (!entityResultType.isAssignableFrom(input.getClass())) {
			throw new IllegalStateException("not mapped and result not of mapped class: " + input.getClass().getName());
		}
		
		final Object ret = input;
		
		return ret;
	}

	@Override
	@SuppressWarnings("unchecked")
	<QUERY> List<Object> mapMultipleEntitities(ExecutableQuery<QUERY> q, QUERY query, List<?> input) {
		return (List<Object>)input;
	}

	@Override
	protected QueryDialect_SQL getDialect() {
		return new QueryDialect_JPQL();
	}
}
