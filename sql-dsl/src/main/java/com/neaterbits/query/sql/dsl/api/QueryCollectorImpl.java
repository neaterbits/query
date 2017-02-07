package com.neaterbits.query.sql.dsl.api;

/**
 * Contains the main collected code of a query
 *
 */

final class QueryCollectorImpl {

	
	// The expected result type
	private final CollectedQueryResult result;
	
	// Optional mappings to map result to result type
	private MappingCollector mappings;
	
	// Select sources (tables or aliases)
	private CollectedSelectSource sources;

	private JoinCollector joins;

	// Select clauses
	private Collector_Clause clauses;
	
	private Collected_Fields groupBy;
	private Collected_Fields orderBy;
	
	QueryCollectorImpl(CollectedQueryResult result) {

		if (result == null) {
			throw new IllegalArgumentException("result == null");
		}

		this.result = result;
	}
	
	
	Class<?> getResultType() {
		return result.getType();
	}
	
	CollectedQueryResult getResult() {
		return result;
	}

	MappingCollector getMappings() {
		return mappings;
	}

	void setMappings(MappingCollector mappings) {
		
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

	void setSources(CollectedSelectSource sources) {
		
		if (sources == null) {
			throw new IllegalArgumentException("sources == null");
		}
		
		if (this.sources != null) {
			throw new IllegalStateException("sources already set");
		}
		
		this.sources = sources;
	}

	JoinCollector getJoins() {
		return joins;
	}

	void setJoins(JoinCollector joins) {

		if (joins == null) {
			throw new IllegalArgumentException("joins == null");
		}

		if (this.joins != null) {
			throw new IllegalStateException("joins already set");
		}

		this.joins = joins;
	}

	Collector_Clause getClauses() {
		return clauses;
	}

	void setClauses(Collector_Clause clauses) {
		
		if (clauses == null) {
			throw new IllegalArgumentException("clauses == null");
		}
		
		if (this.clauses != null) {
			throw new IllegalStateException("clauses already set");
		}
		
		this.clauses = clauses;
	}
	
	void setResultProcessing(Collected_Fields groupBy, Collected_Fields orderBy) {
		this.groupBy = groupBy;
		this.orderBy = orderBy;
	}

	Collected_Fields getGroupBy() {
		return groupBy;
	}

	void setGroupBy(Collected_Fields groupBy) {
		this.groupBy = groupBy;
	}

	public Collected_Fields getOrderBy() {
		return orderBy;
	}


	public void setOrderBy(Collected_Fields orderBy) {
		this.orderBy = orderBy;
	}


	void verify(VerificationErrorCollector collector) {
		throw new UnsupportedOperationException("TODO");
	}
}
