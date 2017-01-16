package com.neaterbits.query.sql.dsl.api;

public interface IAdhocOrClauses<MODEL, RESULT, ENTITY>
		extends ISharedOrClauses<MODEL, RESULT>,
				IAdhocEndClauseBase<MODEL, RESULT> {
		
	ISharedClauseComparableCommonValue<MODEL, RESULT, Integer, IAdhocOrClauses<MODEL, RESULT, ENTITY>>
			or(IFunctionInteger<ENTITY> getter);
		
	ISharedClauseComparableCommonValue<MODEL, RESULT, Long, IAdhocOrClauses<MODEL, RESULT, ENTITY>>
			or(IFunctionLong<ENTITY> getter);
		
	ISharedClauseComparableStringValue<MODEL, RESULT, IAdhocOrClauses<MODEL, RESULT, ENTITY>>
			or(StringFunction<ENTITY> getter);
		
}
