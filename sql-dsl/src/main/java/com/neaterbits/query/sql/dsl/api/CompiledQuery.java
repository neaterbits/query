package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;

import com.neaterbits.query.sql.dsl.api.entity.EntityUtil;
import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

/**
 * Representation of query where we have compiled all information 
 *
 */	

final class CompiledQuery {

	// TODO for now flatten joins onto outer level but could create nested ANSI queris (JPQL not possible, it seems)
	@Deprecated
	private static final boolean FLATTEN_SUB_JOINS = true;
	
	static final ExecutableQueryForCompiledQuery q = new ExecutableQueryForCompiledQuery();
	
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

	CompiledConditions getHaving() {
		return resultProcessing.getHaving().getConditions();
	}
	
	static <MODEL> CompiledQuery compile(Collector_Query<MODEL> collector, QueryMetaModel queryMetaModel) throws CompileException {
		final CompiledQuery ret;

		switch (collector.getQueryStyle()) {
		case CLASSIC:
			ret = compileClassic(collector);
			break;

		case SHORT:
			ret = compileShort(collector, queryMetaModel);
			break;
			
		default:
			throw new UnsupportedOperationException("Unknown query style: " + collector.getQueryStyle());
		}
		
		return ret;
	}
	
	// Classic-query
	static <MODEL> CompiledQuery compileClassic(Collector_Query<MODEL> collector) throws CompileException {
		final CollectedSelectSource sources = collector.getSources();
		final CompiledSelectSources<?> compiledSources = compileSelectSources(sources);

		final SelectSourceLookup lookup = new ClassicSelectSourceLookup(compiledSources);
		
		return compile(collector, lookup);
	}

	// short-model, we do not have the select-sources available, so we must look them up as we go
	static <MODEL> CompiledQuery compileShort(Collector_Query<MODEL> collector, QueryMetaModel queryMetaModel) throws CompileException {
	
		
		final SelectSourceLookup lookup = new ShortSelectSourceLookup(queryMetaModel.getAllManagedTypes(), collector.getAllAliases());
		
		return compile(collector, lookup);
	}
	
	private static <MODEL> CompiledQuery compile(Collector_Query<MODEL> collector, SelectSourceLookup selectSources) throws CompileException {
		if (collector == null) {
			throw new IllegalArgumentException("collector == null");
		}
		
		final CompiledQueryResult compiledQueryResult = compileQueryResult(collector, selectSources);

		final CompiledConditions compiledConditions;
		
		// Check all clauses etc
		final int conditionsMaxDepth;
		if (collector.getClauses() != null && !collector.getClauses().getConditions().isEmpty()) {
			compiledConditions = compileConditions(collector.getClauses(), selectSources, 0);
			
			conditionsMaxDepth = compiledConditions.getMaxDepth();
		}
		else {
			compiledConditions = null;

			conditionsMaxDepth = -1;
		}
		
		final CompiledJoins joins;

		if (collector.getJoins() != null) {
			joins = compileJoins(collector.getJoins(), selectSources);
		}
		else {
			joins = null;
		}
		
		final CompiledResultProcessing resultProcessing;
		
		if (compiledQueryResult instanceof CompiledQueryResult_Mapped) {
			
			// Can only do group-by etc for mapped result
			resultProcessing = compileMappedResultProcessing(
				collector,
				((CompiledQueryResult_Mapped)compiledQueryResult).getMappings(),
				selectSources);
		}
		else if (compiledQueryResult instanceof CompiledQueryResult_Entity) {
			resultProcessing = compileEntityResultProcessing(collector, selectSources);
		}
		else {
			if (collector.getGroupBy() != null) {
				throw new IllegalStateException("Can only fo group-by for queries that map the result");
			}
			
			resultProcessing = null;
		}
		
		final CompiledSelectSources<?> compiledSelectSources = selectSources.compile(collector.getResult());
		

		final CompiledQuery ret = new CompiledQuery(
				compiledQueryResult,
				compiledSelectSources,
				joins,
				compiledConditions,
				conditionsMaxDepth,
				resultProcessing);
		
		
		return ret;
	}
	
