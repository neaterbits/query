package com.neaterbits.query.sql.dsl.api;


final class Classic_Collector_And_Named_Single<MODEL, RESULT> extends Collector_Conditions<MODEL, RESULT>
	implements IClassicSingleAndClausesNamed<MODEL, RESULT> {

	Classic_Collector_And_Named_Single(Collector_Conditions_Initial<MODEL, RESULT> last) {
		super(last, ConditionsType.AND);
	}

	@Override
	public ISharedCondition_Equality_Named<MODEL, RESULT, Integer, IClassicSingleAndClausesNamed<MODEL, RESULT>> and(IFunctionInteger<RESULT> getter) {
		return new Collector_Condition_Equality<MODEL, RESULT, Integer, IClassicSingleAndClausesNamed<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicSingleAndClausesNamed<MODEL, RESULT>> and(StringFunction<RESULT> getter) {
		return new Collector_Condition_String<MODEL, RESULT, IClassicSingleAndClausesNamed<MODEL,RESULT>>(this, makeGetter(getter));
	}
}
