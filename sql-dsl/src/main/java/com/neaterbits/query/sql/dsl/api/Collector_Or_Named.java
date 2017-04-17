package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;
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
		
		implements ISharedLogical_Or_Named_All_And_Function<MODEL, RESULT, OR_CLAUSES, NESTED_AND_CLAUSES> {

	abstract Collector_And_Named<MODEL, RESULT, NESTED_AND_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES, AFTER_GROUP_BY>
			createNestedAndCollector(Collector_Or_Named<MODEL, RESULT, OR_CLAUSES, NESTED_AND_CLAUSES, NESTED_OR_CLAUSES, AFTER_GROUP_BY> orClauses);
			
	Collector_Or_Named(Collector_Conditions_Intermediate<MODEL, RESULT, AFTER_GROUP_BY> qe) {
		super(qe);
	}

	Collector_Or_Named(Collector_Conditions_Initial<MODEL, RESULT, AFTER_GROUP_BY> last) {
		super(last);
	}

	private <T, RR extends Comparable<RR>> ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, OR_CLAUSES>
	
		orClassImplComparable(Function<T, RR> getter) {

		return orClassImplComparable(new FieldExpression(getter));
	}

	private <T, RR extends Comparable<RR>> ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, OR_CLAUSES>
	
		orClassImplComparable(Expression expression) {

		return new Collector_Condition_Comparative<MODEL, RESULT, RR, OR_CLAUSES>(this, expression);
	}

	private ISharedCondition_Comparable_String_All<MODEL, RESULT, OR_CLAUSES> orClassImplString(Expression expression) {
		
		return new Collector_Condition_String<MODEL, RESULT, OR_CLAUSES>(this, expression);
	}
	
	private <T, RR> ISharedCondition_SQLTimeType_All<MODEL, RESULT, RR, OR_CLAUSES> orClassImplSQLTimeType(Function<T, RR> getter) {
			
		return new Collector_Condition_SQLTimeType<>(this, makeGetterExpression(getter));
	}
	
	private <T> ISharedCondition_ByteArray_All<MODEL, RESULT, OR_CLAUSES> orClassImplByteArray(Function<T, byte []> getter) {
		
		return new Collector_Condition_ByteArray<>(this, makeGetterExpression(getter));
	}

	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Boolean, OR_CLAUSES> or(IFunctionBoolean<T> getter) {
		return orClassImplComparable(getter);
	}

	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Byte, OR_CLAUSES> or(IFunctionByte<T> getter) {
		return orClassImplComparable(getter);
	}

	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short, OR_CLAUSES> or(IFunctionShort<T> getter) {
		return orClassImplComparable(getter);
	}

	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, OR_CLAUSES> or(IFunctionInteger<T> getter) {
		return orClassImplComparable(getter);
	}

	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, OR_CLAUSES> or(IFunctionLong<T> getter) {
		return orClassImplComparable(getter);
	}
	
	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigInteger, OR_CLAUSES> or(IFunctionBigInteger<T> getter) {
		return orClassImplComparable(getter);
	}
	
	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Float, OR_CLAUSES> or(IFunctionFloat<T> getter) {
		return orClassImplComparable(getter);
	}

	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, OR_CLAUSES> or(IFunctionDouble<T> getter) {
		return orClassImplComparable(getter);
	}
	
	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, OR_CLAUSES> or(IFunctionBigDecimal<T> getter) {
		return orClassImplComparable(getter);
	}

	@Override
	public final <T> ISharedCondition_Comparable_String_All<MODEL, RESULT, OR_CLAUSES> or(StringFunction<T> getter) {
		return orClassImplString(new FieldExpression(getter));
	}

	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, java.util.Date, OR_CLAUSES> or(IFunctionDate<T> getter) {
		return orClassImplComparable(getter);
	}

	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, java.util.Calendar, OR_CLAUSES> or(IFunctionCalendar<T> getter) {
		return orClassImplComparable(getter);
	}

	@Override
	public final <T> ISharedCondition_SQLTimeType_All<MODEL, RESULT, java.sql.Date, OR_CLAUSES> or(IFunctionSQLDate<T> getter) {
		return orClassImplSQLTimeType(getter);
	}
	
	@Override
	public final <T> ISharedCondition_SQLTimeType_All<MODEL, RESULT, java.sql.Time, OR_CLAUSES> or(IFunctionSQLTime<T> getter) {
		return orClassImplSQLTimeType(getter);
	}

	@Override
	public final <T> ISharedCondition_SQLTimeType_All<MODEL, RESULT, java.sql.Timestamp, OR_CLAUSES> or(IFunctionSQLTimestamp<T> getter) {
		return orClassImplSQLTimeType(getter);
	}
	
	@Override
	public final <T> ISharedCondition_ByteArray_All<MODEL, RESULT, OR_CLAUSES> or(IFunctionByteArray<T> getter) {
		return orClassImplByteArray(getter);
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
	public final ISharedFunctions_Transform_Initial_Named<
			MODEL,
			RESULT,
			OR_CLAUSES,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Byte, OR_CLAUSES>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short, OR_CLAUSES>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, OR_CLAUSES>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, OR_CLAUSES>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigInteger, OR_CLAUSES>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Float, OR_CLAUSES>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, OR_CLAUSES>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, OR_CLAUSES>,
			ISharedCondition_Comparable_String_All<MODEL, RESULT, OR_CLAUSES>> or() {

		final ISharedCollector_Functions_Callback<MODEL, RESULT, OR_CLAUSES> cb = new ISharedCollector_Functions_Callback<MODEL, RESULT, OR_CLAUSES>() {

			@Override
			public ISharedCondition_Comparable_Common_All<MODEL, RESULT, Comparable<?>, OR_CLAUSES> onComparable(Expression expression) {

				return (ISharedCondition_Comparable_Common_All)orClassImplComparable(expression);
			}

			@Override
			public ISharedCondition_Comparable_String_Base<MODEL, RESULT, OR_CLAUSES> onString(Expression expression) {
				return orClassImplString(expression);
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
