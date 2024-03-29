package com.neaterbits.query.sql.dsl.api;


public interface ISharedOperands_Numeric_Alias_Base<
	MODEL,
	RESULT,
	
	//R extends Comparable<R>,
	
	RET extends ISharedFunction_After<MODEL, RESULT>,
	
	TYPE_RET extends ISharedFunction_Next<MODEL, RESULT, RET>
	
	> {

	<T> TYPE_RET plus(ISupplierShort getter);
	
	<T> TYPE_RET plus(ISupplierBigDecimal getter);

}
