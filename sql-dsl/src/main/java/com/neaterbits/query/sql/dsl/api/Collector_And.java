package com.neaterbits.query.sql.dsl.api;


abstract class Collector_And<MODEL, RESULT, AFTER_GROUP_BY>

	extends Collector_Conditions<MODEL, RESULT, AFTER_GROUP_BY> {

	Collector_And(Collector_Conditions_Initial<MODEL, RESULT, AFTER_GROUP_BY> last) {
		super(last, ConditionsType.AND);
	}
	
	Collector_And(BaseQueryEntity<MODEL> qe) {
		super(qe.getQueryCollector(), qe.getModelCompiler(), new Collector_Clause(ConditionsType.AND));
	}
	
}
