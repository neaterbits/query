package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;

import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

/**
 * Execute a query on a set of POJS
 * 
 * @author nhl
 *
 */

final class ExecuteQueryPOJOs<QUERY> extends ExecutableQueryAggregateComputations<QUERY> {

	private static final boolean DEBUG = Boolean.TRUE;

	/**
	 * Whether we have found a match within condition loop
	 * 
	 * This is tri-state since for OR statements we only look at one of the soures at a time
	 * particular select-source at a time and we do not cache results.
	 * We rather do one more check at the end over all source indices for cases where we do not definitely know that we have a match in these case
	 * 
	 * Example OR statement:
	 * 
	 *    foo.field1 = "abc"
	 * OR bar.field2 = "def"
	 * 
	 * Here we could fail match on bar.field2 but there could still be a match on bar.field2.
	 * 
	 * However if ALL the fields in the OR statement are bar fields (ie same source) and we cannot find
	 * a match, then we can definitely say we do not have a match for OR statements.
	 * 
	 * For AND statements, it's a bit easier: All conditions have to evaluate to true for a source, otherwise we skip.
	 * However if there where NO conditions for the source, then we're uncertain
	 * 
	 * If we evaluate over all select sources, then all conditions should have been checked - or they wouldn't be in the where clause.
	 * It is however only OR statements that are troublesome as AND/SINGLE would have caused evaluation to fail.
	 * 
	 * 
	 */

	enum EMatch {
		YES,								// Definitely matches, can recurse to next source
		NO,									// Definitely does not match, jo to next instance from this source instead 
		UNCERTAIN_REQUIRES_REEVALUATION,	// Requires re-evaluation at end, typically for OR queries
		NOT_APPLICABLE_TO_SOURCE			// Not applicable to this particular source so should continue to next source. But re-evaluation not needed
	}

	ExecuteQueryPOJOs(ExecutableQuery<QUERY> q) {
		super(q);
	}

	/*
	private static final ResultEmitter sumIntegerEmitter = (result, vals) -> {
		final Value<Integer> val = (Value<Integer>)result;
		
		if (val.val == null) {
			val.val = vals[0];
		}
		else {
			val.val = (Integer)val.val + (Integer)result;
		}
	};
	*/

	
	Object execute(QUERY query, ExecuteQueryPOJOsInput input, ParamValueResolver collectedParams, QueryMetaModel queryMetaModel) {

		final StructuredDebug debug = DEBUG ? new StructuredDebug("execute") : null;
		Object ret;
	
		try {
		
		final int joinCount = q.getJoinCount(query);

		// TODO: Handle 1 result part case? no need for array
		final ExecuteQueryScratch scratch =  q.createScratchArea(query, queryMetaModel);
		
		scratch.setCollectedParams(collectedParams);
		
		if (joinCount > 0) {
			// We shall perform joins over tables
			// figure out which tables to join etc
			// we may be joining on multiple keys

			/*
			for (int joinIdx = 0; joinIdx < joinCount; ++ joinIdx) {
				q.getJoinLeftSourceIdx(query, joinIdx);
				q.getJoinRightSourceIdx(query, joinIdx);
				
			}
			*/
			ret = loopJoinedNoSets(query, input, scratch, debug);
		}
		else {
			// Loop over all clauses to test
			ret = loopNonJoined(query, input, scratch, debug);
		}

		final EQueryResultGathering gathering = q.getGathering(query);
		
		if (gathering == EQueryResultGathering.AGGREGATE) {
		
			ret = computeAggregateFinalResult(query, ret);
			
			if (ret == null) {

				// return aggregate default
				ret = q.getAggregateDefault(query);
			}
		}
		else if (q.getDimension(query) == EQueryResultDimension.MULTI) {
			// return result as processed collection
			ret = ((ResultCollection)ret).asCollection();
		}

		}
		finally {
		
		if (debug != null) {
			debug.output(System.out);
		}
		}

		return ret;
	}
	
	private Object computeInitialResult(QUERY query) {

		final EQueryResultGathering gathering = q.getGathering(query);
		final Object ret;

		switch (gathering) {
		case AGGREGATE:
			ret = computeAggregateInitialResult(query);
			break;

		case ENTITY:
		case MAPPED:
			final EQueryResultDimension dimension = q.getDimension(query);
			switch (dimension) {
			case SINGLE:
				ret = null; // Result is just the single result instance
				break;
				
			case MULTI:
				ret = makeResultColl(query);
				break;
			
			default:
				throw new UnsupportedOperationException("Unknown dimension " + dimension);
			}
			break;
			
		default:
			throw new UnsupportedOperationException("Unknown gathering " + gathering);
		}
		
		
		return ret;
	}
	
