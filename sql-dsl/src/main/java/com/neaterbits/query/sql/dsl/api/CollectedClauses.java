package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

abstract class CollectedClauses<MODEL, RESULT>
	extends BaseQueryEntity<MODEL>
	implements ISharedLogicalClauses<MODEL, RESULT>, ISharedCompileEndClause<MODEL> {

	final Collector_Clause clauseCollector;

	static Getter makeGetter(Function<?, ?> getter) {
		return new FunctionGetter(getter);
	}

	static Getter makeGetter(Supplier<?> getter) {
		return new SupplierGetter(getter);
	}

	
	CollectedClauses(CollectedClauses<MODEL, RESULT> last) {
		super(last);
		
		this.clauseCollector = last.clauseCollector;
	}
	
	CollectedClauses(BaseQueryEntity<MODEL> last, Collector_Clause collector) {
		super(last);

		this.clauseCollector = collector;
	}
	
	CollectedClauses(QueryCollectorImpl queryCollector, ModelCompiler<MODEL> modelCompiler, Collector_Clause collector) {
		super(queryCollector, modelCompiler);
		
		this.clauseCollector = collector;
	}

	@SuppressWarnings("unchecked")
	final <T extends ISharedOrClauses<MODEL, RESULT>, IMPL extends CollectedClauses<MODEL, RESULT>> void addNestedOrImpl(Consumer<T> orBuilder, IMPL impl) {
		orBuilder.accept((T)impl);
		
		// add nested to super
		clauseCollector.add(this, new CollectedCondition_Nested(impl));
	}

	@SuppressWarnings("unchecked")
	final <T extends ISharedAndClauses<MODEL, RESULT>, IMPL extends CollectedClauses<MODEL, RESULT>> void addNestedAndImpl(Consumer<T> orBuilder, IMPL impl) {
		
		orBuilder.accept((T)impl);
		
		// add nested to super
		clauseCollector.add(this, new CollectedCondition_Nested(impl));
	}
	
	
	@Override
	public final MODEL compile() {
		
		// Get collected query
		final QueryCollectorImpl queryCollector = getQueryCollector();
		
		// Compile the collected query
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
