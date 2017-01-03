package com.neaterbits.query.sql.dsl.api;

final class JPAConditionToOperator {

	<QUERY> PreparedQueryComparisonRHS convert(ExecutableQuery<QUERY> q, QUERY query, int conditionIdx, ConditionValue value, StringBuilder sb) {
		
		final EClauseOperator operator = q.getRootConditionOperator(query, conditionIdx);
		
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

			q.getRootConditionValue(query, conditionIdx).visit(conditionValueVisitor, sb);

			sb.append(")");
			if (Boolean.TRUE) {
				throw new UnsupportedOperationException("TODO - support paramterized by returing a custom JPACondition");
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
		
		return ret;
	}

	private static JPACondition appendLike(boolean wildcardBefore, boolean wildcardAfter, ConditionValue value, StringBuilder param) {

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

			ret = new JPAConditionResolved(param.toString());

		} else if (value instanceof ConditionValue_Param) {

			final ConditionValue_Param conditionValueParam = (ConditionValue_Param) value;

			ret = new JPAConditionLikeWithParamUnresolved(
					param.toString(),
					wildcardBefore,
					wildcardAfter,
					conditionValueParam.getParam());
		} else {
			throw new UnsupportedOperationException(
					"Neither string nor param value for LIKE query: " + value.getClass().getName());
		}

		return ret;
	}

	private static JPACondition appendOpAndValue(String op, ConditionValue value, StringBuilder sb) {
		sb.append(op).append(" ");

		return value.visit(conditionValueVisitor, sb);

		//param.completeResolvedCondition();
	}

	private static final ConditionValueVisitor<StringBuilder, JPACondition> conditionValueVisitor = new ConditionValueVisitor<StringBuilder, JPACondition>() {

		@Override
		public JPACondition onLiteralAny(ConditionValue_Literal_Any<?> value, StringBuilder param) {

			appendLiteral(value.getLiteral(), param);

			return null;
		}

		@Override
		public JPACondition onLiteralString(ConditionValue_Literal_String value, StringBuilder param) {

			appendStringLiteral(value.getLiteral(), param);

			return null;
		}

		@Override
		public JPACondition onArray(ConditionValue_Array value, StringBuilder param) {

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
		public JPACondition onParam(ConditionValue_Param value, StringBuilder param) {

			/*
			param.appendParam(value.getParam());

			return null;
			*/
			throw new UnsupportedOperationException("TODO");
		}

		@Override
		public JPACondition onGetter(ConditionValue_Getter value, StringBuilder param) {
			throw new UnsupportedOperationException("Getter should have been compiled");
		}

		@Override
		public JPACondition onFieldReference(ConditionValue_FieldRerefence value, StringBuilder param) {
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

	private static void appendStringLiteral(String literal, StringBuilder param) {
		param.append("'").append(literal).append("'");
	}

	private static void appendLiteral(Object literal, StringBuilder param) {

		if (literal instanceof String) {
			appendStringLiteral((String) literal, param);
		} else if (literal instanceof Integer) {
			param.append(String.valueOf((Integer) literal));
		} else {
			throw new UnsupportedOperationException("Unknown literal of type " + literal.getClass().getName());
		}
	}

}
