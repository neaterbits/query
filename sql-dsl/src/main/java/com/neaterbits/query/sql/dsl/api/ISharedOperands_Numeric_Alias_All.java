package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

public interface ISharedOperands_Numeric_Alias_All<
		MODEL,
		RESULT,
		
		//R extends Comparable<R>,
		
		RET extends ISharedFunction_After<MODEL, RESULT>,
		
		ALIAS_TYPE_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
		COMMON_TYPE_RET extends ISharedFunction_Next<MODEL, RESULT, RET>
	
	> extends ISharedOperands_Numeric_Alias_Base<MODEL, RESULT, RET, ALIAS_TYPE_RET>,
			  ISharedOperands_Numeric_Common<MODEL, RESULT, RET, COMMON_TYPE_RET> {
		
	ALIAS_TYPE_RET plusOf(ISharedSubOperandsFunction_Alias<MODEL, RESULT, BigDecimal> builder);
		
}
