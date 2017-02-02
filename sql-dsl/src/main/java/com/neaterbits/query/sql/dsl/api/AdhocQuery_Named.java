package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import com.neaterbits.query.sql.dsl.api.entity.OneToManyJoinConditionResolver;
import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

abstract class AdhocQuery_Named<MODEL, RESULT>

		extends AdhocQueryBase<MODEL, AdhocQuery_Named<MODEL, RESULT>> 
		implements

			IAdhocEnd_Base<MODEL, RESULT>,
			
			ISharedLogical_Base<MODEL, RESULT>,
			
			IAdhocFunctions_Callback<MODEL, RESULT, ISharedLogical_Base<MODEL,RESULT>>,
			
			ExecuteQueryPOJOsInput {

	
	private static final int INITIAL_SOURCES = 10;
	private static final int INITIAL_JOINS = 10;
	
	@SuppressWarnings("rawtypes")
	private Function aggregateGetter;

	private AdhocConditions<MODEL, RESULT, ?> conditions;

	private int curSource;
	
	private Collection<?> [] sources;
	private int numSources;

	private AdhocJoin<?, ?> [] joins;
	private int numJoins;
	
	abstract AdhocConditions<MODEL, RESULT, ?> createConditions(int level);

	AdhocQuery_Named(Function<?, ?> aggregateGetter, EAggregateFunction aggregateFunction, ENumericType aggregateNumericInputType, ENumericType aggregateNumericOutputType) {
		super(aggregateFunction, aggregateNumericInputType, aggregateNumericOutputType);

		if (aggregateGetter == null) {
			throw new IllegalArgumentException("aggregateGetter == null");
		}

		this.aggregateGetter = aggregateGetter;
	}


	AdhocQuery_Named(ECollectionType collectionType, Collection<?> coll) {

		super(collectionType);

		if (coll == null) {
			throw new IllegalArgumentException("coll == null");
		}

		addSource(coll);
		
		// Only one source so far
		this.curSource = -1; // to avoid exception about setting to same for initial
		setCurSource(0);
	}

	final int getCurSource() {
		return curSource;
	}

	final void setCurSource(int newCurSource) {

		if (newCurSource == curSource) {
			throw new IllegalStateException("Setting to existing source which is probably not what was intended: " + newCurSource);
		}
		
		if (AdhocDebug.DEBUG_BUILD_QUERY) {
			
			AdhocDebug.println("switching source from " + curSource + " to " + newCurSource);
		}

		this.curSource = newCurSource;
	}

	private int addSource(Collection<?> collection) {
		if (collection == null) {
			throw new IllegalArgumentException("collection == null");
		}

		final int sourceIdx;
		
		if (this.numSources == 0) {
		
			this.sources = new Collection<?>[INITIAL_SOURCES];
			sourceIdx = 0;
			this.sources[0] = collection;
			this.numSources = 1;
		}
		else {

			if (this.numSources == sources.length) {
				// Must copy over
				this.sources = Arrays.copyOf(this.sources, sources.length * 2);
			}
			
			sourceIdx = numSources;
			this.sources[numSources ++] = collection;
		}

		return sourceIdx;
	}
	
	
	@Override
	public FieldReferenceType getQueryFieldReferenceType(AdhocQuery_Named<MODEL, RESULT> query) {
		return FieldReferenceType.ENTITY;
	}

	@Override
	@SuppressWarnings("unchecked")
	public final Object getAggregateResultValue(AdhocQuery_Named<MODEL, RESULT> query, Object instance) {
		return aggregateGetter.apply(instance);
	}

	@Override
	public final ExecuteQueryScratch createScratchArea(AdhocQuery_Named<MODEL, RESULT> query, QueryMetaModel queryMetaModel) {

		initScratchArea(
				getNumResultParts(this),
				getAllSourceCount(this),
				
				getRootConditionCount(this),
				getConditionsMaxDepth(query));

		return this;
	}

	
	/**************************************************************************
	** ExecuteQueryPOJOsInput
	**************************************************************************/
	
	@Override
	public final Collection<?> getPOJOs(int idx) {
		return sources[idx];
	}
	
	// Joins
	@Override
	public final int getJoinCount(AdhocQuery_Named<MODEL, RESULT> query) {
		return numJoins;
	}

	@Override
	public final EJoinType getJoinType(AdhocQuery_Named<MODEL, RESULT> query, int joinIdx) {
		return joins[joinIdx].type;
	}


	@Override
	public final int getJoinLeftSourceIdx(AdhocQuery_Named<MODEL, RESULT> query, int joinIdx) {
		return joins[joinIdx].leftSourceIdx;
	}


	@Override
	public final int getJoinRightSourceIdx(AdhocQuery_Named<MODEL, RESULT> query, int joinIdx) {
		return joins[joinIdx].rightSourceIdx;
	}

	@Override
	public Class<?> getJoinLeftJavaType(AdhocQuery_Named<MODEL, RESULT> query, int joinIdx) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public Class<?> getJoinRightJavaType(AdhocQuery_Named<MODEL, RESULT> query, int joinIdx) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final int getJoinConditionCount(AdhocQuery_Named<MODEL, RESULT> query, int joinIdx) {
		return joins[joinIdx].numConditions;
	}
	
	@Override
	public EJoinConditionType getJoinConditionType(AdhocQuery_Named<MODEL, RESULT> query, int joinIdx, int conditionIdx) {
		return joins[joinIdx].conditions[conditionIdx].getJoinConditionType();
	}

	@Override
	public final int getJoinConditionLeftSourceIdx(AdhocQuery_Named<MODEL, RESULT> query, int joinIdx, int conditionIdx) {
		return joins[joinIdx].leftSourceIdx;
	}


	@Override
	public final int getJoinConditionRightSourceIdx(AdhocQuery_Named<MODEL, RESULT> query, int joinIdx, int conditionIdx) {
		return joins[joinIdx].rightSourceIdx;
	}

	@Override
	public final String getJoinConditionLeftName(AdhocQuery_Named<MODEL, RESULT> query, int joinIdx, int conditionIdx) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final String getJoinConditionRightName(AdhocQuery_Named<MODEL, RESULT> query, int joinIdx, int conditionIdx) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final Class<?> getJoinConditionLeftJavaType(AdhocQuery_Named<MODEL, RESULT> query, int joinIdx, int conditionIdx) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final Class<?> getJoinConditionRightJavaType(AdhocQuery_Named<MODEL, RESULT> query, int joinIdx, int conditionIdx) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final Method getJoinConditionOneToManyCollectionGetter(AdhocQuery_Named<MODEL, RESULT> query, int joinIdx, int conditionIdx) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final CompiledFieldReference getJoinConditionComparisonLhs(AdhocQuery_Named<MODEL, RESULT> query, int joinIdx, int conditionIdx) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final CompiledFieldReference getJoinConditionComparisonRhs(AdhocQuery_Named<MODEL, RESULT> query, int joinIdx, int conditionIdx) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final boolean evaluateJoinCondition(AdhocQuery_Named<MODEL, RESULT> query, int joinIdx, Object instance1, Object instance2, int conditionIdx, OneToManyJoinConditionResolver oneToManyResolver) {
		return joins[joinIdx].conditions[conditionIdx].evaluate(instance1, instance2, oneToManyResolver);
	}
	

	/**************************************************************************
	** ExeutableQuery⋅
	**************************************************************************/

	@Override
	public final int getAllSourceCount(AdhocQuery_Named<MODEL, RESULT> query) {
		return numSources;
	}

	@Override
	public Class<?> getSourceJavaType(AdhocQuery_Named<MODEL, RESULT> query, int sourceIdx) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public String getSourceName(AdhocQuery_Named<MODEL, RESULT> query, int sourceIdx) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final ConditionsType getRootConditionsType(AdhocQuery_Named<MODEL, RESULT> query) {
		return conditions.getConditionsType();
	}

	@Override
	public final int getRootConditionCount(AdhocQuery_Named<MODEL, RESULT> query) {
		return conditions == null ? 0 : conditions.numConditions;
	}

	@Override
	public final int getRootConditionSourceIdx(AdhocQuery_Named<MODEL, RESULT> query, int conditionIdx) {
		return conditions.conditionToSourceIdx[conditionIdx];
	}

	@Override
	public final EClauseOperator getRootConditionOperator(AdhocQuery_Named<MODEL, RESULT> query, int conditionIdx) {
		return conditions.operators[conditionIdx];
	}

	@Override
	public final boolean evaluateRootCondition(AdhocQuery_Named<MODEL, RESULT> query, Object instance, int conditionIdx, ConditionValuesScratch scratch) {
		return conditions.evaluate(instance, conditionIdx, this);
	}
	
	@Override
	public final int getRootConditionNumFunctions(AdhocQuery_Named<MODEL, RESULT> query, int conditionIdx) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final FunctionBase getRootConditionFunction(AdhocQuery_Named<MODEL, RESULT> query, int conditionIdx, int functionIdx) {
		throw new UnsupportedOperationException("TODO");
	}

	
	@Override
	public final List<FunctionBase> getRootConditionFunctions(AdhocQuery_Named<MODEL, RESULT> query, int conditionIdx) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final Method getForDebugRootConditionLhsMethod(AdhocQuery_Named<MODEL, RESULT> query, int conditionIdx) {
		return getForDebugConditionLhsMethod(query, 0, new int[] { conditionIdx });
	}

	@Override
	public final String getForDebugRootConditionValue(AdhocQuery_Named<MODEL, RESULT> query, int conditionIdx) {
		return getForDebugConditionValue(query, 0, new int[] { conditionIdx });
	}

	@Override
	public final boolean isRootConditionOnly(AdhocQuery_Named<MODEL, RESULT> query) {
		return !conditions.hasSubConditions();
	}

	@Override
	public final ConditionsType getConditionsType(AdhocQuery_Named<MODEL, RESULT> query, int level, int[] conditionIndices) {
		return conditions.getConditionsType(level, conditionIndices);
	}


	@Override
	public final int getConditionSourceIdx(AdhocQuery_Named<MODEL, RESULT> query, int level, int[] conditionIndices) {
		
		if (isSubCondition(query, level, conditionIndices)) {
			throw new IllegalStateException("sub conditions do not have source idx");
		}
		
		return conditions.getConditionSourceIdx(level, conditionIndices);
	}

	@Override
	public final boolean evaluateCondition(AdhocQuery_Named<MODEL, RESULT> query, int level, int[] conditionIndices, Object instance, ConditionValuesScratch scratch) {
		return conditions.evaluateCondition(level, conditionIndices, instance, scratch);
	}

	@Override
	public final boolean isSubCondition(AdhocQuery_Named<MODEL, RESULT> query, int level, int[] conditionIndices) {
		return conditions.isSubCondition(level, conditionIndices);
	}

	@Override
	public final int getConditionsCount(AdhocQuery_Named<MODEL, RESULT> query, int level, int[] conditionIndices) {
		final int count =  conditions.getConditionsCount(level, conditionIndices);

		if (count <= 0) {
			throw new IllegalStateException("count <= 0 : " + count + " at level " + level + " of indices " + Arrays.toString(conditionIndices));
		}

		return count;
	}

	@Override
	public final boolean hasConditions(AdhocQuery_Named<MODEL, RESULT> query) {
		return conditions != null;
	}

	@Override
	public final EClauseOperator getOperator(AdhocQuery_Named<MODEL, RESULT> query, int level, int[] conditionIndices) {
		return conditions.getOperator(level, conditionIndices);
	}

	@Override
	public final int getConditionsMaxDepth(AdhocQuery_Named<MODEL, RESULT> query) {
		return conditions == null ? -1 : conditions.getMaxDepth();
	}

	@Override
	public final int getConditionNumFunctions(AdhocQuery_Named<MODEL, RESULT> query, int level, int[] conditionIndices) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final FunctionBase getConditionFunction(AdhocQuery_Named<MODEL, RESULT> query, int level, int[] conditionIndices, int functionIdx) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final List<FunctionBase> getConditionFunctions(AdhocQuery_Named<MODEL, RESULT> query, int level, int[] conditionIndices) {
		throw new UnsupportedOperationException("TODO");
	}

	
	@Override
	public final Method getForDebugConditionLhsMethod(AdhocQuery_Named<MODEL, RESULT> query, int level, int[] conditionIndices) {
		return conditions.getForDebugConditionLhsMethod(level, conditionIndices, getForDebugSourceClasses());
	}

	@Override
	public final String getForDebugConditionValue(AdhocQuery_Named<MODEL, RESULT> query, int level, int[] conditionIndices) {
		return conditions.getForDebugConditionValue(level, conditionIndices).toString();
	}
	
	@Override
	public final int getEntityResultSourceIdx(AdhocQuery_Named<MODEL, RESULT> query) {
		throw new UnsupportedOperationException("TODO");
	}

	private final Class<?> [] getForDebugSourceClasses() {
		
		final Class<?> [] ret = new Class<?>[numSources];
		
		for (int i = 0; i < numSources; ++ i) {
			final Class<?> type = getTypeFromColl(sources[i]); // EntityUtil.getGenericCollectionType(sources[i]);
			
			if (type == null) {
				throw new IllegalStateException("No generic type for source " + i);
			}

			ret[i] = type;
		}
		
		return ret;
	}
	
	private Class<?> getTypeFromColl(Collection<?> coll) {
		return coll.isEmpty() ? Void.class : coll.iterator().next().getClass();
	}

	/**************************************************************************
	** IAdhocSelectSource
	**************************************************************************/
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public final IAdhocLogical_Where_Or_Join_Singular<MODEL, Object, Object> from(Collection<Object> collection) {
		
		addSource(collection);
		
		return (IAdhocLogical_Where_Or_Join_Singular)this;
	}
	

	/**************************************************************************
	** IAdhocGetEndClause
	**************************************************************************/
	
	@Override
	@SuppressWarnings("unchecked")
	public final RESULT get() {
		final ExecuteQueryPOJOs<AdhocQuery_Named<MODEL, RESULT>> executor = new ExecuteQueryPOJOs<>(this);
		
		final Object ret = executor.execute(this, this, null, null);
		
		return (RESULT)ret;
	}

	/**************************************************************************
	** IAdhocWhere
	**************************************************************************/
	final AdhocConditions<MODEL, RESULT, ?> addWhereGetter(Function<?, ?> getter) {
		
		return addWhere(null, getter);
		
	}
	
	private AdhocConditions<MODEL, RESULT, ?> addWhere(AdhocFunctions<MODEL, RESULT, ?, ?, ?, ?> functions, Function<?, ?> getter) {

		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}

		if (this.conditions == null) {
			this.conditions = createConditions(0);
		}

		this.conditions.addFromOuterWhere(functions, getter);

		return conditions;
	}

	@SuppressWarnings("rawtypes")
	final <R extends Comparable<R>, AND_OR extends IAdhocLogical_And_Or<MODEL, Object, Object>>
	
		ISharedCondition_Comparable_Common_Value // <MODEL, Object, R, AND_OR>
				addComparativeWhere(Function<?, ?> function) {
		
		return (ISharedCondition_Comparable_Common_Value)addWhereGetter(function);
	}
	

	@SuppressWarnings("rawtypes")
	final <R extends Comparable<R>, AND_OR extends IAdhocLogical_And_Or<MODEL, Object, Object>>
	
		ISharedCondition_Equality_Value // <MODEL, Object, R, AND_OR>
	
			addConditionWhere(Function<?, ?> function) {

		return (ISharedCondition_Equality_Value)addWhereGetter(function);
	}
	
	final 
		<
		ENTITY,
		RET extends ISharedLogical_Base<MODEL, RESULT>,

		COMPARABLE_CLAUSE extends ISharedCondition_Comparable_Common_Value<MODEL, RESULT, ?, RET>,
		STRING_CLAUSE extends ISharedCondition_Comparable_String_Value<MODEL, RESULT, RET>>
	
	IAdhocFunctions_Initial<MODEL, RESULT, ENTITY, RET, COMPARABLE_CLAUSE, STRING_CLAUSE> addWhere() {
		
		return new AdhocFunctions<MODEL, RESULT, ENTITY, RET, COMPARABLE_CLAUSE, STRING_CLAUSE>(ConditionsType.SINGLE, this);
		
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public final ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Comparable<?>, ISharedLogical_Base<MODEL, RESULT>> onComparable(
			AdhocFunctions<MODEL, RESULT, ?, ?, ?, ?> functions, Function<?, ? extends Comparable<?>> getter) {
		return (ISharedCondition_Comparable_Common_Base)addWhere(functions, getter);
	}

	@Override
	public final ISharedCondition_Comparable_String_Base<MODEL, RESULT, ISharedLogical_Base<MODEL, RESULT>> onString(
			AdhocFunctions<MODEL, RESULT, ?, ?, ?, ?> functions, StringFunction<?> getter) {
		return addWhere(functions, getter);
	}





	/**************************************************************************
	** IAdhocJoin
	**************************************************************************/

	private static final int JOIN_INCR = 2;
	
	// Compile the sub-join into an AdhocJoin
	private <LEFT, RIGHT> AdhocJoin<MODEL, RESULT> compileJoin(EJoinType joinType, Collection<RIGHT> joinTo, Consumer<IAdhocJoinSub<MODEL, RESULT, LEFT, RIGHT>> consumer) {

		if (joinTo == null) {
			throw new IllegalArgumentException("joinTo == null");
		}
		
		if (consumer == null) {
			throw new IllegalArgumentException("consumer == null");
		}
		
		// Left-side is the last sourceIdx added
		final int leftSourceIdx = numSources - 1;
		final int rightSourceIdx = addSource(joinTo);
		
		if (numJoins == 0) {
			this.joins = new AdhocJoin<?, ?>[INITIAL_JOINS];
			
			// We add all queries as
		}
		else if (numJoins == joins.length) {
			this.joins = Arrays.copyOf(joins,  joins.length  * JOIN_INCR);
		}

		// pass query so that additional AND or OR can be added to the query, not to the join instance
		final AdhocJoin<MODEL, RESULT> join = new AdhocJoin<>(this, joinType, leftSourceIdx, rightSourceIdx);

		this.joins[numJoins ++] = join;

		if (curSource != leftSourceIdx) {
			throw new IllegalStateException("curSourceIdx != leftSourceIdx");
		}
		
		// Anything added within join should be added to rightSourceIdx
		setCurSource(rightSourceIdx);
		
		// Run join statement, this may recurse if there are nested joins
		@SuppressWarnings({ "unchecked", "rawtypes" })
		final Consumer<IAdhocJoinSub> c = (Consumer)consumer;
		c.accept(join);
		
		
		// After build, set source back to original
		setCurSource(leftSourceIdx);
		

		// Check what type of conditions are present in the join after having joined
		switch (join.conditionsType) {
		case NONE:
			// No where-clause, nothing to join
			break;

		case SINGLE:
			// Single-where clause, was never merged since we could not figure whether AND or OR.
			// Merge into current as AND
			if (conditions == null) {
				conditions = createConditions(0);
			}

			// Add where-clause from the join onto root query
			conditions.addWhereFromJoin(join.whereFunctions, join.whereGetter, join.whereOperator, join.whereValue, join.rightSourceIdx);
			break;

		case AND:
		case OR:
			// Was merged from Join into query at time builder was run
			break;
			
		default:
			throw new UnsupportedOperationException("Unknown join conditions type " + join.conditionsType);
		}
		
		return join;
	}


	final <LEFT, RIGHT> void compileInnerJoin(Collection<RIGHT> joinTo, Consumer<IAdhocJoinSub<MODEL, RESULT, LEFT, RIGHT>> consumer) {
		compileJoin(EJoinType.INNER, joinTo, consumer);
	}

	final <LEFT, RIGHT> void compileLeftJoin(Collection<RIGHT> joinTo, Consumer<IAdhocJoinSub<MODEL, RESULT, LEFT, RIGHT>> consumer) {
		compileJoin(EJoinType.LEFT, joinTo, consumer);
	}

	AdhocConditions<MODEL, RESULT, ?> mergeJoinComparison(
			AdhocFunctions<MODEL, RESULT, ?, ?, ?, ?> whereFunctions,
			Function<?, ?> whereGetter, EClauseOperator whereOperator, Object whereValue,
			int sourceIdx,
			ConditionsType type,
			AdhocFunctions<MODEL, RESULT, ?, ?, ?, ?> nextFunctions, Function<?, ?> nextGetter) {
		
		if (this.conditions == null) {
			this.conditions = createConditions(0);
		}
		
		return conditions.mergeJoinComparison(whereFunctions, whereGetter, whereOperator, whereValue, sourceIdx, type, nextFunctions, nextGetter);
	}
}

