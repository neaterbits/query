package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.Date;

abstract class CollectedQueryResult_Aggregate extends CollectedQueryResult {

	private final Getter getter;
	private final EAggregateType inputNumericType;
	private final EAggregateType outputNumericType;
	
	abstract EAggregateFunction getAggregateFunction();
	
	CollectedQueryResult_Aggregate(Class<?> type, Getter getter) {
		super(type);
		
		if (getter == null) {
			throw new IllegalArgumentException("getter == null");
		}

		this.getter = getter;
		this.inputNumericType = getNumericType(type);
		
		// TODO: map Short => Integer result type, similar to IAdhoc 
		this.outputNumericType = getNumericType(type);
	}
	
	private static EAggregateType getNumericType(Class<?> type) {
		final EAggregateType ret;
		
		if (type.equals(Short.class)) {
			ret = EAggregateType.SHORT;	
		}
		else if (type.equals(Integer.class)) {
			ret = EAggregateType.INTEGER;
		}
		else if (type.equals(Long.class)) {
			ret = EAggregateType.LONG;
		}
		else if (type.equals(Double.class)) {
			ret = EAggregateType.DOUBLE;
		}
		else if (type.equals(BigDecimal.class)) {
			ret = EAggregateType.DECIMAL;
		}
		else if (type.equals(Date.class)) {
			ret = EAggregateType.DATE;
		}
		else {
			throw new IllegalArgumentException("Not a numeric type: " + type);
		}
		
		return ret;
	}
	

	final Getter getGetter() {
		return getter;
	}

	final EAggregateType getInputNumericType() {
		return inputNumericType;
	}

	final EAggregateType getOutputNumericType() {
		return outputNumericType;
	}

	@Override
	final EQueryResultDimension getDimension() {
		return EQueryResultDimension.SINGLE;
	}

	@Override
	final EQueryResultGathering getGathering() {
		return EQueryResultGathering.AGGREGATE;
	}

	@Override
	final ECollectionType getCollectionType() {
		throw new UnsupportedOperationException("Not a collection type");
	}
}
