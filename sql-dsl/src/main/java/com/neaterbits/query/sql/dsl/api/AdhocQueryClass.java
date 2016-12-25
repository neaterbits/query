package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;

import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

final class AdhocQueryClass<MODEL> extends AdhocQueryBase<MODEL, AdhocQueryClass<MODEL>> 
		implements

			IAdhocNumericTableResult<MODEL, Object, Object>,
			IAdhocNumericInstanceResult<MODEL, Object>,

			IAdhocListCollResult<MODEL, Object ,Object>,

			ISharedClauseComparableCommonValue<MODEL, Object, Comparable<Object>, ISharedLogicalClauses<MODEL, Object>>,
			ISharedClauseComparableStringValue<MODEL, Object, ISharedLogicalClauses<MODEL, Object>>,

			IAdhocAndOrLogicalClauses<MODEL, Object>,

			ExecuteQueryPOJOsInput {

	
	private static final int INITIAL_CONDITIONS = 10;
	private static final int INITIAL_SOURCES = 10;
	
	@SuppressWarnings("rawtypes")
	private Function aggregateGetter;

	@SuppressWarnings("rawtypes")
	private Function [] conditions;
	private EClauseOperator [] operators;
	private int [] conditionToSourceIdx;
	private Object [] values;

	private int numConditions;
	private ConditionsType conditionsType;

	private Collection<?> [] sources;
	private int numSources;
	
	
	AdhocQueryClass(Function<?, ?> aggregateGetter, EAggregateFunction aggregateFunction, ENumericType aggregateNumericInputType, ENumericType aggregateNumericOutputType) {
		super(aggregateFunction, aggregateNumericInputType, aggregateNumericOutputType);

		if (aggregateGetter == null) {
			throw new IllegalArgumentException("aggregateGetter == null");
		}
		
		this.aggregateGetter = aggregateGetter;
	}


	AdhocQueryClass(ECollectionType collectionType, Collection<?> coll) {

		super(collectionType);
		
		if (coll == null) {
			throw new IllegalArgumentException("coll == null");
		}

		addSource(coll);
	}

	
	private void addSource(Collection<?> collection) {
		if (collection == null) {
			throw new IllegalArgumentException("collection == null");
		}

		if (this.sources != null) {
			throw new IllegalStateException("Already has sources");
		}
		
		this.sources = new Collection<?>[INITIAL_SOURCES];
		this.numSources = 1;
		
		this.sources[0] = collection;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public final Object getAggregateResultValue(AdhocQueryClass<MODEL> query, Object instance) {
		return aggregateGetter.apply(instance);
	}

	@Override
	public ExecuteQueryScratch createScratchArea(AdhocQueryClass<MODEL> query, QueryMetaModel queryMetaModel) {

		initScratchArea(getNumResultParts(this), getSourceCount(this), numConditions);
		
		return this;
	}

	private void addCondition(ConditionsType conditionsType, Function<?, ?> function) {
		
		if (conditionsType == null) {
			throw new IllegalArgumentException("conditionsType == null");
		}
		
		if (this.conditionsType == null) {
			if (conditionsType != ConditionsType.SINGLE) {
				throw new IllegalArgumentException("Expected single-condition");
			}
			this.conditionsType = conditionsType;
		}
		else {
			if (conditionsType == ConditionsType.SINGLE) {
				this.conditionsType = conditionsType;
			}
			else if (conditionsType != this.conditionsType) {
				throw new IllegalStateException("Mismatch in condition from " + conditionsType + " to " + this.conditionsType);
			}
		}
		
		if (function == null) {
			throw new IllegalArgumentException("function == null");
		}

		if (numConditions == 0) {
			this.conditions = new Function[INITIAL_CONDITIONS]; 
		}
		else if (numConditions == conditions.length) {
			this.conditions = Arrays.copyOf(conditions, conditions.length * 2);
		}
		
		this.conditions[numConditions] = function;
	}
	
	private ISharedLogicalClauses<MODEL, Object> addOperator(EClauseOperator operator, Object value) {
		if (numConditions == 0) {
			this.operators = new EClauseOperator[INITIAL_CONDITIONS];
			this.values = new Object[INITIAL_CONDITIONS];
			this.conditionToSourceIdx = new int[INITIAL_CONDITIONS];
			
		}
		else if (numConditions == operators.length) {
			
			this.operators = Arrays.copyOf(operators, operators.length * 2);
			this.values = Arrays.copyOf(values, values.length * 2);
			this.conditionToSourceIdx = Arrays.copyOf(conditionToSourceIdx, conditionToSourceIdx.length * 2);
		}

		this.operators[numConditions] = operator;
		this.conditionToSourceIdx[numConditions] = numSources - 1;
		this.values[numConditions ++] = value;
		
		return this;
	}
	
	/**************************************************************************
	** ExecuteQueryPOJOsInput
	**************************************************************************/
	@Override
	public Collection<?> getPOJOs(int idx) {
		return sources[idx];
	}
	
	/**************************************************************************
	** ExeutableQuery⋅
	**************************************************************************/
	@Override
	public final int getSourceCount(AdhocQueryClass<MODEL> query) {
		return numSources;
	}

	@Override
	public final ConditionsType getConditionsType(AdhocQueryClass<MODEL> query) {
		return conditionsType;
	}

	@Override
	public final int getConditionCount(AdhocQueryClass<MODEL> query) {
		return numConditions;
	}

	@Override
	public final int getConditionSourceIdx(AdhocQueryClass<MODEL> query, int conditionIdx) {
		return conditionToSourceIdx[conditionIdx];
	}

	private static final ConditionEvaluatorComparable conditionValueComparable = new ConditionEvaluatorComparable();

	@Override
	public final boolean evaluateCondition(AdhocQueryClass<MODEL> query, Object instance, int conditionIdx, ConditionValuesScratch scratch) {

		final EClauseOperator operator = operators[conditionIdx];
		
		final boolean ret;

		@SuppressWarnings("unchecked")
		final Object instanceValue = conditions[conditionIdx].apply(instance);
		final Object compareValue = values[conditionIdx];

		initConditionScratchValues(instanceValue, compareValue);

		switch (operator) {
		case IS_EQUAL:
			ret = conditionValueComparable.onEqualTo(null, this);
			break;
			
		case NOT_EQUAL:
			ret = conditionValueComparable.onNotEqualTo(null, this);
			break;

		case LESS_THAN:
			ret = conditionValueComparable.onLessThan(null, this);
			break;

		case LESS_OR_EQUAL:
			ret = conditionValueComparable.onLessThanOrEqual(null, this);
			break;

		case GREATER_THAN:
			ret = conditionValueComparable.onGreaterThan(null, this);
			break;

		case GREATER_OR_EQUAL:
			ret = conditionValueComparable.onGreaterThanOrEqual(null, this);
			break;

		case IN:
			ret = conditionValueComparable.onIn(null, this);
			break;

		case STARTS_WITH:
			ret = conditionValueComparable.onStartsWith(null, this);
			break;
			
		case ENDS_WITH:
			ret = conditionValueComparable.onEndsWith(null, this);
			break;

		case CONTAINS:
			ret = conditionValueComparable.onContains(null, this);
			break;

		case MATCHES:
			ret = conditionValueComparable.onMatches(null, this);
			break;			
			
		default:
			throw new UnsupportedOperationException("Unknown condition operator " + operator);
		}

		return ret;
	}
	

	/**************************************************************************
	** IAdhocSelectSource
	**************************************************************************/
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public final IAdhocWhereOrJoin<MODEL, Object, Object> from(Collection<Object> collection) {
		
		addSource(collection);
		
		return (IAdhocWhereOrJoin)this;
	}
	

	/**************************************************************************
	** IAdhocGetEndClause
	**************************************************************************/
	
	@Override
	public Object get() {
		final ExecuteQueryPOJOs<AdhocQueryClass<MODEL>> executor = new ExecuteQueryPOJOs<>(this);
		
		final Object ret = executor.execute(this, this, null, null);
		
		return ret;
	}
	
	/**************************************************************************
	** IAdhocWhere
	**************************************************************************/
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	private <R extends Comparable<R>> ISharedClauseComparableCommonValue<MODEL, Object, R, IAdhocAndOrLogicalClauses<MODEL, Object>>
				addComparativeWhere(Function<?, ?> function) {
		
		addCondition(ConditionsType.SINGLE, function);
		
		return (ISharedClauseComparableCommonValue)this;
	}
	

	@SuppressWarnings({"unchecked", "rawtypes"})
	private <R extends Comparable<R>> ISharedClauseConditionValue<MODEL, Object, R, IAdhocAndOrLogicalClauses<MODEL, Object>>
			addConditionWhere(Function<?, ?> function) {

			addCondition(ConditionsType.SINGLE, function);

			return (ISharedClauseConditionValue)this;
	}

	@Override
	public <E extends Enum<E>> ISharedClauseConditionValue<MODEL, Object, E, IAdhocAndOrLogicalClauses<MODEL, Object>> where(IFunctionEnum<Object, E> func) {
		return addConditionWhere(func);
	}	
	
	@Override
	public ISharedClauseComparableCommonValue<MODEL, Object, Integer, IAdhocAndOrLogicalClauses<MODEL, Object>> where(IFunctionInteger<Object> func) {
		return addComparativeWhere(func);
	}

	@Override
	public ISharedClauseComparableCommonValue<MODEL, Object, BigDecimal, IAdhocAndOrLogicalClauses<MODEL, Object>> where(IFunctionBigDecimal<Object> func) {
		return addComparativeWhere(func);
	}

	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public ISharedClauseComparableStringValue<MODEL, Object, IAdhocAndOrLogicalClauses<MODEL, Object>> where(StringFunction<Object> func) {
		addCondition(null, func);
		
		return (ISharedClauseComparableStringValue)this;
	}

	
	/**************************************************************************
	** ISharedClauseConditionValue
	**************************************************************************/
	
	@Override
	public ISharedLogicalClauses<MODEL, Object> isEqualTo(Comparable<Object> other) {
		return addOperator(EClauseOperator.IS_EQUAL, other);
	}

	@Override
	public ISharedLogicalClauses<MODEL, Object> isNotEqualTo(Comparable<Object> other) {
		return addOperator(EClauseOperator.NOT_EQUAL, other);
	}

	@Override
	public ISharedLogicalClauses<MODEL, Object> in(@SuppressWarnings("unchecked") Comparable<Object>... values) {
		return addOperator(EClauseOperator.IN, values);
	}
	
	/**************************************************************************
	** ISharedClauseComparativeBaseValue
	**************************************************************************/

	@Override
	public ISharedLogicalClauses<MODEL, Object> isGreaterThan(Comparable<Object> value) {
		return addOperator(EClauseOperator.GREATER_THAN, value);
	}

	@Override
	public ISharedLogicalClauses<MODEL, Object> isGreaterOrEqualTo(Comparable<Object> value) {
		return addOperator(EClauseOperator.GREATER_OR_EQUAL, value);
	}

	@Override
	public ISharedLogicalClauses<MODEL, Object> isLesserThan(Comparable<Object> value) {
		return addOperator(EClauseOperator.LESS_THAN, value);
	}

	@Override
	public ISharedLogicalClauses<MODEL, Object> isLesserOrEqualTo(Comparable<Object> value) {
		return addOperator(EClauseOperator.LESS_OR_EQUAL, value);
	}

	/**************************************************************************
	** ISharedClauseComparativeStringValue
	**************************************************************************/

	@Override
	public ISharedLogicalClauses<MODEL, Object> startsWith(String s) {
		return addOperator(EClauseOperator.STARTS_WITH, s);
	}

	@Override
	public ISharedLogicalClauses<MODEL, Object> endsWith(String s) {
		return addOperator(EClauseOperator.ENDS_WITH, s);
	}

	@Override
	public ISharedLogicalClauses<MODEL, Object> contains(String s) {
		return addOperator(EClauseOperator.CONTAINS, s);
	}

	@Override
	public ISharedLogicalClauses<MODEL, Object> matches(String regex) {
		return addOperator(EClauseOperator.MATCHES, regex);
	}

	/**************************************************************************
	** IAdhocAndClauses
	**************************************************************************/
	
	@Override
	public <T> ISharedClauseConditionTable<MODEL, Object, Integer, IAdhocAndClauses<MODEL, Object>> and(IFunctionInteger<T> getter) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public <T> ISharedClauseConditionTable<MODEL, Object, Long, IAdhocAndClauses<MODEL, Object>> and(IFunctionLong<T> getter) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public <T> ISharedClauseComparableStringAll<MODEL, Object, IAdhocAndClauses<MODEL, Object>> and(StringFunction<T> getter) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public <T> ISharedClauseConditionAll<MODEL, Object, Integer, IAdhocOrClauses<MODEL, Object>> or(
			IFunctionInteger<T> getter) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public <T> ISharedClauseConditionAll<MODEL, Object, Long, IAdhocOrClauses<MODEL, Object>> or(IFunctionLong<T> getter) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public <T> ISharedClauseComparableStringAll<MODEL, Object, IAdhocOrClauses<MODEL, Object>> or(StringFunction<T> getter) {
		throw new UnsupportedOperationException("TODO");
	}
}

