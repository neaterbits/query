package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
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
		
		implements
		
			ISharedLogical_And_Named_All<MODEL, RESULT, AND_CLAUSES, NESTED_OR_CLAUSES>,
			
			ISharedLogical_And_Named_Function<
				MODEL,
				RESULT,
				AND_CLAUSES,
				NESTED_OR_CLAUSES /*,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, AND_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, AND_CLAUSES>,
				ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_CLAUSES>*/> {
			

	abstract Collector_Or_Named<MODEL, RESULT, NESTED_OR_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES, AFTER_GROUP_BY>
			createNestedOrCollector(Collector_And_Named<MODEL, RESULT, AND_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES, AFTER_GROUP_BY> andClauses);
			
	Collector_And_Named(Collector_Conditions_Initial<MODEL, RESULT, AFTER_GROUP_BY> last) {
		super(last);
	}
	
	Collector_And_Named(Collector_Conditions_Intermediate<MODEL, RESULT, AFTER_GROUP_BY> qe) {
		super(qe, null);
	}

	private final <T extends ISharedLogical_Or<MODEL, RESULT>> void addNestedOrImpl(Consumer<T> orBuilder) {
		super.addNestedOrImpl(orBuilder, createNestedOrCollector(this)); // new Classic_Collector_Or_NonProcessResult_Named<MODEL, RESULT>(this));
	}

	// TODO: avoid this reverse-mapping
	private <T, RR extends Comparable<RR>>
	
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, AND_CLAUSES> andClassImplComparable(Expression expression) {

		return new Collector_Condition_Comparative<MODEL, RESULT, RR, AND_CLAUSES>(this, expression);
	}
	
	private <T, RR extends Comparable<RR>>
	
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, AND_CLAUSES> andClassImplComparable(Function<T, RR> getter) {

		return andClassImplComparable(makeGetterExpression(getter));
	}

	private ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_CLAUSES>
		andClassImplString(Expression expression) {

			throw new UnsupportedOperationException("TODO");
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
		return new Collector_Condition_String<MODEL, RESULT, AND_CLAUSES>(this, makeGetterExpression(getter));
	}

	@SuppressWarnings("unchecked")
	@Override
	public final AND_CLAUSES andNest(ISharedNestedOrConsumerNamed<MODEL, RESULT, NESTED_OR_CLAUSES> orBuilder) {
		
		addNestedOrImpl(orBuilder);
		
		return (AND_CLAUSES)this;
	}

	@Override
	public final ISharedFunctions_Transform_Initial_Named<
				MODEL,
				RESULT,
				AND_CLAUSES,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short, AND_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, AND_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, AND_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, AND_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, AND_CLAUSES>,
				ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_CLAUSES>
			>
			and() {
		
		@SuppressWarnings({"unchecked", "rawtypes"})
		final ISharedCollector_Functions_Callback<MODEL, RESULT, AND_CLAUSES> cb
				= new ISharedCollector_Functions_Callback<MODEL, RESULT, AND_CLAUSES>() {

			@Override
			public ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Comparable<?>, AND_CLAUSES>
				onComparable(Expression expression) {
				
				return (ISharedCondition_Comparable_Common_All)andClassImplComparable(expression);
			}

			@Override
			public ISharedCondition_Comparable_String_Base<MODEL, RESULT, AND_CLAUSES>
				onString(Expression expression) {
				
				return andClassImplString(expression);
			}
		};

		return new Collector_ConditionFunctions_Named<>(cb);
	}

	@Override
	final Collector_GroupBy<MODEL, RESULT> createGroupByCollector(Collector_Base<MODEL> last, int[] groupByColumns,
			Collector_Conditions_GroupBy<MODEL, RESULT, ?> collectorConditions) {
		return new Collector_GroupBy_Named<>(last, groupByColumns, collectorConditions);
	}
}
