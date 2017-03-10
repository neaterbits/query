package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

abstract class Collector_ExpressionList<
		MODEL,
		RESULT,
		R extends Comparable<R>,
		
		RET extends ISharedFunction_After<MODEL, RESULT>,
		
		NUMERIC_OPERAND_NEXT    extends ISharedFunction_Next<MODEL, RESULT, RET>,
		STRING_OPERAND_NEXT    extends ISharedFunction_Next<MODEL, RESULT, RET>,

		SUM_LONG_RET  	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		COUNT_RET       extends ISharedFunction_Next<MODEL, RESULT, RET>,

		SHORT_RET  		extends ISharedFunction_Next<MODEL, RESULT, RET>,
		INTEGER_RET  	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		LONG_RET 	 	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		DOUBLE_RET   	extends ISharedFunction_Next<MODEL, RESULT, RET>,
		BIGDECIMAL_RET  extends ISharedFunction_Next<MODEL, RESULT, RET>,
		STRING_RET  extends ISharedFunction_Next<MODEL, RESULT, RET>
	>

	extends Collector_ExpressionList_Base<MODEL, RESULT, R, RET> 

	//Implement both Numeric and String in this baseclass 
	implements ISharedOperands_Numeric_Named<MODEL, RESULT, R, RET, NUMERIC_OPERAND_NEXT>,
			   ISharedOperands_String_Named<MODEL, RESULT, RET, STRING_OPERAND_NEXT>, 



	// Also implememnts all functions and just forwards them, so that we can always 
	// just instantiate an expression list wherever expressions are to be collected
			   
	// NOTE! We do not bother about types here since Java does not keep generic-types at runtime in any case
			 
			   ISharedFunctions_Arithmetic_Named<
				   MODEL,
				   RESULT,
	
				   RET,
				   
				   SHORT_RET,
				   INTEGER_RET,
				   LONG_RET,
				   DOUBLE_RET,
				   BIGDECIMAL_RET
				   >,
			   
			   ISharedFunctions_Aggregate_Named<SUM_LONG_RET, COUNT_RET, SHORT_RET, INTEGER_RET, LONG_RET, DOUBLE_RET, BIGDECIMAL_RET> {

	Collector_ExpressionList() {
		
	}

	Collector_ExpressionList(Expression expression) {
		super(expression);
	}
	
	Collector_ExpressionList(Collector_ExpressionList<MODEL, RESULT, R, RET, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> toCopy) {
		super(toCopy);
	}
	
	@SuppressWarnings("unchecked")
	final <CLAUSE> CLAUSE absNoParam() {
		return (CLAUSE)assureNamedFunctions().abs();
	}

	@SuppressWarnings("unchecked")
	final <CLAUSE> CLAUSE sqrtNoParam() {
		return (CLAUSE)assureNamedFunctions().sqrt();
	}

	@Override
	@SuppressWarnings("unchecked")
	public final <T> NUMERIC_OPERAND_NEXT plus(IFunctionShort<T> getter) {
		addField(Operator.PLUS, getter);
		
		return (NUMERIC_OPERAND_NEXT)this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public final <T> NUMERIC_OPERAND_NEXT plus(short value) {
		addValue(Operator.PLUS, value);
		
		return (NUMERIC_OPERAND_NEXT)this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> NUMERIC_OPERAND_NEXT plus(IFunctionBigDecimal<T> getter) {
		addField(Operator.PLUS, getter);
		
		return (NUMERIC_OPERAND_NEXT)this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public final NUMERIC_OPERAND_NEXT plusOf(ISharedSubOperandsFunction_Named<MODEL, RESULT, BigDecimal> builder) {

		addSubNumeric(Operator.PLUS, builder);

		return (NUMERIC_OPERAND_NEXT)this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public final NUMERIC_OPERAND_NEXT plus(BigDecimal value) {
		
		addValue(Operator.PLUS, value);

		return (NUMERIC_OPERAND_NEXT)this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public final <T> STRING_OPERAND_NEXT concat(StringFunction<T> getter) {
		addField(Operator.CONCAT, getter);

		return (STRING_OPERAND_NEXT)this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public final <T> STRING_OPERAND_NEXT concat(String value) {
		addValue(Operator.PLUS, value);
		
		return (STRING_OPERAND_NEXT)this;
	}
	
	//<A extends ISharedFunction_After<MODEL, RESULT>>
			ISharedFunction_Next<MODEL, RESULT, RET> getNamedNoParamNext(ISharedFunction_Next<MODEL, RESULT, RET> def) {
		return def;
	}
	
	//<A extends ISharedFunction_After<MODEL, RESULT>>
			ISharedFunction_Next<MODEL, RESULT, RET> getAliasNoParamNext(ISharedFunction_Next<MODEL, RESULT, RET> def) {

		return def;
	}

	
	private class NamedFunctions
	
		extends Collector_NestedFunctions_Named<MODEL, RESULT, RET, SUM_LONG_RET, COUNT_RET, SHORT_RET, INTEGER_RET, LONG_RET, DOUBLE_RET, BIGDECIMAL_RET, STRING_RET, NUMERIC_OPERAND_NEXT, NUMERIC_OPERAND_NEXT, NUMERIC_OPERAND_NEXT> 
	
		implements ISharedFunction_Next<MODEL, RESULT, RET>
	{

		NamedFunctions(Collector_NestedFunctions_Named<MODEL, RESULT, RET, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> toCopy) {
			super(toCopy);
		}

		NamedFunctions(ISharedCollector_Functions_Callback_Named<MODEL, RESULT, RET> func) {
			super(func);
		}

		@Override
		ISharedFunction_Next<MODEL, RESULT, RET> getNamedNoParamNext() {
			return Collector_ExpressionList.this.getNamedNoParamNext(this);
		}
	}
	
	private NamedFunctions named;
	
	
	/****************************************************************
	 * Named
	 ***************************************************************/
	
	//*************** Arithmetic forwarding functions ***************

	private NamedFunctions assureNamedFunctions() {
		if (this.named == null) {
			
			final ISharedCollector_Functions_Callback_Named<MODEL, RESULT, RET> callback
			
				= new ISharedCollector_Functions_Callback_Named<MODEL, RESULT, RET>() {

					@Override
					public ISharedFunction_Next<MODEL, RESULT, RET> onComparable(Expression expression) {
						throw new UnsupportedOperationException();
					}

					@Override
					public ISharedFunction_Next<MODEL, RESULT, RET> onString(Expression expression) {
						throw new UnsupportedOperationException();
					}
			};
			
			this.named = new NamedFunctions(callback);
		}

		return this.named;
	}
	
	
	@Override
	public final <T> SHORT_RET abs(IFunctionShort<T> getter) {
		return assureNamedFunctions().abs(getter);
	}

	@Override
	public final <T> INTEGER_RET abs(IFunctionInteger<T> getter) {
		return assureNamedFunctions().abs(getter);
	}

	@Override
	public final <T> LONG_RET abs(IFunctionLong<T> getter) {
		return assureNamedFunctions().abs(getter);
	}

	@Override
	public final <T> BIGDECIMAL_RET abs(IFunctionBigDecimal<T> getter) {
		return assureNamedFunctions().abs(getter);
	}

	@Override
	public final <T> SHORT_RET absOfShort(ISharedSubOperandsFunction_Short_Named<MODEL, RESULT> sub) {
		return assureNamedFunctions().absOfShort(sub);
	}

	@Override
	public final <T> INTEGER_RET absOfInteger(ISharedSubOperandsFunction_Integer_Named<MODEL, RESULT> sub) {
		return assureNamedFunctions().absOfInteger(sub);
	}

	@Override
	public final <T> LONG_RET absOfLong(ISharedSubOperandsFunction_Long_Named<MODEL, RESULT> sub) {
		return assureNamedFunctions().absOfLong(sub);
	}

	@Override
	public final <T> BIGDECIMAL_RET absOfDecimal(ISharedSubOperandsFunction_BigDecimal_Named<MODEL, RESULT> sub) {
		return assureNamedFunctions().absOfDecimal(sub);
	}

	@Override
	public final <T> DOUBLE_RET sqrt(IFunctionShort<T> getter) {
		return assureNamedFunctions().sqrt(getter);
	}

	@Override
	public final <T> DOUBLE_RET sqrt(IFunctionInteger<T> getter) {
		return assureNamedFunctions().sqrt(getter);
	}

	@Override
	public final <T> DOUBLE_RET sqrt(IFunctionLong<T> getter) {
		return assureNamedFunctions().sqrt(getter);
	}

	@Override
	public final <T> DOUBLE_RET sqrt(IFunctionBigDecimal<T> getter) {
		return assureNamedFunctions().sqrt(getter);
	}

	@Override
	public final <T> DOUBLE_RET sqrtOf(ISharedSubOperandsFunction_Double_Named<MODEL, RESULT> sub) {
		return assureNamedFunctions().sqrtOf(sub);
	}

	
	//*************** Aggregate forwarding functions ***************

	
	
	@Override
	public final <T> DOUBLE_RET avg(IFunctionShort<T> field) {
		return assureNamedFunctions().avg(field);
	}

	@Override
	public final <T> DOUBLE_RET avg(IFunctionInteger<T> field) {
		return assureNamedFunctions().avg(field);
	}

	@Override
	public final <T> DOUBLE_RET avg(IFunctionLong<T> field) {
		return assureNamedFunctions().avg(field);
	}

	@Override
	public final <T> DOUBLE_RET avg(IFunctionBigDecimal<T> field) {
		return assureNamedFunctions().avg(field);
	}

	@Override
	public final <T> COUNT_RET count(IFunctionShort<T> field) {
		return assureNamedFunctions().count(field);
	}

	@Override
	public final <T> COUNT_RET count(IFunctionInteger<T> field) {
		return assureNamedFunctions().count(field);
	}

	@Override
	public final <T> COUNT_RET count(IFunctionLong<T> field) {
		return assureNamedFunctions().count(field);
	}

	@Override
	public final <T> COUNT_RET count(IFunctionBigDecimal<T> field) {
		return assureNamedFunctions().count(field);
	}

	@Override
	public final <T> SHORT_RET max(IFunctionShort<T> field) {
		return assureNamedFunctions().max(field);
	}

	@Override
	public final <T> INTEGER_RET max(IFunctionInteger<T> field) {
		return assureNamedFunctions().max(field);
	}

	@Override
	public final <T> LONG_RET max(IFunctionLong<T> field) {
		return assureNamedFunctions().max(field);
	}

	@Override
	public final <T> BIGDECIMAL_RET max(IFunctionBigDecimal<T> field) {
		return assureNamedFunctions().max(field);
	}

	@Override
	public final <T> SHORT_RET min(IFunctionShort<T> field) {
		return assureNamedFunctions().min(field);
	}

	@Override
	public final <T> INTEGER_RET min(IFunctionInteger<T> field) {
		return assureNamedFunctions().min(field);
	}

	@Override
	public final <T> LONG_RET min(IFunctionLong<T> field) {
		return assureNamedFunctions().min(field);
	}

	@Override
	public final <T> BIGDECIMAL_RET min(IFunctionBigDecimal<T> field) {
		return assureNamedFunctions().min(field);
	}

	@Override
	public final <T> SUM_LONG_RET sum(IFunctionShort<T> field) {
		return assureNamedFunctions().sum(field);
	}

	@Override
	public final <T> SUM_LONG_RET sum(IFunctionInteger<T> field) {
		return assureNamedFunctions().sum(field);
	}

	@Override
	public final <T> SUM_LONG_RET sum(IFunctionLong<T> field) {
		return assureNamedFunctions().sum(field);
	}

	@Override
	public final <T> BIGDECIMAL_RET sum(IFunctionBigDecimal<T> field) {
		return assureNamedFunctions().sum(field);
	}
	
	/****************************************************************
	 * Alias
	 ***************************************************************/

	
}
