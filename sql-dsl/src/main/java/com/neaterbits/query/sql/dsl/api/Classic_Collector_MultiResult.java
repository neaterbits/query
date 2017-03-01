package com.neaterbits.query.sql.dsl.api;


final class Classic_Collector_MultiResult<MODEL, RESULT>

		extends Classic_Collector_Result<
			MODEL,
			RESULT, 
			ISQLLogical_WhereOrJoin_MultiEntity_Named<MODEL, RESULT>,
			ISQLLogical_WhereOrJoin_MultiEntity_Alias<MODEL, RESULT>,

			ISQLLogical_WhereOrJoin_MultiMapped_Named<MODEL, RESULT>,
			ISQLLogical_WhereOrJoin_MultiMapped_Alias<MODEL, RESULT>,

			IClassicResult_Mapped_Multi_Named<MODEL, RESULT>,
			IClassicResult_Mapped_Multi_Alias<MODEL, RESULT>> 


		implements IClassicResult_Multi<MODEL, RESULT> {

	private final ECollectionType collectionType;
	
	Classic_Collector_MultiResult(SharedSelectSource selectSource, ECollectionType collectionType, ModelCompiler<MODEL> modelCompiler) {
		super(selectSource, modelCompiler);
		
		if (collectionType == null) {
			throw new IllegalArgumentException("collectionType == null");
		}

		this.collectionType = collectionType;
	}

	@Override
	ISQLLogical_WhereOrJoin_MultiEntity_Named<MODEL, RESULT> createWhereOrJoinForNamed() {
		return new SQL_Collector_WhereOrJoin_MultiEntity_Named<>(this);
	}

	@Override
	ISQLLogical_WhereOrJoin_MultiEntity_Alias<MODEL, RESULT> createWhereOrJoinForAlias() {
		return new SQL_Collector_WhereOrJoin_MultiEntity_Alias<>(this);
	}

	// For instantiating where or join when mapped
	@Override
	Classic_Collector_MapToResult_Base<MODEL, RESULT, ISQLLogical_WhereOrJoin_MultiMapped_Named<MODEL, RESULT>, ISQLLogical_WhereOrJoin_MultiMapped_Alias<MODEL, RESULT>> createMapToResult() {
		return new Classic_Collector_MapToResult_Multi<MODEL, RESULT>(getResultType(), collectionType, getModelCompiler());
	}

	@Override
	CollectedQueryResult getCollectedQueryResult() {
		// Called directly on initial collector returned from list(), so return multi-entity since no map() calls occured
		return new CollectedQueryResult_Entity_Multi(getSelectSource(), collectionType);
	}
}