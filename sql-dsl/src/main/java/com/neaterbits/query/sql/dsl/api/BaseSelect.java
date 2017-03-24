package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.function.Function;
import java.util.function.Supplier;

import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

abstract class BaseSelect<
		// for sums, we return Long for short and int so must differentiate from other aggregate
		// fuctions, this is because sum may wrap over limits of type
		NAMED_SUM_LONG_RET,
		NAMED_COUNT_RET,
		
		// for other types aggregates, we return the same result as the input type, eg.
		// max of short-type will never be > short type
		NAMED_SHORT_RET,
		NAMED_INT_RET,
		NAMED_LONG_RET,
		NAMED_DOUBLE_RET,
		NAMED_BIGDECIMAL_RET,

		
		ALIAS_SUM_LONG_RET,
		ALIAS_COUNT_RET,
		
		ALIAS_SHORT_RET,
		ALIAS_INT_RET,
		ALIAS_LONG_RET,
		ALIAS_DOUBLE_RET,
		ALIAS_BIGDECIMAL_RET
		>

	extends BaseQuery

	implements ISQL<
		NAMED_SUM_LONG_RET, NAMED_COUNT_RET, NAMED_SHORT_RET, NAMED_INT_RET, NAMED_LONG_RET, NAMED_DOUBLE_RET, NAMED_BIGDECIMAL_RET,
		ALIAS_SUM_LONG_RET, ALIAS_COUNT_RET, ALIAS_SHORT_RET, ALIAS_INT_RET, ALIAS_LONG_RET, ALIAS_DOUBLE_RET, ALIAS_BIGDECIMAL_RET
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
	public final <T> NAMED_BIGDECIMAL_RET max(IFunctionBigDecimal<T> field) {
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
	public final <T> NAMED_BIGDECIMAL_RET min(IFunctionBigDecimal<T> field) {
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
	public final ALIAS_BIGDECIMAL_RET max(ISupplierBigDecimal field) {
		return max(field, BigDecimal.class, BigDecimal.class);
	}
		
	
	// ------------------------ Max ------------------------
	
	/*
	private <T, NUM, RESULT> IClassicResult_Numeric_Named<RESULT> min(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		return new Collector_AggregateNamedResult<>(new QueryResultMin(resultCl, new FunctionGetter(field)), singleQueryCompiler());
	}
	*/
	
	abstract <NUM, RESULT, RET> RET min(Supplier<NUM> field, Class<NUM> numCl, Class<RESULT> resultCl);

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
	public final ALIAS_BIGDECIMAL_RET min(ISupplierBigDecimal field) {
		return min(field, BigDecimal.class, BigDecimal.class);
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
