package com.neaterbits.query.sql.dsl.api;

final class Classic_Collector_MultiEntityResult<MODEL, RESULT> 

	extends Collector_EntityResult_Base<
			MODEL,
			RESULT,
			
			IClassicLogical_WhereOrJoin_MultiEntity_Named<MODEL, RESULT>,
			IClassicLogical_WhereOrJoin_MultiEntity_Alias<MODEL, RESULT>
			>
	implements IClassicResult_Entity_Multi<MODEL, RESULT> {

	Classic_Collector_MultiEntityResult(SharedSelectSource selectSource, ECollectionType collectionType, ModelCompiler<MODEL> modelCompiler) {
		super(new CollectedQueryResult_Entity_Multi(selectSource, collectionType), modelCompiler);
	}

	@Override
	IClassicLogical_WhereOrJoin_MultiEntity_Named<MODEL, RESULT> createWhereOrJoinForNamed() {
		return new Classic_Collector_WhereOrJoin_MultiEntity_Named<>(this);
	}

	@Override
	IClassicLogical_WhereOrJoin_MultiEntity_Alias<MODEL, RESULT> createWhereOrJoinForAlias() {
		return new Classic_Collector_WhereOrJoin_MultiEntity_Alias<>(this);
	}
}
