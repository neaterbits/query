package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

final class AndClausesImpl<MODEL, RESULT> extends ClausesImpl<MODEL, RESULT>
			implements AndClausesTable<MODEL, RESULT>,
					   AndClausesAlias<MODEL, RESULT> {

	AndClausesImpl(WhereClauseBuilderImpl<MODEL, RESULT> last) {
		super(last);
	}

	@Override
	public <T, RR> ConditionClauseTable<MODEL, RESULT, RR, AndClausesTable<MODEL, RESULT>> and(Function<T, RR> getter) {
		return new ConditionClauseImpl<MODEL, RESULT, RR, AndClausesTable<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public <T> StringClause<MODEL, RESULT, AndClausesTable<MODEL, RESULT>> and(StringFunction<T> getter) {
		return new StringClauseImpl<MODEL, RESULT, AndClausesTable<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public <RR> ConditionClauseAlias<MODEL, RESULT, RR, AndClausesAlias<MODEL, RESULT>> and(Supplier<RR> getter) {
		return new ConditionClauseImpl<MODEL, RESULT, RR, AndClausesAlias<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public StringClause<MODEL, RESULT, AndClausesAlias<MODEL, RESULT>> and(StringSupplier getter) {
		return new StringClauseImpl<MODEL, RESULT, AndClausesAlias<MODEL,RESULT>>(this, makeGetter(getter));
	}
}
