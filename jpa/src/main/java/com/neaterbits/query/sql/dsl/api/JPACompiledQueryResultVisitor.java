package com.neaterbits.query.sql.dsl.api;

import javax.persistence.EntityManager;

final class JPACompiledQueryResultVisitor implements CompiledQueryResultVisitor<StringBuilder, Void> {

	private final EntityManager em;
	
	JPACompiledQueryResultVisitor(EntityManager em) {
		this.em = em;
	}

	@Override
	public Void onEntity(CompiledQueryResultEntity result, StringBuilder sb) {
		
		final Class<?> resultType = result.getOriginal().getType();
		
		sb.append(resultType.getSimpleName()).append(" ").append(resultVarName(resultType));

		return null;
	}

	@Override
	public Void onMapped(CompiledQueryResultMapped result, StringBuilder sb) {
		
		prepareMappings(sb, result.getMappings());
		
		return null;
	}

	@Override
	public Void onAggregate(CompiledQueryResultAggregate result, StringBuilder sb) {

		final QueryResultAggregate original = (QueryResultAggregate)result.getOriginal();
		
		if (original instanceof QueryResultSum) {
			sb.append("sum (");
			
			QueryDataSourceJPA.prepareFieldReference(sb::append, result.getField(), em);
			
			sb.append(")");
		}
		else {
			throw new UnsupportedOperationException("Unknown aggregate: " + original.getClass().getName());
		}
		
		return null;
	}

	// TODO: Assure unique
	private static String resultVarName(Class<?> resultType) {
		return "_result";
	}
	
	private void prepareMappings(StringBuilder sb, CompiledMappings mappings) {
		
		JPAUtil.commaSeparated(sb, mappings.getMappings(), (CompiledMapping mapping) -> {

			final CompiledFieldReference field = mapping.getField();

			QueryDataSourceJPA.prepareFieldReference(sb::append, field, em);
		});
	}
}
