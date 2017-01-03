package com.neaterbits.query.sql.dsl.api;

final class ClassicCollectedOrClauses_Named_Single<MODEL, RESULT> extends CollectedClauses<MODEL, RESULT>
		implements IClassicSingleOrClausesNamed<MODEL, RESULT> {
	
	ClassicCollectedOrClauses_Named_Single(CollectedClauses_Initial<MODEL, RESULT> last) {
		super(last);
	}

	@Override
	public ISharedClauseConditionAll<MODEL, RESULT, Integer, IClassicSingleOrClausesNamed<MODEL, RESULT>> or(IFunctionInteger<RESULT> getter) {
		return new CollectedClause_Condition<MODEL, RESULT, Integer, IClassicSingleOrClausesNamed<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public ISharedClauseComparableStringAll<MODEL, RESULT, IClassicSingleOrClausesNamed<MODEL, RESULT>> or(StringFunction<RESULT> getter) {
		return new CollectedClause_String<MODEL, RESULT, IClassicSingleOrClausesNamed<MODEL,RESULT>>(this, makeGetter(getter));
	}
}
