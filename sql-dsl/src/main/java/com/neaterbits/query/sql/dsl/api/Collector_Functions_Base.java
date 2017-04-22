package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

abstract class Collector_Functions_Base<
		MODEL,
		RESULT,
		
		NAMED_RET extends ISharedFunction_After<MODEL, RESULT>,
		ALIAS_RET extends ISharedFunction_After<MODEL, RESULT>,
		
		NAMED_SUM_LONG_RET  extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_COUNT_RET		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		
		NAMED_BYTE_RET		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_SHORT_RET		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_INTEGER_RET	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_LONG_RET		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_BIGINTEGER_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_FLOAT_RET		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_DOUBLE_RET	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_BIGDECIMAL_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_STRING_RET 	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_DATE_RET 		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_CALENDAR_RET 	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_SQLDATE_RET 	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_SQLTIME_RET 	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_SQLTIMESTAMP_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		
		
		ALIAS_SUM_LONG_RET  extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_COUNT_RET		extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,

		ALIAS_BYTE_RET 		extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_SHORT_RET 	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_INTEGER_RET 	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_LONG_RET    	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_BIGINTEGER_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_FLOAT_RET    	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_DOUBLE_RET  	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_BIGDECIMAL_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_STRING_RET  	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_DATE_RET 		extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_CALENDAR_RET 	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_SQLDATE_RET 	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_SQLTIME_RET 	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_SQLTIMESTAMP_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>
		
		>

	implements 

		ISharedFunctions_Arithmetic_Named<
			MODEL, RESULT,
		
			NAMED_RET,
			NAMED_BYTE_RET,
			NAMED_SHORT_RET,
			NAMED_INTEGER_RET,
			NAMED_LONG_RET,
			NAMED_BIGINTEGER_RET,
			NAMED_FLOAT_RET,
			NAMED_DOUBLE_RET,
			NAMED_BIGDECIMAL_RET>,
			
		ISharedFunctions_String_Named<MODEL, RESULT, NAMED_RET, NAMED_STRING_RET>,
		ISharedFunctions_Aggregate_Named<NAMED_SUM_LONG_RET, NAMED_COUNT_RET, NAMED_BYTE_RET, NAMED_SHORT_RET, NAMED_INTEGER_RET, NAMED_LONG_RET, NAMED_BIGINTEGER_RET, NAMED_FLOAT_RET, NAMED_DOUBLE_RET, NAMED_BIGDECIMAL_RET, NAMED_DATE_RET>,
	
		ISharedFunctions_Arithmetic_Alias<
			MODEL, RESULT,
	
			ALIAS_RET,
			ALIAS_BYTE_RET,
			ALIAS_SHORT_RET,
			ALIAS_INTEGER_RET,
			ALIAS_LONG_RET,
			ALIAS_BIGINTEGER_RET,
			ALIAS_FLOAT_RET,
			ALIAS_DOUBLE_RET,
			ALIAS_BIGDECIMAL_RET>,
			
			
		ISharedFunctions_String_Alias<MODEL, RESULT, ALIAS_RET, ALIAS_STRING_RET>,
		ISharedFunctions_Aggregate_Alias<ALIAS_SUM_LONG_RET, ALIAS_COUNT_RET,
				ALIAS_BYTE_RET, ALIAS_SHORT_RET, ALIAS_INTEGER_RET, ALIAS_LONG_RET, ALIAS_BIGINTEGER_RET, ALIAS_FLOAT_RET, ALIAS_DOUBLE_RET, ALIAS_BIGDECIMAL_RET, ALIAS_DATE_RET>
			
