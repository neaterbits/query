package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.neaterbits.query.sql.dsl.api.PreparedQueryBuilder.FieldReference;

final class JPAQueryResultConverter {

	private final EntityManager em;
	
	JPAQueryResultConverter(EntityManager em) {
		this.em = em;
	}
	
	<QUERY> void convert(ExecutableQuery<QUERY> q, QUERY query, PreparedQueryBuilder sb) {
		
		final EQueryResultGathering resultGathering = q.getGathering(query);
		
		switch (resultGathering) {
		case ENTITY:
			final Class<?> resultType = q.getResultJavaType(query);
			
			sb.addEntityResult(resultType, resultVarName(resultType));
			break;
			
		case MAPPED:
			prepareMappings(sb, q, query);
			break;
			
		case AGGREGATE:
			
			final EAggregateFunction function = q.getAggregateResultFunction(query);
			final CompiledFieldReference resultField = q.getAggregateResultField(query);
			final FieldReference ref = QueryDataSourceJPA.prepareFieldReference(resultField, em);
			
			sb.addAggregateResult(function, ref);
			break;
			
		default:
			throw new UnsupportedOperationException("Unknown result gathering " + resultGathering);
		}
		
	}
	
	// TODO: Assure unique
	private static String resultVarName(Class<?> resultType) {
		return "_result";
	}
	
	private <QUERY> void prepareMappings(PreparedQueryBuilder sb, ExecutableQuery<QUERY> q, QUERY query) {
		
		final int numMappings = q.getMappingCount(query);
		
		final List<FieldReference> refs = new ArrayList<>(numMappings);
		
		for (int i = 0; i < numMappings; ++ i) {
			final CompiledFieldReference field = q.getMappingField(query, i);

			final FieldReference ref = QueryDataSourceJPA.prepareFieldReference(field, em);
			
			refs.add(ref);
		}
		
		sb.addMappings(refs);
	}
}
