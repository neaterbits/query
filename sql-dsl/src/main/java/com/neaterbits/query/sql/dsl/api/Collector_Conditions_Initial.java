package com.neaterbits.query.sql.dsl.api;


// The "where" clause

abstract class Collector_Conditions_Initial<
			MODEL,
			RESULT,
			AFTER_GROUP_BY

		> extends Collector_Conditions<MODEL, RESULT, AFTER_GROUP_BY> {

	Collector_Conditions_Initial(Collector_Base<MODEL> last, EConditionsClause conditionsClause) {
		super(last, new Collector_Clause(conditionsClause, ConditionsType.SINGLE));
	}

	Collector_Conditions_Initial(Collector_Query<MODEL> queryCollector, Collector_Clause collector) {
		
		super(queryCollector, collector);
	}
}
