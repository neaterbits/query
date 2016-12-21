package com.neaterbits.query.sql.dsl.api;

import java.util.Arrays;
import java.util.function.Function;

import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

final class AdhocQueryClass<MODEL> extends AdhocQueryBase<MODEL, AdhocQueryClass<MODEL>> 
		implements IAdhocNumericTableResult<MODEL, Object>,
		
			ISharedClauseComparativeBaseValue<MODEL, Object, Comparable<Object>, ISharedLogicalClauses<MODEL, Object>>,
			ISharedClauseComparativeStringValue<MODEL, Object, ISharedLogicalClauses<MODEL, Object>>,
			
			ISharedLogicalClauses<MODEL, Object> {

	private static final int INITIAL_CONDITIONS = 10;
	
	@SuppressWarnings("rawtypes")
	private Function aggregateGetter;

	@SuppressWarnings("rawtypes")
	private Function [] conditions;
	private EClauseOperator [] operators;

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
		
		if (numConditions == 0) {
			this.conditions = new Function[INITIAL_CONDITIONS]; 
		}
		else if (numConditions == conditions.length) {
			this.conditions = Arrays.copyOf(conditions, conditions.length * 2);
		}
		
		this.conditions[numConditions] = function;
	}
	
	private ISharedLogicalClauses<MODEL, Object> addOperator(EClauseOperator operator) {
		if (numConditions == 0) {
			this.operators =  new EClauseOperator[INITIAL_CONDITIONS]; 
		}
		else if (numConditions == operators.length) {
			this.operators = Arrays.copyOf(operators, operators.length * 2);
		}
		
		this.operators[numConditions ++] = operator;
		
		return this;
	}
	
	
	/**************************************************************************
	** IAdhocWhere
	**************************************************************************/
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	private <R extends Comparable<R>> ISharedClauseComparativeBaseValue<MODEL, Object, R, IAdhocAndOrLogicalClauses<MODEL, Object>>
				addComparativeWhere(Function<?, ?> function) {
		
		addCondition(function);
		
		return (ISharedClauseComparativeBaseValue)this;
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	private <R extends Comparable<R>> ISharedClauseConditionValue<MODEL, Object, R, IAdhocAndOrLogicalClauses<MODEL, Object>>
			addConditionWhere(Function<?, ?> function) {

			addCondition(function);

			return (ISharedClauseConditionValue)this;
	}

	@Override
	public <T, E extends Enum<E>> ISharedClauseConditionValue<MODEL, Object, E, IAdhocAndOrLogicalClauses<MODEL, Object>> where(IFunctionEnum<T, E> func) {
		return addConditionWhere(func);
	}

	@Override
	public <T> ISharedClauseComparativeBaseValue<MODEL, Object, Integer, IAdhocAndOrLogicalClauses<MODEL, Object>> where(IFunctionInteger<T> func) {
		return addComparativeWhere(func);
	}

	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public <T> ISharedClauseComparativeStringValue<MODEL, Object, IAdhocAndOrLogicalClauses<MODEL, Object>> where(StringFunction<T> func) {
		addCondition(func);
		
		return (ISharedClauseComparativeStringValue)this;
	}

	
	/**************************************************************************
	** ISharedClauseConditionValue
	**************************************************************************/
	
	@Override
	public ISharedLogicalClauses<MODEL, Object> isEqualTo(Comparable<Object> other) {
		return addOperator(EClauseOperator.IS_EQUAL);
	}

	@Override
	public ISharedLogicalClauses<MODEL, Object> isNotEqualTo(Comparable<Object> other) {
		return addOperator(EClauseOperator.NOT_EQUAL);
	}

	@Override
	public ISharedLogicalClauses<MODEL, Object> in(Comparable<Object>... values) {
		return addOperator(EClauseOperator.IN);
	}
	
	/**************************************************************************
	** ISharedClauseComparativeBaseValue
	**************************************************************************/

	@Override
	public ISharedLogicalClauses<MODEL, Object> isGreaterThan(Comparable<Object> value) {
		return addOperator(EClauseOperator.GREATER_THAN);
	}

	@Override
	public ISharedLogicalClauses<MODEL, Object> isGreaterOrEqualTo(Comparable<Object> value) {
		return addOperator(EClauseOperator.GREATER_OR_EQUAL);
	}

	@Override
	public ISharedLogicalClauses<MODEL, Object> isLesserThan(Comparable<Object> value) {
		return addOperator(EClauseOperator.LESS_THAN);
	}

	@Override
	public ISharedLogicalClauses<MODEL, Object> isLesserOrEqualTo(Comparable<Object> value) {
		return addOperator(EClauseOperator.LESS_OR_EQUAL);
	}

	/**************************************************************************
	** ISharedClauseComparativeStringValue
	**************************************************************************/

	@Override
	public ISharedLogicalClauses<MODEL, Object> startsWith(String s) {
		return addOperator(EClauseOperator.STARTS_WITH);
	}

	@Override
	public ISharedLogicalClauses<MODEL, Object> endsWith(String s) {
		return addOperator(EClauseOperator.ENDS_WITH);
	}

	@Override
	public ISharedLogicalClauses<MODEL, Object> contains(String s) {
		return addOperator(EClauseOperator.CONTAINS);
	}

	@Override
	public ISharedLogicalClauses<MODEL, Object> matches(String regex) {
		return addOperator(EClauseOperator.MATCHES);
	}

	@Override
	public MODEL compile() {
		throw new UnsupportedOperationException("TODO");
	}
	
}

