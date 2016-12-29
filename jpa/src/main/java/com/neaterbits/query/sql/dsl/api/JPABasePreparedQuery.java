package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

abstract class JPABasePreparedQuery<QUERY> implements DSPreparedQuery {

	private final ExecutableQuery<QUERY> q;
	private final QUERY query;
	private final ParamNameAssigner paramNameAssigner;

	JPABasePreparedQuery(ExecutableQuery<QUERY> queryAccess, QUERY query, ParamNameAssigner paramNameAssigner) {
		
		if (query == null) {
			throw new IllegalArgumentException("resultMode == null");
		}

		this.query = query;
		this.q = queryAccess;
		this.paramNameAssigner = paramNameAssigner;
	}

	String getNameForParam(Param<?> param) {
		return paramNameAssigner.getExistingName(param);
	}
	
	final Object executeWithParams(Query jpaQuery, ParamValueResolver paramCollector) {
		
		if (paramNameAssigner != null) {
			paramNameAssigner.forEach((Param<?> param, String name) -> {
				
				jpaQuery.setParameter(name, paramCollector.resolveParam(param));
				
			});
		}

		Object ret;

		final EQueryResultDimension resultDimension = q.getDimension(query);
		
		switch (resultDimension) {
		case SINGLE:
			try {
				ret = mapSingle(jpaQuery.getSingleResult());
			}
			catch (NoResultException ex) {
				ret = null;
			}
			break;
			
			
		case MULTI:
			ret = mapMultiple(jpaQuery.getResultList());
			break;
			
		default:
			throw new UnsupportedOperationException("Unknown result mode " + resultDimension);
		}
		
		return ret;
	}
	
	private Object mapSingle(Object input) {

		final Object ret;
		
		final EQueryResultGathering resultGathering = q.getGathering(query);
		
		switch (resultGathering) {

		case MAPPED:
			ret = q.createMappedInstance(query);

			final int numMappings = q.getMappingCount(query);
			
			switch (numMappings) {
				case 0:
					throw new IllegalStateException("empty mappings");

				case 1:
					// Just one field
					q.executeMappingSetter(query, 0, ret, input);
					break;
					
				default:
					// More than one
					final Object [] vals = (Object[])input;
					for (int i = 0; i < vals.length; ++ i) {
						
						q.executeMappingSetter(query, i, ret, vals[i]);
					}
					break;
			}
			break;

		case ENTITY:
			
			final Class<?> entityResultType = q.getResultJavaType(query);
			
			if (!entityResultType.isAssignableFrom(input.getClass())) {
				throw new IllegalStateException("not mapped and result not of mapped class: " + input.getClass().getName());
			}
			
			ret = input;
			break;

		case AGGREGATE:
			final Class<?> aggregateResultType = q.getResultJavaType(query);
			
			if (!aggregateResultType.equals(input.getClass())) {
				throw new IllegalStateException("Not of aggregated type " + aggregateResultType.getName() + ": " + input.getClass().getName());
			}

			ret = input;
			break;

		default:
			throw new UnsupportedOperationException("Unknown result gathering " + resultGathering);
		}

		return ret;
	}
	
	private List<Object> mapMultiple(@SuppressWarnings("rawtypes") List input) {
		
		final List<Object> ret = new ArrayList<>(input.size());
		
		for (Object o : input) {
			ret.add(mapSingle(o));
		}

		return ret;
	}
	
}
