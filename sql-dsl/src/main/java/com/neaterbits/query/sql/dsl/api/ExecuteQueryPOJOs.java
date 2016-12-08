package com.neaterbits.query.sql.dsl.api;

import java.util.List;

/**
 * Execute a query on a set of POJS
 * 
 * @author nhl
 *
 */

final class ExecuteQueryPOJOs {

	// TODO: How to emit mapped? Must allocate a result type? Pass inn multiple matches?
	// we must find all joining instances. 
	
	interface QueryResultEmitter {
		
		void emit();
	}
	
	void execute(CompiledQuery query) {

		final CompiledQueryResult queryResult = query.getResult();
		
		queryResult.visit(resultVisitor, null);
		
		// Loop over all data in collections
		// in order to execute query
		// base on clauses
		// must figure out the type of result first

		final QueryResultDimension resultMode = queryResult.getOriginal().getDimension();
		
		switch (resultMode) {
		
		case SINGLE:
			break;
			
		case MULTI:
			
		default:
			throw new UnsupportedOperationException("Unknown query result mode " + resultMode);
		}

		// Loop over all clauses
		final CompiledConditions conditions = query.getConditions();
		
	}

	private static void doSingle(CompiledCondition condition) {
		
	}

	// Do an AND over all instances
	private static void doAnd(List<CompiledCondition> conditions) {
		
	}
	
	// Do an OR over all instances
	private static void doOr() {
		
	}

	
	private static final CompiledQueryResultVisitor<Void, Object> resultVisitor = new CompiledQueryResultVisitor<Void, Object>() {
		
		@Override
		public Object onMapped(CompiledQueryResultMapped result, Void param) {
			throw new UnsupportedOperationException("TODO");
		}
		
		@Override
		public Object onEntity(CompiledQueryResultEntity result, Void param) {
			throw new UnsupportedOperationException("TODO");
		}
		
		@Override
		public Object onAggregate(CompiledQueryResultAggregate result, Void param) {
			throw new UnsupportedOperationException("TODO");
		}
	};
}
