package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

final class AndClausesImpl<MODEL, RESULT> extends ClausesImpl<MODEL, RESULT>
			implements AndClausesTable<MODEL, RESULT>,
					   AndClausesAlias<MODEL, RESULT> {

	AndClausesImpl(ClausesImplInitial<MODEL, RESULT> last) {
		super(last);
	}
	
	private <T, RR> ConditionClauseTable<MODEL, RESULT, RR, AndClausesTable<MODEL, RESULT>> andClassImpl(Function<T, RR> getter) {
		return new ConditionClauseImpl<MODEL, RESULT, RR, AndClausesTable<MODEL,RESULT>>(this, makeGetter(getter));
	}

	private <RR> ConditionClauseAlias<MODEL, RESULT, RR, AndClausesAlias<MODEL, RESULT>> andAliasImpl(Supplier<RR> getter) {
		return new ConditionClauseImpl<MODEL, RESULT, RR, AndClausesAlias<MODEL,RESULT>>(this, makeGetter(getter));
	}
	
	
	@Override
	public <T> ConditionClauseTable<MODEL, RESULT, Integer, AndClausesTable<MODEL, RESULT>> and(IntegerFunction<T> getter) {
		return andClassImpl(getter);
	}

	@Override
	public <T> ConditionClauseTable<MODEL, RESULT, Long, AndClausesTable<MODEL, RESULT>> and(LongFunction<T> getter) {
		return andClassImpl(getter);
	}

	@Override
	public <T> StringClause<MODEL, RESULT, AndClausesTable<MODEL, RESULT>> and(StringFunction<T> getter) {
		return new StringClauseImpl<MODEL, RESULT, AndClausesTable<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public ConditionClauseAlias<MODEL, RESULT, Integer, AndClausesAlias<MODEL, RESULT>> and(IntegerSupplier getter) {
		return andAliasImpl(getter);
	}

	@Override
	public ConditionClauseAlias<MODEL, RESULT, Long, AndClausesAlias<MODEL, RESULT>> and(LongSupplier getter) {
		return andAliasImpl(getter);
	}

	@Override
	public StringClause<MODEL, RESULT, AndClausesAlias<MODEL, RESULT>> and(StringSupplier getter) {
		return new StringClauseImpl<MODEL, RESULT, AndClausesAlias<MODEL,RESULT>>(this, makeGetter(getter));
	}
}
