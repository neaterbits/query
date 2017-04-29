package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

public interface ISharedOperands_Numeric_Named_All<
		MODEL,
		RESULT,
		
		RET extends ISharedFunction_After<MODEL, RESULT>,
		
		TYPE_RET extends ISharedFunction_Next<MODEL, RESULT, RET>
		
	> extends ISharedOperands_Numeric_Named_Base<MODEL, RESULT, RET, TYPE_RET> {

		TYPE_RET plusOf(ISharedSubOperandsFunction_Named<MODEL, RESULT, BigDecimal> builder);

}
