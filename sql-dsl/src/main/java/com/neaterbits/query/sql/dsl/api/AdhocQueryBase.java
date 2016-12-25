package com.neaterbits.query.sql.dsl.api;


import com.neaterbits.query.sql.dsl.api.entity.OneToManyJoinConditionResolver;

/**
 * Fast adhoc query, eg. similar to Streams API but aims at far fewer allocations
 * @author nhl
 *
 */

abstract class AdhocQueryBase<MODEL, QUERY extends AdhocQueryBase<MODEL, QUERY>>
			extends ExecuteQueryScratch
			implements 
				IAdhocNumericTableResult<MODEL, Object, Object>,

				ExecutableQuery<QUERY> {

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
	public final ECollectionType getResultCollectionType(QUERY query) {
		return collectionType;
	}

	@Override
	public final EAggregateFunction getAggregateResultFunction(QUERY query) {
		return aggregateFunction;
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
	public final int getMappingCount(QUERY query) {
		throw new UnsupportedOperationException("Mapping not supported for Adhoc queries");
	}


	@Override
	public final int getMappingSourceIdx(QUERY query, int mappingIdx) {
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
	public final int getJoinCount(QUERY query) {
		return 0;
	}

	@Override
	public final JoinType getJoinType(QUERY query, int joinIdx) {
		throw new UnsupportedOperationException("Joins not supported for Adhoc queries");
	}


	@Override
	public final int getJoinLeftSourceIdx(QUERY query, int joinIdx) {
		throw new UnsupportedOperationException("Joins not supported for Adhoc queries");
	}


	@Override
	public final int getJoinRightSourceIdx(QUERY query, int joinIdx) {
		throw new UnsupportedOperationException("Joins not supported for Adhoc queries");
	}


	@Override
	public final int getJoinConditionCount(QUERY query, int joinIdx) {
		throw new UnsupportedOperationException("Joins not supported for Adhoc queries");
	}


	@Override
	public final int getJoinConditionLeftSourceIdx(QUERY query, int joinIdx, int conditionIdx) {
		throw new UnsupportedOperationException("Joins not supported for Adhoc queries");
	}


	@Override
	public final int getJoinConditionRightSourceIdx(QUERY query, int joinIdx, int conditionIdx) {
		throw new UnsupportedOperationException("Joins not supported for Adhoc queries");
	}


	@Override
	public final boolean evaluateJoinCondition(QUERY query, int joinIdx, Object instance1, Object instance2, int conditionIdx,
			OneToManyJoinConditionResolver oneToManyResolver) {
		throw new UnsupportedOperationException("Joins not supported for Adhoc queries");
	}


	@Override
	public final Object createMappedInstance(QUERY query) {
		throw new UnsupportedOperationException("TODO: support mapping");
	}
}
