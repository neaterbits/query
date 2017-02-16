package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.function.Function;

import com.neaterbits.query.util.java8.MethodFinder;

final class ClassicSelect implements IClassic {

	static final ClassicSelect selectImpl = new ClassicSelect();
	
	private static final Method aliasGetTypeMethod;
	private static final Method aliasGetLastInvokedMethod;
	
	private static <T> ModelCompiler<SingleQuery<T>> singleQueryCompiler() {
		return compiledQuery -> new SharedQuery_Single<>(compiledQuery);
	}

	private static <T> ModelCompiler<MultiQuery<T>> multiQueryCompiler() {
		return compiledQuery -> new SharedQuery_Multi<>(compiledQuery);
	}
	
	@Override
	public <MAPPED_RESULT> IClassicResult_Mapped_Single_All<SingleQuery<MAPPED_RESULT>, MAPPED_RESULT> selectOneOrNull(Class<MAPPED_RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
			}

		return new Collector_MapToResult_Single<SingleQuery<MAPPED_RESULT>, MAPPED_RESULT>(cl, singleQueryCompiler());
	}

	@Override
	public <MAPPED_RESULT> IClassicResult_Mapped_Multi_All<MultiQuery<MAPPED_RESULT>, MAPPED_RESULT> selectList(Class<MAPPED_RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}

		return new Collector_MapToResult_Multi<MultiQuery<MAPPED_RESULT>, MAPPED_RESULT>(cl, ECollectionType.LIST, multiQueryCompiler());
	}

	@Override
	public <ENTITY_RESULT> IClassicResult_Entity_Single<SingleQuery<ENTITY_RESULT>, ENTITY_RESULT> selectOneFrom(Class<ENTITY_RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}

		final SharedSelectSource selectSource = new SharedSelectSource_Named(cl);

		return new Classic_Collector_SingleTypeResult<SingleQuery<ENTITY_RESULT>, ENTITY_RESULT>(selectSource, singleQueryCompiler());
	}

	@Override
	public <ENTITY_RESULT> IClassicResult_Entity_Multi<MultiQuery<ENTITY_RESULT>, ENTITY_RESULT> selectListFrom(Class<ENTITY_RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}
		
		final SharedSelectSource selectSource = new SharedSelectSource_Named(cl);

		return new Classic_Collector_MultiEntityResult<MultiQuery<ENTITY_RESULT>, ENTITY_RESULT>(selectSource, ECollectionType.LIST, multiQueryCompiler());
	}

	@Override
	public <TYPE_RESULT> IClassicSingleWhereClauseBuilderNamed<SingleQuery<TYPE_RESULT>, TYPE_RESULT> oneFrom(Class<TYPE_RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}

		final SharedSelectSource selectSource = new SharedSelectSource_Named(cl);
		
		return new ClassicSingleNamedResult<SingleQuery<TYPE_RESULT>, TYPE_RESULT>(new CollectedQueryResult_Entity_Single(selectSource), singleQueryCompiler());
	}

	@Override
	public <TYPE_RESULT> IClassicSingleWhereClauseBuilderNamed<MultiQuery<TYPE_RESULT>, TYPE_RESULT> listFrom(Class<TYPE_RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}

		final SharedSelectSource selectSource = new SharedSelectSource_Named(cl);

		final CollectedQueryResult_Entity_Multi result = new CollectedQueryResult_Entity_Multi(selectSource, ECollectionType.LIST);
		
		return new ClassicSingleNamedResult<MultiQuery<TYPE_RESULT>, TYPE_RESULT>(result, multiQueryCompiler());
	}
	
	static {
		try {
			aliasGetTypeMethod = IAlias.class.getMethod("getType");
			aliasGetLastInvokedMethod = IAlias.class.getMethod("getLastInvokedMethod");
			
		} catch (NoSuchMethodException | SecurityException ex) {
			throw new IllegalStateException("Failed to get IAlias method", ex);
		}
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
    public <T> Alias<T> aliasAlias(Class<T> aliasType) {
		if (aliasType == null) {
			throw new IllegalArgumentException("aliasType == null");
		}

		final AliasImpl<T> alias = new AliasImpl<T>(aliasType);

		return alias;
    }

	@Override
    public <T> ValParam<T> param(Class<T> paramType) {
		if (paramType == null) {
			throw new IllegalArgumentException("paramType == null");
		}

		final ValParamImpl<T> param = new ValParamImpl<T>(paramType);

		return param;
    }

	@Override
    public <T> InParam<T> inParam(Class<T> paramType) {
		if (paramType == null) {
			throw new IllegalArgumentException("paramType == null");
		}

		final InParamImpl<T> param = new InParamImpl<T>(paramType);

		return param;
    }
    
    // Parameters. We only support known base types that support equals()/hashCode() 
	@Override
    public ValParam<Integer> intParam() {
    	return param(Integer.class);
    }

	@Override
    public ValParam<String> stringParam() {
    	return param(String.class);
    }

	// ======================== Aggregate functions ========================
	
	
	// ------------------------ Sum ------------------------
	

	private <T, NUM, RESULT> IClassicResult_Numeric_Named<RESULT> sum(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		return new Collector_AggregateNamedResult<>(new QueryResultSum(resultCl, new FunctionGetter(field)), singleQueryCompiler());
	}


	@Override
	public <T> IClassicResult_Numeric_Named<Long> sum(IFunctionShort<T> field) {
		return sum(field, Short.class, Long.class);
	}

	@Override
	public <T> IClassicResult_Numeric_Named<Long> sum(IFunctionInteger<T> field) {
		return sum(field, Integer.class, Long.class);
	}
	
	@Override
	public <T> IClassicResult_Numeric_Named<Long> sum(IFunctionLong<T> field) {
		return sum(field, Long.class, Long.class);
	}

	@Override
	public <T> IClassicResult_Numeric_Named<BigDecimal> sum(IFunctionBigDecimal<T> field) {
		return sum(field, BigDecimal.class, BigDecimal.class);
	}


	// ------------------------ Max ------------------------
	
	private <T, NUM, RESULT> IClassicResult_Numeric_Named<RESULT> max(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		return new Collector_AggregateNamedResult<>(new QueryResultMax(resultCl, new FunctionGetter(field)), singleQueryCompiler());
	}


	@Override
	public <T> IClassicResult_Numeric_Named<Short> max(IFunctionShort<T> field) {
		return max(field, Short.class, Short.class);
	}

	@Override
	public <T> IClassicResult_Numeric_Named<Integer> max(IFunctionInteger<T> field) {
		return max(field, Integer.class, Integer.class);
	}
	
	@Override
	public <T> IClassicResult_Numeric_Named<Long> max(IFunctionLong<T> field) {
		return max(field, Long.class, Long.class);
	}

	@Override
	public <T> IClassicResult_Numeric_Named<BigDecimal> max(IFunctionBigDecimal<T> field) {
		return max(field, BigDecimal.class, BigDecimal.class);
	}
		
	
	// ------------------------ Max ------------------------
	
	private <T, NUM, RESULT> IClassicResult_Numeric_Named<RESULT> min(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		return new Collector_AggregateNamedResult<>(new QueryResultMin(resultCl, new FunctionGetter(field)), singleQueryCompiler());
	}


	@Override
	public <T> IClassicResult_Numeric_Named<Short> min(IFunctionShort<T> field) {
		return min(field, Short.class, Short.class);
	}

	@Override
	public <T> IClassicResult_Numeric_Named<Integer> min(IFunctionInteger<T> field) {
		return min(field, Integer.class, Integer.class);
	}
	
	@Override
	public <T> IClassicResult_Numeric_Named<Long> min(IFunctionLong<T> field) {
		return min(field, Long.class, Long.class);
	}

	@Override
	public <T> IClassicResult_Numeric_Named<BigDecimal> min(IFunctionBigDecimal<T> field) {
		return min(field, BigDecimal.class, BigDecimal.class);
	}

	// ------------------------ Avg ------------------------
	
	private <T, NUM, RESULT> IClassicResult_Numeric_Named<RESULT> avg(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		return new Collector_AggregateNamedResult<>(new QueryResultAvg(resultCl, new FunctionGetter(field)), singleQueryCompiler());
	}


	@Override
	public <T> IClassicResult_Numeric_Named<Short> avg(IFunctionShort<T> field) {
		return avg(field, Short.class, Short.class);
	}

	@Override
	public <T> IClassicResult_Numeric_Named<Integer> avg(IFunctionInteger<T> field) {
		return avg(field, Integer.class, Integer.class);
	}
	
	@Override
	public <T> IClassicResult_Numeric_Named<Long> avg(IFunctionLong<T> field) {
		return avg(field, Long.class, Long.class);
	}

	@Override
	public <T> IClassicResult_Numeric_Named<BigDecimal> avg(IFunctionBigDecimal<T> field) {
		return avg(field, BigDecimal.class, BigDecimal.class);
	}

	// ------------------------ Count ------------------------
	
	private <T, NUM, RESULT> IClassicResult_Numeric_Named<RESULT> count(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		return new Collector_AggregateNamedResult<>(new QueryResultCount(resultCl, new FunctionGetter(field)), singleQueryCompiler());
	}


	@Override
	public <T> IClassicResult_Numeric_Named<Long> count(IFunctionShort<T> field) {
		return count(field, Short.class, Long.class);
	}

	@Override
	public <T> IClassicResult_Numeric_Named<Long> count(IFunctionInteger<T> field) {
		return count(field, Integer.class, Long.class);
	}
	
	@Override
	public <T> IClassicResult_Numeric_Named<Long> count(IFunctionLong<T> field) {
		return count(field, Long.class, Long.class);
	}

	@Override
	public <T> IClassicResult_Numeric_Named<Long> count(IFunctionBigDecimal<T> field) {
		return count(field, BigDecimal.class, Long.class);
	}
}
