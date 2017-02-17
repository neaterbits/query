package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;
import java.util.function.Function;

abstract class Collector_Or_Named<
		MODEL,
		RESULT,
		
		OR_CLAUSES extends ISharedLogical_Or_Named_All<MODEL, RESULT, OR_CLAUSES, NESTED_AND_CLAUSES>, // IClassicLogical_And_NonProcessResult_Named<MODEL,
		
		NESTED_AND_CLAUSES extends ISharedLogical_And_Named_All<MODEL, RESULT, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES>,
		NESTED_OR_CLAUSES extends ISharedLogical_Or_Named_All<MODEL, RESULT, NESTED_OR_CLAUSES, NESTED_AND_CLAUSES>,
		AFTER_GROUP_BY>
		
		extends Collector_Or<MODEL, RESULT, AFTER_GROUP_BY>
		
		implements ISharedLogical_Or_Named_All<MODEL, RESULT, OR_CLAUSES, NESTED_AND_CLAUSES> {

	abstract Collector_And_Named<MODEL, RESULT, NESTED_AND_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES, AFTER_GROUP_BY>
			createNestedAndCollector(Collector_Or_Named<MODEL, RESULT, OR_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES, AFTER_GROUP_BY> orClauses);
			
	Collector_Or_Named(Collector_Base<MODEL> qe) {
		super(qe);
	}

	Collector_Or_Named(Collector_Conditions_Initial<MODEL, RESULT, AFTER_GROUP_BY> last) {
		super(last);
	}

	private <T, RR extends Comparable<RR>> ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, OR_CLAUSES> orClassImplComparable(
			Function<T, RR> getter) {

		return orClassImplComparable(null, getter);
	}

	private <T, RR extends Comparable<RR>> ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, OR_CLAUSES> orClassImplComparable(
			CollectedFunctions functions, Function<T, RR> getter) {

		return new Collector_Condition_Comparative<MODEL, RESULT, RR, OR_CLAUSES>(this, functions, makeGetter(getter));
	}

	private ISharedCondition_Comparable_String_All<MODEL, RESULT, OR_CLAUSES> orClassImplString(
			CollectedFunctions functions, StringFunction<?> getter) {
		return new Collector_Condition_String<MODEL, RESULT, OR_CLAUSES>(this, functions, makeGetter(getter));
	}

	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, OR_CLAUSES> or(
			IFunctionInteger<T> getter) {
		return orClassImplComparable(getter);
	}

	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, OR_CLAUSES> or(
			IFunctionLong<T> getter) {
		return orClassImplComparable(getter);
	}

	@Override
	public final <T> ISharedCondition_Comparable_String_All<MODEL, RESULT, OR_CLAUSES> or(StringFunction<T> getter) {
		return orClassImplString(null, getter);
	}

	private final <T extends ISharedLogical_And<MODEL, RESULT>> void addNestedAndImpl(Consumer<T> orBuilder) {
		super.addNestedAndImpl(orBuilder, createNestedAndCollector(this)); // new Classic_Collector_And_NonProcessResult_Named<MODEL, RESULT>(this));
	}

	@SuppressWarnings("unchecked")
	@Override
	public final OR_CLAUSES orNest(
			ISharedNestedAndConsumerNamed<MODEL, RESULT, NESTED_AND_CLAUSES> orBuilder) {

		addNestedAndImpl(orBuilder);

		return (OR_CLAUSES) this;
	}

	@Override
	public final ISharedFunctions_Named_Initial<MODEL, RESULT, OR_CLAUSES, ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, OR_CLAUSES>, ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, OR_CLAUSES>, ISharedCondition_Comparable_String_All<MODEL, RESULT, OR_CLAUSES>> or() {

		@SuppressWarnings({ "unchecked", "rawtypes" })
		final ISharedCollector_Functions_Callback_Named<MODEL, RESULT, OR_CLAUSES> cb = new ISharedCollector_Functions_Callback_Named<MODEL, RESULT, OR_CLAUSES>() {

			@Override
			public ISharedCondition_Comparable_Common_All<MODEL, RESULT, Comparable<?>, OR_CLAUSES> onComparable(
					CollectedFunctions functions, Function getter) {

				return orClassImplComparable(functions, (Function) getter);
			}

			@Override
			public ISharedCondition_Comparable_String_Base<MODEL, RESULT, OR_CLAUSES> onString(
					CollectedFunctions functions, StringFunction getter) {
				return orClassImplString(functions, getter);
			}
		};

		return new Collector_SharedFunctions_Named<>(cb);
	}

}
