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
	
	final void appendFieldReference(QueryBuilder s, FieldReference r) {
		if (r instanceof FieldReferenceAlias) {
			dialect.appendAliasFieldReference(s, (FieldReferenceAlias)r);
		}
		else if (r instanceof FieldReferenceEntity) {
			dialect.appendEntityFieldReference(s, (FieldReferenceEntity)r);
		}
		else {
			throw new UnsupportedOperationException("Unknown field reference type " + r.getClass().getName());
		}
	}
	
	@Override
	PreparedQueryConditionsBuilder createConditionsBuilder(EConditionsClause conditionsClause, boolean atRoot) {
		return new PreparedQueryConditionsBuilderORM(dialect, conditionsClause, atRoot);
	}

	private void resolveFunction(FunctionCalcBase function, int idx, QueryBuilder sb, BiConsumer<Integer, QueryBuilder> appendNext) {
		
		final String functionName = dialect.getFunctionName(function);
		
		sb.append(functionName).append('(');
		
		// recursively append
		appendNext.accept(idx, sb);
		
		sb.append(')');
	}
	
	@Override
	final void addJoinCondition(ConditionsType type, FieldReference left, EClauseOperator operator, FieldReference right) {

		final ConditionsType lastJoinType = updateJoinType(type);

		final String os = getConditionsString(lastJoinType == null ? ConditionsType.SINGLE : type, getConditionsClause());

		sb.append(os).append(' ');
		
		appendFieldReference(sb, left);
		
		if (operator != EClauseOperator.IS_EQUAL) {
			throw new IllegalStateException("Only equals supported for field joins");
		}
		
		sb.append(" = ");

		appendFieldReference(sb, right);
		
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
					resolveFunction(funcs.get(0), 0, sb, new AppendNextFunction(funcs, comparison));
				}
				else {
					// Must add any functions before 
					appendFieldReference(sb, comparison.getLhs());
					
				}
				
				
				sb.append(' ');

				comparison.getRhs().resolve(sb, resolver);
			}
			else {
				throw new UnsupportedOperationException("Unknown condition type " + condition.getClass().getSimpleName());
			}
		}
	}

	protected class AppendNextFunction implements BiConsumer<Integer, QueryBuilder> {

		private final List<FunctionCalcBase> funcs;
		private final PreparedQueryConditionComparison comparison;
		private final int num;
		
		AppendNextFunction(List<FunctionCalcBase> funcs, PreparedQueryConditionComparison comparison) {

			if (funcs == null) {
				throw new IllegalArgumentException("funcs == null");
			}
			
			if (comparison == null) {
				throw new IllegalArgumentException("comparison == null");
			}
			
			this.funcs = funcs;
			this.comparison = comparison;
			this.num = funcs.size();
		 }


		@Override
		public void accept(Integer idx, QueryBuilder sb) {
			
			if (idx < 0) {
				throw new IllegalArgumentException("idx < 0");
			}
			else if (idx == num - 1) {
				// at last idx, return getter
				appendFieldReference(sb, comparison.getLhs());
			}
			else if (idx < num - 1) {
				// resolve next
				final int nextIdx = idx + 1;
				
				final FunctionCalcBase func = funcs.get(nextIdx);
				
				// resolve next call
				resolveFunction(func, nextIdx, sb, this);
			}
			else {
				throw new IllegalStateException("idx out of range");
			}
		}
	}
}
