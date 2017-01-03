package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

final class AndClausesImpl<MODEL, RESULT> extends ClausesImpl<MODEL, RESULT>
			implements IClassicAndClausesNamed<MODEL, RESULT>,
					   IClassicAndClausesAlias<MODEL, RESULT> {

	AndClausesImpl(ClausesImplInitial<MODEL, RESULT> last) {
		super(last);
	}
	
	private <T, RR extends Comparable<RR>, AND_CLAUSES extends ISharedAndClausesNamedAll<MODEL, RESULT, AND_CLAUSES>>
		ISharedClauseComparableCommonAll<MODEL, RESULT, RR, AND_CLAUSES> andClassImpl(Function<T, RR> getter) {
		
		return new ComparativeClauseImpl<MODEL, RESULT, RR, AND_CLAUSES>(this, makeGetter(getter));
	}

	private <RR, AND_CLAUSES extends ISharedAndClausesAlias<MODEL, RESULT, AND_CLAUSES>>
		ISharedConditionClauseAlias<MODEL, RESULT, RR, AND_CLAUSES> andAliasImpl(Supplier<RR> getter) {
		
		return new ConditionClauseImpl<MODEL, RESULT, RR, AND_CLAUSES>(this, makeGetter(getter));
	}
	
	@Override
	public <T> ISharedClauseComparableCommonAll<MODEL, RESULT, Integer, IClassicAndClausesNamed<MODEL, RESULT>> and(IFunctionInteger<T> getter) {
		return andClassImpl(getter);
	}

	@Override
	public <T> ISharedClauseComparableCommonAll<MODEL, RESULT, Long, IClassicAndClausesNamed<MODEL, RESULT>> and(IFunctionLong<T> getter) {
		return andClassImpl(getter);
	}

	@Override
	public <T> ISharedClauseComparableStringAll<MODEL, RESULT, IClassicAndClausesNamed<MODEL, RESULT>> and(StringFunction<T> getter) {
		return new StringClauseImpl<MODEL, RESULT, IClassicAndClausesNamed<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public ISharedConditionClauseAlias<MODEL, RESULT, Integer, IClassicAndClausesAlias<MODEL, RESULT>> and(ISupplierInteger getter) {
		return andAliasImpl(getter);
	}

	@Override
	public ISharedConditionClauseAlias<MODEL, RESULT, Long, IClassicAndClausesAlias<MODEL, RESULT>> and(ISupplierLong getter) {
		return andAliasImpl(getter);
	}

	@Override
	public ISharedClauseComparableStringAll<MODEL, RESULT, IClassicAndClausesAlias<MODEL, RESULT>> and(ISupplierString getter) {
		return new StringClauseImpl<MODEL, RESULT, IClassicAndClausesAlias<MODEL,RESULT>>(this, makeGetter(getter));
	}
}
