package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

final class Short_Collector_Multi_Any_Any<MODEL, RESULT>
	extends Short_Collector_Any_Any_Any<
			MODEL,
			RESULT,
			
			ISQLLogical_WhereOrJoin_MultiMapped_Named<MODEL, RESULT>,
			ISQLLogical_WhereOrJoin_MultiMapped_Alias<MODEL, RESULT, IShortJoin_Condition_MultiMapped_Alias<MODEL, RESULT>>,
						
			IShortResult_Mapped_Multi_Named<MODEL, RESULT>,
			IShortResult_Mapped_Multi_Alias<MODEL, RESULT>,
			
			ISQLLogical_And_MultiEntity_Named<MODEL, RESULT>,
			ISQLLogical_Or_MultiEntity_Named<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
			ISQLJoin_Condition_MultiEntity_Named<MODEL, RESULT, Object, Object>,
			ISQLLogical_AndOr_MultiEntity_Named<MODEL, RESULT>,

			ISQLLogical_And_MultiEntity_Alias<MODEL, RESULT>,
			ISQLLogical_Or_MultiEntity_Alias<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLJoin_Condition_MultiEntity_Alias<MODEL, RESULT>,
			ISQLLogical_AndOr_MultiEntity_Alias<MODEL, RESULT>,

			Void>

	implements IShortResult_Multi<MODEL, RESULT> {


	private final BaseQuery select;
	private final ECollectionType collectionType;
	
	Short_Collector_Multi_Any_Any(BaseQuery select, SharedSelectSource selectSource, ECollectionType collectionType, ModelCompiler<MODEL> modelCompiler) {
		super(select, selectSource, modelCompiler);
		
		if (collectionType == null) {
			throw new IllegalArgumentException("collectionType == null");
		}

		this.select = select;
		this.collectionType = collectionType;
	}
	

	@Override
	CollectedQueryResult getResultWhenNotPresent() {
		// When string to build() so return multi-entity
		return new CollectedQueryResult_Entity_Multi(getSelectSource(), collectionType);
	}

	@Override
	Collector_Conditions_GroupBy<MODEL, RESULT, ?> getAfterWhereNamed() {
		final CollectedQueryResult_Entity_Multi result = new CollectedQueryResult_Entity_Multi(getSelectSource(), collectionType);
		
		return new Short_Collector_Multi_Mapped_Named<>(select, result, getQueryCollector());
	}



	@Override
	Collector_Conditions_GroupBy<MODEL, RESULT, ?> getAfterWhereAlias() {
		final CollectedQueryResult_Entity_Multi result = new CollectedQueryResult_Entity_Multi(getSelectSource(), collectionType);
		
		return new Short_Collector_Multi_Mapped_Alias<>(select, result, getQueryCollector());
	}



	@Override
	final IMappingCollector<MODEL, RESULT> getMapToResultNamed() {
		
		final CollectedQueryResult_Mapped_Multi collectedQueryResult = new CollectedQueryResult_Mapped_Multi(getResultType(), collectionType);
		
		return new Short_Collector_Multi_Mapped_Named<MODEL, RESULT>(select, collectedQueryResult, getQueryCollector());
	}


	@Override
	final IMappingCollector<MODEL, RESULT> getMapToResultAlias() {
		final CollectedQueryResult_Mapped_Multi collectedQueryResult = new CollectedQueryResult_Mapped_Multi(getResultType(), collectionType);

		return new Short_Collector_Multi_Mapped_Alias<MODEL, RESULT>(select, collectedQueryResult, getQueryCollector());
	}

	@Override
	final Collector_Or_Named<
			MODEL,
			RESULT,
			ISQLLogical_Or_MultiEntity_Named<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
			ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>
		>
		createNamedOrCollector() {

		return new SQL_Collector_Or_MultiEntity_Named<>(this);
	}

	@Override
	final Collector_And_Named<
			MODEL,
			RESULT,
			ISQLLogical_And_MultiEntity_Named<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>, 
			ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
			ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>
		>
	
		createNamedAndCollector() {
		
		return new SQL_Collector_And_MultiEntity_Named<>(this);
	}


	@Override
	final Collector_Or_Alias<
			MODEL,
			RESULT,
			ISQLLogical_Or_MultiEntity_Alias<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>, 
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
			ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>
		>
		createAliasOrCollector() {

		return new SQL_Collector_Or_MultiEntity_Alias<>(this);
	}

	@Override
	final Collector_And_Alias<
			MODEL,
			RESULT,
			ISQLLogical_And_MultiEntity_Alias<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
			ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>
	
		> createAliasAndCollector() {
		
		return new SQL_Collector_And_MultiEntity_Alias<>(this);
	}

	@Override
	final Collector_Or_Alias<
			MODEL,
			RESULT,
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
			ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
			ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>
	
		> createAliasNestedOrCollector(
			Collector_And_Alias<
				MODEL,
				RESULT,
				ISQLLogical_And_MultiEntity_Alias<MODEL, RESULT>,
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
		>
		createAliasNestedAndCollector(
			Collector_Or_Alias<
				MODEL,
				RESULT,
				ISQLLogical_Or_MultiEntity_Alias<MODEL, RESULT>,
				ISQLLogical_And_NonProcessResult_Alias<MODEL, RESULT>,
				ISQLLogical_Or_NonProcessResult_Alias<MODEL, RESULT>,
				ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT>> orClauses) {

		return new SQL_Collector_And_NonProcessResult_Alias<>(orClauses);
	}

	@Override
	final Collector_GroupBy<MODEL, RESULT> createGroupByCollector(Collector_Base<MODEL> last, int[] groupByColumns,
			Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {

		throw new UnsupportedOperationException("TODO - must determine named or alias within groupBy");
	}



	@Override
	public ISharedMapFunctions_Initial<
				MODEL,
				RESULT,
				
				IShortResult_Mapped_Multi_Named<MODEL, RESULT>,
				IShortResult_Mapped_Multi_Alias<MODEL, RESULT>,

				ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Long, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Long, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Short, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Integer, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Long, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, Double, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMap_OpsAndTo_Numeric_Named<MODEL, RESULT, BigDecimal, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultOps_String_Named<MODEL, RESULT, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				
				ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Short, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Integer, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Double, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, BigDecimal, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, String, IShortResult_Mapped_Multi_Named<MODEL, RESULT>>,

				ISharedResultOps_Numeric_Alias<MODEL, RESULT, Long, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultOps_Numeric_Alias<MODEL, RESULT, Long, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultOps_Numeric_Alias<MODEL, RESULT, Short, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultOps_Numeric_Alias<MODEL, RESULT, Integer, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultOps_Numeric_Alias<MODEL, RESULT, Long, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultOps_Numeric_Alias<MODEL, RESULT, Double, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultOps_Numeric_Alias<MODEL, RESULT, BigDecimal, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultOps_String_Alias<MODEL, RESULT, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				
				ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Short, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Integer, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Long, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, Double, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, BigDecimal, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>,
				ISharedResultMapperTo<MODEL, RESULT, String, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>
	
		>
			map() {
		/*
		final ISharedCollector_Functions_Callback_Named<MODEL, RESULT, IShortResult_Mapped_Multi_Named<MODEL, RESULT>> namedCallback
		
				= MapFunctionUtil.multiNamedCallback(
					() -> {
						final Short_Collector_MultiResult_Decided_Named new Short_Collector_MultiResult_Decided_Named<>(
						select,
						new CollectedQueryResult_Mapped_Multi(getResultType(), collectionType),
						getModelCompiler())
				});
		
		
		
		final ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, IShortResult_Mapped_Multi_Alias<MODEL, RESULT>> aliasCallback
			= MapFunctionUtil.multiAliasCallback(
				() -> {
					
					
					final Short_Collector_MultiResult_Decided_Alias<> ret = new Short_Collector_MultiResult_Decided_Alias<>(
								select,
								new CollectedQueryResult_Mapped_Multi(getResultType(), collectionType),
								getModelCompiler()));
		
					return ret;
				});
	
		return new SharedMapFunctions_Initial<>(namedCallback, aliasCallback, this);
		*/
		
		throw new UnsupportedOperationException("TODO");
	}
	
	
}
