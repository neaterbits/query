package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import javax.persistence.NoResultException;
import javax.persistence.Query;

abstract class JPABasePreparedQuery implements DSPreparedQuery {

	private final CompiledQuery compiledQuery;
	private final ParamNameAssigner paramNameAssigner;

	JPABasePreparedQuery(CompiledQuery compiledQuery, ParamNameAssigner paramNameAssigner) {
		
		if (compiledQuery == null) {
			throw new IllegalArgumentException("resultMode == null");
		}

		if (paramNameAssigner == null) {
			throw new IllegalArgumentException("paramNameAssigner == null");
		}
		
		this.compiledQuery = compiledQuery;
		this.paramNameAssigner = paramNameAssigner;
	}

	String getNameForParam(Param<?> param) {
		return paramNameAssigner.getExistingName(param);
	}
	
	final Object executeWithParams(Query jpaQuery, ParamValueResolver paramCollector) {
		paramNameAssigner.forEach((Param<?> param, String name) -> {
			
			jpaQuery.setParameter(name, paramCollector.resolveParam(param));
			
		});

		Object ret;
		
		switch (compiledQuery.getResultMode()) {
		case SINGLE:
			try {
				ret = mapSingle(jpaQuery.getSingleResult());
			}
			catch (NoResultException ex) {
				ret = null;
			}
			break;
			
			
		case MULTI:
			ret = mapMultiple(jpaQuery.getResultList());
			break;
			
		default:
			throw new UnsupportedOperationException("Unknown result mode " + compiledQuery.getResultMode());
		}
		
		return ret;
	}
	
	private Object createResult() {
		try {
			return compiledQuery.getResultType().newInstance();
		} catch (InstantiationException | IllegalAccessException ex) {
			throw new IllegalStateException("Failed to instantiate result type " + compiledQuery.getResultType().getName(), ex);
		}
	}
	
	private Object mapSingle(Object input) {

		final Object ret;
		
		if (compiledQuery.getMappings() != null) {
			ret = createResult();
			
			final List<CompiledMapping> mappings = compiledQuery.getMappings().getMappings();

			switch (mappings.size()) {
				case 0:
					throw new IllegalStateException("empty mappings");
				
				case 1:
					// Just one field
					mappings.get(0).getSetter().set(ret, input);
					break;
					
				default:
					// More tha one
					final Object [] vals = (Object[])input;
					for (int i = 0; i < vals.length; ++ i) {
						mappings.get(i).getSetter().set(ret, vals[i]);
					}
					break;
			}
			
		}
		else {
			if (!compiledQuery.getClass().isAssignableFrom(input.getClass())) {
				throw new IllegalStateException("not mapped and result not of mapped class: " + input.getClass().getName());
			}
			
			ret = input;
		}
		
		return ret;
	}
	
	private List<Object> mapMultiple(@SuppressWarnings("rawtypes") List input) {
		
		final List<Object> ret = new ArrayList<>(input.size());
		
		for (Object o : input) {
			ret.add(mapSingle(o));
		}

		return ret;
	}
}
