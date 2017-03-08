package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

final class SubExpressionUtil {

	static <MODEL, RESULT, R extends Comparable<R>, CLAUSE> Expression addSubNumericForFunction(Function_Arithmetic function, ISharedSubOperandsFunction_Named<MODEL, RESULT, R> sub, Collector_SharedFunctions_Base<MODEL, RESULT> last) {
		
		final Expression expression = collectSubFunction(function, sub, true, last);

		return expression;
	}

	static <MODEL, RESULT, CLAUSE> Expression addSubStringForFunction(Function_String function, ISharedSubOperandsFunction_String_Named<MODEL, RESULT> sub, Collector_SharedFunctions_Base<MODEL, RESULT> last) {
		
		final Expression expression = collectSubFunction(function, sub, false, last);

		return expression;
	}

	static <MODEL, RESULT, R extends Comparable<R>, CLAUSE> Expression addSubNumericForOperator(ArithmeticOperator operator, ISharedSubOperandsFunction_Named<MODEL, RESULT, R> sub, Collector_SharedFunctions_Base<MODEL, RESULT> last) {
		
		final Expression expression = intCollectSub(sub, true, last);

		return expression;
	}

	
	private static <MODEL, RESULT, R extends Comparable<R>> Expression intCollectSub(ISharedSubOperandsFunction_Named<MODEL, RESULT, R> sub, boolean numeric, Collector_SharedFunctions_Base<MODEL, RESULT> last) {
		
		if (sub == null) {
			throw new IllegalArgumentException("sub == null");
		}
		
		
		// We'll just create a nested-expression, collect function-calls
		final List<Expression> expressions = new ArrayList<>();
		
		final ISharedCollector_Functions_Callback_Named<MODEL, RESULT, ISharedSubOperand_End_Named<MODEL, RESULT, R>> callback
				= new ISharedCollector_Functions_Callback_Named<MODEL, RESULT, ISharedSubOperand_End_Named<MODEL, RESULT, R>>() {

			@Override
			public ISharedFunction_Next<MODEL, RESULT, ISharedSubOperand_End_Named<MODEL, RESULT, R>> 
					onComparable(CollectedFunctions functions, Function<?, ? extends Comparable<?>> getter) {

				if (!numeric) {
					throw new IllegalStateException("not numeric");
				}

				expressions.add(new NestedFunctionCallsExpression(functions, getter));

				return null;
			}

			@Override
			public ISharedFunction_Next<MODEL, RESULT, ISharedSubOperand_End_Named<MODEL, RESULT, R>>
					onString(CollectedFunctions functions, StringFunction<?> getter) {
				
				if (numeric) {
					throw new IllegalStateException("numeric");
				}

				expressions.add(new NestedFunctionCallsExpression(functions, getter));
				
				return null;
			}
		};

		final SubOperandsBuilder<MODEL, RESULT, R, ISharedSubOperand_End_Named<MODEL, RESULT, R>> ret = new SubOperandsBuilder<>(callback, last);
		
		sub.apply(ret);
		
		final Expression expression;
		if (expressions.size() == 1) {
			expression = expressions.get(0);
		}
		else {
			throw new IllegalStateException("Expected only one expression for now");
		}
		
		return expression;
	}
	
	
	private static <MODEL, RESULT, R extends Comparable<R>> Expression collectSubFunction(FunctionBase function, ISharedSubOperandsFunction_Named<MODEL, RESULT, R> sub, boolean numeric, Collector_SharedFunctions_Base<MODEL, RESULT> last) {
		
		if (function == null) {
			throw new IllegalArgumentException("function == null");
		}
		
		final Expression collected = intCollectSub(sub, numeric, last);
		
		return new FunctionExpression(function, collected);
	}
	
}
