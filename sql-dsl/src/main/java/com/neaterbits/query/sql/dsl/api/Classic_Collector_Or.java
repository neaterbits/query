package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

final class Classic_Collector_Or<MODEL, RESULT> extends Collector_Conditions<MODEL, RESULT>
			implements IClassicOrClausesNamed<MODEL, RESULT>, IClassicOrClausesAlias<MODEL, RESULT> {

	Classic_Collector_Or(Collector_Conditions_Initial<MODEL, RESULT> last) {
		super(last, ConditionsType.OR);
	}
	
	Classic_Collector_Or(BaseQueryEntity<MODEL> qe) {
		super(qe.getQueryCollector(), qe.getModelCompiler(), new Collector_Clause(ConditionsType.OR));
	}
	

	private <T, RR extends Comparable<RR>> ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, IClassicOrClausesNamed<MODEL, RESULT>> orClassImplComparable(Function<T, RR> getter) {
		return orClassImplComparable(null, getter);
	}

	private <T, RR extends Comparable<RR>> ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, IClassicOrClausesNamed<MODEL, RESULT>> orClassImplComparable(CollectedFunctions functions, Function<T, RR> getter) {
		return new Collector_Condition_Comparative<MODEL, RESULT, RR, IClassicOrClausesNamed<MODEL,RESULT>>(this, functions, makeGetter(getter));
	}


	private ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicOrClausesNamed<MODEL, RESULT>> orClassImplString(CollectedFunctions functions, StringFunction<?> getter) {
		return new Collector_Condition_String<MODEL, RESULT, IClassicOrClausesNamed<MODEL,RESULT>>(this, functions, makeGetter(getter));
	}
	
	private <RR extends Comparable<RR>> ISharedCondition_Equality_All<MODEL, RESULT, RR, IClassicOrClausesAlias<MODEL, RESULT>> orAliasImpl(Supplier<RR> getter) {
		return new Collector_Condition_Comparative<MODEL, RESULT, RR, IClassicOrClausesAlias<MODEL,RESULT>>(this, makeGetter(getter));
	}

	@Override
	public <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, IClassicOrClausesNamed<MODEL, RESULT>> or(IFunctionInteger<T> getter) {
		return orClassImplComparable(getter);
	}

	@Override
	public <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, IClassicOrClausesNamed<MODEL, RESULT>> or(IFunctionLong<T> getter) {
		return orClassImplComparable(getter);
	}

	@Override
	public <T> ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicOrClausesNamed<MODEL, RESULT>> or(StringFunction<T> getter) {
		return orClassImplString(null, getter);
	}

	@Override
	public ISharedCondition_Equality_All<MODEL, RESULT, Integer, IClassicOrClausesAlias<MODEL, RESULT>> or(ISupplierInteger getter) {
		return orAliasImpl(getter);
	}

	@Override
	public ISharedCondition_Equality_All<MODEL, RESULT, Long, IClassicOrClausesAlias<MODEL, RESULT>> or(ISupplierLong getter) {
		return orAliasImpl(getter);
	}

	@Override
	public ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicOrClausesAlias<MODEL, RESULT>> or(ISupplierString getter) {
		return new Collector_Condition_String<MODEL, RESULT, IClassicOrClausesAlias<MODEL,RESULT>>(this, makeGetter(getter));
	}

	private final <T extends ISharedLogical_And<MODEL, RESULT>, IMPL extends Collector_Conditions<MODEL, RESULT>> void addNestedAndImpl(Consumer<T> orBuilder) {
		super.addNestedAndImpl(orBuilder, new Classic_Collector_And<MODEL, RESULT>(this));
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
	public ISharedFunctions_Named_Initial<
				MODEL,
				RESULT,
				IClassicOrClausesNamed<MODEL, RESULT>,
				ISharedCondition_Comparable_Common_Base<MODEL, RESULT, ?, IClassicOrClausesNamed<MODEL, RESULT>>,
				ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicOrClausesNamed<MODEL, RESULT>>
			>
			or() {
		
		@SuppressWarnings({"unchecked", "rawtypes"})
		final Collector_Functions_Callback<MODEL, RESULT, IClassicOrClausesNamed<MODEL, RESULT>> cb
				= new Collector_Functions_Callback<MODEL, RESULT, IClassicOrClausesNamed<MODEL, RESULT>>() {

			@Override
			public ISharedCondition_Comparable_Common_All<MODEL, RESULT, Comparable<?>, IClassicOrClausesNamed<MODEL, RESULT>>
				onComparable(CollectedFunctions functions, Function getter) {
				
				return orClassImplComparable(functions, (Function)getter);
			}

			@Override
			public ISharedCondition_Comparable_String_Base<MODEL, RESULT, IClassicOrClausesNamed<MODEL, RESULT>>
				onString(CollectedFunctions functions, StringFunction getter) {
				return orClassImplString(functions, getter);
			}
		};

		return new Collector_SharedFunctions<>(cb);
	}
	
}
