package com.neaterbits.query.sql.dsl.api;


final class AndClausesImplNamedSingle<MODEL, RESULT> extends ClausesImpl<MODEL, RESULT>
	implements IClassicSingleAndClausesNamed<MODEL, RESULT> {

	AndClausesImplNamedSingle(ClausesImplInitial<MODEL, RESULT> last) {
		super(last);
	}

	@Override
	public ISharedClauseConditionNamed<MODEL, RESULT, Integer, IClassicSingleAndClausesNamed<MODEL, RESULT>> and(IFunctionInteger<RESULT> getter) {
		return new ConditionClauseImpl<MODEL, RESULT, Integer, IClassicSingleAndClausesNamed<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public ISharedClauseComparableStringAll<MODEL, RESULT, IClassicSingleAndClausesNamed<MODEL, RESULT>> and(StringFunction<RESULT> getter) {
		return new StringClauseImpl<MODEL, RESULT, IClassicSingleAndClausesNamed<MODEL,RESULT>>(this, makeGetter(getter));
	}
}
