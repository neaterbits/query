package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;


/**
 * Base class for prepared-queries generated for SQL DB
 * @author nhl
 *
 */

abstract class PreparedQuery_DB<QUERY, ORM_QUERY> extends PreparedQuery_DS<QueryDataSource_DB> {

	private final ExecutableQuery<QUERY> q;
	private final QUERY query;
	
	abstract void initParams(ORM_QUERY ormQuery, ParamValueResolver paramCollector);
	
	// return null if not found
	abstract Object executeForSingleResult(ORM_QUERY ormQuery);
	
	abstract List<?> executeForMultiResult(ORM_QUERY ormQuery);
	
	
	PreparedQuery_DB(QueryDataSource_DB dataSource, ExecutableQuery<QUERY> q, QUERY query) {
		
		super(dataSource, q.makeMetaData(query));

		if (query == null) {
			throw new IllegalArgumentException("queryMode == null");
		}

		this.q = q;
		this.query = query;
	}

	
	final Object executeWithParams(ORM_QUERY ormQuery, ParamValueResolver paramCollector) {
		
		initParams(ormQuery, paramCollector);

		Object ret;

		final EQueryResultDimension resultDimension = q.getDimension(query);
		
		switch (resultDimension) {
		case SINGLE:
			final Object ormRet = executeForSingleResult(ormQuery);
			ret = ormRet == null ? null : mapSingle(ormRet);
			break;

		case MULTI:
			final List<?> ormList = executeForMultiResult(ormQuery);
			ret = mapMultiple(ormList);
			break;

		default:
			throw new UnsupportedOperationException("Unknown result mode " + resultDimension);
		}
		
		return ret;
	}
	
	private Object mapSingle(Object input) {
		
		if (input == null) {
			throw new IllegalArgumentException("input == null");
		}

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
			ret = getDataSource().mapSingleEntity(q, query, input);
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
		
		List<Object> ret;

		final EQueryResultGathering resultGathering = q.getGathering(query);
		
		switch (resultGathering) { 
		
		case MAPPED:
		
			ret = new ArrayList<>(input.size());
			
			for (Object o : input) {
				ret.add(mapSingle(o));
			}
			break;
			
		case ENTITY:
			ret = getDataSource().mapMultipleEntitities(q, query, input);
			break;
			
		default:
			throw new UnsupportedOperationException("Unknown gathering " + resultGathering);
		}

		return ret;
	}
	
}
