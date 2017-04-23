package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

abstract class SQL_Collector_WhereOrJoin_Named_Base<

			MODEL,
			RESULT,
			NAMED_JOIN_CONDITION extends ISQLJoin_Condition_Named_Base<MODEL, RESULT, Object, Object, NAMED_JOIN_CONDITION>,
			
			ALIAS_JOIN_CONDITION extends ISQLJoin_Condition_Alias_Base<MODEL, RESULT, ALIAS_JOIN_CONDITION>,
			
			AND_CLAUSES extends ISharedLogical_And_Named_All<MODEL, RESULT, AND_CLAUSES, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>,
			OR_CLAUSES  extends ISharedLogical_Or_Named_All<MODEL, RESULT, OR_CLAUSES,  ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>,
			
			

			// AND_OR extends ISharedLogical_Base<MODEL, RESULT>
			
			AND_OR extends ISharedLogical_And_Or_Named_All<
							MODEL,
							RESULT,
							AND_CLAUSES,
							OR_CLAUSES,
							ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
							ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>>
			>

	extends SQL_Collector_WhereOrJoin_Base<
				MODEL,
				RESULT,
				
				AND_CLAUSES,
				OR_CLAUSES,
				ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
				ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
				
				NAMED_JOIN_CONDITION,
				AND_OR,
				ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>,
				
				ISQLLogical_And_NoOp_Alias<MODEL, RESULT>,
				ISQLLogical_Or_NoOp_Alias<MODEL, RESULT>,
				ISQLLogical_And_NoOp_Alias<MODEL, RESULT>,
				ISQLLogical_Or_NoOp_Alias<MODEL, RESULT>,
				ALIAS_JOIN_CONDITION,
				ISharedLogical_And_Or_Alias<
					MODEL,
					RESULT,
					ISQLLogical_And_NoOp_Alias<MODEL, RESULT>,
					ISQLLogical_Or_NoOp_Alias<MODEL, RESULT>,
					ISQLLogical_And_NoOp_Alias<MODEL, RESULT>,
					ISQLLogical_Or_NoOp_Alias<MODEL, RESULT>
					>,
				Void,

				
				
				ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>>
	implements 
	
				ISQLLogical_WhereOrJoin_Named_Base<MODEL, RESULT>,
				ISQLJoin_Condition_Named_Base<MODEL, RESULT, Object, Object, NAMED_JOIN_CONDITION> {
    
					

	SQL_Collector_WhereOrJoin_Named_Base(Collector_Conditions_Initial<MODEL, RESULT, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> last) {
		super(last);
	}

	
	// implemented in subclass @Override
	public final ISharedFunctions_Transform_Initial_Named<
			MODEL, RESULT,
			AND_OR,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Integer, AND_OR>,
			
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Byte, AND_OR>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Short, AND_OR>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Integer, AND_OR>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Long, AND_OR>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, BigInteger, AND_OR>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Float, AND_OR>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, Double, AND_OR>,
			ISharedCondition_Comparable_Common_All_Compilable<MODEL, RESULT, BigDecimal, AND_OR>,
			ISharedCondition_Comparable_String_All_Compilable<MODEL, RESULT, AND_OR>> 
	
			where() {
				
		return whereNamed();
	}

	// nested-instantiation
			
	@Override
	final Collector_Or_Named<
				MODEL,
				RESULT, 
				ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
				ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
				ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
				ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>
			> 
		createNamedNestedOrCollector(
				Collector_And_Named<
					MODEL,
					RESULT,
					AND_CLAUSES,
					ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
					ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
					ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> andClauses) {

		return new SQL_Collector_Or_NonProcessResult_Named<>(andClauses, null);
	}

	@Override
	final Collector_And_Named<MODEL, RESULT, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>>


		createNamedNestedAndCollector(
			Collector_Or_Named<MODEL, RESULT, OR_CLAUSES, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> orClauses) {
		return new SQL_Collector_And_NonProcessResult_Named<>(orClauses);
	}


	@Override
	final Collector_Or_Alias<MODEL, RESULT, ISQLLogical_Or_NoOp_Alias<MODEL, RESULT>, ISQLLogical_And_NoOp_Alias<MODEL, RESULT>, ISQLLogical_Or_NoOp_Alias<MODEL, RESULT>, Void> createAliasOrCollector() {
		throw new UnsupportedOperationException("Not alias");
	}


	@Override
	final Collector_And_Alias<MODEL, RESULT, ISQLLogical_And_NoOp_Alias<MODEL, RESULT>, ISQLLogical_And_NoOp_Alias<MODEL, RESULT>, ISQLLogical_Or_NoOp_Alias<MODEL, RESULT>, Void> createAliasAndCollector() {
		throw new UnsupportedOperationException("Not alias");
	}


	@Override
	final Collector_Or_Alias<MODEL, RESULT, ISQLLogical_Or_NoOp_Alias<MODEL, RESULT>, ISQLLogical_And_NoOp_Alias<MODEL, RESULT>, ISQLLogical_Or_NoOp_Alias<MODEL, RESULT>, Void> createAliasNestedOrCollector(
			Collector_And_Alias<MODEL, RESULT, ISQLLogical_And_NoOp_Alias<MODEL, RESULT>, ISQLLogical_And_NoOp_Alias<MODEL, RESULT>, ISQLLogical_Or_NoOp_Alias<MODEL, RESULT>, Void> andClauses) {
		
		throw new UnsupportedOperationException("Not alias");
	}


	@Override
	final Collector_And_Alias<MODEL, RESULT, ISQLLogical_And_NoOp_Alias<MODEL, RESULT>, ISQLLogical_And_NoOp_Alias<MODEL, RESULT>, ISQLLogical_Or_NoOp_Alias<MODEL, RESULT>, Void> createAliasNestedAndCollector(
			Collector_Or_Alias<MODEL, RESULT, ISQLLogical_Or_NoOp_Alias<MODEL, RESULT>, ISQLLogical_And_NoOp_Alias<MODEL, RESULT>, ISQLLogical_Or_NoOp_Alias<MODEL, RESULT>, Void> orClauses) {
		throw new UnsupportedOperationException("Not alias");
	}


	@Override
	final Collector_GroupBy<MODEL, RESULT> createGroupByCollector(Collector_Base<MODEL> last, int[] groupByColumns,
			Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {
		return new Collector_GroupBy_Named<>(last, groupByColumns, collectorConditions);
	}
}

