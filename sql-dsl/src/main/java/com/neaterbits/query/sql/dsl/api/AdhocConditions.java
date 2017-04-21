package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

import com.neaterbits.query.util.java8.MethodFinder;

abstract class AdhocConditions<MODEL, RESULT, QUERY extends AdhocQuery_Named<MODEL, RESULT>>

	extends AdhocConditionsStateMachine<MODEL, RESULT, AdhocConditions<MODEL, RESULT, QUERY>>
	implements
		ISharedCondition_Comparable_Common_Value<MODEL, RESULT, Comparable<Object>, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedCondition_Comparable_String_Value<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedLogical_Base<MODEL, RESULT>,

		IAdhocLogical_And<MODEL, RESULT, Object>,
		IAdhocLogical_Or<MODEL, RESULT, Object>,
		IAdhocLogical_And_Or<MODEL, RESULT, Object>,
		
		IAdhocFunctions_Callback<MODEL, RESULT, AdhocConditions<MODEL, RESULT, QUERY>>{

	private static final int INITIAL_CONDITIONS = 10;

	final QUERY query;
	private final int conditionsLevel;
	
	@SuppressWarnings("rawtypes")
	private Function [] conditions;
	EClauseOperator [] operators;
	int [] conditionToSourceIdx;
	private Object [] values;

	private AdhocConditions<MODEL, RESULT, QUERY> [] subConditions;

	// functions for conditions
	private AdhocFunctions<MODEL, RESULT, ?, ?, ?, ?, ?> [] conditionFunctions;

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
		
		this.conditionFunctions = null;
	}

	@Override
	public final RESULT get() {
		return query.get();
	}

	/**************************************************************************
	** ISharedClauseConditionValue
	**************************************************************************/
	private ISharedLogical_Base<MODEL, RESULT> addOperatorRet(EClauseOperator operator, Object value) {
		
		addOperator(operator, value, query.getCurSource()); // Join with current-source from query

		return this;
	}

	private ISharedLogical_Base<MODEL, RESULT> addInOperatorRet(EClauseOperator operator, Comparable<?>  [] values) {
		return addOperatorRet(
				operator,
				Constants.IN_AS_LIST ? Arrays.asList(values) : values);
	}
	
	@Override
	public final ISharedLogical_Base<MODEL, RESULT> isEqualTo(Comparable<Object> other) {
		return addOperatorRet(EClauseOperator.IS_EQUAL, other);
	}

	@Override
	public final ISharedLogical_Base<MODEL, RESULT> isNotEqualTo(Comparable<Object> other) {
		return addOperatorRet(EClauseOperator.NOT_EQUAL, other);
	}

	@Override
	public final ISharedLogical_Base<MODEL, RESULT> in(@SuppressWarnings("unchecked") Comparable<Object>... values) {
		return addInOperatorRet(EClauseOperator.IN, values);
	}
	
	/**************************************************************************
	** ISharedClauseComparativeBaseValue
	**************************************************************************/

	@Override
	public final ISharedLogical_Base<MODEL, RESULT> isGreaterThan(Comparable<Object> value) {
		return addOperatorRet(EClauseOperator.GREATER_THAN, value);
	}

	@Override
	public final ISharedLogical_Base<MODEL, RESULT> isGreaterOrEqualTo(Comparable<Object> value) {
		return addOperatorRet(EClauseOperator.GREATER_OR_EQUAL, value);
	}

	@Override
	public final ISharedLogical_Base<MODEL, RESULT> isLessThan(Comparable<Object> value) {
		return addOperatorRet(EClauseOperator.LESS_THAN, value);
	}

	@Override
	public final ISharedLogical_Base<MODEL, RESULT> isLessOrEqualTo(Comparable<Object> value) {
		return addOperatorRet(EClauseOperator.LESS_OR_EQUAL, value);
	}

	/**************************************************************************
	** ISharedClauseComparativeStringValue
	**************************************************************************/

	@Override
	public final ISharedLogical_Base<MODEL, RESULT> startsWith(String s) {
		return addOperatorRet(EClauseOperator.STARTS_WITH, s);
	}

	@Override
	public final ISharedLogical_Base<MODEL, RESULT> endsWith(String s) {
		return addOperatorRet(EClauseOperator.ENDS_WITH, s);
	}

	@Override
	public final ISharedLogical_Base<MODEL, RESULT> contains(String s) {
		return addOperatorRet(EClauseOperator.CONTAINS, s);
	}

	@Override
	public final ISharedLogical_Base<MODEL, RESULT> matches(String regex) {
		return addOperatorRet(EClauseOperator.MATCHES, regex);
	}


	/**************************************************************************
	** IAdhocAndOrLogicalClauses
	**************************************************************************/
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	private <T extends Comparable<?>> ISharedCondition_Comparable_Common_Value<MODEL, RESULT, T, IAdhocLogical_And<MODEL, RESULT, Object>>  addAndClause(Function<?, T> getter) {
		return (ISharedCondition_Comparable_Common_Value)addCondition(ConditionsType.AND, null, getter, null);
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	private <T extends Comparable<?>> ISharedCondition_Comparable_Common_Value<MODEL, RESULT, T, IAdhocLogical_Or<MODEL, RESULT, Object>>  addOrClause(Function<?, T> getter) {
		return (ISharedCondition_Comparable_Common_Value)addCondition(ConditionsType.OR, null, getter, null);
	}
	
	@Override
	public final ISharedCondition_Comparable_Common_Value<MODEL, RESULT, Integer, IAdhocLogical_Or<MODEL, RESULT, Object>> or(IFunctionInteger<Object> getter) {
		return addOrClause(getter);
	}

	@Override
	public final ISharedCondition_Comparable_Common_Value<MODEL, RESULT, Long, IAdhocLogical_Or<MODEL, RESULT, Object>> or(IFunctionLong<Object> getter) {
		return addOrClause(getter);
	}
	
	@Override
	public final ISharedCondition_Comparable_Common_Value<MODEL, RESULT, BigDecimal, IAdhocLogical_Or<MODEL, RESULT, Object>> or(IFunctionBigDecimal<Object> getter) {
		return addOrClause(getter);
	}
	
	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public final ISharedCondition_Comparable_String_Value<MODEL, RESULT, IAdhocLogical_Or<MODEL, RESULT, Object>> or(IFunctionString<Object> getter) {
		return (ISharedCondition_Comparable_String_Value)addCondition(ConditionsType.OR, null, getter, null);
	}

	@Override
	public final IAdhocFunctions_Initial<
			MODEL,
			RESULT,
			Object,
			IAdhocLogical_Or<MODEL, RESULT, Object>,
			ISharedCondition_Comparable_Common_Value<MODEL, RESULT, Integer, IAdhocLogical_Or<MODEL, RESULT, Object>>,
			ISharedCondition_Comparable_Common_Value<MODEL, RESULT, Long, IAdhocLogical_Or<MODEL, RESULT, Object>>,
			ISharedCondition_Comparable_String_Value<MODEL, RESULT, IAdhocLogical_Or<MODEL, RESULT, Object>>>
	
	
		or() {
		
		
		return new AdhocFunctions<>(ConditionsType.OR, this);
	}
	
	@Override
	public final IAdhocLogical_And<MODEL, RESULT, Object> orNest(Consumer<IAdhocLogical_And<MODEL, RESULT, Object>> andBuilder) {

		if (andBuilder == null) {
			throw new IllegalArgumentException("andBuilder == null");
		}

		addCondition(ConditionsType.OR, null, null, andBuilder);

		return this;
	}

	
	@Override
	public final ISharedCondition_Comparable_Common_Value<MODEL, RESULT, Integer, IAdhocLogical_And<MODEL, RESULT, Object>> and(IFunctionInteger<Object> getter) {
		return addAndClause(getter);
	}

	@Override
	public final ISharedCondition_Comparable_Common_Value<MODEL, RESULT, Long, IAdhocLogical_And<MODEL, RESULT, Object>> and(IFunctionLong<Object> getter) {
		return addAndClause(getter);
	}

	@Override
	public final ISharedCondition_Comparable_Common_Value<MODEL, RESULT, BigDecimal, IAdhocLogical_And<MODEL, RESULT, Object>> and(IFunctionBigDecimal<Object> getter) {
		return addAndClause(getter);
	}

	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public final ISharedCondition_Comparable_String_Value<MODEL, RESULT, IAdhocLogical_And<MODEL, RESULT, Object>> and(IFunctionString<Object> getter) {
		return (ISharedCondition_Comparable_String_Value)addCondition(ConditionsType.AND, null, getter, null);
	}
	
	@Override
	public final IAdhocFunctions_Initial<
			MODEL,
			RESULT,
			Object,
			IAdhocLogical_And<MODEL, RESULT, Object>,
			ISharedCondition_Comparable_Common_Value<MODEL, RESULT, Integer, IAdhocLogical_And<MODEL, RESULT, Object>>,
			ISharedCondition_Comparable_Common_Value<MODEL, RESULT, Long, IAdhocLogical_And<MODEL, RESULT, Object>>,
			ISharedCondition_Comparable_String_Value<MODEL, RESULT, IAdhocLogical_And<MODEL, RESULT, Object>>>
	
	
		and() {
		
		
		return new AdhocFunctions<>(ConditionsType.AND, this);
	}

	@Override
	public final IAdhocLogical_And<MODEL, RESULT, Object> andNest(Consumer<IAdhocLogical_Or<MODEL, RESULT, Object>> orBuilder) {
		if (orBuilder == null) {
			throw new IllegalArgumentException("orBuilder == null");
		}

		addCondition(ConditionsType.AND, null, null, orBuilder);

		return this;
	}


	/**************************************************************************
	** IAdhoc functions callback
	**************************************************************************/

	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Comparable<?>, AdhocConditions<MODEL, RESULT, QUERY>> onComparable(
			AdhocFunctions<MODEL, RESULT, ?, ?, ?, ?, ?> functions, Function<?, ? extends Comparable<?>> getter) {

		
		addCondition(functions.getConditionsType(), functions, getter, null);

		return (ISharedCondition_Comparable_Common_Base)this;
	}

	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public ISharedCondition_Comparable_String_Base<MODEL, RESULT, AdhocConditions<MODEL, RESULT, QUERY>>
	
			onString(AdhocFunctions<MODEL, RESULT, ?, ?, ?, ?, ?> functions, IFunctionString<?> getter) {

		addCondition(functions.getConditionsType(), functions, getter, null);

		return (ISharedCondition_Comparable_String_Base)this;
	}
	
	
	
	/**************************************************************************
	** Overrides
	**************************************************************************/
	
	@Override
	final int getLevel() {
		return conditionsLevel;
	}


	
	@SuppressWarnings("unchecked")
	@Override
	final void intAddConditionToArray(AdhocFunctions<MODEL, RESULT, ?, ?, ?, ?, ?> functions, Function<?, ?> function) {

		if (functions != null) {
			// allocate condition functions if not yet allocated
			if (this.conditionFunctions == null) {
				this.conditionFunctions = new AdhocFunctions[conditions.length];
			}
		}
		
		checkSizes(numConditions);

		if (functions != null) {
			if (conditionFunctions[numConditions] != null) {
				throw new IllegalStateException("condition functions already set at " + numConditions);
			}
			
			// AdhocFunctions contain all functions 
			conditionFunctions[numConditions] = functions;
		}

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
		Object instanceValue = conditions[conditionIdx].apply(instance);
		final Object compareValue = values[conditionIdx];

		if (conditionFunctions != null) {
			// Must apply any functions
			if (conditionFunctions[conditionIdx] != null) {
				
				// re-compute value using function
				instanceValue = conditionFunctions[conditionIdx].applyTo(instanceValue);
			}
		}

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
	
	/**************************************************************************
	** Utilities
	**************************************************************************/

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
			
			if (conditionFunctions != null) {
				this.conditionFunctions = Arrays.copyOf(conditionFunctions, conditionFunctions.length * 2);
			}
			
		}
		else if (num > conditions.length) {
			throw new IllegalStateException("Never to be called with larger than num");
		}
	}
}
