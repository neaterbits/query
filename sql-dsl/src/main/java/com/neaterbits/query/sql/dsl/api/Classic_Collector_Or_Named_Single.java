package com.neaterbits.query.sql.dsl.api;

final class Classic_Collector_Or_Named_Single<MODEL, RESULT> extends Collector_Conditions<MODEL, RESULT>
		implements IClassicSingleOrClausesNamed<MODEL, RESULT> {
	
	Classic_Collector_Or_Named_Single(Collector_Conditions_Initial<MODEL, RESULT> last) {
		super(last, ConditionsType.OR);
	}

	@Override
	public ISharedCondition_Equality_All<MODEL, RESULT, Integer, IClassicSingleOrClausesNamed<MODEL, RESULT>> or(IFunctionInteger<RESULT> getter) {
		return new Collector_Condition_Equality<MODEL, RESULT, Integer, IClassicSingleOrClausesNamed<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicSingleOrClausesNamed<MODEL, RESULT>> or(StringFunction<RESULT> getter) {
		return new Collector_Condition_String<MODEL, RESULT, IClassicSingleOrClausesNamed<MODEL,RESULT>>(this, makeGetter(getter));
	}
}
