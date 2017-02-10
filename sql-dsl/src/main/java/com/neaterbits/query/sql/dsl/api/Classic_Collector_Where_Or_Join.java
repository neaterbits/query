package com.neaterbits.query.sql.dsl.api;


abstract class Classic_Collector_Where_Or_Join<MODEL, RESULT, AFTER_GROUP_BY> 

	extends Collector_Conditions_Initial<MODEL, RESULT, AFTER_GROUP_BY> 
			
	implements ISharedLogical_Where<MODEL, RESULT> {

	Classic_Collector_Where_Or_Join(BaseQueryEntity<MODEL> last) {
		super(last);
	}

	Classic_Collector_Where_Or_Join(QueryCollectorImpl queryCollector, ModelCompiler<MODEL> modelCompiler, Collector_Clause collector) {
		super(queryCollector, modelCompiler, collector);
	}
}
