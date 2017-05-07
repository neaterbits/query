package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

final class Condition_ExpressionList_Comparable_Named< 
		MODEL,
		RESULT,
		R extends Comparable<R>,
		
		RET extends ISharedLogical_Base<MODEL, RESULT>>

	extends Conditions_ExpressionList_Comparable_Base<
		MODEL,
		RESULT,
		R,
		
		RET, ISharedComparison_Comparable_Common_All<MODEL, RESULT, R, RET>,
		
		RET,
		
		RET,
		ISharedLogical_Base<MODEL, RESULT>, // Alias
		ISharedLogical_Base<MODEL, RESULT>,
		
		ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, R, RET>,
		ISharedCondition_OpsAndComp_String_Named<MODEL, RESULT, RET>, // String
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, R, RET>,
		ISharedCondition_OpsAndComp_String_Named<MODEL, RESULT, RET>,
		
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
		ISharedFunction_Next<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>>
	>

	implements 
		ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, R, RET> {
	
	private Collector_Condition_Comparative<MODEL, RESULT, ?, ?> comparableConditions;

	
	Condition_ExpressionList_Comparable_Named(Collector_Conditions_GroupBy<MODEL, RESULT, ?> clause, Expression expression /*, IMappingCollector<MODEL, RESULT> impl */) {
		super(clause, expression, EFieldAccessType.NAMED);
	}
	
	@Override
	ISharedComparison_Comparable_Common_All<MODEL, RESULT, R, RET> assureComparable() {
		return assureNamedComparable();
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	final ISharedComparison_Comparable_Common_All<MODEL, RESULT, R, RET> assureNamedComparable() {

		if (this.comparableConditions != null) {
			throw new IllegalStateException("Condition already set");
		}
		
		this.comparableConditions = new Collector_Condition_Comparative<>(getRetClause(), collectExpressionListOrOne());

		return (ISharedComparison_Comparable_Common_All)comparableConditions;
	}
}
