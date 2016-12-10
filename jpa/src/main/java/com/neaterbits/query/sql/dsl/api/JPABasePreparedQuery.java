package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

abstract class JPABasePreparedQuery implements DSPreparedQuery {

	private final CompiledQuery compiledQuery;
	private final ParamNameAssigner paramNameAssigner;

	JPABasePreparedQuery(CompiledQuery compiledQuery, ParamNameAssigner paramNameAssigner) {
		
		if (compiledQuery == null) {
			throw new IllegalArgumentException("resultMode == null");
		}

		this.compiledQuery = compiledQuery;
		this.paramNameAssigner = paramNameAssigner;
	}

	String getNameForParam(Param<?> param) {
		return paramNameAssigner.getExistingName(param);
	}
	
	final Object executeWithParams(Query jpaQuery, ParamValueResolver paramCollector) {
		
		if (paramNameAssigner != null) {
			paramNameAssigner.forEach((Param<?> param, String name) -> {
				
				jpaQuery.setParameter(name, paramCollector.resolveParam(param));
				
			});
		}

		Object ret;
		
		switch (compiledQuery.getResultMode()) {
		case SINGLE:
			try {
				ret = mapSingle(compiledQuery.getResult(), jpaQuery.getSingleResult());
			}
			catch (NoResultException ex) {
				ret = null;
			}
			break;
			
			
		case MULTI:
			ret = mapMultiple(compiledQuery.getResult(), jpaQuery.getResultList());
			break;
			
		default:
			throw new UnsupportedOperationException("Unknown result mode " + compiledQuery.getResultMode());
		}
		
		return ret;
	}
	
	private static Object createResult(QueryResult result) {
		try {
			return result.getType().newInstance();
		} catch (InstantiationException | IllegalAccessException ex) {
			throw new IllegalStateException("Failed to instantiate result type " + result.getType().getName(), ex);
		}
	}
	
	private Object mapSingle(CompiledQueryResult result, Object input) {

		return result.visit(mapResultVisitor, input);
		
	}
	
	private List<Object> mapMultiple(CompiledQueryResult result,  @SuppressWarnings("rawtypes") List input) {
		
		final List<Object> ret = new ArrayList<>(input.size());
		
		for (Object o : input) {
			ret.add(mapSingle(result, o));
		}

		return ret;
	}
	
	
	private static final CompiledQueryResultVisitor<Object, Object> mapResultVisitor = new CompiledQueryResultVisitor<Object, Object>() {
		
		@Override
		public Object onMapped(CompiledQueryResultMapped result, Object input) {
			final Object ret = createResult(result.getOriginal());
			
			final List<CompiledMapping> mappings = result.getMappings().getMappings();

			switch (mappings.size()) {
				case 0:
					throw new IllegalStateException("empty mappings");

				case 1:
					// Just one field
					mappings.get(0).getSetter().execute(ret, input);
					break;
					
				default:
					// More than one
					final Object [] vals = (Object[])input;
					for (int i = 0; i < vals.length; ++ i) {
						mappings.get(i).getSetter().execute(ret, vals[i]);
					}
					break;
			}
			
			return ret;
		}
		
		@Override
		public Object onEntity(CompiledQueryResultEntity result, Object input) {

			
			if (!result.getOriginal().getType().isAssignableFrom(input.getClass())) {
				throw new IllegalStateException("not mapped and result not of mapped class: " + input.getClass().getName());
			}
			
			return input;
		}

		@Override
		public Object onAggregate(CompiledQueryResultAggregate result, Object input) {
			
			if (!result.getOriginal().getType().equals(input.getClass())) {
				throw new IllegalStateException("Not of aggregated type " + result.getOriginal().getType().getName() + ": " + input.getClass().getName());
			}

			return input;
		}
	};
	
}