	private ResultCollection makeResultColl(QUERY query) {
		// TODO:  Perhaps nested list of some sort for large result sets
		
		final ResultCollection ret;
		final ECollectionType resultType = q.getResultCollectionType(query);
		final EQueryResultGathering gathering = q.getGathering(query);


		final int numGroupBy = q.getGroupByFieldCount(query);
		final int numOrderBy = q.getOrderByFieldCount(query);

		switch (gathering) {
		
		case ENTITY:
			
			if (numGroupBy > 0) {
				throw new IllegalStateException("group by combined with entity query");
			}
			
			switch (resultType) {
			case LIST:
				if (numOrderBy > 0) {
					// Must create an ordered list based on order-by criteria
					ret = new ResultCollection_List_Entity_Ordered<>(q, query);
				}
				else {
					ret = new ResultCollection_List();
				}
				break;
	
			case SET:
				if (numOrderBy > 0) {
					throw new UnsupportedOperationException("TODO - sets and order by > 0, does not make any sense as long as not a SortedSet"); 
				}
				
				ret = new ResultCollection_Set();
				break;
	
			default:
				throw new IllegalArgumentException("Unknown result type " + resultType);
			}
			
			break;
		
		case MAPPED:
			switch (resultType) {
			case LIST:
				// Adds to list, but with a mapping function
				ret = new ResultCollection_List_GroupBy_OrderBy<>(q, query);
				break;
	
			case SET:
				if (numGroupBy > 0 || numOrderBy > 0) {
					throw new UnsupportedOperationException("TODO - sets and gropBy > 0 or order by > 0, does not make any sense as long as not a SortedSet"); 
				}
				ret = new ResultCollection_Set();
				break;
	
			default:
				throw new IllegalArgumentException("Unknown result type " + resultType);
			}
			break;

		default:
			throw new UnsupportedOperationException("Unexpected collection gathering: " + gathering);
		}
		
		
		return ret;
	}
	
	private Object computeResult(QUERY query, Object instance, Object last, ExecuteQueryScratch scratch) {
		
		final EQueryResultGathering gathering = q.getGathering(query);
		
		final Object ret;
		
		switch (gathering) {
		case AGGREGATE:
			ret = addToAggregateResult(query, instance, last, scratch);
			break;
			
		case ENTITY:
			ret = addToEntity(query, last, scratch);
			break;
			
		case MAPPED:
			ret = addToMapped(query, last, scratch); 
			break;
			
		default:
			throw new UnsupportedOperationException("Unknown quert gathering: " + gathering);
		}
		
		return ret;
	}
	
	private Object addToEntity(QUERY query, Object last, ExecuteQueryScratch scratch) {
		
		final EQueryResultDimension dimension = q.getDimension(query);

		final Object ret;

		
		
		if (!scratch.numResultPartsIs(1)) {
			throw new IllegalArgumentException("Expected 1 scratch column");
		}
		
		final Object instance = scratch.get(0);
		
		if (instance == null) {
			throw new IllegalStateException("Got 0 scrach instance");
		}
		
		switch (dimension) {
			case SINGLE:
				if (last != null) {
					throw new IllegalStateException("More than one result for query");
				}
				ret = instance;
				break;
				
			case MULTI:
				ret = addToColl(last, instance);
				break;
				
			default:
				throw new UnsupportedOperationException("Unknown query dimension" + dimension);
		}
		
		return ret;
	}
	
	private Object addToColl(Object last, Object value) {
		
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		((ResultCollection)last).addResult(value);
		
		return last;
	}

