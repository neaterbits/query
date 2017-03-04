package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;
import com.neaterbits.query.util.java8.MethodFinder;

abstract class BaseQuery implements IQueryPreparation {
	
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
	
	static <M> CompiledQuery compile(Collector_Query<M> collected, QueryMetaModel metaModel) {
		// Compile the collected query

		
		CompiledQuery compiledQuery;
		try {
			compiledQuery = CompiledQuery.compile(collected, metaModel);
		} catch (CompileException ex) {
			throw new IllegalStateException("Failed to compile", ex);
		}

		return compiledQuery;
	}

	static <T> ModelCompiler<SingleBuilt<T>> singleQueryCollected() {
		return collectedQuery -> new SharedCollected_Single<>(collectedQuery);
	}

	static <T> ModelCompiler<MultiBuilt<T>> multiQueryCollected() {
		return collectedQuery -> new SharedCollected_Multi<>(collectedQuery);
	}
	
	static <T> ModelCompiler<SingleBuilt<T>> singleQueryCompiled() {
		return collectedQuery -> new SharedCompiled_Single<>(compile(collectedQuery, null));
	}

	static <T> ModelCompiler<MultiBuilt<T>> multiQueryCompiled() {
		return collectedQuery -> new SharedCompiled_Multi<>(compile(collectedQuery, null));
	}
	
	
	abstract EQueryStyle getQueryStyle();
	
	// For adding all aliases for comparison when doing short-style queries (ie.aliases not specified in 'from')
	private final List<IAlias> aliases;
	private final QueryMetaModel queryMetaModel;
	
	BaseQuery(QueryMetaModel queryMetaModel) {
		this.aliases = new ArrayList<>();
		this.queryMetaModel = queryMetaModel;
	}
	
	// Synchronizations since may be reused in various initialization threads
	private synchronized void addAlias(IAlias alias) {
		
		if (alias == null) {
			throw new IllegalArgumentException("alias == null");
		}

		if (this.aliases.contains(alias)) {
			throw new IllegalStateException("alias already added");
		}

		this.aliases.add(alias);
	}
	
	final QueryMetaModel getQueryMetaModel() {
		return queryMetaModel;
	}
	
	
	@Override
    public <T> T alias(Class<T> aliasType) {
		if (aliasType == null) {
			throw new IllegalArgumentException("aliasType == null");
		}
		
		// Create a dynamic-proxy for the aliased type
		
		final InvocationHandler handler = new AliasInvocationHandler(aliasType);
		
		final T ret = MethodFinder.enhance(aliasType, new Class<?> [] { IAlias.class }, handler);
		
		final IAlias alias = (IAlias)ret;
		
		addAlias(alias);
		
		return ret;
    }
	
	// For lookup, must synchronized, but just return unsynchronized copy of current list  
	synchronized List<IAlias> getAliases() {
		return new ArrayList<>(aliases);
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
}
