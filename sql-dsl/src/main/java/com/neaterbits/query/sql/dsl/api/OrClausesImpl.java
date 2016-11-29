package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

final class OrClausesImpl<MODEL, RESULT> extends ClausesImpl<MODEL, RESULT>
			implements OrClausesTable<MODEL, RESULT>, OrClausesAlias<MODEL, RESULT>{

	OrClausesImpl(WhereClauseBuilderImpl<MODEL, RESULT> last) {
		super(last);
	}

	@Override
	public <T, RR> ConditionClause<MODEL, RESULT, RR, OrClausesTable<MODEL, RESULT>> or(Function<T, RR> getter) {
		return new ConditionClauseImpl<MODEL, RESULT, RR, OrClausesTable<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public <T> StringClause<MODEL, RESULT, OrClausesTable<MODEL, RESULT>> or(StringFunction<T> getter) {
		return new StringClauseImpl<MODEL, RESULT, OrClausesTable<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public <RR> ConditionClause<MODEL, RESULT, RR, OrClausesAlias<MODEL, RESULT>> or(Supplier<RR> getter) {
		return new ConditionClauseImpl<MODEL, RESULT, RR, OrClausesAlias<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public StringClause<MODEL, RESULT, OrClausesAlias<MODEL, RESULT>> or(StringSupplier getter) {
		return new StringClauseImpl<MODEL, RESULT, OrClausesAlias<MODEL,RESULT>>(this, makeGetter(getter));
	}
}
