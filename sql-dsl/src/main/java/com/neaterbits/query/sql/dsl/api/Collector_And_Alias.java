package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.function.Consumer;
import java.util.function.Supplier;

abstract class Collector_And_Alias<
			MODEL,
			RESULT,
			AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL,RESULT, AND_CLAUSES, NESTED_OR_CLAUSES>,
			NESTED_AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL,RESULT, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES>,
			NESTED_OR_CLAUSES extends ISharedLogical_Or_Alias_Base<MODEL,RESULT, NESTED_OR_CLAUSES, NESTED_AND_CLAUSES>,
			AFTER_GROUP_BY>

	extends Collector_And<MODEL, RESULT, AFTER_GROUP_BY>
	implements 
	
		ISharedLogical_And_Alias_Base<MODEL, RESULT, AND_CLAUSES, NESTED_OR_CLAUSES>,
		ISharedLogical_And_Alias_Function<MODEL, RESULT, AND_CLAUSES, NESTED_OR_CLAUSES> {

	abstract Collector_Or_Alias<MODEL, RESULT, NESTED_OR_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES, AFTER_GROUP_BY>
		createNestedOrCollector(Collector_And_Alias<MODEL, RESULT, AND_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES, AFTER_GROUP_BY> andClauses);
		
	Collector_And_Alias(Collector_Conditions_Base<MODEL, RESULT> qe) {
		super(qe);
	}

	Collector_And_Alias(Collector_Conditions_Initial<MODEL, RESULT, AFTER_GROUP_BY> last) {
		super(last);
	}
	
	
	final <T extends ISharedLogical_Or<MODEL, RESULT>> void addNestedOrImpl(Consumer<T> orBuilder) {
		super.addNestedOrImpl(orBuilder, createNestedOrCollector(this)); // new Classic_Collector_Or_NonProcessResult_Alias<MODEL, RESULT>(this));
	}
	
	private <RR extends Comparable<RR>,
			M_AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, M_AND_CLAUSES, NESTED_OR_CLAUSES>,
			NESTED_OR_CLAUSES extends ISharedLogical_Or_Alias<MODEL, RESULT>>
		
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, M_AND_CLAUSES> andAliasImplComparable(Supplier<RR> getter) {
		
		return andAliasImplComparable(null, getter);
	}

	private <RR extends Comparable<RR>,
		M_AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, M_AND_CLAUSES, NESTED_OR_CLAUSES>,
		NESTED_OR_CLAUSES extends ISharedLogical_Or_Alias<MODEL, RESULT>>
		
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, M_AND_CLAUSES> andAliasImplComparable(CollectedFunctions functions, Supplier<RR> getter) {
	
			return new Collector_Condition_Comparative<MODEL, RESULT, RR, M_AND_CLAUSES>(this, functions, makeGetter(getter));
	}

	private ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_CLAUSES>
		andAliasImplString(CollectedFunctions functions, ISupplierString getter) {
		
		return new Collector_Condition_String<MODEL, RESULT, AND_CLAUSES>(this, functions, makeGetter(getter));
	}
		
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
		return andAliasImplString(null, getter);
	}


	@SuppressWarnings("unchecked")
	@Override
	public final AND_CLAUSES andNest(ISharedNestedOrConsumerAlias<MODEL, RESULT, NESTED_OR_CLAUSES> orBuilder) {
	
		addNestedOrImpl(orBuilder);
	
		return (AND_CLAUSES)this;
	}
	
	@Override
	public final ISharedFunctions_Transform_Initial_Alias<
				MODEL,
				RESULT,
				AND_CLAUSES,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short, AND_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, AND_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, AND_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, AND_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, AND_CLAUSES>,
				ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_CLAUSES>> and() {

					
		@SuppressWarnings({"unchecked", "rawtypes"})
		final ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, AND_CLAUSES> cb
				= new ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, AND_CLAUSES>() {

			@Override
			public ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Comparable<?>, AND_CLAUSES>
				onComparable(CollectedFunctions functions, Supplier getter) {
				
				return andAliasImplComparable(functions, (Supplier)getter);
			}

			@Override
			public ISharedCondition_Comparable_String_Base<MODEL, RESULT, AND_CLAUSES>
				onString(CollectedFunctions functions, ISupplierString getter) {
				
				return andAliasImplString(functions, getter);
			}
		};

		return new Collector_ConditionFunctions_Alias<>(cb);
	}

	@Override
	final Collector_GroupBy<MODEL, RESULT> createGroupByCollector(Collector_Base<MODEL> last, int[] groupByColumns,
			Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {
		return new Collector_GroupBy_Alias<>(last, groupByColumns, collectorConditions);
	}
}
