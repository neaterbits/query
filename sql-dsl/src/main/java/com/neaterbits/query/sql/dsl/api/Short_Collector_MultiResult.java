package com.neaterbits.query.sql.dsl.api;

final class Short_Collector_MultiResult<MODEL, RESULT>
	extends Short_Collector_Result<
			MODEL,
			RESULT,
			
			ISQLLogical_WhereOrJoin_MultiMapped_Named<MODEL, RESULT>,
			ISQLLogical_WhereOrJoin_MultiMapped_Alias<MODEL, RESULT>,
						
			IShortResult_Mapped_Multi_Named<MODEL, RESULT>,
			IShortResult_Mapped_Multi_Alias<MODEL, RESULT>>

	implements IShortResult_Multi<MODEL, RESULT> {


	private final ECollectionType collectionType;
	
	Short_Collector_MultiResult(SharedSelectSource selectSource, ECollectionType collectionType, ModelCompiler<MODEL> modelCompiler) {
		super(selectSource, modelCompiler);
		
		if (collectionType == null) {
			throw new IllegalArgumentException("collectionType == null");
		}

		this.collectionType = collectionType;
	}

	@Override
	Short_Collector_MapToResult_Base<MODEL, RESULT, ISQLLogical_WhereOrJoin_MultiMapped_Named<MODEL, RESULT>, ISQLLogical_WhereOrJoin_MultiMapped_Alias<MODEL, RESULT>> createMapToResult() {
		
		final CollectedQueryResult_Mapped_Multi collected = new CollectedQueryResult_Mapped_Multi(getResultType(), collectionType);
		
		return new Short_Collector_MapToResult_Multi<>(collected, getModelCompiler());
	}
}
