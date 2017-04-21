package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Contains the main collected code of a query
 *
 */

class QueryCollectorImpl<MODEL> extends Collector_Query<MODEL> {
	

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

	QueryCollectorImpl(Collector_Query<MODEL> queryCollector, CollectedQueryResult result) {
		super(queryCollector, result);
		
		if (queryCollector.getSources() != null) {
			this.sources = queryCollector.getSources();
		}
		
		if (queryCollector.getJoins() != null) {
			this.joins = queryCollector.getJoins();
		}
		
		if (queryCollector.getMappings() != null) {
			this.mappings = queryCollector.getMappings();
		}

		if (queryCollector.getClauses() != null) {
			throw new IllegalStateException("clauses already set in source");
		}
		
		if (queryCollector.getGroupBy() != null) {
			throw new IllegalStateException("groupBy already set in source");
		}
		
		if (queryCollector.getOrderBy() != null) {
			throw new IllegalStateException("orderBy already set in source");
		}
	}

	
	QueryCollectorImpl(BaseQuery baseQuery, ModelCompiler<MODEL> modelCompiler, CollectedQueryResult result) {
		super(baseQuery, modelCompiler, result);
		
		/*
		this.result = result;

		if (result != null) {
			setResult(result);
		}
		*/
	}
	
	/*
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
	*/

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
	ICollectorClause addConditionClauses(EConditionsClause type) {
		if (type == null) {
			throw new IllegalArgumentException("type == null");
		}
		
		if (type != EConditionsClause.WHERE) {
			throw new IllegalArgumentException("Expected WHERE: " + type);
		}

		if (this.clauses != null) {
			throw new IllegalStateException("clauses already set");
		}

		this.clauses = new Collector_Clause(type, ConditionsType.SINGLE);

		return clauses;
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


	@Override
	public String toString() {
		return "QueryCollectorImpl [result=" + getResult() + ", mappings=" + mappings + ", sources=" + sources + ", joins="
				+ joins + ", clauses=" + clauses + ", groupByCollector=" + groupByCollector + ", orderByCollector="
				+ orderByCollector + ", orderByColumns=" + Arrays.toString(orderByColumns) + "]";
	}

	/**
	 * Collects clauses in a query
	 *
	 */

	private static final class Collector_Clause implements ICollectorClause {
		
	    private final EConditionsClause conditionsClause;		
		private final ConditionsType conditionsType;
		private final List<CollectedCondition> conditions;

		Collector_Clause(Collector_Clause whereClauses, ConditionsType newConditionsType) {
			
			
			if (newConditionsType != ConditionsType.AND && newConditionsType != ConditionsType.OR) {
				throw new IllegalArgumentException("expected AND or OR conditions type: " + newConditionsType);
			}
			
			if (whereClauses.conditionsType != ConditionsType.SINGLE) {
				throw new IllegalArgumentException("Expected where clause");
			}
			
			if (whereClauses.conditions.size() != 1) {
				throw new IllegalArgumentException("expected exactly one condition collected, got " + whereClauses.conditions.size());
			}

			this.conditionsClause = whereClauses.conditionsClause;
			this.conditionsType = newConditionsType;
			this.conditions = new ArrayList<>(whereClauses.conditions); // keep condition from WHERE clause
		}

		Collector_Clause(EConditionsClause conditionsClause, ConditionsType conditionsType) {
			
			if (conditionsClause == null) {
				throw new IllegalArgumentException("conditionsClause == null");
			}

			if (conditionsType == null) {
				throw new IllegalArgumentException("conditionsType == null");
			}

			this.conditionsClause = conditionsClause;
			this.conditionsType = conditionsType;
			this.conditions = new ArrayList<CollectedCondition>();
		}
		
		
		@Override
		public boolean isEmpty() {
			return conditions.isEmpty();
		}
		
		@Override
		public EConditionsClause getConditionsClause() {
			return conditionsClause;
		}

		@Override
		public ConditionsType getConditionsType() {
			return conditionsType;
		}


		@Override
		public List<CollectedCondition> getConditions() {
			return conditions;
		}
		
		@Override
		public ICollectorClause addConditionsType(ConditionsType conditionsType) {
			throw new UnsupportedOperationException("TODO");
		}

		@Override
		public void add(CollectedCondition_Nested nested) {
			addCollectedCondition(nested);
		}

		@Override
		public void add(CollectedCondition_NonNested nonNested) {
			addCollectedCondition(nonNested);
		}

		private void addCollectedCondition(CollectedCondition condition) {
			
			if (conditionsType == ConditionsType.SINGLE && !conditions.isEmpty()) {
				throw new IllegalStateException("Trying to add to SINGLE-condition, should create new one");
			}
			

			if (condition == null) {
				throw new IllegalArgumentException("condition == null");
			}

			conditions.add(condition);
		}
	}
}
