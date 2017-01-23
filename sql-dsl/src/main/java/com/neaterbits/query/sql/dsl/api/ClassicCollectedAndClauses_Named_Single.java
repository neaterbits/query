package com.neaterbits.query.sql.dsl.api;


final class ClassicCollectedAndClauses_Named_Single<MODEL, RESULT> extends CollectedClauses<MODEL, RESULT>
	implements IClassicSingleAndClausesNamed<MODEL, RESULT> {

	ClassicCollectedAndClauses_Named_Single(CollectedClauses_Initial<MODEL, RESULT> last) {
		super(last, ConditionsType.AND);
	}

	@Override
	public ISharedClauseConditionNamed<MODEL, RESULT, Integer, IClassicSingleAndClausesNamed<MODEL, RESULT>> and(IFunctionInteger<RESULT> getter) {
		return new CollectedClause_Condition<MODEL, RESULT, Integer, IClassicSingleAndClausesNamed<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public ISharedClauseComparableStringAll<MODEL, RESULT, IClassicSingleAndClausesNamed<MODEL, RESULT>> and(StringFunction<RESULT> getter) {
		return new CollectedClause_String<MODEL, RESULT, IClassicSingleAndClausesNamed<MODEL,RESULT>>(this, makeGetter(getter));
	}
}
