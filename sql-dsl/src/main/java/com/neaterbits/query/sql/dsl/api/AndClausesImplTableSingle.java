package com.neaterbits.query.sql.dsl.api;


final class AndClausesImplTableSingle<MODEL, RESULT> extends ClausesImpl<MODEL, RESULT>
	implements IClassicSingleAndClausesTable<MODEL, RESULT> {

	AndClausesImplTableSingle(ClausesImplInitial<MODEL, RESULT> last) {
		super(last);
	}

	@Override
	public ISharedClauseConditionTable<MODEL, RESULT, Integer, IClassicSingleAndClausesTable<MODEL, RESULT>> and(IFunctionInteger<RESULT> getter) {
		return new ConditionClauseImpl<MODEL, RESULT, Integer, IClassicSingleAndClausesTable<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public ISharedClauseComparativeStringAll<MODEL, RESULT, IClassicSingleAndClausesTable<MODEL, RESULT>> and(StringFunction<RESULT> getter) {
		return new StringClauseImpl<MODEL, RESULT, IClassicSingleAndClausesTable<MODEL,RESULT>>(this, makeGetter(getter));
	}
}
