package com.neaterbits.query.sql.dsl.api;

public interface ISharedOperands_String_Alias<

		MODEL,
		RESULT,
		
		R extends Comparable<R>,
		
		RET extends ISharedFunction_After<MODEL, RESULT>,
		
		TYPE_RET extends ISharedFunction_Next<MODEL, RESULT, RET>
	
	> {
	
	TYPE_RET concat(ISupplierString getter);
	//<T> TYPE_RET plus(ISharedSubOperandsBuilder<Short> builder);
	TYPE_RET concat(String value);
	
	//<T> TYPE_RET plus(Param<Short> param); {

}