	private Object addToMapped(QUERY query, Object last, ExecuteQueryScratch scratch) {
		final EQueryResultDimension dimension = q.getDimension(query);

		final Object ret;
		
		switch (dimension) {
			case SINGLE:
				if (last != null) {
					throw new IllegalStateException("More than one result for query");
				}
				ret = map(query, scratch);
				break;
				
			case MULTI:
				
				final Object toAdd;
				
				if (hasGroupByOrOrderBy(query)) {
					
					// Pass scratch-instance to particular collection that can do grouping and sorting operations
					// by looking at the fields from the scratch instance, not at the entity
					toAdd = scratch; 
				}
				else {
					// no result processing so can just map right away and add to a regular collection
					toAdd = map(query, scratch);
				}

				ret = addToColl(last, toAdd);
				break;
				
			default:
				throw new UnsupportedOperationException("Unknown query dimension" + dimension);
		}
		
		return ret;
	}

	private boolean hasGroupByOrOrderBy(QUERY query) {
		return q.getGroupByFieldCount(query) > 0 || q.getOrderByFieldCount(query) > 0;
	}
	
	private Object map(QUERY query, ExecuteQueryScratch scratch) {
		return ExecuteQueryUtil.mapToOneMappedInstance(q, query, scratch);
	}

	private Object loopJoinedWithSets(QUERY query, int numResultParts, ExecuteQueryPOJOsInput input, int numConditions) {
		return null;
	}
	

	
	// ************************ Joined loop utilizing purely looping ************************
	
	// Loop joined with just comparing over all datastructures that are joinable in a loop, useful and faster for shorter lists of data
	// so similar for relatively short lists compared to cost of creating sets
	// useful as a learning experience as well
	// this is similar to the non-joined case
	//
	// NOTE: Extremely poor performance when joining multiple lists of large numbers
	// eg. n1 * n2 * n3 * n4 .. where nX is the number of elements in list number X 
	// but for two lists of say 10 items each, this is 100 iterations so probably faster.
	// will require measurement for tuning but for later study
	
	
	private Object loopJoinedNoSets(QUERY query, ExecuteQueryPOJOsInput input, ExecuteQueryScratch scratch, StructuredDebug debug) {
		final int numSelectSources = q.getAllSourceCount(query);
		
		final int joinCount = q.getJoinCount(query);
		
		if (joinCount == 0) {
			throw new IllegalStateException("No joins");
		}

		Object result = computeInitialResult(query);
		
		result = loopJoinedNoSets(query, input, 0, numSelectSources, scratch, joinCount, result, false, debug);
		
		return result;
	}
	
	
	// Non-joined simple case. Means outer-join of all structures
	private Object loopJoinedNoSets(QUERY query,
			ExecuteQueryPOJOsInput input, int sourceIdx, int numSources,
			ExecuteQueryScratch scratch, int numJoins, Object result, boolean reevaluationRequired, StructuredDebug debug) {
		
		
		final Collection<?> source = input.getPOJOs(sourceIdx);
		
		if (debug != null) {
			
			debug.addIndent("loopJoinedNoSets sourceIdx=" + sourceIdx);
			
			debug.debug("count=" + source.size() + ", input result=" + result);
		}
		

		for (Object o : source) {
			// Loop over conditions to see if should be added
			if (joinMatches(query, sourceIdx, scratch, o, numJoins)) {

				if (debug != null) {
					debug.debug("join match for " + o);
				}
				

				if (scratch.hasConditions()) {
					
					final EMatch matches = matchesConditions(query, sourceIdx, o, scratch, debug);

					if (debug != null) {
						debug.debug("condition match " + matches + " for " + o);
					}
					
					if (matches == EMatch.NO) {
						// Definite none-match, skip
						continue;
					}
					else if (matches == EMatch.UNCERTAIN_REQUIRES_REEVALUATION) {
						reevaluationRequired = true;
					}
				}
				
				scratch.set(sourceIdx, o);

				if (sourceIdx == numSources - 1) {

					
					if (reevaluationRequired) {
						throw new UnsupportedOperationException("TODO");
					}

					result = computeResult(query, o, result, scratch);

					if (debug != null) {
						debug.debug("end source idx reached, computed result " + result);
					}
				}
				else {
					// Recurse to next source idx
					loopJoinedNoSets(query, input, sourceIdx + 1,
							numSources, scratch, numJoins, result, reevaluationRequired, debug);
				}
			}
			else {
				if (debug != null) {
					debug.debug("no join match for " + o);
				}
			}
		}
		
		if (debug != null) {
			debug.subIndent();
		}
		
		return result;
	}
	
