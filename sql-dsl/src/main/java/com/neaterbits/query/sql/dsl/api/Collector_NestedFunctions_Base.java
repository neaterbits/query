package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

abstract class Collector_NestedFunctions_Base<
		MODEL, 
		RESULT,
		
		NAMED_RET extends ISharedFunction_After<MODEL, RESULT>,
		ALIAS_RET extends ISharedFunction_After<MODEL, RESULT>,
		
		NAMED_SUM_LONG_RET  extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_COUNT_RET		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_SHORT_RET		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_INTEGER_RET	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_LONG_RET		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_DOUBLE_RET	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_BIGDECIMAL_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_DATE_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_STRING_RET 	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		
		
		ALIAS_SUM_LONG_RET  extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_COUNT_RET		extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_SHORT_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_INTEGER_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_LONG_RET    extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_DOUBLE_RET  extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_BIGDECIMAL_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_DATE_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_STRING_RET  extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>
		
		> extends Collector_Functions_Base<
		
		MODEL,
		RESULT,
		
		NAMED_RET,
		ALIAS_RET,

		NAMED_SUM_LONG_RET,
		NAMED_COUNT_RET,
		NAMED_SHORT_RET,
		NAMED_INTEGER_RET,
		NAMED_LONG_RET,
		NAMED_DOUBLE_RET,
		NAMED_BIGDECIMAL_RET,
		NAMED_DATE_RET,
		NAMED_STRING_RET,

		ALIAS_SUM_LONG_RET,
		ALIAS_COUNT_RET,
		ALIAS_SHORT_RET,
		ALIAS_INTEGER_RET,
		ALIAS_LONG_RET,
		ALIAS_DOUBLE_RET,
		ALIAS_BIGDECIMAL_RET,
		ALIAS_DATE_RET,
		ALIAS_STRING_RET
		
		> {
	
	private final List<FunctionExpression> functions;


	// Overridable in case have to switch instance
	@SuppressWarnings("unchecked")
	ISharedFunction_Next<MODEL, RESULT, NAMED_RET> getNamedNoParamNext() {
		return (ISharedFunction_Next<MODEL, RESULT, NAMED_RET>)this;
	}
	
	@SuppressWarnings("unchecked")
	ISharedFunction_Next<MODEL, RESULT, ALIAS_RET> getAliasNoParamNext() {
		return (ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>)this;
	}
	
	/*
	Collector_SharedFunctions_Base() {
		this.functions = new ArrayList<>();
	}
	*/

	Collector_NestedFunctions_Base(/* Collector_NestedFunctions_Base<MODEL, RESULT> last */) {
		this.functions = new ArrayList<FunctionExpression>();
				
				/*
				
			last != null 
				? new ArrayList<>(last.functions)
				: new ArrayList<>();
				*/
	}
	
	Collector_NestedFunctions_Base(Collector_NestedFunctions_Base<MODEL, RESULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> toCopy) {
		this.functions = new ArrayList<>(toCopy.functions);
	}

	
	final void addNoParam(FunctionBase function) {
		if (function == null) {
			throw new IllegalArgumentException("function == null");
		}
		
		functions.add(new FunctionExpression(function));
	}
	

	/*********************** Named ***********************/
	
	private Expression collectExpression() {
		final Expression ret;

		if (functions.isEmpty()) {
			throw new IllegalStateException("No functions");
		}
		else if (functions.size() == 1) {
			throw new IllegalStateException("Should not collect when only 1 function");
		}
		else {
			ret = new NestedFunctionCallsExpression(new CollectedFunctions(functions));
		}

		return ret;
	}
	

	private Expression createFunctionExpression(FunctionBase function, FieldExpression field) {
		final Expression expression;
		
		// May have collected functions already
		if (functions.isEmpty()) {
			expression = new FunctionExpression(function, field);
		}
		else {
			// nested functions

			// Add function that is passed field
			functions.add(new FunctionExpression(function, field));
			
			// addNoParam(function);

			
			final CollectedFunctions collected = new CollectedFunctions(functions);
			
			expression = new NestedFunctionCallsExpression(collected);
		}

		return expression;
	}

	abstract ISharedFunction_Next<MODEL, RESULT, NAMED_RET> continueAfterNamedComparableFunctions(Expression expression);
	abstract ISharedFunction_Next<MODEL, RESULT, NAMED_RET> continueAfterNamedStringFunctions(Expression expression);

	private <T, R extends Comparable<?>> Expression addNamed(FunctionBase function, Function<T, R> getter) {

		final Expression expression = createFunctionExpression(function, new FieldExpression(getter));

		return expression;
	}
	
	@Override
	final <T, R extends Comparable<?>> ISharedFunction_Next<MODEL, RESULT, NAMED_RET> 
			abstractaddAndReturnComparable(Function_Arithmetic function, Function<T, R> getter) {
				
		final Expression expression = addNamed(function, getter);
		
		return continueAfterNamedComparableFunctions(expression);

	}

	@Override
	final <T, R extends Comparable<?>> ISharedFunction_Next<MODEL, RESULT, NAMED_RET>
		abstractaddAndReturnComparable(Function_Aggregate function, Function<T, R> getter) {

		final Expression expression = addNamed(function, getter);

		return continueAfterNamedComparableFunctions(expression);
	}

	@Override
	final <T> ISharedFunction_Next<MODEL, RESULT, NAMED_RET> addAndReturnString(Function_String function, StringFunction<T> getter) {
		final Expression expression = addNamed(function, getter);
		
		return continueAfterNamedStringFunctions(expression);
	}

	@Override
	@SuppressWarnings("unchecked")
	final <R extends Comparable<R>, CLAUSE> CLAUSE addSubNumeric(Function_Arithmetic function, ISharedSubOperandsFunction_Named<MODEL, RESULT, R> sub) {
		
		// Must build sub-functions with parameters
		final FunctionExpression expression = SubExpressionUtil.addSubNumericForFunction(function, sub);
		
		functions.add(expression);
		
		final Expression collected = collectExpression();

		return (CLAUSE)continueAfterNamedComparableFunctions(collected);
	}

	@Override
	final <CLAUSE> CLAUSE addSubString(Function_String function, ISharedSubOperandsFunction_String_Named<MODEL, RESULT> sub) {
		
		throw new UnsupportedOperationException("TODO");
	}

	
	/*********************** Alias ***********************/

	abstract ISharedFunction_Next<MODEL, RESULT, ALIAS_RET> continueAfterAliasComparableFunctions(Expression expression);
	abstract ISharedFunction_Next<MODEL, RESULT, ALIAS_RET> continueAfterAliasStringFunctions(Expression expression);
		
	/*
	private <R extends Comparable<?>> ISharedFunction_Next<MODEL, RESULT, ALIAS_RET> addAlias(FunctionBase function, Supplier<R> getter) {
		
		final Expression expression = createFunctionExpression(function, new FieldExpression(getter));
		
		return continueAfterAliasFunctions(expression);
	}
	*/

	private <R extends Comparable<?>> Expression addAlias(FunctionBase function, Supplier<R> getter) {
		
		final Expression expression = createFunctionExpression(function, new FieldExpression(getter));
		
		return expression;
	}
	
	@Override
	final <R extends Comparable<?>> ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>
			abstractaddAndReturnComparable(Function_Arithmetic function, Supplier<R> getter) {
				
		final Expression expression =  addAlias(function, getter);
		
		return continueAfterAliasComparableFunctions(expression);
	}

	@Override
	final <R extends Comparable<?>> ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>
			abstractaddAndReturnComparable(Function_Aggregate function, Supplier<R> getter) {

		final Expression expression = addAlias(function, getter);
		
		return continueAfterAliasComparableFunctions(expression);
	}

	@Override
	final ISharedFunction_Next<MODEL, RESULT, ALIAS_RET> addAndReturnString(Function_String function, ISupplierString getter) {
		
		final Expression expression = addAlias(function, getter);
		
		return continueAfterAliasStringFunctions(expression);
	}
}

