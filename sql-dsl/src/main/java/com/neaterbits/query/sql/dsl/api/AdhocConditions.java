package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

import com.neaterbits.query.util.java8.MethodFinder;

abstract class AdhocConditions<MODEL, RESULT, QUERY extends AdhocQueryNamed<MODEL, RESULT>>

	extends AdhocConditionsStateMachine<MODEL, RESULT, AdhocConditions<MODEL, RESULT, QUERY>>
	implements
		ISharedClauseComparableCommonValue<MODEL, RESULT, Comparable<Object>, ISharedLogicalClauses<MODEL, RESULT>>,
		ISharedClauseComparableStringValue<MODEL, RESULT, ISharedLogicalClauses<MODEL, RESULT>>,
		ISharedLogicalClauses<MODEL, RESULT>,

		IAdhocAndClauses<MODEL, RESULT, Object>,
		IAdhocOrClauses<MODEL, RESULT, Object>,
		IAdhocAndOrLogicalClauses<MODEL, RESULT, Object> {

	private static final int INITIAL_CONDITIONS = 10;

	final QUERY query;
	private final int conditionsLevel;
	
	@SuppressWarnings("rawtypes")
	private Function [] conditions;
	EClauseOperator [] operators;
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
		
		addOperator(operator, value, query.getCurSource()); // Join with current-source from query

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
	private <T extends Comparable<?>> ISharedClauseComparableCommonValue<MODEL, RESULT, T, IAdhocAndClauses<MODEL, RESULT, Object>>  addAndClause(Function<?, T> getter) {
		return (ISharedClauseComparableCommonValue)intAddCondition(ConditionsType.AND, getter, null);
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	private <T extends Comparable<?>> ISharedClauseComparableCommonValue<MODEL, RESULT, T, IAdhocOrClauses<MODEL, RESULT, Object>>  addOrClause(Function<?, T> getter) {
		return (ISharedClauseComparableCommonValue)intAddCondition(ConditionsType.OR, getter, null);
	}
	
	@Override
	public final ISharedClauseComparableCommonValue<MODEL, RESULT, Integer, IAdhocOrClauses<MODEL, RESULT, Object>> or(IFunctionInteger<Object> getter) {
		return addOrClause(getter);
	}

	@Override
	public final ISharedClauseComparableCommonValue<MODEL, RESULT, Long, IAdhocOrClauses<MODEL, RESULT, Object>> or(IFunctionLong<Object> getter) {
		return addOrClause(getter);
	}
	
	@Override
	public final ISharedClauseComparableCommonValue<MODEL, RESULT, BigDecimal, IAdhocOrClauses<MODEL, RESULT, Object>> or(IFunctionBigDecimal<Object> getter) {
		return addOrClause(getter);
	}
	
	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public final ISharedClauseComparableStringValue<MODEL, RESULT, IAdhocOrClauses<MODEL, RESULT, Object>> or(StringFunction<Object> getter) {
		return (ISharedClauseComparableStringValue)intAddCondition(ConditionsType.OR, getter, null);
	}

	@Override
	public final IAdhocAndClauses<MODEL, RESULT, Object> orNest(Consumer<IAdhocAndClauses<MODEL, RESULT, Object>> andBuilder) {

		if (andBuilder == null) {
			throw new IllegalArgumentException("andBuilder == null");
		}

		intAddCondition(ConditionsType.OR, null, andBuilder);

		return this;
	}

	
	@Override
	public final ISharedClauseComparableCommonValue<MODEL, RESULT, Integer, IAdhocAndClauses<MODEL, RESULT, Object>> and(IFunctionInteger<Object> getter) {
		return addAndClause(getter);
	}

	@Override
	public final ISharedClauseComparableCommonValue<MODEL, RESULT, Long, IAdhocAndClauses<MODEL, RESULT, Object>> and(IFunctionLong<Object> getter) {
		return addAndClause(getter);
	}

	@Override
	public final ISharedClauseComparableCommonValue<MODEL, RESULT, BigDecimal, IAdhocAndClauses<MODEL, RESULT, Object>> and(IFunctionBigDecimal<Object> getter) {
		return addAndClause(getter);
	}

	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public final ISharedClauseComparableStringValue<MODEL, RESULT, IAdhocAndClauses<MODEL, RESULT, Object>> and(StringFunction<Object> getter) {
		return (ISharedClauseComparableStringValue)intAddCondition(ConditionsType.AND, getter, null);
	}

	@Override
	public final IAdhocAndClauses<MODEL, RESULT, Object> andNest(Consumer<IAdhocOrClauses<MODEL, RESULT, Object>> orBuilder) {
		if (orBuilder == null) {
			throw new IllegalArgumentException("orBuilder == null");
		}

		intAddCondition(ConditionsType.AND, null, orBuilder);

		return this;
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
	final void intMoveLastToSubAndAddSub(AdhocConditions<MODEL, RESULT, QUERY> sub) {

		if (numConditions < 2) {
			throw new IllegalStateException("Do not move when less than 2");
		}
		
		if (sub.numConditions != 0) {
			throw new IllegalStateException("Trying to move to instance with no conditions");
		}

		-- numConditions;
		
		final int idx = numConditions;
		
		sub.checkSizes(idx);

		sub.conditions[0] = this.conditions[idx];
		sub.values[0] = this.values[idx];
		sub.operators[0] = this.operators[idx];
		sub.conditionToSourceIdx[0] = this.conditionToSourceIdx[idx];
		
		this.conditions[idx] = null;
		this.values[idx] = null;
		this.operators[idx] = null;
		this.conditionToSourceIdx[idx] = 0;

		// If sub at idx, then add that
		if (this.subConditions != null && this.subConditions[idx] != null) {
			sub.intAddSub(this.subConditions[idx]);
		}
		else {
			++ sub.numConditions;
		}

		intAddSub(sub);
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

	private static final ConditionEvaluator_Comparable_String conditionValueComparable = new ConditionEvaluator_Comparable_String();

	final boolean evaluate(Object instance, int conditionIdx, ConditionValuesScratch scratch) {

		final EClauseOperator operator = operators[conditionIdx];

		final boolean ret;

		if (AdhocDebug.DEBUG_EXECUTE_CONDITIONS)  {
			AdhocDebug.println("applying condition at " + conditionIdx + " to " + instance);
		}

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

	final ConditionsType getConditionsType(int level, int [] conditionIndices) {
		
		final ConditionsType ret;
		
		if (level == this.conditionsLevel) {
			ret = getConditionsType();
		}
		else {
			final int conditionIdx = conditionIndices[this.conditionsLevel];
			
			ret = subConditions[conditionIdx].getConditionsType(level, conditionIndices);
		}

		return ret;
	}

	final int getConditionSourceIdx(int level, int [] conditionIndices) {
		
		final int conditionIdx = conditionIndices[this.conditionsLevel];

		return level == this.conditionsLevel
				? conditionToSourceIdx[conditionIdx]
				: subConditions[conditionIdx].getConditionSourceIdx(level, conditionIndices);
	}

	final boolean isSubCondition(int level, int [] conditionIndices) {
		
		final int conditionIdx = conditionIndices[this.conditionsLevel];

		return level == this.conditionsLevel
				? subConditions != null && subConditions[conditionIdx] != null
				: subConditions[conditionIdx].isSubCondition(level, conditionIndices);
	}
	
	final int getConditionsCount(int level, int [] conditionIndices) {
		
		final int ret;
		
		if (level == this.conditionsLevel) {
			ret = numConditions;
		}
		else {
			final int conditionIdx = conditionIndices[this.conditionsLevel];
			
			ret = subConditions[conditionIdx].getConditionsCount(level, conditionIndices);
		}

		return ret;
	}

	final EClauseOperator getOperator(int level, int [] conditionIndices) {

		final int conditionIdx = conditionIndices[this.conditionsLevel];

		return level == this.conditionsLevel
				? operators[conditionIdx]
				: subConditions[conditionIdx].getOperator(level, conditionIndices);
	}

	
	final boolean evaluateCondition(int level, int [] conditionIndices, Object instance, ConditionValuesScratch scratch) {
		final int conditionIdx = conditionIndices[this.conditionsLevel];

		return level == this.conditionsLevel
				? evaluate(instance, conditionIdx, scratch)
				: subConditions[conditionIdx].evaluateCondition(level, conditionIndices, instance, scratch);
						
	}

	final Method getForDebugConditionLhsMethod(int level, int[] conditionIndices, Class<?> [] sourceClasses) {
		final int conditionIdx = conditionIndices[this.conditionsLevel];

		return level == this.conditionsLevel
				? getForDebugLhsMethod(conditionIdx, sourceClasses)
				: subConditions[conditionIdx].getForDebugConditionLhsMethod(level, conditionIndices, sourceClasses);
		
	}

	final Object getForDebugConditionValue(int level, int[] conditionIndices) {
		final int conditionIdx = conditionIndices[this.conditionsLevel];

		return level == this.conditionsLevel
				? values[conditionIdx]
				: subConditions[conditionIdx].getForDebugConditionValue(level, conditionIndices);
		
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	private Method getForDebugLhsMethod(int conditionIdx, Class<?> [] sourceClasses) {
		final int sourceIdx = conditionToSourceIdx[conditionIdx];

		final Class<?> sourceClass = sourceClasses[sourceIdx];
		
		final Function getter = conditions[conditionIdx];
		
		return MethodFinder.find(sourceClass, getter);
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
