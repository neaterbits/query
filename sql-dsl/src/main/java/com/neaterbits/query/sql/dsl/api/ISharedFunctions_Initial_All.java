package com.neaterbits.query.sql.dsl.api;

import java.math.BigInteger;

@Deprecated // in use?
public interface ISharedFunctions_Initial_All<
		MODEL,
		RESULT,
		
		RET extends ISharedLogical_Base<MODEL, RESULT>,
		
		LENGTH_RET extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Integer, RET>,

		
		BYTE_RET extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Byte, RET>,
		SHORT_RET extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Short, RET>,
		INTEGER_RET extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Integer, RET>,
		LONG_RET extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Long, RET>,
		BIGINTEGER_RET extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, BigInteger, RET>,
		FLOAT_RET extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Float, RET>,
		DOUBLE_RET extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Double, RET>,
		BIGDECIMAL_RET extends ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Double, RET>,
		STRING_RET extends ISharedCondition_Comparable_String_Base<MODEL, RESULT, RET>>

	extends 

		ISharedFunctions_Transform_Initial_Named<MODEL, RESULT, RET, LENGTH_RET, BYTE_RET, SHORT_RET, INTEGER_RET, LONG_RET, BIGINTEGER_RET, FLOAT_RET, DOUBLE_RET, BIGDECIMAL_RET, STRING_RET>,
		ISharedFunctions_Transform_Initial_Alias<MODEL, RESULT, RET, LENGTH_RET, BYTE_RET, SHORT_RET, INTEGER_RET, LONG_RET, BIGINTEGER_RET, FLOAT_RET, DOUBLE_RET, BIGDECIMAL_RET, STRING_RET>

{

}
