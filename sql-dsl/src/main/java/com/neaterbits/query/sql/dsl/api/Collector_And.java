package com.neaterbits.query.sql.dsl.api;


abstract class Collector_And<MODEL, RESULT, AFTER_GROUP_BY>

	extends Collector_Conditions_Intermediate<MODEL, RESULT, AFTER_GROUP_BY> {

	Collector_And(Collector_Conditions_Initial<MODEL, RESULT, AFTER_GROUP_BY> last) {
		super(last, ConditionsType.AND);
	}

	@Deprecated // TODO necessary?
	Collector_And(Collector_Conditions_Base<MODEL, RESULT> qe, Void disambiguate) {
		super(qe.getQueryCollector(), qe.clauseCollector, ConditionsType.AND);
		
		if (qe.clauseCollector != null && !qe.clauseCollector.isEmpty()) {
			throw new IllegalStateException("Not copying non-empty clause-collector");
		}
	}
}
