package com.neaterbits.query.sql.dsl.api;

final class ExecutableQueryForCompiledQuery implements ExecutableQuery<CompiledQuery> {

	@Override
	public QueryResultDimension getResultMode(CompiledQuery query) {
		return query.getResultMode();
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
	public ConditionType getConditionType(CompiledQuery query) {
		
		final CompiledConditions conditions = query.getConditions();
		
		final ConditionType ret;
		
		if (conditions == null) {
			ret = ConditionType.NONE;
		}
		else if (conditions instanceof CompiledConditionsAnd) {
			ret = ConditionType.AND;
		}
		else if (conditions instanceof CompiledConditionsOr) {
			ret = ConditionType.OR;
		}
		else if (conditions instanceof CompiledConditionsSingle) {
			ret = ConditionType.SINGLE;
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
