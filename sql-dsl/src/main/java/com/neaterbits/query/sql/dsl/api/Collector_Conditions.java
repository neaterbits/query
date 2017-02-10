package com.neaterbits.query.sql.dsl.api;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

abstract class Collector_Conditions<MODEL, RESULT, AFTER_GROUP_BY>
	
	extends BaseQueryEntity<MODEL>

	implements ISharedLogical_Base<MODEL, RESULT>, ISharedCompileEndClause<MODEL> {

	final Collector_Clause clauseCollector;

	private Collector_GroupBy<MODEL, RESULT> groupByCollector;
	private int [] groupByColumns;

	private Collector_OrderBy<MODEL, RESULT> orderByCollector;
	private int [] orderByColumns;

	static Getter makeGetter(Function<?, ?> getter) {
		return new FunctionGetter(getter);
	}

	static Getter makeGetter(Supplier<?> getter) {
		return new SupplierGetter(getter);
	}

	
	Collector_Conditions(Collector_Conditions_Initial<MODEL, RESULT, AFTER_GROUP_BY> last, ConditionsType newConditionsType) {
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
	final <T extends ISharedLogical_Or<MODEL, RESULT>, IMPL extends Collector_Conditions<MODEL, RESULT, AFTER_GROUP_BY>>
		void addNestedOrImpl(Consumer<T> orBuilder, IMPL subOrImpl) {
		
		
		orBuilder.accept((T)subOrImpl);
		
		// add nested to super
		clauseCollector.add(new CollectedCondition_Nested(subOrImpl));
	}

	@SuppressWarnings("unchecked")
	final <T extends ISharedLogical_And<MODEL, RESULT>, IMPL extends Collector_Conditions<MODEL, RESULT, AFTER_GROUP_BY>>
	
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

		// Add group-by, order-by etc
		final Collected_GroupBy groupBy = makeCollectedFields(this.groupByCollector, this.groupByColumns,
															  Collected_GroupBy::new, Collected_GroupBy::new);
		
		final Collected_OrderBy orderBy = makeCollectedFields(this.orderByCollector, this.orderByColumns,
				
															collector -> new Collected_OrderBy(collector, collector.getSortOrders()), 
															indices -> new Collected_OrderBy(indices));
		
		if (groupBy != null || orderBy != null) {
			queryCollector.setResultProcessing(groupBy, orderBy);
		}
		
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
	
	private static <T extends Collector_Fields, R extends Collected_Fields> R makeCollectedFields(T collector, int [] indices, Function<T, R> ctor1, Function<int [], R> ctor2) {
		
		final R ret;
		
		if (collector != null && indices != null) {
			throw new IllegalArgumentException("Have both fields and indices");
		}
		else if (collector != null) {
			ret = ctor1.apply(collector);
		}
		else if (indices != null) {
			ret = ctor2.apply(indices);
		}
		else {
			ret = null;
		}

		return ret;
	}
	

	private void checkGroupByNotAlreadySet() {
		if (this.groupByCollector != null || groupByColumns != null) {
			throw new IllegalStateException("groupBy already set");
		}
	}


	// Overriden by interfaces further down in the hierarchy
	public final <T, R> ISharedProcessResult_After_GroupBy_Or_List_Named<MODEL, RESULT> groupBy(Function<T, R> field) {
		
		checkGroupByNotAlreadySet();
		
		this.groupByCollector = new Collector_GroupBy<>(new FunctionGetter(field), this);
		
		return groupByCollector;
	}

	public final <R> ISharedProcessResult_After_GroupBy_Or_List_Alias<MODEL, RESULT> groupBy(Supplier<R> field) {
		
		checkGroupByNotAlreadySet();
		
		this.groupByCollector = new Collector_GroupBy<>(new SupplierGetter(field), this);
		
		return groupByCollector;
	}
	
	@SuppressWarnings("unchecked")
	public final AFTER_GROUP_BY groupBy(int ... resultColumns) {

		checkGroupByNotAlreadySet();

		this.groupByColumns = Arrays.copyOf(resultColumns, resultColumns.length);
		
		return (AFTER_GROUP_BY)this;
	}

	public final ISharedProcessResult_OrderBy_Mapped_Named<MODEL, RESULT> having(int foo) {
		throw new UnsupportedOperationException("TODO");
	}

	public final ISharedProcessResult_OrderBy_Mapped_Alias<MODEL, RESULT> having(String bar) {
		throw new UnsupportedOperationException("TODO");
	}

	private void checkOrderByNotAlreadySet() {

		if (this.orderByCollector != null || orderByColumns != null) {
			throw new IllegalStateException("orderBy already set");
		}
	}
	

	public final <T, R> ISharedProcessResult_OrderBy_AfterSortOrder_Named<MODEL, RESULT> orderBy(Function<T, R> field) {

		checkOrderByNotAlreadySet();
		
		this.orderByCollector = new Collector_OrderBy<>(new FunctionGetter(field), this);
		
		return orderByCollector;
	}

	public final <R> ISharedProcessResult_OrderBy_AfterSortOrder_Alias<MODEL, RESULT> orderBy(Supplier<R> field) {

		checkOrderByNotAlreadySet();
		
		this.orderByCollector = new Collector_OrderBy<>(new SupplierGetter(field), this);
		
		return orderByCollector;
	}

	
	public final ISharedCompileEndClause<MODEL> orderBy(int ... resultColumns) {

		checkOrderByNotAlreadySet();
		
		this.orderByColumns = Arrays.copyOf(resultColumns, resultColumns.length);
		
		return this;
	}
}
