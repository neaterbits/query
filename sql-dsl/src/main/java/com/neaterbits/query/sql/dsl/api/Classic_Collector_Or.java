package com.neaterbits.query.sql.dsl.api;

abstract class Classic_Collector_Or<
			MODEL,
			RESULT,
			NESTED_AND extends Classic_Collector_And<MODEL, RESULT, NESTED_AND, NESTED_OR>,
			NESTED_OR  extends Classic_Collector_Or<MODEL, RESULT, NESTED_AND, NESTED_OR>
		>
		
		extends Collector_Conditions<
			MODEL,
			RESULT,
			
			NESTED_AND,
			NESTED_OR> {
	
	Classic_Collector_Or(Collector_Conditions_Initial<MODEL, RESULT, NESTED_AND, NESTED_OR> last) {
		super(last, ConditionsType.OR);
	}
	
	Classic_Collector_Or(BaseQueryEntity<MODEL> qe) {
		super(qe.getQueryCollector(), qe.getModelCompiler(), new Collector_Clause(ConditionsType.OR));
	}

}
