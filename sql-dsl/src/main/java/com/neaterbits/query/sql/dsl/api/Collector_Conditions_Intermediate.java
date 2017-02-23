package com.neaterbits.query.sql.dsl.api;

abstract class Collector_Conditions_Intermediate<MODEL, RESULT, AFTER_GROUP_BY>
		extends Collector_Conditions<MODEL, RESULT, AFTER_GROUP_BY>{

	Collector_Conditions_Intermediate(Collector_Conditions_Initial<MODEL, RESULT, AFTER_GROUP_BY> last, ConditionsType newConditionsType) {
		super(last, newConditionsType);
	}

	Collector_Conditions_Intermediate(Collector_Base<MODEL> last, Collector_Clause collector) {
		super(last, collector);
	}

	Collector_Conditions_Intermediate(Collector_Query<MODEL> queryCollector, Collector_Clause collector) {
		super(queryCollector, collector);
	}
}