	private boolean joinMatches(QUERY query, int thisSourceIdx, ExecuteQueryScratch scratch, Object o, int numJoins) {
		
		if (numJoins == 0) {
			throw new IllegalArgumentException("numJoins == 0");
		}
		
		// Must join towards all previous data in either order ( <other>.somfield == <this>.somefield or other way around)
		
		for (int s = 0; s < thisSourceIdx; ++ s) {
			// Loop over all joins in query
			for (int joinIdx = 0; joinIdx < numJoins; ++ joinIdx) {

				final int leftJoinSource  = q.getJoinLeftSourceIdx(query, joinIdx);
				final int rightJoinSource = q.getJoinRightSourceIdx(query, joinIdx);
				
				if (leftJoinSource == rightJoinSource) {
					throw new IllegalStateException("Joining to self");
				}
				

				if (s == leftJoinSource && thisSourceIdx == rightJoinSource) {

					if (!joinConditionsMatch(query, thisSourceIdx, o, joinIdx, leftJoinSource, rightJoinSource, scratch)) {
						return false;
					}
					
				}
				else if (s == rightJoinSource && thisSourceIdx == leftJoinSource) {

					if (!joinConditionsMatch(query, thisSourceIdx, o, joinIdx, leftJoinSource, rightJoinSource, scratch)) {
						return false;
					}
					
				}
				else {
					// If no join condition, then we will return a match which in turn means we will not
					// join and will get product of all matching rows
				}
			}
		}

		// No inner loop exit, which means we matched
		return true;
	}

	private boolean joinConditionsMatch(QUERY query, int thisSourceIdx, Object thisInstance, int joinIdx, int joinLeftSourceIdx, int joinRightSourceIdx, ExecuteQueryScratch scratch) {

		final int numJoinConditions = q.getJoinConditionCount(query, joinIdx);

		boolean ret = true;
		
		for (int conditionIdx = 0; conditionIdx < numJoinConditions; ++ conditionIdx) {

			final int conditionLeftSourceIdx  = q.getJoinConditionLeftSourceIdx(query, joinIdx, conditionIdx);
			final int conditionRightSourceIdx = q.getJoinConditionRightSourceIdx(query, joinIdx, conditionIdx);

			// Get instances to match
			if (conditionLeftSourceIdx == conditionRightSourceIdx) {
				throw new IllegalStateException("Joining to self");
			}
			
			if (
				   (conditionLeftSourceIdx == joinLeftSourceIdx && conditionRightSourceIdx == joinRightSourceIdx)
				|| (conditionLeftSourceIdx == joinRightSourceIdx && conditionRightSourceIdx == joinLeftSourceIdx )) {

				// Match
				final Object left;
				final Object right;

				if (thisSourceIdx == conditionLeftSourceIdx) {
					left = thisInstance;
					right = scratch.get(conditionRightSourceIdx);
				}
				else if (thisSourceIdx == conditionRightSourceIdx) {
					left = scratch.get(conditionLeftSourceIdx);
					right = thisInstance;
				}
				else {
					throw new IllegalStateException("thisSourceIdx matches neither left nor right conditions source idx");
				}

				final boolean match = q.evaluateJoinCondition(query, joinIdx, left, right, conditionIdx, scratch.getQueryMetaModel());

				if (!match) {
					// Join condition failed to match so we should skip this instance
					ret = false;
					break;
				}
			}
			else {
				throw new IllegalStateException("Join idxes do not match");
			}
		}

		return ret;
	}
	
	
	// ************************ Non-joined loop ************************

	// Non-joined simple case. Means outer-join of all structures
	private Object loopNonJoined(QUERY query, ExecuteQueryPOJOsInput input, ExecuteQueryScratch scratch, StructuredDebug debug) {

		final int numSelectSources = q.getAllSourceCount(query);
		
		Object result = computeInitialResult(query);

		final Object ret = loopNonJoined(query, input, 0, numSelectSources, scratch, result, false, debug);

		return ret;
	}

