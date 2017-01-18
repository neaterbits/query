package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Base class for separating out the state-machine of adhoc conditions
 * so that can follow that logic more clearly
 * 
 *
 */

abstract class AdhocConditionsStateMachine<MODEL, RESULT, CONDITIONS extends AdhocConditionsStateMachine<MODEL, RESULT, CONDITIONS>> {

	private static final boolean DEBUG = Boolean.TRUE; 
	
	private EAdhocConditionsState state;


	abstract void intAddConditionToArray(Function<?, ?> function);

	abstract void intAddSub(CONDITIONS sub);
	
	abstract void intAddOperator(EClauseOperator operator, Object value, int sourceIdx);

	abstract void intMoveLastToSubAndAddSub(CONDITIONS sub);
	
	abstract boolean hasSubConditions();
	
	abstract CONDITIONS createConditions(int level);
	
	abstract int getLevel();
	

	AdhocConditionsStateMachine() {
		this.state = EAdhocConditionsState.NONE;
	}
	
	final ConditionsType getConditionsType() {
		
		final ConditionsType ret;
		
		switch (state) {
		case NONE:
			ret = ConditionsType.NONE;
			break;
		case WHERE_FROM_JOIN:
		case WHERE_FROM_OUTER:
			ret = ConditionsType.SINGLE;
			break;

		case AND_IN_JOIN:
		case AND_IN_OUTER:
		case AND_MERGED_FROM_JOIN:
		case AND_IN_NESTED:
			
		case WHERE_FROM_JOIN_AND_WHERE_FROM_OUTER: // Two where-clauses which are to be joind
			
		case AND_FROM_JOIN_AND_OUTER: // AND from both joined and outer
			ret = ConditionsType.AND;
			break;
			
		case OR_IN_JOIN:
		case OR_IN_OUTER:
		case OR_IN_NESTED:
			ret = ConditionsType.OR;
			break;
		
		default:
			throw new IllegalStateException("Unknown conditions state "+ state);
		}
		
		return ret;
	}

	void addOperator(EClauseOperator operator, Object value, int sourceIdx) {
		if (DEBUG) {
			final String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();

			System.out.println("addOperator AdhocCondition." + methodName + "() level " + getLevel() + ", source "
					+ sourceIdx + 
					(operator != null ? (" op " + operator + " value " + value) : "")
					+ " in state  " + state);
		}
		
		intAddOperator(operator, value, sourceIdx);
	}
	
