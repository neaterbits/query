package com.neaterbits.query.sql.dsl.api;

import java.util.function.Consumer;
import java.util.function.Supplier;

abstract class Collector_And_Or_Alias<
			MODEL,
			RESULT,
			
			AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, AND_CLAUSES, NESTED_OR_CLAUSES>,
			OR_CLAUSES  extends ISharedLogical_Or_Alias_Base <MODEL, RESULT, OR_CLAUSES,  NESTED_AND_CLAUSES>,

			NESTED_AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES>,
			NESTED_OR_CLAUSES  extends ISharedLogical_Or_Alias_Base <MODEL, RESULT, NESTED_OR_CLAUSES,  NESTED_AND_CLAUSES>,			

			AFTER_GROUP_BY>


	extends Collector_And_Or_Base<MODEL, RESULT, AFTER_GROUP_BY>
	
	implements ISharedLogical_And_Or_Alias<MODEL, RESULT, AND_CLAUSES, OR_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES> {

	abstract Collector_Or_Alias<MODEL, RESULT, OR_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES, AFTER_GROUP_BY> createOrCollector();
    abstract Collector_And_Alias<MODEL, RESULT, AND_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES, AFTER_GROUP_BY> createAndCollector();

	abstract Collector_Or_Alias<MODEL, RESULT, NESTED_OR_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES, AFTER_GROUP_BY>
			createNestedOrCollector(Collector_And_Alias<MODEL, RESULT, AND_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES, AFTER_GROUP_BY> andClauses);
    abstract Collector_And_Alias<MODEL, RESULT, NESTED_AND_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES, AFTER_GROUP_BY>
    		createNestedAndCollector(Collector_Or_Alias<MODEL, RESULT, OR_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES, AFTER_GROUP_BY> orClauses);
    
	Collector_And_Or_Alias(BaseQueryEntity<MODEL> last) {
		super(last);
	}

	Collector_And_Or_Alias(QueryCollectorImpl queryCollector, ModelCompiler<MODEL> modelCompiler,
			Collector_Clause collector) {
		super(queryCollector, modelCompiler, collector);
	}

	// ------------------------  AND ------------------------
	
	
	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, AND_CLAUSES> and(ISupplierInteger getter) {
		return andAliasImplComparable(getter);
	}
	
	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, AND_CLAUSES> and(ISupplierLong getter) {
		return andAliasImplComparable(getter);
	}
	
	@Override
	public final ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_CLAUSES> and(ISupplierString getter) {
	
		final Collector_And_Alias<MODEL, RESULT, AND_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES, AFTER_GROUP_BY> andClauses = createAndCollector();// new Classic_Collector_And_Alias<>(this);
		
		return new Collector_Condition_String<MODEL, RESULT, AND_CLAUSES>(andClauses, makeGetter(getter));
	}

	
	@Override
	public final ISharedFunctions_Alias_Initial<
		MODEL,
		RESULT,
		AND_CLAUSES,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, AND_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, AND_CLAUSES>,
		ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_CLAUSES>
	>
		and() {
	
		@SuppressWarnings({"unchecked", "rawtypes"})
		final ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, AND_CLAUSES> cb
				= new ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, AND_CLAUSES>() {
		
			@Override
			public ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Comparable<?>, AND_CLAUSES>
				onComparable(CollectedFunctions functions, Supplier<? extends Comparable<?>> getter) {
				
				return andAliasImplComparable(functions, (Supplier)getter);
			}
		
			@Override
			public ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_CLAUSES>
				onString(CollectedFunctions functions, ISupplierString getter) {
				
				return andAliasImplString(functions, getter);
			}
		};
	
		return new Collector_SharedFunctions_Alias<>(cb);
	}

	// ------------------------  OR ------------------------


	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, OR_CLAUSES> or(ISupplierInteger getter) {
		return orAliasImplComparable(getter);
	}

	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, OR_CLAUSES> or(ISupplierLong getter) {
		return orAliasImplComparable(getter);
	}
	
	@Override
	public final ISharedCondition_Comparable_String_All<MODEL, RESULT, OR_CLAUSES> or(ISupplierString getter) {
	
		return orAliasImplString(null, getter);
	}
	
	@Override
	public final ISharedFunctions_Alias_Initial<
		MODEL,
		RESULT,
		OR_CLAUSES,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, OR_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, OR_CLAUSES>,
		ISharedCondition_Comparable_String_All<MODEL, RESULT, OR_CLAUSES>
	>
		or() {
	
		@SuppressWarnings({"unchecked", "rawtypes"})
		final ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, OR_CLAUSES> cb
				= new ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, OR_CLAUSES>() {
		
			@Override
			public ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Comparable<?>, OR_CLAUSES>
				onComparable(CollectedFunctions functions, Supplier<? extends Comparable<?>> getter) {
				
				return orAliasImplComparable(functions, (Supplier)getter);
			}

			@Override
			public ISharedCondition_Comparable_String_Base<MODEL, RESULT, OR_CLAUSES> 
				onString(CollectedFunctions functions, ISupplierString getter) {

				return orAliasImplString(functions, getter);
			}
		};

		return new Collector_SharedFunctions_Alias<>(cb);
	}
		
	// ------------------------  AND helpers ------------------------
	
	private <RR extends Comparable<RR>>
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, AND_CLAUSES> andAliasImplComparable(Supplier<RR> getter) {
		
		return andAliasImplComparable(null, getter);
	}
	
	final <RR extends Comparable<RR>>
		
	ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, AND_CLAUSES> andAliasImplComparable(CollectedFunctions functions, Supplier<RR> getter) {
	
		final Collector_And_Alias<MODEL, RESULT, AND_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES, AFTER_GROUP_BY> andClauses = createAndCollector();// new Classic_Collector_And_Alias<MODEL, RESULT>(this);
	
		return new Collector_Condition_Comparative<MODEL, RESULT, RR, AND_CLAUSES>(andClauses, functions, makeGetter(getter));
	}
		
		
	private 
	
		ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_CLAUSES>
			andAliasImplString(CollectedFunctions functions, ISupplierString getter) {
		
		final Collector_And_Alias<MODEL, RESULT, AND_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES, AFTER_GROUP_BY> andClauses = createAndCollector(); // new Classic_Collector_And_Alias<>(this);
		
		return new Collector_Condition_String<MODEL, RESULT, AND_CLAUSES>(andClauses, functions, makeGetter(getter));
	}
	
	// ------------------------  OR helpers ------------------------
	private final <RR extends Comparable<RR>>
	
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, OR_CLAUSES> orAliasImplComparable(Supplier<RR> getter) {
		
		return orAliasImplComparable(null, getter);
	}
	
	private <T, RR extends Comparable<RR>>
	
	ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, OR_CLAUSES> orAliasImplComparable(CollectedFunctions functions, Supplier<RR> getter) {
	
		final Collector_Or_Alias<MODEL, RESULT, OR_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES, AFTER_GROUP_BY> orClauses = createOrCollector(); // new Classic_Collector_Or_Alias<>(this);
	
		return new Collector_Condition_Comparative<MODEL, RESULT, RR, OR_CLAUSES>(orClauses, functions, makeGetter(getter));
	}
	
	private 
		ISharedCondition_Comparable_String_All<MODEL, RESULT, OR_CLAUSES> orAliasImplString(CollectedFunctions functions, ISupplierString getter) {
	
		final Collector_Or_Alias<MODEL, RESULT, OR_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES, AFTER_GROUP_BY> orClauses = createOrCollector(); // new Classic_Collector_Or_Alias<>(this);
	
		return new Collector_Condition_String<MODEL, RESULT, OR_CLAUSES>(orClauses, functions, makeGetter(getter));
	}
	
	
	/*
	private <RR extends Comparable<RR>,
			OR_CLAUSES extends ISharedLogical_Or_Alias_Base<MODEL, RESULT, OR_CLAUSES, NESTED_AND_CLAUSES>,
			NESTED_AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, NESTED_AND_CLAUSES, OR_CLAUSES>>

		ISharedCondition_Equality_All<MODEL, RESULT, RR, OR_CLAUSES> orAliasImplComparable(Collector_Conditions_Initial<MODEL, RESULT> last, Supplier<RR> getter) {
	
		final Classic_Collector_Or_Named<MODEL, RESULT> orClauses = new Classic_Collector_Or_Named<>(last);
	
		return new Collector_Condition_Comparative<MODEL, RESULT, RR, OR_CLAUSES>(orClauses, makeGetter(getter));
	}
	*/
	

	// ------------------------  OR helpers ------------------------
	private final <T extends ISharedLogical_And<MODEL, RESULT>>
		Collector_Or_Alias<MODEL, RESULT, OR_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES, AFTER_GROUP_BY>
		
			addNestedAndImpl(Consumer<T> orBuilder) {
	
		final Collector_Or_Alias<
				MODEL,
				RESULT,
				OR_CLAUSES,
				NESTED_AND_CLAUSES,
				NESTED_OR_CLAUSES,
				AFTER_GROUP_BY> orClauses
					
				
				= createOrCollector(); // new Classic_Collector_Or_Alias<>(this);
		
		// Add to new OR
		orClauses.addNestedAndImpl(orBuilder, createNestedAndCollector(orClauses)); // new Classic_Collector_And_NonProcessResult_Alias<>(orClauses));
		
		
		return orClauses;
	}

	private final <T extends ISharedLogical_Or<MODEL, RESULT>>
		
		Collector_And_Alias<MODEL, RESULT, AND_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES, AFTER_GROUP_BY>
			addNestedOrImpl(Consumer<T> orBuilder) {
	
		final Collector_And_Alias<MODEL, RESULT, AND_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES, AFTER_GROUP_BY> andClauses = createAndCollector(); // new Classic_Collector_And_Alias<>(this);
	
		// Add to new AND clause
		andClauses.addNestedOrImpl(orBuilder, createNestedOrCollector(andClauses));// new Classic_Collector_Or_NonProcessResult_Alias<>(andClauses));
		
		return andClauses;
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public final AND_CLAUSES andNest(ISharedNestedOrConsumerAlias<MODEL, RESULT, NESTED_OR_CLAUSES> orBuilder) {
		return (AND_CLAUSES)addNestedOrImpl(orBuilder);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public final OR_CLAUSES orNest(ISharedNestedAndConsumerAlias<MODEL, RESULT, NESTED_AND_CLAUSES> andBuilder) {
		return (OR_CLAUSES)addNestedAndImpl(andBuilder);
	}
}