	// Non-joined simple case. Means outer-join of all structures
	private Object loopNonJoined(QUERY query,
			ExecuteQueryPOJOsInput input, int sourceIdx, int numSources,
			ExecuteQueryScratch scratch, Object result,
			boolean reevaluationRequired,
			StructuredDebug debug) {

		final Collection<?> source = input.getPOJOs(sourceIdx);

		for (Object o : source) {

			// Loop over conditions to see if should be added
			if (scratch.hasConditions()) {

				final EMatch matches = matchesConditions(query, sourceIdx, o, scratch, debug);

				if (matches == EMatch.NO) {
					// Definite none-match, skip
					continue;
				}
				else if (matches == EMatch.UNCERTAIN_REQUIRES_REEVALUATION) {
					reevaluationRequired = true;
				}
			}

			scratch.set(sourceIdx, o);

			if (sourceIdx == numSources - 1) {
				// emit scratch area
				
				if (reevaluationRequired) {
					throw new UnsupportedOperationException("TODO-reevaluate over all");
				}
				
				result = computeResult(query, o, result, scratch);
			}
			else {
				// Recurse to next source idx
				loopNonJoined(query, input, sourceIdx + 1,
						numSources, scratch, result, reevaluationRequired, debug);
			}
		}
		
		return result;
	}
	
	private EMatch matchesConditions(QUERY query, int sourceIdx, Object instance, ExecuteQueryScratch scratch, StructuredDebug debug) {

		final EMatch ret;

		if (q.isRootConditionOnly(query)) {
			ret = rootOnlyMatches(query, sourceIdx, instance, scratch, debug);
		}
		else {
			// Does not only match on root, we need to evaluate nested subs as well
			final int [] conditionIndices = scratch.assureConditionIndices();
			
			ret = recursiveMatches(q.getExecutableQueryConditions(), query, sourceIdx, instance, 0, conditionIndices, scratch, debug);
		}

		return ret;
	}

	private EMatch recursiveMatches(ExecutableQueryConditions<QUERY> qc, QUERY query, int sourceIdx, Object instance, int level, int [] conditionIndices, ConditionValuesScratch scratch, StructuredDebug debug) {

		if (debug != null) {
			debug.addIndent("recursiveMatches");
			
		}
		
		// Finding recursively everything that matches for a particular source

		final ConditionsType type = qc.getConditionsType(query, level, conditionIndices);

		final EMatch ret;
		
		
		if (debug != null) {
			debug.debug("type=" + type);
		}
		
		switch (type) {
			case SINGLE:
				ret = recursiveMatchSingle(qc, query, sourceIdx, instance, level, conditionIndices, 0, scratch, debug);
				break;
				
			case AND:
				ret = recursiveMatchAnd(qc, query, sourceIdx, instance, level, conditionIndices, scratch, debug);
				break;
				
			case OR:
				ret = recursiveMatchOr(qc, query, sourceIdx, instance, level, conditionIndices, scratch, debug);
				break;
				
			default:
				throw new UnsupportedOperationException("Unknown condition type " + type);
		}
		
		if (debug != null) {
			debug.debug("result=" + ret);
		}
		
		
		if (debug != null) {
			debug.subIndent();
		}

		return ret;
	}

	private EMatch recursiveMatchSingle(ExecutableQueryConditions<QUERY> qc, QUERY query, int sourceIdx, Object instance, int level, int [] conditionIndices, int conditionIdx, ConditionValuesScratch scratch, StructuredDebug debug) {
		
		final EMatch ret;
		
		conditionIndices[level] = conditionIdx;

		final int conditionSourceIdx = qc.getConditionSourceIdx(query, level, conditionIndices);
		
		if (debug != null) {
			debug.addIndent("recursiveMatchSingle sourceIdx=" + sourceIdx  + " level=" + level);
		}
		
		
		if (conditionSourceIdx == sourceIdx) {
			if (qc.isSubCondition(query, level, conditionIndices)) {
				ret = recursiveMatches(qc, query, sourceIdx, instance, level + 1, conditionIndices, scratch, debug);
			}
			else {
				ret = qc.evaluateCondition(query, level, conditionIndices, instance, scratch)
					
					? EMatch.YES  // Definite match
					: EMatch.NO;  // Definite mismatch
			}
		}
		else {
			ret = EMatch.NOT_APPLICABLE_TO_SOURCE; // for a different source, uncertain but for THIS source there is a match
		}
		
		if (debug != null) {
			
			debugMatch(qc, query, level, conditionIndices, ret, debug);
			
			debug.subIndent();
		}
		
		return ret;
	}
	
	private static class ConditionDebug {
		private final EClauseOperator operator;
		private final Method lhsMethod;
		private final String value;

		public ConditionDebug(EClauseOperator operator, Method lhsMethod, String value) {

			this.operator = operator;
			this.lhsMethod = lhsMethod;
			this.value = value;
		}
		
