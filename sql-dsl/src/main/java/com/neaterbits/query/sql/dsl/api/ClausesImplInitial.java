package com.neaterbits.query.sql.dsl.api;


// The "where" clause

abstract class ClausesImplInitial<MODEL, RESULT> extends ClausesImpl<MODEL, RESULT> {

	ClausesImplInitial(ClausesImpl<MODEL, RESULT> last) {
		super(last);
	}

	ClausesImplInitial(BaseQueryEntity<MODEL> last, ClauseCollectorImpl collector) {
		super(last, collector);
	}

	ClausesImplInitial(QueryCollectorImpl queryCollector, ModelCompiler<MODEL> modelCompiler,
			ClauseCollectorImpl collector) {
		
		super(queryCollector, modelCompiler, collector);
	}
}
