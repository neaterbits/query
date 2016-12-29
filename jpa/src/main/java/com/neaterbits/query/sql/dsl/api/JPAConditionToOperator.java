package com.neaterbits.query.sql.dsl.api;

final class JPAConditionToOperator {

	<QUERY> void convert(ExecutableQuery<QUERY> q, QUERY query, int conditionIdx, CompileConditionParam param) {
		
		final EClauseOperator operator = q.getRootConditionOperator(query, conditionIdx);
		
		switch (operator) {
		case IS_EQUAL:
			appendOpAndValue("=", param.getValue(), param);
			break;

		case NOT_EQUAL:
			appendOpAndValue("!=", param.getValue(), param);
			break;

		case IN:
			param.append("(");

			q.getRootConditionValue(query, conditionIdx).visit(conditionValueVisitor, param);

			param.append(")");
			break;

		case GREATER_THAN:
			appendOpAndValue(">", param.getValue(), param);
			break;
			
		case GREATER_OR_EQUAL:
			appendOpAndValue(">=", param.getValue(), param);
			break;
			
		case LESS_THAN:
			appendOpAndValue("<", param.getValue(), param);
			break;
			
		case LESS_OR_EQUAL:
			appendOpAndValue("<=", param.getValue(), param);
			break;
			
		case STARTS_WITH:
			appendLike(false, true, param.getValue(), param);
			break;
			
		case ENDS_WITH:
			appendLike(true, false, param.getValue(), param);
			break;
			
		case CONTAINS:
			appendLike(true, true, param.getValue(), param);
			break;
			
		case MATCHES:
			throw new IllegalArgumentException("matches not supported");
		}


	}

	private static void appendLike(boolean wildcardBefore, boolean wildcardAfter, ConditionValueImpl value,
			CompileConditionParam param) {

		param.append("LIKE ");

		if (value instanceof ConditionValueLiteralStringImpl) {

			param.append("'");

			if (wildcardBefore) {
				param.append("%");
			}

			final String stringValue = ((ConditionValueLiteralStringImpl) value).getLiteral();

			if (stringValue == null) {
				throw new IllegalArgumentException("stringValue == null");
			}

			param.append(stringValue);

			if (wildcardAfter) {
				param.append("%");
			}

			param.append("'");

			param.completeResolvedCondition();
		} else if (value instanceof ConditionValueParamImpl) {

			final ConditionValueParamImpl conditionValueParam = (ConditionValueParamImpl) value;

			param.unresolvedCondition(prefix -> new JPAConditionLikeWithParamUnresolved(prefix, wildcardBefore,
					wildcardAfter, conditionValueParam.getParam()));
		} else {
			throw new UnsupportedOperationException(
					"Neither string nor param value for LIKE query: " + value.getClass().getName());
		}
	}

	private static void appendOpAndValue(String op, ConditionValueImpl value, CompileConditionParam param) {
		param.append(op).append(" ");

		value.visit(conditionValueVisitor, param);

		param.completeResolvedCondition();
	}

	private static final ConditionValueVisitor<CompileConditionParam, Void> conditionValueVisitor = new ConditionValueVisitor<CompileConditionParam, Void>() {

		@Override
		public Void onLiteralAny(ConditionValueLiteralAnyImpl<?> value, CompileConditionParam param) {

			appendLiteral(value.getLiteral(), param);

			return null;
		}

		@Override
		public Void onLiteralString(ConditionValueLiteralStringImpl value, CompileConditionParam param) {

			appendStringLiteral(value.getLiteral(), param);

			return null;
		}

		@Override
		public Void onArray(ConditionValueArrayImpl value, CompileConditionParam param) {

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
		public Void onParam(ConditionValueParamImpl value, CompileConditionParam param) {

			param.appendParam(value.getParam());

			return null;
		}

		@Override
		public Void onGetter(ConditionValueGetterImpl value, CompileConditionParam param) {
			throw new UnsupportedOperationException("Getter should have been compiled");
		}

		@Override
		public Void onFieldReference(ConditionValueFieldRerefenceImpl value, CompileConditionParam param) {

			QueryDataSourceJPA.prepareFieldReference(param::append, value.getFieldReference(), param.getEntityManager());

			return null;
		}
	};

	private static void appendStringLiteral(String literal, CompileConditionParam param) {
		param.append("'").append(literal).append("'");
	}

	private static void appendLiteral(Object literal, CompileConditionParam param) {

		if (literal instanceof String) {
			appendStringLiteral((String) literal, param);
		} else if (literal instanceof Integer) {
			param.append(String.valueOf((Integer) literal));
		} else {
			throw new UnsupportedOperationException("Unknown literal of type " + literal.getClass().getName());
		}
	}

}
