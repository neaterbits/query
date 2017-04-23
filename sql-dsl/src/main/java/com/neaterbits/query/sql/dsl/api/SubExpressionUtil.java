package com.neaterbits.query.sql.dsl.api;

final class SubExpressionUtil {

	static <MODEL, RESULT, R extends Comparable<R>, CLAUSE> FunctionExpression addSubNumericForFunction(Function_Arithmetic function, ISharedSubOperandsFunction_Named<MODEL, RESULT, R> sub, Expression ... expressions) {
		
		final FunctionExpression expression = collectSubFunction(function, sub, true, expressions);

		return expression;
	}
	
	static <MODEL, RESULT, R extends Comparable<R>, CLAUSE> FunctionExpression addSubNumericForFunction(Function_Arithmetic function, ISharedSubOperandsFunction_Alias<MODEL, RESULT, R> sub, Expression ... expressions) {
		
		final FunctionExpression expression = collectSubFunction(function, sub, true, expressions);

		return expression;
	}
	
	

	static <MODEL, RESULT, CLAUSE> Expression addSubStringForFunction(Function_String function, ISharedSubOperandsFunction_String_Named<MODEL, RESULT> sub /* , Collector_NestedFunctions_Base<MODEL, RESULT> last */) {
		
		final Expression expression = collectSubFunction(function, sub, false); //, last);

		return expression;
	}

	static <MODEL, RESULT, R extends Comparable<R>, CLAUSE> Expression addSubNumericForOperator(Operator operator, ISharedSubOperandsFunction_Named<MODEL, RESULT, R> sub /* , Collector_NestedFunctions_Base<MODEL, RESULT> last */) {
		
		final Expression expression = intCollectSub(sub, true); // last);

		return expression;
	}

	
	private static <MODEL, RESULT, R extends Comparable<R>> ExpressionList intCollectSub(ISharedSubOperandsFunction_Named<MODEL, RESULT, R> sub, boolean numeric) {
		
		if (sub == null) {
			throw new IllegalArgumentException("sub == null");
		}
		
		
		// We'll just create a nested-expression, collect function-calls
		/*
		final List<Expression> expressions = new ArrayList<>();

		final ISharedCollector_Functions_Callback_Named<MODEL, RESULT, ISharedSubOperand_End_Named<MODEL, RESULT, R>> callback
				= new ISharedCollector_Functions_Callback_Named<MODEL, RESULT, ISharedSubOperand_End_Named<MODEL, RESULT, R>>() {

			@Override
			public ISharedFunction_Next<MODEL, RESULT, ISharedSubOperand_End_Named<MODEL, RESULT, R>> 
					onComparable(Expression expression) {

				if (!numeric) {
					throw new IllegalStateException("not numeric");
				}

				//expressions.add(new NestedFunctionCallsExpression(functions, getter));
				expressions.add(expression);

				return new Collector_Expression_List_Sub<>(expression);
			}

			@Override
			public ISharedFunction_Next<MODEL, RESULT, ISharedSubOperand_End_Named<MODEL, RESULT, R>>
					onString(Expression expression) {
				
				if (numeric) {
					throw new IllegalStateException("numeric");
				}

				//expressions.add(new NestedFunctionCallsExpression(functions, getter));
				expressions.add(expression);
				
				return new ResultMapperOps_String_Sub<>(expression);
			}
		};
		*/
		
		final SubOperandsBuilder_Initial_Named<
			MODEL,
			RESULT,
			R,
			
			ISharedSubOperand_End_Named<MODEL, RESULT, R>
		
		
			> builder = new SubOperandsBuilder_Initial_Named<>();
		
		
		final ISharedSubOperandsBuilder_Named<MODEL, RESULT, R, ISharedSubOperand_End_Named<MODEL, RESULT, R>> b = builder;
		
		sub.apply(b);
		
		final ExpressionList expressionList = builder.collectAsExpressionList(false); // do not check, allows for expression lists of 1 item
		
		return expressionList;
	}
	

	private static <MODEL, RESULT, R extends Comparable<R>> ExpressionList intCollectSub(ISharedSubOperandsFunction_Alias<MODEL, RESULT, R> sub, boolean numeric) {
		
		if (sub == null) {
			throw new IllegalArgumentException("sub == null");
		}
		
		
		final SubOperandsBuilder_Initial_Alias<
			MODEL,
			RESULT,
			R,
			
			ISharedSubOperand_End_Alias<MODEL, RESULT, R>
		
		
			> builder = new SubOperandsBuilder_Initial_Alias<>();
		
		
		final ISharedSubOperandsBuilder_Alias<MODEL, RESULT, R, ISharedSubOperand_End_Alias<MODEL, RESULT, R>> b = builder;
		
		sub.apply(b);
		
		
		final ExpressionList expressionList = builder.collectAsExpressionList(false); // do not check, allows for expression lists of 1 item
		
		return expressionList;
	}
	
	private static Expression [] merge(ExpressionList list, Expression ... expressions) {
		final Expression [] ret;
		
		if (list == null) {
			throw new IllegalArgumentException("list == null");
		}
		
		if (expressions.length == 0) {
			ret = new Expression[] { list };
		}
		else {
			ret = new Expression[expressions.length + 1];
			
			ret[0] = list;

			int dstIdx = 1;
			for (Expression expression : expressions) {
				if (expression == null) {
					throw new IllegalStateException("expression == null");
				}
				
				ret[dstIdx ++] = expression;
			}
		}
		
		return ret;
	}
	
	private static <MODEL, RESULT, R extends Comparable<R>> FunctionExpression collectSubFunction(FunctionBase function, ISharedSubOperandsFunction_Named<MODEL, RESULT, R> sub, boolean numeric, Expression ... expressions) {
		
		if (function == null) {
			throw new IllegalArgumentException("function == null");
		}
		
		final ExpressionList collected = intCollectSub(sub, numeric);
		
		return new FunctionExpression(function, merge(collected, expressions));
	}
	
	private static <MODEL, RESULT, R extends Comparable<R>> FunctionExpression collectSubFunction(FunctionBase function, ISharedSubOperandsFunction_Alias<MODEL, RESULT, R> sub, boolean numeric, Expression ... expressions) {
		
		if (function == null) {
			throw new IllegalArgumentException("function == null");
		}
		
		final ExpressionList collected = intCollectSub(sub, numeric); // TODO, last);
		
		return new FunctionExpression(function, merge(collected, expressions));
	}
}
