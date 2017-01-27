package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

final class Classic_Collector_And<MODEL, RESULT> extends Collector_Conditions<MODEL, RESULT>
			implements IClassicAndClausesNamed<MODEL, RESULT>,
					   IClassicAndClausesAlias<MODEL, RESULT> {

	Classic_Collector_And(Collector_Conditions_Initial<MODEL, RESULT> last) {
		super(last, ConditionsType.AND);
	}
	
	Classic_Collector_And(BaseQueryEntity<MODEL> qe) {
		super(qe.getQueryCollector(), qe.getModelCompiler(), new Collector_Clause(ConditionsType.AND));
	}
	
	
	private <T, RR extends Comparable<RR>,
			AND_CLAUSES extends ISharedLogical_And_Named_All<MODEL, RESULT, AND_CLAUSES, NESTED_OR_CLAUSES>,
			NESTED_OR_CLAUSES extends ISharedLogical_Or_Named_All<MODEL, RESULT, NESTED_OR_CLAUSES, AND_CLAUSES>>
			
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, AND_CLAUSES> andClassImplComparable(Function<T, RR> getter) {

	    return andClassImplComparable(null, getter);
	}

	private <T, RR extends Comparable<RR>,
		AND_CLAUSES extends ISharedLogical_And_Named_All<MODEL, RESULT, AND_CLAUSES, NESTED_OR_CLAUSES>,
		NESTED_OR_CLAUSES extends ISharedLogical_Or_Named_All<MODEL, RESULT, NESTED_OR_CLAUSES, AND_CLAUSES>>
		
	ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, AND_CLAUSES> andClassImplComparable(CollectedFunctions functions, Function<T, RR> getter) {
	
		return new Collector_Condition_Comparative<MODEL, RESULT, RR, AND_CLAUSES>(this, functions, makeGetter(getter));
	}

	private <
		AND_CLAUSES extends ISharedLogical_And_Named_All<MODEL, RESULT, AND_CLAUSES, NESTED_OR_CLAUSES>,
		NESTED_OR_CLAUSES extends ISharedLogical_Or_Named_All<MODEL, RESULT, NESTED_OR_CLAUSES, AND_CLAUSES>>
	
		ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_CLAUSES> andClassImplString(CollectedFunctions functions, StringFunction<?> getter) {

			return new Collector_Condition_String<MODEL, RESULT, AND_CLAUSES>(this, functions, makeGetter(getter));
}
	
	private <RR,
			AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, AND_CLAUSES, NESTED_OR_CLAUSES>,
			NESTED_OR_CLAUSES extends ISharedLogical_Or_Alias<MODEL, RESULT>>
		ISharedCondition_Equality_Alias<MODEL, RESULT, RR, AND_CLAUSES> andAliasImpl(Supplier<RR> getter) {
		
		return new Collector_Condition_Equality<MODEL, RESULT, RR, AND_CLAUSES>(this, makeGetter(getter));
	}
	
	@Override
	public <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, IClassicAndClausesNamed<MODEL, RESULT>> and(IFunctionInteger<T> getter) {
		return andClassImplComparable(getter);
	}

	@Override
	public <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, IClassicAndClausesNamed<MODEL, RESULT>> and(IFunctionLong<T> getter) {
		return andClassImplComparable(getter);
	}

	@Override
	public <T> ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicAndClausesNamed<MODEL, RESULT>> and(StringFunction<T> getter) {
		return new Collector_Condition_String<MODEL, RESULT, IClassicAndClausesNamed<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public ISharedCondition_Equality_Alias<MODEL, RESULT, Integer, IClassicAndClausesAlias<MODEL, RESULT>> and(ISupplierInteger getter) {
		return andAliasImpl(getter);
	}

	@Override
	public ISharedCondition_Equality_Alias<MODEL, RESULT, Long, IClassicAndClausesAlias<MODEL, RESULT>> and(ISupplierLong getter) {
		return andAliasImpl(getter);
	}

	@Override
	public ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicAndClausesAlias<MODEL, RESULT>> and(ISupplierString getter) {
		return new Collector_Condition_String<MODEL, RESULT, IClassicAndClausesAlias<MODEL,RESULT>>(this, makeGetter(getter));
	}

	private final <T extends ISharedLogical_Or<MODEL, RESULT>, IMPL extends Collector_Conditions<MODEL, RESULT>> void addNestedOrImpl(Consumer<T> orBuilder) {
		super.addNestedOrImpl(orBuilder, new Classic_Collector_Or<MODEL, RESULT>(this));
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
	public ISharedFunctions_Named_Initial<
				MODEL,
				RESULT,
				IClassicAndClausesNamed<MODEL, RESULT>,
				ISharedCondition_Comparable_Common_Base<MODEL, RESULT, ?, IClassicAndClausesNamed<MODEL, RESULT>>,
				ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicAndClausesNamed<MODEL, RESULT>>
			>
			and() {
		
		@SuppressWarnings({"unchecked", "rawtypes"})
		final Collector_Functions_Callback<MODEL, RESULT, IClassicAndClausesNamed<MODEL, RESULT>> cb
				= new Collector_Functions_Callback<MODEL, RESULT, IClassicAndClausesNamed<MODEL, RESULT>>() {

			@Override
			public ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Comparable<?>, IClassicAndClausesNamed<MODEL, RESULT>>
				onComparable(CollectedFunctions functions, Function getter) {
				
				return andClassImplComparable(functions, (Function)getter);
			}

			@Override
			public ISharedCondition_Comparable_String_Base<MODEL, RESULT, IClassicAndClausesNamed<MODEL, RESULT>>
				onString(CollectedFunctions functions, StringFunction getter) {
				
				return andClassImplString(functions, getter);
			}
		};

		return new Collector_SharedFunctions<>(cb);
	}
}
