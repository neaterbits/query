package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

final class Condition_ExpressionList_Comparable_Alias< 
	MODEL,
	RESULT,
	R extends Comparable<R>,
	
	RET extends ISharedLogical_Base<MODEL, RESULT>>
	
	extends Conditions_ExpressionList_Base<
	MODEL,
	RESULT,
	R,
	
	RET,
	
	ISharedLogical_Base<MODEL, RESULT>,
	RET,

	ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, R, RET>,
	ISharedCondition_OpsAndComp_String_Alias<MODEL, RESULT, RET>,
	
	ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
	ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
	ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
	
	ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
	ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
	ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
	ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
	ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
	ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
	ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
	ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
	ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
	ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
	ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
	ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
	ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
	ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>,
	
	
	ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, Long, RET>,
	ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, Long, RET>,
	ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, Integer, RET>,
	
	ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, Byte, RET>,
	ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, Short, RET>,
	ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, Integer, RET>,
	ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, Long, RET>,
	ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, BigInteger, RET>,
	ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, Float, RET>,
	ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, Double, RET>,
	ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, BigDecimal, RET>,
	ISharedFunction_Next<MODEL, RESULT, RET>,
	ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, java.util.Date, RET>,
	ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, java.util.Calendar, RET>,
	ISharedFunction_Next<MODEL, RESULT, RET>,
	ISharedFunction_Next<MODEL, RESULT, RET>,
	ISharedFunction_Next<MODEL, RESULT, RET>
	
	
	>
	
	implements ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, R, RET>
	
	
	{
	
	private Collector_Condition_Comparative<MODEL, RESULT, ?, ?> comparableConditions;
	
	Condition_ExpressionList_Comparable_Alias(Collector_Conditions_GroupBy<MODEL, RESULT, ?> clause, Expression expression) {
		super(clause, expression, EFieldAccessType.NAMED);
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	final ISharedComparison_Comparable_Common_All<MODEL, RESULT, R, RET> assureNamedComparable() {
	
		if (this.comparableConditions != null) {
			throw new IllegalStateException("Condition already set");
		}

		this.comparableConditions = new Collector_Condition_Comparative<>(getRetClause(), collectExpressionListOrOne());

		return (ISharedComparison_Comparable_Common_All) comparableConditions;
	}
	
	
	@Override
	public RET isEqualTo(R other) {
		return assureNamedComparable().isEqualTo(other);
	}

	@Override
	public RET isNotEqualTo(R other) {
		return assureNamedComparable().isNotEqualTo(other);
	}

	@Override
	public RET in(R... values) {
		return assureNamedComparable().in(values);
	}

	@Override
	public RET isGreaterThan(ValParam<R> value) {
		return assureNamedComparable().isGreaterThan(value);
	}

	@Override
	public RET isGreaterOrEqualTo(ValParam<R> value) {
		return assureNamedComparable().isGreaterOrEqualTo(value);
	}

	@Override
	public RET isLessThan(ValParam<R> value) {
		return assureNamedComparable().isLessThan(value);
	}

	@Override
	public RET isLessOrEqualTo(ValParam<R> value) {
		return assureNamedComparable().isLessOrEqualTo(value);
	}

	@Override
	public RET isEqualTo(ValParam<R> other) {
		return assureNamedComparable().isEqualTo(other);
	}

	@Override
	public RET isNotEqualTo(ValParam<R> other) {
		return assureNamedComparable().isNotEqualTo(other);
	}

	@Override
	public RET in(InParam<R> param) {
		return assureNamedComparable().in(param);
	}

	@Override
	public RET isGreaterThan(R value) {
		return assureNamedComparable().isGreaterThan(value);
	}

	@Override
	public RET isGreaterOrEqualTo(R value) {
		return assureNamedComparable().isGreaterOrEqualTo(value);
	}

	@Override
	public RET isLessThan(R value) {
		return assureNamedComparable().isLessThan(value);
	}

	@Override
	public RET isLessOrEqualTo(R value) {
		return assureNamedComparable().isLessOrEqualTo(value);
	}
}