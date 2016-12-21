package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

final class OrClausesImpl<MODEL, RESULT> extends ClausesImpl<MODEL, RESULT>
			implements IClassicOrClausesTable<MODEL, RESULT>, IClassicOrClausesAlias<MODEL, RESULT>{

	OrClausesImpl(ClausesImplInitial<MODEL, RESULT> last) {
		super(last);
	}

	private <T, RR> ISharedClauseConditionAll<MODEL, RESULT, RR, IClassicOrClausesTable<MODEL, RESULT>> orClassImpl(Function<T, RR> getter) {
		return new ConditionClauseImpl<MODEL, RESULT, RR, IClassicOrClausesTable<MODEL,RESULT>>(this, makeGetter(getter));
	}
	
	private <RR> ISharedClauseConditionAll<MODEL, RESULT, RR, IClassicOrClausesAlias<MODEL, RESULT>> orAliasImpl(Supplier<RR> getter) {
		return new ConditionClauseImpl<MODEL, RESULT, RR, IClassicOrClausesAlias<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public <T> ISharedClauseConditionAll<MODEL, RESULT, Integer, IClassicOrClausesTable<MODEL, RESULT>> or(IFunctionInteger<T> getter) {
		return orClassImpl(getter);
	}

	@Override
	public <T> ISharedClauseConditionAll<MODEL, RESULT, Long, IClassicOrClausesTable<MODEL, RESULT>> or(IFunctionLong<T> getter) {
		return orClassImpl(getter);
	}

	@Override
	public <T> ISharedClauseComparableStringAll<MODEL, RESULT, IClassicOrClausesTable<MODEL, RESULT>> or(StringFunction<T> getter) {
		return new StringClauseImpl<MODEL, RESULT, IClassicOrClausesTable<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public ISharedClauseConditionAll<MODEL, RESULT, Integer, IClassicOrClausesAlias<MODEL, RESULT>> or(ISupplierInteger getter) {
		return orAliasImpl(getter);
	}

	@Override
	public ISharedClauseConditionAll<MODEL, RESULT, Long, IClassicOrClausesAlias<MODEL, RESULT>> or(ISupplierLong getter) {
		return orAliasImpl(getter);
	}

	@Override
	public ISharedClauseComparableStringAll<MODEL, RESULT, IClassicOrClausesAlias<MODEL, RESULT>> or(ISupplierString getter) {
		return new StringClauseImpl<MODEL, RESULT, IClassicOrClausesAlias<MODEL,RESULT>>(this, makeGetter(getter));
	}
}
