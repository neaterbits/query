package com.neaterbits.query.sql.dsl.api;

class ConditionValuesScratch {

	private ParamValueResolver collectedParams;
	private Object lhs;
	private Object rhs;
	
	final ParamValueResolver getCollectedParams() {
		return collectedParams;
	}
	
	final void setCollectedParams(ParamValueResolver collectedParams) {
		this.collectedParams = collectedParams;
	}

	final void init(Object lhs, Object rhs) {
		
		// rhs should never be null
		if (rhs == null) {
			throw new IllegalArgumentException("rhs == null");
		}
		
		this.lhs = lhs;
		this.rhs = rhs;
	}

	final Object getLhs() {
		return lhs;
	}

	final Object getRhs() {
		return rhs;
	}

	@SuppressWarnings("unchecked")
	final Comparable<Object> getLhsComparable() {
		return (Comparable<Object>)lhs;
	}

	@SuppressWarnings("unchecked")
	final Comparable<Object> getRhsComparable() {
		return (Comparable<Object>)rhs;
	}
}
