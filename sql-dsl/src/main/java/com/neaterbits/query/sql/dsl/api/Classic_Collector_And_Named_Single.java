package com.neaterbits.query.sql.dsl.api;


final class Classic_Collector_And_Named_Single<
		MODEL, 
		RESULT>
		extends Classic_Collector_And<MODEL, RESULT, Void>
	implements IClassicSingleAndClausesNamed<MODEL, RESULT> {

	Classic_Collector_And_Named_Single(ClassicSingleNamedResult<MODEL, RESULT> last) {
		super(last);
	}

	@Override
	public ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, IClassicSingleAndClausesNamed<MODEL, RESULT>> and(IFunctionInteger<RESULT> getter) {
		return new Collector_Condition_Comparative<MODEL, RESULT, Integer, IClassicSingleAndClausesNamed<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicSingleAndClausesNamed<MODEL, RESULT>> and(StringFunction<RESULT> getter) {
		return new Collector_Condition_String<MODEL, RESULT, IClassicSingleAndClausesNamed<MODEL,RESULT>>(this, makeGetter(getter));
	}
}
