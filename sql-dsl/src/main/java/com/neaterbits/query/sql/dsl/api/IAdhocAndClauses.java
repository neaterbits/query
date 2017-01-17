package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.function.Consumer;

public interface IAdhocAndClauses<MODEL, RESULT, ENTITY>
		extends ISharedAndClauses<MODEL, RESULT>,
			IAdhocEndClauseBase<MODEL, RESULT> {

	ISharedClauseComparableCommonValue<MODEL, RESULT, Integer, IAdhocAndClauses<MODEL, RESULT, ENTITY>>
			and(IFunctionInteger<ENTITY> getter);

	ISharedClauseComparableCommonValue<MODEL, RESULT, Long, IAdhocAndClauses<MODEL, RESULT, ENTITY>>
			and(IFunctionLong<ENTITY> getter);

	ISharedClauseComparableCommonValue<MODEL, RESULT, BigDecimal, IAdhocAndClauses<MODEL, RESULT, ENTITY>>
			and(IFunctionBigDecimal<ENTITY> func);
	
	ISharedClauseComparableStringValue<MODEL, RESULT, IAdhocAndClauses<MODEL, RESULT, ENTITY>>
			and(StringFunction<ENTITY> getter);

	//IAdhocAndClauses<MODEL, RESULT, ENTITY> andNest(Consumer<IAdhocNestedOr<MODEL, RESULT, ENTITY>> orBuilder);
	
	IAdhocAndClauses<MODEL, RESULT, ENTITY> andNest(Consumer<IAdhocOrClauses<MODEL, RESULT, ENTITY>> orBuilder);

}
