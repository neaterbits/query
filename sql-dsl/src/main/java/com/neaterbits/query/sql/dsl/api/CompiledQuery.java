package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;

import com.neaterbits.query.util.java8.Coll8;

/**
 * Representation of query where we have compiled all information 
 *
 */	

final class CompiledQuery {

	private final CompiledQueryResult result;

	private final CompiledSelectSources<?> selectSources;
	
	private final CompiledJoins joins;
	
	private final CompiledConditions conditions;

	private CompiledQuery(
			CompiledQueryResult result,
			CompiledSelectSources<?> selectSources,
			CompiledJoins joins,
			CompiledConditions conditions) {

		if (result == null) {
			throw new IllegalArgumentException("result == null");
		}
		
		if (selectSources == null) {
			throw new IllegalArgumentException("selectSources == null");
		}

		this.result = result;
		this.selectSources = selectSources;
		
		// may be null if no conditions
		this.conditions = conditions;
		
		this.joins = joins;
	}

	QueryResultDimension getResultMode() {
		return result.getOriginal().getDimension();
	}
	
	QueryResultGathering getGathering() {
		return result.getOriginal().getGathering();
	}

	CompiledQueryResult getResult() {
		return result;
	}

	Class<?> getResultType() {
		return result.getOriginal().getType();
	}

	CompiledConditions getConditions() {
		return conditions;
	}

	@SuppressWarnings("unchecked")
	CompiledSelectSources<CompiledSelectSource> getSelectSources() {
		return (CompiledSelectSources<CompiledSelectSource>)selectSources;
	}

	public CompiledJoins getJoins() {
		return joins;
	}

	static CompiledQuery compile(QueryCollectorImpl collector) throws CompileException {
		if (collector == null) {
			throw new IllegalArgumentException("collector == null");
		}
		
		final CompiledGetterSetterCache cache = new CompiledGetterSetterCache();
		final SelectSourceImpl sources = collector.getSources();

		final CompiledSelectSources<?> compiledSources = compileSelectSources(sources);
		final CompiledQueryResult compiledQueryResult = compileQueryResult(collector, compiledSources, cache);

		final CompiledConditions compiledConditions;
		
		// Check all clauses etc
		if (collector.getClauses() != null && !collector.getClauses().getClauses().isEmpty()) {
			compiledConditions = compileConditions(collector.getClauses(), compiledSources, cache);
		}
		else {
			compiledConditions = null;
		}
		
		final CompiledJoins joins;
		
		if (collector.getJoins() != null) {
			joins = compileJoins(collector.getJoins(), compiledSources, cache);
		}
		else {
			joins = null;
		}

		return new CompiledQuery(compiledQueryResult, compiledSources, joins, compiledConditions);
	}

	
	private static CompiledQueryResult compileQueryResult(QueryCollectorImpl collector, CompiledSelectSources<?> compiledSources, CompiledGetterSetterCache cache) throws CompileException {
		
		final CompiledQueryResult ret;
		
		final QueryResult result = collector.getResult();
		
		if (result instanceof QueryResultMapped) {
			
			if (collector.getMappings() != null) {
				
				final CompiledMappings compiledMappings = compileMappings(
						result.getType(),
						collector.getMappings(),
						collector.getSources(),
						compiledSources,
						cache);
				
				ret = new CompiledQueryResultMapped((QueryResultMapped)result, compiledMappings);
			}
			else {
				throw new IllegalStateException("No mappings for result " + result);
			}
		}
		else if (result instanceof QueryResultEntity) {
			ret = new CompiledQueryResultEntity((QueryResultEntity)result);
		}
		else if (result instanceof QueryResultAggregate) {
			ret = compileAggregateQueryResult((QueryResultAggregate)result, compiledSources, cache);
		}
		else {
			throw new UnsupportedOperationException("Unknown query result type " + result.getClass().getName());
		}

		return ret;
	}
	
