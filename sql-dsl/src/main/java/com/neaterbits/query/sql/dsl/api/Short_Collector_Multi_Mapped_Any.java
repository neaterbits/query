package com.neaterbits.query.sql.dsl.api;

abstract class Short_Collector_Multi_Mapped_Any<MODEL, RESULT, AFTER_GROUP_BY>
	extends Short_Collector_Any_MappedOrEntityOrAggregate_Any<
		MODEL,
		RESULT,
		

		ISQLLogical_WhereOrJoin_MultiMapped_Named<MODEL,RESULT>,
		ISQLLogical_WhereOrJoin_MultiMapped_Alias<MODEL,RESULT, IShortJoin_Condition_MultiMapped_Alias<MODEL, RESULT>>,
		
		IShortResult_Mapped_Multi_Named<MODEL, RESULT>,
		IShortResult_Mapped_Multi_Alias<MODEL, RESULT>,
		
		ISQLLogical_And_MultiMapped_Named<MODEL, RESULT>,
		ISQLLogical_Or_MultiMapped_Named<MODEL, RESULT>,
		
		ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
		ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
		
		ISQLJoin_Condition_MultiMapped_Named<MODEL, RESULT, Object, Object>,
		ISQLLogical_AndOr_MultiMapped_Named<MODEL, RESULT>,
		
		ISQLLogical_And_MultiMapped_Alias<MODEL, RESULT>,
		ISQLLogical_Or_MultiMapped_Alias<MODEL, RESULT>,
		
		ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
		ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
		
		IShortJoin_Condition_MultiMapped_Alias<MODEL, RESULT>,
		ISQLLogical_AndOr_MultiMapped_Alias<MODEL, RESULT>,
		
		AFTER_GROUP_BY
		
		> {

	Short_Collector_Multi_Mapped_Any(Collector_Query<MODEL> queryCollector) {
		super(queryCollector);
	}

	@Deprecated
	Short_Collector_Multi_Mapped_Any(Collector_Query<MODEL> queryCollector, CollectedQueryResult_Mapped result) {
		super(queryCollector, result);
	}

	@Override
	final Collector_Or_Named<
			MODEL,
			RESULT,
			ISQLLogical_Or_MultiMapped_Named<MODEL, RESULT>, 
			ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
			ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>
			>
		createNamedOrCollector() {

		return new SQL_Collector_Or_MultiMapped_Named<>(getThisInitial());
	}

	@Override
	final Collector_And_Named<
			MODEL,
			RESULT, 
			ISQLLogical_And_MultiMapped_Named<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
			ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> createNamedAndCollector() {
		
		return new SQL_Collector_And_MultiMapped_Named<>(getThisInitial());
	}

	@Override
	final Collector_Or_Alias<
			MODEL,
			RESULT,
			ISQLLogical_Or_MultiMapped_Alias<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
			ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>>
	
		createAliasOrCollector() {
		
		return new SQL_Collector_Or_MultiMapped_Alias<>(getThisInitial());
	}

	@Override
	final Collector_And_Alias<
			MODEL,
			RESULT,
			ISQLLogical_And_MultiMapped_Alias<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
			ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>>
	
		createAliasAndCollector() {
		
		return new SQL_Collector_And_MultiMapped_Alias<>(getThisInitial());
	}
}
