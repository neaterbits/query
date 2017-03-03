package com.neaterbits.query.sql.dsl.api;

final class Short_Collector_MultiResult_Undecided<MODEL, RESULT>
	extends Short_Collector_Result_Undecided_Base<
			MODEL,
			RESULT,
			
			ISQLLogical_WhereOrJoin_MultiMapped_Named<MODEL, RESULT>,
			ISQLLogical_WhereOrJoin_MultiMapped_Alias<MODEL, RESULT>,
						
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


	private final ECollectionType collectionType;
	
	Short_Collector_MultiResult_Undecided(SharedSelectSource selectSource, ECollectionType collectionType, ModelCompiler<MODEL> modelCompiler) {
		super(selectSource, modelCompiler);
		
		if (collectionType == null) {
			throw new IllegalArgumentException("collectionType == null");
		}

		this.collectionType = collectionType;
	}
	
	

	@Override
	Collector_Conditions_GroupBy<MODEL, RESULT, ?> getAfterWhereNamed() {
		final CollectedQueryResult_Entity_Multi result = new CollectedQueryResult_Entity_Multi(getSelectSource(), collectionType);
		
		return new Short_Collector_MultiResult_Decided_Named<>(result, getModelCompiler());
	}



	@Override
	Collector_Conditions_GroupBy<MODEL, RESULT, ?> getAfterWhereAlias() {
		final CollectedQueryResult_Entity_Multi result = new CollectedQueryResult_Entity_Multi(getSelectSource(), collectionType);
		
		return new Short_Collector_MultiResult_Decided_Alias<>(result, getModelCompiler());
	}



	@Override
	final IMappingCollector<MODEL, RESULT> getMapToResultNamed() {
		
		final CollectedQueryResult_Mapped_Multi collectedQueryResult = new CollectedQueryResult_Mapped_Multi(getResultType(), collectionType);
		
		return new Short_Collector_MultiResult_Decided_Named<MODEL, RESULT>(collectedQueryResult, getModelCompiler());
	}


	@Override
	final IMappingCollector<MODEL, RESULT> getMapToResultAlias() {
		final CollectedQueryResult_Mapped_Multi collectedQueryResult = new CollectedQueryResult_Mapped_Multi(getResultType(), collectionType);

		return new Short_Collector_MultiResult_Decided_Alias<MODEL, RESULT>(collectedQueryResult, getModelCompiler());
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
				ISQLLogical_And_MultiEntity_Named<MODEL, RESULT>,
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
				ISQLLogical_Or_MultiEntity_Named<MODEL, RESULT>,
				ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>,
				ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>,
				ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>> orClauses) {
		
		return new SQL_Collector_And_NonProcessResult_Named<>(orClauses);
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
}