		String getMethodName() {
			return lhsMethod.getDeclaringClass().getSimpleName() + "." + lhsMethod.getName();			
		}
		
		String getConditionsString() {
			return getMethodName() + " " + operator.name() + " " + value;
		}
	}
	
	private ConditionDebug getConditionDebug(ExecutableQueryConditions<QUERY> qc, QUERY query, int level, int [] conditionIndices) {
		final EClauseOperator operator = qc.getOperator(query, level, conditionIndices);
		final Method lhsMethod = qc.getForDebugConditionLhsMethod(query, level, conditionIndices);
		final String value = qc.getForDebugConditionValue(query, level, conditionIndices);

		return new ConditionDebug(operator, lhsMethod, value);
	}
	
	private void debugMatch(ExecutableQueryConditions<QUERY> qc, QUERY query, int level, int [] conditionIndices, EMatch ret, StructuredDebug debug) {
		
		final ConditionDebug conditionDebug = getConditionDebug(qc, query, level, conditionIndices);
		
		debugMatch(conditionDebug, ret, debug);
	}

	private void debugRootMatch(QUERY query, int conditionIdx, EMatch ret, StructuredDebug debug) {
		final EClauseOperator operator = q.getRootConditionOperator(query, conditionIdx);
		final Method lhsMethod = q.getForDebugRootConditionLhsMethod(query, conditionIdx);
		final String value = q.getForDebugRootConditionValue(query, conditionIdx);
		
		final ConditionDebug conditionDebug = new ConditionDebug(operator, lhsMethod, value);
		
		debugMatch(conditionDebug, ret, debug);
	}
	
	private void debugMatch(ConditionDebug conditionDebug, EMatch ret, StructuredDebug debug) {
		
		final String lhsString = conditionDebug.getMethodName();
		
		debug.debug(lhsString, conditionDebug.operator.name(), conditionDebug.value.toString(), " => ", ret != null ? ret.toString() : "null");
	}


	private EMatch recursiveMatchAnd(ExecutableQueryConditions<QUERY> qc, QUERY query, int sourceIdx, Object instance, int level, int [] conditionIndices, ConditionValuesScratch scratch, StructuredDebug debug) {

		int numFromThisSource = 0;

		final int numConditions = qc.getConditionsCount(query, level, conditionIndices);

		if (debug != null) {
			debug.addIndent("recursiveMatchAnd sourceIdx=" + sourceIdx  + " level=" + level);
		}

		for (int conditionIdx = 0; conditionIdx < numConditions; ++ conditionIdx) {
			
			conditionIndices[level] = conditionIdx;
			
			if (qc.isSubCondition(query, level, conditionIndices)) {
				final EMatch subMatch = recursiveMatches(qc, query, sourceIdx, instance, level + 1, conditionIndices, scratch, debug);
				
				switch (subMatch){
				case YES:
					// continue loop
					break;
					
				case NO:
					if (debug != null) {
						final EClauseOperator operator = qc.getOperator(query, level, conditionIndices);
						
						debug.debug("sub MISMATCH conditionIdx " + conditionIdx + ", op=" + operator);
						
						debug.subIndent();
					}
					return EMatch.NO;
					
				case NOT_APPLICABLE_TO_SOURCE:
					// Sub not applicable to source, continue past this one
					throw new UnsupportedOperationException("TODO: What to do about numFromThisSource incrementation here ?");
					
				case UNCERTAIN_REQUIRES_REEVALUATION:
					// Not certain that we can evaluate this one to true, might be true or not, must evaluate at end.
					// So return a provisional match
					return EMatch.UNCERTAIN_REQUIRES_REEVALUATION;

				default:
					throw new UnsupportedOperationException("Unknown match " + subMatch);
				}
			}
			else { 
				final int conditionSourceIdx = qc.getConditionSourceIdx(query, level, conditionIndices);

				if (conditionSourceIdx == sourceIdx) {
				
					if (!qc.evaluateCondition(query, level, conditionIndices, instance, scratch)) {
						if (debug != null) {
							final EClauseOperator operator = qc.getOperator(query, level, conditionIndices);

							debug.debug("MISMATCH conditionIdx " + conditionIdx + ", op=" + operator);
							
							debug.subIndent();
						}
						return EMatch.NO; // Definitely does not match
					}

					++ numFromThisSource; // only count if from this source
				}
			}
		}
		
		final EMatch ret = numFromThisSource == 0
				? EMatch.NOT_APPLICABLE_TO_SOURCE 	// None from this source, so we can continue join even if we did
				: EMatch.YES;  						// Matching all ANDs from this source

		if (debug != null) {
			debug.debug("return " + ret);
			
			debug.subIndent();
		}
		
		return ret;
	}

