package com.neaterbits.query.sql.dsl.api;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

abstract class Collector_Conditions_GroupBy<MODEL, RESULT, AFTER_GROUP_BY>
	
	extends Collector_Conditions_Base<MODEL, RESULT>

	implements ISharedLogical_Base<MODEL, RESULT>, ISharedCompileEndClause<MODEL> {

	
	/* 
	 * Group by and order by handled in baseclass, even if not applicable to all subclasses (eg. nested conditions and having)
	 * This is for simplicity, uses cannot set these for nested instances anyway since they do not implement those
	 * interfaces within the subclasses.
	 * 
	 *  
	 */

	static Getter makeGetter(Function<?, ?> getter) {
		return new FunctionGetter(getter);
	}

	static Getter makeGetter(Supplier<?> getter) {
		return new SupplierGetter(getter);
	}

	abstract Collector_GroupBy<MODEL, RESULT> createGroupByCollector(Collector_Base<MODEL> last, int [] groupByColumns, Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions);
	
	Collector_Conditions_GroupBy(Collector_Conditions_Initial<MODEL, RESULT, AFTER_GROUP_BY> last, ConditionsType newConditionsType) {
		super(last, newConditionsType);
	}

	Collector_Conditions_GroupBy(Collector_Base<MODEL> last, Collector_Clause collector) {
		super(last, collector);
	}
	
	Collector_Conditions_GroupBy(Collector_Query<MODEL> queryCollector, Collector_Clause collector) {
		super(queryCollector, collector);
	}

	@SuppressWarnings("unchecked")
	final <T extends ISharedLogical_Or<MODEL, RESULT>, IMPL extends Collector_Conditions_GroupBy<MODEL, RESULT, AFTER_GROUP_BY>>
		void addNestedOrImpl(Consumer<T> orBuilder, IMPL subOrImpl) {
		
		
		orBuilder.accept((T)subOrImpl);
		
		// add nested to super
		clauseCollector.add(new CollectedCondition_Nested(subOrImpl));
	}

	@SuppressWarnings("unchecked")
	final <T extends ISharedLogical_And<MODEL, RESULT>, IMPL extends Collector_Conditions_GroupBy<MODEL, RESULT, AFTER_GROUP_BY>>
	
		void addNestedAndImpl(Consumer<T> orBuilder, IMPL subAndImpl) {
		
		orBuilder.accept((T)subAndImpl);
		
		// add nested to super
		clauseCollector.add(new CollectedCondition_Nested(subAndImpl));
	}
	
	
	@Override
	public final MODEL compile() {
		
		// Get collected query
		final Collector_Query<MODEL> queryCollector = getQueryCollector();
		
		// Now set clauses before compiling
		
		if (clauseCollector.getConditionsClause() == EConditionsClause.WHERE) {
			queryCollector.setClauses(clauseCollector);
		}

		// Compile the collected query
		CompiledQuery compiledQuery;
		try {
			compiledQuery = CompiledQuery.compile(queryCollector);
		} catch (CompileException ex) {
			throw new IllegalStateException("Failed to compile", ex);
		}
		
		// Compile into model (better name for this operation?)
		return queryCollector.getModelCompiler().compile(compiledQuery);
	}
	
	

	// Overriden by interfaces further down in the hierarchy
	public final <T, R> ISharedProcessResult_After_GroupBy_Or_List_Named<MODEL, RESULT> groupBy(Function<T, R> field) {
		
		final Collector_GroupBy_Named<MODEL, RESULT> groupByCollector = new Collector_GroupBy_Named<>(this, new FunctionGetter(field), this);
		
		getQueryCollector().setGroupBy(groupByCollector);
		
		return (ISharedProcessResult_After_GroupBy_Or_List_Named<MODEL, RESULT>)groupByCollector;
	}

	public final <R> ISharedProcessResult_After_GroupBy_Or_List_Alias<MODEL, RESULT> groupBy(Supplier<R> field) {
		
		final Collector_GroupBy_Alias<MODEL, RESULT> groupByCollector = new Collector_GroupBy_Alias<>(this, new SupplierGetter(field), this);
		
		getQueryCollector().setGroupBy(groupByCollector);
		
		return (ISharedProcessResult_After_GroupBy_Or_List_Alias<MODEL, RESULT>)groupByCollector;
	}
	
	@SuppressWarnings("unchecked")
	public final AFTER_GROUP_BY groupBy(int ... resultColumns) {

		final Collector_GroupBy<MODEL, RESULT> groupByCollector = createGroupByCollector(this, Arrays.copyOf(resultColumns, resultColumns.length), this);;

		getQueryCollector().setGroupBy(groupByCollector);

		return (AFTER_GROUP_BY)this;
	}

	

	public final <T, R> ISharedProcessResult_OrderBy_AfterSortOrder_Named<MODEL, RESULT> orderBy(Function<T, R> field) {

		final Collector_OrderBy<MODEL, RESULT> orderByCollector = new Collector_OrderBy<>(this, new FunctionGetter(field), this);
		
		getQueryCollector().setOrderBy(orderByCollector);
		
		return orderByCollector;
	}

	public final <R> ISharedProcessResult_OrderBy_AfterSortOrder_Alias<MODEL, RESULT> orderBy(Supplier<R> field) {

		final Collector_OrderBy<MODEL, RESULT> orderByCollector = new Collector_OrderBy<>(this, new SupplierGetter(field), this);
		
		getQueryCollector().setOrderBy(orderByCollector);

		return orderByCollector;
	}

	
	public final ISharedCompileEndClause<MODEL> orderBy(int ... resultColumns) {

		
		
		final int [] orderByColumns = Arrays.copyOf(resultColumns, resultColumns.length);
		
		getQueryCollector().setOrderBy(orderByColumns);
		
		
		return this;
	}
}