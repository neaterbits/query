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

	// Non-joined simple case. Means outer-join of all structures
	private Object loopNonJoined(QUERY query, int numResultParts, ExecuteQueryPOJOsInput input, int numConditions) {
		final int numSelectSources = q.getSourceCount(query);
		
		// TODO: Handle 1 result part case
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
