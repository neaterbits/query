package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;

import com.neaterbits.query.sql.dsl.api.PreparedQueryBuilder.FieldReference;

final class PreparedQueryConditionsBuilderJPA extends PreparedQueryConditionsBuilder {

	private final boolean atRoot;
	private final StringBuilder sb;

	private ConditionsType joinType;
	private ConditionsType comparisonType;
	private List<PreparedQueryCondition> conditions;
	
	PreparedQueryConditionsBuilderJPA(boolean atRoot) {
		this.sb = new StringBuilder();
		
		this.atRoot = atRoot;
		this.joinType = null;
		this.comparisonType = null;
		this.conditions = new ArrayList<>();
	}
	
	private void updateJoinType(ConditionsType type) {
		if (type == null) {
			throw new IllegalArgumentException("type == null");
		}
		
		if (this.joinType == null) {
			if (type != ConditionsType.AND) {
				throw new IllegalStateException("Expected AND for joins");
			}
			
			this.joinType = type;
		}
		else {
			if (type != ConditionsType.AND) {
				throw new IllegalStateException("Expected AND for joins");
			}
			
			if (this.joinType != ConditionsType.AND) {
				throw new IllegalStateException("Expected already AND for joins");
			}
		}
	}
	
	private void updateComparisonType(ConditionsType type) {
		if (type == null) {
			throw new IllegalArgumentException("type == null");
		}
		
		if (this.comparisonType == null) {
			if (type != ConditionsType.SINGLE && type != ConditionsType.AND && type != ConditionsType.OR) {
				throw new IllegalStateException("Expected SINGLE, AND or OR as first: " + type);
			}
			
			this.comparisonType = type;
		}
		else {
			if (type != ConditionsType.AND && type != ConditionsType.OR) {
				throw new IllegalStateException("Expected AND or OR for comparison");
			}
			
			if (joinType != null && type != joinType) {
				throw new IllegalStateException("Mismatch with join type");
			}
			
			// Check that does not change eg from AND to OR
			if (this.comparisonType != ConditionsType.SINGLE) {
				if (type != this.comparisonType) {
					throw new IllegalArgumentException("Changed in comparison type");
				}
			}
			
			this.comparisonType = type;
		}
	}
		
		

	@Override
	void addJoinCondition(ConditionsType type, FieldReference left, EClauseOperator operator, FieldReference right) {

		updateJoinType(type);
		
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
	PreparedQueryConditionsBuilder addNested(ConditionsType type) {

		updateJoinType(type);
		
		if (type == null) {
			throw new IllegalArgumentException("type == null");
		}
		
		final PreparedQueryConditionsBuilderJPA sub = new PreparedQueryConditionsBuilderJPA(false);

		conditions.add(new PreparedQueryConditionNested(sub));
		
		return sub;
	}
	
	@Override
	void addComparisonCondition(ConditionsType type, PreparedQueryConditionComparison condition) {
		
		updateComparisonType(type);
		
		if (condition == null) {
			throw new IllegalArgumentException("condition == null");
		}

		conditions.add(condition);
	}

	boolean isEmpty() {
		return conditions.isEmpty();
	}

	int size() {
		return conditions.size();
	}

	ConditionsType getType() {
		return joinType != null ? joinType : comparisonType;
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

		boolean first = true;

		for (PreparedQueryCondition condition : conditions) {
		
			if (first) {
				if (atRoot) {
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
	
	
	/*
	
	
	private static String clauseToString(EClauseOperator clauseOperator) {
		switch (clauseOperator) {
		case 
		}
	}
	*/
}
