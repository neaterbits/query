package com.neaterbits.query.sql.dsl.api;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 */

abstract class Collector_NestedFunctions_Base<
		MODEL, 
		RESULT,
		
		NAMED_RET extends ISharedFunction_After<MODEL, RESULT>,
		ALIAS_RET extends ISharedFunction_After<MODEL, RESULT>,
		UNDECIDED_RET extends ISharedFunction_After<MODEL, RESULT>,
		
		NAMED_SUM_LONG_RET  extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_COUNT_RET		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_LENGTH_RET		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		
		NAMED_BYTE_RET		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_SHORT_RET		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_INTEGER_RET	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_LONG_RET		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_BIGINTEGER_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_FLOAT_RET		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_DOUBLE_RET	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_BIGDECIMAL_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_STRING_RET 	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_DATE_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_CALENDAR_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_SQLDATE_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_SQLTIME_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_SQLTIMESTAMP_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		
		
		ALIAS_SUM_LONG_RET  extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_COUNT_RET		extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_LENGTH_RET	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,

		ALIAS_BYTE_RET 		extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_SHORT_RET 	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_INTEGER_RET 	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_LONG_RET    	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_BIGINTEGER_RET    extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_FLOAT_RET    	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_DOUBLE_RET  	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_BIGDECIMAL_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_STRING_RET  	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_DATE_RET 		extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_CALENDAR_RET 	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_SQLDATE_RET 		extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_SQLTIME_RET 		extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_SQLTIMESTAMP_RET 	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,

		UNDECIDED_SUM_LONG_RET  extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_COUNT_RET		extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_LENGTH_RET	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,

		UNDECIDED_BYTE_RET 		extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_SHORT_RET 	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_INTEGER_RET 	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_LONG_RET    	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_BIGINTEGER_RET    extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_FLOAT_RET    	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_DOUBLE_RET  	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_BIGDECIMAL_RET extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_STRING_RET  	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_DATE_RET 		extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_CALENDAR_RET 	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_SQLDATE_RET 		extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_SQLTIME_RET 		extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_SQLTIMESTAMP_RET 	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>
		
		> extends Collector_Functions_Base<
		
		MODEL,
		RESULT,
		
		NAMED_RET,
		ALIAS_RET,
		UNDECIDED_RET,

		NAMED_SUM_LONG_RET,
		NAMED_COUNT_RET,
		NAMED_LENGTH_RET,
		
		NAMED_BYTE_RET,
		NAMED_SHORT_RET,
		NAMED_INTEGER_RET,
		NAMED_LONG_RET,
		NAMED_BIGINTEGER_RET,
		NAMED_FLOAT_RET,
		NAMED_DOUBLE_RET,
		NAMED_BIGDECIMAL_RET,
		NAMED_STRING_RET,
		NAMED_DATE_RET,
		NAMED_CALENDAR_RET,
		NAMED_SQLDATE_RET,
		NAMED_SQLTIME_RET,
		NAMED_SQLTIMESTAMP_RET,

		ALIAS_SUM_LONG_RET,
		ALIAS_COUNT_RET,
		ALIAS_LENGTH_RET,

		ALIAS_BYTE_RET,
		ALIAS_SHORT_RET,
		ALIAS_INTEGER_RET,
		ALIAS_LONG_RET,
		ALIAS_BIGINTEGER_RET,
		ALIAS_FLOAT_RET,
		ALIAS_DOUBLE_RET,
		ALIAS_BIGDECIMAL_RET,
		ALIAS_STRING_RET,
		ALIAS_DATE_RET,
		ALIAS_CALENDAR_RET,
		ALIAS_SQLDATE_RET,
		ALIAS_SQLTIME_RET,
		ALIAS_SQLTIMESTAMP_RET,
		
		UNDECIDED_SUM_LONG_RET,
		UNDECIDED_COUNT_RET,
		UNDECIDED_LENGTH_RET,

		UNDECIDED_BYTE_RET,
		UNDECIDED_SHORT_RET,
		UNDECIDED_INTEGER_RET,
		UNDECIDED_LONG_RET,
		UNDECIDED_BIGINTEGER_RET,
		UNDECIDED_FLOAT_RET,
		UNDECIDED_DOUBLE_RET,
		UNDECIDED_BIGDECIMAL_RET,
		UNDECIDED_STRING_RET,
		UNDECIDED_DATE_RET,
		UNDECIDED_CALENDAR_RET,
		UNDECIDED_SQLDATE_RET,
		UNDECIDED_SQLTIME_RET,
		UNDECIDED_SQLTIMESTAMP_RET
		
		
		> {
	
	private final List<FunctionExpression> functions;
	private boolean collected;


	// Overridable in case have to switch instance
	@SuppressWarnings("unchecked")
	ISharedFunction_Next<MODEL, RESULT, NAMED_RET> getNamedNoParamNext() {
		return (ISharedFunction_Next<MODEL, RESULT, NAMED_RET>)this;
	}
	
	@SuppressWarnings("unchecked")
	ISharedFunction_Next<MODEL, RESULT, ALIAS_RET> getAliasNoParamNext() {
		return (ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>)this;
	}

	@SuppressWarnings("unchecked")
	ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET> getUndecidedNoParamNext() {
		return (ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>)this;
	}
	
	/*
	Collector_SharedFunctions_Base() {
		this.functions = new ArrayList<>();
	}
	*/

	Collector_NestedFunctions_Base(/* Collector_NestedFunctions_Base<MODEL, RESULT> last */) {
		this.functions = new ArrayList<FunctionExpression>();
		this.collected = false;
	}
	
	Collector_NestedFunctions_Base(Collector_NestedFunctions_Base<
				MODEL, RESULT, ?, ?, ?,
				?, ?, ?,
				?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
				?, ?, ?,
				?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
				?, ?, ?,
				?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> toCopy) {
		this.functions = new ArrayList<>(toCopy.functions);
		this.collected = false;
	}

	
	final void addNoParamFunctionToList(FunctionBase function) {
		if (function == null) {
			throw new IllegalArgumentException("function == null");
		}
		
		functions.add(new FunctionExpression(function));
	}

	// After sub-expression, we must always collect since functions list are uses solely for the purpose of creating NestedFunctionCallsExpression 
	private Expression createFinalExpressionsFromSub() {
		
		if (collected) {
			throw new IllegalStateException("already collected");
		}
		
		final Expression ret;

		if (functions.isEmpty()) {
			throw new IllegalStateException("No functions");
		}
		else if (functions.size() == 1) {
			// This may occur eg in map().absOf(b -> b.something()) 
			// where we have to collect at initial one, but we were still in nested function collection instance here
			// because we did not know what would come after map(), it might has well been eg. map().abs().sqrtOf(...)
			// in which case there would be more than one function
			ret = functions.get(0);
			
			//throw new IllegalStateException("Should not collect when only 1 function");
		}
		else {
			ret = new NestedFunctionCallsExpression(new CollectedFunctions(functions));
		}

		this.collected = true;

		return ret;
	}
	

	private Expression createFinalExpressionFromFieldAccess_Simple(FunctionBase function, FieldExpression field) {
		return createFinalExpressionFromFieldAccess_Multi(function, field);
	}

	// Collects functions after having received  
	private Expression createFinalExpressionFromFieldAccess_Multi(FunctionBase function, Expression ... expressions) {
		
		if (collected) {
			throw new IllegalStateException("already collected");
		}
		
		final Expression expression;
		
		// May have collected functions already
		if (functions.isEmpty()) {
			expression = new FunctionExpression(function, expressions);
		}
		else {
			// nested functions

			// Add function that field  is passed to
			functions.add(new FunctionExpression(function, expressions));
			
			final CollectedFunctions collected = new CollectedFunctions(functions);
			
			expression = new NestedFunctionCallsExpression(collected);
		}

		this.collected = true;
		
		return expression;
	}
	

	/*********************** Named ***********************/
	

	abstract ISharedFunction_Next<MODEL, RESULT, NAMED_RET> continueAfterNamedComparableFunctions(Expression expression);
	abstract ISharedFunction_Next<MODEL, RESULT, NAMED_RET> continueAfterNamedStringFunctions(Expression expression);

	private <T, R extends Comparable<?>> Expression intAddFinalFieldFunction_Simple_Named(FunctionBase function, Function<T, R> getter) {

		final Expression expression = createFinalExpressionFromFieldAccess_Simple(function, new FieldExpression(getter));

		return expression;
	}

	private <T, R extends Comparable<?>> Expression intAddFinalFieldFunction_MultiParam_Named(FunctionBase function, Expression ... expressions) {

		final Expression expression = createFinalExpressionFromFieldAccess_Multi(function, expressions);

		return expression;
	}

	@Override
	final <T, R extends Comparable<?>> ISharedFunction_Next<MODEL, RESULT, NAMED_RET> 
			addFinalFieldFunction_Simple_Arithmetic_Named(Function_Arithmetic function, Function<T, R> getter) {
				
		final Expression expression = intAddFinalFieldFunction_Simple_Named(function, getter);
		
		return continueAfterNamedComparableFunctions(expression);

	}

	@Override
	final <T, R extends Comparable<?>> ISharedFunction_Next<MODEL, RESULT, NAMED_RET>
		addFinalFieldFunction_Simple_Aggregate_Named(Function_Aggregate function, Function<T, R> getter) {

		final Expression expression = intAddFinalFieldFunction_Simple_Named(function, getter);

		return continueAfterNamedComparableFunctions(expression);
	}

	@Override
	final <T> ISharedFunction_Next<MODEL, RESULT, NAMED_RET> addFinalFieldFunction_Simple_String_Named(Function_String function, IFunctionString<T> getter) {
		final Expression expression = intAddFinalFieldFunction_Simple_Named(function, getter);
		
		return continueAfterNamedStringFunctions(expression);
	}

	@Override
	ISharedFunction_Next<MODEL, RESULT, NAMED_RET> addFinalFieldFunction_Multi_Numeric_Named(Function_Arithmetic function, Expression... expressions) {
		
		final Expression expression = intAddFinalFieldFunction_MultiParam_Named(function, expressions);
		
		return continueAfterNamedComparableFunctions(expression);
	}

	@Override
	ISharedFunction_Next<MODEL, RESULT, NAMED_RET> addFinalFieldFunction_Multi_String_Named(Function_String function, Expression... expressions) {
		
		final Expression expression = intAddFinalFieldFunction_MultiParam_Named(function, expressions);
		
		return continueAfterNamedStringFunctions(expression);
	}
	

	@Override
	@SuppressWarnings("unchecked")
	final <R extends Comparable<R>, CLAUSE> CLAUSE addSubNumeric(Function_Arithmetic function, ISharedSubOperandsFunction_Named<MODEL, RESULT, R> sub, Expression ... expressions) {
		
		// Must build sub-functions with parameters
		final FunctionExpression expression = SubExpressionUtil.addSubNumericForFunction(function, sub, expressions);
		
		functions.add(expression);
		
		final Expression collected = createFinalExpressionsFromSub();

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

	private <R extends Comparable<?>> Expression intAddFinalFieldFunction_Simple_Alias(FunctionBase function, Supplier<R> getter) {
		
		final Expression expression = createFinalExpressionFromFieldAccess_Simple(function, new FieldExpression(getter));
		
		return expression;
	}

	private <T, R extends Comparable<?>> Expression intAddFinalFieldFunction_Multi_Alias(FunctionBase function, Expression ... expressions) {

		final Expression expression = createFinalExpressionFromFieldAccess_Multi(function, expressions);

		return expression;
	}
	
	
	@Override
	final <R extends Comparable<?>> ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>
			addFinalFieldFunction_Simple_Arithmetic_Alias(Function_Arithmetic function, Supplier<R> getter) {
				
		final Expression expression =  intAddFinalFieldFunction_Simple_Alias(function, getter);
		
		return continueAfterAliasComparableFunctions(expression);
	}

	@Override
	final <R extends Comparable<?>> ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>
			addFinalFieldFunction_Simple_Aggregate_Alias(Function_Aggregate function, Supplier<R> getter) {

		final Expression expression = intAddFinalFieldFunction_Simple_Alias(function, getter);
		
		return continueAfterAliasComparableFunctions(expression);
	}

	@Override
	final ISharedFunction_Next<MODEL, RESULT, ALIAS_RET> addFinalFieldFunction_Simple_String_Alias(Function_String function, ISupplierString getter) {
		
		final Expression expression = intAddFinalFieldFunction_Simple_Alias(function, getter);
		
		return continueAfterAliasStringFunctions(expression);
	}
	
	@Override
	ISharedFunction_Next<MODEL, RESULT, ALIAS_RET> addFinalFieldFunction_Multi_Numeric_Alias(Function_Arithmetic function, Expression... expressions) {
		
		final Expression expression = intAddFinalFieldFunction_Multi_Alias(function, expressions);
		
		return continueAfterAliasComparableFunctions(expression);
	}

	@Override
	ISharedFunction_Next<MODEL, RESULT, ALIAS_RET> addFinalFieldFunction_Multi_String_Alias(Function_String function, Expression... expressions) {
		
		final Expression expression = intAddFinalFieldFunction_Multi_Alias(function, expressions);
		
		return continueAfterAliasStringFunctions(expression);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	final <R extends Comparable<R>, CLAUSE> CLAUSE addSubNumeric(Function_Arithmetic function, ISharedSubOperandsFunction_Alias<MODEL, RESULT, R> sub, Expression ... expressions) {
		
		// Must build sub-functions with parameters
		final FunctionExpression expression = SubExpressionUtil.addSubNumericForFunction(function, sub, expressions);
		
		functions.add(expression);
		
		final Expression collected = createFinalExpressionsFromSub();

		return (CLAUSE)continueAfterAliasComparableFunctions(collected);
	}

	/*********************** Undecided ***********************/

	abstract ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET> continueAfterUndecidedComparableFunctions(Expression expression);
	abstract ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET> continueAfterUndecidedStringFunctions(Expression expression);

	@Override
	@SuppressWarnings("unchecked")
	final <R extends Comparable<R>, CLAUSE> CLAUSE addSubNumeric(Function_Arithmetic function, ISharedSubOperandsFunction_Undecided<MODEL, RESULT, R> sub, Expression ... expressions) {
		
		// Must build sub-functions with parameters
		final FunctionExpression expression = SubExpressionUtil.addSubNumericForFunction(function, sub, expressions);
		
		functions.add(expression);
		
		final Expression collected = createFinalExpressionsFromSub();

		return (CLAUSE)continueAfterUndecidedComparableFunctions(collected);
	}
}

