package com.neaterbits.query.sql.dsl.api;


/**
 * Common base class for ORM queries / native ANSI SQL due to commonalities
 * 
 * @author nhl
 *
 */

abstract class PreparedQueryConditionsBuilderORM extends PreparedQueryConditionsBuilder {

	private final PrepareQueryFieldReferenceBuilder fieldReferenceBuilder;
	private final StringBuilder sb;
	
	public PreparedQueryConditionsBuilderORM(PrepareQueryFieldReferenceBuilder fieldReferenceBuilder, boolean atRoot) {
		super(atRoot);

		if (fieldReferenceBuilder == null) {
			throw new IllegalArgumentException("fieldReferenceBuilder == null");
		}

		this.fieldReferenceBuilder = fieldReferenceBuilder;
		this.sb = new StringBuilder();
	}
	
	final void appendFieldReference(StringBuilder s ,FieldReference r) {
		if (r instanceof FieldReferenceAlias) {
			fieldReferenceBuilder.appendAliasFieldReference(s, (FieldReferenceAlias)r);
		}
		else if (r instanceof FieldReferenceEntity) {
			fieldReferenceBuilder.appendEntityFieldReference(s, (FieldReferenceEntity)r);
		}
		else {
			throw new UnsupportedOperationException("Unknown field reference type " + r.getClass().getName());
		}
	}
	
	@Override
	final void addJoinCondition(ConditionsType type, FieldReference left, EClauseOperator operator, FieldReference right) {

		final ConditionsType lastJoinType = updateJoinType(type);

		final String os = getConditionsString(lastJoinType == null ? ConditionsType.SINGLE : type);

		sb.append(os).append(' ');
		
		appendFieldReference(sb, left);
		
		if (operator != EClauseOperator.IS_EQUAL) {
			throw new IllegalStateException("Only equals supported for field joins");
		}
		
		sb.append(" = ");

		appendFieldReference(sb, right);
		
		sb.append(' ');
	}
	
	

	private static final String getConditionsString(ConditionsType type) {
		
		if (type == null) {
			throw new IllegalArgumentException("type == null");
		}
		
		final String os;
		
		switch (type) {
		case SINGLE:
			os = "WHERE";
			break;
			
		case AND:
			os = "AND";
			break;
			
		case OR:
			os = "OR";
			break;
			
		default:
			throw new UnsupportedOperationException("Unknown conditions type " + type);
		}

		return os;
	}

	@Override
	final void resolveFromParams(StringBuilder sb, ParamValueResolver resolver) {
		
		final ConditionsType type = getType();

		
		boolean first;
		
		if (this.sb.length() == 0) {
			first = true;
		}
		else {
			sb.append(' ').append(this.sb.toString());

			first = false;
		}

		for (PreparedQueryCondition condition : getConditions()) {
		
			if (first) {
				if (isAtRoot()) {
					sb.append(' ').append("WHERE").append(' ');
				}
				
				first = false;
			}
			else {
				// 
				sb.append(' ').append(getConditionsString(type)).append(' ');
			}
			
			if (condition instanceof PreparedQueryConditionNested) {
				final PreparedQueryConditionNested nested = (PreparedQueryConditionNested)condition;

				sb.append('(');

				nested.getSub().resolveFromParams(sb, resolver);

				sb.append(')');
			}
			else if (condition instanceof PreparedQueryConditionComparison) {
				// comparison
				final PreparedQueryConditionComparison comparison = (PreparedQueryConditionComparison)condition;

				appendFieldReference(sb, comparison.getLhs());
				
				sb.append(' ');

				comparison.getRhs().resolve(sb, resolver);
			}
			else {
				throw new UnsupportedOperationException("Unknown condition type " + condition.getClass().getSimpleName());
			}
		}
	}
}
