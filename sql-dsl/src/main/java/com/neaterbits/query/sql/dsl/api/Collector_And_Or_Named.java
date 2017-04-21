package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

@Deprecated
abstract class Collector_And_Or_Named<
			MODEL,
			RESULT,
			AND_CLAUSES extends ISharedLogical_And_Named_All<MODEL, RESULT, AND_CLAUSES, NESTED_OR_CLAUSES>,
			OR_CLAUSES  extends ISharedLogical_Or_Named_All <MODEL, RESULT, OR_CLAUSES,  NESTED_AND_CLAUSES>,

			NESTED_AND_CLAUSES extends ISharedLogical_And_Named_All<MODEL, RESULT, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES>,
			NESTED_OR_CLAUSES  extends ISharedLogical_Or_Named_All<MODEL, RESULT, NESTED_OR_CLAUSES,  NESTED_AND_CLAUSES>,
			
			AFTER_GROUP_BY> 
			
	extends Collector_And_Or_Named_And_Alias_Base<
		MODEL,
		RESULT,
		
		AND_CLAUSES,
		OR_CLAUSES,
		NESTED_AND_CLAUSES,
		NESTED_OR_CLAUSES,
		AFTER_GROUP_BY,

		// just no-ops for the alias-types
		ISQLLogical_And_NoOp_Alias<MODEL, RESULT>,
		ISQLLogical_Or_NoOp_Alias<MODEL, RESULT>,
		ISQLLogical_And_NoOp_Alias<MODEL, RESULT>,
		ISQLLogical_Or_NoOp_Alias<MODEL, RESULT>,
		Void,

		Void>
		
	implements
	
	
		ISharedLogical_And_Or_Named_All<
			MODEL,
			RESULT,
			AND_CLAUSES,
			OR_CLAUSES,
			NESTED_AND_CLAUSES,
			NESTED_OR_CLAUSES>,
			
		ISharedLogical_And_Named_Function<
			MODEL,
			RESULT,
			AND_CLAUSES,
			NESTED_OR_CLAUSES/*,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, AND_CLAUSES>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, AND_CLAUSES>,
			ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_CLAUSES> */>,
			
			
		ISharedLogical_Or_Named_Function<
			MODEL,
			RESULT,
			OR_CLAUSES,
			NESTED_AND_CLAUSES /*,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, OR_CLAUSES>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, OR_CLAUSES>,
			ISharedCondition_Comparable_String_All<MODEL, RESULT, OR_CLAUSES> */>{

    
	Collector_And_Or_Named(Collector_Conditions_Initial<MODEL, RESULT, Void> last, EConditionsClause conditionsClause) {
		super(last, conditionsClause);
	}

	Collector_And_Or_Named(Collector_GroupBy<MODEL, RESULT> last, EConditionsClause conditionsClause) {
		super(last, conditionsClause);
	}

	Collector_And_Or_Named(Collector_Query<MODEL> queryCollector, ICollectorClause collector) {
		super(queryCollector, collector);
	}

	
	@Override
	public final ISharedFunctions_Transform_Initial_Named<
		MODEL,
		RESULT,
		AND_CLAUSES,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Byte, AND_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short, AND_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, AND_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, AND_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigInteger, AND_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Float, AND_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, AND_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, AND_CLAUSES>,
		ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_CLAUSES>
	>
		and() {
			
		return andNamed();
	}


	@Override
	public final ISharedFunctions_Transform_Initial_Named<
		MODEL,
		RESULT,
		OR_CLAUSES,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Byte, OR_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short, OR_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, OR_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, OR_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigInteger, OR_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Float, OR_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, OR_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, OR_CLAUSES>,
		ISharedCondition_Comparable_String_All<MODEL, RESULT, OR_CLAUSES>
	>
		or() {
			
			return orNamed();
	}

	@Override
	final Collector_GroupBy<MODEL, RESULT> createGroupByCollector(Collector_Base<MODEL> last, int[] groupByColumns,
			Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {
		return new Collector_GroupBy_Named<>(last, groupByColumns, collectorConditions);
	}


	@Override
	final Collector_Or_Alias<MODEL, RESULT, ISQLLogical_Or_NoOp_Alias<MODEL, RESULT>, ISQLLogical_And_NoOp_Alias<MODEL, RESULT>, ISQLLogical_Or_NoOp_Alias<MODEL, RESULT>, Void> createAliasOrCollector() {
		throw new UnsupportedOperationException("This is named");
	}

	@Override
	final Collector_And_Alias<MODEL, RESULT, ISQLLogical_And_NoOp_Alias<MODEL, RESULT>, ISQLLogical_And_NoOp_Alias<MODEL, RESULT>, ISQLLogical_Or_NoOp_Alias<MODEL, RESULT>, Void> createAliasAndCollector() {
		throw new UnsupportedOperationException("This is named");
	}

	@Override
	final Collector_Or_Alias<MODEL, RESULT, ISQLLogical_Or_NoOp_Alias<MODEL, RESULT>, ISQLLogical_And_NoOp_Alias<MODEL, RESULT>, ISQLLogical_Or_NoOp_Alias<MODEL, RESULT>, Void> createAliasNestedOrCollector(
			Collector_And_Alias<MODEL, RESULT, ISQLLogical_And_NoOp_Alias<MODEL, RESULT>, ISQLLogical_And_NoOp_Alias<MODEL, RESULT>, ISQLLogical_Or_NoOp_Alias<MODEL, RESULT>, Void> andClauses) {
		throw new UnsupportedOperationException("This is named");
	}

	@Override
	final Collector_And_Alias<MODEL, RESULT, ISQLLogical_And_NoOp_Alias<MODEL, RESULT>, ISQLLogical_And_NoOp_Alias<MODEL, RESULT>, ISQLLogical_Or_NoOp_Alias<MODEL, RESULT>, Void> createAliasNestedAndCollector(
			Collector_Or_Alias<MODEL, RESULT, ISQLLogical_Or_NoOp_Alias<MODEL, RESULT>, ISQLLogical_And_NoOp_Alias<MODEL, RESULT>, ISQLLogical_Or_NoOp_Alias<MODEL, RESULT>, Void> orClauses) {
		throw new UnsupportedOperationException("This is named");
	}
}
