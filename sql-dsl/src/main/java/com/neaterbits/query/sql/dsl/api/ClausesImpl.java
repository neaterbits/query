package com.neaterbits.query.sql.dsl.api;

abstract class ClausesImpl<MODEL, RESULT>
	extends BaseQueryEntity<MODEL>
	implements LogicalClauses<MODEL, RESULT> {

	final ClauseCollectorImpl clauseCollector;

	ClausesImpl(ClausesImpl<MODEL, RESULT> last) {
		super(last);
		
		this.clauseCollector = last.clauseCollector;
	}
	
	ClausesImpl(BaseQueryEntity<MODEL> last, ClauseCollectorImpl collector) {
		super(last);

		this.clauseCollector = collector;
	}
	
	@Override
	public final MODEL compile() {
		
		// Get collected query
		final QueryCollectorImpl queryCollector = getQueryCollector();
		
		// Compile the collectd query
		final CompiledQuery compiledQuery = CompiledQuery.compile(queryCollector);
		
		// Compile into model (better name for this operation?)
		return getModelCompiler().compile(compiledQuery);
	}
}
