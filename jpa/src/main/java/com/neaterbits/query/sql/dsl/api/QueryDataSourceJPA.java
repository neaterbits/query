package com.neaterbits.query.sql.dsl.api;

import javax.persistence.EntityManager;

/**
 * Query data source implementation for JPA
 * @author nhl
 *
 */

final class QueryDataSourceJPA extends QueryDataSourceBase {

	private final EntityManager em;

	QueryDataSourceJPA(EntityManager entityManager) {
		
		if (entityManager == null) {
			throw new IllegalArgumentException("entityManager == null");
		}

		this.em = entityManager;
	}
	
	private JPAPreparedQuery prepareQuery(CompiledQuery query) {

		final StringBuilder sb = new StringBuilder();
		
		
		final javax.persistence.Query jpaQuery = em.createQuery(sb.toString());
		
		return new JPAPreparedQuery(jpaQuery);
	}
	
	// TODO: Assure unique
	private static String resultVarName(Class<?> resultType) {
		return "_result";
	}

	private static String sourceName(Class<?> entityType) {
		return entityType.getSimpleName().toLowerCase();
	}
	
	private static void prepare(StringBuilder sb, CompiledQuery query) {
		
		final Class<?> resultType = query.getResultType();
		
		sb.append("SELECT ");

		final CompiledMappings mappings = query.getMappings();

		if (mappings != null) {

			// We are returning a mapped type, get each value
			for (CompiledMapping mapping : mappings.getMappings()) {
				// Must return mappings
			}
		}
		else {
			sb.append(resultType.getSimpleName()).append(" ").append(resultVarName(resultType));
		}
	}
	

	@Override
	DSPreparedQuery prepareSingleQuery(CompiledQuery query) {
		if (query == null) {
			throw new IllegalArgumentException("query == null");
		}
		
		return prepareQuery(query);
	}

	@Override
	DSPreparedQuery prepareMultiQuery(CompiledQuery query) {
		if (query == null) {
			throw new IllegalArgumentException("query == null");
		}
		
		return prepareQuery(query);
	}
}

