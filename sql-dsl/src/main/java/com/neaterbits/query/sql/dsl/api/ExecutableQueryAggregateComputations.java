package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

// Separate class just to reduce overall class size

// TODO: Handle overflow in long should fall back to BigDecimal
// TODO: Pass collectors for all ops? Avoids boxing/unboxing ops when dealing with longs

abstract class ExecutableQueryAggregateComputations<QUERY> extends ExecuteQueryBase<QUERY> {

	ExecutableQueryAggregateComputations(ExecutableQuery<QUERY> q) {
		super(q);
	}
	
	private static abstract class AvgCollector {
		private long count;
		
		abstract Object getResult();
		
		AvgCollector() {
			this.count = 0;
		}
		
		final void incr() {
			++ count;
		}
		
		final long getCount() {
			return count;
		}
	}

	private static class LongAvgCollector extends AvgCollector {
		private long sum;
		
		LongAvgCollector() {
			this.sum = 0;
		}
		
		void add(long val) {
			
			sum += val;
			
			incr();
		}
		
		private long getAverage() {
			return sum / getCount();
		}

		@Override
		Object getResult() {
			return getAverage();
		}
	}

	private static class BigDecimalAvgCollector extends AvgCollector {
		private BigDecimal sum;
		
		BigDecimalAvgCollector() {
			this.sum = null;
		}

		void add(BigDecimal val) {
			
			sum = sum == null ? val : sum.add(val);
			
			incr();
		}
		
		private BigDecimal getAverage() {
			// TODO: Rounding?
			return sum.divide(BigDecimal.valueOf(getCount()));
		}

		@Override
		Object getResult() {
			return getAverage();
		}
	}
	
	final Object computeAggregateInitialResult(QUERY query) {

		final Object ret;
		final EAggregateFunction aggregateFunction = q.getAggregateResultFunction(query);

		switch (aggregateFunction) {

		case SUM:
		case MIN:
		case MAX:
			ret = null;
			break;

		case MIN_INSTANCE:
		case MAX_INSTANCE:
			ret = null;
			break;

		case COUNT:
			ret = 0;
			break;

		case AVG:
			ret = null;
			break;

		default:
			throw new UnsupportedOperationException("Unknown aggregate function " + aggregateFunction);
		}

		return ret;
	}

