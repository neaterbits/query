package com.neaterbits.query.sql.dsl.api;

final class Classic_Collector_Or_Named_Single<MODEL, RESULT>

		extends Classic_Collector_Or<MODEL, RESULT, Classic_Collector_And_Named_Single<MODEL, RESULT>, Classic_Collector_Or_Named_Single<MODEL, RESULT>>

		implements IClassicSingleOrClausesNamed<MODEL, RESULT> {
	
	Classic_Collector_Or_Named_Single(ClassicSingleNamedResult<MODEL, RESULT> last) {
		super(last);
	}

	@Override
	public ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, IClassicSingleOrClausesNamed<MODEL, RESULT>> or(IFunctionInteger<RESULT> getter) {
		return new Collector_Condition_Comparative<MODEL, RESULT, Integer, IClassicSingleOrClausesNamed<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicSingleOrClausesNamed<MODEL, RESULT>> or(StringFunction<RESULT> getter) {
		return new Collector_Condition_String<MODEL, RESULT, IClassicSingleOrClausesNamed<MODEL,RESULT>>(this, makeGetter(getter));
	}
}
