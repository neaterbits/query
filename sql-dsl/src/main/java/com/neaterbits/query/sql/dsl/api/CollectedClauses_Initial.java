package com.neaterbits.query.sql.dsl.api;


// The "where" clause

abstract class CollectedClauses_Initial<MODEL, RESULT> extends CollectedClauses<MODEL, RESULT> {

	CollectedClauses_Initial(CollectedClauses<MODEL, RESULT> last) {
		super(last);
	}

	CollectedClauses_Initial(BaseQueryEntity<MODEL> last, Collector_Clause collector) {
		super(last, collector);
	}

	CollectedClauses_Initial(QueryCollectorImpl queryCollector, ModelCompiler<MODEL> modelCompiler,
			Collector_Clause collector) {
		
		super(queryCollector, modelCompiler, collector);
	}
}