	final Object addToAggregateResult(QUERY query, Object instance, Object last, ExecuteQueryScratch scratch) {
		final EAggregateFunction aggregateFunction = q.getAggregateResultFunction(query);
		
		if (!scratch.numResultPartsIs(1)) {
			throw new IllegalArgumentException("Expected exactly one scratch field");
		}
		
		final Object value = q.getAggregateResultValue(query, scratch.get(0));
		
		final Object ret;
		
		switch (aggregateFunction) {

		case SUM:
			ret = computeSum(query, last, value);
			break;

		case MIN:
			ret = computeMin(query, last, value);
			break;
			
		case MAX:
			ret = computeMax(query, last, value);
			break;

		case MIN_INSTANCE:
			ret = computeMinInstance(query, instance, last, value);
			break;
			
		case MAX_INSTANCE:
			ret = computeMaxInstance(query, instance, last, value);
			break;
			
		case COUNT:
			ret = computeCount(query, last, value);
			break;
			
		case AVG:
			ret = computeAvg(query, last, value);
			break;

		default:
			throw new UnsupportedOperationException("Unknown aggregate function " + aggregateFunction);
		}

		return ret;
	}
	
	
	final Object computeAggregateFinalResult(QUERY query, Object value) {
		final EAggregateFunction aggregateFunction = q.getAggregateResultFunction(query);

		final Object ret;
		
		switch (aggregateFunction) {
			case SUM:
			case MIN:
			case MAX:
				ret = value;
				break;
				
			case MIN_INSTANCE:
			case MAX_INSTANCE:
				if (value == null) {
					ret = null;
				}
				else {
					final MinMaxValueBase l = (MinMaxValueBase)value;

					if (l.instance == null) {
						throw new IllegalStateException("instance is null");
					}
					
					ret = l.instance;
				}
				break;
				
			case COUNT:
				ret = value;
				break;
				
			case AVG:
				ret = value == null ? null : ((AvgCollector)value).getResult();
				break;
				
			default:
				throw new UnsupportedOperationException("Unknown aggregate function " + aggregateFunction);
		}
		
		return ret;
	}
	
	
	private final Object computeSum(QUERY query, Object last, Object value) {
		
		final EAggregateType numericType = q.getAggregateNumericInputType(query);
		
		final Object ret;
		
		// TODO: Overflow check
		
		switch (numericType) {
		case SHORT: {
			final Short v = (Short)value;
			if (last == null) {
				ret = (int)v;
			}
			else {
				final Integer l = (Integer)last;
				ret = l + v; 
			}
			break;
		}

		case INTEGER: {
			final Integer v = (Integer)value;
			if (last == null) {
				ret = (int)v;
			}
			else {
				final Integer l = (Integer)last;
				
				ret = l + v; 
			}
			break;
		}
			
			
		case LONG: {
			final Long v = (Long)value;
			if (last == null) {
				ret = v;
			}
			else {
				final Long l = (Long)last;
				
				ret = l + v; 
			}
			break;
		}

		case DECIMAL: {
			final BigDecimal v = (BigDecimal)value;
			if (last == null) {
				ret = v;
			}
			else {
				final BigDecimal b = (BigDecimal)last;
				
				ret = b.add(v); 
			}
			break;
		}

		default:
			throw new UnsupportedOperationException("Unknown numeric type " + numericType);
		
		}
		
		return ret;
	}
	
	private final Object computeMin(QUERY query, Object last, Object value) {
		
		final EAggregateType numericType = q.getAggregateNumericInputType(query);
		
		final Object ret;
		
		
		switch (numericType) {
		case SHORT: {
			final Short v = (Short)value;
			if (last == null) {
				ret = v;
			}
			else {
				final Short l = (Short)last;
				ret = Math.min(l, v); 
			}
			break;
		}

		case INTEGER: {
			final Integer v = (Integer)value;
			if (last == null) {
				ret = v;
			}
			else {
				final Integer l = (Integer)last;
				
				ret = Math.min(l, v); 
			}
			break;
		}
			
			
		case LONG: {
			final Long v = (Long)value;
			if (last == null) {
				ret = v;
			}
			else {
				final Long l = (Long)last;
				
				ret = Math.min(l, v); 
			}
			break;
		}

		case DECIMAL: {
			final BigDecimal v = (BigDecimal)value;
			if (last == null) {
				ret = v;
			}
			else {
				final BigDecimal b = (BigDecimal)last;
				
				switch (b.compareTo(v)) {
				case -1:
					ret = b;
					break;
					
				case 0:
					ret = b;
					break;
					
				case 1:
					ret = v;
					break;
					
				default:
					throw new IllegalStateException("Unknown compare to val");
				}
				
			}
			break;
		}

		default:
			throw new UnsupportedOperationException("Unknown numeric type " + numericType);
		
		}
		
		return ret;
	}
	
