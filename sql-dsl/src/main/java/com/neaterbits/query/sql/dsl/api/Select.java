package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.neaterbits.query.util.java8.MethodFinder;


public class Select {

	public static <MAPPED_RESULT> SingleMapToResult<SingleQuery<MAPPED_RESULT>, MAPPED_RESULT> selectOne(Class<MAPPED_RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}

		return new SingleMapToResultImpl<SingleQuery<MAPPED_RESULT>, MAPPED_RESULT>(cl, compiledQuery -> new SingleQueryImpl<>(compiledQuery));
	}

	public static <MAPPED_RESULT> MultiMapToResult<MultiQuery<MAPPED_RESULT>, MAPPED_RESULT> selectList(Class<MAPPED_RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}

		return new MultiMapToResultImpl<MultiQuery<MAPPED_RESULT>, MAPPED_RESULT>(cl, compiledQuery -> new MultiQueryImpl<>(compiledQuery));
	}

	public static <TYPE_RESULT> SingleTypeResult<SingleQuery<TYPE_RESULT>, TYPE_RESULT> selectOneFrom(Class<TYPE_RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}

		return new SingleTypeResultImpl<SingleQuery<TYPE_RESULT>, TYPE_RESULT>(cl, compiledQuery -> new SingleQueryImpl<>(compiledQuery));
	}

	public static <TYPE_RESULT> MultiTypeResult<MultiQuery<TYPE_RESULT>, TYPE_RESULT> selectListFrom(Class<TYPE_RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}

		return new MultiTypeResultImpl<MultiQuery<TYPE_RESULT>, TYPE_RESULT>(cl, compiledQuery -> new MultiQueryImpl<>(compiledQuery));
	}


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
	
	
    public static <T> T alias(Class<T> aliasType) {
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
    
	
    public static <T> Alias<T> aliasAlias(Class<T> aliasType) {
		if (aliasType == null) {
			throw new IllegalArgumentException("aliasType == null");
		}

		final AliasImpl<T> alias = new AliasImpl<T>(aliasType);

		return alias;
    }

    private static <T> Param<T> param(Class<T> paramType) {
		if (paramType == null) {
			throw new IllegalArgumentException("paramType == null");
		}

		final ParamImpl<T> param = new ParamImpl<T>(paramType);

		return param;
    }
    
    // Parameters. We only support known base types that support equals()/hashCode() 
    public static Param<Integer> intParam() {
    	return param(Integer.class);
    }

    public static Param<String> stringParam() {
    	return param(String.class);
    }
}
