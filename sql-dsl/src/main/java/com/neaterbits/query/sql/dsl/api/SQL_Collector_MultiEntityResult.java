package com.neaterbits.query.sql.dsl.api;

final class SQL_Collector_MultiEntityResult<MODEL, RESULT> 

	extends Collector_EntityResult_Base<
			MODEL,
			RESULT,
			
			ISQLLogical_WhereOrJoin_MultiEntity_Named<MODEL, RESULT>,
			ISQLLogical_WhereOrJoin_MultiEntity_Alias<MODEL, RESULT>
			>
	implements IClassicResult_Entity_Multi<MODEL, RESULT> {

	SQL_Collector_MultiEntityResult(SharedSelectSource selectSource, ECollectionType collectionType, ModelCompiler<MODEL> modelCompiler) {
		super(new CollectedQueryResult_Entity_Multi(selectSource, collectionType), modelCompiler);
	}

	@Override
	ISQLLogical_WhereOrJoin_MultiEntity_Named<MODEL, RESULT> createWhereOrJoinForNamed() {
		return new SQL_Collector_WhereOrJoin_MultiEntity_Named<>(this);
	}

	@Override
	ISQLLogical_WhereOrJoin_MultiEntity_Alias<MODEL, RESULT> createWhereOrJoinForAlias() {
		return new SQL_Collector_WhereOrJoin_MultiEntity_Alias<>(this);
	}
}
