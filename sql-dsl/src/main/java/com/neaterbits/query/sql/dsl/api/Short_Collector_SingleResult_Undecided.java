package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

final class Short_Collector_SingleResult_Undecided<MODEL, RESULT>
	extends Short_Collector_Result_Undecided_Base<
			MODEL,
			RESULT,

			ISQLLogical_WhereOrJoin_SingleResult_Named_And_Function<MODEL, RESULT>,
			ISQLLogical_WhereOrJoin_SingleResult_Alias_And_Function<MODEL, RESULT>,
						
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
			ISQLJoin_Condition_SingleResult_Alias<MODEL, RESULT>,
			ISQLLogical_AndOr_SingleResult_Alias<MODEL, RESULT>,

			Void
			>

	implements
			ISQLLogical_Where_SingleResult_Named_Base<MODEL, RESULT>,
			ISQLLogical_WhereOrJoin_Named_Base<MODEL, RESULT>,
			
			IShortResult_Single<MODEL, RESULT>,
			IMappingCollector<MODEL, RESULT> {
	
	private final BaseShortSelect select;
	

	Short_Collector_SingleResult_Undecided(BaseShortSelect select, SharedSelectSource selectSource, ModelCompiler<MODEL> modelCompiler) {
		super(select, selectSource, modelCompiler);
		
		this.select = select;
	}

	@Override
	CollectedQueryResult getResultWhenNotPresent() {
		// When string to build() so return multi-entity
		return new CollectedQueryResult_Entity_Single(getSelectSource());
	}

	@Override
	final IMappingCollector<MODEL, RESULT> getMapToResultNamed() {
		
		final CollectedQueryResult_Mapped_Single collectedQueryResult = new CollectedQueryResult_Mapped_Single(getResultType());
		
		return new Short_Collector_SingleResult_Decided_Named<MODEL, RESULT>(select, collectedQueryResult, getModelCompiler());
	}


	@Override
	final IMappingCollector<MODEL, RESULT> getMapToResultAlias() {
		final CollectedQueryResult_Mapped_Single collectedQueryResult = new CollectedQueryResult_Mapped_Single(getResultType());

		return new Short_Collector_SingleResult_Decided_Alias<MODEL, RESULT>(select, collectedQueryResult, getModelCompiler());
	}
	

	// ******************* !!! IMPORTANT must switch to one that implements AndOr interface !!! *******************

	@Override
	Collector_Conditions_GroupBy<MODEL, RESULT, ?> getAfterWhereNamed() {
		
		// TODO: rename *MapToResult* here
		final CollectedQueryResult_Entity_Single collectedQueryResult = new CollectedQueryResult_Entity_Single(getSelectSource());

		return new Short_Collector_SingleResult_Decided_Named<>(select, collectedQueryResult, getModelCompiler());
	}


	@Override
	Collector_Conditions_GroupBy<MODEL, RESULT, ?> getAfterWhereAlias() {
		// TODO: rename *MapToResult* here
		final CollectedQueryResult_Entity_Single collectedQueryResult = new CollectedQueryResult_Entity_Single(getSelectSource());

		return new Short_Collector_SingleResult_Decided_Alias<>(select, collectedQueryResult, getModelCompiler());
	}
	
	@Override
	final Collector_Or_Named<
			MODEL, 
			RESULT,
			ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
			ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>
		>
		createNamedOrCollector() {
		
		return new SQL_Collector_Or_NonProcessResult_Named<>(this);
	}


	@Override
	final Collector_And_Named<MODEL, RESULT, ISQLLogical_And_NonProcessResult_Named<
			MODEL,
			RESULT>,
			ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
			ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>
		>
		createNamedAndCollector() {

		return new SQL_Collector_And_NonProcessResult_Named<>(this);
	}


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
			ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>
		>
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
			ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>
		>
		createAliasOrCollector() {
		
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
		>
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
			ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>
		> 
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
			ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>
		> createAliasNestedAndCollector(
				
			Collector_Or_Alias<
				MODEL,
				RESULT,
				ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
				ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
				ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
				ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>> orClauses) {
		
		return new SQL_Collector_And_NonProcessResult_Alias<>(orClauses);
	}

	@Override
	final Collector_GroupBy<MODEL, RESULT> createGroupByCollector(Collector_Base<MODEL> last, int[] groupByColumns,
			Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {
		throw new UnsupportedOperationException("TODO - no where-clause, must determine type in GroupBy, Collector_Named_Or_Alias or similar");
	}

	

	@Override
	public ISharedMapFunctions_Initial<
			MODEL,
			RESULT,
			
			IShortResult_Mapped_Single_Named<MODEL, RESULT>,
			IShortResult_Mapped_Single_Alias<MODEL, RESULT>,
			
			ISharedResultOps_Numeric_Named<MODEL, RESULT, Long, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultOps_Numeric_Named<MODEL, RESULT, Long, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultOps_Numeric_Named<MODEL, RESULT, Short, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultOps_Numeric_Named<MODEL, RESULT, Integer, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultOps_Numeric_Named<MODEL, RESULT, Long, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultOps_Numeric_Named<MODEL, RESULT, Double, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultOps_Numeric_Named<MODEL, RESULT, BigDecimal, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultOps_String_Named<MODEL, RESULT, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			
			ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Single_Named<MODEL, RESULT>>, 
			ISharedResultMapperTo<MODEL, RESULT, Short, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Integer, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Double, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, BigDecimal, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, String, IShortResult_Mapped_Single_Named<MODEL, RESULT>>,
			
			ISharedResultOps_Numeric_Alias<MODEL, RESULT, Long, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultOps_Numeric_Alias<MODEL, RESULT, Long, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultOps_Numeric_Alias<MODEL, RESULT, Short, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultOps_Numeric_Alias<MODEL, RESULT, Integer, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultOps_Numeric_Alias<MODEL, RESULT, Long, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultOps_Numeric_Alias<MODEL, RESULT, Double, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultOps_Numeric_Alias<MODEL, RESULT, BigDecimal, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultOps_String_Alias<MODEL, RESULT, String, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			
			ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Short, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Integer, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, Double, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, BigDecimal, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>,
			ISharedResultMapperTo<MODEL, RESULT, String, IShortResult_Mapped_Single_Alias<MODEL, RESULT>>>

		map() {
		
		final ISharedCollector_Functions_Callback_Named<MODEL, RESULT, IShortResult_Mapped_Single_Named<MODEL, RESULT>> namedCallback
		
			= MapFunctionUtil.singleNamedCallback(
				() -> new Short_Collector_SingleResult_Decided_Named<>(
					select,
					new CollectedQueryResult_Mapped_Single(getResultType()),
					getModelCompiler()));
			
			
			
		final ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, IShortResult_Mapped_Single_Alias<MODEL, RESULT>> aliasCallback
			= MapFunctionUtil.singleAliasCallback(
				() -> {
					
					
					return new Short_Collector_SingleResult_Decided_Alias<>(
						select,
						new CollectedQueryResult_Mapped_Single(getResultType()),
						getModelCompiler());

				});

		return new ResultMapper_ExpressionList_Initial_Undecided<>(this/* namedCallback, aliasCallback */);
	}

	@Override
	public MappingCollector getMappingCollector() {
		throw new UnsupportedOperationException("TODO");
	}
}
