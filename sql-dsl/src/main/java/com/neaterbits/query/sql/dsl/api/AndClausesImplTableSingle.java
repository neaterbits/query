package com.neaterbits.query.sql.dsl.api;


final class AndClausesImplTableSingle<MODEL, RESULT> extends ClausesImpl<MODEL, RESULT>
	implements AndClausesTableSingle<MODEL, RESULT> {

	AndClausesImplTableSingle(ClausesImplInitial<MODEL, RESULT> last) {
		super(last);
	}

	@Override
	public ISharedConditionClauseTable<MODEL, RESULT, Integer, AndClausesTableSingle<MODEL, RESULT>> and(IntegerFunction<RESULT> getter) {
		return new ConditionClauseImpl<MODEL, RESULT, Integer, AndClausesTableSingle<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public ISharedStringClause<MODEL, RESULT, AndClausesTableSingle<MODEL, RESULT>> and(StringFunction<RESULT> getter) {
		return new StringClauseImpl<MODEL, RESULT, AndClausesTableSingle<MODEL,RESULT>>(this, makeGetter(getter));
	}
}
