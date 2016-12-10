package com.neaterbits.query.sql.dsl.api;

final class ExecutableQueryForCompiledQuery implements ExecutableQuery<CompiledQuery> {

	@Override
	public QueryResultDimension getDimension(CompiledQuery query) {
		return query.getResultMode();
	}
	
	@Override
	public QueryResultGathering getGathering(CompiledQuery query) {
		
		return query.getGathering();
	}

	@Override
	public AggregateFunction getAggregateResultFunction(CompiledQuery query) {
		return ((QueryResultAggregate)query.getResult().getOriginal()).getAggregateFunction();
	}
	
	
	@Override
	public NumericType getAggregateNumericType(CompiledQuery query) {
		return ((QueryResultAggregate)query.getResult().getOriginal()).getNumericType();
	}

	@Override
	public Object getAggregateResultValue(CompiledQuery query, Object instance) {
		return ((CompiledQueryResultAggregate)query.getResult()).getField().getValue(instance);
	}

	@Override
	public int getMappingCount(CompiledQuery query) {

		/*
		if (query.getGathering() != QueryResultGathering.MAPPED)) {
			throw new IllegalStateException("Expected mapped");
		}
		*/
		
		final CompiledQueryResultMapped mapped = (CompiledQueryResultMapped)query.getResult();
		
		return mapped.getMappings().getMappings().size();
	}

	@Override
	public Object createMappedInstance(CompiledQuery query) {
		
		final Class<?> type = query.getResult().getOriginal().getType();
		final Object ret;

		try {
			ret = type.newInstance();
		} catch (InstantiationException | IllegalAccessException ex) {
			throw new IllegalStateException("Failed to instantia type " + type);
		}
		
		return ret;
	}

	@Override
	public Object executeMappingGetter(CompiledQuery query, int mappingIdx, Object instance) {
		final CompiledQueryResultMapped mapped = (CompiledQueryResultMapped)query.getResult();
		
		return mapped.getMappings().getMappings().get(mappingIdx).executeGetter(instance);
	}

	@Override
	public void executeMappingSetter(CompiledQuery query, int mappingIdx, Object instance, Object value) {
		final CompiledQueryResultMapped mapped = (CompiledQueryResultMapped)query.getResult();

		mapped.getMappings().getMappings().get(mappingIdx).executeSetter(instance, value);
	}

	@Override
	public int getSourceCount(CompiledQuery query) {
		return query.getSelectSources().getSources().size();
	}

	@Override
	public int getJoinCount(CompiledQuery query) {
		final CompiledJoins joins = query.getJoins();
		
		return joins != null ? joins.getJoins().size() : 0;
	}

	@Override
	public JoinType getJoinType(CompiledQuery query, int joinIdx) {
		return query.getJoins().getJoins().get(joinIdx).getOriginal().getJoinType();
	}

	@Override
	public int getJoinLeftSourceIdx(CompiledQuery query, int joinIdx) {
		return query.getJoins().getJoins().get(joinIdx).getLeft().getIdx();
	}

	@Override
	public int getJoinRightSourceIdx(CompiledQuery query, int joinIdx) {
		return query.getJoins().getJoins().get(joinIdx).getRight().getIdx();
	}


	@Override
	public int getConditionCount(CompiledQuery query) {
		final CompiledConditions conditions = query.getConditions();
		
		return conditions != null ? conditions.getConditions().size() : 0;
	}

	@Override
	public ConditionsType getConditionType(CompiledQuery query) {
		
		final CompiledConditions conditions = query.getConditions();
		
		final ConditionsType ret;
		
		if (conditions == null) {
			ret = ConditionsType.NONE;
		}
		else if (conditions instanceof CompiledConditionsAnd) {
			ret = ConditionsType.AND;
		}
		else if (conditions instanceof CompiledConditionsOr) {
			ret = ConditionsType.OR;
		}
		else if (conditions instanceof CompiledConditionsSingle) {
			ret = ConditionsType.SINGLE;
		}
		else {
			throw new UnsupportedOperationException("Unknown condition type "
							+ conditions.getClass().getSimpleName());
		}

		return ret;
	}

	@Override
	public int getConditionSourceIdx(CompiledQuery query, int conditionIdx) {
		return query.getConditions().getConditions().get(conditionIdx).getLhsSource().getIdx();
	}

	@Override
	public boolean evaluateCondition(CompiledQuery query, Object instance, int conditionIdx) {
		return query.getConditions().getConditions().get(conditionIdx).evaluate(instance);
	}

}
