package com.neaterbits.query.sql.dsl.api;

abstract class Classic_Collector_Or<MODEL, RESULT>
		
		extends Collector_Conditions<MODEL, RESULT> {
	
	Classic_Collector_Or(Collector_Conditions_Initial<MODEL, RESULT> last) {
		super(last, ConditionsType.OR);
	}
	
	Classic_Collector_Or(BaseQueryEntity<MODEL> qe) {
		super(qe.getQueryCollector(), qe.getModelCompiler(), new Collector_Clause(ConditionsType.OR));
	}

}
