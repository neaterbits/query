package com.neaterbits.query.sql.dsl.api;

abstract class Classic_Collector_Or<MODEL, RESULT, AFTER_GROUP_BY>
		
		extends Collector_Conditions<MODEL, RESULT, AFTER_GROUP_BY> {
	
	Classic_Collector_Or(Collector_Conditions_Initial<MODEL, RESULT, AFTER_GROUP_BY> last) {
		super(last, ConditionsType.OR);
	}
	
	Classic_Collector_Or(BaseQueryEntity<MODEL> qe) {
		super(qe.getQueryCollector(), qe.getModelCompiler(), new Collector_Clause(ConditionsType.OR));
	}

}
