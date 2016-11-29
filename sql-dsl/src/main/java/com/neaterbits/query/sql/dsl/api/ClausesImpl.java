package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

abstract class ClausesImpl<MODEL, RESULT>
	extends BaseQueryEntity<MODEL>
	implements LogicalClauses<MODEL, RESULT> {

	final ClauseCollectorImpl clauseCollector;

	static Getter makeGetter(Function<?, ?> getter) {
		return new FunctionGetter(getter);
	}

	static Getter makeGetter(Supplier<?> getter) {
		return new SupplierGetter(getter);
	}

	
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
		CompiledQuery compiledQuery;
		try {
			compiledQuery = CompiledQuery.compile(queryCollector);
		} catch (CompileException ex) {
			throw new IllegalStateException("Failed to compile", ex);
		}
		
		// Compile into model (better name for this operation?)
		return getModelCompiler().compile(compiledQuery);
	}
}
