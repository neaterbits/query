package com.neaterbits.query.sql.dsl.api;

final class ConditionValuesScratch {

	private ParamValueResolver collectedParams;
	private Object lhs;
	private Object rhs;
	
	ParamValueResolver getCollectedParams() {
		return collectedParams;
	}
	
	void setCollectedParams(ParamValueResolver collectedParams) {
		this.collectedParams = collectedParams;
	}

	void init(Object lhs, Object rhs) {
		
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