{
	
	// ********* Named abstract methods *********
	@SuppressWarnings("unchecked")
	private <T, R extends Comparable<?>, RETVAL extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>> RETVAL addAndReturnType(Function_Arithmetic function, Function<T, R> getter) {
		return (RETVAL)abstractaddAndReturnComparable(function, getter);
	}
	
	@SuppressWarnings("unchecked")
	private <T, R extends Comparable<?>, RETVAL extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>> RETVAL addAndReturnType(Function_Aggregate function, Function<T, R> getter) {
		return (RETVAL)abstractaddAndReturnComparable(function, getter);
	}
	
	abstract <T, R extends Comparable<?>> ISharedFunction_Next<MODEL, RESULT, NAMED_RET> abstractaddAndReturnComparable(Function_Arithmetic function, Function<T, R> getter);

	abstract <T, R extends Comparable<?>> ISharedFunction_Next<MODEL, RESULT, NAMED_RET> abstractaddAndReturnComparable(Function_Aggregate function, Function<T, R> getter);
	
	abstract <T> ISharedFunction_Next<MODEL, RESULT, NAMED_RET> addAndReturnString(Function_String function, IFunctionString<T> getter);
	
	abstract ISharedFunction_Next<MODEL, RESULT, NAMED_RET> addAndReturnForNamedStringExpressions(Function_String function, Expression ... expressions);
	
	abstract <R extends Comparable<R>, CLAUSE> CLAUSE addSubNumeric(Function_Arithmetic function, ISharedSubOperandsFunction_Named<MODEL, RESULT, R> sub);
	
	abstract <CLAUSE> CLAUSE addSubString(Function_String function, ISharedSubOperandsFunction_String_Named<MODEL, RESULT> sub);
	
	// ********* Alias abstract methods *********

	@SuppressWarnings("unchecked")
	private <R extends Comparable<?>, RETVAL extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>> RETVAL addAndReturnType(Function_Arithmetic function, Supplier<R> getter) {
		return (RETVAL)abstractaddAndReturnComparable(function, getter);
	}
	
	@SuppressWarnings("unchecked")
	private <R extends Comparable<?>, RETVAL extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>> RETVAL addAndReturnType(Function_Aggregate function, Supplier<R> getter) {
		return (RETVAL)abstractaddAndReturnComparable(function, getter);
	}

	abstract <R extends Comparable<?>> ISharedFunction_Next<MODEL, RESULT, ALIAS_RET> abstractaddAndReturnComparable(Function_Arithmetic function, Supplier<R> getter);

	abstract <R extends Comparable<?>> ISharedFunction_Next<MODEL, RESULT, ALIAS_RET> abstractaddAndReturnComparable(Function_Aggregate function, Supplier<R> getter);

	abstract ISharedFunction_Next<MODEL, RESULT, ALIAS_RET> addAndReturnString(Function_String function, ISupplierString getter);

	abstract ISharedFunction_Next<MODEL, RESULT, ALIAS_RET> addAndReturnForAliasStringExpressions(Function_String function, Expression ... expressions);

	//********************************************************************
	//* Named
	//********************************************************************

	
	// ********* Functions *********
	
	@Override
	@SuppressWarnings("unchecked")
	public final <T> NAMED_STRING_RET lower(IFunctionString<T> getter) {
		return (NAMED_STRING_RET)addAndReturnString(Function_String_Lower.INSTANCE, getter);
	}


	@Override
	@SuppressWarnings("unchecked")
	public final <T> NAMED_STRING_RET upper(IFunctionString<T> getter) {
		return (NAMED_STRING_RET)addAndReturnString(Function_String_Upper.INSTANCE, getter);
	}

	@Override
	@SuppressWarnings("unchecked")
	public final <T> NAMED_STRING_RET trim(IFunctionString<T> getter) {
		return (NAMED_STRING_RET)addAndReturnString(Function_String_Trim.INSTANCE, getter);
	}

	@Override
	@SuppressWarnings("unchecked")
	public final <T> NAMED_STRING_RET substring(IFunctionString<T> getter, int start, int length) {
		return (NAMED_STRING_RET)addAndReturnForNamedStringExpressions(Function_String_Substring.INSTANCE, new FieldExpression(getter), new ValueExpression(start), new ValueExpression(length));
	}

	@Override
	@SuppressWarnings("unchecked")
	public final <T> NAMED_STRING_RET concat(IFunctionString<T> getter1, IFunctionString<T> getter2) {
		return (NAMED_STRING_RET)addAndReturnForNamedStringExpressions(Function_String_Concat.INSTANCE, new FieldExpression(getter1), new FieldExpression(getter2));
	}

	@Override
	@SuppressWarnings("unchecked")
	public final <T> NAMED_STRING_RET concat(IFunctionString<T> getter, String value) {
		return (NAMED_STRING_RET)addAndReturnForNamedStringExpressions(Function_String_Concat.INSTANCE, new FieldExpression(getter), new ValueExpression(value));
	}

	@Override
	@SuppressWarnings("unchecked")
	public final <T> NAMED_STRING_RET concat(String value, IFunctionString<T> getter) {
		return (NAMED_STRING_RET)addAndReturnForNamedStringExpressions(Function_String_Concat.INSTANCE, new ValueExpression(value), new FieldExpression(getter));
	}

	@Override
	@SuppressWarnings("unchecked")
	public final <T> NAMED_STRING_RET concat(IFunctionString<T> getter, Param<String> param) {
		return (NAMED_STRING_RET)addAndReturnForNamedStringExpressions(Function_String_Concat.INSTANCE, new FieldExpression(getter), new ParamExpression(param));
	}

	@Override
	@SuppressWarnings("unchecked")
	public final <T> NAMED_STRING_RET concat(Param<String> param, IFunctionString<T> getter) {
		return (NAMED_STRING_RET)addAndReturnForNamedStringExpressions(Function_String_Concat.INSTANCE, new ParamExpression(param), new FieldExpression(getter));
	}

	@Override
	public final <T> NAMED_BYTE_RET abs(IFunctionByte<T> getter) {
		return addAndReturnType(Function_Arithmetic_Abs.INSTANCE, getter);
	}

	@Override
	public final <T> NAMED_SHORT_RET abs(IFunctionShort<T> getter) {
		return addAndReturnType(Function_Arithmetic_Abs.INSTANCE, getter);
	}

	@Override
	public final <T> NAMED_INTEGER_RET abs(IFunctionInteger<T> getter) {
		return addAndReturnType(Function_Arithmetic_Abs.INSTANCE, getter);
	}

	@Override
	public final <T> NAMED_LONG_RET abs(IFunctionLong<T> getter) {
		return addAndReturnType(Function_Arithmetic_Abs.INSTANCE, getter);
	}
	
	@Override
	public final <T> NAMED_BIGINTEGER_RET abs(IFunctionBigInteger<T> getter) {
		return addAndReturnType(Function_Arithmetic_Abs.INSTANCE, getter);
	}
	
	@Override
	public final <T> NAMED_FLOAT_RET abs(IFunctionFloat<T> getter) {
		return addAndReturnType(Function_Arithmetic_Abs.INSTANCE, getter);
	}
	
	@Override
	public final <T> NAMED_DOUBLE_RET abs(IFunctionDouble<T> getter) {
		return addAndReturnType(Function_Arithmetic_Abs.INSTANCE, getter);
	}
	
	@Override
	public final <T> NAMED_BIGDECIMAL_RET abs(IFunctionBigDecimal<T> getter) {
		return addAndReturnType(Function_Arithmetic_Abs.INSTANCE, getter);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET sqrt(IFunctionByte<T> getter) {
		return addAndReturnType(Function_Arithmetic_Sqrt.INSTANCE, getter);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET sqrt(IFunctionShort<T> getter) {
		return addAndReturnType(Function_Arithmetic_Sqrt.INSTANCE, getter);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET sqrt(IFunctionInteger<T> getter) {
		return addAndReturnType(Function_Arithmetic_Sqrt.INSTANCE, getter);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET sqrt(IFunctionLong<T> getter) {
		return addAndReturnType(Function_Arithmetic_Sqrt.INSTANCE, getter);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET sqrt(IFunctionBigInteger<T> getter) {
		return addAndReturnType(Function_Arithmetic_Sqrt.INSTANCE, getter);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET sqrt(IFunctionFloat<T> getter) {
		return addAndReturnType(Function_Arithmetic_Sqrt.INSTANCE, getter);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET sqrt(IFunctionDouble<T> getter) {
		return addAndReturnType(Function_Arithmetic_Sqrt.INSTANCE, getter);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET sqrt(IFunctionBigDecimal<T> getter) {
		return addAndReturnType(Function_Arithmetic_Sqrt.INSTANCE, getter);
	}

	@Override
	public final <T> NAMED_SHORT_RET absOfShort(ISharedSubOperandsFunction_Short_Named<MODEL, RESULT> sub) {
		return addSubNumeric(Function_Arithmetic_Abs.INSTANCE, sub);
	}

	@Override
	public final <T> NAMED_INTEGER_RET absOfInteger(ISharedSubOperandsFunction_Integer_Named<MODEL, RESULT> sub) {
		return addSubNumeric(Function_Arithmetic_Abs.INSTANCE, sub);
	}

	@Override
	public final <T> NAMED_LONG_RET absOfLong(ISharedSubOperandsFunction_Long_Named<MODEL, RESULT> sub) {
		return addSubNumeric(Function_Arithmetic_Abs.INSTANCE, sub);
	}

	@Override
	public final <T> NAMED_BIGDECIMAL_RET absOfBigDecimal(ISharedSubOperandsFunction_BigDecimal_Named<MODEL, RESULT> sub) {
		return addSubNumeric(Function_Arithmetic_Abs.INSTANCE, sub);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET sqrtOf(ISharedSubOperandsFunction_Double_Named<MODEL, RESULT> sub) {
		return addSubNumeric(Function_Arithmetic_Sqrt.INSTANCE, sub);
	}
	
	// ********* Aggregate methods *********

	@Override
	public final <T> NAMED_DOUBLE_RET avg(IFunctionByte<T> field) {
		return addAndReturnType(Function_Aggregate.AVG, field);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET avg(IFunctionShort<T> field) {
		return addAndReturnType(Function_Aggregate.AVG, field);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET avg(IFunctionInteger<T> field) {
		return addAndReturnType(Function_Aggregate.AVG, field);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET avg(IFunctionLong<T> field) {
		return addAndReturnType(Function_Aggregate.AVG, field);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET avg(IFunctionBigInteger<T> field) {
		return addAndReturnType(Function_Aggregate.AVG, field);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET avg(IFunctionFloat<T> field) {
		return addAndReturnType(Function_Aggregate.AVG, field);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET avg(IFunctionDouble<T> field) {
		return addAndReturnType(Function_Aggregate.AVG, field);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET avg(IFunctionBigDecimal<T> field) {
		return addAndReturnType(Function_Aggregate.AVG, field);
	}

	@Override
	public final <T> NAMED_COUNT_RET count(IFunctionByte<T> field) {
		return addAndReturnType(Function_Aggregate.COUNT, field);
	}

	@Override
	public final <T> NAMED_COUNT_RET count(IFunctionShort<T> field) {
		return addAndReturnType(Function_Aggregate.COUNT, field);
	}

	@Override
	public final <T> NAMED_COUNT_RET count(IFunctionInteger<T> field) {
		return addAndReturnType(Function_Aggregate.COUNT, field);
	}

	@Override
	public final <T> NAMED_COUNT_RET count(IFunctionLong<T> field) {
		return addAndReturnType(Function_Aggregate.COUNT, field);
	}

	@Override
	public final <T> NAMED_COUNT_RET count(IFunctionBigInteger<T> field) {
		return addAndReturnType(Function_Aggregate.COUNT, field);
	}

	@Override
	public final <T> NAMED_COUNT_RET count(IFunctionFloat<T> field) {
		return addAndReturnType(Function_Aggregate.COUNT, field);
	}

	@Override
	public final <T> NAMED_COUNT_RET count(IFunctionDouble<T> field) {
		return addAndReturnType(Function_Aggregate.COUNT, field);
	}

	@Override
	public final <T> NAMED_COUNT_RET count(IFunctionBigDecimal<T> field) {
		return addAndReturnType(Function_Aggregate.COUNT, field);
	}

	@Override
	public final <T> NAMED_BYTE_RET max(IFunctionByte<T> field) {
		return addAndReturnType(Function_Aggregate.MAX, field);
	}

	@Override
	public final <T> NAMED_SHORT_RET max(IFunctionShort<T> field) {
		return addAndReturnType(Function_Aggregate.MAX, field);
	}

	@Override
	public final <T> NAMED_INTEGER_RET max(IFunctionInteger<T> field) {
		return addAndReturnType(Function_Aggregate.MAX, field);
	}

	@Override
	public final <T> NAMED_LONG_RET max(IFunctionLong<T> field) {
		return addAndReturnType(Function_Aggregate.MAX, field);
	}

	@Override
	public final <T> NAMED_BIGINTEGER_RET max(IFunctionBigInteger<T> field) {
		return addAndReturnType(Function_Aggregate.MAX, field);
	}

	@Override
	public final <T> NAMED_FLOAT_RET max(IFunctionFloat<T> field) {
		return addAndReturnType(Function_Aggregate.MAX, field);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET max(IFunctionDouble<T> field) {
		return addAndReturnType(Function_Aggregate.MAX, field);
	}

	@Override
	public final <T> NAMED_BIGDECIMAL_RET max(IFunctionBigDecimal<T> field) {
		return addAndReturnType(Function_Aggregate.MAX, field);
	}

	@Override
	public final <T> NAMED_DATE_RET max(IFunctionDate<T> field) {
		return addAndReturnType(Function_Aggregate.MAX, field);
	}

	@Override
	public final <T> NAMED_BYTE_RET min(IFunctionByte<T> field) {
		return addAndReturnType(Function_Aggregate.MIN, field);
	}

	@Override
	public final <T> NAMED_SHORT_RET min(IFunctionShort<T> field) {
		return addAndReturnType(Function_Aggregate.MIN, field);
	}

	@Override
	public final <T> NAMED_INTEGER_RET min(IFunctionInteger<T> field) {
		return addAndReturnType(Function_Aggregate.MIN, field);
	}

	@Override
	public final <T> NAMED_LONG_RET min(IFunctionLong<T> field) {
		return addAndReturnType(Function_Aggregate.MIN, field);
	}

	@Override
	public final <T> NAMED_BIGINTEGER_RET min(IFunctionBigInteger<T> field) {
		return addAndReturnType(Function_Aggregate.MIN, field);
	}

	@Override
	public final <T> NAMED_FLOAT_RET min(IFunctionFloat<T> field) {
		return addAndReturnType(Function_Aggregate.MIN, field);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET min(IFunctionDouble<T> field) {
		return addAndReturnType(Function_Aggregate.MIN, field);
	}

	@Override
	public final <T> NAMED_BIGDECIMAL_RET min(IFunctionBigDecimal<T> field) {
		return addAndReturnType(Function_Aggregate.MIN, field);
	}

	@Override
	public final <T> NAMED_DATE_RET min(IFunctionDate<T> field) {
		return addAndReturnType(Function_Aggregate.MIN, field);
	}

	@Override
	public final <T> NAMED_SUM_LONG_RET sum(IFunctionByte<T> field) {
		return addAndReturnType(Function_Aggregate.SUM, field);
	}

	@Override
	public final <T> NAMED_SUM_LONG_RET sum(IFunctionShort<T> field) {
		return addAndReturnType(Function_Aggregate.SUM, field);
	}

	@Override
	public final <T> NAMED_SUM_LONG_RET sum(IFunctionInteger<T> field) {
		return addAndReturnType(Function_Aggregate.SUM, field);
	}

	@Override
	public final <T> NAMED_BIGINTEGER_RET sum(IFunctionLong<T> field) {
		return addAndReturnType(Function_Aggregate.SUM, field);
	}

	@Override
	public final <T> NAMED_BIGINTEGER_RET sum(IFunctionBigInteger<T> field) {
		return addAndReturnType(Function_Aggregate.SUM, field);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET sum(IFunctionFloat<T> field) {
		return addAndReturnType(Function_Aggregate.SUM, field);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET sum(IFunctionDouble<T> field) {
		return addAndReturnType(Function_Aggregate.SUM, field);
	}

	@Override
	public final <T> NAMED_BIGDECIMAL_RET sum(IFunctionBigDecimal<T> field) {
		return addAndReturnType(Function_Aggregate.SUM, field);
	}
	
	//********************************************************************
	//* Alias
	//********************************************************************
	
	// ********* Functions *********
	
	@Override
	@SuppressWarnings("unchecked")
	public final <T> ALIAS_STRING_RET lower(ISupplierString getter) {
		return (ALIAS_STRING_RET) addAndReturnString(Function_String_Lower.INSTANCE, getter);
	}

	@Override
	@SuppressWarnings("unchecked")
	public final <T> ALIAS_STRING_RET upper(ISupplierString getter) {
		return (ALIAS_STRING_RET) addAndReturnString(Function_String_Upper.INSTANCE, getter);
	}

	@Override
	@SuppressWarnings("unchecked")
	public final <T> ALIAS_STRING_RET trim(ISupplierString getter) {
		return (ALIAS_STRING_RET) addAndReturnString(Function_String_Trim.INSTANCE, getter);
	}

	@Override
	@SuppressWarnings("unchecked")
	public final <T> ALIAS_STRING_RET substring(ISupplierString getter, int start, int length) {
		return (ALIAS_STRING_RET)addAndReturnForAliasStringExpressions(Function_String_Substring.INSTANCE, new FieldExpression(getter), new ValueExpression(start), new ValueExpression(length));
	}
	
	@Override
	public final <T> ALIAS_STRING_RET concat(ISupplierString getter1, ISupplierString getter2) {
		return (ALIAS_STRING_RET)addAndReturnForAliasStringExpressions(Function_String_Concat.INSTANCE, new FieldExpression(getter1), new FieldExpression(getter2));
	}

	@Override
	public final <T> ALIAS_STRING_RET concat(ISupplierString getter, String value) {
		return (ALIAS_STRING_RET)addAndReturnForAliasStringExpressions(Function_String_Concat.INSTANCE, new FieldExpression(getter), new ValueExpression(value));
	}

	@Override
	public final <T> ALIAS_STRING_RET concat(String value, ISupplierString getter) {
		return (ALIAS_STRING_RET)addAndReturnForAliasStringExpressions(Function_String_Concat.INSTANCE, new ValueExpression(value), new FieldExpression(getter));
	}

	@Override
	public final <T> ALIAS_STRING_RET concat(ISupplierString getter, Param<String> param) {
		return (ALIAS_STRING_RET)addAndReturnForAliasStringExpressions(Function_String_Concat.INSTANCE, new FieldExpression(getter), new ParamExpression(param));
	}

	@Override
	public final <T> ALIAS_STRING_RET concat(Param<String> param, ISupplierString getter) {
		return (ALIAS_STRING_RET)addAndReturnForAliasStringExpressions(Function_String_Concat.INSTANCE, new ParamExpression(param), new FieldExpression(getter));
	}

	@Override
	public final <T> ALIAS_BYTE_RET abs(ISupplierByte getter) {
		return addAndReturnType(Function_Arithmetic_Abs.INSTANCE, getter);
	}

	@Override
	public final <T> ALIAS_SHORT_RET abs(ISupplierShort getter) {
		return addAndReturnType(Function_Arithmetic_Abs.INSTANCE, getter);
	}

	@Override
	public final <T> ALIAS_INTEGER_RET abs(ISupplierInteger getter) {
		return addAndReturnType(Function_Arithmetic_Abs.INSTANCE, getter);
	}

	@Override
	public final <T> ALIAS_LONG_RET abs(ISupplierLong getter) {
		return addAndReturnType(Function_Arithmetic_Abs.INSTANCE, getter);
	}

	@Override
	public final <T> ALIAS_BIGINTEGER_RET abs(ISupplierBigInteger getter) {
		return addAndReturnType(Function_Arithmetic_Abs.INSTANCE, getter);
	}

	@Override
	public final <T> ALIAS_FLOAT_RET abs(ISupplierFloat getter) {
		return addAndReturnType(Function_Arithmetic_Abs.INSTANCE, getter);
	}

	@Override
	public final <T> ALIAS_DOUBLE_RET abs(ISupplierDouble getter) {
		return addAndReturnType(Function_Arithmetic_Abs.INSTANCE, getter);
	}

	@Override
	public final <T> ALIAS_BIGDECIMAL_RET abs(ISupplierBigDecimal getter) {
		return addAndReturnType(Function_Arithmetic_Abs.INSTANCE, getter);
	}

	@Override
	public final <T> ALIAS_DOUBLE_RET sqrt(ISupplierByte getter) {
		return addAndReturnType(Function_Arithmetic_Sqrt.INSTANCE, getter);
	}

	@Override
	public final <T> ALIAS_DOUBLE_RET sqrt(ISupplierShort getter) {
		return addAndReturnType(Function_Arithmetic_Sqrt.INSTANCE, getter);
	}

	@Override
	public final <T> ALIAS_DOUBLE_RET sqrt(ISupplierInteger getter) {
		return addAndReturnType(Function_Arithmetic_Sqrt.INSTANCE, getter);
	}

	@Override
	public final <T> ALIAS_DOUBLE_RET sqrt(ISupplierLong getter) {
		return addAndReturnType(Function_Arithmetic_Sqrt.INSTANCE, getter);
	}

	@Override
	public final <T> ALIAS_DOUBLE_RET sqrt(ISupplierBigInteger getter) {
		return addAndReturnType(Function_Arithmetic_Sqrt.INSTANCE, getter);
	}

	@Override
	public final <T> ALIAS_DOUBLE_RET sqrt(ISupplierFloat getter) {
		return addAndReturnType(Function_Arithmetic_Sqrt.INSTANCE, getter);
	}

	@Override
	public final <T> ALIAS_DOUBLE_RET sqrt(ISupplierDouble getter) {
		return addAndReturnType(Function_Arithmetic_Sqrt.INSTANCE, getter);
	}

	@Override
	public final <T> ALIAS_DOUBLE_RET sqrt(ISupplierBigDecimal getter) {
		return addAndReturnType(Function_Arithmetic_Sqrt.INSTANCE, getter);
	}

	// ********* Aggregate methods *********

	@Override
	public final ALIAS_DOUBLE_RET avg(ISupplierByte field) {
		return addAndReturnType(Function_Aggregate.AVG, field);
	}

	@Override
	public final ALIAS_DOUBLE_RET avg(ISupplierShort field) {
		return addAndReturnType(Function_Aggregate.AVG, field);
	}

	@Override
	public final ALIAS_DOUBLE_RET avg(ISupplierInteger field) {
		return addAndReturnType(Function_Aggregate.AVG, field);
	}

	@Override
	public final ALIAS_DOUBLE_RET avg(ISupplierLong field) {
		return addAndReturnType(Function_Aggregate.AVG, field);
	}

	@Override
	public final ALIAS_DOUBLE_RET avg(ISupplierBigInteger field) {
		return addAndReturnType(Function_Aggregate.AVG, field);
	}

	@Override
	public final ALIAS_DOUBLE_RET avg(ISupplierFloat field) {
		return addAndReturnType(Function_Aggregate.AVG, field);
	}

	@Override
	public final ALIAS_DOUBLE_RET avg(ISupplierDouble field) {
		return addAndReturnType(Function_Aggregate.AVG, field);
	}

	@Override
	public final ALIAS_DOUBLE_RET avg(ISupplierBigDecimal field) {
		return addAndReturnType(Function_Aggregate.AVG, field);
	}

	@Override
	public final ALIAS_COUNT_RET count(ISupplierByte field) {
		return addAndReturnType(Function_Aggregate.COUNT, field);
	}

	@Override
	public final ALIAS_COUNT_RET count(ISupplierShort field) {
		return addAndReturnType(Function_Aggregate.COUNT, field);
	}

	@Override
	public final ALIAS_COUNT_RET count(ISupplierInteger field) {
		return addAndReturnType(Function_Aggregate.COUNT, field);
	}

	@Override
	public final ALIAS_COUNT_RET count(ISupplierLong field) {
		return addAndReturnType(Function_Aggregate.COUNT, field);
	}

	@Override
	public final ALIAS_COUNT_RET count(ISupplierBigInteger field) {
		return addAndReturnType(Function_Aggregate.COUNT, field);
	}

	@Override
	public final ALIAS_COUNT_RET count(ISupplierFloat field) {
		return addAndReturnType(Function_Aggregate.COUNT, field);
	}

	@Override
	public final ALIAS_COUNT_RET count(ISupplierDouble field) {
		return addAndReturnType(Function_Aggregate.COUNT, field);
	}

	@Override
	public final ALIAS_COUNT_RET count(ISupplierBigDecimal field) {
		return addAndReturnType(Function_Aggregate.COUNT, field);
	}

	@Override
	public final ALIAS_BYTE_RET max(ISupplierByte field) {
		return addAndReturnType(Function_Aggregate.MAX, field);
	}

	@Override
	public final ALIAS_SHORT_RET max(ISupplierShort field) {
		return addAndReturnType(Function_Aggregate.MAX, field);
	}

	@Override
	public final ALIAS_INTEGER_RET max(ISupplierInteger field) {
		return addAndReturnType(Function_Aggregate.MAX, field);
	}

	@Override
	public final ALIAS_LONG_RET max(ISupplierLong field) {
		return addAndReturnType(Function_Aggregate.MAX, field);
	}

	@Override
	public final ALIAS_BIGINTEGER_RET max(ISupplierBigInteger field) {
		return addAndReturnType(Function_Aggregate.MAX, field);
	}

	@Override
	public final ALIAS_FLOAT_RET max(ISupplierFloat field) {
		return addAndReturnType(Function_Aggregate.MAX, field);
	}

	@Override
	public final ALIAS_DOUBLE_RET max(ISupplierDouble field) {
		return addAndReturnType(Function_Aggregate.MAX, field);
	}

	@Override
	public final ALIAS_BIGDECIMAL_RET max(ISupplierBigDecimal field) {
		return addAndReturnType(Function_Aggregate.MAX, field);
	}

	@Override
	public final ALIAS_DATE_RET max(ISupplierDate field) {
		return addAndReturnType(Function_Aggregate.MAX, field);
	}

	@Override
	public final ALIAS_BYTE_RET min(ISupplierByte field) {
		return addAndReturnType(Function_Aggregate.MIN, field);
	}

	@Override
	public final ALIAS_SHORT_RET min(ISupplierShort field) {
		return addAndReturnType(Function_Aggregate.MIN, field);
	}

	@Override
	public final ALIAS_INTEGER_RET min(ISupplierInteger field) {
		return addAndReturnType(Function_Aggregate.MIN, field);
	}

	@Override
	public final ALIAS_LONG_RET min(ISupplierLong field) {
		return addAndReturnType(Function_Aggregate.MIN, field);
	}

	@Override
	public final ALIAS_BIGINTEGER_RET min(ISupplierBigInteger field) {
		return addAndReturnType(Function_Aggregate.MIN, field);
	}

	@Override
	public final ALIAS_FLOAT_RET min(ISupplierFloat field) {
		return addAndReturnType(Function_Aggregate.MIN, field);
	}

	@Override
	public final ALIAS_DOUBLE_RET min(ISupplierDouble field) {
		return addAndReturnType(Function_Aggregate.MIN, field);
	}

	@Override
	public final ALIAS_BIGDECIMAL_RET min(ISupplierBigDecimal field) {
		return addAndReturnType(Function_Aggregate.MIN, field);
	}

	@Override
	public final ALIAS_DATE_RET min(ISupplierDate field) {
		return addAndReturnType(Function_Aggregate.MIN, field);
	}

	@Override
	public final ALIAS_SUM_LONG_RET sum(ISupplierByte field) {
		return addAndReturnType(Function_Aggregate.SUM, field);
	}

	@Override
	public final ALIAS_SUM_LONG_RET sum(ISupplierShort field) {
		return addAndReturnType(Function_Aggregate.SUM, field);
	}

	@Override
	public final ALIAS_SUM_LONG_RET sum(ISupplierInteger field) {
		return addAndReturnType(Function_Aggregate.SUM, field);
	}

	@Override
	public final ALIAS_BIGINTEGER_RET sum(ISupplierLong field) {
		return addAndReturnType(Function_Aggregate.SUM, field);
	}

	@Override
	public final ALIAS_BIGINTEGER_RET sum(ISupplierBigInteger field) {
		return addAndReturnType(Function_Aggregate.SUM, field);
	}

	@Override
	public final ALIAS_DOUBLE_RET sum(ISupplierFloat field) {
		return addAndReturnType(Function_Aggregate.SUM, field);
	}

	@Override
	public final ALIAS_DOUBLE_RET sum(ISupplierDouble field) {
		return addAndReturnType(Function_Aggregate.SUM, field);
	}

	@Override
	public final ALIAS_BIGDECIMAL_RET sum(ISupplierBigDecimal field) {
		return addAndReturnType(Function_Aggregate.SUM, field);
	}
}
