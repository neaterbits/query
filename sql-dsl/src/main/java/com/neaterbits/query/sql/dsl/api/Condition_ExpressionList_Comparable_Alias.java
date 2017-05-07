package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

final class Condition_ExpressionList_Comparable_Alias< 
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
		
		ISharedLogical_Base<MODEL, RESULT>,
		RET,
		ISharedLogical_Base<MODEL, RESULT>,
	
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedFunction_Next<MODEL, RESULT, RET>,
		ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, R, RET>,
		ISharedCondition_OpsAndComp_String_Alias<MODEL, RESULT, RET>,
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
	
	implements ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, R, RET> {
	
	private Collector_Condition_Comparative<MODEL, RESULT, ?, ?> comparableConditions;
	
	Condition_ExpressionList_Comparable_Alias(Collector_Conditions_GroupBy<MODEL, RESULT, ?> clause, Expression expression) {
		super(clause, expression, EFieldAccessType.NAMED);
	}
	
	
	@Override
	ISharedComparison_Comparable_Common_All<MODEL, RESULT, R, RET> assureComparable() {
		return assureAliasComparable();
	}


	@SuppressWarnings({"unchecked", "rawtypes"})
	final ISharedComparison_Comparable_Common_All<MODEL, RESULT, R, RET> assureAliasComparable() {
	
		if (this.comparableConditions != null) {
			throw new IllegalStateException("Condition already set");
		}

		this.comparableConditions = new Collector_Condition_Comparative<>(getRetClause(), collectExpressionListOrOne());

		return (ISharedComparison_Comparable_Common_All) comparableConditions;
	}
}