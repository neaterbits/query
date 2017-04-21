package com.neaterbits.query.sql.dsl.api;


// The "where" clause

abstract class Collector_Conditions_Initial<
			MODEL,
			RESULT,
			AFTER_GROUP_BY

		> extends Collector_Conditions_GroupBy<MODEL, RESULT, AFTER_GROUP_BY> {

	Collector_Conditions_Initial(Collector_Conditions_Initial<MODEL, RESULT, AFTER_GROUP_BY> last, EConditionsClause conditionsClause) {
		super(last, last.getQueryCollector().addConditionClauses(conditionsClause));
	}

	Collector_Conditions_Initial(Collector_Conditions_Initial<MODEL, RESULT, AFTER_GROUP_BY> last, EConditionsClause conditionsClause, CollectedQueryResult result) {
		super(last, last.getQueryCollector().addConditionClauses(conditionsClause), result);
	}
	

	@Deprecated // TODO necessary?
	Collector_Conditions_Initial(Collector_Query<MODEL> queryCollector, ICollectorClause collector) {
		
		super(queryCollector, collector);
	}

	Collector_Conditions_Initial(Collector_Query<MODEL> queryCollector) {
		super(queryCollector, null);
	}

	Collector_Conditions_Initial(Collector_Query<MODEL> queryCollector, EConditionsClause conditionsClause) {
		
		super(queryCollector, queryCollector.addConditionClauses(conditionsClause));
	}

	
	@Deprecated // TODO in use?
	Collector_Conditions_Initial(Collector_GroupBy<MODEL, RESULT> last, EConditionsClause conditionsClause) {
		super(last, last.getQueryCollector().addConditionClauses(conditionsClause));
	}
	
	
	// TODO : Could refactor away this cast perhaps? However Collector_Conditions_GroupBy does return AFTER_GROUP_BY type
	@SuppressWarnings("unchecked")
	final <AG, RET extends Collector_Conditions_Initial<MODEL, RESULT, AG>> RET getThisInitial() {
		return (RET)this;
	}
}
