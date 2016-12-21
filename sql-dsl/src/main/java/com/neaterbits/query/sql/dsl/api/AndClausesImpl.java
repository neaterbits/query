package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

final class AndClausesImpl<MODEL, RESULT> extends ClausesImpl<MODEL, RESULT>
			implements AndClausesTable<MODEL, RESULT>,
					   IClassicAndClausesAlias<MODEL, RESULT> {

	AndClausesImpl(ClausesImplInitial<MODEL, RESULT> last) {
		super(last);
	}
	
	private <T, RR> ISharedConditionClauseTable<MODEL, RESULT, RR, AndClausesTable<MODEL, RESULT>> andClassImpl(Function<T, RR> getter) {
		return new ConditionClauseImpl<MODEL, RESULT, RR, AndClausesTable<MODEL,RESULT>>(this, makeGetter(getter));
	}

	private <RR> ISharedConditionClauseAlias<MODEL, RESULT, RR, IClassicAndClausesAlias<MODEL, RESULT>> andAliasImpl(Supplier<RR> getter) {
		return new ConditionClauseImpl<MODEL, RESULT, RR, IClassicAndClausesAlias<MODEL,RESULT>>(this, makeGetter(getter));
	}
	
	
	@Override
	public <T> ISharedConditionClauseTable<MODEL, RESULT, Integer, AndClausesTable<MODEL, RESULT>> and(IntegerFunction<T> getter) {
		return andClassImpl(getter);
	}

	@Override
	public <T> ISharedConditionClauseTable<MODEL, RESULT, Long, AndClausesTable<MODEL, RESULT>> and(LongFunction<T> getter) {
		return andClassImpl(getter);
	}

	@Override
	public <T> ISharedStringClause<MODEL, RESULT, AndClausesTable<MODEL, RESULT>> and(StringFunction<T> getter) {
		return new StringClauseImpl<MODEL, RESULT, AndClausesTable<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public ISharedConditionClauseAlias<MODEL, RESULT, Integer, IClassicAndClausesAlias<MODEL, RESULT>> and(IntegerSupplier getter) {
		return andAliasImpl(getter);
	}

	@Override
	public ISharedConditionClauseAlias<MODEL, RESULT, Long, IClassicAndClausesAlias<MODEL, RESULT>> and(LongSupplier getter) {
		return andAliasImpl(getter);
	}

	@Override
	public ISharedStringClause<MODEL, RESULT, IClassicAndClausesAlias<MODEL, RESULT>> and(StringSupplier getter) {
		return new StringClauseImpl<MODEL, RESULT, IClassicAndClausesAlias<MODEL,RESULT>>(this, makeGetter(getter));
	}
}
