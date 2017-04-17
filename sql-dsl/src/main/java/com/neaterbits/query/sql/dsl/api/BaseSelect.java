package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.function.Function;
import java.util.function.Supplier;

import org.w3c.dom.DOMConfiguration;

import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

abstract class BaseSelect<
		// for sums, we return Long for short and int so must differentiate from other aggregate
		// fuctions, this is because sum may wrap over limits of type
		NAMED_SUM_LONG_RET,
		NAMED_COUNT_RET,
		
		// for other types aggregates, we return the same result as the input type, eg.
		// max of short-type will never be > short type
		NAMED_BYTE_RET,
		NAMED_SHORT_RET,
		NAMED_INT_RET,
		NAMED_LONG_RET,
		NAMED_BIGINTEGER_RET,
		NAMED_FLOAT_RET,
		NAMED_DOUBLE_RET,
		NAMED_BIGDECIMAL_RET,
		NAMED_DATE_RET,
		NAMED_CALENDAR_RET,
		NAMED_SQLDATE_RET,
		NAMED_SQLTIME_RET,
		NAMED_SQLTIMESTAMP_RET,

		
		ALIAS_SUM_LONG_RET,
		ALIAS_COUNT_RET,
		
		ALIAS_BYTE_RET,
		ALIAS_SHORT_RET,
		ALIAS_INT_RET,
		ALIAS_LONG_RET,
		ALIAS_BIGINTEGER_RET,
		ALIAS_FLOAT_RET,
		ALIAS_DOUBLE_RET,
		ALIAS_BIGDECIMAL_RET,
		ALIAS_DATE_RET,
		ALIAS_CALENDAR_RET,
		ALIAS_SQLDATE_RET,
		ALIAS_SQLTIME_RET,
		ALIAS_SQLTIMESTAMP_RET
		>

	extends BaseQuery

	implements ISQL<
		NAMED_SUM_LONG_RET, NAMED_COUNT_RET, NAMED_BYTE_RET, NAMED_SHORT_RET, NAMED_INT_RET, NAMED_LONG_RET, NAMED_BIGINTEGER_RET, NAMED_FLOAT_RET, NAMED_DOUBLE_RET, NAMED_BIGDECIMAL_RET, NAMED_DATE_RET, NAMED_CALENDAR_RET, NAMED_SQLDATE_RET, NAMED_SQLTIME_RET, NAMED_SQLTIMESTAMP_RET,
		ALIAS_SUM_LONG_RET, ALIAS_COUNT_RET, ALIAS_BYTE_RET, ALIAS_SHORT_RET, ALIAS_INT_RET, ALIAS_LONG_RET, ALIAS_BIGINTEGER_RET, ALIAS_FLOAT_RET, ALIAS_DOUBLE_RET, ALIAS_BIGDECIMAL_RET, ALIAS_DATE_RET, ALIAS_CALENDAR_RET, ALIAS_SQLDATE_RET, ALIAS_SQLTIME_RET, ALIAS_SQLTIMESTAMP_RET
		> 
	
	{
		
	BaseSelect(QueryMetaModel queryMetaModel) {
		super(queryMetaModel);
	}

		

	// ======================== Aggregate functions ========================
		
    //*************************************************************************
    // Named
	//*************************************************************************
	
	
	// ------------------------ Sum ------------------------
	

	/*
	private <T, NUM, RESULT> IClassicResult_Numeric_Named<RESULT> sum(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		return new Collector_AggregateNamedResult<>(new QueryResultSum(resultCl, new FunctionGetter(field)), singleQueryCompiler());
	}
	*/

	abstract <T, NUM, RESULT, RET> RET sum(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl);
	


	@Override
	public final <T> NAMED_SUM_LONG_RET sum(IFunctionShort<T> field) {
		return sum(field, Short.class, Long.class);
	}

	@Override
	public final <T> NAMED_SUM_LONG_RET sum(IFunctionInteger<T> field) {
		return sum(field, Integer.class, Long.class);
	}
	
	@Override
	public final <T> NAMED_SUM_LONG_RET sum(IFunctionLong<T> field) {
		return sum(field, Long.class, Long.class);
	}

	@Override
	public final <T> NAMED_BIGDECIMAL_RET sum(IFunctionBigDecimal<T> field) {
		return sum(field, BigDecimal.class, BigDecimal.class);
	}


	// ------------------------ Max ------------------------
	
	/*
	private <T, NUM, RESULT> IClassicResult_Numeric_Named<RESULT> max(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		return new Collector_AggregateNamedResult<>(new QueryResultMax(resultCl, new FunctionGetter(field)), singleQueryCompiler());
	}
	*/

	abstract <T, NUM, RESULT, RET> RET max(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl);

	@Override
	public final <T> NAMED_BYTE_RET max(IFunctionByte<T> field) {
		return max(field, Byte.class, Byte.class);
	}

	@Override
	public final <T> NAMED_SHORT_RET max(IFunctionShort<T> field) {
		return max(field, Short.class, Short.class);
	}

	@Override
	public final <T> NAMED_INT_RET max(IFunctionInteger<T> field) {
		return max(field, Integer.class, Integer.class);
	}
	
	@Override
	public final <T> NAMED_LONG_RET max(IFunctionLong<T> field) {
		return max(field, Long.class, Long.class);
	}

	@Override
	public final <T> NAMED_BIGINTEGER_RET max(IFunctionBigInteger<T> field) {
		return max(field, BigInteger.class, BigInteger.class);
	}

	@Override
	public final <T> NAMED_FLOAT_RET max(IFunctionFloat<T> field) {
		return max(field, Float.class, Float.class);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET max(IFunctionDouble<T> field) {
		return max(field, Double.class, Double.class);
	}

	@Override
	public final <T> NAMED_BIGDECIMAL_RET max(IFunctionBigDecimal<T> field) {
		return max(field, BigDecimal.class, BigDecimal.class);
	}
		
	@Override
	public final <T> NAMED_DATE_RET max(IFunctionDate<T> field) {
		return max(field, Date.class, Date.class);
	}
	
	// ------------------------ Max ------------------------
	
	/*
	private <T, NUM, RESULT> IClassicResult_Numeric_Named<RESULT> min(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		return new Collector_AggregateNamedResult<>(new QueryResultMin(resultCl, new FunctionGetter(field)), singleQueryCompiler());
	}
	*/
	
	abstract <T, NUM, RESULT, RET> RET min(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl);

	@Override
	public final <T> NAMED_BYTE_RET min(IFunctionByte<T> field) {
		return min(field, Byte.class, Byte.class);
	}

	@Override
	public final <T> NAMED_SHORT_RET min(IFunctionShort<T> field) {
		return min(field, Short.class, Short.class);
	}

	@Override
	public final <T> NAMED_INT_RET min(IFunctionInteger<T> field) {
		return min(field, Integer.class, Integer.class);
	}
	
	@Override
	public final <T> NAMED_LONG_RET min(IFunctionLong<T> field) {
		return min(field, Long.class, Long.class);
	}

	@Override
	public final <T> NAMED_BIGINTEGER_RET min(IFunctionBigInteger<T> field) {
		return min(field, BigInteger.class, BigInteger.class);
	}

	@Override
	public final <T> NAMED_FLOAT_RET min(IFunctionFloat<T> field) {
		return min(field, Float.class, Float.class);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET min(IFunctionDouble<T> field) {
		return min(field, Double.class, Double.class);
	}

	@Override
	public final <T> NAMED_BIGDECIMAL_RET min(IFunctionBigDecimal<T> field) {
		return min(field, BigDecimal.class, BigDecimal.class);
	}

	@Override
	public final <T> NAMED_DATE_RET min(IFunctionDate<T> field) {
		return min(field, java.util.Date.class, java.util.Date.class);
	}

	
	// ------------------------ Avg ------------------------

	/*
	private <T, NUM, RESULT> IClassicResult_Numeric_Named<RESULT> avg(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		return new Collector_AggregateNamedResult<>(new QueryResultAvg(resultCl, new FunctionGetter(field)), singleQueryCompiler());
	}
	*/

	abstract <T, NUM, RESULT, RET> RET avg(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl);

	@Override
	public final <T> NAMED_DOUBLE_RET avg(IFunctionShort<T> field) {
		return avg(field, Short.class, Double.class);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET avg(IFunctionInteger<T> field) {
		return avg(field, Integer.class, Double.class);
	}
	
	@Override
	public final <T> NAMED_DOUBLE_RET avg(IFunctionLong<T> field) {
		return avg(field, Long.class, Double.class);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET avg(IFunctionBigDecimal<T> field) {
		return avg(field, BigDecimal.class, Double.class);
	}

	// ------------------------ Count ------------------------
	
	/*
	private <T, NUM, RESULT> IClassicResult_Numeric_Named<RESULT> count(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		return new Collector_AggregateNamedResult<>(new QueryResultCount(resultCl, new FunctionGetter(field)), singleQueryCompiler());
	}
	*/
	
	abstract <T, NUM, RESULT> NAMED_COUNT_RET count(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl);
	

	@Override
	public final <T> NAMED_COUNT_RET count(IFunctionShort<T> field) {
		return count(field, Short.class, Long.class);
	}

	@Override
	public final <T> NAMED_COUNT_RET count(IFunctionInteger<T> field) {
		return count(field, Integer.class, Long.class);
	}
	
	@Override
	public final <T> NAMED_COUNT_RET count(IFunctionLong<T> field) {
		return count(field, Long.class, Long.class);
	}

	@Override
	public final <T> NAMED_COUNT_RET count(IFunctionBigDecimal<T> field) {
		return count(field, BigDecimal.class, Long.class);
	}

	
    //*************************************************************************
    // Alias
	//*************************************************************************
	
	
	// ------------------------ Sum ------------------------
	

	/*
	private <T, NUM, RESULT> IClassicResult_Numeric_Named<RESULT> sum(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		return new Collector_AggregateNamedResult<>(new QueryResultSum(resultCl, new FunctionGetter(field)), singleQueryCompiler());
	}
	*/

	abstract <NUM, RESULT, RET> RET sum(Supplier<NUM> field, Class<NUM> numCl, Class<RESULT> resultCl);
	

	@Override
	public final ALIAS_SUM_LONG_RET sum(ISupplierShort field) {
		return sum(field, Short.class, Long.class);
	}

	@Override
	public final ALIAS_SUM_LONG_RET sum(ISupplierInteger field) {
		return sum(field, Integer.class, Long.class);
	}
	
	@Override
	public final ALIAS_SUM_LONG_RET sum(ISupplierLong field) {
		return sum(field, Long.class, Long.class);
	}

	@Override
	public final ALIAS_BIGDECIMAL_RET sum(ISupplierBigDecimal field) {
		return sum(field, BigDecimal.class, BigDecimal.class);
	}


	// ------------------------ Max ------------------------
	
	/*
	private <T, NUM, RESULT> IClassicResult_Numeric_Named<RESULT> max(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		return new Collector_AggregateNamedResult<>(new QueryResultMax(resultCl, new FunctionGetter(field)), singleQueryCompiler());
	}
	*/

	abstract <NUM, RESULT, RET> RET max(Supplier<NUM> field, Class<NUM> numCl, Class<RESULT> resultCl);

	@Override
	public final ALIAS_BYTE_RET max(ISupplierByte field) {
		return max(field, Byte.class, Byte.class);
	}

	@Override
	public final ALIAS_SHORT_RET max(ISupplierShort field) {
		return max(field, Short.class, Short.class);
	}

	@Override
	public final ALIAS_INT_RET max(ISupplierInteger field) {
		return max(field, Integer.class, Integer.class);
	}
	
	@Override
	public final ALIAS_LONG_RET max(ISupplierLong field) {
		return max(field, Long.class, Long.class);
	}

	@Override
	public final ALIAS_BIGINTEGER_RET max(ISupplierBigInteger field) {
		return max(field, BigInteger.class, BigInteger.class);
	}

	@Override
	public final ALIAS_FLOAT_RET max(ISupplierFloat field) {
		return max(field, Float.class, Float.class);
	}

	@Override
	public final ALIAS_DOUBLE_RET max(ISupplierDouble field) {
		return max(field, Double.class, Double.class);
	}

	@Override
	public final ALIAS_BIGDECIMAL_RET max(ISupplierBigDecimal field) {
		return max(field, BigDecimal.class, BigDecimal.class);
	}
		
	@Override
	public final ALIAS_DATE_RET max(ISupplierDate field) {
		return max(field, Date.class, Date.class);
	}
	
	// ------------------------ Min ------------------------
	
	/*
	private <T, NUM, RESULT> IClassicResult_Numeric_Named<RESULT> min(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		return new Collector_AggregateNamedResult<>(new QueryResultMin(resultCl, new FunctionGetter(field)), singleQueryCompiler());
	}
	*/
	
	abstract <NUM, RESULT, RET> RET min(Supplier<NUM> field, Class<NUM> numCl, Class<RESULT> resultCl);

	@Override
	public final ALIAS_BYTE_RET min(ISupplierByte field) {
		return min(field, Byte.class, Byte.class);
	}

	@Override
	public final ALIAS_SHORT_RET min(ISupplierShort field) {
		return min(field, Short.class, Short.class);
	}

	@Override
	public final ALIAS_INT_RET min(ISupplierInteger field) {
		return min(field, Integer.class, Integer.class);
	}
	
	@Override
	public final ALIAS_LONG_RET min(ISupplierLong field) {
		return min(field, Long.class, Long.class);
	}

	@Override
	public final ALIAS_BIGINTEGER_RET min(ISupplierBigInteger field) {
		return min(field, BigInteger.class, BigInteger.class);
	}

	@Override
	public final ALIAS_FLOAT_RET min(ISupplierFloat field) {
		return min(field, Float.class, Float.class);
	}

	@Override
	public final ALIAS_DOUBLE_RET min(ISupplierDouble field) {
		return min(field, Double.class, Double.class);
	}

	@Override
	public final ALIAS_BIGDECIMAL_RET min(ISupplierBigDecimal field) {
		return min(field, BigDecimal.class, BigDecimal.class);
	}

	@Override
	public final ALIAS_DATE_RET min(ISupplierDate field) {
		return min(field, java.util.Date.class, java.util.Date.class);
	}

	// ------------------------ Avg ------------------------

	/*
	private <T, NUM, RESULT> IClassicResult_Numeric_Named<RESULT> avg(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		return new Collector_AggregateNamedResult<>(new QueryResultAvg(resultCl, new FunctionGetter(field)), singleQueryCompiler());
	}
	*/

	abstract <NUM, RESULT, RET> RET avg(Supplier<NUM> field, Class<NUM> numCl, Class<RESULT> resultCl);

	@Override
	public final ALIAS_DOUBLE_RET avg(ISupplierShort field) {
		return avg(field, Short.class, Double.class);
	}

	@Override
	public final ALIAS_DOUBLE_RET avg(ISupplierInteger field) {
		return avg(field, Integer.class, Double.class);
	}
	
	@Override
	public final ALIAS_DOUBLE_RET avg(ISupplierLong field) {
		return avg(field, Long.class, Double.class);
	}

	@Override
	public final ALIAS_DOUBLE_RET avg(ISupplierBigDecimal field) {
		return avg(field, BigDecimal.class, Double.class);
	}

	// ------------------------ Count ------------------------
	
	/*
	private <T, NUM, RESULT> IClassicResult_Numeric_Named<RESULT> count(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		return new Collector_AggregateNamedResult<>(new QueryResultCount(resultCl, new FunctionGetter(field)), singleQueryCompiler());
	}
	*/
	
	abstract <NUM, RESULT> ALIAS_COUNT_RET count(Supplier<NUM> field, Class<NUM> numCl, Class<RESULT> resultCl);
	

	@Override
	public final ALIAS_COUNT_RET count(ISupplierShort field) {
		return count(field, Short.class, Long.class);
	}

	@Override
	public final ALIAS_COUNT_RET count(ISupplierInteger field) {
		return count(field, Integer.class, Long.class);
	}
	
	@Override
	public final ALIAS_COUNT_RET count(ISupplierLong field) {
		return count(field, Long.class, Long.class);
	}

	@Override
	public final ALIAS_COUNT_RET count(ISupplierBigDecimal field) {
		return count(field, BigDecimal.class, Long.class);
	}	
}
