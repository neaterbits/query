package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

/**
 * Base class for separating out the state-machine of adhoc conditions
 * so that can follow that logic more clearly
 * 
 *
 */

abstract class AdhocConditionsStateMachine<MODEL, RESULT, CONDITIONS extends AdhocConditionsStateMachine<MODEL, RESULT, CONDITIONS>> {

	private EAdhocConditionsState state;

	abstract void intAddConditionToArray(Function<?, ?> function);

	abstract void intAddSub(CONDITIONS sub);
	
	abstract void intAddOperator(EClauseOperator operator, Object value, int sourceIdx);
	
	abstract boolean hasSubConditions();
	
	abstract CONDITIONS createConditions(int level);
	
	abstract int getLevel();

	AdhocConditionsStateMachine() {
		this.state = EAdhocConditionsState.NONE;
	}
	
	/*
	private void conditionsCheck(ConditionsType conditionsType) {

		if (conditionsType == null) {
			throw new IllegalArgumentException("conditionsType == null");
		}

		if (this.state == null) {
			if (conditionsType != ConditionsType.SINGLE) {
				throw new IllegalArgumentException("Expected single-condition");
			}
			this.state = conditionsType;
		}
		else {
			if (this.state == ConditionsType.SINGLE) {
				this.state = conditionsType;
			}
			else if (conditionsType != this.state) {
				throw new IllegalStateException("Mismatch in condition from " + conditionsType + " to " + this.state);
			}
		}
	}
	*/

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
			
		case WHERE_FROM_JOIN_AND_WHERE_FROM_OUTER: // Two where-clauses which become AND
			
		case AND_FROM_JOIN_AND_OUTER: // AND from both joined and outer
			ret = ConditionsType.AND;
			break;
			
		case OR_IN_JOIN:
		case OR_IN_OUTER:
			ret = ConditionsType.OR;
			break;
		
		default:
			throw new IllegalStateException("Unknown conditions state "+ state);
		}
		
		return ret;
	}
	
	/*
	final boolean hasJoinOrSubComparisons() {
		final boolean ret;

		if (hasSubConditions()) {
			ret = true;
		}
		else {
			switch (state) {
			case NONE:
				ret = false;
				break;
			case WHERE_FROM_JOIN:
				ret = true;
				break;
				
			case WHERE_FROM_OUTER:
				ret = false;
				break;
	
			case AND_IN_JOIN:
			case AND_IN_OUTER:
			case AND_MERGED_FROM_JOIN:
				
			case WHERE_FROM_JOIN_AND_WHERE_FROM_OUTER: // To where-clauses which become AND
				ret = false;
				break;
				
			case OR_IN_JOIN:
				ret = true;
				break;
				
			case OR_IN_OUTER:
				ret = false;
				break;
			
			default:
				throw new IllegalStateException("Unknown conditions state "+ state);
			}
		}

		return ret;
	}
	*/
	

	final CONDITIONS intAddCondition(ConditionsType conditionsType, Function<?, ?> function) {

		if (function == null) {
			throw new IllegalArgumentException("function == null");
		}

		//conditionsCheck(conditionsType);

		final EAdhocConditionsState newState;

		final CONDITIONS ret;
		
		switch (state) {
		case AND_IN_JOIN:
		case AND_IN_OUTER:
		case AND_MERGED_FROM_JOIN:
		case OR_IN_JOIN:
		case OR_IN_OUTER:
			// Keep current state
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

		case WHERE_FROM_JOIN_AND_WHERE_FROM_OUTER:
			switch (conditionsType) {
			case AND:
				newState = EAdhocConditionsState.AND_FROM_JOIN_AND_OUTER;
				ret = getThis();
				break;

			case OR:
				// Two where-clauses, must create a sub-join and add that
				final int level = getLevel();
				if (level != 0) {
					throw new IllegalStateException("Not at root level");
				}

				final CONDITIONS sub = createConditions(level + 1);
				
				sub.intAddSplitOr(function);
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

		setState(newState);

		intAddConditionToArray(function);
		
		return ret;
	}
	
	void intAddSplitOr(Function<?, ?> function) {

		if (function == null) {
			throw new IllegalArgumentException("function == null");
		}
			
		
		final EAdhocConditionsState newState;

		switch (state) {
		case NONE:
			newState = EAdhocConditionsState.OR_IN_OUTER;
			break;

		default:
			throw new IllegalStateException("Unknown conditions state "+ state);
		}

		setState(newState);
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
		
		setState(newState);
	}
	
	private void setState(EAdhocConditionsState newState) {
		if (newState == null) {
			throw new IllegalArgumentException("newState == null");
		}

		this.state = newState;
	}
	
	@SuppressWarnings("unchecked")
	final CONDITIONS mergeJoinComparison(
			Function<?, ?> whereFunction, EClauseOperator whereOperator, Object whereValue,
			int sourceIdx,
			ConditionsType type, Function<?, ?> nextFunction) {

		final CONDITIONS ret;
		
		switch (type) {
		
		case OR:
			// Must add sub-condition to current
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
		
		
		setState(newState);
		
		intAddConditionToArray(whereFunction);
		intAddOperator(whereOperator, whereValue, sourceIdx);
		
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

		setState(newState);

		intAddConditionToArray(whereFunction);
		intAddOperator(whereOperator, whereValue, sourceIdx);
	}

	/*
	final void addSub(ConditionsType type, AdhocConditions<MODEL, RESULT, ?> sub) {
		
		if (type == null) {
			throw new IllegalArgumentException("type == null");
		}
		
		if (sub == null) {
			throw new IllegalArgumentException("sub == null");
		}
		
		
		switch (state) {
		default:
			break;
		}

		//initConditionsType(type);
		
		intAddSub(sub);
	}
	*/
	
	/*
	
	private void initConditionsType(ConditionsType type) {
		if (type != ConditionsType.AND && type != ConditionsType.OR) {
			throw new IllegalArgumentException("Expected AND or OR condition type:  " + state);
		}

		if (this.state == null) {
			this.state = type;
		}
		else {
			switch (this.state) {
			case SINGLE:
				this.state = type;
				break;

			case AND:
			case OR:
				if (this.state != type) {
					throw new IllegalStateException("Conditions type mismatch: " + this.state + "/" + type);
				}
				break;

			default:
				throw new IllegalStateException("Unknown condition type " + type);
			}
		}
	}
	*/
}
