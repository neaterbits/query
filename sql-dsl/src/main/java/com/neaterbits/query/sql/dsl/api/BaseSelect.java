package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.function.Function;

import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

abstract class BaseSelect<
		// for sums, we return Long for short and int so must differentiate from other aggregate
		// fuctions, this is because sum may wrap over limits of type
		SUM_LONG_RET,
		COUNT_RET,
		
		// for other types aggregates, we return the same result as the input type, eg.
		// max of short-type will never be > short type
		SHORT_RET,
		INT_RET,
		LONG_RET,
		BIGDECIMAL_RET
		>

	extends BaseQuery

	implements ISQL<SUM_LONG_RET, COUNT_RET, SHORT_RET, INT_RET, LONG_RET, BIGDECIMAL_RET> 
	
	{
		

	// ======================== Aggregate functions ========================
	
	
	// ------------------------ Sum ------------------------
	

	/*
	private <T, NUM, RESULT> IClassicResult_Numeric_Named<RESULT> sum(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		return new Collector_AggregateNamedResult<>(new QueryResultSum(resultCl, new FunctionGetter(field)), singleQueryCompiler());
	}
	*/

	abstract <T, NUM, RESULT, RET> RET sum(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl);
	
	BaseSelect(QueryMetaModel queryMetaModel) {
		super(queryMetaModel);
	}



	@Override
	public final <T> SUM_LONG_RET sum(IFunctionShort<T> field) {
		return sum(field, Short.class, Long.class);
	}

	@Override
	public final <T> SUM_LONG_RET sum(IFunctionInteger<T> field) {
		return sum(field, Integer.class, Long.class);
	}
	
	@Override
	public final <T> SUM_LONG_RET sum(IFunctionLong<T> field) {
		return sum(field, Long.class, Long.class);
	}

	@Override
	public final <T> BIGDECIMAL_RET sum(IFunctionBigDecimal<T> field) {
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
	public final <T> SHORT_RET max(IFunctionShort<T> field) {
		return max(field, Short.class, Short.class);
	}

	@Override
	public final <T> INT_RET max(IFunctionInteger<T> field) {
		return max(field, Integer.class, Integer.class);
	}
	
	@Override
	public final <T> LONG_RET max(IFunctionLong<T> field) {
		return max(field, Long.class, Long.class);
	}

	@Override
	public final <T> BIGDECIMAL_RET max(IFunctionBigDecimal<T> field) {
		return max(field, BigDecimal.class, BigDecimal.class);
	}
		
	
	// ------------------------ Max ------------------------
	
	/*
	private <T, NUM, RESULT> IClassicResult_Numeric_Named<RESULT> min(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		return new Collector_AggregateNamedResult<>(new QueryResultMin(resultCl, new FunctionGetter(field)), singleQueryCompiler());
	}
	*/
	
	abstract <T, NUM, RESULT, RET> RET min(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl);

	@Override
	public final <T> SHORT_RET min(IFunctionShort<T> field) {
		return min(field, Short.class, Short.class);
	}

	@Override
	public final <T> INT_RET min(IFunctionInteger<T> field) {
		return min(field, Integer.class, Integer.class);
	}
	
	@Override
	public final <T> LONG_RET min(IFunctionLong<T> field) {
		return min(field, Long.class, Long.class);
	}

	@Override
	public final <T> BIGDECIMAL_RET min(IFunctionBigDecimal<T> field) {
		return min(field, BigDecimal.class, BigDecimal.class);
	}

	// ------------------------ Avg ------------------------

	/*
	private <T, NUM, RESULT> IClassicResult_Numeric_Named<RESULT> avg(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		return new Collector_AggregateNamedResult<>(new QueryResultAvg(resultCl, new FunctionGetter(field)), singleQueryCompiler());
	}
	*/

	abstract <T, NUM, RESULT, RET> RET avg(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl);

	@Override
	public final <T> SHORT_RET avg(IFunctionShort<T> field) {
		return avg(field, Short.class, Short.class);
	}

	@Override
	public final <T> INT_RET avg(IFunctionInteger<T> field) {
		return avg(field, Integer.class, Integer.class);
	}
	
	@Override
	public final <T> LONG_RET avg(IFunctionLong<T> field) {
		return avg(field, Long.class, Long.class);
	}

	@Override
	public final <T> BIGDECIMAL_RET avg(IFunctionBigDecimal<T> field) {
		return avg(field, BigDecimal.class, BigDecimal.class);
	}

	// ------------------------ Count ------------------------
	
	/*
	private <T, NUM, RESULT> IClassicResult_Numeric_Named<RESULT> count(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		return new Collector_AggregateNamedResult<>(new QueryResultCount(resultCl, new FunctionGetter(field)), singleQueryCompiler());
	}
	*/
	
	abstract <T, NUM, RESULT> COUNT_RET count(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl);
	

	@Override
	public final <T> COUNT_RET count(IFunctionShort<T> field) {
		return count(field, Short.class, Long.class);
	}

	@Override
	public final <T> COUNT_RET count(IFunctionInteger<T> field) {
		return count(field, Integer.class, Long.class);
	}
	
	@Override
	public final <T> COUNT_RET count(IFunctionLong<T> field) {
		return count(field, Long.class, Long.class);
	}

	@Override
	public final <T> COUNT_RET count(IFunctionBigDecimal<T> field) {
		return count(field, BigDecimal.class, Long.class);
	}
}
