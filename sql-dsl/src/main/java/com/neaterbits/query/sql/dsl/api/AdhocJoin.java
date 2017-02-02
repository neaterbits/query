package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;

import com.neaterbits.query.sql.dsl.api.entity.OneToManyJoinConditionResolver;

final class AdhocJoin<MODEL, RESULT>
	implements
		IAdhocJoinSub<MODEL, RESULT, Object, Object>,
		IAdhocJoinSubOrCondition<MODEL, RESULT, Object, Object>,
		IAdhocWhereOrJoinSub<MODEL, RESULT, Object>,

		ISharedCondition_Comparable_Common_Value<MODEL, RESULT, Comparable<Object>, ISharedLogical_Base<MODEL, RESULT>>,
		ISharedCondition_Comparable_String_Value<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
		
		ISharedLogical_Base<MODEL, RESULT>,
		IAdhocLogical_And_Or<MODEL, RESULT, Object>,
		
		IAdhocFunctions_Callback<MODEL, RESULT, ISharedLogical_Base<MODEL,RESULT>> {

	
	enum State {
		INIT,
		WHERE_GETTER_ADDED,
		WHERE_CLAUSE_ADDED,
		COLLECT_FUNCTIONS,
		FUNCTIONS_COLLECTED,
		NO_FUNCTIONS
	};
	
	private static final int JOIN_CONDITIONS_INITIAL = 10;
	
	private State state;
	
	final AdhocQuery_Named<MODEL, RESULT> query;
	final EJoinType type;
	final int leftSourceIdx;
	final int rightSourceIdx;

	JoinCondition [] conditions;
	int numConditions;

	ConditionsType conditionsType;
	
	
	// Cache initial where-clause until we know whether this is
	@SuppressWarnings("rawtypes")
	Function whereGetter;
	EClauseOperator whereOperator;
	Object whereValue;
	AdhocFunctions<MODEL, RESULT, ?, ?, ?, ?> whereFunctions;

	
	
	AdhocJoin(AdhocQuery_Named<MODEL, RESULT> query, EJoinType type, int leftSourceIdx, int rightSourceIdx) {

		if (query == null) {
			throw new IllegalArgumentException("query == null");
		}

		if (type == null) {
			throw new IllegalArgumentException("type == null");
		}

		this.query = query;
		this.type = type;
		this.leftSourceIdx = leftSourceIdx;
		this.rightSourceIdx = rightSourceIdx;
		
		this.conditionsType = ConditionsType.NONE;
		
		this.state = State.INIT;
	}

	
	private void setState(State newState) {

		if (newState == null) {
			throw new IllegalArgumentException("newState == null");
		}

		this.state = newState;
	}


	/**************************************************************************
	** Join conditions
	**************************************************************************/
	@Override
	public final IAdhocJoinSubOrCondition<MODEL, RESULT, Object, Object> on(CollectionFunction<Object, Object> joinCollection) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final IAdhocJoinSubOrCondition<MODEL, RESULT, Object, Object> compare(IFunctionInteger<Object> left, IFunctionInteger<Object> right) {
		addJoinCondition(new AdhocJoin.JoinConditionCompare(left, right));

		return this;
	}
	
	
	/**************************************************************************
	** IAdhocJoin
	**************************************************************************/
	@Override
	public <JOIN_TO> IAdhocWhereOrJoinSub<MODEL, RESULT, Object> innerJoin(Collection<JOIN_TO> joinTo, Consumer<IAdhocJoinSub<MODEL, RESULT, Object, JOIN_TO>> consumer) {

		query.compileInnerJoin(joinTo, consumer);
		
		return this;
	}

	@Override
	public <JOIN_TO> IAdhocWhereOrJoinSub<MODEL, RESULT, Object> leftJoin(Collection<JOIN_TO> joinTo, Consumer<IAdhocJoinSub<MODEL, RESULT, Object, JOIN_TO>> consumer) {

		query.compileLeftJoin(joinTo, consumer);

		return this;
	}
	

	/**************************************************************************
	** IAdhocWhere
	**************************************************************************/

	private void addWhere(Function<?, ?> function) {
		
		if (function == null) {
			throw new IllegalArgumentException("function == null");
		}

		final State newState;
		
		switch (state) {
		case INIT:
		
			if (this.whereGetter != null) {
				throw new IllegalStateException("where condition already set");
			}
	
			this.whereGetter = function;
	
			if (this.conditionsType != ConditionsType.NONE) {
				throw new IllegalStateException("Expected conditions type to not be set");
			}
			
			this.conditionsType = ConditionsType.SINGLE;
			newState = State.WHERE_GETTER_ADDED;
			break;
			
		default:
			throw new IllegalStateException("Unexpected state " + state);
		}
		
		setState(newState);
	}
	
	// Where-implementation - just adds condition straight to main query as already handles conditions from multiple select sources
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <E extends Enum<E>> ISharedCondition_Equality_Value<MODEL, RESULT, E, IAdhocLogical_And_Or<MODEL, RESULT, Object>> where(IFunctionEnum<Object, E> func) {

		addWhere(func);

		return (ISharedCondition_Equality_Value)this;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ISharedCondition_Comparable_Common_Value<MODEL, RESULT, Integer, IAdhocLogical_And_Or<MODEL, RESULT, Object>> where(IFunctionInteger<Object> func) {

		addWhere(func);

		return (ISharedCondition_Comparable_Common_Value)this;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ISharedCondition_Comparable_Common_Value<MODEL, RESULT, BigDecimal, IAdhocLogical_And_Or<MODEL, RESULT, Object>> where(IFunctionBigDecimal<Object> func) {

		addWhere(func);

		return (ISharedCondition_Comparable_Common_Value)this;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ISharedCondition_Comparable_String_Value<MODEL, RESULT, IAdhocLogical_And_Or<MODEL, RESULT, Object>> where(StringFunction<Object> func) {

		addWhere(func);

		return (ISharedCondition_Comparable_String_Value)this;
	}

	
	@Override
	public IAdhocFunctions_Initial<
			MODEL,
			RESULT,
			Object,
			IAdhocLogical_And_Or<MODEL, RESULT, Object>,
			ISharedCondition_Comparable_Common_Value<MODEL, RESULT, ? extends Comparable<?>, IAdhocLogical_And_Or<MODEL, RESULT, Object>>,
		    ISharedCondition_Comparable_String_Value<MODEL, RESULT, IAdhocLogical_And_Or<MODEL, RESULT, Object>>>
	
	
			where() {
		
		final AdhocFunctions<
				MODEL, RESULT,
				Object,
				IAdhocLogical_And_Or<MODEL, RESULT, Object>,
				ISharedCondition_Comparable_Common_Value<MODEL, RESULT, ? extends Comparable<?>, IAdhocLogical_And_Or<MODEL, RESULT, Object>>,
				ISharedCondition_Comparable_String_Value<MODEL, RESULT, IAdhocLogical_And_Or<MODEL, RESULT, Object>>>
		
		
		   functions = new AdhocFunctions<>(ConditionsType.SINGLE, this);
		
		
		return functions;
	}

	/**************************************************************************
	** ISharedClauseConditionValue
	**************************************************************************/


	final void addOperator(EClauseOperator operator, Object value) {
		

		if (operator == null) {
			throw new IllegalArgumentException("operator == null");
		}
		
		final State newState;
		
		switch (state) {

		case WHERE_GETTER_ADDED:
			if (this.whereOperator != null) {
				throw new IllegalStateException("where operator already set - should only be called once since additional AND or OR should be added to root conditions");
			}
			
			this.whereOperator = operator;
			this.whereValue = value;
			newState = State.WHERE_CLAUSE_ADDED;
			break;
			
		default:
			throw new IllegalStateException("Unexpected state " + state);
		}
		
		setState(newState);
	}
		

	private ISharedLogical_Base<MODEL, RESULT> addOperatorRet(EClauseOperator operator, Object value) {
		addOperator(operator, value);

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
	
	
	private AdhocConditions<MODEL, RESULT, ?> addConditionInt(ConditionsType conditionsType, AdhocFunctions<MODEL, RESULT, ?, ?, ?, ?> nextFunctions, Function<?, ?> getter) {
		
		if (this.conditionsType != ConditionsType.SINGLE) {
			throw new IllegalStateException("Expected single conditions type " + conditionsType);
		}

		this.conditionsType = conditionsType; 
		
		// Merge into query top level conditions now that we know merge type, those will collect the rest of the join conditions
		return query.mergeJoinComparison(whereFunctions, whereGetter, whereOperator, whereValue, rightSourceIdx, conditionsType, nextFunctions, getter);
	}

	private AdhocConditions<MODEL, RESULT, ?> addConditionCollectedFunctions(ConditionsType conditionsType, AdhocFunctions<MODEL, RESULT, ?, ?, ?, ?> nextFunctions, Function<?, ?> getter) {
		
		final State newState;
		
		final AdhocConditions<MODEL, RESULT, ?> ret;
		
		switch (state) {
		case COLLECT_FUNCTIONS:
			ret = addConditionInt(conditionsType, null, getter);
			newState = State.FUNCTIONS_COLLECTED;
			break;
			
		default:
			throw new IllegalStateException("Unexpected state " + state);
		}
		
		setState(newState);
		
		return ret;
	}

	private AdhocConditions<MODEL, RESULT, ?> addConditionNoFunctions(ConditionsType conditionsType, Function<?, ?> getter) {
		
		final State newState;
		
		final AdhocConditions<MODEL, RESULT, ?> ret;
		
		switch (state) {
		case WHERE_CLAUSE_ADDED:
			ret = addConditionInt(conditionsType, null, getter);
			newState = State.NO_FUNCTIONS;
			break;
			
		default:
			throw new IllegalStateException("Unexpected state " + state);
		}
		
		setState(newState);
		
		return ret;
	}
	
	/******************************** Or ********************************/
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	private <FIELD extends Comparable<?>> ISharedCondition_Comparable_Common_Value<MODEL, RESULT, FIELD, IAdhocLogical_And<MODEL, RESULT, Object>>  addAndClause(Function<?, FIELD> getter) {
		return (ISharedCondition_Comparable_Common_Value)addConditionNoFunctions(ConditionsType.AND, getter);
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	private <T extends Comparable<?>> ISharedCondition_Comparable_Common_Value<MODEL, RESULT, T, IAdhocLogical_Or<MODEL, RESULT, Object>>  addOrClause(Function<?, T> getter) {
		return (ISharedCondition_Comparable_Common_Value)addConditionNoFunctions(ConditionsType.OR, getter);
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
	public IAdhocFunctions_Initial<
		MODEL,
		RESULT,
		Object,
		IAdhocLogical_Or<MODEL, RESULT, Object>,
		ISharedCondition_Comparable_Common_Value<MODEL, RESULT, BigDecimal, IAdhocLogical_Or<MODEL, RESULT, Object>>,
		ISharedCondition_Comparable_String_Value<MODEL, RESULT, IAdhocLogical_Or<MODEL, RESULT, Object>>>
	
			or() {
		
		

		final AdhocFunctions<
				MODEL,
				RESULT,
				Object,
				IAdhocLogical_Or<MODEL, RESULT, Object>,
				ISharedCondition_Comparable_Common_Value<MODEL, RESULT, BigDecimal, IAdhocLogical_Or<MODEL, RESULT, Object>>,
				ISharedCondition_Comparable_String_Value<MODEL, RESULT, IAdhocLogical_Or<MODEL, RESULT, Object>>> functions;
		
		
		final State newState;
		
		switch (state) {
		case WHERE_CLAUSE_ADDED:
			functions = new AdhocFunctions<>(ConditionsType.OR, this);
			newState = State.COLLECT_FUNCTIONS;
			break;

		default:
			throw new IllegalStateException("Unexpected state " + state);
		}
		
		setState(newState);

		return functions;
	}


	@Override
	public IAdhocLogical_And<MODEL, RESULT, Object> orNest(Consumer<IAdhocLogical_And<MODEL, RESULT, Object>> andBuilder) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	@SuppressWarnings({"unchecked", "rawtypes"})
	public final ISharedCondition_Comparable_String_Value<MODEL, RESULT, IAdhocLogical_Or<MODEL, RESULT, Object>> or(StringFunction<Object> getter) {
		return (ISharedCondition_Comparable_String_Value)addConditionNoFunctions(ConditionsType.OR, getter);
	}


	/******************************** And ********************************/
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
	public final ISharedCondition_Comparable_String_Value<MODEL, RESULT, IAdhocLogical_And<MODEL, RESULT, Object>> and(StringFunction<Object> getter) {
		return (ISharedCondition_Comparable_String_Value)addConditionNoFunctions(ConditionsType.AND, getter);
	}

	@Override
	public IAdhocFunctions_Initial<
		MODEL,
		RESULT,
		Object,
		IAdhocLogical_And<MODEL, RESULT, Object>,
		ISharedCondition_Comparable_Common_Value<MODEL, RESULT, BigDecimal, IAdhocLogical_And<MODEL, RESULT, Object>>,
		ISharedCondition_Comparable_String_Value<MODEL, RESULT, IAdhocLogical_And<MODEL, RESULT, Object>>>
	
			and() {
		
		

		final AdhocFunctions<
				MODEL,
				RESULT,
				Object,
				IAdhocLogical_And<MODEL, RESULT, Object>,
				ISharedCondition_Comparable_Common_Value<MODEL, RESULT, BigDecimal, IAdhocLogical_And<MODEL, RESULT, Object>>,
				ISharedCondition_Comparable_String_Value<MODEL, RESULT, IAdhocLogical_And<MODEL, RESULT, Object>>> functions;
		
		
		final State newState;
		
		switch (state) {
		case WHERE_CLAUSE_ADDED:
			functions = new AdhocFunctions<>(ConditionsType.AND, this);
			newState = State.COLLECT_FUNCTIONS;
			break;

		default:
			throw new IllegalStateException("Unexpected state " + state);
		}
		
		setState(newState);

		return functions;
	}
	
	@Override
	public IAdhocLogical_And<MODEL, RESULT, Object> andNest(Consumer<IAdhocLogical_Or<MODEL, RESULT, Object>> orBuilder) {
		throw new UnsupportedOperationException("TODO");
	}

	/**************************************************************************
	** IAdhocFunctions_Callback
	**************************************************************************/

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Comparable<?>, ISharedLogical_Base<MODEL, RESULT>>
	
		onComparable(AdhocFunctions<MODEL, RESULT, ?, ?, ?, ?> functions, Function<?, ? extends Comparable<?>> getter) {

		addConditionCollectedFunctions(functions.getConditionsType(), functions, getter);
		
		return (ISharedCondition_Comparable_Common_Base)this;
	}

	@Override
	public ISharedCondition_Comparable_String_Base<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>> onString(
			AdhocFunctions<MODEL, RESULT, ?, ?, ?, ?> functions, StringFunction<?> getter) {

		addConditionCollectedFunctions(functions.getConditionsType(), functions, getter);
		
		return this;
	}
	
	
	
	/**************************************************************************
	** Helpers
	**************************************************************************/
	
	
	private void addJoinCondition(JoinCondition condition) {
		
		if (condition == null) {
			throw new IllegalArgumentException("condition == null");
		}

		if (numConditions == 0) {
			this.conditions = new JoinCondition[JOIN_CONDITIONS_INITIAL];
		}
		else if (numConditions == conditions.length) {
			this.conditions = Arrays.copyOf(conditions, conditions.length * 2);
		}
		
		this.conditions[numConditions ++] = condition;
	}

	static abstract class JoinCondition {

		abstract EJoinConditionType getJoinConditionType();
		
		abstract boolean evaluate(Object instance1, Object instance2, OneToManyJoinConditionResolver oneToManyResolver);
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	static class JoinConditionCompare extends JoinCondition {
		private final Function left;
		private final Function right;

		JoinConditionCompare(Function<?, ?> left, Function<?, ?> right) {

			if (left == null) {
				throw new IllegalArgumentException("left == null");
			}

			if (right == null) {
				throw new IllegalArgumentException("right == null");
			}

			this.left = left;
			this.right = right;
		}

		@Override
		boolean evaluate(Object instance1, Object instance2, OneToManyJoinConditionResolver oneToManyResolver) {
			
			final Object lhs = left.apply(instance1);
			final Object rhs = right.apply(instance2);
			
			return EvaluateUtil.evaluateComparables(lhs, rhs);
		}

		@Override
		EJoinConditionType getJoinConditionType() {
			return EJoinConditionType.COMPARISON;
		}
	}

	@Override
	@Deprecated
	public RESULT get() {
		// TODO: Remove this from base of IAdhocAndOrLogicalClauses => IAdhocAndClauses for joins so that do
		// not have to implement here (and is not callable in API)
		throw new UnsupportedOperationException("TODO - remove this");
	}
}
