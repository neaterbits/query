package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.function.Function;

import com.neaterbits.query.util.java8.MethodFinder;

final class SelectImpl implements IClassic {

	static final SelectImpl selectImpl = new SelectImpl();
	
	private static final Method aliasGetTypeMethod;
	private static final Method aliasGetLastInvokedMethod;
	
	private static <T> ModelCompiler<SingleQuery<T>> singleQueryCompiler() {
		return compiledQuery -> new SingleQueryImpl<>(compiledQuery);
	}

	private static <T> ModelCompiler<MultiQuery<T>> multiQueryCompiler() {
		return compiledQuery -> new MultiQueryImpl<>(compiledQuery);
	}
	
	@Override
	public <MAPPED_RESULT> IClassicSingleMapToResult<SingleQuery<MAPPED_RESULT>, MAPPED_RESULT> selectOneOrNull(Class<MAPPED_RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
			}

		return new SingleMapToResultImpl<SingleQuery<MAPPED_RESULT>, MAPPED_RESULT>(cl, singleQueryCompiler());
	}

	@Override
	public <MAPPED_RESULT> IClassicMultiMapToResult<MultiQuery<MAPPED_RESULT>, MAPPED_RESULT> selectList(Class<MAPPED_RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}

		return new MultiMapToResultImpl<MultiQuery<MAPPED_RESULT>, MAPPED_RESULT>(cl, ECollectionType.LIST, multiQueryCompiler());
	}

	@Override
	public <TYPE_RESULT> IClassicSingleTypeResult<SingleQuery<TYPE_RESULT>, TYPE_RESULT> selectOneFrom(Class<TYPE_RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}

		final SelectSource selectSource = new SelectSourceClass(cl);

		return new SingleTypeResultImpl<SingleQuery<TYPE_RESULT>, TYPE_RESULT>(selectSource, singleQueryCompiler());
	}

	@Override
	public <TYPE_RESULT> IClassicMultiTypeResult<MultiQuery<TYPE_RESULT>, TYPE_RESULT> selectListFrom(Class<TYPE_RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}
		
		final SelectSource selectSource = new SelectSourceClass(cl);

		return new MultiTypeResultImpl<MultiQuery<TYPE_RESULT>, TYPE_RESULT>(selectSource, ECollectionType.LIST, multiQueryCompiler());
	}

	@Override
	public <TYPE_RESULT> IClassicSingleWhereClauseBuilderNamed<SingleQuery<TYPE_RESULT>, TYPE_RESULT> oneFrom(Class<TYPE_RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}

		final SelectSource selectSource = new SelectSourceClass(cl);
		
		return new SingleNamedResultImpl<SingleQuery<TYPE_RESULT>, TYPE_RESULT>(new QueryResultEntitySingle(selectSource), singleQueryCompiler());
	}

	@Override
	public <TYPE_RESULT> IClassicSingleWhereClauseBuilderNamed<MultiQuery<TYPE_RESULT>, TYPE_RESULT> listFrom(Class<TYPE_RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}

		final SelectSource selectSource = new SelectSourceClass(cl);

		final QueryResultEntityMulti result = new QueryResultEntityMulti(selectSource, ECollectionType.LIST);
		
		return new SingleNamedResultImpl<MultiQuery<TYPE_RESULT>, TYPE_RESULT>(result, multiQueryCompiler());
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
    public <T> Param<T> param(Class<T> paramType) {
		if (paramType == null) {
			throw new IllegalArgumentException("paramType == null");
		}

		final ParamImpl<T> param = new ParamImpl<T>(paramType);

		return param;
    }
    
    // Parameters. We only support known base types that support equals()/hashCode() 
	@Override
    public Param<Integer> intParam() {
    	return param(Integer.class);
    }

	@Override
    public Param<String> stringParam() {
    	return param(String.class);
    }

	// ======================== Aggregate functions ========================
	
	
	// ------------------------ Sum ------------------------
	

	private <T, NUM> IClassicNumericNamedResult<NUM> sum(Function<T, NUM> field, Class<NUM> cl) {
		return new AggregateNamedResultImpl<>(new QueryResultSum(cl, new FunctionGetter(field)), singleQueryCompiler());
	}
	
	@Override
	public <T> IClassicNumericNamedResult<Short> sum(IFunctionShort<T> field) {
		return sum(field, Short.class);
	}

	@Override
	public <T> IClassicNumericNamedResult<Integer> sum(IFunctionInteger<T> field) {
		return sum(field, Integer.class);
	}

	@Override
	public <T> IClassicNumericNamedResult<Long> sum(IFunctionLong<T> field) {
		return sum(field, Long.class);
	}

	@Override
	public <T> IClassicNumericNamedResult<BigDecimal> sum(IFunctionBigDecimal<T> field) {
		return sum(field, BigDecimal.class);
	}
}
