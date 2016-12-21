package com.neaterbits.query.sql.dsl.api;

final class OrClausesImplTableSingle<MODEL, RESULT> extends ClausesImpl<MODEL, RESULT>
		implements IClassicSingleOrClausesTable<MODEL, RESULT> {
	
	OrClausesImplTableSingle(ClausesImplInitial<MODEL, RESULT> last) {
		super(last);
	}

	@Override
	public ISharedClauseConditionAll<MODEL, RESULT, Integer, IClassicSingleOrClausesTable<MODEL, RESULT>> or(IFunctionInteger<RESULT> getter) {
		return new ConditionClauseImpl<MODEL, RESULT, Integer, IClassicSingleOrClausesTable<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public ISharedClauseComparativeStringAll<MODEL, RESULT, IClassicSingleOrClausesTable<MODEL, RESULT>> or(StringFunction<RESULT> getter) {
		return new StringClauseImpl<MODEL, RESULT, IClassicSingleOrClausesTable<MODEL,RESULT>>(this, makeGetter(getter));
	}
}
