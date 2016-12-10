package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

abstract class QueryResultAggregate extends QueryResult {

	private final Getter getter;
	private final NumericType numericType;
	
	abstract AggregateFunction getAggregateFunction();
	
	QueryResultAggregate(Class<?> type, Getter getter) {
		super(type);
		
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}

		this.getter = getter;
		this.numericType = getNumericType(type);
	}
	
	private static NumericType getNumericType(Class<?> type) {
		final NumericType ret;
		
		if (type.equals(Short.class)) {
			ret = NumericType.SHORT;	
		}
		else if (type.equals(Integer.class)) {
			ret = NumericType.INTEGER;
		}
		else if (type.equals(Long.class)) {
			ret = NumericType.LONG;
		}
		else if (type.equals(BigDecimal.class)) {
			ret = NumericType.DECIMAL;
		}
		else {
			throw new IllegalArgumentException("Not a numeric type: " + type);
		}
		
		return ret;
	}
	

	final Getter getGetter() {
		return getter;
	}

	final NumericType getNumericType() {
		return numericType;
	}

	@Override
	final QueryResultDimension getDimension() {
		return QueryResultDimension.SINGLE;
	}

	@Override
	final QueryResultGathering getGathering() {
		return QueryResultGathering.AGGREGATE;
	}
}
