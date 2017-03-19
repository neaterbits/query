package com.neaterbits.query.sql.dsl.api;

abstract class Short_Collector_SingleResult_JoinUndecided_Named_Base<

		MODEL, 
		RESULT,
		
		MAPPED_NAMED_WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_Named_Base<MODEL, RESULT>,
		// MAPPED_ALIAS_WHERE_OR_JOIN extends ISQLLogical_WhereOrJoin_Alias_Base<MODEL, RESULT>,
		
		/*
		MAPPED_SOURCE_NAMED extends ISharedSelectSourceBuilder<MODEL, RESULT>,
		MAPPED_SOURCE_ALIAS extends ISharedSelectSourceBuilder<MODEL, RESULT>,
		*/
		
		NAMED_AND_CLAUSES  extends ISharedLogical_And_Named_All <MODEL, RESULT, NAMED_AND_CLAUSES,  NAMED_NESTED_OR_CLAUSES>,
		NAMED_OR_CLAUSES  extends ISharedLogical_Or_Named_All <MODEL, RESULT, NAMED_OR_CLAUSES,  NAMED_NESTED_AND_CLAUSES>,
		
		NAMED_NESTED_AND_CLAUSES extends ISharedLogical_And_Named_All<MODEL, RESULT, NAMED_NESTED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES>,
		NAMED_NESTED_OR_CLAUSES  extends ISharedLogical_Or_Named_All<MODEL, RESULT, NAMED_NESTED_OR_CLAUSES,  NAMED_NESTED_AND_CLAUSES>,			
		
		NAMED_JOIN_CONDITION extends ISQLJoin_Condition_Named_Base<MODEL, RESULT, Object, Object, NAMED_JOIN_CONDITION>,
		
		NAMED_AND_OR extends ISharedLogical_And_Or_Named_All<
			MODEL,
			RESULT,
			NAMED_AND_CLAUSES,
			NAMED_OR_CLAUSES,
			NAMED_NESTED_AND_CLAUSES,
			NAMED_NESTED_OR_CLAUSES>,
		
		
		AFTER_GROUP_BY
		
		>

	extends Short_Collector_WhereOrJoin_Base<
	
			MODEL, RESULT,
			
			MAPPED_NAMED_WHERE_OR_JOIN,
			ISQLLogical_WhereOrJoin_Alias_Base<MODEL,RESULT>,
			

			NAMED_AND_CLAUSES,
			NAMED_OR_CLAUSES,
			
			NAMED_NESTED_AND_CLAUSES,
			NAMED_NESTED_OR_CLAUSES,
			
			NAMED_JOIN_CONDITION,
			NAMED_AND_OR,
			
			
			ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
			IShortJoin_Condition_SingleResult_Alias<MODEL, RESULT>,
			ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>,
			
			AFTER_GROUP_BY>
	
		{

	Short_Collector_SingleResult_JoinUndecided_Named_Base(Collector_Query<MODEL> queryCollector,
			Collector_Clause clauseCollector) {
		super(queryCollector, clauseCollector);
	}

	@Override
	Collector_Or_Named<MODEL, RESULT, NAMED_OR_CLAUSES, NAMED_NESTED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> createNamedOrCollector() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	Collector_And_Named<MODEL, RESULT, NAMED_AND_CLAUSES, NAMED_NESTED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> createNamedAndCollector() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	Collector_Or_Named<MODEL, RESULT, NAMED_NESTED_OR_CLAUSES, NAMED_NESTED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> createNamedNestedOrCollector(
			Collector_And_Named<MODEL, RESULT, NAMED_AND_CLAUSES, NAMED_NESTED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> andClauses) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	Collector_And_Named<MODEL, RESULT, NAMED_NESTED_AND_CLAUSES, NAMED_NESTED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> createNamedNestedAndCollector(
			Collector_Or_Named<MODEL, RESULT, NAMED_OR_CLAUSES, NAMED_NESTED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> orClauses) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	Collector_Or_Alias<MODEL, RESULT, ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>, ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>, ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>, ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>> createAliasOrCollector() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	Collector_And_Alias<MODEL, RESULT, ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>, ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>, ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>, ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>> createAliasAndCollector() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	Collector_Or_Alias<MODEL, RESULT, ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>, ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>, ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>, ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>> createAliasNestedOrCollector(
			Collector_And_Alias<MODEL, RESULT, ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>, ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>, ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>, ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>> andClauses) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	Collector_And_Alias<MODEL, RESULT, ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>, ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>, ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>, ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>> createAliasNestedAndCollector(
			Collector_Or_Alias<MODEL, RESULT, ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>, ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>, ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>, ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>> orClauses) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	Collector_GroupBy<MODEL, RESULT> createGroupByCollector(Collector_Base<MODEL> last, int[] groupByColumns,
			Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {
		// TODO Auto-generated method stub
		return null;
	}

	
		}
