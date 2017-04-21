package com.neaterbits.query.sql.dsl.api;

abstract class Collector_Or<MODEL, RESULT, AFTER_GROUP_BY>
		
		extends Collector_Conditions_Intermediate<MODEL, RESULT, AFTER_GROUP_BY> {
	
	Collector_Or(Collector_Conditions_Initial<MODEL, RESULT, AFTER_GROUP_BY> last) {
		super(last, ConditionsType.OR);
	}

	Collector_Or(Collector_Conditions_Intermediate<MODEL, RESULT, AFTER_GROUP_BY> qe) {
		super(qe.getQueryCollector(), qe.clauseCollector, ConditionsType.OR);

		if (qe.clauseCollector != null && !qe.clauseCollector.isEmpty()) {
			throw new IllegalStateException("Not copying non-empty clause-collector");
		}
	}
}
