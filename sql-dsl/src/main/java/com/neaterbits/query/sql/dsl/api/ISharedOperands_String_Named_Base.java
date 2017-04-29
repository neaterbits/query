package com.neaterbits.query.sql.dsl.api;

public interface ISharedOperands_String_Named_Base<
		MODEL,
		RESULT,
		
		RET extends ISharedFunction_After<MODEL, RESULT>,
		
		TYPE_RET extends ISharedFunction_Next<MODEL, RESULT, RET>
		
	> extends ISharedOperands_String_Common<MODEL, RESULT, RET, TYPE_RET> {

	/* We need types for all to make sure one can add integer-field and short-fild.
	* But we only need one return-type (for now)
	* 
	*  
	* 
	<T> TYPE_RET plus(Function<T, R> getter);
	<T> TYPE_RET plus(ISharedSubOperandsBuilder<R> builder);
	<T> TYPE_RET plus(R value);
	<T> TYPE_RET plus(Param<R> param);
	*/
	
	<T> TYPE_RET concat(IFunctionString<T> getter);
	//<T> TYPE_RET plus(ISharedSubOperandsBuilder<Short> builder);
	//<T> TYPE_RET plus(Param<Short> param);

}