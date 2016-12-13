package com.neaterbits.query.sql.dsl.api;

final class OrClausesImplTableSingle<MODEL, RESULT> extends ClausesImpl<MODEL, RESULT>
		implements OrClausesTableSingle<MODEL, RESULT> {
	
	OrClausesImplTableSingle(ClausesImplInitial<MODEL, RESULT> last) {
		super(last);
	}

	@Override
	public ConditionClause<MODEL, RESULT, Integer, OrClausesTableSingle<MODEL, RESULT>> or(IntegerFunction<RESULT> getter) {
		return new ConditionClauseImpl<MODEL, RESULT, Integer, OrClausesTableSingle<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public StringClause<MODEL, RESULT, OrClausesTableSingle<MODEL, RESULT>> or(StringFunction<RESULT> getter) {
		return new StringClauseImpl<MODEL, RESULT, OrClausesTableSingle<MODEL,RESULT>>(this, makeGetter(getter));
	}
}