	private final Object computeMax(QUERY query, Object last, Object value) {
		
		final EAggregateType numericType = q.getAggregateNumericInputType(query);
		
		final Object ret;
		
		
		switch (numericType) {
		case SHORT: {
			final Short v = (Short)value;
			if (last == null) {
				ret = v;
			}
			else {
				final Short l = (Short)last;
				ret = Math.max(l, v); 
			}
			break;
		}

		case INTEGER: {
			final Integer v = (Integer)value;
			if (last == null) {
				ret = v;
			}
			else {
				final Integer l = (Integer)last;
				
				ret = Math.max(l, v); 
			}
			break;
		}
			
			
		case LONG: {
			final Long v = (Long)value;
			if (last == null) {
				ret = v;
			}
			else {
				final Long l = (Long)last;
				
				ret = Math.max(l, v); 
			}
			break;
		}

		case DECIMAL: {
			final BigDecimal v = (BigDecimal)value;
			if (last == null) {
				ret = v;
			}
			else {
				final BigDecimal b = (BigDecimal)last;
				
				switch (b.compareTo(v)) {
				case -1:
					ret = v;
					break;
					
				case 0:
					ret = b;
					break;
					
				case 1:
					ret = b;
					break;
					
				default:
					throw new IllegalStateException("Unknown compare to val");
				}
				
			}
			break;
		}

		default:
			throw new UnsupportedOperationException("Unknown numeric type " + numericType);
		
		}
		
		return ret;
	}


	private final Object computeMinInstance(QUERY query, Object instance, Object last, Object value) {
		
		final EAggregateType numericType = q.getAggregateNumericInputType(query);
		
		final MinMaxValueBase ret;
		
		switch (numericType) {
		case SHORT: {
			final Short v = (Short)value;
			if (last == null) {
				ret = new MinMaxValueShort(instance, v);
			}
			else {
				final MinMaxValueShort l = (MinMaxValueShort)last;

				ret = l;
				
				if (v < l.value) {
					l.instance = instance;
					l.value = v;
				}
			}
			break;
		}

		case INTEGER: {
			final Integer v = (Integer)value;
			if (last == null) {
				ret = new MinMaxValueInt(instance, v);
			}
			else {
				final MinMaxValueInt l = (MinMaxValueInt)last;

				ret = l;
				
				if (v < l.value) {
					l.instance = instance;
					l.value = v;
				}
			}
			break;
		}
			
			
		case LONG: {
			final Long v = (Long)value;
			if (last == null) {
				ret = new MinMaxValueLong(instance, v);
			}
			else {
				final MinMaxValueLong l = (MinMaxValueLong)last;
				
				ret = l;
				
				if (v < l.value) {
					l.instance = instance;
					l.value = v;
				}
			}
			break;
		}

		case DECIMAL: {
			final BigDecimal v = (BigDecimal)value;
			if (last == null) {
				ret = new MinMaxValueBigDecimal(instance, v);
			}
			else {
				final MinMaxValueBigDecimal b = (MinMaxValueBigDecimal)last;

				ret = b;
				
				switch (b.value.compareTo(v)) {
				case -1:
				case 0:
					break;
					
				case 1:
					b.instance = instance;
					b.value = v;
					break;
					
				default:
					throw new IllegalStateException("Unknown compare to val");
				}
				
			}
			break;
		}

		default:
			throw new UnsupportedOperationException("Unknown numeric type " + numericType);
		
		}
		
		return ret;
	}
	
	private final Object computeMaxInstance(QUERY query, Object instance, Object last, Object value) {
		
		final EAggregateType numericType = q.getAggregateNumericInputType(query);

		final MinMaxValueBase ret;

		switch (numericType) {
		case SHORT: {
			final Short v = (Short)value;
			if (last == null) {
				ret = new MinMaxValueShort(instance, v);
			}
			else {
				final MinMaxValueShort l = (MinMaxValueShort)last;
				
				ret = l;

				if (v > l.value) {
					l.instance = instance;
					l.value = v;
				}
			}
			break;
		}

		case INTEGER: {
			final Integer v = (Integer)value;
			if (last == null) {
				ret = new MinMaxValueInt(instance, v);
			}
			else {
				final MinMaxValueInt l = (MinMaxValueInt)last;
				
				ret = l;
					
				if (v > l.value) {
					l.instance = instance;
					l.value = v;
				}
			}
			break;
		}
			
			
		case LONG: {
			final Long v = (Long)value;
			if (last == null) {
				ret = new MinMaxValueLong(instance, v);
			}
			else {
				final MinMaxValueLong l = (MinMaxValueLong)last;

				ret = l;

				if (v > l.value) {
					l.instance = instance;
					l.value = v;
				}
			}
			break;
		}

		case DECIMAL: {
			final BigDecimal v = (BigDecimal)value;
			if (last == null) {
				ret = new MinMaxValueBigDecimal(instance, v);
			}
			else {
				final BigDecimal b = (BigDecimal)last;
				final MinMaxValueBigDecimal l = (MinMaxValueBigDecimal)last;
				
				ret = l;
				
				switch (b.compareTo(v)) {
				case -1:
					l.instance = instance;
					l.value = v;
					break;
					
				case 0:
				case 1:
					
				default:
					throw new IllegalStateException("Unknown compare to val");
				}
			}
			break;
		}

		default:
			throw new UnsupportedOperationException("Unknown numeric type " + numericType);
		}
		
		return ret;
	}
	
