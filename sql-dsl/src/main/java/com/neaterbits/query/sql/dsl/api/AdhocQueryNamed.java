package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;

import com.neaterbits.query.sql.dsl.api.entity.OneToManyJoinConditionResolver;
import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

abstract class AdhocQueryNamed<MODEL, RESULT>

		extends AdhocQueryBase<MODEL, AdhocQueryNamed<MODEL, RESULT>> 
		implements

			IAdhocEndClauseBase<MODEL, RESULT>,
			
			
			ISharedLogicalClauses<MODEL, RESULT>,
			
			
			ExecuteQueryPOJOsInput {

	
	private static final int INITIAL_SOURCES = 10;
	private static final int INITIAL_JOINS = 10;
	
	@SuppressWarnings("rawtypes")
	private Function aggregateGetter;

	private AdhocConditions<MODEL, RESULT, ?> conditions;

	private Collection<?> [] sources;
	private int numSources;

	private AdhocJoin<?, ?> [] joins;
	private int numJoins;
	
	abstract AdhocConditions<MODEL, RESULT, ?> createConditions(int level);

	AdhocQueryNamed(Function<?, ?> aggregateGetter, EAggregateFunction aggregateFunction, ENumericType aggregateNumericInputType, ENumericType aggregateNumericOutputType) {
		super(aggregateFunction, aggregateNumericInputType, aggregateNumericOutputType);

		if (aggregateGetter == null) {
			throw new IllegalArgumentException("aggregateGetter == null");
		}

		this.aggregateGetter = aggregateGetter;
	}


	AdhocQueryNamed(ECollectionType collectionType, Collection<?> coll) {

		super(collectionType);

		if (coll == null) {
			throw new IllegalArgumentException("coll == null");
		}

		addSource(coll);
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
	public FieldReferenceType getQueryFieldReferenceType(AdhocQueryNamed<MODEL, RESULT> query) {
		return FieldReferenceType.ENTITY;
	}

	@Override
	@SuppressWarnings("unchecked")
	public final Object getAggregateResultValue(AdhocQueryNamed<MODEL, RESULT> query, Object instance) {
		return aggregateGetter.apply(instance);
	}

	@Override
	public final ExecuteQueryScratch createScratchArea(AdhocQueryNamed<MODEL, RESULT> query, QueryMetaModel queryMetaModel) {

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
	public final int getJoinCount(AdhocQueryNamed<MODEL, RESULT> query) {
		return numJoins;
	}

	@Override
	public final EJoinType getJoinType(AdhocQueryNamed<MODEL, RESULT> query, int joinIdx) {
		return joins[joinIdx].type;
	}


	@Override
	public final int getJoinLeftSourceIdx(AdhocQueryNamed<MODEL, RESULT> query, int joinIdx) {
		return joins[joinIdx].leftSourceIdx;
	}


	@Override
	public final int getJoinRightSourceIdx(AdhocQueryNamed<MODEL, RESULT> query, int joinIdx) {
		return joins[joinIdx].rightSourceIdx;
	}

	@Override
	public Class<?> getJoinLeftJavaType(AdhocQueryNamed<MODEL, RESULT> query, int joinIdx) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public Class<?> getJoinRightJavaType(AdhocQueryNamed<MODEL, RESULT> query, int joinIdx) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final int getJoinConditionCount(AdhocQueryNamed<MODEL, RESULT> query, int joinIdx) {
		return joins[joinIdx].numConditions;
	}
	
	@Override
	public EJoinConditionType getJoinConditionType(AdhocQueryNamed<MODEL, RESULT> query, int joinIdx, int conditionIdx) {
		return joins[joinIdx].conditions[conditionIdx].getJoinConditionType();
	}

	@Override
	public final int getJoinConditionLeftSourceIdx(AdhocQueryNamed<MODEL, RESULT> query, int joinIdx, int conditionIdx) {
		return joins[joinIdx].leftSourceIdx;
	}


	@Override
	public final int getJoinConditionRightSourceIdx(AdhocQueryNamed<MODEL, RESULT> query, int joinIdx, int conditionIdx) {
		return joins[joinIdx].rightSourceIdx;
	}

	@Override
	public final String getJoinConditionLeftName(AdhocQueryNamed<MODEL, RESULT> query, int joinIdx, int conditionIdx) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final String getJoinConditionRightName(AdhocQueryNamed<MODEL, RESULT> query, int joinIdx, int conditionIdx) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final Class<?> getJoinConditionLeftJavaType(AdhocQueryNamed<MODEL, RESULT> query, int joinIdx, int conditionIdx) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final Class<?> getJoinConditionRightJavaType(AdhocQueryNamed<MODEL, RESULT> query, int joinIdx, int conditionIdx) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final Method getJoinConditionOneToManyCollectionGetter(AdhocQueryNamed<MODEL, RESULT> query, int joinIdx, int conditionIdx) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final CompiledFieldReference getJoinConditionComparisonLhs(AdhocQueryNamed<MODEL, RESULT> query, int joinIdx, int conditionIdx) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final CompiledFieldReference getJoinConditionComparisonRhs(AdhocQueryNamed<MODEL, RESULT> query, int joinIdx, int conditionIdx) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final boolean evaluateJoinCondition(AdhocQueryNamed<MODEL, RESULT> query, int joinIdx, Object instance1, Object instance2, int conditionIdx, OneToManyJoinConditionResolver oneToManyResolver) {
		return joins[joinIdx].conditions[conditionIdx].evaluate(instance1, instance2, oneToManyResolver);
	}
	

	/**************************************************************************
	** ExeutableQuery⋅
	**************************************************************************/

	@Override
	public final int getAllSourceCount(AdhocQueryNamed<MODEL, RESULT> query) {
		return numSources;
	}

	@Override
	public Class<?> getSourceJavaType(AdhocQueryNamed<MODEL, RESULT> query, int sourceIdx) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public String getSourceName(AdhocQueryNamed<MODEL, RESULT> query, int sourceIdx) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public final ConditionsType getRootConditionsType(AdhocQueryNamed<MODEL, RESULT> query) {
		return conditions.getConditionsType();
	}

	@Override
	public final int getRootConditionCount(AdhocQueryNamed<MODEL, RESULT> query) {
		return conditions == null ? 0 : conditions.numConditions;
	}

	@Override
	public final int getRootConditionSourceIdx(AdhocQueryNamed<MODEL, RESULT> query, int conditionIdx) {
		return conditions.conditionToSourceIdx[conditionIdx];
	}

	@Override
	public EClauseOperator getRootConditionOperator(AdhocQueryNamed<MODEL, RESULT> query, int conditionIdx) {
		return conditions.operators[conditionIdx];
	}

	@Override
	public final boolean evaluateRootCondition(AdhocQueryNamed<MODEL, RESULT> query, Object instance, int conditionIdx, ConditionValuesScratch scratch) {
		return conditions.evaluate(instance, conditionIdx, this);
	}

	@Override
	public Method getForDebugRootConditionLhsMethod(AdhocQueryNamed<MODEL, RESULT> query, int conditionIdx) {
		return getForDebugConditionLhsMethod(query, 0, new int[] { conditionIdx });
	}

	@Override
	public String getForDebugRootConditionValue(AdhocQueryNamed<MODEL, RESULT> query, int conditionIdx) {
		return getForDebugConditionValue(query, 0, new int[] { conditionIdx });
	}

	@Override
	public final boolean isRootConditionOnly(AdhocQueryNamed<MODEL, RESULT> query) {
		return !conditions.hasSubConditions();
	}

	@Override
	public final ConditionsType getConditionsType(AdhocQueryNamed<MODEL, RESULT> query, int level, int[] conditionIndices) {
		return conditions.getConditionsType(level, conditionIndices);
	}


	@Override
	public final int getConditionSourceIdx(AdhocQueryNamed<MODEL, RESULT> query, int level, int[] conditionIndices) {
		return conditions.getConditionSourceIdx(level, conditionIndices);
	}

	@Override
	public final boolean evaluateCondition(AdhocQueryNamed<MODEL, RESULT> query, int level, int[] conditionIndices, Object instance, ConditionValuesScratch scratch) {
		return conditions.evaluateCondition(level, conditionIndices, instance, scratch);
	}

	@Override
	public final boolean isSubCondition(AdhocQueryNamed<MODEL, RESULT> query, int level, int[] conditionIndices) {
		return conditions.isSubCondition(level, conditionIndices);
	}

	@Override
	public final int getConditionsCount(AdhocQueryNamed<MODEL, RESULT> query, int level, int[] conditionIndices) {
		final int count =  conditions.getConditionsCount(level, conditionIndices);

		if (count <= 0) {
			throw new IllegalStateException("count <= 0 : " + count + " at level " + level + " of indices " + Arrays.toString(conditionIndices));
		}

		return count;
	}

	@Override
	public final boolean hasConditions(AdhocQueryNamed<MODEL, RESULT> query) {
		return conditions != null;
	}

	@Override
	public final EClauseOperator getOperator(AdhocQueryNamed<MODEL, RESULT> query, int level, int[] conditionIndices) {
		return conditions.getOperator(level, conditionIndices);
	}

	@Override
	public final int getConditionsMaxDepth(AdhocQueryNamed<MODEL, RESULT> query) {
		return conditions == null ? -1 : conditions.getMaxDepth();
	}
	
	@Override
	public Method getForDebugConditionLhsMethod(AdhocQueryNamed<MODEL, RESULT> query, int level, int[] conditionIndices) {
		return conditions.getForDebugConditionLhsMethod(level, conditionIndices, getForDebugSourceClasses());
	}

	@Override
	public String getForDebugConditionValue(AdhocQueryNamed<MODEL, RESULT> query, int level, int[] conditionIndices) {
		return conditions.getForDebugConditionValue(level, conditionIndices).toString();
	}
	
	@Override
	public int getEntityResultSourceIdx(AdhocQueryNamed<MODEL, RESULT> query) {
		throw new UnsupportedOperationException("TODO");
	}

	private Class<?> [] getForDebugSourceClasses() {
		
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
	public final IAdhocWhereOrJoinSingular<MODEL, Object, Object> from(Collection<Object> collection) {
		
		addSource(collection);
		
		return (IAdhocWhereOrJoinSingular)this;
	}
	

	/**************************************************************************
	** IAdhocGetEndClause
	**************************************************************************/
	
	@Override
	@SuppressWarnings("unchecked")
	public final RESULT get() {
		final ExecuteQueryPOJOs<AdhocQueryNamed<MODEL, RESULT>> executor = new ExecuteQueryPOJOs<>(this);
		
		final Object ret = executor.execute(this, this, null, null);
		
		return (RESULT)ret;
	}

	/**************************************************************************
	** IAdhocWhere
	**************************************************************************/
	final AdhocConditions<MODEL, RESULT, ?> addWhere(Function<?, ?> function) {

		if (this.conditions == null) {
			this.conditions = createConditions(0);
		}

		this.conditions.addFromOuterWhere(function);

		return conditions;
	}

	@SuppressWarnings("rawtypes")
	final <R extends Comparable<R>, AND_OR extends IAdhocAndOrLogicalClauses<MODEL, Object, Object>>
	
		ISharedClauseComparableCommonValue // <MODEL, Object, R, AND_OR>
				addComparativeWhere(Function<?, ?> function) {
		
		return (ISharedClauseComparableCommonValue)addWhere(function);
	}
	

	@SuppressWarnings("rawtypes")
	final <R extends Comparable<R>, AND_OR extends IAdhocAndOrLogicalClauses<MODEL, Object, Object>>
	
		ISharedClauseConditionValue // <MODEL, Object, R, AND_OR>
	
			addConditionWhere(Function<?, ?> function) {

		return (ISharedClauseConditionValue)addWhere(function);
	}

	
	/**************************************************************************
	** IAdhocJoin
	**************************************************************************/

	private static final int JOIN_INCR = 2;
	
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

		final AdhocJoin<MODEL, RESULT> join = new AdhocJoin<>(this, joinType, leftSourceIdx, rightSourceIdx);

		this.joins[numJoins ++] = join;
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		final Consumer<IAdhocJoinSub> c = (Consumer)consumer;

		c.accept(join);

		// Check what type of conditions are present
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

			conditions.addWhereFromJoin(join.whereCondition, join.whereOperator, join.whereValue, join.rightSourceIdx);
			break;

		case AND:
		case OR:
			// Was merged from Join into existing at time builder was run
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
			Function<?, ?> whereFunction, EClauseOperator whereOperator, Object whereValue,
			int sourceIdx,
			ConditionsType type, Function<?, ?> nextFunction) {
		
		final AdhocConditions<MODEL, RESULT, ?> ret;
		
		if (this.conditions == null) {
			this.conditions = createConditions(0);
		}
		
		return conditions.mergeJoinComparison(whereFunction, whereOperator, whereValue, sourceIdx, type, nextFunction);
	}
}