	private static CompiledQueryResultAggregate compileAggregateQueryResult(
			QueryResultAggregate result,
			CompiledSelectSources<?> compiledSelectSources,
			CompiledGetterSetterCache cache) throws CompileException {

		final CompiledFieldReference fieldReference = compiledSelectSources.makeFieldReference(
				result,
				result.getGetter(),
				cache);

		return new CompiledQueryResultAggregate(result, fieldReference);
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
	
	private static CompiledSelectSources<?> compileSelectSources(SelectSourceImpl sources) {

		final CompiledSelectSources<?> compiled;

		// Figure out the type of select source
		if (sources instanceof SelectSourceClassesImpl) {
			final List<CompiledSelectSourceClass> compiledList = new ArrayList<>();
			
			// table names
			final SelectSourceClassesImpl selectSourceClasses = (SelectSourceClassesImpl)sources;

			int classNo = 0;
			
			for (Class<?> cl : selectSourceClasses.getClasses()) {
				final String name = cl.getSimpleName().toLowerCase();

				if (Coll8.has(compiledList, e -> e.getName().equals(name))) {
					throw new IllegalStateException("Two entity classes with same lowercase name \"" + name + "\"");
				}

				final CompiledSelectSourceClass c = new CompiledSelectSourceClass(selectSourceClasses, cl, name, classNo);

				compiledList.add(c);

				++ classNo;
			}

			compiled = new CompiledSelectSourcesClass(sources, compiledList);
		}
		else if (sources instanceof SelectSourceAliasesImpl) {
			final List<CompiledSelectSourceAlias> compiledList = new ArrayList<>();

			final SelectSourceAliasesImpl selectSourceAliases = (SelectSourceAliasesImpl)sources;

			int aliasNo = 0;
			
			for (IAlias alias : selectSourceAliases.getAliases()) {
				final CompiledSelectSourceAlias c = new CompiledSelectSourceAlias(selectSourceAliases, alias, "al" + aliasNo, aliasNo);

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

	private static CompiledJoins compileJoins(JoinCollector collector, CompiledSelectSources<?> sources, CompiledGetterSetterCache cache) throws CompileException {
		
		final List<CollectedJoin> collected = collector.getJoins();
		
		final List<CompiledJoin> compiledJoins = new ArrayList<>(collected.size());
		
		Boolean isClass = null;
		
		for (CollectedJoin join : collector.getJoins()) {
			final List<CollectedJoinCondition> collectedConditons = join.getJoinConditions();
			final List<CompiledJoinCondition> compiledJoinConditions = new ArrayList<>(collectedConditons.size());

			TypeMapSource leftSource = null;
			TypeMapSource rightSource = null;

			if (join instanceof CollectedJoinClasses) {
				if (join.getLeftType() != null && join.getRightType() != null) {
					leftSource  = sources.getClassesSource(join.getLeftType());
					rightSource = sources.getClassesSource(join.getRightType());
				}
				
				isClass = true;
			}
			else if (join instanceof CollectedJoinAliases) {
				final CollectedJoinAliases aliasJoin = (CollectedJoinAliases)join;
				leftSource  = sources.getAliasesSource(aliasJoin.getLeftAlias());
				rightSource = sources.getAliasesSource(aliasJoin.getRightAlias());
				isClass = false;
			}
			else {
				throw new UnsupportedOperationException("Unknown join type " + join.getClass().getName());
			}
			
			
			for (CollectedJoinCondition joinCondition : collectedConditons) {
				
				final boolean thisOneIsClass;

				if (joinCondition instanceof CollectedJoinConditionClasses) {
					thisOneIsClass = true;
				}
				else if (joinCondition instanceof CollectedJoinConditionAliases) {
					thisOneIsClass = false;
				}
				else {
					throw new UnsupportedOperationException("Unknown join type " + joinCondition.getClass().getName());
				}
				
				if (isClass == null) {
					isClass = thisOneIsClass;
				}
				else {
					if (isClass != thisOneIsClass) {
						throw new IllegalStateException("Both class and alias joins");
					}
				}
				
				final CompiledFieldReference left = sources.makeFieldReference(joinCondition, joinCondition.getLeftGetter(), cache);
				final CompiledFieldReference right = sources.makeFieldReference(joinCondition, joinCondition.getRightGetter(), cache);
				
				final CompiledJoinCondition compiledJoinCondition = new CompiledJoinCondition(joinCondition, left.getSource(), right.getSource());
		
				compiledJoinConditions.add(compiledJoinCondition);
			}

			
				
			
			final CompiledJoin compiledJoin = new CompiledJoin(
					join,
					leftSource,
					rightSource,
					compiledJoinConditions);

			compiledJoins.add(compiledJoin);
		}
		
		final CompiledJoins ret = isClass
				? new CompiledJoinsClasses(compiledJoins)
				: new CompiledJoinsAliases(compiledJoins);

		return ret;
	}
	
	
	
	private static CompiledConditions compileConditions(ClauseCollectorImpl clauses, CompiledSelectSources<?> sources, CompiledGetterSetterCache cache)
		throws CompileException {

		final List<ClauseImpl> list = clauses.getClauses();
		
		if (list.isEmpty()) {
			throw new IllegalStateException("no clauses");
		}

		if (!(list.get(0).getClause() instanceof ClausesImplInitial<?, ?>)) {
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
			
			if (clauseClass.equals(AndClausesImpl.class) || clauseClass.equals(AndClausesImplTableSingle.class)) {
				ret = new CompiledConditionsAnd(conditions);
			}
			else if (clauseClass.equals(OrClausesImpl.class) || clauseClass.equals(OrClausesImplTableSingle.class)) {
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
		
		final CompiledFieldReference lhs = sources.makeFieldReference(condition, condition.getGetter(), cache);
		
		ConditionValueImpl value;
		
		if (condition instanceof ValueConditionImpl) {
			value = ((ValueConditionImpl)condition).getValue();
			
			if (value instanceof ConditionValueGetterImpl) {
				// Getter value, should compile
				
				final Getter valueGetter = ((ConditionValueGetterImpl)value).getGetter();
				
				final CompiledFieldReference rhs = sources.makeFieldReference(value, valueGetter, cache);
				
				value = new ConditionValueFieldRerefenceImpl(rhs);
			}
		}
		else {
			value = null;
		}

		final CompiledCondition ret = new CompiledCondition(condition, lhs, value);
		
		return ret;
	}
}
