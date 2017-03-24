package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

import com.neaterbits.query.sql.dsl.api.entity.EntityModelUtil;
import com.neaterbits.query.sql.dsl.api.entity.Relation;

final class QueryDialect_JPQL extends QueryDialect_SQL {

	@Override
	boolean supportsNonRelationJoins() {
		return false;
	}
	
	@Override
	void addEntityResult(QueryBuilder sb, EFieldAccessType fieldReferenceType, SourceReference sourceReference) {

		final Class<?> resultType = sourceReference.getJavaType();
		final String resultVarName = sourceReference.getVarName();
		
		sb.append(resultVarName);

		//sb.append(resultType.getSimpleName()).append(" ").append(resultVarName);
	}
	
	
	@Override
	void appendJoinStatement(QueryBuilder sb, EJoinType joinType) {

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
	void addComparisonJoin(QueryBuilder sb, List<JoinFieldComparison> fieldComparisons, SourceReference from, SourceReference to) {
		throw new UnsupportedOperationException("Not supported for now");
	}


	@Override
	void addOneToManyJoin(QueryBuilder sb, Relation relation, EFieldAccessType fieldReferenceType, SourceReference from, SourceReference to) {
		
		// final String entityAliasName = q.getJoinConditionLeftName(query, joinIdx, conditionIdx);
		
		
		final String entityAliasName = from.getVarName();
		final String collectionAttrName = relation.getFrom().getAttribute().getName();

		// final String joinVarName = "join" + joinParamIdx++;

		final String joinVarName = to.getVarName();
		
		addOneToManyJoin(sb, entityAliasName, collectionAttrName, joinVarName);
	}

	@Override
	public void appendAliasFieldReference(QueryBuilder sb, FieldReferenceAlias ref) {
		appendFieldReferenceStatic(sb, ref);
	}

	@Override
	public void appendEntityFieldReference(QueryBuilder sb, FieldReferenceEntity ref) {
		appendFieldReferenceStatic(sb, ref);
	}

	static void appendFieldReferenceStatic(QueryBuilder sb, FieldReference ref) {
		appendFieldReference(sb::append, ref);
	}

	@Override
	void addSelectSource(QueryBuilder sb, EFieldAccessType fieldReferenceType, SourceReference ref) {
		final String entityName = getSourceTypeName(ref.getJavaType());

		sb.append(entityName).append(' ').append(ref.getVarName());
	}

	@Override
	final ConditionStringBuilder makeConditionStringBuilder(QueryParametersDistinct distinctParams) {
		return new ConditionStringBuilder_JPQL(this, distinctParams);
	}
	
	private String getSourceTypeName(Class<?> javaType) {
		return javaType.getSimpleName();
	}

	private static void appendFieldReference(Consumer<String> c, FieldReference ref) {
		c.accept(ref.getVarName());
		c.accept(".");
		c.accept(ref.getColumnName());
	}
	

	private void addOneToManyJoin(QueryBuilder sb, String entityAliasName, String collectionAttrName, String joinVarName) {
		// Output JPA join on collection
		// from name in from-clause
		sb.append(" ")
		  .append(entityAliasName).append(".").append(collectionAttrName)
		  .append(" ")
		  .append(joinVarName);
	}

	@Override
	<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL extends Collection<ATTRIBUTE>>
		String getFieldNameForGetter(EntityModelUtil<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL> entityModelUtil, Class<?> type, Method getter) {

		return entityModelUtil.getModel().getEntityFieldNameForGetter(type, getter);
	}

	@Override
	String getBigDecimalLiteral(BigDecimal value) {
		return "'" + value.toPlainString() + "'";
	}
}
