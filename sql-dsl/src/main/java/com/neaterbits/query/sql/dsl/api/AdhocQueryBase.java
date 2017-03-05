package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

/**
 * Fast adhoc query, eg. similar to Streams API but aims at far fewer allocations
 * @author nhl
 *
 */

abstract class AdhocQueryBase<MODEL, QUERY extends AdhocQueryBase<MODEL, QUERY>>
			extends ExecuteQueryScratch
			implements 
				IAdhocNumericNamedResult<MODEL, Object, Object>,

				ExecutableQuery<QUERY>,
				ExecutableQueryConditions<QUERY> {

	private final EQueryResultDimension dimension;
	private final EQueryResultGathering gathering;
	
	// For aggregate result
	private EAggregateFunction aggregateFunction;
	private ENumericType aggregateNumericInputType;
	private ENumericType aggregateNumericOutputType;

	// For collection result
	private ECollectionType collectionType;
	
	AdhocQueryBase(EAggregateFunction aggregateFunction, ENumericType aggregateNumericInputType, ENumericType aggregateNumericOutputType) {
		
		if (aggregateFunction == null) {
			throw new IllegalArgumentException("aggregateFunction == null");
		}
		
		if (aggregateNumericInputType == null) {
			throw new IllegalArgumentException("aggregateNumericInputType == null");
		}

		if (aggregateNumericOutputType == null) {
			throw new IllegalArgumentException("aggregateNumericOutputType == null");
		}
			
		
		this.dimension = EQueryResultDimension.SINGLE;
		this.gathering = EQueryResultGathering.AGGREGATE;
		
		this.aggregateFunction = aggregateFunction;
		this.aggregateNumericInputType = aggregateNumericInputType;
		this.aggregateNumericOutputType = aggregateNumericOutputType;
	}
	
	
	AdhocQueryBase(ECollectionType collectionType) {
		
		if (collectionType == null) {
			throw new IllegalArgumentException("collectionType == null");	
		}
		
		this.dimension = EQueryResultDimension.MULTI;
		this.gathering = EQueryResultGathering.ENTITY;
		this.collectionType = collectionType;
	}
	
	
	/**************************************************************************
	** ExeutableQuery⋅
	**************************************************************************/

	
	@Override
	public final EQueryResultDimension getDimension(QUERY query) {
		return dimension;
	}

	@Override
	public final EQueryResultGathering getGathering(QUERY query) {
		return gathering;
	}

	@Override
	public final Class<?> getResultJavaType(QUERY query) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final ECollectionType getResultCollectionType(QUERY query) {
		return collectionType;
	}

	@Override
	public final EAggregateFunction getAggregateResultFunction(QUERY query) {
		return aggregateFunction;
	}

	@Override
	public final CompiledFieldReference getAggregateResultField(QUERY query) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final ENumericType getAggregateNumericInputType(QUERY query) {
		return aggregateNumericInputType;
	}

	@Override
	public final ENumericType getAggregateNumericOutputType(QUERY query) {
		return aggregateNumericOutputType;
	}

	@Override
	public final ExecutableQueryConditions<QUERY> getExecutableQueryConditions() {
		return this;
	}

	@Override
	public final int getMappingCount(QUERY query) {
		throw new UnsupportedOperationException("Mapping not supported for Adhoc queries");
	}


	@Override
	public final int getMappingSourceIdx(QUERY query, int mappingIdx) {
		throw new UnsupportedOperationException("Mapping not supported for Adhoc queries");
	}

	@Override
	public final CompiledFieldReference getMappingField(QUERY query, int mappingIdx) {
		throw new UnsupportedOperationException("Mapping not supported for Adhoc queries");
	}

	@Override
	public final Object executeMappingGetter(QUERY query, int mappingIdx, Object instance) {
		throw new UnsupportedOperationException("Mapping not supported for Adhoc queries");
	}


	@Override
	public final void executeMappingSetter(QUERY query, int mappingIdx, Object instance, Object value) {
		throw new UnsupportedOperationException("Mapping not supported for Adhoc queries");
	}


	@Override
	public int getMappingNumFunctions(QUERY query, int mappingIdx) {
		throw new UnsupportedOperationException("TODO: support mapping");
	}
	
	@Override
	public FunctionCalcBase getMappingFunction(QUERY query, int mappingIdx, int functionIdx) {
		throw new UnsupportedOperationException("TODO: support mapping");
	}

	@Override
	public final Object createMappedInstance(QUERY query) {
		throw new UnsupportedOperationException("TODO: support mapping");
	}
	
	@Override
	public final CompiledFieldReference getRootConditionLhs(QUERY query, int conditionIdx) {
		throw new UnsupportedOperationException("TODO - not supported for Adhoc queries");
	}

	@Override
	public final ConditionValue getRootConditionValue(QUERY query, int conditionIdx) {
		throw new UnsupportedOperationException("TODO - not supported for Adhoc queries");
	}
	
	@Override
	public final Class<?> [] getSelectSourceClasses(QUERY query) {
		throw new UnsupportedOperationException("TODO - not supported for Adhoc queries");
	}

	@Override
	public final CompiledFieldReference getConditionLhs(QUERY query, int level, int [] conditionIndices) {
		throw new UnsupportedOperationException("TODO - not supported for Adhoc queries");
	}

	@Override
	public final ConditionValue getConditionValue(QUERY query, int level, int [] conditionIndices) {
		throw new UnsupportedOperationException("TODO - not supported for Adhoc queries");
	}
	
	@Override
	public final int getGroupByFieldCount(QUERY query) {
		throw new UnsupportedOperationException("TODO - not supported for Adhoc queries");
	}

	@Override
	public final int getGroupByFieldIndex(QUERY query, int idx) {
		throw new UnsupportedOperationException("TODO - not supported for Adhoc queries");
	}

	@Override
	public final int getOrderByFieldCount(QUERY query) {
		throw new UnsupportedOperationException("TODO - not supported for Adhoc queries");
	}

	@Override
	public final int getOrderByFieldIndex(QUERY query, int idx) {
		throw new UnsupportedOperationException("TODO - not supported for Adhoc queries");
	}

	@Override
	public ESortOrder getOrderBySortOrder(QUERY query, int idx) {
		throw new UnsupportedOperationException("TODO - not supported for Adhoc queries");
	}

	@Override
	public Function<?, ?> getEntityOrderByFieldGetter(QUERY query, int idx) {
		throw new UnsupportedOperationException("TODO - not supported for Adhoc queries");
	}

	@Override
	public boolean hasConditions(QUERY query) {
		throw new UnsupportedOperationException("TODO - not supported for Adhoc queries");
	}

	@Override
	public ExecutableQueryConditions<QUERY> getExecutableQueryHaving() {
		throw new UnsupportedOperationException("TODO - not supported for Adhoc queries");
	}

	@Override
	public boolean hasHaving(QUERY query) {
		throw new UnsupportedOperationException("TODO - not supported for Adhoc queries");
	}
}
