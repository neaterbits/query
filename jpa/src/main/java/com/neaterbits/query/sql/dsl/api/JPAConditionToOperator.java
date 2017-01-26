package com.neaterbits.query.sql.dsl.api;

import java.util.function.BiFunction;

import com.google.inject.internal.Function;

final class JPAConditionToOperator {

	<QUERY> PreparedQueryComparisonRHS convert(ExecutableQuery<QUERY> q, QUERY query, EClauseOperator operator, ConditionValue value, ConditionStringBuilder builder) {
		
		final PreparedQueryComparisonRHS ret;
		
		switch (operator) {
		case IS_EQUAL:
			ret = appendOpAndValue("=", value, builder);
			break;

		case NOT_EQUAL:
			ret = appendOpAndValue("!=", value, builder);
			break;

		case IN:
			builder.append("in ");
			
			if (value.getType() == EConditionValue.PARAM) {
				// IN list is a parameter so ask builder to add since may be different ways, depending
				ret = builder.convertInParam((ConditionValue_Param)value);
			}
			else {
				// Literals, should be same for any type of SQL like syntax (JPQL or native)
				builder.append('(');

				final Param<?> resolvedParam = value.visit(conditionValueVisitor, builder);
				
				if (resolvedParam != null) {
					throw new IllegalStateException("Should never return parameters as this is for resolved-values");
				}
				
				builder.append(')');
				
				ret = new JPAConditionResolved(builder.getBuiltString(), resolvedParam);
			}
			break;

		case GREATER_THAN:
			ret = appendOpAndValue(">", value, builder);
			break;
			
		case GREATER_OR_EQUAL:
			ret = appendOpAndValue(">=", value, builder);
			break;
			
		case LESS_THAN:
			ret = appendOpAndValue("<", value, builder);
			break;
			
		case LESS_OR_EQUAL:
			ret = appendOpAndValue("<=", value, builder);
			break;
			
		case STARTS_WITH:
			ret = appendLike(false, true, value, builder);
			break;
			
		case ENDS_WITH:
			ret = appendLike(true, false, value, builder);
			break;
			
		case CONTAINS:
			ret = appendLike(true, true, value, builder);
			break;
			
		case MATCHES:
			throw new IllegalArgumentException("matches not supported");
			
		default:
			throw new UnsupportedOperationException("Unknown comparator type " + operator);
		}
		
		if (ret == null) {
			throw new IllegalStateException("no rhs for operator " + operator);
		}
		
		return ret;
	}

	private static JPACondition appendLike(boolean wildcardBefore, boolean wildcardAfter, ConditionValue value, ConditionStringBuilder builder) {

		final JPACondition ret;
		
		builder.append("LIKE ");

		if (value instanceof ConditionValue_Literal_String) {

			builder.append("'");

			if (wildcardBefore) {
				builder.append("%");
			}

			final String stringValue = ((ConditionValue_Literal_String) value).getLiteral();

			if (stringValue == null) {
				throw new IllegalArgumentException("stringValue == null");
			}

			builder.append(stringValue);

			if (wildcardAfter) {
				builder.append("%");
			}

			builder.append("'");

			ret = new JPAConditionResolved(builder.getBuiltString(), null);

		} else if (value instanceof ConditionValue_Param) {

			final ConditionValue_Param conditionValueParam = (ConditionValue_Param) value;

			ret = new JPAConditionLikeWithParamUnresolved(
					builder.getBuiltString(),
					wildcardBefore,
					wildcardAfter,
					conditionValueParam.getParam());
		} else {
			throw new UnsupportedOperationException(
					"Neither string nor param value for LIKE query: " + value.getClass().getName());
		}

		return ret;
	}

	private static JPACondition appendOpAndValue(String op, ConditionValue value, ConditionStringBuilder sb) {
		sb.append(op).append(" ");

		final Param<?> resolvedParam = value.visit(conditionValueVisitor, sb);

		// new resolved condition
		return new JPAConditionResolved(sb.getBuiltString(), resolvedParam);
	}

	private static final ConditionValueVisitor<ConditionStringBuilder, Param<?>> conditionValueVisitor = new ConditionValueVisitor<ConditionStringBuilder, Param<?>>() {

		@Override
		public Param<?> onLiteralAny(ConditionValue_Literal_Any<?> value, ConditionStringBuilder builder) {

			appendLiteral(value.getLiteral(), builder::append);

			return null;
		}

		@Override
		public Param<?> onLiteralString(ConditionValue_Literal_String value, ConditionStringBuilder builder) {

			appendStringLiteral(value.getLiteral(), builder::append);

			return null;
		}

		@Override
		public Param<?> onArray(ConditionValue_Array value, ConditionStringBuilder builder) {

			final Object[] values = value.getValues();

			for (int i = 0; i < values.length; ++i) {
				if (i > 0) {
					builder.append(", ");
				}

				appendLiteral(values[i], builder::append);
			}

			return null;
		}

		@Override
		public Param<?> onParam(ConditionValue_Param value, ConditionStringBuilder builder) {
			
			final Param<?> param = value.getParam();
			
			// We must append a param-value to the built query, that is 
			// for JPQL this could be :param1, or ? for a native-query.
			builder.appendParam(param);

			return param;
		}

		@Override
		public Param<?> onGetter(ConditionValue_Getter value, ConditionStringBuilder builder) {
			throw new UnsupportedOperationException("Getter should have been compiled");
		}

		@Override
		public Param<?> onFieldReference(ConditionValue_FieldRerefence value, ConditionStringBuilder builder) {
			throw new UnsupportedOperationException("Field references should only be present in joins");
		}
		
		
		/*
		@Override
		public Void onFieldReference(ConditionValueFieldRerefenceImpl value, CompileConditionParam param) {

			QueryDataSourceJPA.prepareFieldReference(param::append, value.getFieldReference(), param.getEntityManager());

			return null;
		}
		*/
	};

	private static void appendStringLiteral(String literal, Function<String, ?> append) {
		append.apply("'");
		append.apply(literal);
		append.apply("'");
	}

	static void appendLiteral(Object literal, Function<String, ?> append) {

		if (literal instanceof String) {
			appendStringLiteral((String) literal, append);
		} else if (literal instanceof Integer) {
			append.apply(String.valueOf((Integer) literal));
		} else {
			throw new UnsupportedOperationException("Unknown literal of type " + literal.getClass().getName());
		}
	}

}
