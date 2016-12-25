package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

/**
 * Execute a query on a set of POJS
 * 
 * @author nhl
 *
 */

final class ExecuteQueryPOJOs<QUERY> extends ExecutableQueryAggregateComputations<QUERY> {
	
	
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
		
		final int joinCount = q.getJoinCount(query);
		
		Object ret;

		// TODO: Handle 1 result part case? no need for array
		final ExecuteQueryScratch scratch =  q.createScratchArea(query, queryMetaModel);
		
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
			ret = loopJoinedNoSets(query, input, scratch);
		}
		else {
			// Loop over all clauses to test
			ret = loopNonJoined(query, input, scratch);
		}

		final EQueryResultGathering gathering = q.getGathering(query);
		
		if (gathering == EQueryResultGathering.AGGREGATE) {
		
			ret = computeAggregateFinalResult(query, ret);
			
			if (ret == null) {

				// return aggregate default
				ret = q.getAggregateDefault(query);
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
	
	private Collection<?> makeResultColl(QUERY query) {
		// TODO:  Perhaps nested list of some sort for large result sets
		
		final Collection<?> ret;
		final ECollectionType resultType = q.getResultCollectionType(query);

		switch (resultType) {

		case LIST:
			ret = new ArrayList<>();
			break;

		case SET:
			ret = new HashSet<>();
			break;

		default:
			throw new IllegalArgumentException("Unknown result type " + resultType);
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Object addToColl(Object last, Object value) {
		
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		((Collection)last).add(value);
		
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
				ret = addToColl(last, map(query, scratch));
				break;
				
			default:
				throw new UnsupportedOperationException("Unknown query dimension" + dimension);
		}
		
		return ret;
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
	
	
	private Object loopJoinedNoSets(QUERY query, ExecuteQueryPOJOsInput input, ExecuteQueryScratch scratch) {
		final int numSelectSources = q.getSourceCount(query);
		
		final int joinCount = q.getJoinCount(query);
		
		if (joinCount == 0) {
			throw new IllegalStateException("No joins");
		}

		Object result = computeInitialResult(query);
		
		result = loopJoinedNoSets(query, input, 0, numSelectSources, scratch, joinCount, result);
		
		return result;
	}
	
	
	// Non-joined simple case. Means outer-join of all structures
	private Object loopJoinedNoSets(QUERY query,
			ExecuteQueryPOJOsInput input, int sourceIdx, int numSources,
			ExecuteQueryScratch scratch, int numJoins, Object result) {
		
		
		final Collection<?> source = input.getPOJOs(sourceIdx);
		

		for (Object o : source) {
			// Loop over conditions to see if should be added
			
			if (   joinMatches(query, sourceIdx, scratch, o, numJoins)
			    && scratch.getNumConditions()  == 0 || matches(query, sourceIdx, o, scratch)) {
				
				scratch.set(sourceIdx, o);

				if (sourceIdx == numSources - 1) {
					result = computeResult(query, o, result, scratch);
				}
				else {
					// Recurse to next source idx
					loopJoinedNoSets(query, input, sourceIdx + 1,
							numSources, scratch, numJoins, result);
				}
			}
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
					return false;
				}
			}
			else {
				throw new IllegalStateException("Join idxes do not match");
			}
		}

		return true;
	}
	
	
	// ************************ Non-joined loop ************************

	// Non-joined simple case. Means outer-join of all structures
	private Object loopNonJoined(QUERY query, ExecuteQueryPOJOsInput input, ExecuteQueryScratch scratch) {
		final int numSelectSources = q.getSourceCount(query);
		
		Object result = computeInitialResult(query);

		final Object ret = loopNonJoined(query, input, 0, numSelectSources, scratch, result);
		
		/*
		// For each select source, loop over
		for (int sourceIdx = 0; sourceIdx < numSelectSources; ++ sourceIdx) {
			final Collection<?> source = input.getPOJOs(sourceIdx);
			
			for (Object o : source) {
				// Loop over conditions to see if should be added
				if (numConditions == 0 || matches(query, sourceIdx, o, numConditions)) {
					scratch[sourceIdx] = o;
				}
			}
		}
		*/

		return ret;
	}

	// Non-joined simple case. Means outer-join of all structures
	private Object loopNonJoined(QUERY query,
			ExecuteQueryPOJOsInput input, int sourceIdx, int numSources,
			ExecuteQueryScratch scratch, Object result) {
		
		final Collection<?> source = input.getPOJOs(sourceIdx);

		for (Object o : source) {
			// Loop over conditions to see if should be added
			if (scratch.getNumConditions() == 0 || matches(query, sourceIdx, o, scratch)) {
				scratch.set(sourceIdx, o);

				if (sourceIdx == numSources - 1) {
					// emit scratch area
					
					result = computeResult(query, o, result, scratch);
				}
				else {
					// Recurse to next source idx
					loopNonJoined(query, input, sourceIdx + 1,
							numSources, scratch, result);
				}
			}
		}
		
		return result;
	}
	
	
	private boolean matches(QUERY query, int sourceIdx, Object instance, ExecuteQueryScratch scratch) {
		// Loop over conditions
		final ConditionsType type = q.getConditionsType(query);
		
		final boolean ret;
		
		switch (type) {
			case SINGLE:
				ret = match(query, sourceIdx, instance, 0, scratch);
				break;
				
			case AND:
				ret = matchAnd(query, sourceIdx, instance, scratch);
				break;
				
			case OR:
				ret = matchOr(query, sourceIdx, instance, scratch);
				break;
				
			default:
				throw new UnsupportedOperationException("Unknown condition type " + type);
		}
		
		return ret;
	}
	
	private boolean match(QUERY query, int sourceIdx, Object instance, int conditionIdx, ConditionValuesScratch scratch) {
		
		final boolean ret;
		
		if (q.getConditionSourceIdx(query, conditionIdx) == sourceIdx) {
			ret = q.evaluateCondition(query, instance, conditionIdx, scratch);
		}
		else {
			ret = true; // for a different source, return true
		}
		
		return ret;
	}


	private boolean matchAnd(QUERY query, int sourceIdx, Object instance, ExecuteQueryScratch scratch) {
		
		for (int conditionIdx = 0; conditionIdx < scratch.getNumConditions(); ++ conditionIdx) {
			if (!match(query, sourceIdx, instance, conditionIdx, scratch)) {
				return false;
			}
		}
		
		return true;
	}

	private boolean matchOr(QUERY query, int sourceIdx, Object instance, ExecuteQueryScratch scratch) {
		for (int conditionIdx = 0; conditionIdx < scratch.getNumConditions(); ++ conditionIdx) {
			if (match(query, sourceIdx, instance, conditionIdx, scratch)) {
				return true;
			}
		}
		
		return false;
	}

}
