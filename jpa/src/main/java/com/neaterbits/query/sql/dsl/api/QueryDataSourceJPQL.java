package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

public final class QueryDataSourceJPQL extends QueryDataSourceJPA {

	public QueryDataSourceJPQL(EntityManager entityManager) {
		super(entityManager);
	}
	
	@Override
	PreparedQueryBuilder createBuilder() {
		return new PreparedQueryBuilderJPQL();
	}
	

	@Override
	<QUERY> DSPreparedQueryDB<QUERY, javax.persistence.Query> makeCompletePreparedQuery(ExecutableQuery<QUERY> q, QUERY query, ParamNameAssigner paramNameAssigner, PreparedQueryBuilder sb) {
		final String jpql = sb.toString();
		
		System.out.println("## jpql:\n" + jpql);
		
		final javax.persistence.Query jpaQuery = em.createQuery(jpql);

		return new JPACompletePreparedQuery<QUERY>(this, q, query, paramNameAssigner, jpaQuery);
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
}
