package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

final class OrClausesImpl<MODEL, RESULT> extends ClausesImpl<MODEL, RESULT>
			implements OrClausesTable<MODEL, RESULT>, OrClausesAlias<MODEL, RESULT>{

	OrClausesImpl(ClausesImplInitial<MODEL, RESULT> last) {
		super(last);
	}

	private <T, RR> ConditionClause<MODEL, RESULT, RR, OrClausesTable<MODEL, RESULT>> orClassImpl(Function<T, RR> getter) {
		return new ConditionClauseImpl<MODEL, RESULT, RR, OrClausesTable<MODEL,RESULT>>(this, makeGetter(getter));
	}
	
	private <RR> ConditionClause<MODEL, RESULT, RR, OrClausesAlias<MODEL, RESULT>> orAliasImpl(Supplier<RR> getter) {
		return new ConditionClauseImpl<MODEL, RESULT, RR, OrClausesAlias<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public <T> ConditionClause<MODEL, RESULT, Integer, OrClausesTable<MODEL, RESULT>> or(IntegerFunction<T> getter) {
		return orClassImpl(getter);
	}

	@Override
	public <T> ConditionClause<MODEL, RESULT, Long, OrClausesTable<MODEL, RESULT>> or(LongFunction<T> getter) {
		return orClassImpl(getter);
	}

	@Override
	public <T> StringClause<MODEL, RESULT, OrClausesTable<MODEL, RESULT>> or(StringFunction<T> getter) {
		return new StringClauseImpl<MODEL, RESULT, OrClausesTable<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public ConditionClause<MODEL, RESULT, Integer, OrClausesAlias<MODEL, RESULT>> or(IntegerSupplier getter) {
		return orAliasImpl(getter);
	}

	@Override
	public ConditionClause<MODEL, RESULT, Long, OrClausesAlias<MODEL, RESULT>> or(LongSupplier getter) {
		return orAliasImpl(getter);
	}

	@Override
	public StringClause<MODEL, RESULT, OrClausesAlias<MODEL, RESULT>> or(StringSupplier getter) {
		return new StringClauseImpl<MODEL, RESULT, OrClausesAlias<MODEL,RESULT>>(this, makeGetter(getter));
	}
}
