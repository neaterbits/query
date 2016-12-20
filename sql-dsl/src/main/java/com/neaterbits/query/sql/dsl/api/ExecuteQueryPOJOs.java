package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Execute a query on a set of POJS
 * 
 * @author nhl
 *
 */

final class ExecuteQueryPOJOs<QUERY> extends ExecutableQueryAggregateComputations<QUERY> {
	
	interface ResultEmitter<RESULT> {
		<QRY> void emit(RESULT result, Object [] vals);
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

	
	void execute(QUERY query, ExecuteQueryPOJOsInput input) {

		
		final int joinCount = q.getJoinCount(query);
		
		if (joinCount > 0) {
			// We shall perform joins over tables
			// figure out which tables to join etc
			// we may be joining on multiple keys
			
			for (int joinIdx = 0; joinIdx < joinCount; ++ joinIdx) {
				q.getJoinLeftSourceIdx(query, joinIdx);
				q.getJoinRightSourceIdx(query, joinIdx);
			}
		}
		else {
			// Loop over all clauses to test
			final int numConditions = q.getConditionCount(query);

			final int numResultParts = q.getNumResultParts(query);
			
			loopNonJoined(query, numResultParts, input, numConditions);
		}
	}
	
	private Object computeInitialResult(QUERY query) {

		final QueryResultGathering gathering = q.getGathering(query);
		final Object ret;

		switch (gathering) {
		case AGGREGATE:
			ret = computeAggregateInitialResult(query);
			break;

		case ENTITY:
		case MAPPED:
			final QueryResultDimension dimension = q.getDimension(query);
			switch (dimension) {
			case SINGLE:
				ret = null; // Result is just the single result instance
				break;
				
			case MULTI:
				ret = makeResultList(); 
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
	
	private List<?> makeResultList() {
		// TODO:  Perhaps nested list of some sort for large result sets
		return new ArrayList<>();
	}
	
	private Object computeResult(QUERY query, Object last, Object [] scratch) {
		
		final QueryResultGathering gathering = q.getGathering(query);
		
		//fortsett: Kan i stedet kalle denne logikken fra inner-loop og så bare holde en variabel som sendes inn og returners for hver gang
		
		final Object ret;
		
		switch (gathering) {
		case AGGREGATE:
			ret = addToAggregateResult(query, last, scratch);
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
	
	private Object addToEntity(QUERY query, Object last, Object [] scratch) {
		
		final QueryResultDimension dimension = q.getDimension(query);

		final Object ret;

		if (scratch.length != 1) {
			throw new IllegalArgumentException("Expected 1 scratch column");
		}
		
		final Object instance = scratch[0];
		
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
				ret = addToList(last, instance);
				break;
				
			default:
				throw new UnsupportedOperationException("Unknown query dimension" + dimension);
		}
		
		return ret;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Object addToList(Object last, Object value) {
		
		if (value == null) {
			throw new IllegalArgumentException("value == null");
		}
		
		((List)last).add(value);
		
		return last;
	}

	private Object addToMapped(QUERY query, Object last, Object [] scratch) {
		
		final QueryResultDimension dimension = q.getDimension(query);

		final Object ret;
		
		switch (dimension) {
			case SINGLE:
				if (last != null) {
					throw new IllegalStateException("More than one result for query");
				}
				ret = map(query, scratch);
				break;
				
			case MULTI:
				ret = addToList(last, map(query, scratch));
				break;
				
			default:
				throw new UnsupportedOperationException("Unknown query dimension" + dimension);
		}
		
		return ret;
	}

	private Object map(QUERY query, Object [] scratch) {
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
	
	
	private Object loopJoinedNoSets(QUERY query, int numResultParts, ExecuteQueryPOJOsInput input, int numConditions) {
		final int numSelectSources = q.getSourceCount(query);
		
		// TODO: Handle 1 result part case? no need for array
		final Object[] scratch = new Object[numResultParts];

		final int joinCount = q.getJoinCount(query);
		
		if (joinCount == 0) {
			throw new IllegalStateException("No joins");
		}

		Object result = computeInitialResult(query);
		
		result = loopJoinedNoSets(query, numResultParts, input, 0, numSelectSources, scratch, numConditions, joinCount, result);
		
		return result;
	}
	
	
	// Non-joined simple case. Means outer-join of all structures
	private Object loopJoinedNoSets(QUERY query, int numResultParts,
			ExecuteQueryPOJOsInput input, int sourceIdx, int numSources,
			Object [] scratch, int numConditions, int numJoins, Object result) {
		
		
		final Collection<?> source = input.getPOJOs(sourceIdx);
		

		for (Object o : source) {
			// Loop over conditions to see if should be added
			
			if (   joinMatches(query, sourceIdx, scratch, o, numJoins)
			    && numConditions == 0 || matches(query, sourceIdx, o, numConditions)) {
				
				scratch[sourceIdx] = o;

				if (sourceIdx == numSources - 1) {
					// emit scratch area
					
					result = computeResult(query, result, scratch);
				}
				else {
					// Recurse to next source idx
					loopNonJoined(query, numResultParts, input, sourceIdx + 1,
							numSources, scratch, numConditions, result);
				}
			}
		}
		
		return result;
	}
	
	private boolean joinMatches(QUERY query, int thisSourceIdx, Object [] scratch, Object o, int numJoins) {
		
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

	private boolean joinConditionsMatch(QUERY query, int thisSourceIdx, Object thisInstance, int joinIdx, int joinLeftSourceIdx, int joinRightSourceIdx, Object [] scratch) {

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
					right = scratch[conditionRightSourceIdx];
				}
				else if (thisSourceIdx == conditionRightSourceIdx) {
					left = scratch[conditionLeftSourceIdx];
					right = thisInstance;
				}
				else {
					throw new IllegalStateException("thisSourceIdx matches neither left nor right conditions source idx");
				}

				final boolean match = q.evaluateJoinCondition(query, joinIdx, left, right, conditionIdx);

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
	private Object loopNonJoined(QUERY query, int numResultParts, ExecuteQueryPOJOsInput input, int numConditions) {
		final int numSelectSources = q.getSourceCount(query);
		
		// TODO: Handle 1 result part case? no need for array
		final Object[] scratch = new Object[numResultParts];

		Object result = computeInitialResult(query);

		final Object ret = loopNonJoined(query, numResultParts, input, 0, numSelectSources, scratch, numConditions, result);
		
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
	private Object loopNonJoined(QUERY query, int numResultParts,
			ExecuteQueryPOJOsInput input, int sourceIdx, int numSources,
			Object [] scratch, int numConditions, Object result) {
		
		
		final Collection<?> source = input.getPOJOs(sourceIdx);
		
		

		for (Object o : source) {
			// Loop over conditions to see if should be added
			if (numConditions == 0 || matches(query, sourceIdx, o, numConditions)) {
				scratch[sourceIdx] = o;

				if (sourceIdx == numSources - 1) {
					// emit scratch area
					
					result = computeResult(query, result, scratch);
				}
				else {
					// Recurse to next source idx
					loopNonJoined(query, numResultParts, input, sourceIdx + 1,
							numSources, scratch, numConditions, result);
				}
			}
		}
		
		return result;
	}
	
	
	private boolean matches(QUERY query, int sourceIdx, Object instance, int numConditions) {
		// Loop over conditions
		final ConditionsType type = q.getConditionType(query);
		
		final boolean ret;
		
		switch (type) {
			case SINGLE:
				ret = match(query, sourceIdx, instance, 0);
				break;
				
			case AND:
				ret = matchAnd(query, sourceIdx, instance, numConditions);
				break;
				
			case OR:
				ret = matchOr(query, sourceIdx, instance, numConditions);
				break;
				
			default:
				throw new UnsupportedOperationException("Unknown condition type " + type);
		}
		
		return ret;
	}
	
	private boolean match(QUERY query, int sourceIdx, Object instance, int conditionIdx) {
		
		final boolean ret;
		
		if (q.getConditionSourceIdx(query, conditionIdx) == sourceIdx) {
			ret = q.evaluateCondition(query, instance, conditionIdx);
		}
		else {
			ret = true; // for a different source, return true
		}
		
		return ret;
	}


	private boolean matchAnd(QUERY query, int sourceIdx, Object instance, int numConditions) {
		
		for (int conditionIdx = 0; conditionIdx < numConditions; ++ conditionIdx) {
			if (!match(query, sourceIdx, instance, conditionIdx)) {
				return false;
			}
		}
		
		return true;
	}

	private boolean matchOr(QUERY query, int sourceIdx, Object instance, int numConditions) {
		for (int conditionIdx = 0; conditionIdx < numConditions; ++ conditionIdx) {
			if (match(query, sourceIdx, instance, conditionIdx)) {
				return true;
			}
		}
		
		return false;
	}

	
}