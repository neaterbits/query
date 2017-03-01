package com.neaterbits.query.sql.dsl.api;

import java.util.List;

import javax.persistence.EntityManager;

public final class QueryDataSourceJPQL extends QueryDataSourceJPA {

	public QueryDataSourceJPQL(EntityManager entityManager) {
		super(entityManager);
	}
	
	@Override
	PreparedQueryBuilder createBuilder() {
		return new PreparedQueryBuilderORM<>(getEntityModelUtil(), getDialect());
	}
	
	@Override
	final <QUERY> PreparedQuery_DB<QUERY> makeHalfwayPreparedQuery(ExecutableQuery<QUERY> queryAccess, QUERY query,
			QueryParametersDistinct distinctParams, PreparedQueryBuilder base, PreparedQueryConditionsBuilder conditions) {
		
		return new PreparedQuery_DB_Halfway<QUERY>(this, queryAccess, query, distinctParams, base, (PreparedQueryConditionsBuilderORM)conditions);
	}
	

	@Override
	<QUERY> PreparedQuery_DB<QUERY> makeCompletePreparedQuery(ExecutableQuery<QUERY> q, QUERY query, QueryParametersDistinct distinctParams, PreparedQueryBuilder sb) {
		final String jpql = sb.toString();
		
		System.out.println("## jpql:\n" + jpql);
		
		final QueryRunner_JPA queryRunner = createQueryRunner(jpql);

		return new PreparedQuery_DB_Complete<QUERY>(
				this,
				queryRunner,
				q,
				query,
				distinctParams);
	}
	

	@Override
	QueryRunner_JPA createQueryRunner(String queryString) {
		return new QueryRunner_JPA_JPQL(em.createQuery(queryString));
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
