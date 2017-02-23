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

	private Collector_Joins joins;

	// Select clauses
	private Collector_Clause clauses;
	
	private Collected_GroupBy groupBy;
	private Collected_OrderBy orderBy;
	
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

	Collector_Joins getJoins() {
		return joins;
	}

	void setJoins(Collector_Joins joins) {

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
	
	void setResultProcessing(Collected_GroupBy groupBy, Collected_OrderBy orderBy) {
		this.groupBy = groupBy;
		this.orderBy = orderBy;
	}

	Collected_GroupBy getGroupBy() {
		return groupBy;
	}

	/*
	void setGroupBy(Collected_GroupBy groupBy) {
		this.groupBy = groupBy;
	}
	*/

	public Collected_OrderBy getOrderBy() {
		return orderBy;
	}


	/*
	public void setOrderBy(Collected_OrderBy orderBy) {
		this.orderBy = orderBy;
	}
	*/


	void verify(VerificationErrorCollector collector) {
		throw new UnsupportedOperationException("TODO");
	}
}
