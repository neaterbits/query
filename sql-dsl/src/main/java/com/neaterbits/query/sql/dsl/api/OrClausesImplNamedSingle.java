package com.neaterbits.query.sql.dsl.api;

final class OrClausesImplNamedSingle<MODEL, RESULT> extends ClausesImpl<MODEL, RESULT>
		implements IClassicSingleOrClausesNamed<MODEL, RESULT> {
	
	OrClausesImplNamedSingle(ClausesImplInitial<MODEL, RESULT> last) {
		super(last);
	}

	@Override
	public ISharedClauseConditionAll<MODEL, RESULT, Integer, IClassicSingleOrClausesNamed<MODEL, RESULT>> or(IFunctionInteger<RESULT> getter) {
		return new ConditionClauseImpl<MODEL, RESULT, Integer, IClassicSingleOrClausesNamed<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public ISharedClauseComparableStringAll<MODEL, RESULT, IClassicSingleOrClausesNamed<MODEL, RESULT>> or(StringFunction<RESULT> getter) {
		return new StringClauseImpl<MODEL, RESULT, IClassicSingleOrClausesNamed<MODEL,RESULT>>(this, makeGetter(getter));
	}
}
