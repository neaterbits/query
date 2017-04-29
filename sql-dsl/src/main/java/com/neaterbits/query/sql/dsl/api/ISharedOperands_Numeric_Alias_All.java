package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

public interface ISharedOperands_Numeric_Alias_All<
		MODEL,
		RESULT,
		
		//R extends Comparable<R>,
		
		RET extends ISharedFunction_After<MODEL, RESULT>,
		
		TYPE_RET extends ISharedFunction_Next<MODEL, RESULT, RET>
	
	> extends ISharedOperands_Numeric_Alias_Base<MODEL, RESULT, RET, TYPE_RET>,
			  ISharedOperands_Numeric_Common<MODEL, RESULT, RET, TYPE_RET> {
		
	TYPE_RET plusOf(ISharedSubOperandsFunction_Alias<MODEL, RESULT, BigDecimal> builder);
		
}
