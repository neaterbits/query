package com.neaterbits.query.sql.dsl.api;


// The "where" clause

abstract class Collector_Conditions_Initial<
			MODEL,
			RESULT,
			AFTER_GROUP_BY

		> extends Collector_Conditions_GroupBy<MODEL, RESULT, AFTER_GROUP_BY> {

	Collector_Conditions_Initial(Collector_Base<MODEL> last, EConditionsClause conditionsClause) {
		super(last, new Collector_Clause(conditionsClause, ConditionsType.SINGLE));
	}

	Collector_Conditions_Initial(Collector_Query<MODEL> queryCollector, Collector_Clause collector) {
		
		super(queryCollector, collector);
	}
	
	// TODO : Could refactor away this cast perhaps? However Collector_Conditions_GroupBy does return AFTER_GROUP_BY type
	@SuppressWarnings("unchecked")
	final <AG, RET extends Collector_Conditions_Initial<MODEL, RESULT, AG>> RET getThisInitial() {
		return (RET)this;
	}
}
