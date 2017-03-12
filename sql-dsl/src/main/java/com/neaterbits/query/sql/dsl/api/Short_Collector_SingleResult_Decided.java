package com.neaterbits.query.sql.dsl.api;

abstract class Short_Collector_SingleResult_Decided<
		MODEL, 
		RESULT,
		AFTER_GROUP_BY> 
	extends Short_Collector_Result_Decided_Base<
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
	Short_Collector_SingleResult_Decided(BaseQuery select, CollectedQueryResult_Mapped_Single result, ModelCompiler<MODEL> modelCompiler) {
		super(select, result, modelCompiler);
	}

	Short_Collector_SingleResult_Decided(BaseQuery select, CollectedQueryResult_Entity_Single result, ModelCompiler<MODEL> modelCompiler) {
		super(select, result, modelCompiler);
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
		
		return new SQL_Collector_Or_NonProcessResult_Named<>(this);
	}


	@Override
	final Collector_And_Named<
			MODEL,
			RESULT,
			ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
			ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> createNamedAndCollector() {


		return new SQL_Collector_And_NonProcessResult_Named<>(this);
	}


	@Override
	final Collector_Or_Named<
			MODEL,
			RESULT,
			ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
			ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>>
	
		createNamedNestedOrCollector(
			Collector_And_Named<
						MODEL,
						RESULT,
						ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
						ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
						ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
						ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> andClauses) {
		
		
		return new SQL_Collector_Or_NonProcessResult_Named<>(andClauses);
	}


	@Override
	final Collector_And_Named<
			MODEL,
			RESULT,
			ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
			ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> 
	
		createNamedNestedAndCollector(
			Collector_Or_Named<
						MODEL,
						RESULT,
						ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
						ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
						ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
						ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> orClauses) {

		return new SQL_Collector_And_NonProcessResult_Named<>(orClauses);
	}


	@Override
	final Collector_Or_Alias<
			MODEL,
			RESULT,
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
			ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>> createAliasOrCollector() {
		
		return new SQL_Collector_Or_NonProcessResult_Alias<>(this);
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
		
		return new SQL_Collector_And_NonProcessResult_Alias<>(this);
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
				ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
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
				ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
				ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
				ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
				ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>> orClauses) {
		
		
		return new SQL_Collector_And_NonProcessResult_Alias<>(orClauses);
	}
}
