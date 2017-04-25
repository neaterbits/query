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

	private static final int [] NO_LEVELS = new int [] { 0 };
	
	private FunctionBase [] getMapFunctions() {

		final int numMappings = q.getMappingCount(query);

		final FunctionBase [] ret = new FunctionBase[numMappings];

		for (int mappingIdx = 0; mappingIdx < numMappings; ++ mappingIdx) {
		
			final ExecutableQueryExpressions expressions = q.getMappingExpressions(query, mappingIdx);
			final EExpressionType expressionType = expressions.getExpressionType(0, NO_LEVELS);

			final FunctionBase function = expressionType == EExpressionType.FUNCTION
					? expressions.getFunction(0, NO_LEVELS)
					: null;

			ret[mappingIdx] = function;
		}
		
		return ret;
	}
	
	
	final Object executeWithParams(QueryRunner queryRunner, ParamValueResolver paramCollector) {
		
		initParams(queryRunner, paramCollector);

		Object ret;

		final EQueryResultDimension resultDimension = q.getDimension(query);
		final EQueryResultGathering resultGathering = q.getGathering(query);
		
		// Cache functions for result conversion
		final FunctionBase [] mapFunctions = resultGathering == EQueryResultGathering.MAPPED
				? getMapFunctions()
				: null;
		
		
		switch (resultDimension) {
		case SINGLE:
			final Object ormRet = queryRunner.executeForSingleResult();
			ret = ormRet == null ? null : mapSingle(resultGathering, ormRet, mapFunctions);
			break;

		case MULTI:
			final List<?> ormList = queryRunner.executeForMultiResult();
			ret = mapMultiple(resultGathering, ormList, mapFunctions);
			break;

		default:
			throw new UnsupportedOperationException("Unknown result mode " + resultDimension);
		}
		
		return ret;
	}
	
	private Object mapSingle(EQueryResultGathering resultGathering, Object input, FunctionBase [] mapFunctions) {
		
		if (input == null) {
			throw new IllegalArgumentException("input == null");
		}

		final Object ret;
		
		
		switch (resultGathering) {

		case MAPPED:
			ret = q.createMappedInstance(query);

			final int numMappings = q.getMappingCount(query);
			
			switch (numMappings) {
				case 0:
					throw new IllegalStateException("empty mappings");

				case 1:
					// Just one field
					executeMappingSetterWithConversion(0, ret, input, mapFunctions);
					break;
					
				default:
					// More than one
					final Object [] vals = (Object[])input;
					for (int i = 0; i < vals.length; ++ i) {
						executeMappingSetterWithConversion(i, ret, vals[i], mapFunctions);
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
					ret = getDataSource().convertUnknownAggregateResult(aggregateResultType, input);
				}
				else {
					ret = input;
				}
			}
			
			break;

		default:
			throw new UnsupportedOperationException("Unknown result gathering " + resultGathering);
		}

		return ret;
	}
	
	
	private void executeMappingSetterWithConversion(int mappingIdx, Object instance, Object value, FunctionBase [] mapFunctions) {
		
		final Object toSet;

		if (mapFunctions[mappingIdx] != null) {
			if (value != null) {
				toSet = getDataSource().convertFunctionResultBeforeMapping(mapFunctions[mappingIdx], value);
			}
			else {
				toSet = null;
			}
		}
		else {
			toSet = value;
		}

		q.executeMappingSetter(query, mappingIdx, instance, toSet);
	}
	
	private List<Object> mapMultiple(EQueryResultGathering resultGathering, @SuppressWarnings("rawtypes") List input, FunctionBase [] mappedFunctions) {
		
		List<Object> ret;

		switch (resultGathering) { 
		
		case MAPPED:
		
			ret = new ArrayList<>(input.size());
			
			for (Object o : input) {
				ret.add(mapSingle(resultGathering, o, mappedFunctions));
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
