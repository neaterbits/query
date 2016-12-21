package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

final class OrClausesImpl<MODEL, RESULT> extends ClausesImpl<MODEL, RESULT>
			implements IClassicOrClausesTable<MODEL, RESULT>, IClassicOrClausesAlias<MODEL, RESULT>{

	OrClausesImpl(ClausesImplInitial<MODEL, RESULT> last) {
		super(last);
	}

	private <T, RR> ISharedConditionClause<MODEL, RESULT, RR, IClassicOrClausesTable<MODEL, RESULT>> orClassImpl(Function<T, RR> getter) {
		return new ConditionClauseImpl<MODEL, RESULT, RR, IClassicOrClausesTable<MODEL,RESULT>>(this, makeGetter(getter));
	}
	
	private <RR> ISharedConditionClause<MODEL, RESULT, RR, IClassicOrClausesAlias<MODEL, RESULT>> orAliasImpl(Supplier<RR> getter) {
		return new ConditionClauseImpl<MODEL, RESULT, RR, IClassicOrClausesAlias<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public <T> ISharedConditionClause<MODEL, RESULT, Integer, IClassicOrClausesTable<MODEL, RESULT>> or(IntegerFunction<T> getter) {
		return orClassImpl(getter);
	}

	@Override
	public <T> ISharedConditionClause<MODEL, RESULT, Long, IClassicOrClausesTable<MODEL, RESULT>> or(LongFunction<T> getter) {
		return orClassImpl(getter);
	}

	@Override
	public <T> ISharedStringClause<MODEL, RESULT, IClassicOrClausesTable<MODEL, RESULT>> or(StringFunction<T> getter) {
		return new StringClauseImpl<MODEL, RESULT, IClassicOrClausesTable<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public ISharedConditionClause<MODEL, RESULT, Integer, IClassicOrClausesAlias<MODEL, RESULT>> or(IntegerSupplier getter) {
		return orAliasImpl(getter);
	}

	@Override
	public ISharedConditionClause<MODEL, RESULT, Long, IClassicOrClausesAlias<MODEL, RESULT>> or(LongSupplier getter) {
		return orAliasImpl(getter);
	}

	@Override
	public ISharedStringClause<MODEL, RESULT, IClassicOrClausesAlias<MODEL, RESULT>> or(StringSupplier getter) {
		return new StringClauseImpl<MODEL, RESULT, IClassicOrClausesAlias<MODEL,RESULT>>(this, makeGetter(getter));
	}
}
