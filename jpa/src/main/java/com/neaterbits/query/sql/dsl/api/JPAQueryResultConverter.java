package com.neaterbits.query.sql.dsl.api;

import javax.persistence.EntityManager;

final class JPAQueryResultConverter {

	private final EntityManager em;
	
	JPAQueryResultConverter(EntityManager em) {
		this.em = em;
	}
	
	<QUERY> void convert(ExecutableQuery<QUERY> q, QUERY query, StringBuilder sb) {
		
		final EQueryResultGathering resultGathering = q.getGathering(query);
		
		switch (resultGathering) {
		case ENTITY:
			final Class<?> resultType = q.getResultJavaType(query);
			
			sb.append(resultType.getSimpleName()).append(" ").append(resultVarName(resultType));
			break;
			
		case MAPPED:
			prepareMappings(sb, q, query);
			break;
			
		case AGGREGATE:
			
			final EAggregateFunction function = q.getAggregateResultFunction(query);
			
			switch (function) {
			case SUM:
				sb.append("sum (");
				
				final CompiledFieldReference resultField = q.getAggregateResultField(query);
				
				QueryDataSourceJPA.prepareFieldReference(sb::append, resultField, em);
				
				sb.append(")");
				break;
				
			default:
				throw new UnsupportedOperationException("Unknown aggregate: " + function);
			}
			break;
			
		default:
			throw new UnsupportedOperationException("Unknown result gathering " + resultGathering);
		}
		
	}
	
	// TODO: Assure unique
	private static String resultVarName(Class<?> resultType) {
		return "_result";
	}
	
	private <QUERY> void prepareMappings(StringBuilder sb, ExecutableQuery<QUERY> q, QUERY query) {
		
		final int numMappings = q.getMappingCount(query);
		
		for (int i = 0; i < numMappings; ++ i) {
			if (i > 0) {
				sb.append(", ");
			}
			
			final CompiledFieldReference field = q.getMappingField(query, i);

			QueryDataSourceJPA.prepareFieldReference(sb::append, field, em);
		}
		

		/*
		JPAUtil.commaSeparated(sb, mappings.getMappings(), (CompiledMapping mapping) -> {

			final CompiledFieldReference field = mapping.getField();

			QueryDataSourceJPA.prepareFieldReference(sb::append, field, em);
		});
		*/
	}
}
