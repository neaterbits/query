package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.function.Consumer;

public interface IAdhocAndClauses<MODEL, RESULT, ENTITY>
		extends ISharedLogical_And<MODEL, RESULT>,
			IAdhocEndClauseBase<MODEL, RESULT> {

	ISharedCondition_Comparable_Common_Value<MODEL, RESULT, Integer, IAdhocAndClauses<MODEL, RESULT, ENTITY>>
			and(IFunctionInteger<ENTITY> getter);

	ISharedCondition_Comparable_Common_Value<MODEL, RESULT, Long, IAdhocAndClauses<MODEL, RESULT, ENTITY>>
			and(IFunctionLong<ENTITY> getter);

	ISharedCondition_Comparable_Common_Value<MODEL, RESULT, BigDecimal, IAdhocAndClauses<MODEL, RESULT, ENTITY>>
			and(IFunctionBigDecimal<ENTITY> func);
	
	ISharedCondition_Comparable_String_Value<MODEL, RESULT, IAdhocAndClauses<MODEL, RESULT, ENTITY>>
			and(StringFunction<ENTITY> getter);

	//IAdhocAndClauses<MODEL, RESULT, ENTITY> andNest(Consumer<IAdhocNestedOr<MODEL, RESULT, ENTITY>> orBuilder);
	
	IAdhocAndClauses<MODEL, RESULT, ENTITY> andNest(Consumer<IAdhocOrClauses<MODEL, RESULT, ENTITY>> orBuilder);

}
