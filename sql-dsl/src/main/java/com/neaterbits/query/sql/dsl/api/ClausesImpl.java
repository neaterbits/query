package com.neaterbits.query.sql.dsl.api;

abstract class ClausesImpl<MODEL, RESULT>
	extends BaseQueryEntity
	implements LogicalClauses<MODEL, RESULT> {

	final ClauseCollectorImpl clauseCollector;

	ClausesImpl(ClausesImpl<MODEL, RESULT> last) {
		super(last);
		
		this.clauseCollector = last.clauseCollector;
	}
	
	ClausesImpl(BaseQueryEntity last, ClauseCollectorImpl collector) {
		super(last);

		this.clauseCollector = collector;
	}
}
