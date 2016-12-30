package com.neaterbits.query.sql.dsl.api;

import java.util.List;
import java.util.function.Consumer;


final class JPAPreparedQueryBuilder implements PreparedQueryBuilder {

	private final StringBuilder sb;

	JPAPreparedQueryBuilder() {
		this.sb = new StringBuilder();
	}

	@Override
	public void select() {
		sb.append("SELECT ");
	}

	@Override
	public void addEntityResult(Class<?> resultType, String resultVarName) {
		sb.append(resultType.getSimpleName()).append(" ").append(resultVarName);
	}
	
	
	@Override
	public void addAggregateResult(EAggregateFunction function, FieldReference field) {
		switch (function) {
		case SUM:
			sb.append("sum (");

			appendFieldReference(sb, field);
			
			sb.append(")");
			break;
			
		default:
			throw new UnsupportedOperationException("Unknown aggregate: " + function);
		}
	}

	@Override
	public void addMappings(List<FieldReference> references) {
		JPAUtil.commaSeparated(sb, references, (s, r) -> appendFieldReference(s, r));
	}

	@Override
	public void addSelectSources(List<SourceReference> references) {

		sb.append("\n").append("FROM ");

		JPAUtil.commaSeparated(sb, references, (s, ref) -> {
			final String entityName = entityName(ref.getJavaType());

			s.append(entityName).append(' ').append(ref.getVarName());

		});
	}

	
	@Override
	public void appendJoinStatement(EJoinType joinType) {
		switch (joinType) {
		
		case LEFT:
			sb.append(" LEFT JOIN ");
			break;
			
		case INNER:
			sb.append(" INNER JOIN ");
			break;
			
		default:
			throw new UnsupportedOperationException("Unknown join type " + joinType);
		}
	}

	
	@Override
	public void addOneToManyJoin(String entityAliasName, String collectionAttrName, String joinVarName) {
		// Output JPA join on collection
		// from name in from-clause
		sb.append(" ")
		  .append(entityAliasName).append(".").append(collectionAttrName)
		  .append(" ")
		  .append(joinVarName);
	}
	

	@Override
	public void resolveFromParams(PreparedQueryConditionsBuilder conditionsBuilder, ParamValueResolver paramValueResolver) {
		conditionsBuilder.resolveFromParams(sb, paramValueResolver);
	}

	private String entityName(Class<?> entityType) {
		return entityType.getSimpleName();
	}

	@Override
	public String toString() {
		return sb.toString();
	}

	static void appendFieldReference(StringBuilder sb, FieldReference ref) {
		appendFieldReference(sb::append, ref);
	}

	private static void appendFieldReference(Consumer<String> c, FieldReference ref) {
		c.accept(ref.getVarName());
		c.accept(".");
		c.accept(ref.getColumnName());
	}
}
