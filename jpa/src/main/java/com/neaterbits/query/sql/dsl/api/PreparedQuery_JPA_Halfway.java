package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

abstract class PreparedQuery_JPA_Halfway<QUERY> extends PreparedQuery_JPA_Base<QUERY> {

	private final String base;
	private final PreparedQueryConditionsBuilderJPA conditions;
	
	PreparedQuery_JPA_Halfway(QueryDataSourceJPA dataSource, ExecutableQuery<QUERY> queryAccess, QUERY query, QueryParametersDistinct distinctParams, String base, PreparedQueryConditionsBuilderJPA conditions) {
		super(dataSource, queryAccess, query, distinctParams);
		
		if (base == null) {
			throw new IllegalArgumentException("base == null");
		}
		
		if (base.trim().isEmpty()) {
			throw new IllegalArgumentException("no base");
		}
		
		if (conditions == null) {
			throw new IllegalArgumentException("conditions == null");
		}
		
		if (conditions.isEmpty()) {
			throw new IllegalArgumentException("no conditions");
		}
		
		if (conditions.size() > 1 && conditions.getType() == null) {
			throw new IllegalArgumentException("no op when conditions > 1");
		}
		
		
		this.base = base;
		this.conditions = conditions;
	}

	@Override
	final void initParams(Query jpaQuery, ParamValueResolver paramCollector) {
		
		// Since this is a completely resolved query, that means that all parameters could be resolved to a :paramXyz
		// thus we can iterate over all params

		final List<Param<?>> paramsThatWereResolved  = new ArrayList<>();

		// Set all unique params still in use
		conditions.walk(comparison -> {
			
			if (comparison.isUnresolved()) {
				// still unresolved so is resolved into literal instead, skip
			}
			else {
				final JPAConditionResolved condition = (JPAConditionResolved)comparison.getRhs();

				final Param<?> param = condition.getAnyResolvedParam();
				
				if (param != null && !paramsThatWereResolved.contains(param)) {
					paramsThatWereResolved.add(param);
				}
			}
		});
		
		
		// Got list of all params that were resolved to query parameters, now add them to query 
		for (Param<?> param : paramsThatWereResolved) {
			final int idx = distincParams.getIndexOf(param);

			if (idx < 0) {
				throw new IllegalStateException("param not found");
			}
			
			final Object value = paramCollector.resolveParam(param);
			
			setParam(jpaQuery, (BaseParamImpl<?>)param, idx, value);
		}
	}
	
	@Override
	Object execute(ParamValueResolver collectedParams) {
		
		final StringBuilder sb = new StringBuilder(base);

		sb.append(" ");

		conditions.resolveFromParams(sb, collectedParams);

		final String queryString = sb.toString();
		
		System.out.println("## execute halfway-query: " + queryString);

		final Query jpaQuery = ((QueryDataSourceJPA)getDataSource()).createJPAQuery(queryString); 

		return executeWithParams(jpaQuery, collectedParams); 
	}
}