	private EMatch recursiveMatchOr(ExecutableQueryConditions<QUERY> qc, QUERY query, int sourceIdx, Object instance, int level, int [] conditionIndices, ConditionValuesScratch scratch, StructuredDebug debug) {
		
		if (debug != null) {
			debug.addIndent("recursiveMatchOr sourceIdx=" + sourceIdx + " level=" + level);
		}
		
		final int numConditions = qc.getConditionsCount(query, level, conditionIndices);

		int numFromThisSource = 0;

		for (int conditionIdx = 0; conditionIdx < numConditions; ++ conditionIdx) {
			
			conditionIndices[level] = conditionIdx;
			
				
			if (qc.isSubCondition(query, level, conditionIndices)) {
				final EMatch subMatch = recursiveMatches(qc, query, sourceIdx, instance, level + 1, conditionIndices, scratch, debug);
				
				switch (subMatch){
				case YES:
					if (debug != null) {
						final EClauseOperator operator = qc.getOperator(query, level, conditionIndices);
						
						debug.debug("sub MATCH conditionIdx " + conditionIdx + ", op=" + operator);
						
						debug.subIndent();
					}
					return EMatch.YES;
					
				case NO:
					if (debug != null) {
						debug.debug("sub mismatch conditionIdx " + conditionIdx);
					}
					break;
					
				case NOT_APPLICABLE_TO_SOURCE:
					// Sub not applicable to source, continue past this one
					throw new UnsupportedOperationException("TODO: What to do about numFromThisSource incrementation here ?");
					
				case UNCERTAIN_REQUIRES_REEVALUATION:
					// Not certain that we can evaluate this one to true, might be true or not, must evaluate at end.
					throw new UnsupportedOperationException("TODO: How to evaluate this one ? Perhaps keep variable of result");
					
				default:
					throw new UnsupportedOperationException("Unknown match " + subMatch);
				}
				
			}
			else {
				
				final int conditionSourceIdx = qc.getConditionSourceIdx(query, level, conditionIndices);
				
				System.out.println("## got source idx " + conditionSourceIdx + " for level " + level + ", indices: " + Arrays.toString(conditionIndices));
				

				if (conditionSourceIdx == sourceIdx) { // only count if for this source

					if (debug != null) {
						debug.debug("evaluating this-level condition level " + level + ", source " + sourceIdx + " on indices " + Arrays.toString(conditionIndices));
					}
				
					if (qc.evaluateCondition(query, level, conditionIndices, instance, scratch)) {
	
						if (debug != null) {
							final EClauseOperator operator = qc.getOperator(query, level, conditionIndices);

							debug.debug("MATCH conditionIdx " + conditionIdx + ", op=" + operator);

							debug.subIndent();
						}
	
						return EMatch.YES; // One of conditions matched, so we have a match
					}

					++ numFromThisSource;
				}
			}
		}
		
		
		final EMatch ret = numConditions == numFromThisSource
				? EMatch.NO										// All conditions in OR were from this source and not matched, so there is definitely no match
				: EMatch.UNCERTAIN_REQUIRES_REEVALUATION;		// No conditions of this source matched but other sources might so we say this is uncertain and match later

		if (debug != null) {
			debug.debug("return end match numConditions=" + numConditions + ", numFromThisSource=" + numFromThisSource + " : " + ret);
			
			debug.subIndent();
		}

		return ret;
	}
	
	private EMatch rootOnlyMatches(QUERY query, int sourceIdx, Object instance, ExecuteQueryScratch scratch, StructuredDebug debug) {
		// Loop over conditions
		final ConditionsType type = q.getRootConditionsType(query);
		
		final EMatch ret;
		
		switch (type) {
			case SINGLE:
				ret = rootOnlyMatchSingle(query, sourceIdx, instance, 0, scratch, debug);
				break;
				
			case AND:
				ret = rootOnlyMatchAnd(query, sourceIdx, instance, scratch, debug);
				break;
				
			case OR:
				ret = rootOnlyMatchOr(query, sourceIdx, instance, scratch, debug);
				break;
				
			default:
				throw new UnsupportedOperationException("Unknown condition type " + type);
		}
		
		return ret;
	}
	
