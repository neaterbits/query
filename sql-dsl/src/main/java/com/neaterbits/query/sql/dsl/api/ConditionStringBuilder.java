package com.neaterbits.query.sql.dsl.api;

import com.neaterbits.query.sql.dsl.api.entity.IEntityModelUtil;

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

	// for convenience when passing to visitors etc
	private final QueryDialect_SQL dialect;
	private final IEntityModelUtil entityModelUtil;
	private final StringBuilder sb;

	abstract void appendParam(Param<?> param);

	abstract PreparedQueryComparisonRHS convertInParam(ConditionValue_Param value);

	ConditionStringBuilder(QueryDialect_SQL dialect, IEntityModelUtil entityModelUtil) {

		if (dialect == null) {
			throw new IllegalArgumentException("dialect == null");
		}

		if (entityModelUtil == null) {
			throw new IllegalArgumentException("entityModelUtil == null");
		}

		this.entityModelUtil = entityModelUtil;
		this.dialect = dialect;
		this.sb = new StringBuilder();
	}

	final QueryDialect_SQL getDialect() {
		return dialect;
	}

	final IEntityModelUtil getEntityModelUtil() {
		return entityModelUtil;
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
