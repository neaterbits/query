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
		IAdhocWhereOrJoinSub<MODEL, RESULT, Object>
	
{

	
	private static final int JOIN_CONDITIONS_INITIAL = 10;
	
	final AdhocQueryClass<MODEL, RESULT> query;
	final EJoinType type;
	final int leftSourceIdx;
	final int rightSourceIdx;

	JoinCondition [] conditions;
	int numConditions;

	AdhocJoin(AdhocQueryClass<MODEL, RESULT> query, EJoinType type, int leftSourceIdx, int rightSourceIdx) {

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
	}
	
	@Override
	public final IAdhocJoinSubOrCondition<MODEL, RESULT, Object, Object> on(CollectionFunction<Object, Object> joinCollection) {
		
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final IAdhocJoinSubOrCondition<MODEL, RESULT, Object, Object> compare(IFunctionInteger<Object> left, IFunctionInteger<Object> right) {
		addCondition(new AdhocJoin.JoinConditionCompare(left, right));

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

	
	// Where-implementation - just adds condition straight to main query as already handles conditions from multiple select sources
	@Override
	public <E extends Enum<E>> ISharedClauseConditionValue<MODEL, RESULT, E, IAdhocAndOrLogicalClauses<MODEL, RESULT>> where(IFunctionEnum<Object, E> func) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public ISharedClauseComparableCommonValue<MODEL, RESULT, Integer, IAdhocAndOrLogicalClauses<MODEL, RESULT>> where(IFunctionInteger<Object> func) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public ISharedClauseComparableCommonValue<MODEL, RESULT, BigDecimal, IAdhocAndOrLogicalClauses<MODEL, RESULT>> where(IFunctionBigDecimal<Object> func) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public ISharedClauseComparableStringValue<MODEL, RESULT, IAdhocAndOrLogicalClauses<MODEL, RESULT>> where(StringFunction<Object> func) {
		throw new UnsupportedOperationException("TODO");
	}

	void addCondition(JoinCondition condition) {
		
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
	}
}
