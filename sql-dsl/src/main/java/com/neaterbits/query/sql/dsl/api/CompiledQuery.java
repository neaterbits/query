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
	
	private final int conditionsMaxDepth;
	
	private final CompiledResultProcessing resultProcessing;
	

	private CompiledQuery(
			CompiledQueryResult result,
			CompiledSelectSources<?> selectSources,
			CompiledJoins joins,
			CompiledConditions conditions,
			int conditionsMaxDepth,
			CompiledResultProcessing resultProcessing) {

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
		this.conditionsMaxDepth = conditionsMaxDepth;
		this.resultProcessing = resultProcessing;
	}

	EQueryResultDimension getResultMode() {
		return result.getDimension();
	}
	
	EQueryResultGathering getGathering() {
		return result.getGathering();
	}

	CompiledQueryResult getResult() {
		return result;
	}

	Class<?> getResultType() {
		return result.getResultType();
	}

	CompiledConditions getConditions() {
		return conditions;
	}

	@SuppressWarnings("unchecked")
	CompiledSelectSources<CompiledSelectSource> getSelectSources() {
		return (CompiledSelectSources<CompiledSelectSource>)selectSources;
	}
	
	Class<?> [] getSelectSourceClasses() {
		
		@SuppressWarnings("unchecked")
		final List<CompiledSelectSource> sources = (List<CompiledSelectSource>)selectSources.getSources();
		
		final int num = sources.size();
		final Class<?> [] ret = new Class<?>[num];

		for (int i = 0; i < num; ++ i) {
			ret[i] = sources.get(i).getType();
		}
		
		return ret;
	}

	int getConditionsMaxDepth() {
		return conditionsMaxDepth;
	}

	CompiledJoins getJoins() {
		return joins;
	}
	
	CompiledResultProcessing getResultProcessing() {
		return resultProcessing;
	}

	static CompiledQuery compile(QueryCollectorImpl collector) throws CompileException {
		if (collector == null) {
			throw new IllegalArgumentException("collector == null");
		}
		
		final CompiledGetterSetterCache cache = new CompiledGetterSetterCache();
		final CollectedSelectSource sources = collector.getSources();

		final CompiledSelectSources<?> compiledSources = compileSelectSources(sources);
		final CompiledQueryResult compiledQueryResult = compileQueryResult(collector, compiledSources, cache);

		final CompiledConditions compiledConditions;
		
		// Check all clauses etc
		final int conditionsMaxDepth;
		if (collector.getClauses() != null && !collector.getClauses().getConditions().isEmpty()) {
			compiledConditions = compileConditions(collector.getClauses(), compiledSources, cache, 0);
			
			conditionsMaxDepth = compiledConditions.getMaxDepth();
		}
		else {
			compiledConditions = null;

			conditionsMaxDepth = -1;
		}
		
		final CompiledJoins joins;

		if (collector.getJoins() != null) {
			joins = compileJoins(collector.getJoins(), compiledSources, cache);
		}
		else {
			joins = null;
		}
		
		final CompiledResultProcessing resultProcessing;
		
		if (compiledQueryResult instanceof CompiledQueryResult_Mapped) {
			
			// Can only do group-by etc for mapped result
			resultProcessing = compileResultProcessing(
				collector,
				((CompiledQueryResult_Mapped)compiledQueryResult).getMappings(),
				compiledSources,
				cache);
		}
		else {
			if (collector.getGroupBy() != null) {
				throw new IllegalStateException("Can only fo group-by for queries that map the result");
			}
			
			resultProcessing = null;
		}
		

		return new CompiledQuery(
				compiledQueryResult,
				compiledSources,
				joins,
				compiledConditions,
				conditionsMaxDepth,
				resultProcessing);
	}
	
	private static int getProcessingResultIndexStartingAtOne(CompiledMappings mappings, CompiledGetterFunction getter) {
		
		// Must compare with source-columns mapping
		int idx = 1;
		for (CompiledMapping mapping : mappings.getMappings()) {
			// Get the field we have selected from  
			final CompiledGetter mappingGetter = mapping.getField().getGetter();
			
			if (getter.equals(mappingGetter)) {
				return idx;
			}
			
			++ idx;
		}
		
		throw new IllegalStateException("Could not find index in mappings from getter: " + getter.getGetterMethod());
	}
	
	interface ResultProcessingFieldsCtor<T extends Collected_Fields, R extends CompiledResultFields> {
		
		R create(T fields, int [] indicesStartingAtOne, FunctionGetter[] getters);
		
	}
	

	private static <T extends Collected_Fields, R extends CompiledResultFields> 
		R compileResultProcessingFields(
			T fields,
			CompiledMappings mappings,
			CompiledSelectSources<?> sources,
			CompiledGetterSetterCache cache,
			ResultProcessingFieldsCtor<T, R> ctor) throws CompileException {
		
		final int [] columns;
		final FunctionGetter [] getters;

		if (fields.getFields() != null) {

			getters = fields.getFields().toArray();
			
			
			columns = new int[getters.length];
			
			for (int i = 0; i < getters.length; ++ i) {
				final CompiledGetterFunction getter = (CompiledGetterFunction)cache.findGetterFromTypes(sources.getTypes(), getters[i].getter);
				
				columns[i] = getProcessingResultIndexStartingAtOne(mappings, getter);
			}
		}
		else if (fields.getColumns() != null) {
			columns = fields.getColumns();
			getters = null;
		}
		else {
			throw new IllegalStateException("neither fields not columns");
		}

		
		return ctor.create(fields, columns, getters);
	}
	
	private static CompiledResultProcessing compileResultProcessing(
					QueryCollectorImpl collector,
					CompiledMappings mappings,
					CompiledSelectSources<?> sources,
					CompiledGetterSetterCache cache) throws CompileException {

		final CompiledResultProcessing ret;
		
		
		final CompiledGroupBy compiledGroupBy;
		CompiledHaving compiledHaving = null;
		final CompiledOrderBy compiledOrderBy;
		
		if (collector.getGroupBy() != null) {
			compiledGroupBy = compileResultProcessingFields(collector.getGroupBy(), mappings, sources, cache, (fields, indices, getters) -> new CompiledGroupBy(indices, getters) );

			final Collector_Clause having = collector.getGroupBy().getHaving();
			
			if (having != null) {
				final CompiledConditions havingConditions = compileConditions(having, sources, cache, 0);
				
				compiledHaving = new CompiledHaving(havingConditions);
				
			}
		}
		else {
			compiledGroupBy = null;
		}
		
		if (collector.getOrderBy() != null) {
			
			compiledOrderBy = compileResultProcessingFields(collector.getOrderBy(), mappings, sources, cache, (fields, indices, getters) -> new CompiledOrderBy(indices, getters, fields.getSortOrders()));
		}
		else {
			compiledOrderBy = null;
		}
		
		if (compiledGroupBy != null || compiledOrderBy != null) {
			ret = new CompiledResultProcessing(compiledGroupBy, compiledHaving, compiledOrderBy);
		}
		else {
			ret = null;
		}

		return ret;
	}
	
	
	private static CompiledQueryResult compileQueryResult(QueryCollectorImpl collector, CompiledSelectSources<?> compiledSources, CompiledGetterSetterCache cache) throws CompileException {
		
		final CompiledQueryResult ret;
		
		final CollectedQueryResult result = collector.getResult();
		
		if (result instanceof CollectedQueryResult_Mapped) {
			
			if (collector.getMappings() != null) {
				
				final CompiledMappings compiledMappings = compileMappings(
						result.getType(),
						collector.getMappings(),
						collector.getSources(),
						compiledSources,
						cache);
				
				ret = new CompiledQueryResult_Mapped((CollectedQueryResult_Mapped)result, compiledMappings);
			}
			else {
				throw new IllegalStateException("No mappings for result " + result);
			}
		}
		else if (result instanceof CollectedQueryResult_Entity) {
			ret = new CompiledQueryResult_Entity((CollectedQueryResult_Entity)result);
		}
		else if (result instanceof QueryResultAggregate) {
			ret = compileAggregateQueryResult((QueryResultAggregate)result, compiledSources, cache);
		}
		else {
			throw new UnsupportedOperationException("Unknown query result type " + result.getClass().getName());
		}

		return ret;
	}
	
	private static CompiledQueryResult_Aggregate compileAggregateQueryResult(
			QueryResultAggregate result,
			CompiledSelectSources<?> compiledSelectSources,
			CompiledGetterSetterCache cache) throws CompileException {

		final CompiledFieldReference fieldReference = compiledSelectSources.makeFieldReference(
				result,
				result.getGetter(),
				cache);

		return new CompiledQueryResult_Aggregate(result, fieldReference);
	}


	private static CompiledMappings compileMappings(
			Class<?> resultType,
			MappingCollector mappingCollector,
			CollectedSelectSource selectSource,
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
			CollectedSelectSource selectSource,
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
	
	private static CompiledSelectSources<?> compileSelectSources(CollectedSelectSource sources) {

		final CompiledSelectSources<?> compiled;

		// Figure out the type of select source
		if (sources instanceof CollectedSelectSource_Named) {
			final List<CompiledSelectSource_Named> compiledList = new ArrayList<>();
			
			// table names
			final CollectedSelectSource_Named selectSourceClasses = (CollectedSelectSource_Named)sources;

			int classNo = 0;
			
			for (Class<?> cl : selectSourceClasses.getClasses()) {
				final String name = cl.getSimpleName().toLowerCase();

				if (Coll8.has(compiledList, e -> e.getName().equals(name))) {
					throw new IllegalStateException("Two entity classes with same lowercase name \"" + name + "\"");
				}

				final CompiledSelectSource_Named c = new CompiledSelectSource_Named(selectSourceClasses, cl, name, classNo);

				compiledList.add(c);

				++ classNo;
			}

			compiled = new CompiledSelectSources_Named(sources, compiledList);
		}
		else if (sources instanceof CollectedSelectSource_Aliases) {
			final List<CompiledSelectSource_Alias> compiledList = new ArrayList<>();

			final CollectedSelectSource_Aliases selectSourceAliases = (CollectedSelectSource_Aliases)sources;

			int aliasNo = 0;
			
			for (IAlias alias : selectSourceAliases.getAliases()) {
				final CompiledSelectSource_Alias c = new CompiledSelectSource_Alias(selectSourceAliases, alias, "al" + aliasNo, aliasNo);

				compiledList.add(c);

				++ aliasNo;
			}

			compiled = new CompiledSelectSources_Alias(sources, compiledList);
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

			if (join instanceof CollectedJoin_Named) {
				if (join.getLeftType() != null && join.getRightType() != null) {
					leftSource  = sources.getNamedSource(join.getLeftType());
					rightSource = sources.getNamedSource(join.getRightType());
				}
				
				isClass = true;
			}
			else if (join instanceof CollectedJoin_Alias) {
				final CollectedJoin_Alias aliasJoin = (CollectedJoin_Alias)join;
				leftSource  = sources.getAliasesSource(aliasJoin.getLeftAlias());
				rightSource = sources.getAliasesSource(aliasJoin.getRightAlias());
				isClass = false;
			}
			else {
				throw new UnsupportedOperationException("Unknown join type " + join.getClass().getName());
			}
			
			
			for (CollectedJoinCondition joinCondition : collectedConditons) {
				
				final boolean thisOneIsClass;

				if (joinCondition instanceof CollectedJoinCondition_Comparison_Named) {
					thisOneIsClass = true;
				}
				else if (joinCondition instanceof CollectedJoinCondition_Comparison_Alias) {
					thisOneIsClass = false;
				}
				else if (joinCondition instanceof CollectedJoinCondition_OneToMany_Named) {
					thisOneIsClass = true;
				}
				else if (joinCondition instanceof CollectedJoinCondition_OneToMany_Alias) {
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
				
				final CompiledJoinCondition compiledJoinCondition;
				
				if (joinCondition instanceof CollectedJoinCondition_Comparison) {

					final CollectedJoinCondition_Comparison joinConditionComparison = (CollectedJoinCondition_Comparison)joinCondition;

					final CompiledFieldReference left = sources.makeFieldReference(joinCondition, joinConditionComparison.getLeftGetter(), cache);
					final CompiledFieldReference right = sources.makeFieldReference(joinCondition, joinConditionComparison.getRightGetter(), cache);
					
					compiledJoinCondition = new CompiledJoinCondition_Comparison(joinConditionComparison, left, right);
				}
				else if (joinCondition instanceof CollectedJoinCondition_OneToMany) {

					final CollectedJoinCondition_OneToMany oneToMany = (CollectedJoinCondition_OneToMany)joinCondition;
					final CompiledFieldReference collection = sources.makeFieldReference(joinCondition, oneToMany.getCollectionGetter(), cache);
					
					final TypeMapSource left;
					final TypeMapSource right;

					final Class<?> collectionSourceType = collection.getSource().getType();

					if (collectionSourceType.equals(leftSource.getType())) {
						left = leftSource;
						right = rightSource;
					}
					else if (collectionSourceType.equals(rightSource.getType())) {
						left = rightSource;
						right = leftSource;
					}
					else {
						throw new UnsupportedOperationException("Could not match neither left nor right : collection=" + collectionSourceType + ", left=" + leftSource.getType() + ", right=" + rightSource.getType());
					}

					compiledJoinCondition = new CompiledJoinCondition_OneToMany(oneToMany, left, right, collection);
				}
				else {
					throw new UnsupportedOperationException("Unknown join condition instance " + joinCondition.getClass().getSimpleName());
				}

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
				? new CompiledJoins_Named(compiledJoins)
				: new CompiledJoins_Alias(compiledJoins);

		return ret;
	}
	
	private static CompiledConditions compileConditions(Collector_Clause clauses, CompiledSelectSources<?> sources, CompiledGetterSetterCache cache, int level)
		throws CompileException {

		final List<CollectedCondition> list = clauses.getConditions();
		
		if (list.isEmpty()) {
			throw new IllegalStateException("no clauses");
		}

		final CompiledConditions ret;
		
		final int num = list.size();
		
		if (num == 1) {
			
			final CompiledCondition singleCondition = compileCondition(list.get(0), sources, cache, level);
			
			ret = new CompiledConditions_Single(singleCondition);
		}
		else {
			final List<CompiledCondition> conditions = new ArrayList<>(num);

			conditions.add(compileCondition(list.get(0), sources, cache, level));

			for (int i = 1; i < num; ++ i) {

				final CollectedCondition condition = list.get(i);

				conditions.add(compileCondition(condition, sources, cache, level));
			}
			
			switch (clauses.getConditionsType()) {
			case AND:
				ret = new CompiledConditions_And(conditions);
				break;
				
			case OR:
				ret = new CompiledConditions_Or(conditions);
				break;
				
			default:
				throw new IllegalStateException("Unknown conditions type " + clauses.getConditionsType());
			}
		}

		return ret;
	}

	private static CompiledCondition compileCondition(CollectedCondition condition, CompiledSelectSources<?> sources, CompiledGetterSetterCache cache, int level) throws CompileException {
		
		if (condition == null) {
			throw new IllegalArgumentException("condition == null");
		}
		
		final CompiledCondition ret;
		
		if (condition instanceof CollectedCondition_NonNested) {
			ret = compileNonNestedCondition((CollectedCondition_NonNested)condition, sources, cache);
		}
		else if (condition instanceof CollectedCondition_Nested) {
			ret = compileNestedCondition((CollectedCondition_Nested)condition, sources, cache, level);
		}
		else {
			throw new UnsupportedOperationException("unknown condition type " + condition);
		}

		return ret;
	}


	private static CompiledCondition compileNestedCondition(CollectedCondition_Nested condition, CompiledSelectSources<?> sources, CompiledGetterSetterCache cache, int level) throws CompileException {

		final CompiledConditionNested ret;
		
		// Compiled recursively
		final CompiledConditions compiled = compileConditions(condition.getCollected().clauseCollector, sources, cache, level + 1);
		
		ret = new CompiledConditionNested(compiled);
		
		return ret;
	}

	
	private static CompiledCondition compileNonNestedCondition(CollectedCondition_NonNested condition, CompiledSelectSources<?> sources, CompiledGetterSetterCache cache) throws CompileException {
		
		final CompiledFieldReference lhs = sources.makeFieldReference(condition, condition.getGetter(), cache);
		
		ConditionValue value;
		
		if (condition instanceof CollectedCondition_Value) {
			value = ((CollectedCondition_Value)condition).getValue();
			
			if (value instanceof ConditionValue_Getter) {
				// Getter value, should compile
				
				final Getter valueGetter = ((ConditionValue_Getter)value).getGetter();
				
				final CompiledFieldReference rhs = sources.makeFieldReference(value, valueGetter, cache);
				
				value = new ConditionValue_FieldRerefence(rhs);
			}
		}
		else {
			value = null;
		}

		final CompiledConditionComparison ret = new CompiledConditionComparison(condition, lhs, value);
		
		return ret;
	}
}
