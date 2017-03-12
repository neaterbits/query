package com.neaterbits.query.sql.dsl.api;

@Deprecated
final class Classic_Collector_MultiEntityResult<MODEL, RESULT> 

	extends Classic_Collector_EntityResult_Base<
			MODEL,
			RESULT,
			
			ISQLLogical_WhereOrJoin_MultiEntity_Named<MODEL, RESULT>,
			ISQLLogical_WhereOrJoin_MultiEntity_Alias<MODEL, RESULT>
			>
	implements IClassicResult_Entity_Multi<MODEL, RESULT> {

	Classic_Collector_MultiEntityResult(ClassicSelect select, SharedSelectSource selectSource, ECollectionType collectionType, ModelCompiler<MODEL> modelCompiler) {
		super(select, new CollectedQueryResult_Entity_Multi(selectSource, collectionType), modelCompiler);
	}

	@Override
	ISQLLogical_WhereOrJoin_MultiEntity_Named<MODEL, RESULT> createWhereOrJoinForNamed() {
		return new SQL_Collector_WhereOrJoin_MultiEntity_Named<>(this);
	}

	@Override
	ISQLLogical_WhereOrJoin_MultiEntity_Alias<MODEL, RESULT> createWhereOrJoinForAlias() {
		return new Classic_Collector_WhereOrJoin_MultiEntity_Alias<>(this);
	}

	@Override
	CollectedQueryResult getCollectedQueryResult() {
		throw new UnsupportedOperationException("Should already have passed query-result to constructor");
	}
}
