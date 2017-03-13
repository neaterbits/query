package com.neaterbits.query.sql.dsl.api;

import java.util.List;
import java.util.function.BiConsumer;

/**
 * Common base class for ORM queries / native ANSI SQL due to commonalities
 * 
 * @author nhl
 *
 */

final class PreparedQueryConditionsBuilderORM extends PreparedQueryConditionsBuilder {

	private final QueryDialect_SQL dialect;
	private final QueryBuilder sb;
	
	
	PreparedQueryConditionsBuilderORM(QueryDialect_SQL dialect, EConditionsClause conditionsClause, boolean atRoot) {
		super(conditionsClause, atRoot);

		if (dialect == null) {
			throw new IllegalArgumentException("dialect == null");
		}
		

		this.dialect = dialect;
		this.sb = new QueryBuilder();
	}
	
	@Override
	PreparedQueryConditionsBuilder createConditionsBuilder(EConditionsClause conditionsClause, boolean atRoot) {
		return new PreparedQueryConditionsBuilderORM(dialect, conditionsClause, atRoot);
	}

	
	@Override
	final void addJoinCondition(ConditionsType type, FieldReference left, EClauseOperator operator, FieldReference right) {

		final ConditionsType lastJoinType = updateJoinType(type);

		final String os = getConditionsString(lastJoinType == null ? ConditionsType.SINGLE : type, getConditionsClause());

		sb.append(os).append(' ');
		
		dialect.appendFieldReference(sb, left);
		
		if (operator != EClauseOperator.IS_EQUAL) {
			throw new IllegalStateException("Only equals supported for field joins");
		}
		
		sb.append(" = ");

		dialect.appendFieldReference(sb, right);
		
		sb.append(' ');
	}
	
	

	private static final String getConditionsString(ConditionsType type, EConditionsClause conditionsClause) {
		
		if (type == null) {
			throw new IllegalArgumentException("type == null");
		}
		
		final String os;
		
		switch (type) {
		case SINGLE:
			switch (conditionsClause) {
			case WHERE:
				os = "WHERE";
				break;

			case HAVING:
				os = "HAVING";
				break;

			default:
				throw new UnsupportedOperationException("Unknown conditions clause " + conditionsClause);
			}
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
	final void resolveFromParams(QueryBuilder sb, ParamValueResolver resolver) {

		final ConditionsType type = getType();
		
		boolean first;

		if (this.sb.isEmpty()) {
			first = true;
		}
		else {
			sb.append(' ').append(this.sb.asQueryString());

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
				sb.append(' ').append(getConditionsString(type, getConditionsClause())).append(' ');
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

				// We must add any functions
				final List<FunctionCalcBase> funcs = comparison.getLhsFunctions();
				
				if (funcs != null) {
					// recursively resolve so that we nest output
					PreparedQueryBuilderUtil.resolveFunction(dialect, funcs, comparison.getLhs(), sb);
				}
				else {
					// Must add any functions before 
					dialect.appendFieldReference(sb, comparison.getLhs());
				}
				
				
				sb.append(' ');

				comparison.getRhs().resolve(sb, resolver);
			}
			else {
				throw new UnsupportedOperationException("Unknown condition type " + condition.getClass().getSimpleName());
			}
		}
	}

}