	private final Object computeCount(QUERY query, Object last, Object value) {

		final long l = (Long)last;
		
		return l + 1;
	}

	private static LongAvgCollector assureLongAvgCollector(Object last) {
		final LongAvgCollector ret;

		if (last == null) {
			ret = new LongAvgCollector();
		}
		else {
			ret = (LongAvgCollector)last;
		}
		
		return ret;
	}
	
	private static BigDecimalAvgCollector assureBigDecimalAvgCollector(Object last) {
		final BigDecimalAvgCollector ret;

		if (last == null) {
			ret = new BigDecimalAvgCollector();
		}
		else {
			ret = (BigDecimalAvgCollector)last;
		}
		
		return ret;
	}
	
	private final Object computeAvg(QUERY query, Object last, Object value) {
		
		final EAggregateType numericType = q.getAggregateNumericInputType(query);
		
		final AvgCollector ret;
		
		switch (numericType) {
		case SHORT: {
			final LongAvgCollector longAvgCollector = assureLongAvgCollector(last);

			longAvgCollector.add((Short)value);
			ret = longAvgCollector;
			break;
		}

		case INTEGER: {
			final LongAvgCollector longAvgCollector = assureLongAvgCollector(last);

			longAvgCollector.add((Integer)value);
			ret = longAvgCollector;
			break;
		}
			
			
		case LONG: {
			final LongAvgCollector longAvgCollector = assureLongAvgCollector(last);

			longAvgCollector.add((Long)value);
			ret = longAvgCollector;
			break;
		}

		case DECIMAL: {
			final BigDecimalAvgCollector longAvgCollector = assureBigDecimalAvgCollector(last);

			longAvgCollector.add((BigDecimal)value);
			ret = longAvgCollector;
			break;
		}

		default:
			throw new UnsupportedOperationException("Unknown numeric type " + numericType);
		
		}
		
		return ret;
	}

	
	private static class MinMaxValueBase {
		Object instance;

		MinMaxValueBase(Object instance) {
			
			if (instance == null) {
				throw new IllegalArgumentException("instance == null");
			}

			this.instance = instance;
		}
	}

	private static final class MinMaxValueShort extends MinMaxValueBase {
		short value;

		MinMaxValueShort(Object instance, short value) {
			super(instance);

			this.value = value;
		}
	}
	
	private static final class MinMaxValueInt extends MinMaxValueBase {
		private int value;

		MinMaxValueInt(Object instance, int value) {
			super(instance);

			this.value = value;
		}
	}
	
	private static final class MinMaxValueLong extends MinMaxValueBase {
		private long value;

		MinMaxValueLong(Object instance, long value) {
			super(instance);

			this.value = value;
		}
	}
	
	
	private static final class MinMaxValueBigDecimal extends MinMaxValueBase {
		private BigDecimal value;

		MinMaxValueBigDecimal(Object instance, BigDecimal value) {
			super(instance);
			
			if (value == null) {
				throw new IllegalArgumentException("value == null");
			}

			this.value = value;
		}
	}
}