	private static class ColumnAndGetter {
		private final int column;
		private final CompiledFieldReference getter;

		ColumnAndGetter(int column, CompiledFieldReference getter) {
			
			if (getter == null) {
				throw new IllegalArgumentException("getter == null");
			}
			
			this.column = column;
			this.getter = getter;
		}
	}
	
	private static ColumnAndGetter getMappedProcessingResultIndexStartingAtOne(CompiledMappings mappings, CompiledGetter getter) {
		
		// Must compare with source-columns mapping
		int idx = 1;
		for (CompiledMapping mapping : mappings.getMappings()) {
			// Get the field we have selected from  
			
			// Can only map to plain field expressions, aggregates and so no cannot be ordered-by
			if (mapping.getExpression() instanceof CompiledFieldExpression) {
				
				final CompiledFieldExpression fieldExpression = (CompiledFieldExpression)mapping.getExpression();

				if (getter.equals(fieldExpression.getFieldReference().getGetter())) {
					return new ColumnAndGetter(idx, fieldExpression.getFieldReference());
				}
			}
			
			++ idx;
		}
		
		throw new IllegalStateException("Could not find index in mappings from getter: " + getter.getGetterMethod());
	}
	
	interface ResultProcessingFieldsCtor<T extends Collected_Fields, R extends CompiledResultFields> {
		
		R create(T fields, int [] indicesStartingAtOne, CompiledFieldReference[] getters);
		
	}
	

	private static <T extends Collected_Fields, R extends CompiledResultFields> 
		R compileMappedResultProcessingFields(
			T fields,
			CompiledMappings mappings,
			SelectSourceLookup sources,
			ResultProcessingFieldsCtor<T, R> ctor) throws CompileException {
		
		final int [] columns;
		final CompiledFieldReference [] compiledGetters;
		

		if (fields.getFields() != null) {

			final Getter [] getters = fields.getFields().toArray();
			
			columns = new int[getters.length];
			compiledGetters = new CompiledFieldReference[getters.length];
			
			for (int i = 0; i < getters.length; ++ i) {
				
				final CompiledGetter getter = sources.findGetter(getters[i]);
				
				final ColumnAndGetter columnAndGetter = getMappedProcessingResultIndexStartingAtOne(mappings, getter);
						
				columns[i] = columnAndGetter.column;
				compiledGetters[i] = columnAndGetter.getter;
			}
		}
		else if (fields.getColumns() != null) {
			columns = fields.getColumns();
			compiledGetters = getFieldReferences(mappings, fields.getColumns().length, fields.getColumns());
		}
		else {
			throw new IllegalStateException("neither fields not columns");
		}

		return ctor.create(fields, columns, compiledGetters);
	}
	
