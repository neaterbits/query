package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.function.Function;

import com.neaterbits.query.util.java8.MethodFinder;

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

	implements ISQL<SUM_LONG_RET, COUNT_RET, SHORT_RET, INT_RET, LONG_RET, BIGDECIMAL_RET> {
		
	private static final Method aliasGetTypeMethod;
	private static final Method aliasGetLastInvokedMethod;
		
	static {
		try {
			aliasGetTypeMethod = IAlias.class.getMethod("getType");
			aliasGetLastInvokedMethod = IAlias.class.getMethod("getLastInvokedMethod");
			
		} catch (NoSuchMethodException | SecurityException ex) {
			throw new IllegalStateException("Failed to get IAlias method", ex);
		}
	}
	
	
	static <T> ModelCompiler<SingleCompiled<T>> singleQueryCompiler() {
		return compiledQuery -> new SharedCompiled_Single<>(compiledQuery);
	}

	static <T> ModelCompiler<MultiCompiled<T>> multiQueryCompiler() {
		return compiledQuery -> new SharedCompiled_Multi<>(compiledQuery);
	}
	
	@Override
    public <T> T alias(Class<T> aliasType) {
		if (aliasType == null) {
			throw new IllegalArgumentException("aliasType == null");
		}
		
		// Create a dynamic-proxy for the aliased type
		
		final InvocationHandler handler = new AliasInvocationHandler(aliasType);
		
		return MethodFinder.enhance(aliasType, new Class<?> [] { IAlias.class }, handler);
    }
    
    private static class AliasInvocationHandler implements InvocationHandler {

    	private final Class<?> aliasType;
    	
    	private Method lastInvokedMethod;
    	
    	
		AliasInvocationHandler(Class<?> aliasType) {
			this.aliasType = aliasType;
			this.lastInvokedMethod = null;
		}


		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			final Object ret;
			
			if (method.getDeclaringClass().equals(IAlias.class)) {
				if (method.equals(aliasGetTypeMethod)) {
					ret = aliasType;
				}
				else if (method.equals(aliasGetLastInvokedMethod)) {
					ret = lastInvokedMethod;
					this.lastInvokedMethod = null;
				}
				else {
					throw new IllegalArgumentException("Unknown IAlias method " + method.getName());
				}
			}
			else {
				// Store last invoked for later retrieval
				lastInvokedMethod = method;
				
				ret = null;
			}

			return ret;
		}
    }
    
	
	@Override
    public final <T> Alias<T> aliasAlias(Class<T> aliasType) {
		if (aliasType == null) {
			throw new IllegalArgumentException("aliasType == null");
		}

		final AliasImpl<T> alias = new AliasImpl<T>(aliasType);

		return alias;
    }

	@Override
    public final <T> ValParam<T> param(Class<T> paramType) {
		if (paramType == null) {
			throw new IllegalArgumentException("paramType == null");
		}

		final ValParamImpl<T> param = new ValParamImpl<T>(paramType);

		return param;
    }

	@Override
    public final <T> InParam<T> inParam(Class<T> paramType) {
		if (paramType == null) {
			throw new IllegalArgumentException("paramType == null");
		}

		final InParamImpl<T> param = new InParamImpl<T>(paramType);

		return param;
    }
    
    // Parameters. We only support known base types that support equals()/hashCode() 
	@Override
    public final ValParam<Integer> intParam() {
    	return param(Integer.class);
    }

	@Override
    public final ValParam<String> stringParam() {
    	return param(String.class);
    }

	// ======================== Aggregate functions ========================
	
	
	// ------------------------ Sum ------------------------
	

	/*
	private <T, NUM, RESULT> IClassicResult_Numeric_Named<RESULT> sum(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		return new Collector_AggregateNamedResult<>(new QueryResultSum(resultCl, new FunctionGetter(field)), singleQueryCompiler());
	}
	*/

	abstract <T, NUM, RESULT, RET> RET sum(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl);

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
