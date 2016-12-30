package com.neaterbits.query.sql.dsl.api;

import java.util.List;

import com.neaterbits.query.sql.dsl.api.PreparedQueryBuilder.FieldReference;

final class PreparedQueryConditionsBuilderJPA extends PreparedQueryConditionsBuilder {

	private final StringBuilder sb;

	private boolean nested;
	private ConditionsType type;
	private List<PreparedQueryCondition> conditions;
	
	PreparedQueryConditionsBuilderJPA() {
		this.sb = new StringBuilder();
		
		this.nested = false;
		this.type = null;
		this.conditions = null;
	}

	@Override
	void addCondition(ConditionsType type, FieldReference left, EClauseOperator operator, FieldReference right) {
		
		final String os = getConditionsString(type);

		sb.append(os).append(' ');
		
		JPAPreparedQueryBuilder.appendFieldReference(sb, left);
		
		if (operator != EClauseOperator.IS_EQUAL) {
			throw new IllegalStateException("Only equals supported for field joins");
		}
		
		sb.append(" = ");

		JPAPreparedQueryBuilder.appendFieldReference(sb, right);
		
		sb.append(' ');
	}
	
	@Override
	void addNested(ConditionsType type, List<PreparedQueryCondition> conditions) {
		addConditions(true, type, conditions);
	}

	@Override
	void addConditions(ConditionsType type, List<PreparedQueryCondition> conditions) {
		addConditions(false, type, conditions);
	}

	boolean isEmpty() {
		return conditions.isEmpty();
	}

	int size() {
		return conditions.size();
	}

	ConditionsType getType() {
		return type;
	}
	
	private void addConditions(boolean nested, ConditionsType type, List<PreparedQueryCondition> conditions) {
		if (type == null) {
			throw new IllegalArgumentException("type == null");
		}
		
		if (conditions == null) {
			throw new IllegalArgumentException("conditions == null");
		}
		
		if (conditions.isEmpty()) {
			throw new IllegalArgumentException("no conditions");
		}
		
		switch (type) {
		
		case SINGLE:
			if (conditions.size() != 1) {
				throw new IllegalStateException("Expected exactly one condition: " + conditions);
			}
			break;
			
		case AND:
		case OR:
			if (conditions.size() < 2) {
				throw new IllegalStateException("Expected two or more conditions: " + conditions);
			}
			break;

		default:
			throw new UnsupportedOperationException("Unknown conditons type " + type);
		}

		this.nested = nested;
		this.type = type;
		this.conditions = conditions;
	}

	private static final String getConditionsString(ConditionsType type) {
		
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
	void addAllConditions(StringBuilder sb, ParamValueResolver resolver) {
		

		boolean prependType;
		
		if (nested) {
			sb.append(' ').append(getConditionsString(type)).append(' ');
			sb.append('(');
			
			prependType = false;
		}
		else {
			prependType = true;
		}
		
		for (PreparedQueryCondition condition : conditions) {
			
			if (prependType) {
				sb.append(' ').append(getConditionsString(type)).append(' ');
			}
			
			JPAPreparedQueryBuilder.appendFieldReference(sb, condition.getLhs());
			condition.getLhs();
			
			sb.append(' ');
			
			condition.getRhs().resolve(sb, resolver);
			
			prependType = true;
		}
		
		if (nested) {
			sb.append(')');
		}
	}
	
	
	/*
	
	
	private static String clauseToString(EClauseOperator clauseOperator) {
		switch (clauseOperator) {
		case 
		}
	}
	*/
}
