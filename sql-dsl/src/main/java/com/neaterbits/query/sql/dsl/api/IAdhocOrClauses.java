package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.function.Consumer;

public interface IAdhocOrClauses<MODEL, RESULT, ENTITY>
		extends ISharedOrClauses<MODEL, RESULT>,
				IAdhocEndClauseBase<MODEL, RESULT> {
		
	ISharedClauseComparableCommonValue<MODEL, RESULT, Integer, IAdhocOrClauses<MODEL, RESULT, ENTITY>>
			or(IFunctionInteger<ENTITY> getter);
		
	ISharedClauseComparableCommonValue<MODEL, RESULT, Long, IAdhocOrClauses<MODEL, RESULT, ENTITY>>
			or(IFunctionLong<ENTITY> getter);
		
	ISharedClauseComparableCommonValue<MODEL, RESULT, BigDecimal, IAdhocOrClauses<MODEL, RESULT, ENTITY>>
			or(IFunctionBigDecimal<ENTITY> func);

	
	ISharedClauseComparableStringValue<MODEL, RESULT, IAdhocOrClauses<MODEL, RESULT, ENTITY>>
			or(StringFunction<ENTITY> getter);

	//IAdhocAndClauses<MODEL, RESULT, ENTITY> orNest(Consumer<IAdhocNestedAnd<MODEL, RESULT, ENTITY>> andBuilder);
	IAdhocAndClauses<MODEL, RESULT, ENTITY> orNest(Consumer<IAdhocAndClauses<MODEL, RESULT, ENTITY>> andBuilder);
}
