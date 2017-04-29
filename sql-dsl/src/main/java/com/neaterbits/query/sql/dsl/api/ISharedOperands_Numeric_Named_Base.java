package com.neaterbits.query.sql.dsl.api;


public interface ISharedOperands_Numeric_Named_Base<
		MODEL,
		RESULT,
		
		RET extends ISharedFunction_After<MODEL, RESULT>,
		
		TYPE_RET extends ISharedFunction_Next<MODEL, RESULT, RET>
	
	>  {

	
	<T> TYPE_RET plus(IFunctionShort<T> getter);
	//<T> TYPE_RET plus(ISharedSubOperandsBuilder<Short> builder);
	//<T> TYPE_RET plus(Param<Short> param);
	
	<T> TYPE_RET plus(IFunctionBigDecimal<T> getter);
	
}
