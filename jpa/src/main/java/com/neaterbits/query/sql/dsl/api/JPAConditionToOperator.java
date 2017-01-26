package com.neaterbits.query.sql.dsl.api;


final class JPAConditionToOperator {

	<QUERY> PreparedQueryComparisonRHS convert(ExecutableQuery<QUERY> q, QUERY query, EClauseOperator operator, ConditionValue value, ConditionStringBuilder sb) {
		
		final JPACondition ret;
		
		switch (operator) {
		case IS_EQUAL:
			ret = appendOpAndValue("=", value, sb);
			break;

		case NOT_EQUAL:
			ret = appendOpAndValue("!=", value, sb);
			break;

		case IN:
			sb.append("(");

			value.visit(conditionValueVisitor, sb);

			sb.append(")");
			if (Boolean.TRUE) {
				throw new UnsupportedOperationException("TODO - support parameterized by returing a custom JPACondition");
			}
			else {
				ret = null;
			}
			break;

		case GREATER_THAN:
			ret = appendOpAndValue(">", value, sb);
			break;
			
		case GREATER_OR_EQUAL:
			ret = appendOpAndValue(">=", value, sb);
			break;
			
		case LESS_THAN:
			ret = appendOpAndValue("<", value, sb);
			break;
			
		case LESS_OR_EQUAL:
			ret = appendOpAndValue("<=", value, sb);
			break;
			
		case STARTS_WITH:
			ret = appendLike(false, true, value, sb);
			break;
			
		case ENDS_WITH:
			ret = appendLike(true, false, value, sb);
			break;
			
		case CONTAINS:
			ret = appendLike(true, true, value, sb);
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

	private static JPACondition appendLike(boolean wildcardBefore, boolean wildcardAfter, ConditionValue value, ConditionStringBuilder param) {

		final JPACondition ret;
		
		param.append("LIKE ");

		if (value instanceof ConditionValue_Literal_String) {

			param.append("'");

			if (wildcardBefore) {
				param.append("%");
			}

			final String stringValue = ((ConditionValue_Literal_String) value).getLiteral();

			if (stringValue == null) {
				throw new IllegalArgumentException("stringValue == null");
			}

			param.append(stringValue);

			if (wildcardAfter) {
				param.append("%");
			}

			param.append("'");

			ret = new JPAConditionResolved(param.getBuiltString());

		} else if (value instanceof ConditionValue_Param) {

			final ConditionValue_Param conditionValueParam = (ConditionValue_Param) value;

			ret = new JPAConditionLikeWithParamUnresolved(
					param.getBuiltString(),
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

		value.visit(conditionValueVisitor, sb);

		// new resolved condition
		return new JPAConditionResolved(sb.getBuiltString());
	}

	private static final ConditionValueVisitor<ConditionStringBuilder, Void> conditionValueVisitor = new ConditionValueVisitor<ConditionStringBuilder, Void>() {

		@Override
		public Void onLiteralAny(ConditionValue_Literal_Any<?> value, ConditionStringBuilder param) {

			appendLiteral(value.getLiteral(), param);

			return null;
		}

		@Override
		public Void onLiteralString(ConditionValue_Literal_String value, ConditionStringBuilder param) {

			appendStringLiteral(value.getLiteral(), param);

			return null;
		}

		@Override
		public Void onArray(ConditionValue_Array value, ConditionStringBuilder param) {

			final Object[] values = value.getValues();

			for (int i = 0; i < values.length; ++i) {
				if (i > 0) {
					param.append(", ");
				}

				appendLiteral(values[i], param);
			}

			return null;
		}

		@Override
		public Void onParam(ConditionValue_Param value, ConditionStringBuilder param) {
			
			// We must append a param-value to the built query, that is 
			// for JPQL this could be :param1, or ? for a native-query.
			param.appendParam(value.getParam());

			return null;
		}

		@Override
		public Void onGetter(ConditionValue_Getter value, ConditionStringBuilder param) {
			throw new UnsupportedOperationException("Getter should have been compiled");
		}

		@Override
		public Void onFieldReference(ConditionValue_FieldRerefence value, ConditionStringBuilder param) {
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

	private static void appendStringLiteral(String literal, ConditionStringBuilder param) {
		param.append("'").append(literal).append("'");
	}

	private static void appendLiteral(Object literal, ConditionStringBuilder param) {

		if (literal instanceof String) {
			appendStringLiteral((String) literal, param);
		} else if (literal instanceof Integer) {
			param.append(String.valueOf((Integer) literal));
		} else {
			throw new UnsupportedOperationException("Unknown literal of type " + literal.getClass().getName());
		}
	}

}
