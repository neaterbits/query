package com.neaterbits.query.sql.dsl.api;

abstract class Collector_And_Or_Base<MODEL, RESULT, AFTER_GROUP_BY> 

	extends Collector_Conditions_Initial<MODEL, RESULT, AFTER_GROUP_BY> {

	Collector_And_Or_Base(Collector_Base<MODEL> last) {
		super(last);
	}
	
	Collector_And_Or_Base(QueryCollectorImpl queryCollector, ModelCompiler<MODEL> modelCompiler, Collector_Clause collector) {
		super(queryCollector, modelCompiler, collector);
	}
}
