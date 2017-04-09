package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

abstract class SQL_Collector_WhereOrJoin_Alias_Base<

				MODEL,
				RESULT,
				NAMED_JOIN_CONDITION extends ISQLJoin_Condition_Named_Base<MODEL, RESULT, Object, Object, NAMED_JOIN_CONDITION>,
				ALIAS_JOIN_CONDITION extends ISQLJoin_Condition_Alias_Base<MODEL, RESULT, ALIAS_JOIN_CONDITION>,
				
				AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, AND_CLAUSES, ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>>,
				OR_CLAUSES  extends ISharedLogical_Or_Alias_Base <MODEL, RESULT, OR_CLAUSES,  ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>>,

				AND_OR extends ISharedLogical_And_Or_Alias<
							MODEL,
							RESULT,
							AND_CLAUSES,
							OR_CLAUSES,
							ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
							ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>>,
							
							
				THIS_AFTER_GROUP_BY>

	extends SQL_Collector_WhereOrJoin_Base<
			MODEL,
			RESULT,

			
			ISQLLogical_And_NoOp_Named<MODEL, RESULT>,
			ISQLLogical_Or_NoOp_Named<MODEL, RESULT>,
			ISQLLogical_And_NoOp_Named<MODEL, RESULT>,
			ISQLLogical_Or_NoOp_Named<MODEL, RESULT>,
			NAMED_JOIN_CONDITION,
			ISharedLogical_And_Or_Named_All<
				MODEL,
				RESULT,
				ISQLLogical_And_NoOp_Named<MODEL, RESULT>,
				ISQLLogical_Or_NoOp_Named<MODEL, RESULT>,
				ISQLLogical_And_NoOp_Named<MODEL, RESULT>,
				ISQLLogical_Or_NoOp_Named<MODEL, RESULT>
				>,
			Void,
			

			AND_CLAUSES,
			OR_CLAUSES,
			// nested
			ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
			

			ALIAS_JOIN_CONDITION,
			AND_OR,
			ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>,
			
			
			THIS_AFTER_GROUP_BY
			>


	implements 
		   ISQLLogical_WhereOrJoin_Alias_Base<MODEL, RESULT>,
		   IShared_Join,
		   
		   // Needed by subclasses so implement here
		   ISharedLogical_And_Or_Alias<MODEL, RESULT,
		   		AND_CLAUSES,
		   		OR_CLAUSES,
		   		ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
		   		ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>>
		   		
		    {
			   
    
    /*

    abstract Classic_Collector_And_Alias<MODEL, RESULT, IClassicLogical_And_NonProcessResult_Alias<MODEL, RESULT>>
    				createNestedAnd(Classic_Collector_Or_Alias<MODEL, RESULT, OR_CLAUSES> orClauses); 
    
    
    abstract Classic_Collector_Or_Alias<MODEL, RESULT, IClassicLogical_Or_NonProcessResult_Alias<MODEL, RESULT>>
    				createNestedOr(Classic_Collector_And_Alias<MODEL, RESULT, AND_CLAUSES> andClauses);
	*/ 
			   
	SQL_Collector_WhereOrJoin_Alias_Base(Collector_Conditions_Initial<MODEL, RESULT, THIS_AFTER_GROUP_BY> last) {
		super(last, EConditionsClause.WHERE);
	}


	// JoinCondition, marks this as implemented in subclass by implementing matching interface there
	public final ISharedFunctions_Transform_Initial_Alias<
			MODEL, RESULT,
			AND_OR,
			
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short, AND_OR>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, AND_OR>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, AND_OR>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, AND_OR>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, AND_OR>,
			ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_OR>
	> 
	
			where() {
				
		return whereAlias();
	}

			
			
	@Override
	public final ISharedFunctions_Transform_Initial_Alias<
			MODEL,
			RESULT,
			OR_CLAUSES, 
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short, OR_CLAUSES>, ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, OR_CLAUSES>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, OR_CLAUSES>, ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, OR_CLAUSES>, 
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, OR_CLAUSES>, ISharedCondition_Comparable_String_All<MODEL, RESULT, OR_CLAUSES>
			> or() {
		return orAlias();
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
			> and() {
		return andAlias();
	}


	@Override
	final Collector_Or_Alias<
			MODEL,
			RESULT,
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
			
			ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>>
	
		createAliasNestedOrCollector(
			Collector_And_Alias<
				MODEL,
				RESULT,
				AND_CLAUSES,
				ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
				ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
					
				ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>> andClauses) {
		
		return new SQL_Collector_Or_NonProcessResult_Alias<>(andClauses);
	}

	@Override
	final Collector_And_Alias<
			MODEL,
			RESULT,
			ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
			ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>>
	
		createAliasNestedAndCollector(
			Collector_Or_Alias<
				MODEL,
				RESULT,
				OR_CLAUSES,
				ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
				ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
				ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>> orClauses) {

		return new SQL_Collector_And_NonProcessResult_Alias<>(orClauses);
	}

	@Override
	final Collector_GroupBy<MODEL, RESULT> createGroupByCollector(Collector_Base<MODEL> last, int[] groupByColumns,
			Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {
		return new Collector_GroupBy_Alias<>(last, groupByColumns, collectorConditions);
	}

	@Override
	final Collector_Or_Named<
			MODEL,
			RESULT,
			ISQLLogical_Or_NoOp_Named<MODEL, RESULT>,
			ISQLLogical_And_NoOp_Named<MODEL, RESULT>,
			ISQLLogical_Or_NoOp_Named<MODEL, RESULT>,
			Void>
	
		createNamedOrCollector() {
		
		throw new UnsupportedOperationException("Not named");
	}


	@Override
	final Collector_And_Named<
			MODEL,
			RESULT,
			ISQLLogical_And_NoOp_Named<MODEL, RESULT>,
			ISQLLogical_And_NoOp_Named<MODEL, RESULT>,
			ISQLLogical_Or_NoOp_Named<MODEL, RESULT>,
			Void>
	
		createNamedAndCollector() {
		
		throw new UnsupportedOperationException("Not named");
	}


	@Override
	final Collector_Or_Named<
			MODEL,
			RESULT,
			ISQLLogical_Or_NoOp_Named<MODEL, RESULT>,
			ISQLLogical_And_NoOp_Named<MODEL, RESULT>,
			ISQLLogical_Or_NoOp_Named<MODEL, RESULT>,
			Void>
	
		createNamedNestedOrCollector(
					
		Collector_And_Named<
			MODEL,
			RESULT, 
			ISQLLogical_And_NoOp_Named<MODEL, RESULT>,
			ISQLLogical_And_NoOp_Named<MODEL, RESULT>,
			ISQLLogical_Or_NoOp_Named<MODEL, RESULT>,
			Void> andClauses) {
		
		throw new UnsupportedOperationException("Not named");
	}


	@Override
	final Collector_And_Named<
			MODEL,
			RESULT,
			ISQLLogical_And_NoOp_Named<MODEL, RESULT>,
			ISQLLogical_And_NoOp_Named<MODEL, RESULT>, 
			ISQLLogical_Or_NoOp_Named<MODEL, RESULT>,
			Void>

		createNamedNestedAndCollector(
			Collector_Or_Named<
				MODEL,
				RESULT,
				ISQLLogical_Or_NoOp_Named<MODEL, RESULT>,
				ISQLLogical_And_NoOp_Named<MODEL, RESULT>,
				ISQLLogical_Or_NoOp_Named<MODEL, RESULT>,
				Void> orClauses) {
		
		throw new UnsupportedOperationException("Not named");
	}
}

