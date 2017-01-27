package com.neaterbits.query.sql.dsl.api;


abstract class Classic_Collector_And<
			MODEL,
			RESULT,
			NESTED_AND extends Classic_Collector_And<MODEL, RESULT, NESTED_AND, NESTED_OR>,
			NESTED_OR  extends Classic_Collector_Or<MODEL, RESULT, NESTED_AND, NESTED_OR>>
			
			
			extends Collector_Conditions<
					MODEL,
					RESULT,
					NESTED_AND,
					NESTED_OR> {

	Classic_Collector_And(Collector_Conditions_Initial<MODEL, RESULT, NESTED_AND, NESTED_OR> last) {
		super(last, ConditionsType.AND);
	}
	
	Classic_Collector_And(BaseQueryEntity<MODEL> qe) {
		super(qe.getQueryCollector(), qe.getModelCompiler(), new Collector_Clause(ConditionsType.AND));
	}
	
}
