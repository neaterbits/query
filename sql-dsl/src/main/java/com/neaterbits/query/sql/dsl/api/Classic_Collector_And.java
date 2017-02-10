package com.neaterbits.query.sql.dsl.api;


abstract class Classic_Collector_And<MODEL, RESULT, AFTER_GROUP_BY>
			
			
			extends Collector_Conditions<MODEL, RESULT, AFTER_GROUP_BY> {

	Classic_Collector_And(Collector_Conditions_Initial<MODEL, RESULT, AFTER_GROUP_BY> last) {
		super(last, ConditionsType.AND);
	}
	
	Classic_Collector_And(BaseQueryEntity<MODEL> qe) {
		super(qe.getQueryCollector(), qe.getModelCompiler(), new Collector_Clause(ConditionsType.AND));
	}
	
}
