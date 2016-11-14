package com.neaterbits.query.sql.dsl.api;

abstract class ClausesImpl<MODEL, RESULT>
	extends BaseQueryEntity
	implements LogicalClauses<MODEL, RESULT> {

	final ClauseCollectorImpl clauseCollector;

	ClausesImpl(BaseQueryEntity last) {
		super(last);

		this.clauseCollector = new ClauseCollectorImpl(this);
	}
}
