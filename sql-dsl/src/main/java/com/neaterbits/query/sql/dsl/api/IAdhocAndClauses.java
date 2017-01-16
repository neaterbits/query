package com.neaterbits.query.sql.dsl.api;

public interface IAdhocAndClauses<MODEL, RESULT, ENTITY>
		extends ISharedAndClauses<MODEL, RESULT>,
			IAdhocEndClauseBase<MODEL, RESULT> {

	ISharedClauseComparableCommonValue<MODEL, RESULT, Integer, IAdhocAndClauses<MODEL, RESULT, ENTITY>>
			and(IFunctionInteger<ENTITY> getter);

	ISharedClauseComparableCommonValue<MODEL, RESULT, Long, IAdhocAndClauses<MODEL, RESULT, ENTITY>>
			and(IFunctionLong<ENTITY> getter);

	ISharedClauseComparableStringValue<MODEL, RESULT, IAdhocAndClauses<MODEL, RESULT, ENTITY>>
			and(StringFunction<ENTITY> getter);
	
}
