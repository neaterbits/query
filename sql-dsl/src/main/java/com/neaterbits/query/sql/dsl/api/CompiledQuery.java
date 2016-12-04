package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;

import com.neaterbits.query.util.java8.Coll8;

/**
 * Representation of query where we have compiled all information 
 *
 */	

final class CompiledQuery {

	private final Class<?> resultType;
	
	private final CompiledMappings mappings;
	private final CompiledSelectSources<?> selectSources;
	
	private final CompiledConditions conditions;

	private CompiledQuery(
			Class<?> resultType,
			CompiledMappings mappings,
			CompiledSelectSources<?> selectSources,
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

	@SuppressWarnings("unchecked")
	CompiledSelectSources<CompiledSelectSource> getSelectSources() {
		return (CompiledSelectSources<CompiledSelectSource>)selectSources;
	}

	static CompiledQuery compile(QueryCollectorImpl collector) throws CompileException {
		if (collector == null) {
			throw new IllegalArgumentException("collector == null");
		}
		
		final Class<?> resultType = collector.getResultType();
		final CompiledMappings compiledMappings;
		
		final CompiledGetterSetterCache cache = new CompiledGetterSetterCache();

		final SelectSourceImpl sources = collector.getSources();

		final CompiledSelectSources<?> compiledSources = compileSelectSources(sources);
		
		if (collector.getMappings() != null) {
			compiledMappings = compileMappings(resultType, collector.getMappings(), sources, compiledSources, cache);
		}
		else {
			compiledMappings = null;
		}

		final CompiledConditions compiledConditions;
		
		// Check all clauses etc
		if (collector.getClauses() != null) {
			compiledConditions = compileConditions(collector.getClauses(), compiledSources, cache);
		}
		else {
			compiledConditions = null;
		}

		return new CompiledQuery(
				resultType,
				compiledMappings,
				compiledSources,
				compiledConditions);
	}

	private static CompiledMappings compileMappings(
			Class<?> resultType,
			MappingCollector mappingCollector,
			SelectSourceImpl selectSource,
			CompiledSelectSources<?> compiledSelectSources,
			CompiledGetterSetterCache cache) throws CompileException {

		final List<CollectedMapping> collectedMappings = mappingCollector.getCollectedMappings();
		final List<CompiledMapping> compiledMappings = new ArrayList<>(collectedMappings.size());

		for (CollectedMapping collected : collectedMappings) {

			final CompiledMapping compiled = compileMapping(
					resultType,
					collected,
					selectSource,
					compiledSelectSources,
					cache);

			compiledMappings.add(compiled);
		}

		return new CompiledMappings(compiledMappings);
	}

	private static CompiledSelectSources<?> compileSelectSources(SelectSourceImpl sources) {

		final CompiledSelectSources<?> compiled;

		// Figure out the type of select source
		if (sources instanceof SelectSourceClassesImpl) {
			final List<CompiledSelectSourceClass> compiledList = new ArrayList<>();
			
			// table names
			final SelectSourceClassesImpl selectSourceClasses = (SelectSourceClassesImpl)sources;

			for (Class<?> cl : selectSourceClasses.getClasses()) {
				final String name = cl.getName().toLowerCase();

				if (Coll8.has(compiledList, e -> e.getName().equals(name))) {
					throw new IllegalStateException("Two entity classes with same lowercase name \"" + name + "\"");
				}

				final CompiledSelectSourceClass c = new CompiledSelectSourceClass(selectSourceClasses, cl, name);

				compiledList.add(c);
			}

			compiled = new CompiledSelectSourcesClass(sources, compiledList);
		}
		else if (sources instanceof SelectSourceAliasesImpl) {
			final List<CompiledSelectSourceAlias> compiledList = new ArrayList<>();

			final SelectSourceAliasesImpl selectSourceAliases = (SelectSourceAliasesImpl)sources;

			int aliasNo = 0;
			
			for (IAlias alias : selectSourceAliases.getAliases()) {
				final CompiledSelectSourceAlias c = new CompiledSelectSourceAlias(selectSourceAliases, alias, "al" + aliasNo);

				compiledList.add(c);

				++ aliasNo;
			}

			compiled = new CompiledSelectSourcesAlias(sources, compiledList);
		}
		else {
			throw new IllegalArgumentException("Unknown select sources type " + sources);
		}

		return compiled;
	}

	private static CompiledMapping compileMapping(
			Class<?> resultType,
			CollectedMapping collected,
			SelectSourceImpl selectSource,
			CompiledSelectSources<?> compiledSelectSources,
			CompiledGetterSetterCache cache) throws CompileException {

		final CompiledSetter mapSetter = cache.compileSetterUntyped(resultType, collected.getSetter());
		
		if (mapSetter == null) {
			throw new CompileException("Unknown mapping setter " + collected);
		}
		
		final CompiledFieldReference fieldReference = compiledSelectSources.makeFieldReference(
				collected,
				collected.getGetter(),
				cache);

		return new CompiledMapping(fieldReference, mapSetter);
	}
	
	private static CompiledConditions compileConditions(ClauseCollectorImpl clauses, CompiledSelectSources<?> sources, CompiledGetterSetterCache cache)
		throws CompileException {

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
			
			final CompiledCondition singleCondition = compileCondition(list.get(0).getCondition(), sources, cache);
			
			ret = new CompiledConditionsSingle(singleCondition);
		}
		else {
			final Class<?> clauseClass = list.get(1).getClause().getClass();
			final List<CompiledCondition> conditions = new ArrayList<>(num);

			
			conditions.add(compileCondition(list.get(0).getCondition(), sources, cache));

			for (int i = 1; i < num; ++ i) {

				final ClauseImpl clause = list.get(i);
				final Class<?> otherClauseClass = clause.getClause().getClass();

				if (!clauseClass.equals(otherClauseClass)) {
					throw new IllegalStateException("class mismatch: " + clauseClass.getSimpleName() + "/" + otherClauseClass.getSimpleName());
				}

				conditions.add(compileCondition(clause.getCondition(), sources, cache));
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

	private static CompiledCondition compileCondition(ConditionImpl condition, CompiledSelectSources<?> sources, CompiledGetterSetterCache cache) throws CompileException {
		
		if (condition == null) {
			throw new IllegalArgumentException("condition == null");
		}
		
		final CompiledCondition ret = new CompiledCondition(condition, sources.makeFieldReference(condition, condition.getGetter(), cache));
		
		return ret;
	}
}
