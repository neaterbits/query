package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

public interface ISharedOperands_Numeric_Common<
	MODEL,
	RESULT,
	
	RET extends ISharedFunction_After<MODEL, RESULT>,
	
	TYPE_RET extends ISharedFunction_Next<MODEL, RESULT, RET>
	
	>  {

	<T> TYPE_RET plus(short value);
	
	TYPE_RET plus(BigDecimal value);
	

}
