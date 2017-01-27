package com.neaterbits.query.sql.dsl.api;


// The "where" clause

abstract class Collector_Conditions_Initial<
			MODEL,
			RESULT,

			NESTED_AND extends Collector_Conditions<MODEL, RESULT, NESTED_AND, NESTED_OR>,
			NESTED_OR  extends Collector_Conditions <MODEL, RESULT, NESTED_AND, NESTED_OR>
		> extends Collector_Conditions<MODEL, RESULT, NESTED_AND, NESTED_OR> {

	Collector_Conditions_Initial(BaseQueryEntity<MODEL> last) {
		super(last, new Collector_Clause(ConditionsType.SINGLE));
	}

	Collector_Conditions_Initial(QueryCollectorImpl queryCollector, ModelCompiler<MODEL> modelCompiler,
			Collector_Clause collector) {
		
		super(queryCollector, modelCompiler, collector);
	}
}
