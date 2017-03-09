package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class SubExpressionUtil {

	static <MODEL, RESULT, R extends Comparable<R>, CLAUSE> Expression addSubNumericForFunction(Function_Arithmetic function, ISharedSubOperandsFunction_Named<MODEL, RESULT, R> sub /*, Collector_NestedFunctions_Base<MODEL, RESULT> last */) {
		
		final Expression expression = collectSubFunction(function, sub, true); // , last);

		return expression;
	}

	static <MODEL, RESULT, CLAUSE> Expression addSubStringForFunction(Function_String function, ISharedSubOperandsFunction_String_Named<MODEL, RESULT> sub /* , Collector_NestedFunctions_Base<MODEL, RESULT> last */) {
		
		final Expression expression = collectSubFunction(function, sub, false); //, last);

		return expression;
	}

	static <MODEL, RESULT, R extends Comparable<R>, CLAUSE> Expression addSubNumericForOperator(ArithmeticOperator operator, ISharedSubOperandsFunction_Named<MODEL, RESULT, R> sub /* , Collector_NestedFunctions_Base<MODEL, RESULT> last */) {
		
		final Expression expression = intCollectSub(sub, true); // last);

		return expression;
	}

	
	private static <MODEL, RESULT, R extends Comparable<R>> ExpressionList intCollectSub(ISharedSubOperandsFunction_Named<MODEL, RESULT, R> sub, boolean numeric /*, Collector_NestedFunctions_Base<MODEL, RESULT> last */) {
		
		if (sub == null) {
			throw new IllegalArgumentException("sub == null");
		}
		
		
		// We'll just create a nested-expression, collect function-calls
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

				return new ResultMapperOps_Numeric_Sub<>(expression);
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

		final SubOperandsBuilder_Initial<MODEL, RESULT, R, ISharedSubOperand_End_Named<MODEL, RESULT, R>> ret = new SubOperandsBuilder_Initial<>(callback);
		
		sub.apply(ret);
		
		final ExpressionList expression;
		/*
		if (expressions.size() == 1) {
			expression = expressions.get(0);
		}
		else {
			throw new IllegalStateException("Expected only one expression for now");
		}
		*/
		
		// Always return list, even if just one entry, so that generated query always looks like code
		expression = new ExpressionList(expressions, Collections.emptyList(), false);
		
		return expression;
	}
	
	
	private static <MODEL, RESULT, R extends Comparable<R>> Expression collectSubFunction(FunctionBase function, ISharedSubOperandsFunction_Named<MODEL, RESULT, R> sub, boolean numeric /*, Collector_NestedFunctions_Base<MODEL, RESULT> last */) {
		
		if (function == null) {
			throw new IllegalArgumentException("function == null");
		}
		
		final ExpressionList collected = intCollectSub(sub, numeric); // TODO, last);
		
		return new FunctionExpression(function, collected);
	}
	
}
