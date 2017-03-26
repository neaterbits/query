package com.neaterbits.query.sql.dsl.api;

final class Short_Collector_Single_Any_Any_Instance<MODEL, RESULT>
		extends Short_Collector_Initial_Single_Any_Any_Base<MODEL, RESULT> {
	
	
	Short_Collector_Single_Any_Any_Instance(BaseQuery select, SharedSelectSource selectSource,
			ModelCompiler<MODEL> modelCompiler) {
		super(select, selectSource, modelCompiler);
	}

	@Override
	<JOIN_FROM> Short_Collector_Single_Entity_Named_TypedJoin<MODEL, RESULT, JOIN_FROM> typedJoinCollector() {
		
		// Went straight to join, so this is entity
		
		return new Short_Collector_Single_Entity_Named_TypedJoin<MODEL, RESULT, JOIN_FROM>(this);
	}
}
