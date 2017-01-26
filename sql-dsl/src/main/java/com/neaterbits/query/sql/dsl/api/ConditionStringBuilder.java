package com.neaterbits.query.sql.dsl.api;

/**
 * Abstract base class for buiding conditions
 * 
 * Difference between subclasses is eg. how to build
 * query parameter names. Some query formats can use named version eg. :param1
 * while other use ? and then set parameters by index.
 * 
 * @author nhl
 *
 */

abstract class ConditionStringBuilder {

	private final StringBuilder sb;

	abstract void appendParam(Param<?> param);
	
	abstract PreparedQueryComparisonRHS convertInParam(ConditionValue_Param value);

	ConditionStringBuilder() {
		this.sb = new StringBuilder();
	}

	final ConditionStringBuilder append(String s) {
		sb.append(s);

		return this;
	}
	
	final ConditionStringBuilder append(char c) {
		sb.append(c);
		
		return this;
	}
	
	final ConditionStringBuilder append(int i) {
		sb.append(i);
		
		return this;
	}
	
	final void clear() {
		sb.setLength(0);
	}

	@Override
	public String toString() {
		throw new UnsupportedOperationException("call getBuiltString()");
	}

	String getBuiltString() {
		return sb.toString();
	}
}