	private EMatch rootOnlyMatchSingle(QUERY query, int sourceIdx, Object instance, int conditionIdx, ConditionValuesScratch scratch, StructuredDebug debug) {
		
		if (debug != null) {
			debug.addIndent("rootOnlyMatchSingle sourceIdx=" + sourceIdx);
		}
		
		final EMatch ret;
		
		final int conditionSourceIdx = q.getRootConditionSourceIdx(query, conditionIdx);
		
		if (conditionSourceIdx == sourceIdx) {
			ret = q.evaluateRootCondition(query, instance, conditionIdx, scratch)
					
					? EMatch.YES  // Definite match
					: EMatch.NO;  // Definite mismatch
		}
		else {
			ret = EMatch.NOT_APPLICABLE_TO_SOURCE; // for a different source, uncertain but for THIS source there is a match
		}
		
		if (debug != null) {
			debugRootMatch(query, conditionIdx, ret, debug);
			
			debug.subIndent();
		}
		
		return ret;
	}


	private EMatch rootOnlyMatchAnd(QUERY query, int sourceIdx, Object instance, ExecuteQueryScratch scratch, StructuredDebug debug) {

		int numFromThisSource = 0;
		
		if (debug != null) {
			debug.addIndent("rootOnlyMatchAnd sourceIdx=" + sourceIdx);
		}

		for (int conditionIdx = 0; conditionIdx < scratch.getNumConditions(); ++ conditionIdx) {
			if (q.getRootConditionSourceIdx(query, conditionIdx) == sourceIdx) {
				
				if (!q.evaluateRootCondition(query, instance, conditionIdx, scratch)) {
					
					
					final EMatch ret = EMatch.NO;
					if (debug != null) {
						debugRootMatch(query, conditionIdx, ret, debug);
						debug.debug("MISMATCH conditionIdx=" + conditionIdx);
						
						debug.subIndent();
					}
					return ret; // Definitely does not match
				}
				
				++ numFromThisSource;
			}
			
		}
		
		final EMatch ret = numFromThisSource == 0
				? EMatch.NOT_APPLICABLE_TO_SOURCE 	// None from this source, so we can continue join even if we did
				: EMatch.YES;  						// Matching all ANDs from this source

		if (debug != null) {
			debug.debug("return " + ret);
			
			debug.subIndent();
		}
		
		return ret;
	}

	private EMatch rootOnlyMatchOr(QUERY query, int sourceIdx, Object instance, ExecuteQueryScratch scratch, StructuredDebug debug) {
		
		final int numConditions = scratch.getNumConditions();

		int numFromThisSource = 0;

		if (debug != null) {
			debug.addIndent("rootOnlyMatchOr sourceIdx=" + sourceIdx);
		}
		
		for (int conditionIdx = 0; conditionIdx < numConditions; ++ conditionIdx) {
			if (q.getRootConditionSourceIdx(query, conditionIdx) == sourceIdx) {
				
				if (q.evaluateRootCondition(query, instance, conditionIdx, scratch)) {

					final EMatch ret = EMatch.YES;
					
					if (debug != null) {
						debugRootMatch(query, conditionIdx, ret, debug);
						debug.debug("MATCH conditionIdx " + conditionIdx);
					}

					return ret; // One of conditions matched, so we have a match
				}
				
				++ numFromThisSource;
			}
		}

		final EMatch ret = numConditions == numFromThisSource
				? EMatch.NO										// All conditions in OR were from this source and not matched, so there is definitely no match
				: EMatch.UNCERTAIN_REQUIRES_REEVALUATION;		// No conditions of this source matched but other sources might so we say this is uncertain and match later

		if (debug != null) {
			debug.debug("return " + ret);
			
			debug.subIndent();
		}

		return ret;
	}

	/*
	private static void debug(String s) {
		System.out.println(s);
	}

	private static void debug(int indent, String s) {

		for (int i = 0; i < indent; ++ i) {
			System.out.append("  ");
		}

		System.out.println(s);
	}
*/	
}
