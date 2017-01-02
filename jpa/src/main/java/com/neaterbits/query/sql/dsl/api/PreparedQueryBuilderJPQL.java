package com.neaterbits.query.sql.dsl.api;

import java.util.List;
import java.util.function.Consumer;

import com.neaterbits.query.sql.dsl.api.entity.Relation;



final class PreparedQueryBuilderJPQL extends PreparedQueryBuilderORM {

	
	
	
	@Override
	void addEntityResult(FieldReferenceType fieldReferenceType, SourceReference sourceReference) {

		final Class<?> resultType = sourceReference.getJavaType();
		final String resultVarName = sourceReference.getVarName();

		sb.append(resultType.getSimpleName()).append(" ").append(resultVarName);
	}
	
	
	@Override
	void addAggregateResult(EAggregateFunction function, FieldReference field) {
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
	void appendJoinStatement(EJoinType joinType) {

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
	void addComparisonJoin(List<JoinFieldComparison> fieldComparisons, SourceReference from, SourceReference to) {
		throw new UnsupportedOperationException("Not supported for now");
	}


	@Override
	void addOneToManyJoin(Relation relation, FieldReferenceType fieldReferenceType, SourceReference from, SourceReference to) {
		
		// final String entityAliasName = q.getJoinConditionLeftName(query, joinIdx, conditionIdx);
		
		
		final String entityAliasName = from.getVarName();
		final String collectionAttrName = relation.getFrom().getAttribute().getName();

		// final String joinVarName = "join" + joinParamIdx++;

		final String joinVarName = to.getVarName();
		
		addOneToManyJoin(entityAliasName, collectionAttrName, joinVarName);
	}


	private void addOneToManyJoin(String entityAliasName, String collectionAttrName, String joinVarName) {
		// Output JPA join on collection
		// from name in from-clause
		sb.append(" ")
		  .append(entityAliasName).append(".").append(collectionAttrName)
		  .append(" ")
		  .append(joinVarName);
	}

	@Override
	public String toString() {
		return getQueryAsString();
	}

	@Override
	String getQueryAsString() {
		return sb.toString();
	}

	@Override
	public void appendAliasFieldReference(StringBuilder sb, FieldReferenceAlias ref) {
		appendFieldReferenceStatic(sb, ref);
	}

	@Override
	public void appendEntityFieldReference(StringBuilder sb, FieldReferenceEntity r) {
		throw new UnsupportedOperationException("TODO");
	}

	static void appendFieldReferenceStatic(StringBuilder sb, FieldReferenceAlias ref) {
		appendFieldReference(sb::append, ref);
	}

	@Override
	void addSelectSource(StringBuilder sb, FieldReferenceType fieldReferenceType, SourceReference ref) {
		final String entityName = getSourceTypeName(ref.getJavaType());

		sb.append(entityName).append(' ').append(ref.getVarName());
	}

	private String getSourceTypeName(Class<?> javaType) {
		return javaType.getSimpleName();
	}

	private static void appendFieldReference(Consumer<String> c, FieldReferenceAlias ref) {
		c.accept(ref.getVarName());
		c.accept(".");
		c.accept(ref.getColumnName());
	}
}
