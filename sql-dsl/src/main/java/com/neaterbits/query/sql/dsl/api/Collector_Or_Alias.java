package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.apache.tools.ant.taskdefs.compilers.Sj;

abstract class Collector_Or_Alias<
		MODEL, 
		RESULT,
		OR_CLAUSES extends ISharedLogical_Or_Alias_Base<MODEL, RESULT, OR_CLAUSES, NESTED_AND_CLAUSES>,
		NESTED_AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES>,
		NESTED_OR_CLAUSES extends ISharedLogical_Or_Alias_Base<MODEL, RESULT, NESTED_OR_CLAUSES, NESTED_AND_CLAUSES>,
		AFTER_GROUP_BY
		>

	extends Collector_Or<MODEL, RESULT, AFTER_GROUP_BY>
	implements 
		ISharedLogical_Or_Alias_Base<MODEL, RESULT, OR_CLAUSES, NESTED_AND_CLAUSES>,
		ISharedLogical_Or_Alias_Function<MODEL, RESULT, OR_CLAUSES, NESTED_AND_CLAUSES> {

	abstract Collector_And_Alias<MODEL, RESULT, NESTED_AND_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES, AFTER_GROUP_BY>
		createNestedAndCollector(Collector_Or_Alias<MODEL, RESULT, OR_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES, AFTER_GROUP_BY> orClauses);
		
	Collector_Or_Alias(Collector_Conditions_Base<MODEL, RESULT> qe) {
		super(qe);
	}

	Collector_Or_Alias(Collector_Conditions_Initial<MODEL, RESULT, AFTER_GROUP_BY> last) {
		super(last);
	}

	private <RR extends Comparable<RR>> ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, OR_CLAUSES> orAliasImplComparable(Supplier<RR> getter) {
		
		return orAliasImplComparable(null, getter);
	}

	private <RR extends Comparable<RR>> ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, OR_CLAUSES> orAliasImplComparable(CollectedFunctions functions, Supplier<RR> getter) {
		
		final Expression expression = makeExpression(functions, makeGetterExpression(getter));
		
		return new Collector_Condition_Comparative<MODEL, RESULT, RR, OR_CLAUSES>(this, expression);
	}
	
	private ISharedCondition_Comparable_String_All<MODEL, RESULT, OR_CLAUSES>
		orAliasImplString(CollectedFunctions functions, ISupplierString getter) {

		final Expression expression = makeExpression(functions, makeGetterExpression(getter));
			
		return new Collector_Condition_String<MODEL, RESULT, OR_CLAUSES>(this, expression);
	}

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

	private final <T extends ISharedLogical_And<MODEL, RESULT>>
		
		void addNestedAndImpl(Consumer<T> orBuilder) {
		
		super.addNestedAndImpl(orBuilder, createNestedAndCollector(this)); // new Classic_Collector_And_NonProcessResult_Alias<>(this));
	}

	@SuppressWarnings("unchecked")
	@Override
	public final OR_CLAUSES orNest(ISharedNestedAndConsumerAlias<MODEL, RESULT, NESTED_AND_CLAUSES> orBuilder) {

		addNestedAndImpl(orBuilder);

		return (OR_CLAUSES)this;
	}

	@Override
	public final ISharedFunctions_Transform_Initial_Alias<
			MODEL,
			RESULT,
			OR_CLAUSES,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short, OR_CLAUSES>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, OR_CLAUSES>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, OR_CLAUSES>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, OR_CLAUSES>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, OR_CLAUSES>,
			ISharedCondition_Comparable_String_All<MODEL, RESULT, OR_CLAUSES>> or() {

		@SuppressWarnings({ "unchecked", "rawtypes" })
		final ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, OR_CLAUSES> cb = new ISharedCollector_Functions_Callback_Alias<MODEL, RESULT, OR_CLAUSES>() {

			@Override
			public ISharedCondition_Comparable_Common_All<MODEL, RESULT, Comparable<?>, OR_CLAUSES> onComparable(CollectedFunctions functions, Supplier getter) {

				return orAliasImplComparable(functions, (Supplier)getter);
			}

			@Override
			public ISharedCondition_Comparable_String_Base<MODEL, RESULT, OR_CLAUSES> onString(CollectedFunctions functions, ISupplierString getter) {
				return orAliasImplString(functions, getter);
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
