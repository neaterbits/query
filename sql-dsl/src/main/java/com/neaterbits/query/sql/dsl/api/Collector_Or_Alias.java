package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.function.Consumer;
import java.util.function.Supplier;

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
		
	Collector_Or_Alias(Collector_Conditions_Intermediate<MODEL, RESULT, AFTER_GROUP_BY> qe) {
		super(qe);
	}

	Collector_Or_Alias(Collector_Conditions_Initial<MODEL, RESULT, AFTER_GROUP_BY> last) {
		super(last);
	}

	private <RR extends Comparable<RR>> ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, OR_CLAUSES> orAliasImplComparable(Supplier<RR> getter) {
		
		return orAliasImplComparable(makeGetterExpression(getter));
	}

	private <RR extends Comparable<RR>> ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, OR_CLAUSES> orAliasImplComparable(Expression expression) {
		
		return new Collector_Condition_Comparative<MODEL, RESULT, RR, OR_CLAUSES>(this, expression);
	}
	
	private ISharedCondition_Comparable_String_All<MODEL, RESULT, OR_CLAUSES> orAliasImplString(Expression expression) {

		return new Collector_Condition_String<MODEL, RESULT, OR_CLAUSES>(this, expression);
	}

	private <RR> ISharedCondition_SQLTimeType_All<MODEL, RESULT, RR, OR_CLAUSES> orAliasImplSQLTimeType(Supplier<RR> getter) {

		return new Collector_Condition_SQLTimeType<MODEL, RESULT, RR, OR_CLAUSES>(this, makeGetterExpression(getter));
	}

	private <RR> ISharedCondition_ByteArray_All<MODEL, RESULT, OR_CLAUSES> orAliasImplByteArray(Supplier<byte []> getter) {

		return new Collector_Condition_ByteArray<MODEL, RESULT, OR_CLAUSES>(this, makeGetterExpression(getter));
	}
	
	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, Boolean, OR_CLAUSES> or(ISupplierBoolean getter) {
		return orAliasImplComparable(getter);
	}

	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, Byte, OR_CLAUSES> or(ISupplierByte getter) {
		return orAliasImplComparable(getter);
	}
	
	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short, OR_CLAUSES> or(ISupplierShort getter) {
		return orAliasImplComparable(getter);
	}
	
	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, OR_CLAUSES> or(ISupplierInteger getter) {
		return orAliasImplComparable(getter);
	}

	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, OR_CLAUSES> or(ISupplierLong getter) {
		return orAliasImplComparable(getter);
	}
	
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigInteger, OR_CLAUSES> or(ISupplierBigInteger getter) {
		return orAliasImplComparable(getter);
	}

	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, Float, OR_CLAUSES> or(ISupplierFloat getter) {
		return orAliasImplComparable(getter);
	}
	
	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, OR_CLAUSES> or(ISupplierDouble getter) {
		return orAliasImplComparable(getter);
	}

	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, OR_CLAUSES> or(ISupplierBigDecimal getter) {
		return orAliasImplComparable(getter);
	}

	@Override
	public final ISharedCondition_Comparable_String_All<MODEL, RESULT, OR_CLAUSES> or(ISupplierString getter) {
		return orAliasImplString(makeGetterExpression(getter));
	}

	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, java.util.Date, OR_CLAUSES> or(ISupplierDate getter) {
		return orAliasImplComparable(getter);
	}

	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, java.util.Calendar, OR_CLAUSES> or(ISupplierCalendar getter) {
		return orAliasImplComparable(getter);
	}

	@Override
	public final ISharedCondition_SQLTimeType_All<MODEL, RESULT, java.sql.Date, OR_CLAUSES> or(ISupplierSQLDate getter) {
		return orAliasImplSQLTimeType(getter);
	}

	@Override
	public final ISharedCondition_SQLTimeType_All<MODEL, RESULT, java.sql.Time, OR_CLAUSES> or(ISupplierSQLTime getter) {
		return orAliasImplSQLTimeType(getter);
	}

	@Override
	public final ISharedCondition_SQLTimeType_All<MODEL, RESULT, java.sql.Timestamp, OR_CLAUSES> or(ISupplierSQLTimestamp getter) {
		return orAliasImplSQLTimeType(getter);
	}
	
	@Override
	public final ISharedCondition_ByteArray_All<MODEL, RESULT, OR_CLAUSES> or(ISupplierByteArray getter) {
		return orAliasImplByteArray(getter);
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

		final ISharedCollector_Functions_Callback<MODEL, RESULT, OR_CLAUSES> cb = new ISharedCollector_Functions_Callback<MODEL, RESULT, OR_CLAUSES>() {

			@Override
			public ISharedFunction_Next<MODEL, RESULT, OR_CLAUSES> onComparable(Expression expression) {
				return orAliasImplComparable(expression);
			}

			@Override
			public ISharedFunction_Next<MODEL, RESULT, OR_CLAUSES> onString(Expression expression) {
				return orAliasImplString(expression);
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
