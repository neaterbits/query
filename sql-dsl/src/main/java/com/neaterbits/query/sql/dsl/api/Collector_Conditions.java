package com.neaterbits.query.sql.dsl.api;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

abstract class Collector_Conditions<MODEL, RESULT>
	
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

	
	Collector_Conditions(Collector_Conditions_Initial<MODEL, RESULT> last, ConditionsType newConditionsType) {
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
	final <T extends ISharedLogical_Or<MODEL, RESULT>, IMPL extends Collector_Conditions<MODEL, RESULT>>
		void addNestedOrImpl(Consumer<T> orBuilder, IMPL subOrImpl) {
		
		
		orBuilder.accept((T)subOrImpl);
		
		// add nested to super
		clauseCollector.add(new CollectedCondition_Nested(subOrImpl));
	}

	@SuppressWarnings("unchecked")
	final <T extends ISharedLogical_And<MODEL, RESULT>, IMPL extends Collector_Conditions<MODEL, RESULT>>
	
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
		
		if (this.groupByCollector == null || groupByColumns != null) {
			throw new IllegalStateException("groupBy already set");
		}
		
		this.groupByCollector = new Collector_GroupBy<>(field, this);;
		
		return groupByCollector;
	}

	@SuppressWarnings("unchecked")
	public final ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT> groupBy(int ... resultColumns) {
		if (this.groupByCollector == null || groupByColumns != null) {
			throw new IllegalStateException("groupBy already set");
		}

		this.groupByColumns = Arrays.copyOf(resultColumns, resultColumns.length);
		
		return (ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT>)this;
	}

	public final ISharedProcessResult_OrderBy_Named<MODEL, RESULT> having() {
		throw new UnsupportedOperationException("TODO");
	}
	
	public final <T, R> ISharedProcessResult_After_OrderBy_Or_List_Named<MODEL, RESULT> orderBy(Function<T, R> field) {
		if (this.orderByCollector == null || orderByColumns != null) {
			throw new IllegalStateException("orderBy already set");
		}
		
		this.orderByCollector = new Collector_OrderBy<>(field, this);;
		
		return orderByCollector;
	}

	
	public final ISharedCompileEndClause<MODEL> orderBy(int ... resultColumns) {
		if (this.orderByCollector == null || orderByColumns != null) {
			throw new IllegalStateException("oderBy already set");
		}

		this.orderByColumns = Arrays.copyOf(resultColumns, resultColumns.length);
		
		return this;
	}
}
