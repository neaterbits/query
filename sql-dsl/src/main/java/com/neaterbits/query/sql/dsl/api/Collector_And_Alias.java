package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;
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
		
	Collector_And_Alias(Collector_Conditions_Base<MODEL, RESULT> qe, Void disambiguate) {
		super(qe, disambiguate);
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

		return andAliasImplComparable(makeGetterExpression(getter));
	}

	private <RR extends Comparable<RR>,
		M_AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, M_AND_CLAUSES, NESTED_OR_CLAUSES>,
		NESTED_OR_CLAUSES extends ISharedLogical_Or_Alias<MODEL, RESULT>>
		
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, M_AND_CLAUSES> andAliasImplComparable(Expression expression) {

		//final Expression expression = makeExpression(functions, makeGetterExpression(getter));
		
		return new Collector_Condition_Comparative<MODEL, RESULT, RR, M_AND_CLAUSES>(this, expression);
	}

	private ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_CLAUSES>
		andAliasImplString(Expression expression) {
		
		return new Collector_Condition_String<MODEL, RESULT, AND_CLAUSES>(this, expression);
	}

	private <RR> ISharedCondition_SQLTimeType_All<MODEL, RESULT, RR, AND_CLAUSES>
		andAliasImplSQLTimeType(Supplier<RR> getter) {
		
		return new Collector_Condition_SQLTimeType<MODEL, RESULT, RR, AND_CLAUSES>(this, makeGetterExpression(getter));
	}
		
	private <RR> ISharedCondition_ByteArray_All<MODEL, RESULT, AND_CLAUSES>
		andAliasImplByteArray(Supplier<byte[]> getter) {
		
		return new Collector_Condition_ByteArray<MODEL, RESULT, AND_CLAUSES>(this, makeGetterExpression(getter));
	}

	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, Boolean, AND_CLAUSES> and(ISupplierBoolean getter) {
		return andAliasImplComparable(getter);
	}

	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, Byte, AND_CLAUSES> and(ISupplierByte getter) {
		return andAliasImplComparable(getter);
	}

	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short, AND_CLAUSES> and(ISupplierShort getter) {
		return andAliasImplComparable(getter);
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
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigInteger, AND_CLAUSES> and(ISupplierBigInteger getter) {
		return andAliasImplComparable(getter);
	}

	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, Float, AND_CLAUSES> and(ISupplierFloat getter) {
		return andAliasImplComparable(getter);
	}
	
	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, AND_CLAUSES> and(ISupplierDouble getter) {
		return andAliasImplComparable(getter);
	}
	
	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, AND_CLAUSES> and(ISupplierBigDecimal getter) {
		return andAliasImplComparable(getter);
	}

	@Override
	public final ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_CLAUSES> and(ISupplierString getter) {
		return andAliasImplString(makeGetterExpression(getter));
	}

	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, java.util.Date, AND_CLAUSES> and(ISupplierDate getter) {
		return andAliasImplComparable(getter);
	}

	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, java.util.Calendar, AND_CLAUSES> and(ISupplierCalendar getter) {
		return andAliasImplComparable(getter);
	}
	
	@Override
	public final ISharedCondition_SQLTimeType_All<MODEL, RESULT, java.sql.Date, AND_CLAUSES> and(ISupplierSQLDate getter) {
		return andAliasImplSQLTimeType(getter);
	}

	@Override
	public final ISharedCondition_SQLTimeType_All<MODEL, RESULT, java.sql.Time, AND_CLAUSES> and(ISupplierSQLTime getter) {
		return andAliasImplSQLTimeType(getter);
	}
	
	@Override
	public final ISharedCondition_SQLTimeType_All<MODEL, RESULT, java.sql.Timestamp, AND_CLAUSES> and(ISupplierSQLTimestamp getter) {
		return andAliasImplSQLTimeType(getter);
	}

	@Override
	public final ISharedCondition_ByteArray_All<MODEL, RESULT, AND_CLAUSES> and(ISupplierByteArray getter) {
		return andAliasImplByteArray(getter);
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
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Byte, AND_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short, AND_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, AND_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, AND_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigInteger, AND_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Float, AND_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, AND_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, AND_CLAUSES>,
				ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_CLAUSES>> and() {

					
		final ISharedCollector_Functions_Callback<MODEL, RESULT, AND_CLAUSES> cb
				= new ISharedCollector_Functions_Callback<MODEL, RESULT, AND_CLAUSES>() {

			@Override
			public ISharedFunction_Next<MODEL, RESULT, AND_CLAUSES> onComparable(Expression expression) {
				return andAliasImplComparable(expression);
			}

			@Override
			public ISharedFunction_Next<MODEL, RESULT, AND_CLAUSES> onString(Expression expression) {
				return andAliasImplString(expression);
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
