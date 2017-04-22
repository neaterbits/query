package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

abstract class Collector_ExpressionList<
		MODEL,
		RESULT,
		R, // extends Comparable<R>,

		OPERAND_RET extends ISharedFunction_After<MODEL, RESULT>,
		
		NAMED_RET extends ISharedFunction_After<MODEL, RESULT>,
		ALIAS_RET extends ISharedFunction_After<MODEL, RESULT>,
		
		NUMERIC_OPERAND_NEXT    extends ISharedFunction_Next<MODEL, RESULT, OPERAND_RET>,
		STRING_OPERAND_NEXT    extends ISharedFunction_Next<MODEL, RESULT, OPERAND_RET>,

		NAMED_SUM_LONG_RET  	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_COUNT_RET       extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,

		NAMED_BYTE_RET  	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_SHORT_RET  	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_INTEGER_RET  	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_LONG_RET 	 	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_BIGINTEGER_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_FLOAT_RET 	 extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_DOUBLE_RET   	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_BIGDECIMAL_RET  extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_STRING_RET  	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_DATE_RET  	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_CALENDAR_RET  extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_SQLDATE_RET  	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_SQLTIME_RET  	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_SQLTIMESTAMP_RET  extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,


		ALIAS_SUM_LONG_RET  extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_COUNT_RET     extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		
		ALIAS_BYTE_RET  	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_SHORT_RET  	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_INTEGER_RET  	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_LONG_RET 	 	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_BIGINTEGER_RET 	 	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_FLOAT_RET 	 	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_DOUBLE_RET   	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_BIGDECIMAL_RET  extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_STRING_RET  	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_DATE_RET  	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_CALENDAR_RET  extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_SQLDATE_RET  	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_SQLTIME_RET  	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_SQLTIMESTAMP_RET  extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>
	>

	extends Collector_ExpressionList_Base<MODEL, RESULT, R, OPERAND_RET> 

	//Implement both Numeric and String in this baseclass 
	implements 
	
			   ISharedOperands_Numeric_Named<MODEL, RESULT, OPERAND_RET, NUMERIC_OPERAND_NEXT>,
			   ISharedOperands_String_Named<MODEL, RESULT, OPERAND_RET, STRING_OPERAND_NEXT>, 

			   ISharedOperands_Numeric_Alias<MODEL, RESULT, OPERAND_RET, NUMERIC_OPERAND_NEXT>,
			   ISharedOperands_String_Alias<MODEL, RESULT, OPERAND_RET, STRING_OPERAND_NEXT>,
			   



	// Also implements all functions and just forwards them, so that we can always 
	// just instantiate an expression list wherever expressions are to be collected
			   
	// NOTE! We do not bother about types here since Java does not keep generic-types at runtime in any case
			 
			   ISharedFunctions_Arithmetic_Named<
				   MODEL,
				   RESULT,
	
				   NAMED_RET,
				   
				   NAMED_BYTE_RET,
				   NAMED_SHORT_RET,
				   NAMED_INTEGER_RET,
				   NAMED_LONG_RET,
				   NAMED_BIGINTEGER_RET,
				   NAMED_FLOAT_RET,
				   NAMED_DOUBLE_RET,
				   NAMED_BIGDECIMAL_RET
				   >,
			   
			   ISharedFunctions_Aggregate_Named<NAMED_SUM_LONG_RET, NAMED_COUNT_RET, NAMED_BYTE_RET, NAMED_SHORT_RET, NAMED_INTEGER_RET, NAMED_LONG_RET, NAMED_BIGINTEGER_RET, NAMED_FLOAT_RET, NAMED_DOUBLE_RET, NAMED_BIGDECIMAL_RET, NAMED_DATE_RET>,
			   ISharedFunctions_String_Named<MODEL, RESULT, NAMED_RET, NAMED_STRING_RET>,
			   
			   ISharedFunctions_Arithmetic_Alias<
				   MODEL,
				   RESULT,
	
				   ALIAS_RET,
				   
				   ALIAS_BYTE_RET,
				   ALIAS_SHORT_RET,
				   ALIAS_INTEGER_RET,
				   ALIAS_LONG_RET,
				   ALIAS_BIGINTEGER_RET,
				   ALIAS_FLOAT_RET,
				   ALIAS_DOUBLE_RET,
				   ALIAS_BIGDECIMAL_RET
			   >,
			   ISharedFunctions_Aggregate_Alias<ALIAS_SUM_LONG_RET, ALIAS_COUNT_RET, ALIAS_BYTE_RET, ALIAS_SHORT_RET, ALIAS_INTEGER_RET, ALIAS_LONG_RET, ALIAS_BIGINTEGER_RET, ALIAS_FLOAT_RET, ALIAS_DOUBLE_RET, ALIAS_BIGDECIMAL_RET, ALIAS_DATE_RET>,
			   ISharedFunctions_String_Alias<MODEL, RESULT, ALIAS_RET, ALIAS_STRING_RET> {

	private EFieldAccessType fieldAccessType;
	private List<FunctionBase> undecidedFunctions;
					
				   
    Collector_ExpressionList() {
		
	}

	Collector_ExpressionList(Expression expression, String fromSub) {
		super(expression);
	}
	
	Collector_ExpressionList(Expression expression, EFieldAccessType fieldAccessType) {
		super(expression);
		
		if (fieldAccessType == null) {
			throw new IllegalArgumentException("fieldAccessType == null");
		}
		
		this.fieldAccessType = fieldAccessType;
	}
	
	Collector_ExpressionList(Collector_ExpressionList<MODEL, RESULT, R, OPERAND_RET,
			?, ?,
			?, ?,
			?, ?,
		    ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
			?, ?,
			?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
					> toCopy) {
		super(toCopy);
		
		this.fieldAccessType = toCopy.fieldAccessType;
		
		this.undecidedFunctions = toCopy.undecidedFunctions == null
						? null
						: new ArrayList<>(toCopy.undecidedFunctions);
	}
	
	
	private void setAccessType(EFieldAccessType accessType) {
		
		if (accessType == null) {
			throw new IllegalArgumentException("accessType == null");
		}

		if (this.fieldAccessType != null) {
			
		    if (this.fieldAccessType != accessType) {
		    	throw new IllegalStateException("access type mismatch");
		    }
		    
		    
		}
		else {
			this.fieldAccessType = accessType;
			
			// Check if added functions while undecided, and re-add them now
			
			if (undecidedFunctions != null) {
				for (FunctionBase function : undecidedFunctions) {
					addNoParam(function);
				}
				
				// reset to null as no longer required
				this.undecidedFunctions = null;
			}
		}
	}
	
	private void setNamed() {
		setAccessType(EFieldAccessType.NAMED);
	}

	private void setAliased() {
		setAccessType(EFieldAccessType.NAMED);
	}
	
	final EFieldAccessType getFieldAccessType() {
		return fieldAccessType;
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
	public final <T> STRING_OPERAND_NEXT concat(IFunctionString<T> getter) {
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
	

	abstract NamedFunctions createNamedFunctions(ISharedCollector_Functions_Callback<MODEL, RESULT, NAMED_RET> func);

	protected class NamedFunctions<
	
		NO_PARAM_ARITHMETIC_SAME_TYPE_RET,
		NO_PARAM_ARITHMETIC_DOUBLE_RET,
		
		NO_PARAM_STRING_RET>
	
	
		extends Collector_NestedFunctions_Named<
					MODEL, RESULT, NAMED_RET,
					
					NAMED_SUM_LONG_RET, NAMED_COUNT_RET, NAMED_BYTE_RET, NAMED_SHORT_RET, NAMED_INTEGER_RET, NAMED_LONG_RET, NAMED_BIGINTEGER_RET, NAMED_FLOAT_RET, NAMED_DOUBLE_RET, NAMED_BIGDECIMAL_RET, NAMED_STRING_RET, NAMED_DATE_RET, NAMED_CALENDAR_RET, NAMED_SQLDATE_RET, NAMED_SQLTIME_RET, NAMED_SQLTIMESTAMP_RET,
					
					NO_PARAM_ARITHMETIC_SAME_TYPE_RET,
					NO_PARAM_ARITHMETIC_DOUBLE_RET,
					
					NO_PARAM_STRING_RET
					> 
	
		implements ISharedFunction_Next<MODEL, RESULT, NAMED_RET>
	{


		NamedFunctions(ISharedCollector_Functions_Callback<MODEL, RESULT, NAMED_RET> func) {
			super(func);
		}

		@Override
		ISharedFunction_Next<MODEL, RESULT, NAMED_RET> getNamedNoParamNext() {
			return this;
		}
	}
	
	private NamedFunctions named;
	

	abstract AliasFunctions createAliasFunctions(ISharedCollector_Functions_Callback<MODEL, RESULT, ALIAS_RET> func);
	
	protected class AliasFunctions<
		NO_PARAM_ARITHMETIC_SAME_TYPE_RET,
		NO_PARAM_ARITHMETIC_DOUBLE_RET,
		
		NO_PARAM_STRING_RET>
	
		extends Collector_NestedFunctions_Alias<
					MODEL, RESULT, ALIAS_RET,
					
					ALIAS_SUM_LONG_RET, ALIAS_COUNT_RET, ALIAS_BYTE_RET, ALIAS_SHORT_RET, ALIAS_INTEGER_RET, ALIAS_LONG_RET, ALIAS_BIGINTEGER_RET, ALIAS_FLOAT_RET, ALIAS_DOUBLE_RET, ALIAS_BIGDECIMAL_RET, ALIAS_STRING_RET, ALIAS_DATE_RET, ALIAS_CALENDAR_RET, ALIAS_SQLDATE_RET, ALIAS_SQLTIME_RET, ALIAS_SQLTIMESTAMP_RET,
					
					NO_PARAM_ARITHMETIC_SAME_TYPE_RET,
					NO_PARAM_ARITHMETIC_DOUBLE_RET,
					
					NO_PARAM_STRING_RET
					> 
	
		implements ISharedFunction_Next<MODEL, RESULT, ALIAS_RET> {
		
			protected AliasFunctions(Collector_NestedFunctions_Alias<
								MODEL, RESULT, ALIAS_RET,
								?, ?,
								?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
								?, ?, ?> toCopy) {
				super(toCopy);
			}
		
			protected AliasFunctions(ISharedCollector_Functions_Callback<MODEL, RESULT, ALIAS_RET> func) {
				super(func);
			}
		
			@Override
			ISharedFunction_Next<MODEL, RESULT, ALIAS_RET> getAliasNoParamNext() {
				return Collector_ExpressionList.this.getAliasNoParamNext(this);
			}
		}
		
	private AliasFunctions alias;
	
	/****************************************************************
	 * Named
	 ***************************************************************/
	
	final void addNoParam(FunctionBase function) {
		
		if (function == null) {
			throw new IllegalArgumentException("function == null");
		}

		if (fieldAccessType == null) {
			if (undecidedFunctions == null) {
				undecidedFunctions = new ArrayList<>();
			}
			
			undecidedFunctions.add(function);
		}
		else {
			// Already know access type
			switch (fieldAccessType) {
			case NAMED:
				assureNamedFunctions().addNoParam(function);
				break;
				
			case ALIAS:
				assureAliasFunctions().addNoParam(function);
				break;
				
			default:
				throw new UnsupportedOperationException("Unknown field access type: " + fieldAccessType);
			}
		}
	}
	
	//*************** Arithmetic forwarding functions ***************

	private NamedFunctions<?, ?, ?> assureNamedFunctions() {
		
		
		setNamed();
		
		if (this.named == null) {
			
			final ISharedCollector_Functions_Callback<MODEL, RESULT, NAMED_RET> callback
			
				= new ISharedCollector_Functions_Callback<MODEL, RESULT, NAMED_RET>() {

					@Override
					public ISharedFunction_Next<MODEL, RESULT, NAMED_RET> onComparable(Expression expression) {
						return addNamedFunctionResult(expression);
					}

					@Override
					public ISharedFunction_Next<MODEL, RESULT, NAMED_RET> onString(Expression expression) {
						return addNamedFunctionResult(expression);
					}
			};
			
			this.named = createNamedFunctions(callback);
		}

		@SuppressWarnings("unchecked")
		final NamedFunctions<?, ?, ?> ret = this.named;
		
		return ret;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	ISharedFunction_Next<MODEL, RESULT, NAMED_RET> addNamedFunctionResult(Expression expression) {
		addExpression(expression);
		
		// must clear named-functions as to collect new ones after operand
		this.named = null;

		return (ISharedFunction_Next)this;
	}
	

	private AliasFunctions<?, ?, ?> assureAliasFunctions() {
		
		setAliased();
		
		if (this.alias == null) {
			
			final ISharedCollector_Functions_Callback<MODEL, RESULT, ALIAS_RET> callback
			
				= new ISharedCollector_Functions_Callback<MODEL, RESULT, ALIAS_RET>() {

				@Override
				public ISharedFunction_Next<MODEL, RESULT, ALIAS_RET> onComparable(Expression expression) {
					throw new UnsupportedOperationException("TODO");
				}

				@Override
				public ISharedFunction_Next<MODEL, RESULT, ALIAS_RET> onString(Expression expression) {
					throw new UnsupportedOperationException("TODO");
				}
			};
			
			this.alias = createAliasFunctions(callback);
		}
		
		@SuppressWarnings("unchecked")
		final AliasFunctions<?, ?, ?> ret = this.alias;

		return ret;
	}
	
	
	@Override
	public final <T> NAMED_BYTE_RET abs(IFunctionByte<T> getter) {
		return assureNamedFunctions().abs(getter);
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
	public final <T> NAMED_BIGINTEGER_RET abs(IFunctionBigInteger<T> getter) {
		return assureNamedFunctions().abs(getter);
	}

	@Override
	public final <T> NAMED_FLOAT_RET abs(IFunctionFloat<T> getter) {
		return assureNamedFunctions().abs(getter);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET abs(IFunctionDouble<T> getter) {
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
	public final <T> NAMED_DOUBLE_RET sqrt(IFunctionByte<T> getter) {
		return assureNamedFunctions().sqrt(getter);
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
	public final <T> NAMED_DOUBLE_RET sqrt(IFunctionBigInteger<T> getter) {
		return assureNamedFunctions().sqrt(getter);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET sqrt(IFunctionFloat<T> getter) {
		return assureNamedFunctions().sqrt(getter);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET sqrt(IFunctionDouble<T> getter) {
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
	public final <T> NAMED_DOUBLE_RET avg(IFunctionByte<T> field) {
		return assureNamedFunctions().avg(field);
	}

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
	public final <T> NAMED_DOUBLE_RET avg(IFunctionBigInteger<T> field) {
		return assureNamedFunctions().avg(field);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET avg(IFunctionFloat<T> field) {
		return assureNamedFunctions().avg(field);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET avg(IFunctionDouble<T> field) {
		return assureNamedFunctions().avg(field);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET avg(IFunctionBigDecimal<T> field) {
		return assureNamedFunctions().avg(field);
	}

	@Override
	public final <T> NAMED_COUNT_RET count(IFunctionByte<T> field) {
		return assureNamedFunctions().count(field);
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
	public final <T> NAMED_COUNT_RET count(IFunctionBigInteger<T> field) {
		return assureNamedFunctions().count(field);
	}

	@Override
	public final <T> NAMED_COUNT_RET count(IFunctionFloat<T> field) {
		return assureNamedFunctions().count(field);
	}

	@Override
	public final <T> NAMED_COUNT_RET count(IFunctionDouble<T> field) {
		return assureNamedFunctions().count(field);
	}

	@Override
	public final <T> NAMED_COUNT_RET count(IFunctionBigDecimal<T> field) {
		return assureNamedFunctions().count(field);
	}

	@Override
	public final <T> NAMED_BYTE_RET max(IFunctionByte<T> field) {
		return assureNamedFunctions().max(field);
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
	public final <T> NAMED_BIGINTEGER_RET max(IFunctionBigInteger<T> field) {
		return assureNamedFunctions().max(field);
	}

	@Override
	public final <T> NAMED_FLOAT_RET max(IFunctionFloat<T> field) {
		return assureNamedFunctions().max(field);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET max(IFunctionDouble<T> field) {
		return assureNamedFunctions().max(field);
	}

	@Override
	public final <T> NAMED_BIGDECIMAL_RET max(IFunctionBigDecimal<T> field) {
		return assureNamedFunctions().max(field);
	}

	@Override
	public final <T> NAMED_DATE_RET max(IFunctionDate<T> field) {
		return assureNamedFunctions().max(field);
	}

	@Override
	public final <T> NAMED_BYTE_RET min(IFunctionByte<T> field) {
		return assureNamedFunctions().min(field);
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
	public final <T> NAMED_BIGINTEGER_RET min(IFunctionBigInteger<T> field) {
		return assureNamedFunctions().min(field);
	}

	@Override
	public final <T> NAMED_FLOAT_RET min(IFunctionFloat<T> field) {
		return assureNamedFunctions().min(field);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET min(IFunctionDouble<T> field) {
		return assureNamedFunctions().min(field);
	}

	@Override
	public final <T> NAMED_BIGDECIMAL_RET min(IFunctionBigDecimal<T> field) {
		return assureNamedFunctions().min(field);
	}

	@Override
	public final <T> NAMED_DATE_RET min(IFunctionDate<T> field) {
		return assureNamedFunctions().min(field);
	}

	@Override
	public final <T> NAMED_SUM_LONG_RET sum(IFunctionByte<T> field) {
		return assureNamedFunctions().sum(field);
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
	public final <T> NAMED_BIGINTEGER_RET sum(IFunctionLong<T> field) {
		return assureNamedFunctions().sum(field);
	}

	@Override
	public final <T> NAMED_BIGINTEGER_RET sum(IFunctionBigInteger<T> field) {
		return assureNamedFunctions().sum(field);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET sum(IFunctionFloat<T> field) {
		return assureNamedFunctions().sum(field);
	}

	@Override
	public final <T> NAMED_DOUBLE_RET sum(IFunctionDouble<T> field) {
		return assureNamedFunctions().sum(field);
	}

	@Override
	public final <T> NAMED_BIGDECIMAL_RET sum(IFunctionBigDecimal<T> field) {
		return assureNamedFunctions().sum(field);
	}

	@Override
	public final <T> NAMED_STRING_RET lower(IFunctionString<T> getter) {
		return assureNamedFunctions().lower(getter);
	}

	@Override
	public final <T> NAMED_STRING_RET upper(IFunctionString<T> getter) {
		return assureNamedFunctions().upper(getter);
	}

	@Override
	public final <T> NAMED_STRING_RET trim(IFunctionString<T> getter) {
		return assureNamedFunctions().trim(getter);
	}

		
	/****************************************************************
	 * Alias
	 ***************************************************************/

	//*************** Arithmetic forwarding functions ***************


	@Override
	public final <T> ALIAS_BYTE_RET abs(ISupplierByte getter) {
		return assureAliasFunctions().abs(getter);
	}

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
	public final <T> ALIAS_BIGINTEGER_RET abs(ISupplierBigInteger getter) {
		return assureAliasFunctions().abs(getter);
	}

	@Override
	public final <T> ALIAS_FLOAT_RET abs(ISupplierFloat getter) {
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
	public final <T> ALIAS_DOUBLE_RET sqrt(ISupplierByte getter) {
		return assureAliasFunctions().sqrt(getter);
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
	public final <T> ALIAS_DOUBLE_RET sqrt(ISupplierBigInteger getter) {
		return assureAliasFunctions().sqrt(getter);
	}

	@Override
	public final <T> ALIAS_DOUBLE_RET sqrt(ISupplierFloat getter) {
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
	public final ALIAS_DOUBLE_RET avg(ISupplierByte field) {
		return assureAliasFunctions().avg(field);
	}

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
	public final ALIAS_DOUBLE_RET avg(ISupplierBigInteger field) {
		return assureAliasFunctions().avg(field);
	}

	@Override
	public final ALIAS_DOUBLE_RET avg(ISupplierFloat field) {
		return assureAliasFunctions().avg(field);
	}

	@Override
	public final ALIAS_DOUBLE_RET avg(ISupplierDouble field) {
		return assureAliasFunctions().avg(field);
	}

	@Override
	public final ALIAS_DOUBLE_RET avg(ISupplierBigDecimal field) {
		return assureAliasFunctions().avg(field);
	}

	@Override
	public final ALIAS_COUNT_RET count(ISupplierByte field) {
		return assureAliasFunctions().count(field);
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
	public final ALIAS_COUNT_RET count(ISupplierBigInteger field) {
		return assureAliasFunctions().count(field);
	}

	@Override
	public final ALIAS_COUNT_RET count(ISupplierFloat field) {
		return assureAliasFunctions().count(field);
	}

	@Override
	public final ALIAS_COUNT_RET count(ISupplierDouble field) {
		return assureAliasFunctions().count(field);
	}

	@Override
	public final ALIAS_COUNT_RET count(ISupplierBigDecimal field) {
		return assureAliasFunctions().count(field);
	}

	@Override
	public final ALIAS_BYTE_RET max(ISupplierByte field) {
		return assureAliasFunctions().max(field);
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
	public final ALIAS_BIGINTEGER_RET max(ISupplierBigInteger field) {
		return assureAliasFunctions().max(field);
	}

	@Override
	public final ALIAS_FLOAT_RET max(ISupplierFloat field) {
		return assureAliasFunctions().max(field);
	}

	@Override
	public final ALIAS_DOUBLE_RET max(ISupplierDouble field) {
		return assureAliasFunctions().max(field);
	}

	@Override
	public final ALIAS_BIGDECIMAL_RET max(ISupplierBigDecimal field) {
		return assureAliasFunctions().max(field);
	}

	@Override
	public final ALIAS_DATE_RET max(ISupplierDate field) {
		return assureAliasFunctions().max(field);
	}

	@Override
	public final ALIAS_BYTE_RET min(ISupplierByte field) {
		return assureAliasFunctions().min(field);
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
	public final ALIAS_BIGINTEGER_RET min(ISupplierBigInteger field) {
		return assureAliasFunctions().min(field);
	}

	@Override
	public final ALIAS_FLOAT_RET min(ISupplierFloat field) {
		return assureAliasFunctions().min(field);
	}

	@Override
	public final ALIAS_DOUBLE_RET min(ISupplierDouble field) {
		return assureAliasFunctions().min(field);
	}

	@Override
	public final ALIAS_BIGDECIMAL_RET min(ISupplierBigDecimal field) {
		return assureAliasFunctions().min(field);
	}
	
	@Override
	public final ALIAS_DATE_RET min(ISupplierDate field) {
		return assureAliasFunctions().min(field);
	}
	

	@Override
	public final ALIAS_SUM_LONG_RET sum(ISupplierByte field) {
		return assureAliasFunctions().sum(field);
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
	public final ALIAS_BIGINTEGER_RET sum(ISupplierLong field) {
		return assureAliasFunctions().sum(field);
	}

	@Override
	public final ALIAS_BIGINTEGER_RET sum(ISupplierBigInteger field) {
		return assureAliasFunctions().sum(field);
	}

	@Override
	public final ALIAS_DOUBLE_RET sum(ISupplierFloat field) {
		return assureAliasFunctions().sum(field);
	}

	@Override
	public final ALIAS_DOUBLE_RET sum(ISupplierDouble field) {
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
