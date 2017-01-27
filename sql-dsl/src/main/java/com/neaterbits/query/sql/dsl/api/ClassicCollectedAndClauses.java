package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

final class ClassicCollectedAndClauses<MODEL, RESULT> extends CollectedClauses<MODEL, RESULT>
			implements IClassicAndClausesNamed<MODEL, RESULT>,
					   IClassicAndClausesAlias<MODEL, RESULT> {

	ClassicCollectedAndClauses(CollectedClauses_Initial<MODEL, RESULT> last) {
		super(last, ConditionsType.AND);
	}
	
	ClassicCollectedAndClauses(BaseQueryEntity<MODEL> qe) {
		super(qe.getQueryCollector(), qe.getModelCompiler(), new Collector_Clause(ConditionsType.AND));
	}
	
	
	private <T, RR extends Comparable<RR>,
			AND_CLAUSES extends ISharedAndClausesNamedAll<MODEL, RESULT, AND_CLAUSES, NESTED_OR_CLAUSES>,
			NESTED_OR_CLAUSES extends ISharedOrClausesNamedAll<MODEL, RESULT, NESTED_OR_CLAUSES, AND_CLAUSES>>
			
		ISharedClauseComparableCommonAll<MODEL, RESULT, RR, AND_CLAUSES> andClassImplComparable(Function<T, RR> getter) {

	    return andClassImplComparable(null, getter);
	}

	private <T, RR extends Comparable<RR>,
		AND_CLAUSES extends ISharedAndClausesNamedAll<MODEL, RESULT, AND_CLAUSES, NESTED_OR_CLAUSES>,
		NESTED_OR_CLAUSES extends ISharedOrClausesNamedAll<MODEL, RESULT, NESTED_OR_CLAUSES, AND_CLAUSES>>
		
	ISharedClauseComparableCommonAll<MODEL, RESULT, RR, AND_CLAUSES> andClassImplComparable(CollectedFunctions functions, Function<T, RR> getter) {
	
		return new CollectedClause_Comparative<MODEL, RESULT, RR, AND_CLAUSES>(this, functions, makeGetter(getter));
	}

	private <
		AND_CLAUSES extends ISharedAndClausesNamedAll<MODEL, RESULT, AND_CLAUSES, NESTED_OR_CLAUSES>,
		NESTED_OR_CLAUSES extends ISharedOrClausesNamedAll<MODEL, RESULT, NESTED_OR_CLAUSES, AND_CLAUSES>>
	
		ISharedClauseComparableStringAll<MODEL, RESULT, AND_CLAUSES> andClassImplString(CollectedFunctions functions, StringFunction<?> getter) {

			return new CollectedClause_String<MODEL, RESULT, AND_CLAUSES>(this, functions, makeGetter(getter));
}
	
	private <RR,
			AND_CLAUSES extends ISharedAndClausesAliasBase<MODEL, RESULT, AND_CLAUSES, NESTED_OR_CLAUSES>,
			NESTED_OR_CLAUSES extends ISharedOrClausesAlias<MODEL, RESULT>>
		ISharedConditionClauseAlias<MODEL, RESULT, RR, AND_CLAUSES> andAliasImpl(Supplier<RR> getter) {
		
		return new CollectedClause_Condition<MODEL, RESULT, RR, AND_CLAUSES>(this, makeGetter(getter));
	}
	
	@Override
	public <T> ISharedClauseComparableCommonAll<MODEL, RESULT, Integer, IClassicAndClausesNamed<MODEL, RESULT>> and(IFunctionInteger<T> getter) {
		return andClassImplComparable(getter);
	}

	@Override
	public <T> ISharedClauseComparableCommonAll<MODEL, RESULT, Long, IClassicAndClausesNamed<MODEL, RESULT>> and(IFunctionLong<T> getter) {
		return andClassImplComparable(getter);
	}

	@Override
	public <T> ISharedClauseComparableStringAll<MODEL, RESULT, IClassicAndClausesNamed<MODEL, RESULT>> and(StringFunction<T> getter) {
		return new CollectedClause_String<MODEL, RESULT, IClassicAndClausesNamed<MODEL,RESULT>>(this, makeGetter(getter));
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
		return new CollectedClause_String<MODEL, RESULT, IClassicAndClausesAlias<MODEL,RESULT>>(this, makeGetter(getter));
	}

	private final <T extends ISharedOrClauses<MODEL, RESULT>, IMPL extends CollectedClauses<MODEL, RESULT>> void addNestedOrImpl(Consumer<T> orBuilder) {
		super.addNestedOrImpl(orBuilder, new ClassicCollectedOrClauses<MODEL, RESULT>(this));
	}
	
	@Override
	public IClassicAndClausesAlias<MODEL, RESULT> andNest(ISharedNestedOrConsumerAlias<MODEL, RESULT, IClassicOrClausesAlias<MODEL, RESULT>> orBuilder) {
	
		addNestedOrImpl(orBuilder);
	
		return this;
	}

	@Override
	public IClassicAndClausesNamed<MODEL, RESULT> andNest(ISharedNestedOrConsumerNamed<MODEL, RESULT, IClassicOrClausesNamed<MODEL, RESULT>> orBuilder) {
		
		addNestedOrImpl(orBuilder);
		
		return this;
	}
	
	@Override
	public ISharedFunctionsNamedInitial<
				MODEL,
				RESULT,
				IClassicAndClausesNamed<MODEL, RESULT>,
				ISharedClauseComparableCommonBase<MODEL, RESULT, ?, IClassicAndClausesNamed<MODEL, RESULT>>,
				ISharedClauseComparableStringAll<MODEL, RESULT, IClassicAndClausesNamed<MODEL, RESULT>>
			>
			and() {
		
		@SuppressWarnings({"unchecked", "rawtypes"})
		final Collector_Functions_Callback<MODEL, RESULT, IClassicAndClausesNamed<MODEL, RESULT>> cb
				= new Collector_Functions_Callback<MODEL, RESULT, IClassicAndClausesNamed<MODEL, RESULT>>() {

			@Override
			public ISharedClauseComparableCommonBase<MODEL, RESULT, Comparable<?>, IClassicAndClausesNamed<MODEL, RESULT>>
				onComparable(CollectedFunctions functions, Function getter) {
				
				return andClassImplComparable(functions, (Function)getter);
			}

			@Override
			public ISharedClauseComparableStringBase<MODEL, RESULT, IClassicAndClausesNamed<MODEL, RESULT>>
				onString(CollectedFunctions functions, StringFunction getter) {
				
				return andClassImplString(functions, getter);
			}
		};

		return new Collector_SharedFunctions<>(cb);
	}
}
