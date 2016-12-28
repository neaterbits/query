package com.neaterbits.query.sql.dsl.api;

import java.util.Arrays;
import java.util.function.Function;

abstract class AdhocConditions<MODEL, RESULT, QUERY extends AdhocQueryClass<MODEL, RESULT>>

	extends AdhocConditionsStateMachine<MODEL, RESULT, AdhocConditions<MODEL, RESULT, QUERY>>
	implements
		ISharedClauseComparableCommonValue<MODEL, RESULT, Comparable<Object>, ISharedLogicalClauses<MODEL, RESULT>>,
		ISharedClauseComparableStringValue<MODEL, RESULT, ISharedLogicalClauses<MODEL, RESULT>>,
		ISharedLogicalClauses<MODEL, RESULT>,

		IAdhocAndClauses<MODEL, RESULT>,
		IAdhocOrClauses<MODEL, RESULT>,
		IAdhocAndOrLogicalClauses<MODEL, RESULT> {

	private static final int INITIAL_CONDITIONS = 10;

	final QUERY query;
	private final int conditionsLevel;
	
	@SuppressWarnings("rawtypes")
	private Function [] conditions;
	private EClauseOperator [] operators;
	int [] conditionToSourceIdx;
	private Object [] values;

	private AdhocConditions<MODEL, RESULT, QUERY> [] subConditions;
	
	int numConditions;
	
	AdhocConditions(QUERY query, int level) {

		if (query == null) {
			throw new IllegalArgumentException("query == null");
		}

		this.query = query;
		this.conditionsLevel = level;

		this.conditions 			= new Function		 [INITIAL_CONDITIONS]; 
		this.operators 				= new EClauseOperator[INITIAL_CONDITIONS];
		this.values 				= new Object		 [INITIAL_CONDITIONS];
		this.conditionToSourceIdx 	= new int			 [INITIAL_CONDITIONS];

	}

	@Override
	public final RESULT get() {
		return query.get();
	}

	/**************************************************************************
	** ISharedClauseConditionValue
	**************************************************************************/
	private ISharedLogicalClauses<MODEL, RESULT> addOperatorRet(EClauseOperator operator, Object value) {
		intAddOperator(operator, value, 0); // Always join source 0

		return this;
	}
	
	@Override
	public final ISharedLogicalClauses<MODEL, RESULT> isEqualTo(Comparable<Object> other) {
		return addOperatorRet(EClauseOperator.IS_EQUAL, other);
	}

	@Override
	public final ISharedLogicalClauses<MODEL, RESULT> isNotEqualTo(Comparable<Object> other) {
		return addOperatorRet(EClauseOperator.NOT_EQUAL, other);
	}

	@Override
	public final ISharedLogicalClauses<MODEL, RESULT> in(@SuppressWarnings("unchecked") Comparable<Object>... values) {
		return addOperatorRet(EClauseOperator.IN, values);
	}
	
	/**************************************************************************
	** ISharedClauseComparativeBaseValue
	**************************************************************************/

	@Override
	public final ISharedLogicalClauses<MODEL, RESULT> isGreaterThan(Comparable<Object> value) {
		return addOperatorRet(EClauseOperator.GREATER_THAN, value);
	}

	@Override
	public final ISharedLogicalClauses<MODEL, RESULT> isGreaterOrEqualTo(Comparable<Object> value) {
		return addOperatorRet(EClauseOperator.GREATER_OR_EQUAL, value);
	}

	@Override
	public final ISharedLogicalClauses<MODEL, RESULT> isLessThan(Comparable<Object> value) {
		return addOperatorRet(EClauseOperator.LESS_THAN, value);
	}

	@Override
	public final ISharedLogicalClauses<MODEL, RESULT> isLessOrEqualTo(Comparable<Object> value) {
		return addOperatorRet(EClauseOperator.LESS_OR_EQUAL, value);
	}

	/**************************************************************************
	** ISharedClauseComparativeStringValue
	**************************************************************************/

	@Override
	public final ISharedLogicalClauses<MODEL, RESULT> startsWith(String s) {
		return addOperatorRet(EClauseOperator.STARTS_WITH, s);
	}

	@Override
	public final ISharedLogicalClauses<MODEL, RESULT> endsWith(String s) {
		return addOperatorRet(EClauseOperator.ENDS_WITH, s);
	}

	@Override
	public final ISharedLogicalClauses<MODEL, RESULT> contains(String s) {
		return addOperatorRet(EClauseOperator.CONTAINS, s);
	}

	@Override
	public final ISharedLogicalClauses<MODEL, RESULT> matches(String regex) {
		return addOperatorRet(EClauseOperator.MATCHES, regex);
	}


	/**************************************************************************
	** IAdhocAndOrLogicalClauses
	**************************************************************************/
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	private <T extends Comparable<?>> ISharedClauseComparableCommonValue<MODEL, RESULT, T, IAdhocAndClauses<MODEL, RESULT>>  addAndClause(Function<?, T> getter) {
		intAddCondition(ConditionsType.AND, getter);
		
		return (ISharedClauseComparableCommonValue)this;
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	private <T extends Comparable<?>> ISharedClauseComparableCommonValue<MODEL, RESULT, T, IAdhocOrClauses<MODEL, RESULT>>  addOrClause(Function<?, T> getter) {
		intAddCondition(ConditionsType.OR, getter);
		
		return (ISharedClauseComparableCommonValue)this;
	}
	
	@Override
	public final <T> ISharedClauseComparableCommonValue<MODEL, RESULT, Integer, IAdhocOrClauses<MODEL, RESULT>> or(IFunctionInteger<T> getter) {
		return addOrClause(getter);
	}


	@Override
	public final <T> ISharedClauseComparableCommonValue<MODEL, RESULT, Long, IAdhocOrClauses<MODEL, RESULT>> or(IFunctionLong<T> getter) {
		return addOrClause(getter);
	}
	
	
	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public final <T> ISharedClauseComparableStringValue<MODEL, RESULT, IAdhocOrClauses<MODEL, RESULT>> or(StringFunction<T> getter) {
		intAddCondition(ConditionsType.OR, getter);

		return (ISharedClauseComparableStringValue)this;
	}


	@Override
	public final <T> ISharedClauseComparableCommonValue<MODEL, RESULT, Integer, IAdhocAndClauses<MODEL, RESULT>> and(IFunctionInteger<T> getter) {
		return addAndClause(getter);
	}

	@Override
	public final <T> ISharedClauseComparableCommonValue<MODEL, RESULT, Long, IAdhocAndClauses<MODEL, RESULT>> and(IFunctionLong<T> getter) {
		return addAndClause(getter);
	}

	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public final <T> ISharedClauseComparableStringValue<MODEL, RESULT, IAdhocAndClauses<MODEL, RESULT>> and(StringFunction<T> getter) {
		intAddCondition(ConditionsType.AND, getter);

		return (ISharedClauseComparableStringValue)this;
	}

	@Override
	final int getLevel() {
		return conditionsLevel;
	}

	@Override
	final void intAddConditionToArray(Function<?, ?> function) {
		checkSizes(numConditions);

		this.conditions[numConditions] = function;
	}
	
	
	@Override
	final void intAddOperator(EClauseOperator operator, Object value, int sourceIdx) {
		
		checkSizes(numConditions);

		this.operators[numConditions] = operator;
		
		// !! Always 0 here since this is the outer-loop.
		// !! Joins will add more more select-sources before this is called so cannot go with numSources - 1
		this.conditionToSourceIdx[numConditions] = sourceIdx;
		this.values[numConditions ++] = value;
	}

	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	final void intAddSub(AdhocConditions<MODEL, RESULT, QUERY> sub) {

		// Assure that space in existing
		checkSizes(numConditions);

		if (subConditions == null) {
			this.subConditions = new AdhocConditions[conditions.length];
		}

		subConditions[numConditions ++] = (AdhocConditions)sub;
	}

	private static final ConditionEvaluatorComparableString conditionValueComparable = new ConditionEvaluatorComparableString();

	final boolean evaluate(Object instance, int conditionIdx, ConditionValuesScratch scratch) {

		final EClauseOperator operator = operators[conditionIdx];

		final boolean ret;

		@SuppressWarnings("unchecked")
		final Object instanceValue = conditions[conditionIdx].apply(instance);
		final Object compareValue = values[conditionIdx];

		scratch.initConditionScratchValues(instanceValue, compareValue);

		switch (operator) {
		case IS_EQUAL:
			ret = conditionValueComparable.onEqualTo(null, scratch);
			break;
			
		case NOT_EQUAL:
			ret = conditionValueComparable.onNotEqualTo(null, scratch);
			break;

		case LESS_THAN:
			ret = conditionValueComparable.onLessThan(null, scratch);
			break;

		case LESS_OR_EQUAL:
			ret = conditionValueComparable.onLessThanOrEqual(null, scratch);
			break;

		case GREATER_THAN:
			ret = conditionValueComparable.onGreaterThan(null, scratch);
			break;

		case GREATER_OR_EQUAL:
			ret = conditionValueComparable.onGreaterThanOrEqual(null, scratch);
			break;

		case IN:
			ret = conditionValueComparable.onIn(null, scratch);
			break;

		case STARTS_WITH:
			ret = conditionValueComparable.onStartsWith(null, scratch);
			break;
			
		case ENDS_WITH:
			ret = conditionValueComparable.onEndsWith(null, scratch);
			break;

		case CONTAINS:
			ret = conditionValueComparable.onContains(null, scratch);
			break;

		case MATCHES:
			ret = conditionValueComparable.onMatches(null, scratch);
			break;			
			
		default:
			throw new UnsupportedOperationException("Unknown condition operator " + operator);
		}

		return ret;
	}
	
	@Override
	final boolean hasSubConditions() {
		return subConditions != null;
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	AdhocConditions<MODEL, RESULT, QUERY> createConditions(int level) {
		return (AdhocConditions)query.createConditions(level);
	}

	final ConditionsType getConditionsType(int [] conditionIndices, int level, int numLevels) {
		return level == this.conditionsLevel
				? getConditionsType()
				: subConditions[conditionIndices[level]].getConditionsType(conditionIndices, level + 1, numLevels);
	}

	final int getConditionSourceIdx(int [] conditionIndices, int level, int numLevels) {
		
		final int conditionIdx = conditionIndices[level];

		return level == this.conditionsLevel
				? conditionToSourceIdx[conditionIdx]
				: subConditions[conditionIdx].getConditionSourceIdx(conditionIndices, level + 1, numLevels);
	}

	final boolean evaluateCondition(int [] conditionIndices, int level, int numLevels, Object instance, ConditionValuesScratch scratch) {
		final int conditionIdx = conditionIndices[level];

		return level == this.conditionsLevel
				? evaluate(instance, conditionIdx, scratch)
				: subConditions[conditionIdx].evaluateCondition(conditionIndices, level + 1, numLevels, instance, scratch);
						
	}
	
	// Depth of sub-levels
	final int getMaxDepth() {
		
		int ret;
		
		if (subConditions != null) {
			
			ret = 1;
			
			for (AdhocConditions<MODEL, RESULT, QUERY> sub : subConditions) {
				if (sub != null) {
					final int m = sub.getMaxDepth();
					
					if (m + 1 > ret) {
						ret = m + 1; // + 1 for current level
					}
				}
			}
		}
		else {
			ret = 1;
		}

		return ret;
	}

	private void checkSizes(int num) {

		if (num == conditions.length) {
			this.conditions = Arrays.copyOf(conditions, conditions.length * 2);
			this.operators = Arrays.copyOf(operators, operators.length * 2);
			this.values = Arrays.copyOf(values, values.length * 2);
			this.conditionToSourceIdx = Arrays.copyOf(conditionToSourceIdx, conditionToSourceIdx.length * 2);

			if (subConditions != null) {
				this.subConditions = Arrays.copyOf(subConditions, subConditions.length * 2);
			}
		}
		else if (num > conditions.length) {
			throw new IllegalStateException("Never to be called with larger than num");
		}
	}
}
