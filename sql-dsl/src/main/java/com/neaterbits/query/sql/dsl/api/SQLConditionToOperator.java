package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import com.neaterbits.query.sql.dsl.api.entity.ETemporalType;
import com.neaterbits.query.sql.dsl.api.entity.IEntityModelUtil;

final class SQLConditionToOperator {

	<QUERY> PreparedQueryComparisonRHS convert(CompiledFieldReference field, EClauseOperator operator, ConditionValue value, ConditionStringBuilder b) {
		
		final PreparedQueryComparisonRHS ret;
		
		final ConditionValueVisitorParam builder = new ConditionValueVisitorParam(field, b);
		
		switch (operator) {
		
		case IS_NULL:
			b.append("IS NULL");
			ret = new SQLConditionResolved(b.getBuiltString(), null);
			break;
			
		case IS_NOT_NULL:
			b.append("IS NOT NULL");
			ret = new SQLConditionResolved(b.getBuiltString(), null);
			break;
		
		case IS_EQUAL:
			ret = appendOpAndValue("=", value, builder);
			break;

		case NOT_EQUAL:
			ret = appendOpAndValue("!=", value, builder);
			break;

		case IN:
			b.append("in ");
			
			if (value.getType() == EConditionValue.PARAM) {
				// IN list is a parameter so ask builder to add since may be different ways, depending
				ret = b.convertInParam((ConditionValue_Param)value);
			}
			else {
				// Literals, should be same for any type of SQL like syntax (JPQL or native)
				b.append('(');
				
				

				final Param<?> resolvedParam = value.visit(conditionValueVisitor, builder);
				
				if (resolvedParam != null) {
					throw new IllegalStateException("Should never return parameters as this is for resolved-values");
				}
				
				b.append(')');
				
				ret = new SQLConditionResolved(b.getBuiltString(), resolvedParam);
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
			ret = appendLike(false, true, value, b);
			break;
			
		case ENDS_WITH:
			ret = appendLike(true, false, value, b);
			break;
			
		case CONTAINS:
			ret = appendLike(true, true, value, b);
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

	private static SQLCondition appendLike(boolean wildcardBefore, boolean wildcardAfter, ConditionValue value, ConditionStringBuilder builder) {

		final SQLCondition ret;
		
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

			ret = new SQLConditionResolved(builder.getBuiltString(), null);

		} else if (value instanceof ConditionValue_Param) {

			final ConditionValue_Param conditionValueParam = (ConditionValue_Param) value;

			ret = new SQLConditionLikeWithParamUnresolved(
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

	private static SQLCondition appendOpAndValue(String op, ConditionValue value, ConditionValueVisitorParam visitorParam) {
		visitorParam.builder.append(op).append(" ");

		final Param<?> resolvedParam = value.visit(conditionValueVisitor, visitorParam);

		// new resolved condition
		return new SQLConditionResolved(visitorParam.builder.getBuiltString(), resolvedParam);
	}
	
	private static class ConditionValueVisitorParam {
		private final CompiledFieldReference field;
		private final ConditionStringBuilder builder;

		public ConditionValueVisitorParam(CompiledFieldReference field, ConditionStringBuilder conditionStringBuilder) {
			this.field = field;
			this.builder = conditionStringBuilder;
		}
	}

	private static final ConditionValueVisitor<ConditionValueVisitorParam, Param<?>> conditionValueVisitor = new ConditionValueVisitor<ConditionValueVisitorParam, Param<?>>() {

		@Override
		public Param<?> onLiteralAny(ConditionValue_Literal_Any<?> value, ConditionValueVisitorParam param) {

			appendLiteral(param.field, param.builder.getEntityModelUtil(), param.builder.getDialect(), value.getLiteral(), param.builder::append);

			return null;
		}

		@Override
		public Param<?> onLiteralString(ConditionValue_Literal_String value, ConditionValueVisitorParam param) {

			appendStringLiteral(value.getLiteral(), param.builder::append);

			return null;
		}

		@Override
		public Param<?> onArray(ConditionValue_Array value, ConditionValueVisitorParam param) {
			
			appendLiteralArray(param.field, param.builder.getEntityModelUtil(), param.builder.getDialect(), value.getValues(), param.builder::append);

			return null;
		}
		

		@Override
		public Param<?> onList(ConditionValue_List value, ConditionValueVisitorParam param) {
			
			appendLiteralList(param.field, param.builder.getEntityModelUtil(), param.builder.getDialect(), value.getValues(), param.builder::append);
			
			
			return null;
		}

		@Override
		public Param<?> onParam(ConditionValue_Param value, ConditionValueVisitorParam visitorParam) {
			
			final Param<?> param = value.getParam();
			
			// We must append a param-value to the built query, that is 
			// for JPQL this could be :param1, or ? for a native-query.
			visitorParam.builder.appendParam(param);

			return param;
		}

		@Override
		public Param<?> onGetter(ConditionValue_Getter value, ConditionValueVisitorParam param) {
			throw new UnsupportedOperationException("Getter should have been compiled");
		}

		@Override
		public Param<?> onFieldReference(ConditionValue_FieldRerefence value, ConditionValueVisitorParam param) {
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

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	private static final SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// TODO: Test time-ranges to make sure that correctly compares to literals
	private static final SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSS");

																		    //01234567890123456
	// private static final SimpleDateFormat checkFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

	private static boolean areAll0sFrom(String s, int pos) {

		if (pos >= s.length()) {
			throw new IllegalArgumentException("pos after end of string: \"" + s + "\" : " + pos);
		}

		for (int i = 0; i < s.length(); ++ i) {
			if (s.charAt(i) != '0') {
				return false;
			}
		}

		return true;
	}

	private static void appendJavaUtilDateLiteral(CompiledFieldReference getter, IEntityModelUtil entityModelUtil, java.util.Date literal, Function<String, ?> append) {

		final Class<?> type = getter.getSource().getType();
		final ETemporalType temporalType = entityModelUtil.getTemporalTypeForGetter(type, getter.getGetter().getGetterMethod());

		appendJavaUtilDateLiteral(temporalType, literal, append);
	}

	private static void appendJavaUtilDateLiteral(ETemporalType temporalType, java.util.Date literal, Function<String, ?> append) {
		// Figure out whether can add this
		
		// Figure date-parameters for type

		final StringBuilder sb = new StringBuilder(100);

		// Figure out whether can be expressed as date or time or timestamp
		
		switch (temporalType) {
		case DATE:
			sb.append("{d '");
			sb.append(dateFormat.format(literal));
			sb.append("'}");
			break;

		case TIME:
			sb.append("{t '");
			sb.append(timeFormat.format(literal));
			sb.append("'}");
			break;
			
		case TIMESTAMP:
			sb.append("{ts '");
			sb.append(timestampFormat.format(literal));
			sb.append("'}");
			break;
			
		default:
			throw new UnsupportedOperationException("TODO - support more temporal types");
		
		}
		
		/*
		final String toCheck = checkFormat.format(literal);
		
		if (areAll0sFrom(toCheck, 14)) {
			// has fraction, must be specified as timestamp
			throw new UnsupportedOperationException("TODO - must test all these literals");
		}
		else {
			if (areAll0sFrom(toCheck, 8)) {
				// Must be expressed as time
				throw new UnsupportedOperationException("TODO - must test all these literals: " + toCheck);
			}
			else {
				// express as date.
				sb.append("{d '");
				sb.append(dateFormat.format(literal));
				sb.append("'}");
			}
		}
		*/

		append.apply(sb.toString());
	}

	static void appendLiteral(CompiledFieldReference fieldReference, IEntityModelUtil entityModelUtil, QueryDialect_SQL dialect, Object literal, Function<String, ?> append) {

		if (literal instanceof String) {
			appendStringLiteral((String) literal, append);
		} else if (literal instanceof Integer) {
			append.apply(String.valueOf((Integer) literal));
		} else if (literal instanceof BigDecimal) {
			final String asString = dialect.getBigDecimalLiteral((BigDecimal)literal);

			append.apply(asString);
		}
		else if (literal instanceof Double) {
			append.apply(String.valueOf((Double)literal));
		}
		else if (literal instanceof java.util.Date) {
			// Must append date as string, but must figure out what type this is first so that
			// can determine whether is date or time or timestamp
			appendJavaUtilDateLiteral(fieldReference, entityModelUtil, (java.util.Date)literal, append);
		} else {
			throw new UnsupportedOperationException("Unknown literal of type " + literal.getClass().getName());
		}
	}

	static void appendLiteralArray(CompiledFieldReference fieldReference, IEntityModelUtil entityModelUtil, QueryDialect_SQL dialect, Object [] values, Function<String, ?> append) {
		appendLiteralList(fieldReference, entityModelUtil, dialect, Arrays.asList(values), append);
	}

	static void appendLiteralList(CompiledFieldReference fieldReference, IEntityModelUtil entityModelUtil, QueryDialect_SQL dialect, List<?> values, Function<String, ?> append) {

		final int num = values.size();

		for (int i = 0; i < num; ++i) {
			if (i > 0) {
				append.apply(", ");
			}

			appendLiteral(fieldReference, entityModelUtil, dialect, values.get(i), append);
		}
	}

}
