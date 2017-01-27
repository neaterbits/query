package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.function.Consumer;

public interface IAdhocOrClauses<MODEL, RESULT, ENTITY>
		extends ISharedLogical_Or<MODEL, RESULT>,
				IAdhocEndClauseBase<MODEL, RESULT> {
		
	ISharedCondition_Comparable_Common_Value<MODEL, RESULT, Integer, IAdhocOrClauses<MODEL, RESULT, ENTITY>>
			or(IFunctionInteger<ENTITY> getter);
		
	ISharedCondition_Comparable_Common_Value<MODEL, RESULT, Long, IAdhocOrClauses<MODEL, RESULT, ENTITY>>
			or(IFunctionLong<ENTITY> getter);
		
	ISharedCondition_Comparable_Common_Value<MODEL, RESULT, BigDecimal, IAdhocOrClauses<MODEL, RESULT, ENTITY>>
			or(IFunctionBigDecimal<ENTITY> func);

	
	ISharedCondition_Comparable_String_Value<MODEL, RESULT, IAdhocOrClauses<MODEL, RESULT, ENTITY>>
			or(StringFunction<ENTITY> getter);

	//IAdhocAndClauses<MODEL, RESULT, ENTITY> orNest(Consumer<IAdhocNestedAnd<MODEL, RESULT, ENTITY>> andBuilder);
	IAdhocAndClauses<MODEL, RESULT, ENTITY> orNest(Consumer<IAdhocAndClauses<MODEL, RESULT, ENTITY>> andBuilder);
}
