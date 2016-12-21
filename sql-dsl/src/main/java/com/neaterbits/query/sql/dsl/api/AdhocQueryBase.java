package com.neaterbits.query.sql.dsl.api;


import com.neaterbits.query.sql.dsl.api.entity.OneToManyJoinConditionResolver;

/**
 * Fast adhoc query, eg. similar to Streams API but aims at far fewer allocations
 * @author nhl
 *
 */

abstract class AdhocQueryBase<QUERY extends AdhocQueryBase<QUERY>>
			extends ExecuteQueryScratch
			implements IAdhocNumericTableResult<Object>, ExecutableQuery<QUERY> {


	private final QueryResultDimension dimension;
	private final QueryResultGathering gathering;
	
	// For aggregate result
	private AggregateFunction aggregateFunction;
	private NumericType aggregateNumericType; 
	
	private ConditionsType conditionsType;
	
	
	AdhocQueryBase(AggregateFunction aggregateFunction, NumericType aggregateNumericType) {
		
		if (aggregateFunction == null) {
			throw new IllegalArgumentException("aggregateFunction == null");
		}
		
		if (aggregateNumericType == null) {
			throw new IllegalArgumentException("aggregateNumericType == null");
		}
		
		this.dimension = QueryResultDimension.SINGLE;
		this.gathering = QueryResultGathering.AGGREGATE;
		
		this.aggregateFunction = aggregateFunction;
		this.aggregateNumericType = aggregateNumericType;
	}


	@Override
	public final QueryResultDimension getDimension(QUERY query) {
		return dimension;
	}


	@Override
	public final QueryResultGathering getGathering(QUERY query) {
		return gathering;
	}

	@Override
	public final AggregateFunction getAggregateResultFunction(QUERY query) {
		return aggregateFunction;
	}


	@Override
	public final NumericType getAggregateNumericType(QUERY query) {
		return aggregateNumericType;
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
	public final int getSourceCount(QUERY query) {
		throw new UnsupportedOperationException("TODO");
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
	public final ConditionsType getConditionsType(QUERY query) {
		return conditionsType;
	}

	@Override
	public final Object createMappedInstance(QUERY query) {
		throw new UnsupportedOperationException("TODO: support mapping");
	}

	@Override
	public final int getConditionCount(QUERY query) {
		return 0;
	}


	@Override
	public final int getConditionSourceIdx(QUERY query, int conditionIdx) {
		throw new UnsupportedOperationException("TODO: support conditions");
	}


	@Override
	public final boolean evaluateCondition(QUERY query, Object instance, int conditionIdx, ConditionValuesScratch scratch) {
		throw new UnsupportedOperationException("TODO: support conditions");
	}
}