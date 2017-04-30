package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.function.Consumer;

public interface IAdhocLogical_Or<MODEL, RESULT, ENTITY>
		extends ISharedLogical_Or<MODEL, RESULT>,
				IAdhocEnd_Base<MODEL, RESULT> {
		
	ISharedComparison_Comparable_Common_Value<MODEL, RESULT, Integer, IAdhocLogical_Or<MODEL, RESULT, ENTITY>>
			or(IFunctionInteger<ENTITY> getter);
		
	ISharedComparison_Comparable_Common_Value<MODEL, RESULT, Long, IAdhocLogical_Or<MODEL, RESULT, ENTITY>>
			or(IFunctionLong<ENTITY> getter);
		
	ISharedComparison_Comparable_Common_Value<MODEL, RESULT, BigDecimal, IAdhocLogical_Or<MODEL, RESULT, ENTITY>>
			or(IFunctionBigDecimal<ENTITY> func);

	
	ISharedComparison_Comparable_String_Value<MODEL, RESULT, IAdhocLogical_Or<MODEL, RESULT, ENTITY>>
			or(IFunctionString<ENTITY> getter);

    IAdhocFunctions_Initial<
			MODEL,
			RESULT,
			ENTITY,
			IAdhocLogical_Or<MODEL, RESULT, ENTITY>,
			
			ISharedComparison_Comparable_Common_Value<MODEL, RESULT, Integer, IAdhocLogical_Or<MODEL, RESULT, ENTITY>>,
			ISharedComparison_Comparable_Common_Value<MODEL, RESULT, Long, IAdhocLogical_Or<MODEL, RESULT, ENTITY>>,
			ISharedComparison_Comparable_String_Value<MODEL, RESULT, IAdhocLogical_Or<MODEL, RESULT, ENTITY>>
	
    	> or();
    
	
	//IAdhocAndClauses<MODEL, RESULT, ENTITY> orNest(Consumer<IAdhocNestedAnd<MODEL, RESULT, ENTITY>> andBuilder);
	IAdhocLogical_And<MODEL, RESULT, ENTITY> orNest(Consumer<IAdhocLogical_And<MODEL, RESULT, ENTITY>> andBuilder);
}
