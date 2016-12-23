package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.function.Function;

import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

final class AdhocQueryClass<MODEL> extends AdhocQueryBase<MODEL, AdhocQueryClass<MODEL>> 
		implements IAdhocNumericTableResult<MODEL, Object, Object>,
		
			ISharedClauseComparableCommonValue<MODEL, Object, Comparable<Object>, ISharedLogicalClauses<MODEL, Object>>,
			ISharedClauseComparableStringValue<MODEL, Object, ISharedLogicalClauses<MODEL, Object>>,
			
			IAdhocAndOrLogicalClauses<MODEL, Object> {

	private static final int INITIAL_CONDITIONS = 10;
	
	@SuppressWarnings("rawtypes")
	private Function aggregateGetter;

	@SuppressWarnings("rawtypes")
	private Function [] conditions;
	private EClauseOperator [] operators;
	private Object [] values;

	private int numConditions;
	
	AdhocQueryClass(Function<?, ?> aggregateGetter, EAggregateFunction aggregateFunction, ENumericType aggregateNumericType) {
		super(aggregateFunction, aggregateNumericType);

		if (aggregateGetter == null) {
			throw new IllegalArgumentException("aggregateGetter == null");
		}
		
		this.aggregateGetter = aggregateGetter;
	}

	@Override
	@SuppressWarnings("unchecked")
	public final Object getAggregateResultValue(AdhocQueryClass<MODEL> query, Object instance) {
		return aggregateGetter.apply(instance);
	}

	@Override
	public ExecuteQueryScratch createScratchArea(AdhocQueryClass<MODEL> query, QueryMetaModel queryMetaModel) {
		return this;
	}

	private void addCondition(Function<?, ?> function) {
		
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
		}
		else if (numConditions == operators.length) {
			this.operators = Arrays.copyOf(operators, operators.length * 2);
			this.values = Arrays.copyOf(values, values.length * 2);
		}
		
		this.operators[numConditions] = operator;
		this.values[numConditions ++] = value;
		
		return this;
	}
	
	/**************************************************************************
	** IAdhocGetEndClause
	**************************************************************************/
	
	@Override
	public Object get() {
		throw new UnsupportedOperationException("TODO");
	}
	
	/**************************************************************************
	** IAdhocWhere
	**************************************************************************/
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	private <R extends Comparable<R>> ISharedClauseComparableCommonValue<MODEL, Object, R, IAdhocAndOrLogicalClauses<MODEL, Object>>
				addComparativeWhere(Function<?, ?> function) {
		
		addCondition(function);
		
		return (ISharedClauseComparableCommonValue)this;
	}
	

	@SuppressWarnings({"unchecked", "rawtypes"})
	private <R extends Comparable<R>> ISharedClauseConditionValue<MODEL, Object, R, IAdhocAndOrLogicalClauses<MODEL, Object>>
			addConditionWhere(Function<?, ?> function) {

			addCondition(function);

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
		addCondition(func);
		
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
		return null;
	}

	@Override
	public <T> ISharedClauseConditionTable<MODEL, Object, Long, IAdhocAndClauses<MODEL, Object>> and(
			IFunctionLong<T> getter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> ISharedClauseComparableStringAll<MODEL, Object, IAdhocAndClauses<MODEL, Object>> and(
			StringFunction<T> getter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> ISharedClauseConditionAll<MODEL, Object, Integer, IAdhocOrClauses<MODEL, Object>> or(
			IFunctionInteger<T> getter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> ISharedClauseConditionAll<MODEL, Object, Long, IAdhocOrClauses<MODEL, Object>> or(
			IFunctionLong<T> getter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> ISharedClauseComparableStringAll<MODEL, Object, IAdhocOrClauses<MODEL, Object>> or(
			StringFunction<T> getter) {
		// TODO Auto-generated method stub
		return null;
	}

	
}

