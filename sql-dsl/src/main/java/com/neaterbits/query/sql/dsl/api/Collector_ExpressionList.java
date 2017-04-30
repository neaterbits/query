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
		UNDECIDED_RET extends ISharedFunction_After<MODEL, RESULT>,
		
		NUMERIC_OPERAND_NEXT    extends ISharedFunction_Next<MODEL, RESULT, OPERAND_RET>,
		STRING_OPERAND_NEXT    extends ISharedFunction_Next<MODEL, RESULT, OPERAND_RET>,

		NAMED_SUM_LONG_RET  extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_COUNT_RET   	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_LENGTH_RET    extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,

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
		ALIAS_LENGTH_RET    extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		
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
		ALIAS_SQLTIMESTAMP_RET  extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		
		UNDECIDED_SUM_LONG_RET  extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_COUNT_RET     extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_LENGTH_RET    extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		
		UNDECIDED_BYTE_RET  	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_SHORT_RET  	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_INTEGER_RET  	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_LONG_RET 	 	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_BIGINTEGER_RET 	 	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_FLOAT_RET 	 	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_DOUBLE_RET   	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_BIGDECIMAL_RET  extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_STRING_RET  	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_DATE_RET  	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_CALENDAR_RET  extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_SQLDATE_RET  	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_SQLTIME_RET  	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_SQLTIMESTAMP_RET  extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>
		
	>

	extends Collector_ExpressionList_Base<MODEL, RESULT, R, OPERAND_RET> 

	//Implement both Numeric and String in this baseclass 
	implements 
	
			   ISharedOperands_Numeric_Named_All<MODEL, RESULT, OPERAND_RET, NUMERIC_OPERAND_NEXT>,
			   ISharedOperands_String_Named_All<MODEL, RESULT, OPERAND_RET, STRING_OPERAND_NEXT>, 

			   ISharedOperands_Numeric_Alias_All<MODEL, RESULT, OPERAND_RET, NUMERIC_OPERAND_NEXT>,
			   ISharedOperands_String_Alias_All<MODEL, RESULT, OPERAND_RET, STRING_OPERAND_NEXT>,
			   



	// Also implements all functions and just forwards them, so that we can always 
	// just instantiate an expression list wherever expressions are to be collected
			   
	// NOTE! We do not bother about types here since Java does not keep generic-types at runtime in any case
			 
			   ISharedFunctions_Arithmetic_Named_All<
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
			   
			   ISharedFunctions_Aggregate_Named_All<NAMED_SUM_LONG_RET, NAMED_COUNT_RET, NAMED_BYTE_RET, NAMED_SHORT_RET, NAMED_INTEGER_RET, NAMED_LONG_RET, NAMED_BIGINTEGER_RET, NAMED_FLOAT_RET, NAMED_DOUBLE_RET, NAMED_BIGDECIMAL_RET, NAMED_DATE_RET>,
			   ISharedFunctions_String_Named_All<MODEL, RESULT, NAMED_RET, NAMED_LENGTH_RET, NAMED_STRING_RET>,
			   
			   ISharedFunctions_Arithmetic_Alias_All<
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
			   ISharedFunctions_Aggregate_Alias_All<ALIAS_SUM_LONG_RET, ALIAS_COUNT_RET, ALIAS_BYTE_RET, ALIAS_SHORT_RET, ALIAS_INTEGER_RET, ALIAS_LONG_RET, ALIAS_BIGINTEGER_RET, ALIAS_FLOAT_RET, ALIAS_DOUBLE_RET, ALIAS_BIGDECIMAL_RET, ALIAS_DATE_RET>,
			   ISharedFunctions_String_Alias_All<MODEL, RESULT, ALIAS_RET, ALIAS_LENGTH_RET, ALIAS_STRING_RET>,

		   ISharedFunctions_Arithmetic_Undecided<
				   MODEL,
				   RESULT,
	
				   NAMED_RET,
				   ALIAS_RET,
				   UNDECIDED_RET,

				   NAMED_BYTE_RET,
				   NAMED_SHORT_RET,
				   NAMED_INTEGER_RET,
				   NAMED_LONG_RET,
				   NAMED_BIGINTEGER_RET,
				   NAMED_FLOAT_RET,
				   NAMED_DOUBLE_RET,
				   NAMED_BIGDECIMAL_RET,
				   
				   ALIAS_BYTE_RET,
				   ALIAS_SHORT_RET,
				   ALIAS_INTEGER_RET,
				   ALIAS_LONG_RET,
				   ALIAS_BIGINTEGER_RET,
				   ALIAS_FLOAT_RET,
				   ALIAS_DOUBLE_RET,
				   ALIAS_BIGDECIMAL_RET,
				   
				   UNDECIDED_BYTE_RET,
				   UNDECIDED_SHORT_RET,
				   UNDECIDED_INTEGER_RET,
				   UNDECIDED_LONG_RET,
				   UNDECIDED_BIGINTEGER_RET,
				   UNDECIDED_FLOAT_RET,
				   UNDECIDED_DOUBLE_RET,
				   UNDECIDED_BIGDECIMAL_RET
				   >  {
			   
					   
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
					
			?, ?, ?,
					
			?, ?, ?,
		    ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
			?, ?, ?,
			?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
			?, ?, ?,
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
		setAccessType(EFieldAccessType.ALIAS);
	}

	private void setUndecided() {
		setAccessType(EFieldAccessType.UNDECIDED);
	}
	
	final EFieldAccessType getFieldAccessType() {
		return fieldAccessType;
	}
	
	
	@SuppressWarnings("unchecked")
	final <CLAUSE> CLAUSE absNamedNoParam() {
		return (CLAUSE)assureNamedFunctions().abs();
	}

	@SuppressWarnings("unchecked")
	final <CLAUSE> CLAUSE sqrtNamedNoParam() {
		return (CLAUSE)assureNamedFunctions().sqrt();
	}

	@SuppressWarnings("unchecked")
	final <CLAUSE> CLAUSE lowerNamedNoParam() {
		return (CLAUSE)assureNamedFunctions().lower();
	}

	@SuppressWarnings("unchecked")
	final <CLAUSE> CLAUSE upperNamedNoParam() {
		return (CLAUSE)assureNamedFunctions().upper();
	}
	
	@SuppressWarnings("unchecked")
	final <CLAUSE> CLAUSE trimNamedNoParam() {
		return (CLAUSE)assureNamedFunctions().trim();
	}

	@SuppressWarnings("unchecked")
	final <CLAUSE> CLAUSE lengthNamedNoParam() {
		return (CLAUSE)assureNamedFunctions().length();
	}

	@SuppressWarnings("unchecked")
	final <CLAUSE> CLAUSE absAliasNoParam() {
		return (CLAUSE)assureAliasFunctions().abs();
	}

	@SuppressWarnings("unchecked")
	final <CLAUSE> CLAUSE sqrtAliasNoParam() {
		return (CLAUSE)assureAliasFunctions().sqrt();
	}

	@SuppressWarnings("unchecked")
	final <CLAUSE> CLAUSE lowerAliasNoParam() {
		return (CLAUSE)assureAliasFunctions().lower();
	}

	@SuppressWarnings("unchecked")
	final <CLAUSE> CLAUSE upperAliasNoParam() {
		return (CLAUSE)assureAliasFunctions().upper();
	}
	
	@SuppressWarnings("unchecked")
	final <CLAUSE> CLAUSE trimAliasNoParam() {
		return (CLAUSE)assureAliasFunctions().trim();
	}

	@SuppressWarnings("unchecked")
	final <CLAUSE> CLAUSE lengthAliasNoParam() {
		return (CLAUSE)assureAliasFunctions().length();
	}

	@SuppressWarnings("unchecked")
	final <CLAUSE> CLAUSE absUndecidedNoParam() {
		return (CLAUSE)assureUndecidedFunctions().abs();
	}

	@SuppressWarnings("unchecked")
	final <CLAUSE> CLAUSE sqrtUndecidedNoParam() {
		return (CLAUSE)assureUndecidedFunctions().sqrt();
	}

	@SuppressWarnings("unchecked")
	final <CLAUSE> CLAUSE lowerUndecidedNoParam() {
		return (CLAUSE)assureUndecidedFunctions().lower();
	}

	@SuppressWarnings("unchecked")
	final <CLAUSE> CLAUSE upperUndecidedNoParam() {
		return (CLAUSE)assureUndecidedFunctions().upper();
	}
	
	@SuppressWarnings("unchecked")
	final <CLAUSE> CLAUSE trimUndecidedNoParam() {
		return (CLAUSE)assureUndecidedFunctions().trim();
	}

	@SuppressWarnings("unchecked")
	final <CLAUSE> CLAUSE lengthUndecidedNoParam() {
		return (CLAUSE)assureUndecidedFunctions().length();
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
	
	ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET> getUndecidedNoParamNext(ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET> def) {
		return def;
	}
	
	
	abstract NamedFunctions createNamedFunctions(ISharedCollector_Functions_Callback<MODEL, RESULT, NAMED_RET> func);

	protected class NamedFunctions<
	
		NO_PARAM_ARITHMETIC_SAME_TYPE_RET,
		NO_PARAM_ARITHMETIC_DOUBLE_RET,
		
		NO_PARAM_STRING_SAME_TYPE_RET,
		NO_PARAM_STRING_LENGTH_RET
		>
	
	
		extends Collector_NestedFunctions_Named<
					MODEL, RESULT, NAMED_RET,
					
					NAMED_SUM_LONG_RET, NAMED_COUNT_RET, NAMED_LENGTH_RET, NAMED_BYTE_RET, NAMED_SHORT_RET, NAMED_INTEGER_RET, NAMED_LONG_RET, NAMED_BIGINTEGER_RET, NAMED_FLOAT_RET, NAMED_DOUBLE_RET, NAMED_BIGDECIMAL_RET, NAMED_STRING_RET, NAMED_DATE_RET, NAMED_CALENDAR_RET, NAMED_SQLDATE_RET, NAMED_SQLTIME_RET, NAMED_SQLTIMESTAMP_RET,
					
					NO_PARAM_ARITHMETIC_SAME_TYPE_RET,
					NO_PARAM_ARITHMETIC_DOUBLE_RET,
					
					NO_PARAM_STRING_SAME_TYPE_RET,
					NO_PARAM_STRING_LENGTH_RET
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
		
		NO_PARAM_STRING_SAME_TYPE_RET,
		NO_PARAM_STRING_LENGTH_RET
		>
	
		extends Collector_NestedFunctions_Alias<
					MODEL, RESULT, ALIAS_RET,
					
					ALIAS_SUM_LONG_RET, ALIAS_COUNT_RET, ALIAS_LENGTH_RET, ALIAS_BYTE_RET, ALIAS_SHORT_RET, ALIAS_INTEGER_RET, ALIAS_LONG_RET, ALIAS_BIGINTEGER_RET, ALIAS_FLOAT_RET, ALIAS_DOUBLE_RET, ALIAS_BIGDECIMAL_RET, ALIAS_STRING_RET, ALIAS_DATE_RET, ALIAS_CALENDAR_RET, ALIAS_SQLDATE_RET, ALIAS_SQLTIME_RET, ALIAS_SQLTIMESTAMP_RET,
					
					NO_PARAM_ARITHMETIC_SAME_TYPE_RET,
					NO_PARAM_ARITHMETIC_DOUBLE_RET,
					
					NO_PARAM_STRING_SAME_TYPE_RET,
					NO_PARAM_STRING_LENGTH_RET
					> 
	
		implements ISharedFunction_Next<MODEL, RESULT, ALIAS_RET> {
		
			protected AliasFunctions(Collector_NestedFunctions_Alias<
								MODEL, RESULT, ALIAS_RET,
								?, ?, ?,
								?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
								?, ?, ?, ?> toCopy) {
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

	protected class UndecidedFunctions<
		NO_PARAM_ARITHMETIC_SAME_TYPE_RET,
		NO_PARAM_ARITHMETIC_DOUBLE_RET,
		
		NO_PARAM_STRING_SAME_TYPE_RET,
		NO_PARAM_STRING_LENGTH_RET
		>

	extends Collector_NestedFunctions_Undecided<
				MODEL, RESULT, 
				
				UNDECIDED_RET,
				
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
				UNDECIDED_SQLTIMESTAMP_RET,
				
				NO_PARAM_ARITHMETIC_SAME_TYPE_RET,
				NO_PARAM_ARITHMETIC_DOUBLE_RET,
				
				NO_PARAM_STRING_SAME_TYPE_RET,
				NO_PARAM_STRING_LENGTH_RET
				> 

	implements ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET> {
	
		protected UndecidedFunctions(Collector_NestedFunctions_Undecided<
							MODEL, RESULT, UNDECIDED_RET,
							?, ?, ?,
							?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,
							?, ?, ?, ?> toCopy) {
			super(toCopy);
		}
	
		protected UndecidedFunctions(ISharedCollector_Functions_Callback<MODEL, RESULT, UNDECIDED_RET> func) {
			super(func);
		}
	
		@Override
		ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET> getUndecidedNoParamNext() {
			return Collector_ExpressionList.this.getUndecidedNoParamNext(this);
		}
	}
	
	private UndecidedFunctions undecided;
	
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
				assureNamedFunctions().addNoParamFunctionToList(function);
				break;
				
			case ALIAS:
				assureAliasFunctions().addNoParamFunctionToList(function);
				break;
				
			default:
				throw new UnsupportedOperationException("Unknown field access type: " + fieldAccessType);
			}
		}
	}
	
	//*************** Arithmetic forwarding functions ***************

	private NamedFunctions<?, ?, ?, ?> assureNamedFunctions() {
		
		
		setNamed();
		
		if (this.named == null) {
			
			final ISharedCollector_Functions_Callback<MODEL, RESULT, NAMED_RET> callback
			
				= new ISharedCollector_Functions_Callback<MODEL, RESULT, NAMED_RET>() {

					@Override
					public ISharedFunction_Next<MODEL, RESULT, NAMED_RET> onComparable(Expression expression) {
						addNamedFunctionResult(expression);
						
						return getNamedComparableFunctionNext(expression);
					}

					@Override
					public ISharedFunction_Next<MODEL, RESULT, NAMED_RET> onString(Expression expression) {
						addNamedFunctionResult(expression);
						
						return getNamedStringFunctionNext(expression);
					}
			};
			
			this.named = createNamedFunctions(callback);
		}

		@SuppressWarnings("unchecked")
		final NamedFunctions<?, ?, ?, ?> ret = this.named;
		
		return ret;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	ISharedFunction_Next<MODEL, RESULT, NAMED_RET> getNamedComparableFunctionNext(Expression expression) {
		return (ISharedFunction_Next)this;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	ISharedFunction_Next<MODEL, RESULT, NAMED_RET> getNamedStringFunctionNext(Expression expression) {
		return (ISharedFunction_Next)this;
	}
	
	
	private final void addNamedFunctionResult(Expression expression) {
		addExpression(expression);
		
		// must clear named-functions as to collect new ones after operand
		this.named = null;
	}
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	ISharedFunction_Next<MODEL, RESULT, ALIAS_RET> getAliasComparableFunctionNext(Expression expression) {
		return (ISharedFunction_Next)this;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	ISharedFunction_Next<MODEL, RESULT, ALIAS_RET> getAliasStringFunctionNext(Expression expression) {
		return (ISharedFunction_Next)this;
	}
	
	
	private final void addAliasFunctionResult(Expression expression) {
		addExpression(expression);
		
		// must clear alias-functions as to collect new ones after operand
		this.alias = null;
	}
	

	private AliasFunctions<?, ?, ?, ?> assureAliasFunctions() {
		
		setAliased();
		
		if (this.alias == null) {
			
			final ISharedCollector_Functions_Callback<MODEL, RESULT, ALIAS_RET> callback
			
				= new ISharedCollector_Functions_Callback<MODEL, RESULT, ALIAS_RET>() {

				@Override
				public ISharedFunction_Next<MODEL, RESULT, ALIAS_RET> onComparable(Expression expression) {
					addAliasFunctionResult(expression);
					
					return getAliasComparableFunctionNext(expression);
				}

				@Override
				public ISharedFunction_Next<MODEL, RESULT, ALIAS_RET> onString(Expression expression) {
					addAliasFunctionResult(expression);
					
					return getAliasStringFunctionNext(expression);
				}
			};
			
			this.alias = createAliasFunctions(callback);
		}
		
		@SuppressWarnings("unchecked")
		final AliasFunctions<?, ?, ?, ?> ret = this.alias;

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
	public final NAMED_BYTE_RET absOfByte(ISharedSubOperandsFunction_Byte_Named<MODEL, RESULT> sub) {
		return assureNamedFunctions().absOfByte(sub);
	}

	@Override
	public final NAMED_SHORT_RET absOfShort(ISharedSubOperandsFunction_Short_Named<MODEL, RESULT> sub) {
		return assureNamedFunctions().absOfShort(sub);
	}

	@Override
	public final NAMED_INTEGER_RET absOfInteger(ISharedSubOperandsFunction_Integer_Named<MODEL, RESULT> sub) {
		return assureNamedFunctions().absOfInteger(sub);
	}

	@Override
	public final NAMED_LONG_RET absOfLong(ISharedSubOperandsFunction_Long_Named<MODEL, RESULT> sub) {
		return assureNamedFunctions().absOfLong(sub);
	}

	@Override
	public final NAMED_BIGINTEGER_RET absOfBigInteger(ISharedSubOperandsFunction_BigInteger_Named<MODEL, RESULT> sub) {
		return assureNamedFunctions().absOfBigInteger(sub);
	}

	@Override
	public final NAMED_FLOAT_RET absOfFloat(ISharedSubOperandsFunction_Float_Named<MODEL, RESULT> sub) {
		return assureNamedFunctions().absOfFloat(sub);
	}

	@Override
	public final NAMED_DOUBLE_RET absOfDouble(ISharedSubOperandsFunction_Double_Named<MODEL, RESULT> sub) {
		return assureNamedFunctions().absOfDouble(sub);
	}

	@Override
	public final NAMED_BIGDECIMAL_RET absOfBigDecimal(ISharedSubOperandsFunction_BigDecimal_Named<MODEL, RESULT> sub) {
		return assureNamedFunctions().absOfBigDecimal(sub);
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
	public final NAMED_DOUBLE_RET sqrtOf(ISharedSubOperandsFunction_Byte_Named<MODEL, RESULT> sub) {
		return assureNamedFunctions().sqrtOf(sub);
	}

	@Override
	public final NAMED_DOUBLE_RET sqrtOf(ISharedSubOperandsFunction_Short_Named<MODEL, RESULT> sub) {
		return assureNamedFunctions().sqrtOf(sub);
	}

	@Override
	public final NAMED_DOUBLE_RET sqrtOf(ISharedSubOperandsFunction_Integer_Named<MODEL, RESULT> sub) {
		return assureNamedFunctions().sqrtOf(sub);
	}

	@Override
	public final NAMED_DOUBLE_RET sqrtOf(ISharedSubOperandsFunction_BigInteger_Named<MODEL, RESULT> sub) {
		return assureNamedFunctions().sqrtOf(sub);
	}

	@Override
	public final NAMED_DOUBLE_RET sqrtOf(ISharedSubOperandsFunction_Long_Named<MODEL, RESULT> sub) {
		return assureNamedFunctions().sqrtOf(sub);
	}

	@Override
	public final NAMED_DOUBLE_RET sqrtOf(ISharedSubOperandsFunction_Float_Named<MODEL, RESULT> sub) {
		return assureNamedFunctions().sqrtOf(sub);
	}

	@Override
	public final NAMED_DOUBLE_RET sqrtOf(ISharedSubOperandsFunction_Double_Named<MODEL, RESULT> sub) {
		return assureNamedFunctions().sqrtOf(sub);
	}

	@Override
	public final NAMED_DOUBLE_RET sqrtOf(ISharedSubOperandsFunction_BigDecimal_Named<MODEL, RESULT> sub) {
		return assureNamedFunctions().sqrtOf(sub);
	}
	
	@Override
	public final <T> NAMED_INTEGER_RET mod(IFunctionInteger<T> getter, int value) {
		return assureNamedFunctions().mod(getter, value);
	}

	@Override
	public final NAMED_INTEGER_RET modOf(ISharedSubOperandsFunction_Integer_Named<MODEL, RESULT> sub, int value) {
		return assureNamedFunctions().modOf(sub, value);
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
	public final <T> NAMED_LENGTH_RET length(IFunctionString<T> getter) {
		return assureNamedFunctions().length(getter);
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

	@Override
	public final <T> NAMED_STRING_RET substring(IFunctionString<T> getter, int start, int length) {
		return assureNamedFunctions().substring(getter, start, length);
	}

	@Override
	public final <T> NAMED_STRING_RET concat(IFunctionString<T> getter1, IFunctionString<T> getter2) {
		return assureNamedFunctions().concat(getter1, getter2);
	}

	@Override
	public final <T> NAMED_STRING_RET concat(IFunctionString<T> getter, String value) {
		return assureNamedFunctions().concat(getter, value);
	}

	@Override
	public final <T> NAMED_STRING_RET concat(String value, IFunctionString<T> getter) {
		return assureNamedFunctions().concat(value, getter);
	}

	@Override
	public final <T> NAMED_STRING_RET concat(IFunctionString<T> getter, Param<String> param) {
		return assureNamedFunctions().concat(getter, param);
	}

	@Override
	public final <T> NAMED_STRING_RET concat(Param<String> param, IFunctionString<T> getter) {
		return assureNamedFunctions().concat(param, getter);
	}

	/****************************************************************
	 * Alias
	 ***************************************************************/

	//*************** Arithmetic forwarding functions ***************


	@Override
	public final ALIAS_BYTE_RET abs(ISupplierByte getter) {
		return assureAliasFunctions().abs(getter);
	}

	@Override
	public final ALIAS_SHORT_RET abs(ISupplierShort getter) {
		return assureAliasFunctions().abs(getter);
	}

	@Override
	public final ALIAS_INTEGER_RET abs(ISupplierInteger getter) {
		return assureAliasFunctions().abs(getter);
	}

	@Override
	public final ALIAS_LONG_RET abs(ISupplierLong getter) {
		return assureAliasFunctions().abs(getter);
	}

	@Override
	public final ALIAS_BIGINTEGER_RET abs(ISupplierBigInteger getter) {
		return assureAliasFunctions().abs(getter);
	}

	@Override
	public final ALIAS_FLOAT_RET abs(ISupplierFloat getter) {
		return assureAliasFunctions().abs(getter);
	}

	@Override
	public final ALIAS_DOUBLE_RET abs(ISupplierDouble getter) {
		return assureAliasFunctions().abs(getter);
	}

	@Override
	public final ALIAS_BIGDECIMAL_RET abs(ISupplierBigDecimal getter) {
		return assureAliasFunctions().abs(getter);
	}

	@Override
	public final ALIAS_BYTE_RET absOfByte(ISharedSubOperandsFunction_Byte_Alias<MODEL, RESULT> sub) {
		return assureAliasFunctions().absOfByte(sub);
	}

	@Override
	public final ALIAS_SHORT_RET absOfShort(ISharedSubOperandsFunction_Short_Alias<MODEL, RESULT> sub) {
		return assureAliasFunctions().absOfShort(sub);
	}

	@Override
	public final ALIAS_INTEGER_RET absOfInteger(ISharedSubOperandsFunction_Integer_Alias<MODEL, RESULT> sub) {
		return assureAliasFunctions().absOfInteger(sub);
	}

	@Override
	public final ALIAS_LONG_RET absOfLong(ISharedSubOperandsFunction_Long_Alias<MODEL, RESULT> sub) {
		return assureAliasFunctions().absOfLong(sub);
	}

	@Override
	public final ALIAS_BIGINTEGER_RET absOfBigInteger(ISharedSubOperandsFunction_BigInteger_Alias<MODEL, RESULT> sub) {
		return assureAliasFunctions().absOfBigInteger(sub);
	}

	@Override
	public final ALIAS_FLOAT_RET absOfFloat(ISharedSubOperandsFunction_Float_Alias<MODEL, RESULT> sub) {
		return assureAliasFunctions().absOfFloat(sub);
	}

	@Override
	public final ALIAS_DOUBLE_RET absOfDouble(ISharedSubOperandsFunction_Double_Alias<MODEL, RESULT> sub) {
		return assureAliasFunctions().absOfDouble(sub);
	}

	@Override
	public final ALIAS_BIGDECIMAL_RET absOfBigDecimal(ISharedSubOperandsFunction_BigDecimal_Alias<MODEL, RESULT> sub) {
		return assureAliasFunctions().absOfBigDecimal(sub);
	}

	@Override
	public final ALIAS_DOUBLE_RET sqrt(ISupplierByte getter) {
		return assureAliasFunctions().sqrt(getter);
	}

	@Override
	public final ALIAS_DOUBLE_RET sqrt(ISupplierShort getter) {
		return assureAliasFunctions().sqrt(getter);
	}

	@Override
	public final ALIAS_DOUBLE_RET sqrt(ISupplierInteger getter) {
		return assureAliasFunctions().sqrt(getter);
	}

	@Override
	public final ALIAS_DOUBLE_RET sqrt(ISupplierLong getter) {
		return assureAliasFunctions().sqrt(getter);
	}

	@Override
	public final ALIAS_DOUBLE_RET sqrt(ISupplierBigInteger getter) {
		return assureAliasFunctions().sqrt(getter);
	}

	@Override
	public final ALIAS_DOUBLE_RET sqrt(ISupplierFloat getter) {
		return assureAliasFunctions().sqrt(getter);
	}

	@Override
	public final ALIAS_DOUBLE_RET sqrt(ISupplierDouble getter) {
		return assureAliasFunctions().sqrt(getter);
	}

	@Override
	public final ALIAS_DOUBLE_RET sqrt(ISupplierBigDecimal getter) {
		return assureAliasFunctions().sqrt(getter);
	}

	@Override
	public final ALIAS_DOUBLE_RET sqrtOf(ISharedSubOperandsFunction_Byte_Alias<MODEL, RESULT> sub) {
		return assureAliasFunctions().sqrtOf(sub);
	}

	@Override
	public final ALIAS_DOUBLE_RET sqrtOf(ISharedSubOperandsFunction_Short_Alias<MODEL, RESULT> sub) {
		return assureAliasFunctions().sqrtOf(sub);
	}

	@Override
	public final ALIAS_DOUBLE_RET sqrtOf(ISharedSubOperandsFunction_Integer_Alias<MODEL, RESULT> sub) {
		return assureAliasFunctions().sqrtOf(sub);
	}

	@Override
	public final ALIAS_DOUBLE_RET sqrtOf(ISharedSubOperandsFunction_BigInteger_Alias<MODEL, RESULT> sub) {
		return assureAliasFunctions().sqrtOf(sub);
	}

	@Override
	public final ALIAS_DOUBLE_RET sqrtOf(ISharedSubOperandsFunction_Long_Alias<MODEL, RESULT> sub) {
		return assureAliasFunctions().sqrtOf(sub);
	}

	@Override
	public final ALIAS_DOUBLE_RET sqrtOf(ISharedSubOperandsFunction_Float_Alias<MODEL, RESULT> sub) {
		return assureAliasFunctions().sqrtOf(sub);
	}

	@Override
	public final ALIAS_DOUBLE_RET sqrtOf(ISharedSubOperandsFunction_Double_Alias<MODEL, RESULT> sub) {
		return assureAliasFunctions().sqrtOf(sub);
	}

	@Override
	public final ALIAS_DOUBLE_RET sqrtOf(ISharedSubOperandsFunction_BigDecimal_Alias<MODEL, RESULT> sub) {
		return assureAliasFunctions().sqrtOf(sub);
	}

	@Override
	public final ALIAS_INTEGER_RET mod(ISupplierInteger getter, int value) {
		return assureAliasFunctions().mod(getter, value);
	}

	@Override
	public final ALIAS_INTEGER_RET modOf(ISharedSubOperandsFunction_Integer_Alias<MODEL, RESULT> sub, int value) {
		return assureAliasFunctions().modOf(sub, value);
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
	public final ALIAS_LENGTH_RET length(ISupplierString getter) {
		return assureAliasFunctions().length(getter);
	}
	
	@Override
	public final ALIAS_STRING_RET lower(ISupplierString getter) {
		return assureAliasFunctions().lower(getter);
	}

	@Override
	public final ALIAS_STRING_RET upper(ISupplierString getter) {
		return assureAliasFunctions().upper(getter);
	}

	@Override
	public final ALIAS_STRING_RET trim(ISupplierString getter) {
		return assureAliasFunctions().trim(getter);
	}

	@Override
	public final ALIAS_STRING_RET substring(ISupplierString getter, int start, int length) {
		return assureAliasFunctions().substring(getter, start, length);
	}

	@Override
	public final ALIAS_STRING_RET concat(ISupplierString getter1, ISupplierString getter2) {
		return assureAliasFunctions().concat(getter1, getter2);
	}

	@Override
	public final ALIAS_STRING_RET concat(ISupplierString getter, String value) {
		return assureAliasFunctions().concat(getter, value);
	}

	@Override
	public final ALIAS_STRING_RET concat(String value, ISupplierString getter) {
		return assureAliasFunctions().concat(value, getter);
	}

	@Override
	public final ALIAS_STRING_RET concat(ISupplierString getter, Param<String> param) {
		return assureAliasFunctions().concat(getter, param);
	}

	@Override
	public final ALIAS_STRING_RET concat(Param<String> param, ISupplierString getter) {
		return assureAliasFunctions().concat(param, getter);
	}
	
	
	/****************************************************************
	 * Undecided
	 ***************************************************************/
	
	abstract UndecidedFunctions createUndecidedFunctions(ISharedCollector_Functions_Callback<MODEL, RESULT, UNDECIDED_RET> func);
	

	private UndecidedFunctions<?, ?, ?, ?> assureUndecidedFunctions() {
		
		
		setUndecided();
		
		if (this.undecided == null) {
			
			final ISharedCollector_Functions_Callback<MODEL, RESULT, UNDECIDED_RET> callback
			
				= new ISharedCollector_Functions_Callback<MODEL, RESULT, UNDECIDED_RET>() {

					@Override
					public ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET> onComparable(Expression expression) {
						addNamedFunctionResult(expression);
						
						return getUndecidedComparableFunctionNext(expression);
					}

					@Override
					public ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET> onString(Expression expression) {
						addNamedFunctionResult(expression);
						
						return getUndecidedStringFunctionNext(expression);
					}
			};
			
			this.undecided= createUndecidedFunctions(callback);
		}

		@SuppressWarnings("unchecked")
		final UndecidedFunctions<?, ?, ?, ?> ret = this.undecided;
		
		return ret;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET> getUndecidedComparableFunctionNext(Expression expression) {
		return (ISharedFunction_Next)this;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET> getUndecidedStringFunctionNext(Expression expression) {
		return (ISharedFunction_Next)this;
	}
	
	
	@Override
	public final UNDECIDED_BYTE_RET absOfByte(ISharedSubOperandsFunction_Byte_Undecided<MODEL, RESULT> sub) {
		return assureUndecidedFunctions().absOfByte(sub);
	}

	@Override
	public final UNDECIDED_SHORT_RET absOfShort(ISharedSubOperandsFunction_Short_Undecided<MODEL, RESULT> sub) {
		return assureUndecidedFunctions().absOfShort(sub);
	}

	@Override
	public final UNDECIDED_INTEGER_RET absOfInteger(ISharedSubOperandsFunction_Integer_Undecided<MODEL, RESULT> sub) {
		return assureUndecidedFunctions().absOfInteger(sub);
	}

	@Override
	public final UNDECIDED_LONG_RET absOfLong(ISharedSubOperandsFunction_Long_Undecided<MODEL, RESULT> sub) {
		return assureUndecidedFunctions().absOfLong(sub);
	}

	@Override
	public final UNDECIDED_BIGINTEGER_RET absOfBigInteger(ISharedSubOperandsFunction_BigInteger_Undecided<MODEL, RESULT> sub) {
		return assureUndecidedFunctions().absOfBigInteger(sub);
	}

	@Override
	public final UNDECIDED_FLOAT_RET absOfFloat(ISharedSubOperandsFunction_Float_Undecided<MODEL, RESULT> sub) {
		return assureUndecidedFunctions().absOfFloat(sub);
	}

	@Override
	public final UNDECIDED_DOUBLE_RET absOfDouble(ISharedSubOperandsFunction_Double_Undecided<MODEL, RESULT> sub) {
		return assureUndecidedFunctions().absOfDouble(sub);
	}

	@Override
	public final UNDECIDED_BIGDECIMAL_RET absOfBigDecimal(ISharedSubOperandsFunction_BigDecimal_Undecided<MODEL, RESULT> sub) {
		return assureUndecidedFunctions().absOfBigDecimal(sub);
	}

	@Override
	public final UNDECIDED_DOUBLE_RET sqrtOf(ISharedSubOperandsFunction_Byte_Undecided<MODEL, RESULT> sub) {
		return assureUndecidedFunctions().sqrtOf(sub);
	}

	@Override
	public final UNDECIDED_DOUBLE_RET sqrtOf(ISharedSubOperandsFunction_Short_Undecided<MODEL, RESULT> sub) {
		return assureUndecidedFunctions().sqrtOf(sub);
	}

	@Override
	public final UNDECIDED_DOUBLE_RET sqrtOf(ISharedSubOperandsFunction_Integer_Undecided<MODEL, RESULT> sub) {
		return assureUndecidedFunctions().sqrtOf(sub);
	}

	@Override
	public final UNDECIDED_DOUBLE_RET sqrtOf(ISharedSubOperandsFunction_BigInteger_Undecided<MODEL, RESULT> sub) {
		return assureUndecidedFunctions().sqrtOf(sub);
	}

	@Override
	public final UNDECIDED_DOUBLE_RET sqrtOf(ISharedSubOperandsFunction_Long_Undecided<MODEL, RESULT> sub) {
		return assureUndecidedFunctions().sqrtOf(sub);
	}

	@Override
	public final UNDECIDED_DOUBLE_RET sqrtOf(ISharedSubOperandsFunction_Float_Undecided<MODEL, RESULT> sub) {
		return assureUndecidedFunctions().sqrtOf(sub);
	}

	@Override
	public final UNDECIDED_DOUBLE_RET sqrtOf(ISharedSubOperandsFunction_Double_Undecided<MODEL, RESULT> sub) {
		return assureUndecidedFunctions().sqrtOf(sub);
	}

	@Override
	public final UNDECIDED_DOUBLE_RET sqrtOf(ISharedSubOperandsFunction_BigDecimal_Undecided<MODEL, RESULT> sub) {
		return assureUndecidedFunctions().sqrtOf(sub);
	}

	@Override
	public final UNDECIDED_INTEGER_RET modOf(ISharedSubOperandsFunction_Integer_Undecided<MODEL, RESULT> sub, int value) {
		return assureUndecidedFunctions().modOf(sub, value);
	}
}
