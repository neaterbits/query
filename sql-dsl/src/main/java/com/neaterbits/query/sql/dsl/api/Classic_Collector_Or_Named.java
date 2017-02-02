package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;
import java.util.function.Function;

final class Classic_Collector_Or_Named<MODEL, RESULT> extends Classic_Collector_Or<MODEL, RESULT, Classic_Collector_And_Named<MODEL, RESULT>, Classic_Collector_Or_Named<MODEL, RESULT>>
			implements IClassicOrClausesNamed<MODEL, RESULT> {

	

	Classic_Collector_Or_Named(BaseQueryEntity<MODEL> qe) {
		super(qe);
	}

	Classic_Collector_Or_Named(Classic_Collector_Where_Or_Join_Named<MODEL, RESULT> last) {
		super(last);
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

	private final <T extends ISharedLogical_And<MODEL, RESULT>> void addNestedAndImpl(Consumer<T> orBuilder) {
		super.addNestedAndImpl(orBuilder, new Classic_Collector_And_Named<MODEL, RESULT>(this));
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
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, IClassicOrClausesNamed<MODEL, RESULT>>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, IClassicOrClausesNamed<MODEL, RESULT>>,
				ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicOrClausesNamed<MODEL, RESULT>>
			>
			or() {
		
		@SuppressWarnings({"unchecked", "rawtypes"})
		final ISharedCollector_Functions_Callback_Named<MODEL, RESULT, IClassicOrClausesNamed<MODEL, RESULT>> cb
				= new ISharedCollector_Functions_Callback_Named<MODEL, RESULT, IClassicOrClausesNamed<MODEL, RESULT>>() {

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

		return new Collector_SharedFunctions_Named<>(cb);
	}
	
}
