package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

public interface ISharedOperands_Undecided< 
		MODEL,
		RESULT,
		
		R extends Comparable<R>,
		
		NAMED_RET extends ISharedFunction_After<MODEL, RESULT>,
		ALIAS_RET extends ISharedFunction_After<MODEL, RESULT>,
		UNDECIDED_RET extends ISharedFunction_After<MODEL, RESULT>,
		
		NAMED_TYPE_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		ALIAS_TYPE_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>
		
	
	> extends
			ISharedOperands_
			ISharedOperands_Alias<MODEL, RESULT, R, RET, ALIAS_RET> {
}
