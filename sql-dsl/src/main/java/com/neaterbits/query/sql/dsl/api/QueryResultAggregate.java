package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

abstract class QueryResultAggregate extends QueryResult {

	private final Getter getter;
	private final ENumericType numericType;
	
	abstract EAggregateFunction getAggregateFunction();
	
	QueryResultAggregate(Class<?> type, Getter getter) {
		super(type);
		
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}

		this.getter = getter;
		this.numericType = getNumericType(type);
	}
	
	private static ENumericType getNumericType(Class<?> type) {
		final ENumericType ret;
		
		if (type.equals(Short.class)) {
			ret = ENumericType.SHORT;	
		}
		else if (type.equals(Integer.class)) {
			ret = ENumericType.INTEGER;
		}
		else if (type.equals(Long.class)) {
			ret = ENumericType.LONG;
		}
		else if (type.equals(BigDecimal.class)) {
			ret = ENumericType.DECIMAL;
		}
		else {
			throw new IllegalArgumentException("Not a numeric type: " + type);
		}
		
		return ret;
	}
	

	final Getter getGetter() {
		return getter;
	}

	final ENumericType getNumericType() {
		return numericType;
	}

	@Override
	final EQueryResultDimension getDimension() {
		return EQueryResultDimension.SINGLE;
	}

	@Override
	final EQueryResultGathering getGathering() {
		return EQueryResultGathering.AGGREGATE;
	}
}
