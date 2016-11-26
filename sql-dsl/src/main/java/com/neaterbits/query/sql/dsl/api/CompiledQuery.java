package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;

/**
 * Representation of query where we have compiled all information 
 *
 */

final class CompiledQuery {

	private final Class<?> resultType;
	private final CompiledConditions conditions;

	private CompiledQuery(Class<?> resultType, CompiledConditions conditions) {

		if (resultType == null) {
			throw new IllegalArgumentException("resultType == null");
		}

		this.resultType = resultType;
		
		// may be null if no conditions
		this.conditions = conditions;
	}
	
	static CompiledQuery compile(QueryCollectorImpl collector) {
		if (collector == null) {
			throw new IllegalArgumentException("collector == null");
		}
		
		final CompiledConditions compiledConditions;
		
		// Check all clauses etc
		if (collector.getClauses() != null) {
			compiledConditions = compileConditions(collector.getClauses());
		}
		else {
			compiledConditions = null;
		}

		return new CompiledQuery(collector.getResultType(), compiledConditions);
	}
	
	private static CompiledConditions compileConditions(ClauseCollectorImpl clauses) {

		final List<ClauseImpl> list = clauses.getClauses();
		
		if (list.isEmpty()) {
			throw new IllegalStateException("no clauses");
		}

		if (!(list.get(0).getClause() instanceof WhereClauseBuilderImpl<?, ?>)) {
			throw new IllegalStateException("first entry is not a whereclause");
		}
		
		final CompiledConditions ret;
		
		final int num = list.size();
		
		if (num == 1) {
			ret = new CompiledConditionsSingle(list.get(0).getCondition());
		}
		else {
			final Class<?> clauseClass = list.get(1).getClause().getClass();
			
			final List<ConditionImpl> conditions = new ArrayList<ConditionImpl>(num);
			
			conditions.add(list.get(0).getCondition());
			
			for (int i = 1; i < num; ++ i) {
				
				final ClauseImpl clause = list.get(i);
				
				final Class<?> otherClauseClass = clause.getClause().getClass();
				
				if (!clauseClass.equals(otherClauseClass)) {
					throw new IllegalStateException("class mismatch");
				}
				
				conditions.add(clause.getCondition());
			}
			
			if (clauseClass.equals(AndClausesImpl.class)) {
				ret = new CompiledConditionsAnd(conditions);
			}
			else if (clauseClass.equals(OrClausesImpl.class)) {
				ret = new CompiledConditionsOr(conditions);
			}
			else {
				throw new IllegalStateException("Unknown clause class " + clauseClass);
			}
		}

		return ret;
	}
}
