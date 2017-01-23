package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

final class ClassicCollectedOrClauses<MODEL, RESULT> extends CollectedClauses<MODEL, RESULT>
			implements IClassicOrClausesNamed<MODEL, RESULT>, IClassicOrClausesAlias<MODEL, RESULT> {

	ClassicCollectedOrClauses(CollectedClauses_Initial<MODEL, RESULT> last) {
		super(last);
	}
	
	ClassicCollectedOrClauses(BaseQueryEntity<MODEL> qe, Collector_Clause clausesCollector) {
		super(qe.getQueryCollector(), qe.getModelCompiler(), clausesCollector);
	}
	

	private <T, RR extends Comparable<RR>> ISharedClauseComparableCommonAll<MODEL, RESULT, RR, IClassicOrClausesNamed<MODEL, RESULT>> orClassImpl(Function<T, RR> getter) {
		return new CollectedClause_Comparative<MODEL, RESULT, RR, IClassicOrClausesNamed<MODEL,RESULT>>(this, makeGetter(getter));
	}
	
	private <RR extends Comparable<RR>> ISharedClauseConditionAll<MODEL, RESULT, RR, IClassicOrClausesAlias<MODEL, RESULT>> orAliasImpl(Supplier<RR> getter) {
		return new CollectedClause_Comparative<MODEL, RESULT, RR, IClassicOrClausesAlias<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public <T> ISharedClauseComparableCommonAll<MODEL, RESULT, Integer, IClassicOrClausesNamed<MODEL, RESULT>> or(IFunctionInteger<T> getter) {
		return orClassImpl(getter);
	}

	@Override
	public <T> ISharedClauseComparableCommonAll<MODEL, RESULT, Long, IClassicOrClausesNamed<MODEL, RESULT>> or(IFunctionLong<T> getter) {
		return orClassImpl(getter);
	}

	@Override
	public <T> ISharedClauseComparableStringAll<MODEL, RESULT, IClassicOrClausesNamed<MODEL, RESULT>> or(StringFunction<T> getter) {
		return new CollectedClause_String<MODEL, RESULT, IClassicOrClausesNamed<MODEL,RESULT>>(this, makeGetter(getter));
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
		return new CollectedClause_String<MODEL, RESULT, IClassicOrClausesAlias<MODEL,RESULT>>(this, makeGetter(getter));
	}

	private final <T extends ISharedAndClauses<MODEL, RESULT>, IMPL extends CollectedClauses<MODEL, RESULT>> void addNestedAndImpl(Consumer<T> orBuilder) {
		super.addNestedAndImpl(orBuilder, this, new ClassicCollectedAndClauses<MODEL, RESULT>(this, new Collector_Clause()));
	}
	
	@Override
	public IClassicOrClausesAlias<MODEL, RESULT> orNest(ISharedNestedAndConsumerAlias<MODEL, RESULT, IClassicAndClausesAlias<MODEL, RESULT>> orBuilder) {
	
		addNestedAndImpl(orBuilder);
		
		return this;
	}

	@Override
	public IClassicOrClausesNamed<MODEL, RESULT> orNest(ISharedNestedAndConsumerNamed<MODEL, RESULT, IClassicAndClausesNamed<MODEL, RESULT>> orBuilder) {

		addNestedAndImpl(orBuilder);
		
		return this;
	}
}
