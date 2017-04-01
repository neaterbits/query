package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

@Deprecated
abstract class Collector_And_Or_Alias<
			MODEL,
			RESULT,
			
			AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, AND_CLAUSES, NESTED_OR_CLAUSES>,
			OR_CLAUSES  extends ISharedLogical_Or_Alias_Base <MODEL, RESULT, OR_CLAUSES,  NESTED_AND_CLAUSES>,

			NESTED_AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES>,
			NESTED_OR_CLAUSES  extends ISharedLogical_Or_Alias_Base <MODEL, RESULT, NESTED_OR_CLAUSES,  NESTED_AND_CLAUSES>,			

			AFTER_GROUP_BY>


	extends Collector_And_Or_Named_And_Alias_Base<
			MODEL,
			RESULT,
			
			ISQLLogical_And_NoOp_Named<MODEL, RESULT>,
			ISQLLogical_Or_NoOp_Named<MODEL, RESULT>,
			ISQLLogical_And_NoOp_Named<MODEL, RESULT>,
			ISQLLogical_Or_NoOp_Named<MODEL, RESULT>,
			Void,
			
			AND_CLAUSES,
			OR_CLAUSES,
			NESTED_AND_CLAUSES,
			NESTED_OR_CLAUSES,
			AFTER_GROUP_BY,
			
			Void>
	
	implements ISharedLogical_And_Or_Alias<MODEL, RESULT, AND_CLAUSES, OR_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES>,
			ISharedLogical_And_Alias_Function<MODEL, RESULT, AND_CLAUSES, NESTED_OR_CLAUSES>,
			ISharedLogical_Or_Alias_Function<MODEL, RESULT, OR_CLAUSES, NESTED_AND_CLAUSES> {

    
	Collector_And_Or_Alias(Collector_Conditions_Initial<MODEL, RESULT, Void> last, EConditionsClause conditionsClause) {
		super(last, conditionsClause);
	}

	Collector_And_Or_Alias(Collector_Query<MODEL> queryCollector, Collector_Clause collector) {
		super(queryCollector, collector);
	}

	Collector_And_Or_Alias(Collector_GroupBy<MODEL, RESULT> last, EConditionsClause conditionsClause) {
		super(last, conditionsClause);
	}
	
	
	@Override
	public final ISharedFunctions_Transform_Initial_Alias<
		MODEL,
		RESULT,
		AND_CLAUSES,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short, AND_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, AND_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, AND_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, AND_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, AND_CLAUSES>,
		ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_CLAUSES>
	>
		and() {
			
		return andAlias();
	
	}

	
	@Override
	public final ISharedFunctions_Transform_Initial_Alias<
		MODEL,
		RESULT,
		OR_CLAUSES,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short, OR_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, OR_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, OR_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, OR_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, OR_CLAUSES>,
		ISharedCondition_Comparable_String_All<MODEL, RESULT, OR_CLAUSES>
	>
		or() {
			
		return orAlias();
	}
		

	@Override
	final Collector_GroupBy<MODEL, RESULT> createGroupByCollector(Collector_Base<MODEL> last, int[] groupByColumns,
			Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {
		return new Collector_GroupBy_Alias<>(last, groupByColumns, collectorConditions);
	}

	@Override
	final Collector_Or_Named<MODEL, RESULT, ISQLLogical_Or_NoOp_Named<MODEL, RESULT>, ISQLLogical_And_NoOp_Named<MODEL, RESULT>, ISQLLogical_Or_NoOp_Named<MODEL, RESULT>, Void> createNamedOrCollector() {
		throw new UnsupportedOperationException("This is alias");
	}

	@Override
	final Collector_And_Named<MODEL, RESULT, ISQLLogical_And_NoOp_Named<MODEL, RESULT>, ISQLLogical_And_NoOp_Named<MODEL, RESULT>, ISQLLogical_Or_NoOp_Named<MODEL, RESULT>, Void> createNamedAndCollector() {
		throw new UnsupportedOperationException("This is alias");
	}

	@Override
	final Collector_Or_Named<MODEL, RESULT, ISQLLogical_Or_NoOp_Named<MODEL, RESULT>, ISQLLogical_And_NoOp_Named<MODEL, RESULT>, ISQLLogical_Or_NoOp_Named<MODEL, RESULT>, Void> createNamedNestedOrCollector(
			Collector_And_Named<MODEL, RESULT, ISQLLogical_And_NoOp_Named<MODEL, RESULT>, ISQLLogical_And_NoOp_Named<MODEL, RESULT>, ISQLLogical_Or_NoOp_Named<MODEL, RESULT>, Void> andClauses) {
		throw new UnsupportedOperationException("This is alias");
	}

	@Override
	final Collector_And_Named<MODEL, RESULT, ISQLLogical_And_NoOp_Named<MODEL, RESULT>, ISQLLogical_And_NoOp_Named<MODEL, RESULT>, ISQLLogical_Or_NoOp_Named<MODEL, RESULT>, Void> createNamedNestedAndCollector(
			Collector_Or_Named<MODEL, RESULT, ISQLLogical_Or_NoOp_Named<MODEL, RESULT>, ISQLLogical_And_NoOp_Named<MODEL, RESULT>, ISQLLogical_Or_NoOp_Named<MODEL, RESULT>, Void> orClauses) {

		throw new UnsupportedOperationException("This is alias");
	}
}
	