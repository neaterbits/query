package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

abstract class Collector_Conditions<MODEL, RESULT,
		NESTED_AND extends Collector_Conditions<MODEL, RESULT, NESTED_AND, NESTED_OR>,
		NESTED_OR  extends Collector_Conditions<MODEL, RESULT, NESTED_AND, NESTED_OR>>
		
	extends BaseQueryEntity<MODEL>
	implements ISharedLogical_Base<MODEL, RESULT>, ISharedCompileEndClause<MODEL> {

	final Collector_Clause clauseCollector;

	static Getter makeGetter(Function<?, ?> getter) {
		return new FunctionGetter(getter);
	}

	static Getter makeGetter(Supplier<?> getter) {
		return new SupplierGetter(getter);
	}

	
	Collector_Conditions(Collector_Conditions_Initial<MODEL, RESULT, NESTED_AND, NESTED_OR> last, ConditionsType newConditionsType) {
		super(last);
		
		if (last.clauseCollector.getConditionsType() != ConditionsType.SINGLE) {
			throw new IllegalArgumentException("Only call this constructor after WHERE");
		}
		
		
		this.clauseCollector = new Collector_Clause(last.clauseCollector, newConditionsType);
	}
	
	Collector_Conditions(BaseQueryEntity<MODEL> last, Collector_Clause collector) {
		super(last);

		this.clauseCollector = collector;
	}
	
	Collector_Conditions(QueryCollectorImpl queryCollector, ModelCompiler<MODEL> modelCompiler, Collector_Clause collector) {
		super(queryCollector, modelCompiler);
		
		this.clauseCollector = collector;
	}

	@SuppressWarnings("unchecked")
	final <T extends ISharedLogical_Or<MODEL, RESULT>, IMPL extends Collector_Conditions<MODEL, RESULT, NESTED_AND, NESTED_OR>>
		void addNestedOrImpl(Consumer<T> orBuilder, IMPL subOrImpl) {
		
		
		orBuilder.accept((T)subOrImpl);
		
		// add nested to super
		clauseCollector.add(new CollectedCondition_Nested(subOrImpl));
	}

	@SuppressWarnings("unchecked")
	final <T extends ISharedLogical_And<MODEL, RESULT>, IMPL extends Collector_Conditions<MODEL, RESULT, NESTED_AND, NESTED_OR>>
	
		void addNestedAndImpl(Consumer<T> orBuilder, IMPL subAndImpl) {
		
		orBuilder.accept((T)subAndImpl);
		
		// add nested to super
		clauseCollector.add(new CollectedCondition_Nested(subAndImpl));
	}
	
	
	@Override
	public final MODEL compile() {
		
		// Get collected query
		final QueryCollectorImpl queryCollector = getQueryCollector();
		
		if (queryCollector.getClauses() != null) {
			throw new IllegalStateException("clauses already set");
		}

		// Now set clauses before compiling
		queryCollector.setClauses(clauseCollector);
		
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


	// Overriden by interfaces further down in the hierarchy
	public final <T, R> ISharedProcessResult_After_GroupBy_Or_List_Named<MODEL, RESULT> groupBy(Function<T, R> field) {
		throw new UnsupportedOperationException("TODO");
	}

	public final ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT> groupBy(int ... resultColumns) {
		throw new UnsupportedOperationException("TODO");
	}

	public final <T, R> ISharedProcessResult_After_OrderBy_Or_List_Named<MODEL, RESULT> orderBy(Function<T, R> field) {
		throw new UnsupportedOperationException("TODO");
	}

	public final ISharedCompileEndClause<MODEL> orderBy(int ... resultColumns) {
		throw new UnsupportedOperationException("TODO");
	}
}
