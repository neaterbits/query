package com.neaterbits.query.sql.dsl.api;

final class Short_Collector_Single_Any_Any_Instance<MODEL, RESULT>
		extends Short_Collector_Initial_Single_Any_Any_Base<MODEL, RESULT> {
	
	
	Short_Collector_Single_Any_Any_Instance(BaseQuery select, SharedSelectSource selectSource,
			ModelCompiler<MODEL> modelCompiler) {
		super(select, modelCompiler, null, selectSource);
	}

	@Override
	<JOIN_FROM> Short_Collector_Single_Entity_Named_TypedJoin<MODEL, RESULT, JOIN_FROM> namedTypedJoinCollector() {
		
		// Went straight to join, so this is entity
		return new Short_Collector_Single_Entity_Named_TypedJoin<MODEL, RESULT, JOIN_FROM>(this);
	}

	@Override
	<JOIN_FROM> IShortLogical_WhereOrJoin_SingleResult_Alias<MODEL, RESULT> aliasTypedJoinCollector() {
		// Went straight to join, so this is entity, but may just pass "this" ?
		// or create Short_Collector_Single_Entity_Alias_TypedJoin<MODEL, RESULT, JOIN_FROM>(this)
		// from patterm above
		throw new UnsupportedOperationException("TODO");
	}
	
	
}
