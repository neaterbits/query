package com.neaterbits.query.sql.dsl.api;

abstract class Collector_Or<MODEL, RESULT, AFTER_GROUP_BY>
		
		extends Collector_Conditions<MODEL, RESULT, AFTER_GROUP_BY> {
	
	Collector_Or(Collector_Conditions_Initial<MODEL, RESULT, AFTER_GROUP_BY> last) {
		super(last, ConditionsType.OR);
	}
	
	Collector_Or(Collector_Base<MODEL> qe) {
		super(qe.getQueryCollector(), qe.getModelCompiler(), new Collector_Clause(ConditionsType.OR));
	}

}
