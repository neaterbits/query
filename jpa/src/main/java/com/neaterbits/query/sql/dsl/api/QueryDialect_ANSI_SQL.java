package com.neaterbits.query.sql.dsl.api;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;

import com.neaterbits.query.sql.dsl.api.entity.ESubClassing;
import com.neaterbits.query.sql.dsl.api.entity.IEntity;
import com.neaterbits.query.sql.dsl.api.entity.IEntityFields;
import com.neaterbits.query.sql.dsl.api.entity.IEntityModelUtil;
import com.neaterbits.query.sql.dsl.api.entity.Relation;

final class QueryDialect_ANSI_SQL extends QueryDialect_SQL {

	QueryDialect_ANSI_SQL(IEntityModelUtil entityModelUtil) {
		super(entityModelUtil);
	}

	@Override
	boolean supportsNonRelationJoins() {
		return true;
	}
	
	/*
	@Override
	boolean requiresSelectedFieldsAsPartOfFrom() {
		// TODO Must always add types from clause, even if in join ?
		// looks like might not
		return false;
	}
	*/

	@Override
	void addEntityResult(QueryBuilder sb, EFieldAccessType fieldReferenceType, SourceReference sourceReference) {

		// Select one complete entity, we need to select all attributes of the model
		final Class<?> resultType = sourceReference.getJavaType();

		final IEntity entity = entityModelUtil.getEntityInfo(resultType);

		if (entity == null) {
			throw new IllegalStateException("No entity model for " + resultType);
		}

		final String prefix = getEntityResultVarName(fieldReferenceType, sourceReference, entity);

		if (entity.isBaseType()) {
			// We have to select across multiple tables to get all subtype information since
			// this may be a polymorphic query
			
			switch (entity.getSubClassing()) {
			case SINGLE_TABLE:
				// Simplest option for subclassing, just read all attributes since they're all in the one single table
				appendFieldListInherited(sb, entity, prefix, entity.getSingleTableSubclassingColumn());
				break;
				
			default:
				throw new UnsupportedOperationException("Unknown subclassing " + entity.getSubClassing());
			}
			
		}
		else {
			appendFieldList(sb, entity, prefix);
		}
	}
	
	private String getEntityResultVarName(EFieldAccessType fieldReferenceType, SourceReference sourceReference, IEntityFields entity) {
		
		final String ret;
		
		switch (fieldReferenceType) {
		case ALIAS:
			ret = sourceReference.getVarName();
			break;
			
		case NAMED:
			ret = entity.getTableName();
			break;
			
		default:
			throw new UnsupportedOperationException("Unknown field reference type " + fieldReferenceType);
		}

		return ret;
	}

	private static void appendExtraColumns(QueryBuilder sb, String prefix, String ... extraColumns) {
		for (String extraColumn : extraColumns) {
			
			if (extraColumn == null) {
				throw new IllegalArgumentException("extraColumn == null");
			}
			
			sb.append(", ");
			sb.append(prefix).append('.').append(extraColumn);
		}
		
	}
	
	private static void appendColumn(QueryBuilder sb, String prefix, int idx, String column) {
		if (idx > 0) {
			sb.append(", ");
		}

		sb.append(prefix).append('.').append(column);		
	}

	private static void appendFieldListInherited(QueryBuilder sb, IEntity entity, String prefix, String ... extraColumns) {
		
		if (entity.getSubClassing() != ESubClassing.SINGLE_TABLE) {
			throw new IllegalStateException("Only single-table subclassing for now");
		}

		QueryDataSourceJPANative.forEachInheritanceColumn(entity, (e, attr, column, idx) -> {
			
			appendColumn(sb, prefix, idx, column);
			
		});
		

		appendExtraColumns(sb, prefix, extraColumns);
	}

	
	private static void appendFieldList(QueryBuilder sb, IEntity entity, String prefix, String ... extraColumns) {
		
		QueryDataSourceJPANative.forEachEntityColumn(entity, 0, (e, attr, column, idx) -> {
			
			appendColumn(sb, prefix, idx, column);
		});
		

		appendExtraColumns(sb, prefix, extraColumns);
		
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

	/*
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
	*/

	private void appendJoinVar(QueryBuilder sb, SourceReference to) {
		sb.append(getSourceTypeName(to.getJavaType()));

		if (to.getFieldAccessType() == EFieldAccessType.ALIAS) {
			sb.append(' ').append(to.getVarName());
		}
	}
	
	

	@Override
	String getFunctionName(FunctionBase function) {
		
		final String ret;
		
		if (function instanceof Function_String_Substring) {
			ret = "substr";
		}
		else {
			ret = super.getFunctionName(function);;
		}

		return ret;
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
	void addOneToManyJoin(QueryBuilder sb, Relation relation, EFieldAccessType fieldReferenceType, SourceReference from, SourceReference to) {
		
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
				
			case NAMED:
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
	void addSelectSource(QueryBuilder sb, EFieldAccessType fieldReferenceType, SourceReference ref) {
		
		switch (fieldReferenceType) {
		case NAMED:
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
	public void appendAliasFieldReference(QueryBuilder sb, FieldReference_Alias r) {
		sb.append(r.getVarName()).append(".").append(r.getColumnName());
	}
	
	@Override
	public void appendEntityFieldReference(QueryBuilder sb, FieldReference_Entity r) {
		final String tableName = getTableName(r.getJavaType());
		
		sb.append(tableName).append(".").append(r.getColumnName());
	}

	@Override
	final ConditionStringBuilder makeConditionStringBuilder(QueryParametersDistinct distinctParams) {
		return new ConditionStringBuilder_Native(this, entityModelUtil, distinctParams);
	}
	
	private String getTableName(Class<?> javaType) {
		final String tableName = entityModelUtil.getEntityInfo(javaType).getTableName();

		return tableName;
	}

	@Override
	String getFieldNameForGetter(IEntityModelUtil entityModelUtil, Class<?> type, Method getter) {
		return entityModelUtil.getColumnNameForGetter(type, getter);
	}

	@Override
	String getBigDecimalLiteral(BigDecimal value) {
		return value.toPlainString();
	}
}
