package com.neaterbits.query.sql.dsl.api;

import com.neaterbits.query.sql.dsl.api.PreparedQueryBuilder.FieldReference;

final class PreparedQueryConditionsBuilderJPA extends PreparedQueryConditionsBuilder {

	private final StringBuilder sb;

	PreparedQueryConditionsBuilderJPA(boolean atRoot) {
		super(atRoot);

		this.sb = new StringBuilder();
	}

	@Override
	PreparedQueryConditionsBuilder createConditionsBuilder(boolean atRoot) {
		return new PreparedQueryConditionsBuilderJPA(atRoot);
	}

	@Override
	void addJoinCondition(ConditionsType type, FieldReference left, EClauseOperator operator, FieldReference right) {

		final ConditionsType lastJoinType = updateJoinType(type);

		final String os = getConditionsString(lastJoinType == null ? ConditionsType.SINGLE : type);

		sb.append(os).append(' ');
		
		JPAPreparedQueryBuilder.appendFieldReference(sb, left);
		
		if (operator != EClauseOperator.IS_EQUAL) {
			throw new IllegalStateException("Only equals supported for field joins");
		}
		
		sb.append(" = ");

		JPAPreparedQueryBuilder.appendFieldReference(sb, right);
		
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
	void resolveFromParams(StringBuilder sb, ParamValueResolver resolver) {
		
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

				JPAPreparedQueryBuilder.appendFieldReference(sb, comparison.getLhs());
				
				sb.append(' ');
				
				comparison.getRhs().resolve(sb, resolver);
			}
			else {
				throw new UnsupportedOperationException("Unknown condition type " + condition.getClass().getSimpleName());
			}
		}
	}
}
