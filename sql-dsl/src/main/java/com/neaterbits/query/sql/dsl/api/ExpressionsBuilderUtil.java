package com.neaterbits.query.sql.dsl.api;

import com.neaterbits.query.sql.dsl.api.entity.IEntityModelUtil;

final class ExpressionsBuilderUtil {
	static <QUERY> void outputExpressions(QueryDialect_SQL dialect, IEntityModelUtil entityModelUtil, QueryBuilder s, ExecutableQueryExpressions expressions, EFieldAccessType fieldReferenceType) {
		// Start at root and recurse downwards
		
		int level = 0;
		int [] context = new int[100]; // probably less than 100 nested-levels
		
		context[0] = 0; // just one root expression
		
		outputExpression(dialect, entityModelUtil, s, expressions, fieldReferenceType, level, context);
	}
	
	private static <QUERY> void outputExpression(QueryDialect_SQL dialect, IEntityModelUtil entityModelUtil, QueryBuilder s, ExecutableQueryExpressions expressions, EFieldAccessType fieldReferenceType, int level, int [] context) {
		// Start at root and recurse downwards
		
		final EExpressionType type = expressions.getExpressionType(level, context);
		
		switch (type) {
		case FIELD:
			
			final CompiledFieldReference fieldReference = expressions.getFieldReference(level, context);
			
			dialect.appendFieldReference(s, prepareFieldReference(dialect, entityModelUtil, fieldReferenceType, fieldReference));
			break;
			
		case VALUE:
			// TODO: handle this properly, must wrap string literals etc
			
			final Comparable<?> value = expressions.getValue(level, context);
			
			s.append(value.toString());
			break;
			
		case LIST:
			s.append('(');

			final int num = expressions.getSubCount(level, context);

			for (int i = 0; i < num; ++ i) {

				if (i > 0) {
					final char opChar;

					final Operator operator = expressions.getListOperator(level, context, i - 1);
					
					switch (operator) {
					case PLUS: 		opChar = '+'; break;
					case MINUS: 	opChar = '-'; break;
					case MULTIPLY: 	opChar = '*'; break;
					case DIVIDE: 	opChar = '/'; break;
					
					default:
						throw new UnsupportedOperationException("Unknown arithmetic operator " + operator);
					}
					
					s.sb.append(' ').append(opChar).append(' ');
				}
				
				// recurse
				context[level + 1] = i;

				outputExpression(dialect, entityModelUtil, s, expressions, fieldReferenceType, level + 1, context);
			}

			s.append(')');
			break;
			
		case FUNCTION:
			final FunctionBase function = expressions.getFunction(level, context);
			final String functionName = dialect.getFunctionName(function);
			
			s.append(functionName);
			s.append('(');
			
			final int numParameters = expressions.getSubCount(level, context);
			
			for (int i = 0; i < numParameters; ++ i) {
				
				if (i > 0) {
					s.append(", ");
				}
				
				context[level + 1] = i;

				// recurse for param
				outputExpression(dialect, entityModelUtil, s, expressions, fieldReferenceType, level + 1, context);
			}
			
			s.append(')');
			
			break;
			
		default:
			throw new UnsupportedOperationException("Unknown expression type " + type);
		}
		
	}

	static <QUERY> FieldReference prepareFieldReference(QueryDialect_SQL dialect, IEntityModelUtil entityModelUtil, EFieldAccessType fieldReferenceType, CompiledFieldReference field) {
		
		final TypeMapSource source = field.getSource();

		final CompiledGetter getter = field.getGetter();

		final String columnName = getColumnOrFieldNameForGetter(dialect, entityModelUtil, source, getter);

		final FieldReference ret;
		
		switch (fieldReferenceType) {
		case ALIAS:
			ret = new FieldReference_Alias(source.getName(), columnName);
			break;
			
		case NAMED:
			ret = new FieldReference_Entity(source.getType(), source.getName(), columnName);
			break;
			
		default:
			throw new IllegalStateException("field reference type: " + fieldReferenceType);
		}

		return ret;
	}
	
	static final String getColumnOrFieldNameForGetter(QueryDialect_SQL dialect, IEntityModelUtil entityModelUtil, TypeMapSource source, CompiledGetter getter) {
		
		return dialect.getFieldNameForGetter(entityModelUtil, source.getType(), getter.getGetterMethod());
		
		// return entityModelUtil.getModel().getColumnNameForGetter(source.getType(), getter.getGetterMethod());
	}

}
