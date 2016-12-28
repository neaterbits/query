package com.neaterbits.query.sql.dsl.api;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.crypto.CipherInputStream;

import com.neaterbits.query.sql.dsl.api.entity.OneToManyJoinConditionResolver;
import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

abstract class AdhocQueryClass<MODEL, RESULT>

		extends AdhocQueryBase<MODEL, AdhocQueryClass<MODEL, RESULT>> 
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

	AdhocQueryClass(Function<?, ?> aggregateGetter, EAggregateFunction aggregateFunction, ENumericType aggregateNumericInputType, ENumericType aggregateNumericOutputType) {
		super(aggregateFunction, aggregateNumericInputType, aggregateNumericOutputType);

		if (aggregateGetter == null) {
			throw new IllegalArgumentException("aggregateGetter == null");
		}
		
		this.aggregateGetter = aggregateGetter;
	}


	AdhocQueryClass(ECollectionType collectionType, Collection<?> coll) {

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
	@SuppressWarnings("unchecked")
	public final Object getAggregateResultValue(AdhocQueryClass<MODEL, RESULT> query, Object instance) {
		return aggregateGetter.apply(instance);
	}

	@Override
	public final ExecuteQueryScratch createScratchArea(AdhocQueryClass<MODEL, RESULT> query, QueryMetaModel queryMetaModel) {

		initScratchArea(
				getNumResultParts(this),
				getSourceCount(this),
				
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
	public final int getJoinCount(AdhocQueryClass<MODEL, RESULT> query) {
		return numJoins;
	}

	@Override
	public final EJoinType getJoinType(AdhocQueryClass<MODEL, RESULT> query, int joinIdx) {
		return joins[joinIdx].type;
	}


	@Override
	public final int getJoinLeftSourceIdx(AdhocQueryClass<MODEL, RESULT> query, int joinIdx) {
		return joins[joinIdx].leftSourceIdx;
	}


	@Override
	public final int getJoinRightSourceIdx(AdhocQueryClass<MODEL, RESULT> query, int joinIdx) {
		return joins[joinIdx].rightSourceIdx;
	}


	@Override
	public final int getJoinConditionCount(AdhocQueryClass<MODEL, RESULT> query, int joinIdx) {
		return joins[joinIdx].numConditions;
	}


	@Override
	public final int getJoinConditionLeftSourceIdx(AdhocQueryClass<MODEL, RESULT> query, int joinIdx, int conditionIdx) {
		return joins[joinIdx].leftSourceIdx;
	}


	@Override
	public final int getJoinConditionRightSourceIdx(AdhocQueryClass<MODEL, RESULT> query, int joinIdx, int conditionIdx) {
		return joins[joinIdx].rightSourceIdx;
	}


	@Override
	public final boolean evaluateJoinCondition(AdhocQueryClass<MODEL, RESULT> query, int joinIdx, Object instance1, Object instance2, int conditionIdx, OneToManyJoinConditionResolver oneToManyResolver) {
		return joins[joinIdx].conditions[conditionIdx].evaluate(instance1, instance2, oneToManyResolver);
	}
	

	/**************************************************************************
	** ExeutableQuery⋅
	**************************************************************************/
	@Override
	public final int getSourceCount(AdhocQueryClass<MODEL, RESULT> query) {
		return numSources;
	}

	@Override
	public final ConditionsType getRootConditionsType(AdhocQueryClass<MODEL, RESULT> query) {
		return conditions.getConditionsType();
	}

	@Override
	public final int getRootConditionCount(AdhocQueryClass<MODEL, RESULT> query) {
		return conditions == null ? 0 : conditions.numConditions;
	}

	@Override
	public final int getRootConditionSourceIdx(AdhocQueryClass<MODEL, RESULT> query, int conditionIdx) {
		return conditions.conditionToSourceIdx[conditionIdx];
	}

	@Override
	public final boolean evaluateRootCondition(AdhocQueryClass<MODEL, RESULT> query, Object instance, int conditionIdx, ConditionValuesScratch scratch) {
		return conditions.evaluate(instance, conditionIdx, this);
	}

	@Override
	public final boolean isRootConditionOnly(AdhocQueryClass<MODEL, RESULT> query) {
		return !conditions.hasSubConditions();
	}

	@Override
	public final ConditionsType getConditionsType(AdhocQueryClass<MODEL, RESULT> query, int level, int[] conditionIndices) {
		return conditions.getConditionsType(level, conditionIndices);
	}


	@Override
	public final int getConditionSourceIdx(AdhocQueryClass<MODEL, RESULT> query, int level, int[] conditionIndices) {
		return conditions.getConditionSourceIdx(level, conditionIndices);
	}


	@Override
	public final boolean evaluateCondition(AdhocQueryClass<MODEL, RESULT> query, int level, int[] conditionIndices, Object instance, ConditionValuesScratch scratch) {
		return conditions.evaluateCondition(level, conditionIndices, instance, scratch);
	}
	
	@Override
	public final boolean isSubCondition(AdhocQueryClass<MODEL, RESULT> query, int level, int[] conditionIndices) {
		return conditions.isSubCondition(level, conditionIndices);
	}

	@Override
	public final int getConditionsCount(AdhocQueryClass<MODEL, RESULT> query, int level, int[] conditionIndices) {
		final int count =  conditions.getConditionsCount(level, conditionIndices);

		if (count <= 0) {
			throw new IllegalStateException("count <= 0 : " + count + " at level " + level + " of indices " + Arrays.toString(conditionIndices));
		}

		return count;
	}
	
	@Override
	public EClauseOperator getOperator(AdhocQueryClass<MODEL, RESULT> query, int level, int[] conditionIndices) {
		return conditions.getOperator(level, conditionIndices);
	}

	@Override
	public int getConditionsMaxDepth(AdhocQueryClass<MODEL, RESULT> query) {
		return conditions == null ? -1 : conditions.getMaxDepth();
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
		final ExecuteQueryPOJOs<AdhocQueryClass<MODEL, RESULT>> executor = new ExecuteQueryPOJOs<>(this);
		
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
	final <R extends Comparable<R>, AND_OR extends IAdhocAndOrLogicalClauses<MODEL, Object>>
	
		ISharedClauseComparableCommonValue // <MODEL, Object, R, AND_OR>
				addComparativeWhere(Function<?, ?> function) {
		
		return (ISharedClauseComparableCommonValue)addWhere(function);
	}
	

	@SuppressWarnings("rawtypes")
	final <R extends Comparable<R>, AND_OR extends IAdhocAndOrLogicalClauses<MODEL, Object>>
	
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
			// Was merged from Join into existing 
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

