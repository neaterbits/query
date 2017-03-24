package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;


/**
 * Base class for prepared-queries generated for SQL DB
 * @author nhl
 *
 */

abstract class PreparedQuery_DB<QUERY> extends PreparedQuery_DS<QueryDataSource_DB> {

	private final ExecutableQuery<QUERY> q;
	private final QUERY query;
	
	final QueryParametersDistinct distinctParams;

	
	abstract void initParams(QueryRunner query, ParamValueResolver paramCollector);
	
	// return null if not found
	
	
	PreparedQuery_DB(QueryDataSource_DB dataSource, ExecutableQuery<QUERY> q, QUERY query, QueryParametersDistinct distinctParams) {
		
		super(dataSource, q.makeMetaData(query));

		if (query == null) {
			throw new IllegalArgumentException("queryMode == null");
		}

		this.distinctParams = distinctParams;
		this.q = q;
		this.query = query;
	}

	
	
	final Object executeWithParams(QueryRunner queryRunner, ParamValueResolver paramCollector) {
		
		initParams(queryRunner, paramCollector);

		Object ret;

		final EQueryResultDimension resultDimension = q.getDimension(query);
		
		switch (resultDimension) {
		case SINGLE:
			final Object ormRet = queryRunner.executeForSingleResult();
			ret = ormRet == null ? null : mapSingle(ormRet);
			break;

		case MULTI:
			final List<?> ormList = queryRunner.executeForMultiResult();
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
			
			final EAggregateFunction aggregateFunction = q.getAggregateResultFunction(query);
			
			// TODO There are some incompatibilities between JPA and native for avg,
			// JPA returns Double while native returns BigDecimal
			// TODO always return BigDecimal?
			
			
			if (aggregateFunction == EAggregateFunction.AVG) {
				ret = getDataSource().convertAvgAggregateResult(aggregateResultType, input);
			}
			else if (aggregateFunction == EAggregateFunction.COUNT) {
				if (!aggregateResultType.equals(Long.class)) {
					throw new IllegalStateException("Expected Long type for count(): " + aggregateResultType.getName());
				}
			
				/*
				if (input != null && !input.getClass().equals(Long.class)) {
					throw new IllegalStateException("Expected Long result for count(): " + input.getClass().getName());
				}
				*/
				
				ret = getDataSource().convertCountAggregateResult(aggregateResultType, input);
			}
			else {
				if (!aggregateResultType.equals(input.getClass())) {
					throw new IllegalStateException("Not of aggregated type " + aggregateResultType.getName() + ": " + input.getClass().getName());
				}

				ret = input;
			}
			
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
