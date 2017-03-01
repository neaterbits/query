package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

/**
 * Contains the main collected code of a query
 *
 */

class QueryCollectorImpl<MODEL> extends Collector_Query<MODEL> {
	
	// The expected result type
	private CollectedQueryResult result;
	
	// Optional mappings to map result to result type
	private MappingCollector mappings;
	
	// Select sources (tables or aliases)
	private CollectedSelectSource sources;

	private Collector_Joins joins;

	// Select clauses
	private Collector_Clause clauses;
	
	/*
	private Collected_GroupBy groupBy;
	private Collected_OrderBy orderBy;
	*/
	
	
	private Collector_GroupBy<MODEL, ?> groupByCollector;

	// moved to groupByCollector for common collection of 'having' clause  
	//private int [] groupByColumns;

	private Collector_OrderBy<MODEL, ?> orderByCollector;
	private int [] orderByColumns;
	
	
	QueryCollectorImpl(ModelCompiler<MODEL> modelCompiler, CollectedQueryResult result) {
		super(modelCompiler);

		if (result != null) {
			setResult(result);
		}
	}
	
	@Override
	void setResult(CollectedQueryResult result) {
		if (result == null) {
			throw new IllegalArgumentException("result == null");
		}

		if (this.result != null) {
			throw new IllegalStateException("result already set");
		}

		this.result = result;
	}

	Class<?> getResultType() {
		return result.getType();
	}
	
	CollectedQueryResult getResult() {
		return result;
	}

	@Override
	MappingCollector getMappings() {
		return mappings;
	}

	
	@Override
	final void setMappings(MappingCollector mappings) {
		
		if (mappings == null) {
			throw new IllegalArgumentException("mappings == null");
		}
		
		if (this.mappings != null) {
			throw new IllegalStateException("mappings already set");
		}
		
		this.mappings = mappings;
	}

	CollectedSelectSource getSources() {
		return sources;
	}

	@Override
	void setSources(CollectedSelectSource sources) {
		
		if (sources == null) {
			throw new IllegalArgumentException("sources == null");
		}
		
		if (this.sources != null) {
			throw new IllegalStateException("sources already set");
		}
		
		this.sources = sources;
	}

	Collector_Joins getJoins() {
		return joins;
	}

	@Override
	void setJoins(Collector_Joins joins) {

		if (joins == null) {
			throw new IllegalArgumentException("joins == null");
		}

		if (this.joins != null) {
			throw new IllegalStateException("joins already set");
		}

		this.joins = joins;
	}

	@Override
	Collector_Clause getClauses() {
		return clauses;
	}

	@Override
	void setClauses(Collector_Clause clauses) {
		
		if (clauses == null) {
			throw new IllegalArgumentException("clauses == null");
		}
		
		if (this.clauses != null) {
			throw new IllegalStateException("clauses already set");
		}
		
		this.clauses = clauses;
	}
	
	@Override
	Collected_GroupBy getGroupBy() {
		final int [] groupByColumns = this.groupByCollector != null ? this.groupByCollector.getGroupByColumns() : null;
		
		
		// Add group-by, order-by etc
		final Collected_GroupBy groupBy = makeCollectedFields(this.groupByCollector, groupByColumns,
															  Collected_GroupBy::new, indices -> new Collected_GroupBy(indices, groupByCollector.getHaving()));
		
		return groupBy;
	}


	@Override
	Collected_OrderBy getOrderBy() {
		final Collected_OrderBy orderBy = makeCollectedFields(this.orderByCollector, this.orderByColumns,
				
				collector -> new Collected_OrderBy(collector, collector.getSortOrders()), 
				indices -> new Collected_OrderBy(indices));
		
		return orderBy;
	}


	@Override
	void setGroupBy(Collector_GroupBy<MODEL, ?> groupBy) {
		if (this.groupByCollector != null) {
			throw new IllegalStateException("groupBy alread set");
		}

		if (groupBy == null) {
			throw new IllegalArgumentException("groupBy == null");
		}
		
		this.groupByCollector = groupBy;
	}


	@Override
	void setOrderBy(Collector_OrderBy<MODEL, ?> orderBy) {
		checkOrderByNotAlreadySet();
		
		if (orderBy == null) {
			throw new IllegalArgumentException("orderBy == null");
		}
		
		this.orderByCollector = orderBy;
	}


	@Override
	void setOrderBy(int[] resultColumns) {
		// TODO Auto-generated method stub
		
		checkOrderByNotAlreadySet();
		
		if (resultColumns == null) {
			throw new IllegalArgumentException("resultColumns == null");
		}
		
		this.orderByColumns = resultColumns;
	}


	void verify(VerificationErrorCollector collector) {
		throw new UnsupportedOperationException("TODO");
	}
	
	private void checkOrderByNotAlreadySet() {

		if (this.orderByCollector != null || orderByColumns != null) {
			throw new IllegalStateException("orderBy already set");
		}
	}

	private static <T extends Collector_Fields<?>, R extends Collected_Fields> R makeCollectedFields(T collector, int [] indices, Function<T, R> ctor1, Function<int [], R> ctor2) {
		
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
}
