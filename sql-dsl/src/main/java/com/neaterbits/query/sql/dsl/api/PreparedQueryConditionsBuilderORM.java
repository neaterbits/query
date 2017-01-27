package com.neaterbits.query.sql.dsl.api;

import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

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
	
	abstract void resolveFunction(FunctionBase function, int idx, StringBuilder sb, BiConsumer<Integer, StringBuilder> appendNext);

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

				// We must add any functions
				final List<FunctionBase> funcs = comparison.getLhsFunctions();
				
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

	protected class AppendNextFunction implements BiConsumer<Integer, StringBuilder> {

		private final List<FunctionBase> funcs;
		private final PreparedQueryConditionComparison comparison;
		private final int num;
		
		AppendNextFunction(List<FunctionBase> funcs, PreparedQueryConditionComparison comparison) {

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
		public void accept(Integer idx, StringBuilder sb) {
			
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
				
				final FunctionBase func = funcs.get(nextIdx);
				
				// resolve next call
				resolveFunction(func, nextIdx, sb, this);
			}
			else {
				throw new IllegalStateException("idx out of range");
			}
		}
	}
}
