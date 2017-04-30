package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.function.Consumer;

public interface IAdhocLogical_And<MODEL, RESULT, ENTITY>
		extends ISharedLogical_And<MODEL, RESULT>,
			IAdhocEnd_Base<MODEL, RESULT> {

	ISharedComparison_Comparable_Common_Value<MODEL, RESULT, Integer, IAdhocLogical_And<MODEL, RESULT, ENTITY>>
			and(IFunctionInteger<ENTITY> getter);

	ISharedComparison_Comparable_Common_Value<MODEL, RESULT, Long, IAdhocLogical_And<MODEL, RESULT, ENTITY>>
			and(IFunctionLong<ENTITY> getter);

	ISharedComparison_Comparable_Common_Value<MODEL, RESULT, BigDecimal, IAdhocLogical_And<MODEL, RESULT, ENTITY>>
			and(IFunctionBigDecimal<ENTITY> func);
	
	ISharedComparison_Comparable_String_Value<MODEL, RESULT, IAdhocLogical_And<MODEL, RESULT, ENTITY>>
			and(IFunctionString<ENTITY> getter);

    IAdhocFunctions_Initial<
    		MODEL,
    		RESULT,
    		ENTITY,
    		IAdhocLogical_And<MODEL, RESULT, ENTITY>,
    		
    		ISharedComparison_Comparable_Common_Value<MODEL, RESULT, Integer, IAdhocLogical_And<MODEL, RESULT, ENTITY>>,
    		ISharedComparison_Comparable_Common_Value<MODEL, RESULT, Long, IAdhocLogical_And<MODEL, RESULT, ENTITY>>,
    		ISharedComparison_Comparable_String_Value<MODEL, RESULT, IAdhocLogical_And<MODEL, RESULT, ENTITY>>
    		
    		> and();
	
	IAdhocLogical_And<MODEL, RESULT, ENTITY> andNest(Consumer<IAdhocLogical_Or<MODEL, RESULT, ENTITY>> orBuilder);

}