	private static CompiledFieldReference [] getFieldReferences(CompiledMappings mapped, int num, int [] columnsStartingAt1) {
		
		final CompiledFieldReference [] ret = new CompiledFieldReference[num];
		
		for (int i = 0; i < num; ++ i) {
			final int idx = columnsStartingAt1[i] - 1;
			
			final CompiledExpression expression = mapped.getMappings().get(idx).getExpression();
			
			if (!(expression instanceof CompiledFieldExpression)) {
				throw new IllegalStateException("Expected field expression for ordered-by mapping, should have been validated already");
			}
			
			final CompiledFieldReference compiledFieldRef = ((CompiledFieldExpression)expression).getFieldReference();

			if (compiledFieldRef == null) {
				throw new IllegalStateException("null fieldReference at " + i);
			}

			ret[i] = compiledFieldRef;
		}

		return ret;
	}

	
	private static <MODEL> CompiledResultProcessing compileMappedResultProcessing(
					Collector_Query<MODEL> collector,
					CompiledMappings mappings,
					SelectSourceLookup sources) throws CompileException {

		final CompiledResultProcessing ret;
		
		
		final CompiledGroupBy compiledGroupBy;
		CompiledHaving compiledHaving = null;
		final CompiledOrderBy compiledOrderBy;
		
		if (collector.getGroupBy() != null) {
			compiledGroupBy = compileMappedResultProcessingFields(collector.getGroupBy(), mappings, sources, (fields, indices, getters) -> new CompiledGroupBy(indices, getters) );

			final ICollectorClause having = collector.getGroupBy().getHaving();
			
			if (having != null) {
				final CompiledConditions havingConditions = compileConditions(having, sources,0);
				
				compiledHaving = new CompiledHaving(havingConditions);
				
			}
		}
		else {
			compiledGroupBy = null;
		}
		
		if (collector.getOrderBy() != null) {
			compiledOrderBy = compileMappedResultProcessingFields(collector.getOrderBy(), mappings, sources, (fields, indices, getters) -> new CompiledOrderByMapped(indices, getters, fields.getSortOrders()));
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
	
	private static <MODEL> CompiledOrderByEntity compileEntityOrderByFields(Collected_OrderBy collected, SelectSourceLookup sources) throws CompileException {
		
		final Getter [] getters = collected.getFields().toArray();
		final CompiledFieldReference [] compiledReferences = new CompiledFieldReference[getters.length];
		
		for (int i = 0; i < getters.length; ++ i) {
			compiledReferences[i] = sources.makeFieldReference(null, getters[i]);
		}

		// Must always pass fields since order by numbers does not make sense
		return new CompiledOrderByEntity(compiledReferences, collected.getSortOrders());
	}
	

	private static <MODEL> CompiledResultProcessing compileEntityResultProcessing(Collector_Query<MODEL> collector, SelectSourceLookup sources) throws CompileException {
		
		if (collector.getGroupBy() != null) {
			throw new IllegalStateException("Should not have group-by for entity queries");
		}
		
		final CompiledResultProcessing ret;
		
		if (collector.getOrderBy() != null) {
			final CompiledOrderByEntity orderBy = compileEntityOrderByFields(collector.getOrderBy(), sources);
			
			ret = new CompiledResultProcessing(null, null, orderBy);
		}
		else {
			ret = null;
		}
		
		
		return ret;
	}
	
	private static <MODEL> CompiledQueryResult compileQueryResult(Collector_Query<MODEL> collector, SelectSourceLookup sourceLookup) throws CompileException {
		
		final CompiledQueryResult ret;
		
		final CollectedQueryResult result = collector.getResult();
		
		if (result instanceof CollectedQueryResult_Mapped) {
			
			if (collector.getMappings() != null) {
				
				final CompiledMappings compiledMappings = compileMappings(result.getType(), collector.getMappings(), sourceLookup);
				
				ret = new CompiledQueryResult_Mapped((CollectedQueryResult_Mapped)result, compiledMappings);
			}
			else {
				throw new IllegalStateException("No mappings for result " + result);
			}
		}
		else if (result instanceof CollectedQueryResult_Entity) {
			ret = new CompiledQueryResult_Entity((CollectedQueryResult_Entity)result);
		}
		else if (result instanceof CollectedQueryResult_Aggregate) {
			ret = compileAggregateQueryResult((CollectedQueryResult_Aggregate)result, sourceLookup);
		}
		else {
			throw new UnsupportedOperationException("Unknown query result type " + result.getClass().getName());
		}

		return ret;
	}
	
	private static CompiledQueryResult_Aggregate compileAggregateQueryResult(CollectedQueryResult_Aggregate result, SelectSourceLookup sources) throws CompileException {

		final CompiledFieldReference fieldReference = sources.makeFieldReference(result, result.getGetter());

		return new CompiledQueryResult_Aggregate(result, fieldReference);
	}


	private static CompiledMappings compileMappings(
			Class<?> resultType,
			MappingCollector mappingCollector,
			SelectSourceLookup sources) throws CompileException {

		final List<CollectedMapping> collectedMappings = mappingCollector.getCollectedMappings();
		final List<CompiledMapping> compiledMappings = new ArrayList<>(collectedMappings.size());

		for (CollectedMapping collected : collectedMappings) {

			final CompiledMapping compiled = compileMapping(resultType, collected, sources);

			compiledMappings.add(compiled);
		}

		return new CompiledMappings(compiledMappings);
	}
	
	private static CompiledMapping compileMapping(
			Class<?> resultType,
			CollectedMapping collected,
			SelectSourceLookup sources) throws CompileException {

		final CompiledSetter mapSetter = sources.compileSetterUntyped(resultType, collected.getSetter());
		
		if (mapSetter == null) {
			throw new CompileException("Unknown mapping setter " + collected);
		}

		/*
		final CompiledFieldReference fieldReference = sources.makeFieldReference(collected, collected.getGetter());

		return new CompiledMapping(fieldReference, mapSetter, collected.get));
		*/
		
		return new CompiledMapping(compileExpression(collected.getExpression(), sources), mapSetter);
	}
	
	/*
	 * Compiling an expression is just switching any field expression for other 
	 */
	private static CompiledExpression compileExpression(Expression toCompile, SelectSourceLookup sources) throws CompileException {
		try {
			return intCompileExpression(toCompile, sources);
		}
		catch (RuntimeException ex) {
			if (ex.getCause() instanceof CompileException) {
				throw (CompileException)ex.getCause();
			}
			else {
				throw ex;
			}
		}
		
	}
		
	private static CompiledExpression intCompileExpression(Expression toCompile, SelectSourceLookup sources) {
		
		final CompiledExpression expression = toCompile.visit(compileVisitor, sources);
		
		if (expression == null) {
			throw new IllegalStateException("expression == null");
		}

		return expression;
	}

	private static List<CompiledExpression> intCompileExpressions(List<? extends Expression> toCompile, SelectSourceLookup sources) {
		
		final List<CompiledExpression> ret = new ArrayList<>(toCompile.size());
		
		for (Expression expression : toCompile) {
			ret.add(intCompileExpression(expression, sources));
		}

		return ret;
	}
	
	private static final ExpressionVisitor<SelectSourceLookup, CompiledExpression> compileVisitor = new ExpressionVisitor<SelectSourceLookup, CompiledExpression>() {
		@Override
		public CompiledExpression onNestedFunctionCalls(NestedFunctionCallsExpression nested, SelectSourceLookup param) {
			
			if (nested.getField() != null) {
				throw new IllegalStateException("should be null");
			}
			
			final List<FunctionExpression> collectedFunctions = nested.getFunctions().getFunctions();
			
			//final List<CompiledFunctionExpression> compiledFunctions = new ArrayList<>(collectedFunctions.size());
			
			//final CompiledFieldExpression compiledField = (CompiledFieldExpression)intCompileExpression(nested.getField(), param);

			/*
			
			for (FunctionExpression collected : collectedFunctions) {
				final CompiledFunctionExpression compiled = (CompiledFunctionExpression)intCompileExpression(collected, param);
				
				compiledFunctions.add(compiled);
			}
			*/
			
			// Compile in reverse order
			final int num = collectedFunctions.size();

			CompiledFunctionExpression last = null;

			for (int i = num - 1; i >= 0; -- i) {
				final FunctionExpression collected = collectedFunctions.get(i);
				final CompiledFunctionExpression compiled = (CompiledFunctionExpression)intCompileExpression(collected, param);

				if (last != null) {
					// nested call
					compiled.getParameters().add(last);
				}

				last = compiled;
			}
			
			
			//return new CompiledNestedFunctionCallsExpression(compiledFunctions);
			
			return last;
		}
		
		@Override
		public CompiledExpression onList(ExpressionList list, SelectSourceLookup param) {
			
			final List<CompiledExpression> compiled = new ArrayList<>(list.getExpressions().size());
			
			for (Expression expression : list.getExpressions()) {
				compiled.add(intCompileExpression(expression, param));
			}

			return new CompiledExpressionList(compiled, new ArrayList<>(list.getOperators()));
		}
		
		@Override
		public CompiledExpression onFunction(FunctionExpression function, SelectSourceLookup param) {
			final List<CompiledExpression> params = intCompileExpressions(function.getParameters(), param);
			
			return new CompiledFunctionExpression(function.getFunction(), params);
		}
		
		@Override
		public CompiledExpression onField(FieldExpression field, SelectSourceLookup param) {
			try {
				final CompiledFieldReference fieldReference = param.makeFieldReference(null, field.getGetter());
	
				return new CompiledFieldExpression(fieldReference);
			}
			catch (CompileException ex) {
				throw new RuntimeException(ex);
			}
		}
		
		@Override
		public CompiledExpression onValue(ValueExpression value, SelectSourceLookup param) {
			return new CompiledValueExpression(value.getValue());
		}

		@Override
		public CompiledExpression onParam(ParamExpression param, SelectSourceLookup visitorParam) {
			throw new UnsupportedOperationException("TODO");
		}
	};
	
	
	private static CompiledSelectSources<?> compileSelectSources(CollectedSelectSource sources) {

		final CompiledSelectSources<?> compiled;

		// Figure out the type of select source
		if (sources instanceof CollectedSelectSource_Named) {
			final List<CompiledSelectSource_Named> compiledList = new ArrayList<>();
			
			// table names
			final CollectedSelectSource_Named selectSourceClasses = (CollectedSelectSource_Named)sources;

			int classNo = 0;
			
			for (Class<?> cl : selectSourceClasses.getClasses()) {

				final String name = CompiledSelectSource_Named.getName(cl, compiledList);
				
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
				
				final String name = CompiledSelectSource_Alias.getName(aliasNo);
				
				final CompiledSelectSource_Alias c = new CompiledSelectSource_Alias(selectSourceAliases, alias, name, aliasNo);

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
	
	private static class JoinSources {
		private final TypeMapSource left;
		private final TypeMapSource right;
		private final boolean isClass;
		
		
		JoinSources(TypeMapSource left, TypeMapSource right, boolean isClass) {
			
			if (left == null) {
				throw new IllegalArgumentException("left == null");
			}
			
			if (right == null) {
				throw new IllegalArgumentException("right == null");
			}
			
			this.left = left;
			this.right = right;
			this.isClass = isClass;
		}
	}
	
	private static JoinSources getExplicitJoinSources(CollectedJoin join, SelectSourceLookup sources) {
		TypeMapSource leftSource = null;
		TypeMapSource rightSource = null;
		
		final boolean isClass;

		if (join instanceof CollectedJoin_Named) {
			if (join.getLeftType() != null && join.getRightType() != null) {
				// Classic
				leftSource  = sources.getNamedSource(join.getLeftType());
				rightSource = sources.getNamedSource(join.getRightType());
			}
			
			isClass = true;
		}
		else if (join instanceof CollectedJoin_Alias) {
			final CollectedJoin_Alias aliasJoin = (CollectedJoin_Alias)join;
			
			if (aliasJoin.getLeftAlias() != null && aliasJoin.getRightAlias() != null) {
				// Classic
				leftSource  = sources.getAliasesSource(aliasJoin.getLeftAlias());
				rightSource = sources.getAliasesSource(aliasJoin.getRightAlias());
			}
			isClass = false;
		}
		else {
			throw new UnsupportedOperationException("Unknown join type " + join.getClass().getName());
		}

		final JoinSources ret;
		
		if (leftSource != null && rightSource != null) {
			ret = new JoinSources(leftSource, rightSource, isClass);
		}
		else if (leftSource == null && rightSource == null) {
			ret = null;
		}
		else {
			throw new IllegalStateException("Expecting to have know either both left and right hand side or knowing neither");
		}

		return ret;
	}

	private static final boolean isClass(CollectedJoinCondition joinCondition) {
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

		return thisOneIsClass;
	}

	
	private static CompiledJoins compileJoins(Collector_Joins collector, SelectSourceLookup sources) throws CompileException {
		
		final List<CollectedJoin> collected = collector.getJoins();
		
		final List<CompiledJoin> compiledJoins = new ArrayList<>(collected.size());
		
		Boolean isClass = null;
		
		for (CollectedJoin join : collector.getJoins()) {
			final List<CollectedJoinCondition> collectedConditons = join.getJoinConditions();
			final List<CompiledJoinCondition> compiledJoinConditions = new ArrayList<>(collectedConditons.size());

			JoinSources joinSources = getExplicitJoinSources(join, sources);
			
			for (CollectedJoinCondition joinCondition : collectedConditons) {

				final boolean thisOneIsClass = isClass(joinCondition);

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

					final CompiledFieldReference left = sources.makeFieldReference(joinCondition, joinConditionComparison.getLeftGetter());
					final CompiledFieldReference right = sources.makeFieldReference(joinCondition, joinConditionComparison.getRightGetter());
					
					compiledJoinCondition = new CompiledJoinCondition_Comparison(joinConditionComparison, left, right);
					
					if (joinSources == null) {
						joinSources = new JoinSources(left.getSource(), right.getSource(), isClass);
					}
				}
				else if (joinCondition instanceof CollectedJoinCondition_OneToMany) {

					final CollectedJoinCondition_OneToMany oneToMany = (CollectedJoinCondition_OneToMany)joinCondition;
					final CompiledFieldReference collection = sources.makeFieldReference(joinCondition, oneToMany.getCollectionGetter());
					
					final TypeMapSource left;
					final TypeMapSource right;

					final Class<?> collectionSourceType = collection.getSource().getType();

					if (joinSources != null) {
						
						// classic syntax where has explicit declaration of types
						
						if (collectionSourceType.equals(joinSources.left.getType())) {
							left = joinSources.left;
							right = joinSources.right;
						}
						else if (collectionSourceType.equals(joinSources.right.getType())) {
							left = joinSources.right;
							right = joinSources.left;
						}
						else {
							throw new UnsupportedOperationException("Could not match neither left nor right : collection=" + collectionSourceType + 
										", left=" + joinSources.left.getType() + ", right=" + joinSources.right.getType());
						}
					} else {
						// short-syntax where source is not explicitly declared
						left = collection.getSource();
						
						// TODO For now, we only support generic-collection joins so we can retrieve the type here,
						// otherwise we have to change the query builder a bit in order to do this.
						// BUT for all practical purposes, we require generic collections for typesafety anyway.. and all > Java 5 code will use generics most likely
						
						final Class<?> type = EntityUtil.getGenericCollectionMemberType(collection.getGetter().getGetterMethod());
						if (type == null) {
							throw new IllegalStateException("Unable to get class type for oneToMany collection join");
						}
						
						if (isClass) {
							right = sources.getNamedSource(type);
	
							joinSources = new JoinSources(left, right, isClass);
						}
						else {
							// Alias can be found from supplier, or?? 
							// if there are multiple aliases for that particular type, how to know the right one?
							// should one always specify one? May have to do that in this case.
							final CollectedJoinCondition_OneToMany_Alias oneToManyAlias = (CollectedJoinCondition_OneToMany_Alias)joinCondition;

							right = sources.getAliasesSource(oneToManyAlias.getRhs());
							
							joinSources = new JoinSources(left, right, isClass);
						}
					}

					compiledJoinCondition = new CompiledJoinCondition_OneToMany(oneToMany, left, right, collection);
				}
				else {
					throw new UnsupportedOperationException("Unknown join condition instance " + joinCondition.getClass().getSimpleName());
				}

				compiledJoinConditions.add(compiledJoinCondition);
			}
			
			CompiledJoins subJoins;
			
			if (join.getSubJoins() != null) {
				// nested joins, compile recursively
				subJoins = compileJoins(join.getSubJoins(), sources);
				
				if (FLATTEN_SUB_JOINS) {
					// Add to outer-list which will be re-added to outer-list again if recursed multiple levels
					compiledJoins.addAll(subJoins.getJoins());
					
					subJoins = null;
				}
				
			}
			else  {
				subJoins = null;
			}

			final CompiledJoin compiledJoin = new CompiledJoin(
					join,
					joinSources.left,
					joinSources.right,
					compiledJoinConditions,
					subJoins);

			compiledJoins.add(compiledJoin);
		}

		final CompiledJoins ret = isClass
				? new CompiledJoins_Named(compiledJoins)
				: new CompiledJoins_Alias(compiledJoins);

		return ret;
	}
	
	private static CompiledConditions compileConditions(ICollectorClause clauses, SelectSourceLookup sources, int level)
		throws CompileException {

		final List<CollectedCondition> list = clauses.getConditions();
		
		if (list.isEmpty()) {
			throw new IllegalStateException("no clauses");
		}

		final CompiledConditions ret;
		
		final int num = list.size();
		
		if (num == 1) {
			
			final CompiledCondition singleCondition = compileCondition(list.get(0), sources, level);
			
			ret = new CompiledConditions_Single(singleCondition);
		}
		else {
			final List<CompiledCondition> conditions = new ArrayList<>(num);

			conditions.add(compileCondition(list.get(0), sources, level));

			for (int i = 1; i < num; ++ i) {

				final CollectedCondition condition = list.get(i);

				conditions.add(compileCondition(condition, sources, level));
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

	private static CompiledCondition compileCondition(CollectedCondition condition, SelectSourceLookup sources, int level) throws CompileException {
		
		if (condition == null) {
			throw new IllegalArgumentException("condition == null");
		}
		
		final CompiledCondition ret;
		
		if (condition instanceof CollectedCondition_NonNested) {
			ret = compileNonNestedCondition((CollectedCondition_NonNested)condition, sources);
		}
		else if (condition instanceof CollectedCondition_Nested) {
			ret = compileNestedCondition((CollectedCondition_Nested)condition, sources, level);
		}
		else {
			throw new UnsupportedOperationException("unknown condition type " + condition);
		}

		return ret;
	}


	private static CompiledCondition compileNestedCondition(CollectedCondition_Nested condition, SelectSourceLookup sources, int level) throws CompileException {

		final CompiledConditionNested ret;
		
		// Compiled recursively
		final CompiledConditions compiled = compileConditions(condition.getCollected().clauseCollector, sources, level + 1);
		
		ret = new CompiledConditionNested(compiled);
		
		return ret;
	}

	
	private static CompiledCondition compileNonNestedCondition(CollectedCondition_NonNested condition, SelectSourceLookup sources) throws CompileException {
		
		final CompiledExpression lhs = compileExpression(condition.getLhs(), sources);
		
		
		// final CompiledFieldReference lhs = sources.makeFieldReference(condition, condition.getGetter());
		
		ConditionValue value;
		
		if (condition instanceof CollectedCondition_Value) {
			value = ((CollectedCondition_Value)condition).getValue();
			
			if (value instanceof ConditionValue_Getter) {
				// Getter value, should compile
				
				final Getter valueGetter = ((ConditionValue_Getter)value).getGetter();
				
				final CompiledFieldReference rhs = sources.makeFieldReference(value, valueGetter);
				
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
