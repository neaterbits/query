package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;

/**
 * Representation of query where we have compiled all information 
 *
 */

final class CompiledQuery {

	private final Class<?> resultType;
	
	
	private final CompiledMappings mappings;
	private final SelectSourceImpl selectSources; // Types in the "from" part of query
	
	private final CompiledConditions conditions;

	private CompiledQuery(
			Class<?> resultType,
			CompiledMappings mappings,
			SelectSourceImpl selectSources,
			CompiledConditions conditions) {

		if (resultType == null) {
			throw new IllegalArgumentException("resultType == null");
		}
		
		if (selectSources == null) {
			throw new IllegalArgumentException("selectSources == null");
		}

		this.resultType = resultType;
		this.mappings = mappings;
		this.selectSources = selectSources;
		
		// may be null if no conditions
		this.conditions = conditions;
	}

	Class<?> getResultType() {
		return resultType;
	}

	CompiledMappings getMappings() {
		return mappings;
	}

	CompiledConditions getConditions() {
		return conditions;
	}
	
	SelectSources getSelectSources() {
		return selectSources;
	}

	static CompiledQuery compile(QueryCollectorImpl collector) throws CompileException {
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
		
		final SelectSourceImpl sources = collector.getSources();
		
		final Class<?> resultType = collector.getResultType();
		final CompiledMappings compiledMappings;
		
		final CompiledGetterSetterCache cache = new CompiledGetterSetterCache();
		
		if (collector.getMappings() != null) {
			compiledMappings = compileMappings(resultType, collector.getMappings(), sources, cache);
		}
		else {
			compiledMappings = null;
		}

		return new CompiledQuery(
				resultType,
				compiledMappings,
				collector.getSources(),
				compiledConditions);
	}

	private static CompiledMappings compileMappings(
			Class<?> resultType,
			MappingCollector mappingCollector,
			SelectSourceImpl selectSource,
			CompiledGetterSetterCache cache) throws CompileException {

		final List<CollectedMapping> collectedMappings = mappingCollector.getCollectedMappings();
		final List<CompiledMapping> compiledMappings = new ArrayList<>(collectedMappings.size());

		for (CollectedMapping collected : collectedMappings) {
			final CompiledMapping compiled = compileMapping(resultType, collected, selectSource, cache);

			compiledMappings.add(compiled);
		}

		return new CompiledMappings(compiledMappings);
	}

	private static CompiledMapping compileMapping(
			Class<?> resultType,
			CollectedMapping collected,
			SelectSourceImpl selectSource,
			CompiledGetterSetterCache cache) throws CompileException {

		final CompiledGetter mapGetter = selectSource.compileGetter(collected, cache);
		final CompiledSetter mapSetter = cache.compileSetterUntyped(resultType, collected.getSetter());
		
		if (mapSetter == null) {
			throw new CompileException("Unknown mapping setter " + collected);
		}

		return new CompiledMapping(mapGetter, mapSetter);
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
					throw new IllegalStateException("class mismatch: " + clauseClass.getSimpleName() + "/" + otherClauseClass.getSimpleName());
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
				throw new IllegalStateException("Unknown clause class "
					    + clauseClass.getSimpleName()
						+ ", at 0 = " + list.get(0).getClause().getClass().getSimpleName());
			}
		}

		return ret;
	}
}
