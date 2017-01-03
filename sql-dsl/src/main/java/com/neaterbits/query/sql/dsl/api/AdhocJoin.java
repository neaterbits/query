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

		ISharedClauseComparableCommonValue<MODEL, RESULT, Comparable<Object>, ISharedLogicalClauses<MODEL, RESULT>>,
		ISharedClauseComparableStringValue<MODEL, RESULT, ISharedLogicalClauses<MODEL, RESULT>>,
		
		ISharedLogicalClauses<MODEL, RESULT>,
		IAdhocAndOrLogicalClauses<MODEL, RESULT> {

	
	private static final int JOIN_CONDITIONS_INITIAL = 10;
	
	final AdhocQueryNamed<MODEL, RESULT> query;
	final EJoinType type;
	final int leftSourceIdx;
	final int rightSourceIdx;

	JoinCondition [] conditions;
	int numConditions;

	ConditionsType conditionsType;
	
	
	// Cache initial where-clause until we know whether this is
	@SuppressWarnings("rawtypes")
	Function whereCondition;
	EClauseOperator whereOperator;
	Object whereValue;

	
	AdhocJoin(AdhocQueryNamed<MODEL, RESULT> query, EJoinType type, int leftSourceIdx, int rightSourceIdx) {

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
	}

	@Override
	public final IAdhocJoinSubOrCondition<MODEL, RESULT, Object, Object> on(CollectionFunction<Object, Object> joinCollection) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final IAdhocJoinSubOrCondition<MODEL, RESULT, Object, Object> compare(IFunctionInteger<Object> left, IFunctionInteger<Object> right) {
		addJoinCondition(new AdhocJoin.JoinConditionCompare(left, right));

		return this;
	}
	
	
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

		if (this.whereCondition != null) {
			throw new IllegalStateException("where condition already set");
		}

		this.whereCondition = function;

		if (this.conditionsType != ConditionsType.NONE) {
			throw new IllegalStateException("Expected conditions type to not be set");
		}
		
		this.conditionsType = ConditionsType.SINGLE;
	}
	
	// Where-implementation - just adds condition straight to main query as already handles conditions from multiple select sources
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <E extends Enum<E>> ISharedClauseConditionValue<MODEL, RESULT, E, IAdhocAndOrLogicalClauses<MODEL, RESULT>> where(IFunctionEnum<Object, E> func) {

		addWhere(func);

		return (ISharedClauseConditionValue)this;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ISharedClauseComparableCommonValue<MODEL, RESULT, Integer, IAdhocAndOrLogicalClauses<MODEL, RESULT>> where(IFunctionInteger<Object> func) {

		addWhere(func);

		return (ISharedClauseComparableCommonValue)this;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ISharedClauseComparableCommonValue<MODEL, RESULT, BigDecimal, IAdhocAndOrLogicalClauses<MODEL, RESULT>> where(IFunctionBigDecimal<Object> func) {

		addWhere(func);

		return (ISharedClauseComparableCommonValue)this;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ISharedClauseComparableStringValue<MODEL, RESULT, IAdhocAndOrLogicalClauses<MODEL, RESULT>> where(StringFunction<Object> func) {

		addWhere(func);

		return (ISharedClauseComparableStringValue)this;
	}

	
	/**************************************************************************
	** ISharedClauseConditionValue
	**************************************************************************/


	final void addOperator(EClauseOperator operator, Object value) {
		

		if (operator == null) {
			throw new IllegalArgumentException("operator == null");
		}

		
		if (this.whereOperator != null) {
			throw new IllegalStateException("where operator already set");
		}
		
		this.whereOperator = operator;
		this.whereValue = value;
	}
		

	private ISharedLogicalClauses<MODEL, RESULT> addOperatorRet(EClauseOperator operator, Object value) {
		addOperator(operator, value);

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
	
	
	private AdhocConditions<MODEL, RESULT, ?> addConditionInt(ConditionsType conditionsType, Function<?, ?> getter) {
		
		if (this.conditionsType != ConditionsType.SINGLE) {
			throw new IllegalStateException("Expected single conditions type " + conditionsType);
		}

		this.conditionsType = conditionsType; 
		
		// Merge into query conditions now that we know merge type, those will collect the rest of the join conditions
		return query.mergeJoinComparison(whereCondition, whereOperator, whereValue, rightSourceIdx, conditionsType, getter);
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	private <T extends Comparable<?>> ISharedClauseComparableCommonValue<MODEL, RESULT, T, IAdhocAndClauses<MODEL, RESULT>>  addAndClause(Function<?, T> getter) {
		addConditionInt(ConditionsType.AND, getter);
		
		return (ISharedClauseComparableCommonValue)this;
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	private <T extends Comparable<?>> ISharedClauseComparableCommonValue<MODEL, RESULT, T, IAdhocOrClauses<MODEL, RESULT>>  addOrClause(Function<?, T> getter) {
		addConditionInt(ConditionsType.OR, getter);
		
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
		addConditionInt(ConditionsType.OR, getter);

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
		addConditionInt(ConditionsType.AND, getter);

		return (ISharedClauseComparableStringValue)this;
	}
	
	
	void addJoinCondition(JoinCondition condition) {
		
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
}
