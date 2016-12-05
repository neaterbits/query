package com.neaterbits.query.sql.dsl.api;

/**
 * Contains the main collected code of a query
 *
 */

final class QueryCollectorImpl {

	private final QueryResultMode resultMode;
	
	// The expected result type
	private final Class<?> resultType;
	
	// Optional mappings to map result to result type
	private MappingCollector mappings;
	
	// Select sources (tables or aliases)
	private SelectSourceImpl sources;

	// Select clauses
	private ClauseCollectorImpl clauses;
	
	QueryCollectorImpl(QueryResultMode resultMode, Class<?> resultType) {

		if (resultMode == null) {
			throw new IllegalArgumentException("resultMode == null");
		}

		if (resultType == null) {
			throw new IllegalArgumentException("resultType == null");
		}

		this.resultMode = resultMode;
		this.resultType = resultType;
	}

	QueryCollectorImpl(QueryResultMode resultMode, Class<?> resultType, MappingCollector mappings,
			SelectSourceImpl sources, ClauseCollectorImpl clauses, boolean isMulti) {
		
		if (resultMode == null) {
			throw new IllegalArgumentException("resultMode == null");
		}

		if (resultType == null) {
			throw new IllegalArgumentException("resultType == null");
		}
		
		if (sources == null) {
			throw new IllegalArgumentException("sources == null");
		}
		
		if (clauses == null) {
			throw new IllegalArgumentException("clauses == null");
		}

		this.resultMode = resultMode;
		this.resultType = resultType;
		this.mappings = mappings;
		this.sources = sources;
		this.clauses = clauses;
	}

	
	QueryResultMode getResultMode() {
		return resultMode;
	}
	
	
	Class<?> getResultType() {
		return resultType;
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
