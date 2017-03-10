package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.function.Supplier;

abstract class Collector_ExpressionList<
		MODEL,
		RESULT,
		R extends Comparable<R>,

		OPERAND_RET extends ISharedFunction_After<MODEL, RESULT>,
		
		NAMED_RET extends ISharedFunction_After<MODEL, RESULT>,
		ALIAS_RET extends ISharedFunction_After<MODEL, RESULT>,
		
		NUMERIC_OPERAND_NEXT    extends ISharedFunction_Next<MODEL, RESULT, OPERAND_RET>,
		STRING_OPERAND_NEXT    extends ISharedFunction_Next<MODEL, RESULT, OPERAND_RET>,

		NAMED_SUM_LONG_RET  	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_COUNT_RET       extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,

		NAMED_SHORT_RET  	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_INTEGER_RET  	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_LONG_RET 	 	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_DOUBLE_RET   	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_BIGDECIMAL_RET  extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_STRING_RET  	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,


		ALIAS_SUM_LONG_RET  extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_COUNT_RET     extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		
		ALIAS_SHORT_RET  	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_INTEGER_RET  	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_LONG_RET 	 	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_DOUBLE_RET   	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_BIGDECIMAL_RET  extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_STRING_RET  extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>
	>

	extends Collector_ExpressionList_Base<MODEL, RESULT, R, OPERAND_RET> 

	//Implement both Numeric and String in this baseclass 
	implements 
	
			   ISharedOperands_Numeric_Named<MODEL, RESULT, R, OPERAND_RET, NUMERIC_OPERAND_NEXT>,
			   ISharedOperands_String_Named<MODEL, RESULT, OPERAND_RET, STRING_OPERAND_NEXT>, 

			   ISharedOperands_Numeric_Alias<MODEL, RESULT, R, OPERAND_RET, NUMERIC_OPERAND_NEXT>,
			   ISharedOperands_String_Alias<MODEL, RESULT, OPERAND_RET, STRING_OPERAND_NEXT>,
			   



	// Also implememnts all functions and just forwards them, so that we can always 
	// just instantiate an expression list wherever expressions are to be collected
			   
	// NOTE! We do not bother about types here since Java does not keep generic-types at runtime in any case
			 
			   ISharedFunctions_Arithmetic_Named<
				   MODEL,
				   RESULT,
	
				   NAMED_RET,
				   
				   NAMED_SHORT_RET,
				   NAMED_INTEGER_RET,
				   NAMED_LONG_RET,
				   NAMED_DOUBLE_RET,
				   NAMED_BIGDECIMAL_RET
				   >,
			   
			   ISharedFunctions_Aggregate_Named<NAMED_SUM_LONG_RET, NAMED_COUNT_RET, NAMED_SHORT_RET, NAMED_INTEGER_RET, NAMED_LONG_RET, NAMED_DOUBLE_RET, NAMED_BIGDECIMAL_RET>,
			   ISharedFunctions_String_Named<MODEL, RESULT, NAMED_RET, NAMED_STRING_RET>,
			   
			   ISharedFunctions_Arithmetic_Alias<
				   MODEL,
				   RESULT,
	
				   ALIAS_RET,
				   
				   ALIAS_SHORT_RET,
				   ALIAS_INTEGER_RET,
				   ALIAS_LONG_RET,
				   ALIAS_DOUBLE_RET,
				   ALIAS_BIGDECIMAL_RET
			   >,
			   ISharedFunctions_Aggregate_Alias<ALIAS_SUM_LONG_RET, ALIAS_COUNT_RET, ALIAS_SHORT_RET, ALIAS_INTEGER_RET, ALIAS_LONG_RET, ALIAS_DOUBLE_RET, ALIAS_BIGDECIMAL_RET>,
			   ISharedFunctions_String_Alias<MODEL, RESULT, ALIAS_RET, ALIAS_STRING_RET>
			   {

	Collector_ExpressionList() {
		
	}

	Collector_ExpressionList(Expression expression) {
		super(expression);
	}
	
	Collector_ExpressionList(Collector_ExpressionList<MODEL, RESULT, R, OPERAND_RET,
			?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
			?, ?, ?, ?, ?, ?, ?, ?, ?, ?
					> toCopy) {
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
	public final <T> NUMERIC_OPERAND_NEXT plus(IFunctionBigDecimal<T> getter) {
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
	public final <T> NUMERIC_OPERAND_NEXT plus(ISupplierShort getter) {
		addField(Operator.PLUS, getter);
		
		return (NUMERIC_OPERAND_NEXT)this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public final <T> NUMERIC_OPERAND_NEXT plus(ISupplierBigDecimal getter) {
		addField(Operator.PLUS, getter);
		
		return (NUMERIC_OPERAND_NEXT)this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public final NUMERIC_OPERAND_NEXT plusOf(ISharedSubOperandsFunction_Alias<MODEL, RESULT, BigDecimal> builder) {
		addSubNumeric(Operator.PLUS, builder);
		
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

	@Override
	@SuppressWarnings("unchecked")
	public final STRING_OPERAND_NEXT concat(ISupplierString getter) {
		addField(Operator.CONCAT, getter);
		
		return (STRING_OPERAND_NEXT)this;
	}
	
	
	ISharedFunction_Next<MODEL, RESULT, NAMED_RET> getNamedNoParamNext(ISharedFunction_Next<MODEL, RESULT, NAMED_RET> def) {
		return def;
	}
	
	ISharedFunction_Next<MODEL, RESULT, ALIAS_RET> getAliasNoParamNext(ISharedFunction_Next<MODEL, RESULT, ALIAS_RET> def) {
		return def;
	}

	
	private class NamedFunctions
	
		extends Collector_NestedFunctions_Named<
					MODEL, RESULT, NAMED_RET,
					
					NAMED_SUM_LONG_RET, NAMED_COUNT_RET, NAMED_SHORT_RET, NAMED_INTEGER_RET, NAMED_LONG_RET, NAMED_DOUBLE_RET, NAMED_BIGDECIMAL_RET, NAMED_STRING_RET,
					
					NUMERIC_OPERAND_NEXT, NUMERIC_OPERAND_NEXT, STRING_OPERAND_NEXT
					> 
	
		implements ISharedFunction_Next<MODEL, RESULT, NAMED_RET>
	{

		NamedFunctions(Collector_NestedFunctions_Named<MODEL, RESULT, NAMED_RET, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?> toCopy) {
			super(toCopy);
		}

		NamedFunctions(ISharedCollector_Functions_Callback_Named<MODEL, RESULT, NAMED_RET> func) {
			super(func);
		}

		@Override
		ISharedFunction_Next<MODEL, RESULT, NAMED_RET> getNamedNoParamNext() {
			return Collector_ExpressionList.this.getNamedNoParamNext(this);
		}
	}
	
	private NamedFunctions named;
	

	private class AliasFunctions 
	
		extends Collector_NestedFunctions_Alias<
					MODEL, RESULT, ALIAS_RET,
					
					ALIAS_SUM_LONG_RET, ALIAS_COUNT_RET, ALIAS_SHORT_RET, ALIAS_INTEGER_RET, ALIAS_LONG_RET, ALIAS_DOUBLE_RET, ALIAS_BIGDECIMAL_RET, ALIAS_STRING_RET
					> 
	
		implements ISharedFunction_Next<MODEL, RESULT, ALIAS_RET> {
		
			AliasFunctions(Collector_NestedFunctions_Alias<MODEL, RESULT, ALIAS_RET, ?, ?, ?, ?, ?, ?, ?, ?> toCopy) {
				super(toCopy);
			}
		
			AliasFunctions(ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, ALIAS_RET> func) {
				super(func);
			}
		
			@Override
			ISharedFunction_Next<MODEL, RESULT, ALIAS_RET> getAliasNoParamNext() {
				return Collector_ExpressionList.this.getAliasNoParamNext(this);
			}

			@Override
			ISharedFunction_Next<MODEL, RESULT, ALIAS_RET> continueAfterAliasFunctions(Expression expression) {
				throw new UnsupportedOperationException("TODO");
			}
		}
		
	private AliasFunctions alias;
	
	/****************************************************************
	 * Named
	 ***************************************************************/
	
	final void addNoParam(FunctionBase function) {
		throw new UnsupportedOperationException("TODO - must figure whether named or alias, may have to add to temporary list");
	}
	
	//*************** Arithmetic forwarding functions ***************

	private NamedFunctions assureNamedFunctions() {
		if (this.named == null) {
			
			final ISharedCollector_Functions_Callback_Named<MODEL, RESULT, NAMED_RET> callback
			
				= new ISharedCollector_Functions_Callback_Named<MODEL, RESULT, NAMED_RET>() {

					@Override
					public ISharedFunction_Next<MODEL, RESULT, NAMED_RET> onComparable(Expression expression) {
						return addNamedFunctionResult(expression);
					}

					@Override
					public ISharedFunction_Next<MODEL, RESULT, NAMED_RET> onString(Expression expression) {
						return addNamedFunctionResult(expression);
					}
			};
			
			this.named = new NamedFunctions(callback);
		}

		return this.named;
	}
	
	ISharedFunction_Next<MODEL, RESULT, NAMED_RET> addNamedFunctionResult(Expression expression) {
		addExpression(expression);
		
		// must clear named-functionns as to collect new ones after operand
		this.named = null;

		return (ISharedFunction_Next)this;
	}
	

	private AliasFunctions assureAliasFunctions() {
		if (this.alias == null) {
			
			final ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, ALIAS_RET> callback
			
				= new ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, ALIAS_RET>() {
					
					

					@Override
					public ISharedFunction_Next<MODEL, RESULT, ALIAS_RET> onComparable(CollectedFunctions functions,
							Supplier<? extends Comparable<?>> getter) {
						throw new UnsupportedOperationException();
					}

					@Override
					public ISharedFunction_Next<MODEL, RESULT, ALIAS_RET> onString(CollectedFunctions functions,
							ISupplierString getter) {
						throw new UnsupportedOperationException();
					}

			};
			
			this.alias = new AliasFunctions(callback);
		}

		return this.alias;
	}
	
	
	@Override
	public final <T> NAMED_SHORT_RET abs(IFunctionShort<T> getter) {
		return assureNamedFunctions().abs(getter);
	}

	@Override
	public final <T> NAMED_INTEGER_RET abs(IFunctionInteger<T> getter) {
		return assureNamedFunctions().abs(getter);
	}

	@Override
	public final <T> NAMED_LONG_RET abs(IFunctionLong<T> getter) {
		return assureNamedFunctions().abs(getter);
	}

	@Override
	public final <T> NAMED_BIGDECIMAL_RET abs(IFunctionBigDecimal<T> getter) {
		return assureNamedFunctions().abs(getter);
	}

	@Override
	public final <T> NAMED_SHORT_RET absOfShort(ISharedSubOperandsFunction_Short_Named<MODEL, RESULT> sub) {
		return assureNamedFunctions().absOfShort(sub);
	}

	@Override
	public final <T> NAMED_INTEGER_RET absOfInteger(ISharedSubOperandsFunction_Integer_Named<MODEL, RESULT> sub) {
		return assureNamedFunctions().absOfInteger(sub);
	}

	@Override
	public final <T> NAMED_LONG_RET absOfLong(ISharedSubOperandsFunction_Long_Named<MODEL, RESULT> sub) {
		return assureNamedFunctions().absOfLong(sub);
	}

	@Override
	public final <T> NAMED_BIGDECIMAL_RET absOfDecimal(ISharedSubOperandsFunction_BigDecimal_Named<MODEL, RESULT> sub) {
		return assureNamedFunctions().absOfDecimal(sub);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET sqrt(IFunctionShort<T> getter) {
		return assureNamedFunctions().sqrt(getter);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET sqrt(IFunctionInteger<T> getter) {
		return assureNamedFunctions().sqrt(getter);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET sqrt(IFunctionLong<T> getter) {
		return assureNamedFunctions().sqrt(getter);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET sqrt(IFunctionBigDecimal<T> getter) {
		return assureNamedFunctions().sqrt(getter);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET sqrtOf(ISharedSubOperandsFunction_Double_Named<MODEL, RESULT> sub) {
		return assureNamedFunctions().sqrtOf(sub);
	}

	
	//*************** Aggregate forwarding functions ***************
	
	@Override
	public final <T> NAMED_DOUBLE_RET avg(IFunctionShort<T> field) {
		return assureNamedFunctions().avg(field);
	}


	@Override
	public final <T> NAMED_DOUBLE_RET avg(IFunctionInteger<T> field) {
		return assureNamedFunctions().avg(field);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET avg(IFunctionLong<T> field) {
		return assureNamedFunctions().avg(field);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET avg(IFunctionBigDecimal<T> field) {
		return assureNamedFunctions().avg(field);
	}

	@Override
	public final <T> NAMED_COUNT_RET count(IFunctionShort<T> field) {
		return assureNamedFunctions().count(field);
	}

	@Override
	public final <T> NAMED_COUNT_RET count(IFunctionInteger<T> field) {
		return assureNamedFunctions().count(field);
	}

	@Override
	public final <T> NAMED_COUNT_RET count(IFunctionLong<T> field) {
		return assureNamedFunctions().count(field);
	}

	@Override
	public final <T> NAMED_COUNT_RET count(IFunctionBigDecimal<T> field) {
		return assureNamedFunctions().count(field);
	}

	@Override
	public final <T> NAMED_SHORT_RET max(IFunctionShort<T> field) {
		return assureNamedFunctions().max(field);
	}

	@Override
	public final <T> NAMED_INTEGER_RET max(IFunctionInteger<T> field) {
		return assureNamedFunctions().max(field);
	}

	@Override
	public final <T> NAMED_LONG_RET max(IFunctionLong<T> field) {
		return assureNamedFunctions().max(field);
	}

	@Override
	public final <T> NAMED_BIGDECIMAL_RET max(IFunctionBigDecimal<T> field) {
		return assureNamedFunctions().max(field);
	}

	@Override
	public final <T> NAMED_SHORT_RET min(IFunctionShort<T> field) {
		return assureNamedFunctions().min(field);
	}

	@Override
	public final <T> NAMED_INTEGER_RET min(IFunctionInteger<T> field) {
		return assureNamedFunctions().min(field);
	}

	@Override
	public final <T> NAMED_LONG_RET min(IFunctionLong<T> field) {
		return assureNamedFunctions().min(field);
	}

	@Override
	public final <T> NAMED_BIGDECIMAL_RET min(IFunctionBigDecimal<T> field) {
		return assureNamedFunctions().min(field);
	}

	@Override
	public final <T> NAMED_SUM_LONG_RET sum(IFunctionShort<T> field) {
		return assureNamedFunctions().sum(field);
	}

	@Override
	public final <T> NAMED_SUM_LONG_RET sum(IFunctionInteger<T> field) {
		return assureNamedFunctions().sum(field);
	}

	@Override
	public final <T> NAMED_SUM_LONG_RET sum(IFunctionLong<T> field) {
		return assureNamedFunctions().sum(field);
	}

	@Override
	public final <T> NAMED_BIGDECIMAL_RET sum(IFunctionBigDecimal<T> field) {
		return assureNamedFunctions().sum(field);
	}

	@Override
	public final <T> NAMED_STRING_RET lower(StringFunction<T> getter) {
		return assureNamedFunctions().lower(getter);
	}

	@Override
	public final <T> NAMED_STRING_RET upper(StringFunction<T> getter) {
		return assureNamedFunctions().upper(getter);
	}

	@Override
	public final <T> NAMED_STRING_RET trim(StringFunction<T> getter) {
		return assureNamedFunctions().trim(getter);
	}

		
	/****************************************************************
	 * Alias
	 ***************************************************************/

	//*************** Arithmetic forwarding functions ***************

	@Override
	public final <T> ALIAS_SHORT_RET abs(ISupplierShort getter) {
		return assureAliasFunctions().abs(getter);
	}


	@Override
	public final <T> ALIAS_INTEGER_RET abs(ISupplierInteger getter) {
		return assureAliasFunctions().abs(getter);
	}

	@Override
	public final <T> ALIAS_LONG_RET abs(ISupplierLong getter) {
		return assureAliasFunctions().abs(getter);
	}

	@Override
	public final <T> ALIAS_DOUBLE_RET abs(ISupplierDouble getter) {
		return assureAliasFunctions().abs(getter);
	}

	@Override
	public final <T> ALIAS_BIGDECIMAL_RET abs(ISupplierBigDecimal getter) {
		return assureAliasFunctions().abs(getter);
	}

	@Override
	public final <T> ALIAS_DOUBLE_RET sqrt(ISupplierShort getter) {
		return assureAliasFunctions().sqrt(getter);
	}

	@Override
	public final <T> ALIAS_DOUBLE_RET sqrt(ISupplierInteger getter) {
		return assureAliasFunctions().sqrt(getter);
	}

	@Override
	public final <T> ALIAS_DOUBLE_RET sqrt(ISupplierLong getter) {
		return assureAliasFunctions().sqrt(getter);
	}

	@Override
	public final <T> ALIAS_DOUBLE_RET sqrt(ISupplierDouble getter) {
		return assureAliasFunctions().sqrt(getter);
	}

	@Override
	public final <T> ALIAS_DOUBLE_RET sqrt(ISupplierBigDecimal getter) {
		return assureAliasFunctions().sqrt(getter);
	}

	
	//*************** Aggregate forwarding functions ***************

	@Override
	public final ALIAS_DOUBLE_RET avg(ISupplierShort field) {
		return assureAliasFunctions().avg(field);
	}

	@Override
	public final ALIAS_DOUBLE_RET avg(ISupplierInteger field) {
		return assureAliasFunctions().avg(field);
	}

	@Override
	public final ALIAS_DOUBLE_RET avg(ISupplierLong field) {
		return assureAliasFunctions().avg(field);
	}

	@Override
	public final ALIAS_DOUBLE_RET avg(ISupplierBigDecimal field) {
		return assureAliasFunctions().avg(field);
	}

	@Override
	public final ALIAS_COUNT_RET count(ISupplierShort field) {
		return assureAliasFunctions().count(field);
	}

	@Override
	public final ALIAS_COUNT_RET count(ISupplierInteger field) {
		return assureAliasFunctions().count(field);
	}

	@Override
	public final ALIAS_COUNT_RET count(ISupplierLong field) {
		return assureAliasFunctions().count(field);
	}

	@Override
	public final ALIAS_COUNT_RET count(ISupplierBigDecimal field) {
		return assureAliasFunctions().count(field);
	}

	@Override
	public final ALIAS_SHORT_RET max(ISupplierShort field) {
		return assureAliasFunctions().max(field);
	}

	@Override
	public final ALIAS_INTEGER_RET max(ISupplierInteger field) {
		return assureAliasFunctions().max(field);
	}

	@Override
	public final ALIAS_LONG_RET max(ISupplierLong field) {
		return assureAliasFunctions().max(field);
	}

	@Override
	public final ALIAS_BIGDECIMAL_RET max(ISupplierBigDecimal field) {
		return assureAliasFunctions().max(field);
	}

	@Override
	public final ALIAS_SHORT_RET min(ISupplierShort field) {
		return assureAliasFunctions().min(field);
	}

	@Override
	public final ALIAS_INTEGER_RET min(ISupplierInteger field) {
		return assureAliasFunctions().min(field);
	}

	@Override
	public final ALIAS_LONG_RET min(ISupplierLong field) {
		return assureAliasFunctions().min(field);
	}

	@Override
	public final ALIAS_BIGDECIMAL_RET min(ISupplierBigDecimal field) {
		return assureAliasFunctions().min(field);
	}

	@Override
	public final ALIAS_SUM_LONG_RET sum(ISupplierShort field) {
		return assureAliasFunctions().sum(field);
	}

	@Override
	public final ALIAS_SUM_LONG_RET sum(ISupplierInteger field) {
		return assureAliasFunctions().sum(field);
	}

	@Override
	public final ALIAS_SUM_LONG_RET sum(ISupplierLong field) {
		return assureAliasFunctions().sum(field);
	}

	@Override
	public final ALIAS_BIGDECIMAL_RET sum(ISupplierBigDecimal field) {
		return assureAliasFunctions().sum(field);
	}

	//*************** String forwarding functions ***************
	
	@Override
	public final <T> ALIAS_STRING_RET lower(ISupplierString getter) {
		return assureAliasFunctions().lower(getter);
	}

	@Override
	public final <T> ALIAS_STRING_RET upper(ISupplierString getter) {
		return assureAliasFunctions().upper(getter);
	}

	@Override
	public final <T> ALIAS_STRING_RET trim(ISupplierString getter) {
		return assureAliasFunctions().trim(getter);
	}
}
