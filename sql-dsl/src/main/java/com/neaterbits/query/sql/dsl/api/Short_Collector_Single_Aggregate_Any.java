package com.neaterbits.query.sql.dsl.api;

abstract class Short_Collector_Single_Aggregate_Any<MODEL, RESULT>
		extends Short_Collector_WhereOrJoin_Base<
			MODEL,
			RESULT,
			
			/*
			IShortLogical_WhereOrJoin_SingleResult_Entity_Named<MODEL, RESULT, RESULT>,
			IShortLogical_WhereOrJoin_SingleResult_Entity_Alias<MODEL, RESULT>,
			*/
						
			
			IShortResult_Mapped_Single_Named<MODEL, RESULT>,
			IShortResult_Mapped_Single_Alias<MODEL, RESULT>,
			
			ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
			ISQLJoin_Condition_SingleResult_Named<MODEL, RESULT, Object, Object>,
			ISQLLogical_AndOr_SingleResult_Named<MODEL, RESULT>,
			
			ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
			IShortJoin_Condition_SingleResult_Alias<MODEL, RESULT>,
			ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>,
			
			Void
		> {
	
	

	Short_Collector_Single_Aggregate_Any(BaseQuery query, ModelCompiler<MODEL> modelCompiler, QueryResultAggregate result) {

		super(
				new QueryCollectorImpl<>(
						query,
						modelCompiler,
						result),

				new Collector_Clause(EConditionsClause.WHERE, ConditionsType.SINGLE));
	}

	@Override
	final Collector_Or_Named<
			MODEL,
			RESULT,
			
			ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
			ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>

		> createNamedOrCollector() {

		return new SQL_Collector_Or_NonProcessResult_Named<>(this);
	}

	@Override
	final Collector_And_Named<
			MODEL,
			RESULT,

			ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
			ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>
	
		> createNamedAndCollector() {
		
		return new SQL_Collector_And_NonProcessResult_Named<>(this);
	}

	@Override
	final Collector_Or_Alias<
			MODEL,
			RESULT,
			
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
			ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>
	
		> createAliasOrCollector() {

		return new SQL_Collector_Or_NonProcessResult_Alias<>(this);
	}

	@Override
	final Collector_And_Alias<
			MODEL,
			RESULT,

			ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
			ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>
	
		> createAliasAndCollector() {
		
		return new SQL_Collector_And_NonProcessResult_Alias<>(this);
	}

	@Override
	final Collector_GroupBy<MODEL, RESULT> createGroupByCollector(Collector_Base<MODEL> last, int[] groupByColumns,
			Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {

		throw new UnsupportedOperationException("Not supported for aggregate queries");
	}
}