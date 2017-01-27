package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

final class ClassicCollectedOrClauses<MODEL, RESULT> extends CollectedClauses<MODEL, RESULT>
			implements IClassicOrClausesNamed<MODEL, RESULT>, IClassicOrClausesAlias<MODEL, RESULT> {

	ClassicCollectedOrClauses(CollectedClauses_Initial<MODEL, RESULT> last) {
		super(last, ConditionsType.OR);
	}
	
	ClassicCollectedOrClauses(BaseQueryEntity<MODEL> qe) {
		super(qe.getQueryCollector(), qe.getModelCompiler(), new Collector_Clause(ConditionsType.OR));
	}
	

	private <T, RR extends Comparable<RR>> ISharedClauseComparableCommonAll<MODEL, RESULT, RR, IClassicOrClausesNamed<MODEL, RESULT>> orClassImplComparable(Function<T, RR> getter) {
		return orClassImplComparable(null, getter);
	}

	private <T, RR extends Comparable<RR>> ISharedClauseComparableCommonAll<MODEL, RESULT, RR, IClassicOrClausesNamed<MODEL, RESULT>> orClassImplComparable(CollectedFunctions functions, Function<T, RR> getter) {
		return new CollectedClause_Comparative<MODEL, RESULT, RR, IClassicOrClausesNamed<MODEL,RESULT>>(this, functions, makeGetter(getter));
	}


	private ISharedClauseComparableStringAll<MODEL, RESULT, IClassicOrClausesNamed<MODEL, RESULT>> orClassImplString(CollectedFunctions functions, StringFunction<?> getter) {
		return new CollectedClause_String<MODEL, RESULT, IClassicOrClausesNamed<MODEL,RESULT>>(this, functions, makeGetter(getter));
	}
	
	private <RR extends Comparable<RR>> ISharedClauseConditionAll<MODEL, RESULT, RR, IClassicOrClausesAlias<MODEL, RESULT>> orAliasImpl(Supplier<RR> getter) {
		return new CollectedClause_Comparative<MODEL, RESULT, RR, IClassicOrClausesAlias<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public <T> ISharedClauseComparableCommonAll<MODEL, RESULT, Integer, IClassicOrClausesNamed<MODEL, RESULT>> or(IFunctionInteger<T> getter) {
		return orClassImplComparable(getter);
	}

	@Override
	public <T> ISharedClauseComparableCommonAll<MODEL, RESULT, Long, IClassicOrClausesNamed<MODEL, RESULT>> or(IFunctionLong<T> getter) {
		return orClassImplComparable(getter);
	}

	@Override
	public <T> ISharedClauseComparableStringAll<MODEL, RESULT, IClassicOrClausesNamed<MODEL, RESULT>> or(StringFunction<T> getter) {
		return orClassImplString(null, getter);
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
		super.addNestedAndImpl(orBuilder, new ClassicCollectedAndClauses<MODEL, RESULT>(this));
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

	@Override
	public ISharedFunctionsNamedInitial<
				MODEL,
				RESULT,
				IClassicOrClausesNamed<MODEL, RESULT>,
				ISharedClauseComparableCommonBase<MODEL, RESULT, ?, IClassicOrClausesNamed<MODEL, RESULT>>,
				ISharedClauseComparableStringAll<MODEL, RESULT, IClassicOrClausesNamed<MODEL, RESULT>>
			>
			or() {
		
		@SuppressWarnings({"unchecked", "rawtypes"})
		final Collector_Functions_Callback<MODEL, RESULT, IClassicOrClausesNamed<MODEL, RESULT>> cb
				= new Collector_Functions_Callback<MODEL, RESULT, IClassicOrClausesNamed<MODEL, RESULT>>() {

			@Override
			public ISharedClauseComparableCommonAll<MODEL, RESULT, Comparable<?>, IClassicOrClausesNamed<MODEL, RESULT>>
				onComparable(CollectedFunctions functions, Function getter) {
				
				return orClassImplComparable(functions, (Function)getter);
			}

			@Override
			public ISharedClauseComparableStringBase<MODEL, RESULT, IClassicOrClausesNamed<MODEL, RESULT>>
				onString(CollectedFunctions functions, StringFunction getter) {
				return orClassImplString(functions, getter);
			}
		};

		return new Collector_SharedFunctions<>(cb);
	}
	
}
