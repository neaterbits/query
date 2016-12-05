package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

final class AndClausesImplTableSingle<MODEL, RESULT> extends ClausesImpl<MODEL, RESULT>
	implements AndClausesTableSingle<MODEL, RESULT> {

	AndClausesImplTableSingle(ClausesImplInitial<MODEL, RESULT> last) {
		super(last);
	}

	@Override
	public <RR> ConditionClauseTable<MODEL, RESULT, RR, AndClausesTableSingle<MODEL, RESULT>> and(Function<RESULT, RR> getter) {
		return new ConditionClauseImpl<MODEL, RESULT, RR, AndClausesTableSingle<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public StringClause<MODEL, RESULT, AndClausesTableSingle<MODEL, RESULT>> and(StringFunction<RESULT> getter) {
		return new StringClauseImpl<MODEL, RESULT, AndClausesTableSingle<MODEL,RESULT>>(this, makeGetter(getter));
	}
}
