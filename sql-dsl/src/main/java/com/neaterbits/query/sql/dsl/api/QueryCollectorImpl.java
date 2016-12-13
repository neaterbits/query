package com.neaterbits.query.sql.dsl.api;

/**
 * Contains the main collected code of a query
 *
 */

final class QueryCollectorImpl {

	
	// The expected result type
	private final QueryResult result;
	
	// Optional mappings to map result to result type
	private MappingCollector mappings;
	
	// Select sources (tables or aliases)
	private SelectSourceImpl sources;

	private JoinCollector joins;

	// Select clauses
	private ClauseCollectorImpl clauses;
	
	QueryCollectorImpl(QueryResult result) {

		if (result == null) {
			throw new IllegalArgumentException("result == null");
		}

		this.result = result;
	}

	QueryCollectorImpl(QueryResult result, MappingCollector mappings,
			SelectSourceImpl sources,
			ClauseCollectorImpl clauses, boolean isMulti) {
		

		if (result == null) {
			throw new IllegalArgumentException("result == null");
		}
		
		if (sources == null) {
			throw new IllegalArgumentException("sources == null");
		}
		
		if (clauses == null) {
			throw new IllegalArgumentException("clauses == null");
		}

		this.result = result;
		this.mappings = mappings;
		this.sources = sources;
		this.clauses = clauses;
	}

	
	/*
	QueryResultMode getResultMode() {
		return result.getMode();
	}
	*/
	
	
	Class<?> getResultType() {
		return result.getType();
	}
	
	QueryResult getResult() {
		return result;
	}

	

//	void setResultType(Class<?> resultType) {
//		
//		if (resultType == null) {
//			throw new IllegalArgumentException("resultType == null");
//		}
//		
//		if (this.resultType != null) {
//			throw new IllegalStateException("resultType already set");
//		}
//		
//		this.resultType = resultType;
//	}

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

	SelectSourceImpl getSources() {
		return sources;
	}

	void setSources(SelectSourceImpl sources) {
		
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

	ClauseCollectorImpl getClauses() {
		return clauses;
	}

	void setClauses(ClauseCollectorImpl clauses) {
		
		if (clauses == null) {
			throw new IllegalArgumentException("clauses == null");
		}
		
		if (this.clauses != null) {
			throw new IllegalStateException("clauses already set");
		}
		
		this.clauses = clauses;
	}
	
	

	void verify(VerificationErrorCollector collector) {
		throw new UnsupportedOperationException("TODO");
	}
}
