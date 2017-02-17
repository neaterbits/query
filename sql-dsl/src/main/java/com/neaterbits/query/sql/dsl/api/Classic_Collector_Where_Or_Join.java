package com.neaterbits.query.sql.dsl.api;


@Deprecated
abstract class Classic_Collector_Where_Or_Join<MODEL, RESULT, AFTER_GROUP_BY> 

	extends Collector_Conditions_Initial<MODEL, RESULT, AFTER_GROUP_BY> 
			
	implements ISharedLogical_Where<MODEL, RESULT> {

	@Deprecated
	Classic_Collector_Where_Or_Join(Collector_Base<MODEL> last) {
		super(last);
	}

	@Deprecated
	Classic_Collector_Where_Or_Join(QueryCollectorImpl queryCollector, ModelCompiler<MODEL> modelCompiler, Collector_Clause collector) {
		super(queryCollector, modelCompiler, collector);
	}
}
