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
	
	static {
		try {
			aliasGetTypeMethod = IAlias.class.getMethod("getType");
		} catch (NoSuchMethodException | SecurityException ex) {
			throw new IllegalStateException("Failed to get IAlias.getType() method", ex);
		}
	}
	
	
    public static <T> T alias(Class<T> aliasType) {
		if (aliasType == null) {
			throw new IllegalArgumentException("aliasType == null");
		}
		
		// Create a dynamic-proxy for the aliased type
		
		final InvocationHandler handler = (proxy, method, args) -> {
			final Object ret;
			
			if (method.getDeclaringClass().equals(IAlias.class)) {
				if (!method.equals(aliasGetTypeMethod)) {
					throw new IllegalArgumentException("Expected getType to be called");
				}
				
				ret = aliasType;
			}
			else {
				throw new UnsupportedOperationException("N/A: " + method.getName());
			}

			return ret;
		};
		
		
		return MethodFinder.enhance(aliasType, new Class<?> [] { IAlias.class }, handler);
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
