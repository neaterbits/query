package com.neaterbits.query.sql.dsl.api;

import java.util.function.Supplier;

final class SharedMapFunctions_Initial<
		MODEL,
		RESULT,

		NAMED_RET extends ISharedSelectSourceBuilder<MODEL, RESULT>,
		ALIAS_RET extends ISharedSelectSourceBuilder<MODEL, RESULT>,

		
		NAMED_SUM_LONG_RET  extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_COUNT_RET		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_SHORT_RET		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_INT_RET		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_LONG_RET		extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_BIGDECIMAL_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_STRING_RET 	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		
		ALIAS_SUM_LONG_RET 	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_COUNT_RET		extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_SHORT_RET		extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_INT_RET		extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_LONG_RET		extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_BIGDECIMAL_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_STRING_RET 	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>
		
		
		
		/* Not comparables, we just map to ResultMapperTo for all
		NAMED_INTEGER_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Integer, NAMED_RET>,
		NAMED_LONG_CLAUSE, //  extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Long, NAMED_RET>,
		NAMED_STRING_CLAUSE, //  extends ISharedCondition_Comparable_String_Base<MODEL, RESULT, NAMED_RET>,
		
		ALIAS_INTEGER_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Integer, ALIAS_RET>,
		ALIAS_LONG_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Long, ALIAS_RET>,
		ALIAS_STRING_CLAUSE extends ISharedCondition_Comparable_String_Base<MODEL, RESULT, ALIAS_RET>
		*/
		
		/*
		NAMED_INTEGER_CLAUSE extends ISharedResultMapperTo<MODEL, RESULT, Integer, NAMED_RET>,
		NAMED_LONG_CLAUSE    extends ISharedResultMapperTo<MODEL, RESULT, Long,    NAMED_RET>,
		NAMED_STRING_CLAUSE  extends ISharedResultMapperTo<MODEL, RESULT, String,  NAMED_RET>,
		
		ALIAS_INTEGER_CLAUSE extends ISharedResultMapperTo<MODEL, RESULT, Integer, ALIAS_RET>,
		ALIAS_LONG_CLAUSE 	 extends ISharedResultMapperTo<MODEL, RESULT, Long,    ALIAS_RET>,
		ALIAS_STRING_CLAUSE  extends ISharedResultMapperTo<MODEL, RESULT, Integer, ALIAS_RET>
		*/
		>
		
	
	implements ISharedMapFunctions_Initial<
			MODEL,
			RESULT,

			NAMED_RET,
			ALIAS_RET,
			
			NAMED_SUM_LONG_RET,
			NAMED_COUNT_RET,
			NAMED_SHORT_RET,
			NAMED_INT_RET,
			NAMED_LONG_RET,
			NAMED_BIGDECIMAL_RET,
			NAMED_STRING_RET,

			ALIAS_SUM_LONG_RET,
			ALIAS_COUNT_RET,
			ALIAS_SHORT_RET,
			ALIAS_INT_RET,
			ALIAS_LONG_RET,
			ALIAS_BIGDECIMAL_RET,
			ALIAS_STRING_RET
			
			/*
			,
			
			
			NAMED_INTEGER_CLAUSE,
			NAMED_LONG_CLAUSE,
			NAMED_STRING_CLAUSE,
			
			ALIAS_INTEGER_CLAUSE,
			ALIAS_LONG_CLAUSE,
			ALIAS_STRING_CLAUSE
			*/
		> {
		
		
    private Collector_MapFunctions_Alias<MODEL, RESULT, ALIAS_RET, ALIAS_SUM_LONG_RET, ALIAS_COUNT_RET, ALIAS_SHORT_RET, ALIAS_INT_RET, ALIAS_LONG_RET, ALIAS_BIGDECIMAL_RET, ALIAS_INT_RET, ALIAS_LONG_RET, ALIAS_STRING_RET> alias;

    
    
	SharedMapFunctions_Initial() {
		this.alias = null;
	}

	@Override
	public <T> NAMED_INT_RET abs(IFunctionInteger<T> getter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> NAMED_LONG_RET abs(IFunctionLong<T> getter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> ALIAS_INT_RET abs(ISupplierInteger getter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> ALIAS_LONG_RET abs(ISupplierLong getter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> NAMED_SHORT_RET avg(IFunctionShort<T> field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> NAMED_INT_RET avg(IFunctionInteger<T> field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> NAMED_LONG_RET avg(IFunctionLong<T> field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> NAMED_BIGDECIMAL_RET avg(IFunctionBigDecimal<T> field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ALIAS_SHORT_RET avg(ISupplierShort field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ALIAS_INT_RET avg(ISupplierInteger field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ALIAS_LONG_RET avg(ISupplierLong field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ALIAS_BIGDECIMAL_RET avg(ISupplierBigDecimal field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> NAMED_COUNT_RET count(IFunctionShort<T> field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> NAMED_COUNT_RET count(IFunctionInteger<T> field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> NAMED_COUNT_RET count(IFunctionLong<T> field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> NAMED_COUNT_RET count(IFunctionBigDecimal<T> field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ALIAS_COUNT_RET count(ISupplierShort field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ALIAS_COUNT_RET count(ISupplierInteger field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ALIAS_COUNT_RET count(ISupplierLong field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ALIAS_COUNT_RET count(ISupplierBigDecimal field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> ALIAS_STRING_RET lower(ISupplierString getter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> NAMED_STRING_RET lower(StringFunction<T> getter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> NAMED_SHORT_RET max(IFunctionShort<T> field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> NAMED_INT_RET max(IFunctionInteger<T> field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> NAMED_LONG_RET max(IFunctionLong<T> field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> NAMED_BIGDECIMAL_RET max(IFunctionBigDecimal<T> field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ALIAS_SHORT_RET max(ISupplierShort field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ALIAS_INT_RET max(ISupplierInteger field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ALIAS_LONG_RET max(ISupplierLong field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ALIAS_BIGDECIMAL_RET max(ISupplierBigDecimal field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> NAMED_SHORT_RET min(IFunctionShort<T> field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> NAMED_INT_RET min(IFunctionInteger<T> field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> NAMED_LONG_RET min(IFunctionLong<T> field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> NAMED_BIGDECIMAL_RET min(IFunctionBigDecimal<T> field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ALIAS_SHORT_RET min(ISupplierShort field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ALIAS_INT_RET min(ISupplierInteger field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ALIAS_LONG_RET min(ISupplierLong field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ALIAS_BIGDECIMAL_RET min(ISupplierBigDecimal field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> NAMED_INT_RET sqrt(IFunctionInteger<T> getter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> NAMED_LONG_RET sqrt(IFunctionLong<T> getter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> ALIAS_INT_RET sqrt(ISupplierInteger getter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> ALIAS_LONG_RET sqrt(ISupplierLong getter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> NAMED_SUM_LONG_RET sum(IFunctionShort<T> field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> NAMED_SUM_LONG_RET sum(IFunctionInteger<T> field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> NAMED_SUM_LONG_RET sum(IFunctionLong<T> field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> NAMED_BIGDECIMAL_RET sum(IFunctionBigDecimal<T> field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ALIAS_SUM_LONG_RET sum(ISupplierShort field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ALIAS_SUM_LONG_RET sum(ISupplierInteger field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ALIAS_SUM_LONG_RET sum(ISupplierLong field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ALIAS_BIGDECIMAL_RET sum(ISupplierBigDecimal field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> ALIAS_STRING_RET trim(ISupplierString getter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> NAMED_STRING_RET trim(StringFunction<T> getter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> ALIAS_STRING_RET upper(ISupplierString getter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> NAMED_STRING_RET upper(StringFunction<T> getter) {
		// TODO Auto-generated method stub
		return null;
	} 

    
    

	}
