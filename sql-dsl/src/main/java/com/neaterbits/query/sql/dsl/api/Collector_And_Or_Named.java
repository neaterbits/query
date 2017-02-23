package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;
import java.util.function.Function;

abstract class Collector_And_Or_Named<
			MODEL,
			RESULT,
			AND_CLAUSES extends ISharedLogical_And_Named_All<MODEL, RESULT, AND_CLAUSES, NESTED_OR_CLAUSES>,
			OR_CLAUSES  extends ISharedLogical_Or_Named_All <MODEL, RESULT, OR_CLAUSES,  NESTED_AND_CLAUSES>,

			NESTED_AND_CLAUSES extends ISharedLogical_And_Named_All<MODEL, RESULT, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES>,
			NESTED_OR_CLAUSES  extends ISharedLogical_Or_Named_All<MODEL, RESULT, NESTED_OR_CLAUSES,  NESTED_AND_CLAUSES>,			
			
			AFTER_GROUP_BY> 
			
	extends Collector_And_Or_Base<MODEL, RESULT, AFTER_GROUP_BY>
		
	implements ISharedLogical_And_Or_Named_All<
			MODEL,
			RESULT,
			AND_CLAUSES,
			OR_CLAUSES,
			NESTED_AND_CLAUSES,
			NESTED_OR_CLAUSES> {
	
    abstract Collector_Or_Named<MODEL, RESULT, OR_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES, AFTER_GROUP_BY> createOrCollector();
    abstract Collector_And_Named<MODEL, RESULT, AND_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES, AFTER_GROUP_BY> createAndCollector();
	
	abstract Collector_Or_Named<MODEL, RESULT, NESTED_OR_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES, AFTER_GROUP_BY> createNestedOrCollector(
				Collector_And_Named<MODEL, RESULT, AND_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES, AFTER_GROUP_BY> andClauses);
	
    abstract Collector_And_Named<MODEL, RESULT, NESTED_AND_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES, AFTER_GROUP_BY> createNestedAndCollector(
    			Collector_Or_Named<MODEL, RESULT, OR_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES, AFTER_GROUP_BY> orClauses);
    
	Collector_And_Or_Named(Collector_Base<MODEL> last, EConditionsClause conditionsClause) {
		super(last, conditionsClause);
	}
	
	Collector_And_Or_Named(Collector_Query<MODEL> queryCollector, Collector_Clause collector) {
		super(queryCollector, collector);
	}

	// ------------------------  AND ------------------------


	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, AND_CLAUSES> and(IFunctionInteger<T> getter) {
		return andClassImplComparative(getter);
	}

	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, AND_CLAUSES> and(IFunctionLong<T> getter) {
		return andClassImplComparative(getter);
	}

	@Override
	public final <T> ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_CLAUSES> and(StringFunction<T> getter) {
		return andClassImplString(null, getter);
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

	// ------------------------  OR ------------------------


	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, OR_CLAUSES> or(IFunctionInteger<T> getter) {
		return orClassImplComparable(getter);
	}

	
	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, OR_CLAUSES> or(IFunctionLong<T> getter) {
		return orClassImplComparable(getter);
	}


	@Override
	public final <T> ISharedCondition_Comparable_String_All<MODEL, RESULT, OR_CLAUSES> or(StringFunction<T> getter) {
		return orClassImplString(null, getter);
	}

	@Override
	public final ISharedFunctions_Named_Initial<
		MODEL,
		RESULT,
		OR_CLAUSES,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, OR_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, OR_CLAUSES>,
		ISharedCondition_Comparable_String_All<MODEL, RESULT, OR_CLAUSES>
	>
		or() {

		@SuppressWarnings({"unchecked", "rawtypes"})
		final ISharedCollector_Functions_Callback_Named<MODEL, RESULT, OR_CLAUSES> cb
				= new ISharedCollector_Functions_Callback_Named<MODEL, RESULT, OR_CLAUSES>() {
		
			@Override
			public ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Comparable<?>, OR_CLAUSES>
				onComparable(CollectedFunctions functions, Function getter) {
				
				return orClassImplComparable(functions, (Function)getter);
			}
		
			@Override
			public ISharedCondition_Comparable_String_Base<MODEL, RESULT, OR_CLAUSES> 
				onString(CollectedFunctions functions, StringFunction getter) {
				return orClassImplString(functions, getter);
			}
		};

		return new Collector_SharedFunctions_Named<>(cb);
	}
	// ------------------------  AND helpers ------------------------

	private <T, RR extends Comparable<RR>>
			
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, AND_CLAUSES> andClassImplComparative(Function<T, RR> getter) {
		
		return andClassImplComparable(null, getter);
	}

	final <T, RR extends Comparable<RR>>
		
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, AND_CLAUSES> andClassImplComparable(CollectedFunctions functions, Function<T, RR> getter) {
	
		final Collector_And_Named<MODEL, RESULT, AND_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES, AFTER_GROUP_BY> andClauses = createAndCollector(); // new Classic_Collector_And_Named<>(this);
	
		return new Collector_Condition_Comparative<MODEL, RESULT, RR, AND_CLAUSES>(andClauses, functions, makeGetter(getter));
	}
		
		
	private ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_CLAUSES> andClassImplString(CollectedFunctions functions, StringFunction<?> getter) {
		
		final Collector_And_Named<MODEL, RESULT, AND_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES, AFTER_GROUP_BY> andClauses = createAndCollector(); // new Classic_Collector_And_Named<>(this);
		
		return new Collector_Condition_String<MODEL, RESULT, AND_CLAUSES>(andClauses, functions, makeGetter(getter));
	}

	// ------------------------  OR helpers ------------------------
	private <T, RR extends Comparable<RR>>
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, OR_CLAUSES> orClassImplComparable(Function<T, RR> getter) {
		
		return orClassImplComparable(null, getter);
	}

	private <T, RR extends Comparable<RR>>
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, OR_CLAUSES> orClassImplComparable(CollectedFunctions functions, Function<T, RR> getter) {
	
		final Collector_Or_Named<MODEL, RESULT, OR_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES, AFTER_GROUP_BY> orClauses = createOrCollector(); // new Classic_Collector_Or_Named<>(this);

		return new Collector_Condition_Comparative<MODEL, RESULT, RR, OR_CLAUSES>(orClauses, functions, makeGetter(getter));
	}
	private ISharedCondition_Comparable_String_All<MODEL, RESULT, OR_CLAUSES>
				orClassImplString(CollectedFunctions functions, StringFunction<?> getter) {

		final Collector_Or_Named<MODEL, RESULT, OR_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES, AFTER_GROUP_BY> andClauses = createOrCollector(); // new Classic_Collector_Or_Named<>(this);

		return new Collector_Condition_String<MODEL, RESULT, OR_CLAUSES>(andClauses, functions, makeGetter(getter));
	}
	

	// ------------------------  OR helpers ------------------------
	private final <T extends ISharedLogical_And<MODEL, RESULT>>
		Collector_Or_Named<MODEL, RESULT, OR_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES, AFTER_GROUP_BY> addNestedAndImpl(Consumer<T> orBuilder) {
	
		final Collector_Or_Named<MODEL, RESULT, OR_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES, AFTER_GROUP_BY> orClauses = createOrCollector(); // new Classic_Collector_Or_Named<>(this);
		
		// Add to new OR
		orClauses.addNestedAndImpl(orBuilder, createNestedAndCollector(orClauses)); // new Classic_Collector_And_NonProcessResult_Named<MODEL, RESULT>(orClauses));
		
		return orClauses;
	}
	
	private final <T extends ISharedLogical_Or<MODEL, RESULT>>
		Collector_And_Named<MODEL, RESULT, AND_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES, AFTER_GROUP_BY> addNestedOrImpl(Consumer<T> orBuilder) {

		final Collector_And_Named<MODEL, RESULT, AND_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES, AFTER_GROUP_BY> andClauses = createAndCollector(); // new Classic_Collector_And_Named<>(this);

		// Add to new AND clause
		andClauses.addNestedOrImpl(orBuilder, createNestedOrCollector(andClauses)); // new Classic_Collector_Or_NonProcessResult_Named<MODEL, RESULT>(andClauses));
		
		return andClauses;
	}

	@SuppressWarnings("unchecked")
	@Override
	public final AND_CLAUSES andNest(ISharedNestedOrConsumerNamed<MODEL, RESULT, NESTED_OR_CLAUSES> orBuilder) {
		return (AND_CLAUSES)addNestedOrImpl(orBuilder);
	}

	@SuppressWarnings("unchecked")
	@Override
	public final OR_CLAUSES orNest(ISharedNestedAndConsumerNamed<MODEL, RESULT, NESTED_AND_CLAUSES> andBuilder) {
		return (OR_CLAUSES)addNestedAndImpl(andBuilder);
	}

	@Override
	final Collector_GroupBy<MODEL, RESULT> createGroupByCollector(Collector_Base<MODEL> last, int[] groupByColumns,
			Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {
		return new Collector_GroupBy_Named<>(last, groupByColumns, collectorConditions);
	}
}
