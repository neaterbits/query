package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

public interface ISharedLogical_Where_Named_Base<
					MODEL,
					RESULT,
					CONDITION_CLAUSE extends ISharedLogical_Base<MODEL, RESULT>,
					
					INTEGER_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Integer, CONDITION_CLAUSE>,
					LONG_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Long, CONDITION_CLAUSE>,
					BIGDECIMAL_CLAUSE extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, BigDecimal, CONDITION_CLAUSE>,
					STRING_CLAUSE  extends ISharedCondition_Comparable_String_Base<MODEL, RESULT, CONDITION_CLAUSE>>

	extends ISharedLogical_Where<MODEL, RESULT> {

	<T> INTEGER_CLAUSE where(IFunctionInteger<T> func);

	<T> LONG_CLAUSE where(IFunctionLong<T> func);

	<T> BIGDECIMAL_CLAUSE where(IFunctionBigDecimal<T> func);

	<T> STRING_CLAUSE where(StringFunction<T> func);
    

}
