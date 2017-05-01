package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

final class Condition_ExpressionList_Comparable_Named< 
		MODEL,
		RESULT,
		R extends Comparable<R>,
		
		RET extends ISharedLogical_Base<MODEL, RESULT>>

	extends Conditions_ExpressionList_Base<
		MODEL,
		RESULT,
		R,
		
		RET,
		
		RET,
		ISharedLogical_Base<MODEL, RESULT>, // Alias
		
		ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, R, RET>,
		ISharedCondition_OpsAndComp_String_Named<MODEL, RESULT, RET>, // String
		
		ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Long, RET>,
		ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Long, RET>,
		ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Integer, RET>,
		
		ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Byte, RET>,
		ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Short, RET>,
		ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Integer, RET>,
		ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Long, RET>,
		ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, BigInteger, RET>,
		ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Float, RET>,
		ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Double, RET>,
		ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, BigDecimal, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, java.util.Date, RET>,
		ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, java.util.Calendar, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		
	
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
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>
	
	>

	implements 
		ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, R, RET>
		

	{
	
	private final Collector_Conditions_GroupBy<MODEL, RESULT, ?> clause;
	private Collector_Condition_Comparative<MODEL, RESULT, ?, ?> comparableConditions;

	
	//private final IMappingCollector<MODEL, RESULT> impl;

	

	// 	TODO go over constructor calls and use static utility methods? 
	Condition_ExpressionList_Comparable_Named(Collector_Conditions_GroupBy<MODEL, RESULT, ?> clause, Expression expression /*, IMappingCollector<MODEL, RESULT> impl */) {
		super(expression /*, impl */, EFieldAccessType.NAMED);

		
		this.clause = clause;
	
		/*
		if (impl == null) {
			throw new IllegalArgumentException("impl == null");
		}
		
		this.impl = impl;
		*/
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	final ISharedComparison_Comparable_Common_All<MODEL, RESULT, R, RET> assureNamedComparable() {

		if (this.comparableConditions != null) {
			throw new IllegalStateException("Condition already set");
		}
		
		this.comparableConditions = new Collector_Condition_Comparative<>(clause, collectExpressionListOrOne());

		return (ISharedComparison_Comparable_Common_All)comparableConditions;
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
