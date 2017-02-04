package com.neaterbits.query.sql.dsl.api;


abstract class Classic_Collector_Where_Or_Join<MODEL, RESULT> 

	extends Collector_Conditions_Initial<MODEL, RESULT> 
			
	implements ISharedLogical_Where<MODEL, RESULT> {

	Classic_Collector_Where_Or_Join(BaseQueryEntity<MODEL> last) {
		super(last);
	}

	Classic_Collector_Where_Or_Join(QueryCollectorImpl queryCollector, ModelCompiler<MODEL> modelCompiler, Collector_Clause collector) {
		super(queryCollector, modelCompiler, collector);
	}
}
