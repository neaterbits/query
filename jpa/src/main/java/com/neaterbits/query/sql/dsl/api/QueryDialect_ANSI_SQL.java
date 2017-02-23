package com.neaterbits.query.sql.dsl.api;

import java.util.Collection;
import java.util.List;

import com.neaterbits.query.sql.dsl.api.entity.EntityModelUtil;
import com.neaterbits.query.sql.dsl.api.entity.IEntity;
import com.neaterbits.query.sql.dsl.api.entity.Relation;

final class QueryDialect_ANSI_SQL<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL extends Collection<ATTRIBUTE>>

	extends QueryDialect_SQL {

	private final EntityModelUtil<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL> entityModelUtil;

	QueryDialect_ANSI_SQL(EntityModelUtil<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL> entityModelUtil) {
			
		if (entityModelUtil == null) {
			throw new IllegalArgumentException("entityModelUtil == null");
		}

		this.entityModelUtil = entityModelUtil;
	}

	@Override
	boolean supportsNonRelationJoins() {
		return true;
	}
	
	@Override
	void addEntityResult(QueryBuilder sb, FieldReferenceType fieldReferenceType, SourceReference sourceReference) {

		// Select one complete entity, we need to select all attributes of the model
		final Class<?> resultType = sourceReference.getJavaType();

		final IEntity entity = entityModelUtil.getEntityInfo(resultType);

		if (entity == null) {
			throw new IllegalStateException("No entity model for " + resultType);
		}

		final String prefix = getEntityResultVarName(fieldReferenceType, sourceReference, entity);

		appendFieldList(sb, entity, prefix);
	}
	
	private String getEntityResultVarName(FieldReferenceType fieldReferenceType, SourceReference sourceReference, IEntity entity) {
		
		final String ret;
		
		switch (fieldReferenceType) {
		case ALIAS:
			ret = sourceReference.getVarName();
			break;
			
		case ENTITY:
			ret = entity.getTableName();
			break;
			
		default:
			throw new UnsupportedOperationException("Unknown field reference type " + fieldReferenceType);
		}

		return ret;
	}
	
	
	private static void appendFieldList(QueryBuilder sb, IEntity entity, String prefix) {
		
		QueryDataSourceJPANative.forEachResultColumn(entity, (attr, column, idx) -> {
			
			if (idx > 0) {
				sb.append(", ");
			}

			sb.append(prefix).append('.').append(column);
		});
		
		/*
		
		boolean first = true;
		
		// Get all attributes in order
		for (IEntityAttribute attr : entity.getAttributes()) {
			for (String column : attr.getColumns()) {
				
				if (attr.getType() == AttributeType.RELATION) {
					// Skip all relational attributes
					continue;
				}
				
				if (attr.getType() != AttributeType.SCALAR) {
					throw new IllegalStateException("Support non-scalar attribute " + attr.getType() + " for "+ attr.getName() + " of  " + entity.getName());
				}
				
				if (first) {
					first = false;
				}
				else {
					sb.append(", ");
				}
				
				sb.append(prefix).append('.').append(column);
			}
		}
		*/
	}

	@Override
	void addAggregateResult(QueryBuilder sb, EAggregateFunction function, FieldReference field) {
		
		switch (function) {
		case SUM:
			sb.append("SUM (");
	
			appendFieldReference(sb, field);
			
			sb.append(" )");
			break;
			
		default:
			throw new UnsupportedOperationException();
		}
	}

	private void appendJoinVar(QueryBuilder sb, SourceReference to) {
		sb.append(getSourceTypeName(to.getJavaType())).append(' ').append(to.getVarName());
	}
	
	@Override
	void addComparisonJoin(QueryBuilder sb, List<JoinFieldComparison> fieldComparisons, SourceReference from, SourceReference to) {
		
		appendJoinVar(sb, to);
		
		sb.append(' ');
		
		final int num = fieldComparisons.size();
		
		for (int i = 0; i < num; ++ i) {

			sb.append(i == 0 ? "ON" : "AND");

			sb.append(' ');
			
			final JoinFieldComparison comparison = fieldComparisons.get(i);

			appendFieldReference(sb, comparison.getLeft());

			addOp(sb);

			appendFieldReference(sb, comparison.getRight());
		}
	}

	@Override
	void addOneToManyJoin(QueryBuilder sb, Relation relation, FieldReferenceType fieldReferenceType, SourceReference from, SourceReference to) {
		
		// One-to-many is a bit tricky, we must write ANSI joins on the model fields
		
		// Must add ON query for the columns in question

		appendJoinVar(sb, to);
		
		sb.append(' ');
		
		final String [] fromColumns = relation.getFrom().getAttribute().getColumns();
		final String [] toColumns   = relation.getTo()  .getAttribute().getColumns();

		if (fromColumns.length != toColumns.length) {
			throw new IllegalArgumentException("from columns != to columns: " + fromColumns.length + "/" + toColumns.length);
		}

		if (fromColumns.length == 0) {
			throw new IllegalArgumentException("No columns");
		}
		
		if  (!from.getJavaType().equals(relation.getFrom().getEntityType())) {
			throw new IllegalStateException("from type mismatch");
		}
		
		if  (!to.getJavaType().equals(relation.getTo().getEntityType())) {
			throw new IllegalStateException("to type mismatch");
		}
		
		for (int i = 0; i < fromColumns.length; ++ i) {

			String fromTableName = null;
			String toTableName = null;
			
			sb.append(i == 0 ? "ON" : "AND");

			sb.append(' ');
			
			switch (fieldReferenceType) {
			case ALIAS:
				sb.append(from.getVarName()).append('.').append(fromColumns[i]);
				
				addOp(sb);
				
				sb.append(to.getVarName()).append('.').append(toColumns[i]);
				break;
				
			case ENTITY:
				if (fromTableName == null) {
					fromTableName = getTableName(from.getJavaType());
				}
				sb.append(fromTableName).append('.').append(fromColumns[i]);
				
				addOp(sb);

				if (toTableName == null) {
					toTableName = getTableName(to.getJavaType());
				}
				sb.append(toTableName).append('.').append(toColumns[i]);
				break;
				
			default:
				throw new UnsupportedOperationException("Unknown field reference type " + fieldReferenceType);
			}
		}
	}
	
	private static void addOp(QueryBuilder sb) {
		sb.append(" = ");
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
	void addSelectSource(QueryBuilder sb, FieldReferenceType fieldReferenceType, SourceReference ref) {
		
		switch (fieldReferenceType) {
		case ENTITY:
			// Just add table name
			sb.append(getSourceTypeName(ref.getJavaType()));
			break;

		case ALIAS:
			// Add both table name and alias
			sb.append(getSourceTypeName(ref.getJavaType())).append(' ').append(ref.getVarName());
			break;

		default:
			throw new UnsupportedOperationException("Unknown referene type " + fieldReferenceType);
		}
	}

	private String getSourceTypeName(Class<?> javaType) {
		final String tableName = entityModelUtil.getEntityInfo(javaType).getTableName();
		
		if (tableName == null) {
			throw new IllegalStateException("tableName == null");
		}

		return tableName;
	}

	@Override
	public void appendAliasFieldReference(QueryBuilder sb, FieldReferenceAlias r) {
		sb.append(r.getVarName()).append(".").append(r.getColumnName());
	}
	
	@Override
	public void appendEntityFieldReference(QueryBuilder sb, FieldReferenceEntity r) {
		final String tableName = getTableName(r.getJavaType());
		
		sb.append(tableName).append(".").append(r.getColumnName());
	}
	
	private String getTableName(Class<?> javaType) {
		final String tableName = entityModelUtil.getEntityInfo(javaType).getTableName();

		return tableName;
	}
}
