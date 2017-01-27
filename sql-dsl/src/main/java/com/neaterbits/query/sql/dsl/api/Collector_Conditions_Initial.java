package com.neaterbits.query.sql.dsl.api;


// The "where" clause

abstract class Collector_Conditions_Initial<MODEL, RESULT> extends Collector_Conditions<MODEL, RESULT> {

	Collector_Conditions_Initial(BaseQueryEntity<MODEL> last) {
		super(last, new Collector_Clause(ConditionsType.SINGLE));
	}

	Collector_Conditions_Initial(QueryCollectorImpl queryCollector, ModelCompiler<MODEL> modelCompiler,
			Collector_Clause collector) {
		
		super(queryCollector, modelCompiler, collector);
	}
}