	final <QUERY extends AdhocQueryNamed<MODEL, RESULT>>
			CONDITIONS intAddCondition(ConditionsType conditionsType, Function<?, ?> function, Consumer<?> nestedBuilder) {

		if (function == null && nestedBuilder == null) {
			throw new IllegalArgumentException("function == null && nested == null");
		}

		if (function != null && nestedBuilder != null) {
			throw new IllegalArgumentException("function != null && nested != null");
		}
		

		//conditionsCheck(conditionsType);

		final EAdhocConditionsState newState;
		
		boolean added = false;

		final CONDITIONS ret;
		
		switch (state) {
		case AND_IN_JOIN:
		case AND_IN_OUTER:
		case AND_MERGED_FROM_JOIN:
		case AND_IN_NESTED:

			if (conditionsType != ConditionsType.AND) {
				throw new IllegalStateException("Unxpected condition in state " + state + ": " + conditionsType);
			}

			newState = state;
			ret = getThis();
			break;

		case OR_IN_JOIN:
		case OR_IN_OUTER:
		case OR_IN_NESTED:
			
			if (conditionsType != ConditionsType.OR) {
				throw new IllegalStateException("Unxpected condition in state " + state + ": " + conditionsType);
			}

			newState = state;
			ret = getThis();
			break;

		case WHERE_FROM_OUTER:
			switch (conditionsType) {
			case AND:
				newState = EAdhocConditionsState.AND_IN_OUTER;
				break;

			case OR:
				newState = EAdhocConditionsState.OR_IN_OUTER;
				break;

			default:
				throw new IllegalStateException("Unknown condition type " + conditionsType);
			}
			ret = getThis();
			break;

		case WHERE_FROM_JOIN_AND_WHERE_FROM_OUTER: // Two where-clases, adding a new one in outer
			switch (conditionsType) {
			case AND:
				newState = EAdhocConditionsState.AND_FROM_JOIN_AND_OUTER;
				
				// Nothing to do here, we add at end in regular fashion
				ret = getThis();
				break;

			case OR:
				// Two where-clauses, must create a nested condition and add the latter WHERE to that
				// so that we AND the join-comparison to the OR'ed where and new condition at the outer level
				
				// TODO: What if more than one or here? Looks like we're returning sub below, so
				// one is adding new ORs at outer level to sub
				final int level = getLevel();
				if (level != 0) {
					throw new IllegalStateException("Not at root level");
				}

				final CONDITIONS sub = createConditions(level + 1);

				// Remove last conditions and move to new sub OR-condition ?
				sub.intSplitIntoSubOrs(getThis(), function, nestedBuilder);
				added = true;
				
				ret = sub;

				newState = EAdhocConditionsState.AND_FROM_JOIN_AND_OUTER;
				break;

			default:
				throw new IllegalStateException("Unknown condition type " + conditionsType);
			}
			break;

		default:
			throw new IllegalStateException("Unknown conditions state "+ state);
		}

		if (!added) {
			if (function != null) {
				// If function, just call subclass to add to subclass specific storage (may be multiple implementations for trying to optimize for allocations) 
				intAddConditionToArray(function);
			}
			else if (nestedBuilder != null) {
				// add nested as sub-condition in current
				// nested-condition must have the opposite type of what is present at *this* level
				addNested(conditionsType.opposite(), nestedBuilder, getLevel());
			}
			else {
				throw new IllegalStateException();
			}
		}

		setState(newState, -1, null, null);
		
		return ret;
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	private void addNested(ConditionsType conditionsType, Consumer<?> nestedBuilder, int curLevel) {
		
		final CONDITIONS nested = createConditions(curLevel + 1);
		
		
		// Initiate state-machine for nested
		nested.initNested(conditionsType, nestedBuilder);
		
		// Must call builder in order to add the user-specified conditions
		((Consumer)nestedBuilder).accept(nested);
		
		// Call subclass to add
		intAddSub(nested);
	}

	final void initNested(ConditionsType conditionsType, Consumer<?> nestedBuilder) {

		if (conditionsType == null) {
			throw new IllegalArgumentException("conditionsType == null");
		}

		final EAdhocConditionsState newState;

		switch (state) {
		case NONE:
			switch (conditionsType) {
			case AND:
				newState = EAdhocConditionsState.AND_IN_NESTED;
				break;

			case OR:
				newState = EAdhocConditionsState.OR_IN_NESTED;
				break;

			default:
				throw new UnsupportedOperationException("Unknown conditions type " + conditionsType);
			}
			break;

		default:
			throw new IllegalStateException("Unknown conditions state "+ state);
		
		}

		setState(newState, -1, null, null);
	}
	
	
	// Called when there are AND conditions from joins that must be merged with OR conditions outer-level
	// We must move the outer OR into a nested condition
	// in order to make sure we use AND at the outer level
	// may take either a function, or nested-info if the first or at outer level was a nested one
	final void intSplitIntoSubOrs(
			CONDITIONS c,
			Function<?, ?> function,
			Consumer<?> nestedBuilder) {

		final EAdhocConditionsState newState;

		switch (state) {
		case NONE:
			newState = EAdhocConditionsState.OR_IN_OUTER;

			// Move last from c to this
			c.intMoveLastToSubAndAddSub(getThis());
			
			if (function != null) {
				intAddConditionToArray(function);
			}
			else if (nestedBuilder != null) {
				
				// Add nested as sub to this (so there's three levels involved)
				// Conditions type is always AND since we do this for an OR at the top-level
				addNested(ConditionsType.AND, nestedBuilder, c.getLevel());
			}
			else {
				throw new IllegalStateException();
			}
			break;

		default:
			throw new IllegalStateException("Unknown conditions state "+ state);
		}

		setState(newState, -1, null, null);
	}

	@SuppressWarnings({"unchecked"})
	private CONDITIONS getThis() {
		return (CONDITIONS)this;
	}

	/*
	final void initFromWhere(Function<?, ?> whereFunction) {
		if (this.state != null) {
			throw new IllegalStateException("conditions already set");
		}

		intaddCondition(ConditionsType.SINGLE, whereFunction);
	}
	*/

	final void addFromOuterWhere(Function<?, ?> whereFunction) {

		final EAdhocConditionsState newState;

		switch (state) {
		case NONE:
			newState = EAdhocConditionsState.WHERE_FROM_OUTER;
			break;

		case WHERE_FROM_JOIN:
			// Got one single where-clause from joins, we have to merge
			// queries using AND operator.
			// BUT! We do not know yet what type of outer-query this is.
			// It could be and AND or and OR query, depending. Or just the WHERE clause, in which case it is an AND query
			newState = EAdhocConditionsState.WHERE_FROM_JOIN_AND_WHERE_FROM_OUTER;
			break;
		
		default:
			throw new IllegalStateException("Unexpected state " + state);
		}

		intAddConditionToArray(whereFunction);

		setState(newState, -1, null, null);
	}

	private void setState(EAdhocConditionsState newState, int sourceIdx, EClauseOperator operator, Object value) {

		if (newState == null) {
			throw new IllegalArgumentException("newState == null");
		}

		if (DEBUG) {
			final String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();

			System.out.println("AdhocCondition." + methodName + "() level " + getLevel() + ", source "
					+ sourceIdx + 
					(operator != null ? (" op " + operator + " value " + value) : "")
					+ ": " + state + " => " + newState);
		}

		this.state = newState;
	}

	@SuppressWarnings("unchecked")
	final CONDITIONS mergeJoinComparison(
			Function<?, ?> whereFunction, EClauseOperator whereOperator, Object whereValue,
			int sourceIdx,
			ConditionsType type, Function<?, ?> nextFunction) {

		final CONDITIONS ret;
		
		switch (state) {
		case NONE:
		
			switch (type) {
			
			case OR:
				// Must add sub-condition to current
				// Too complex to add OR at this level so upper state becomes AND
				final CONDITIONS subConditions = createConditions(1);
	
				subConditions.addWhereAndFunctionFromJoin(whereFunction, whereOperator, whereValue, sourceIdx, type, nextFunction);
	
				intAddSub(subConditions);
	
				ret = subConditions;
				break;

			case AND:
				// Add to existing conditions
				addWhereAndFunctionFromJoin(whereFunction, whereOperator, whereValue, sourceIdx, type, nextFunction);
	
				ret = (CONDITIONS)this;
				break;

			default:
				throw new UnsupportedOperationException("Unexpected type " + type);

			}
			break;
			
		default:
			throw new IllegalStateException("Unexpected state " + state);
			
		}

		return ret;
	}
	
	final void addWhereAndFunctionFromJoin(
			Function<?, ?> whereFunction, EClauseOperator whereOperator, Object whereValue,
			int sourceIdx,
			ConditionsType type, Function<?, ?> nextFunction) {

		if (whereFunction == null) {
			throw new IllegalArgumentException("whereFunction == null");
		}
		
		if (whereOperator == null) {
			throw new IllegalArgumentException("whereOperator == null");
		}
		
		if (whereValue == null) {
			throw new IllegalArgumentException("whereValue == null");
		}
		
		
		if (nextFunction == null) {
			throw new IllegalArgumentException("nextFunction == null");
		}

		if (type == null) {
			throw new IllegalArgumentException("type == null");
		}
		
		final EAdhocConditionsState newState;
		
		switch (state) {
		
		case NONE:
			switch (type) {
			case AND:
				newState = EAdhocConditionsState.AND_IN_JOIN;
				break;

			case OR:
				newState = EAdhocConditionsState.OR_IN_JOIN;
				break;

			default:
				throw new IllegalStateException("Unknown condition type " + type);
			}
			break;
		
		default:
			throw new IllegalStateException("Unexpected state " + state);
		}
		
		
		setState(newState, sourceIdx, whereOperator, whereValue);
		
		intAddConditionToArray(whereFunction);
		addOperator(whereOperator, whereValue, sourceIdx);
		
		intAddConditionToArray(nextFunction);
	}

	final void addWhereFromJoin(Function<?, ?> whereFunction, EClauseOperator whereOperator, Object whereValue, int sourceIdx) {
		if (whereFunction == null) {
			throw new IllegalArgumentException("whereFunction == null");
		}

		if (whereOperator == null) {
			throw new IllegalArgumentException("whereOperator == null");
		}

		if (whereValue == null) {
			throw new IllegalArgumentException("whereValue == null");
		}

		final EAdhocConditionsState newState;

		// Set as single if not already set
		switch (state) {
		case NONE:
			newState = EAdhocConditionsState.WHERE_FROM_JOIN;
			break;

		default:
			throw new IllegalStateException("Unexpected state " + state);
		}

		setState(newState, sourceIdx, whereOperator, whereValue);

		intAddConditionToArray(whereFunction);
		addOperator(whereOperator, whereValue, sourceIdx);
	}
}
