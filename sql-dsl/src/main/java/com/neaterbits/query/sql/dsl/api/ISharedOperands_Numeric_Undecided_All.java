package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

public interface ISharedOperands_Numeric_Undecided_All<
	MODEL,
	RESULT,
	
	RET extends ISharedFunction_After<MODEL, RESULT>,
	
	NAMED_TYPE_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
	ALIAS_TYPE_RET extends ISharedFunction_Next<MODEL, RESULT, RET>,
	UNDECIDED_TYPE_RET extends ISharedFunction_Next<MODEL, RESULT, RET>
	
	> extends ISharedOperands_Numeric_Named_Base<MODEL, RESULT, RET, NAMED_TYPE_RET>,
			  ISharedOperands_Numeric_Alias_Base<MODEL, RESULT, RET, ALIAS_TYPE_RET>,
			  ISharedOperands_Numeric_Common<MODEL, RESULT, RET, UNDECIDED_TYPE_RET> {
	
	UNDECIDED_TYPE_RET plusOf(ISharedSubOperandsFunction_Undecided<MODEL, RESULT, BigDecimal> builder);

}
