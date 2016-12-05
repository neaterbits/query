package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

final class OrClausesImplTableSingle<MODEL, RESULT> extends ClausesImpl<MODEL, RESULT>
		implements OrClausesTableSingle<MODEL, RESULT> {
	
	OrClausesImplTableSingle(ClausesImplInitial<MODEL, RESULT> last) {
		super(last);
	}

	@Override
	public <RR> ConditionClause<MODEL, RESULT, RR, OrClausesTableSingle<MODEL, RESULT>> or(Function<RESULT, RR> getter) {
		return new ConditionClauseImpl<MODEL, RESULT, RR, OrClausesTableSingle<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public StringClause<MODEL, RESULT, OrClausesTableSingle<MODEL, RESULT>> or(StringFunction<RESULT> getter) {
		return new StringClauseImpl<MODEL, RESULT, OrClausesTableSingle<MODEL,RESULT>>(this, makeGetter(getter));
	}
}
