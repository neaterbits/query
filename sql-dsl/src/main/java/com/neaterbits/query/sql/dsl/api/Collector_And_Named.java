package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;
import java.util.function.Function;

abstract class Collector_And_Named<
		MODEL,
		RESULT,
		AND_CLAUSES extends ISharedLogical_And_Named_All<MODEL, RESULT, AND_CLAUSES, ?>, // IClassicLogical_Or_NonProcessResult_Named<MODEL,
		
		NESTED_AND_CLAUSES extends ISharedLogical_And_Named_All<MODEL, RESULT, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES>,
		NESTED_OR_CLAUSES extends ISharedLogical_Or_Named_All<MODEL, RESULT, NESTED_OR_CLAUSES, NESTED_AND_CLAUSES>,
		
		AFTER_GROUP_BY>

		extends Collector_And<MODEL, RESULT, AFTER_GROUP_BY>
		
		implements ISharedLogical_And_Named_All<MODEL, RESULT, AND_CLAUSES, NESTED_OR_CLAUSES> {

	abstract Collector_Or_Named<MODEL, RESULT, NESTED_OR_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES, AFTER_GROUP_BY>
			createNestedOrCollector(Collector_And_Named<MODEL, RESULT, AND_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES, AFTER_GROUP_BY> andClauses);
			
	Collector_And_Named(BaseQueryEntity<MODEL> qe) {
		super(qe);
	}

	Collector_And_Named(Collector_Conditions_Initial<MODEL, RESULT, AFTER_GROUP_BY> last) {
		super(last);
	}

	
	private final <T extends ISharedLogical_Or<MODEL, RESULT>> void addNestedOrImpl(Consumer<T> orBuilder) {
		super.addNestedOrImpl(orBuilder, createNestedOrCollector(this)); // new Classic_Collector_Or_NonProcessResult_Named<MODEL, RESULT>(this));
	}
	
	private <T, RR extends Comparable<RR>>
	
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, AND_CLAUSES> andClassImplComparable(Function<T, RR> getter) {

		return andClassImplComparable(null, getter);
	}

	private <T, RR extends Comparable<RR>>
	
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, AND_CLAUSES> andClassImplComparable(CollectedFunctions functions, Function<T, RR> getter) {
	
		return new Collector_Condition_Comparative<MODEL, RESULT, RR, AND_CLAUSES>(this, functions, makeGetter(getter));
	}

	private ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_CLAUSES>
		andClassImplString(CollectedFunctions functions, StringFunction<?> getter) {
	
		return new Collector_Condition_String<MODEL, RESULT, AND_CLAUSES>(this, functions, makeGetter(getter));
	}

	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, AND_CLAUSES> and(IFunctionInteger<T> getter) {
		return andClassImplComparable(getter);
	}

	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, AND_CLAUSES> and(IFunctionLong<T> getter) {
		return andClassImplComparable(getter);
	}

	@Override
	public final <T> ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_CLAUSES> and(StringFunction<T> getter) {
		return new Collector_Condition_String<MODEL, RESULT, AND_CLAUSES>(this, makeGetter(getter));
	}

	@SuppressWarnings("unchecked")
	@Override
	public final AND_CLAUSES andNest(ISharedNestedOrConsumerNamed<MODEL, RESULT, NESTED_OR_CLAUSES> orBuilder) {
		
		addNestedOrImpl(orBuilder);
		
		return (AND_CLAUSES)this;
	}

	@Override
	public final ISharedFunctions_Named_Initial<
				MODEL,
				RESULT,
				AND_CLAUSES,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, AND_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, AND_CLAUSES>,
				ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_CLAUSES>
			>
			and() {
		
		@SuppressWarnings({"unchecked", "rawtypes"})
		final ISharedCollector_Functions_Callback_Named<MODEL, RESULT, AND_CLAUSES> cb
				= new ISharedCollector_Functions_Callback_Named<MODEL, RESULT, AND_CLAUSES>() {

			@Override
			public ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Comparable<?>, AND_CLAUSES>
				onComparable(CollectedFunctions functions, Function getter) {
				
				return andClassImplComparable(functions, (Function)getter);
			}

			@Override
			public ISharedCondition_Comparable_String_Base<MODEL, RESULT, AND_CLAUSES>
				onString(CollectedFunctions functions, StringFunction getter) {
				
				return andClassImplString(functions, getter);
			}
		};

		return new Collector_SharedFunctions_Named<>(cb);
	}
}
