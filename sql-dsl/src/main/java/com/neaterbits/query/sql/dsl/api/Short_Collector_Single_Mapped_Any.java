package com.neaterbits.query.sql.dsl.api;

abstract class Short_Collector_Single_Mapped_Any<
		MODEL, 
		RESULT,
		AFTER_GROUP_BY> 
	extends Short_Collector_Any_MappedOrEntity_Any<
			MODEL,
			RESULT,
			
			ISQLLogical_WhereOrJoin_SingleResult_Named_And_Function<MODEL,RESULT>,
			ISQLLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL,RESULT>,
			
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
			
			
			AFTER_GROUP_BY>


			
{
	Short_Collector_Single_Mapped_Any(BaseQuery select, CollectedQueryResult_Mapped_Single result, Collector_Query<MODEL> queryCollector) {
		super(select, result, queryCollector);
	}

	Short_Collector_Single_Mapped_Any(BaseQuery select, CollectedQueryResult_Entity_Single result, Collector_Query<MODEL> queryCollector) {
		super(select, result, queryCollector);
	}

	@Override
	final Collector_Or_Named<
			MODEL,
			RESULT,
			ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
			ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>>
	
		createNamedOrCollector() {
		
		@SuppressWarnings({"unchecked", "rawtypes"})
		final Collector_Conditions_Initial<MODEL, RESULT, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> conditions
			= (Collector_Conditions_Initial)this;
		
		return new SQL_Collector_Or_NonProcessResult_Named<>(conditions);
	}


	@Override
	final Collector_And_Named<
			MODEL,
			RESULT,
			ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
			ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> createNamedAndCollector() {

		@SuppressWarnings({"unchecked", "rawtypes"})
		final Collector_Conditions_Initial<MODEL, RESULT, ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> conditions
			= (Collector_Conditions_Initial)this;
		

		return new SQL_Collector_And_NonProcessResult_Named<>(conditions);
	}

	@Override
	final Collector_Or_Alias<
			MODEL,
			RESULT,
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
			ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>> createAliasOrCollector() {
		
		@SuppressWarnings({"unchecked", "rawtypes"})
		final Collector_Conditions_Initial<MODEL, RESULT, ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>> conditions
			= (Collector_Conditions_Initial)this;
		
		return new SQL_Collector_Or_NonProcessResult_Alias<>(conditions);
	}


	@Override
	final Collector_And_Alias<
			MODEL,
			RESULT,
			ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>, 
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>, 
			ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>>
		
		createAliasAndCollector() {
		
		@SuppressWarnings({"unchecked", "rawtypes"})
		final Collector_Conditions_Initial<MODEL, RESULT, ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>> 
			conditions = (Collector_Conditions_Initial)this;
		
		return new SQL_Collector_And_NonProcessResult_Alias<>(conditions);
	}

}
