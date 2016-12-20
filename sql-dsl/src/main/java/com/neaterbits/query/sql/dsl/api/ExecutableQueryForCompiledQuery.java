package com.neaterbits.query.sql.dsl.api;


import com.neaterbits.query.sql.dsl.api.entity.ScalarType;

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
		
		final CompiledMapping mapping = mapped.getMappings().getMappings().get(mappingIdx);
		
		return mapping.executeGetter(instance);
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
		return query.getJoins().getJoins().get(joinIdx).getJoinType();
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
	public int getJoinConditionCount(CompiledQuery query, int joinIdx) {
		return query.getJoins().getJoins().size();
	}

	/*
	@Override
	public ConditionsType getJoinConditionType(CompiledQuery query) {
		// TODO Auto-generated method stub
		return null;
	}
	*/

	@Override
	public int getJoinConditionLeftSourceIdx(CompiledQuery query, int joinIdx, int conditionIdx) {
		return query.getJoins().getJoins().get(joinIdx).getConditions().get(conditionIdx).getLeft().getIdx();
	}

	@Override
	public int getJoinConditionRightSourceIdx(CompiledQuery query, int joinIdx, int conditionIdx) {
		return query.getJoins().getJoins().get(joinIdx).getConditions().get(conditionIdx).getRight().getIdx();
	}

	@Override
	public boolean evaluateJoinCondition(CompiledQuery query, int joinIdx, Object instance1, Object instance2, int conditionIdx) {
		return query.getJoins().getJoins().get(joinIdx).getConditions().get(conditionIdx).evaluate(instance1, instance2);
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
	public boolean evaluateCondition(CompiledQuery query, Object instance, int conditionIdx, ConditionValuesScratch scratch) {
		
		final CompiledCondition condition = query.getConditions().getConditions().get(conditionIdx);
		
		// Must evaluate condition with params.
		// First figure lhs
		final Object lhs  = condition.getLhs().getValue(instance);

		// Then figure rhs
		final Object rhs = condition.getValue().visit(valueVisitor, scratch.getCollectedParams());
		
		// At last, evaluate
		scratch.init(lhs, rhs);
		
		final ScalarType scalarType = condition.getScalarType();
		
		final boolean ret;
		
		if (scalarType == ScalarType.STRING) {
			ret = condition.getOriginal().visit(stringEvaluator, scratch);
		}
		else {
			ret = condition.getOriginal().visit(comparableEvaluator, scratch);
		}

		return ret;
	}

	private static final ConditionEvaluatorComparable comparableEvaluator = new ConditionEvaluatorComparable();
	private static final ConditionEvaluatorComparableString stringEvaluator = new ConditionEvaluatorComparableString();
	
	
	private static final ConditionValueVisitor<ParamValueResolver, Object> valueVisitor = new ConditionValueVisitor<ParamValueResolver, Object>() {
		
		@Override
		public Object onParam(ConditionValueParamImpl value, ParamValueResolver param) {
			return param.resolveParam(value.getParam());
		}
		
		@Override
		public Object onLiteralString(ConditionValueLiteralStringImpl value, ParamValueResolver param) {
			return value.getLiteral();
		}
		
		@Override
		public Object onLiteralAny(ConditionValueLiteralAnyImpl<?> value, ParamValueResolver param) {
			return value.getLiteral();
		}
		
		@Override
		public Object onGetter(ConditionValueGetterImpl value, ParamValueResolver param) {
			throw new UnsupportedOperationException("Should only call getters in joins");
		}
		
		@Override
		public Object onFieldReference(ConditionValueFieldRerefenceImpl value, ParamValueResolver param) {
			throw new UnsupportedOperationException("Should only call getters in joins");
		}
		
		@Override
		public Object onArray(ConditionValueArrayImpl value, ParamValueResolver param) {
			return value.getValues();
		}
	};
}
