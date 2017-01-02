package com.neaterbits.query.sql.dsl.api;

import java.util.Collection;


import com.neaterbits.query.sql.dsl.api.entity.EntityModelUtil;
import com.neaterbits.query.sql.dsl.api.entity.IEntity;

final class PreparedQueryBuilderANSISQL<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL extends Collection<ATTRIBUTE>> extends PreparedQueryBuilderORM {

	private final EntityModelUtil<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL> entityModelUtil;

	PreparedQueryBuilderANSISQL(EntityModelUtil<MANAGED, EMBEDDED, IDENTIFIABLE, ATTRIBUTE, COLL> entityModelUtil) {
		
		if (entityModelUtil == null) {
			throw new IllegalArgumentException("entityModelUtil == null");
		}

		this.entityModelUtil = entityModelUtil;
	}

	@Override
	void addEntityResult(FieldReferenceType fieldReferenceType, SourceReference sourceReference) {

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
	
	
	private static void appendFieldList(StringBuilder sb, IEntity entity, String prefix) {
		
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
	void addAggregateResult(EAggregateFunction function, FieldReference field) {
		
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

	@Override
	void addOneToManyJoin(String entityAliasName, String collectionAttrName, String joinVarName) {
		throw new UnsupportedOperationException("TODO");
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
	void addSelectSource(StringBuilder sb, FieldReferenceType fieldReferenceType, SourceReference ref) {
		
		switch (fieldReferenceType) {
		case ENTITY:
			// Just add taable name
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
	public void appendAliasFieldReference(StringBuilder sb, FieldReferenceAlias r) {
		sb.append(r.getVarName()).append(".").append(r.getColumnName());
	}
	
	@Override
	public void appendEntityFieldReference(StringBuilder sb, FieldReferenceEntity r) {
		final String tableName = entityModelUtil.getEntityInfo(r.getJavaType()).getTableName();
		
		sb.append(tableName).append(".").append(r.getColumnName());
	}

	@Override
	String getQueryAsString() {
		return sb.toString();
	}
}
