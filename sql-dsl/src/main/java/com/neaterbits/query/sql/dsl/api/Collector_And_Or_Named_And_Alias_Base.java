package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/*
 * Move here, shared code for both named and alias case
 * so that we can reuse for case when we do not yet know
 * named or alias at time of where - clauses 
 * 
 */

abstract class Collector_And_Or_Named_And_Alias_Base<
			MODEL,
			RESULT,

			NAMED_AND_CLAUSES extends ISharedLogical_And_Named_All<MODEL, RESULT, NAMED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES>,
			NAMED_OR_CLAUSES  extends ISharedLogical_Or_Named_All <MODEL, RESULT, NAMED_OR_CLAUSES,  NAMED_NESTED_AND_CLAUSES>,

			NAMED_NESTED_AND_CLAUSES extends ISharedLogical_And_Named_All<MODEL, RESULT, NAMED_NESTED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES>,
			NAMED_NESTED_OR_CLAUSES  extends ISharedLogical_Or_Named_All<MODEL, RESULT, NAMED_NESTED_OR_CLAUSES,  NAMED_NESTED_AND_CLAUSES>,			
			
			NAMED_AFTER_GROUP_BY,
			
			ALIAS_AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, ALIAS_AND_CLAUSES, ALIAS_NESTED_OR_CLAUSES>,
			ALIAS_OR_CLAUSES  extends ISharedLogical_Or_Alias_Base <MODEL, RESULT, ALIAS_OR_CLAUSES,  ALIAS_NESTED_AND_CLAUSES>,

			ALIAS_NESTED_AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, ALIAS_NESTED_AND_CLAUSES, ALIAS_NESTED_OR_CLAUSES>,
			ALIAS_NESTED_OR_CLAUSES  extends ISharedLogical_Or_Alias_Base <MODEL, RESULT, ALIAS_NESTED_OR_CLAUSES,  ALIAS_NESTED_AND_CLAUSES>,			
			
			ALIAS_AFTER_GROUP_BY,
			
			
			THIS_AFTER_GROUP_BY>


	extends Collector_And_Or_Base<MODEL, RESULT, THIS_AFTER_GROUP_BY>

	implements
		ISharedLogical_And_Named_All<MODEL, RESULT, NAMED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES>,
		ISharedLogical_Or_Named_All<MODEL, RESULT, NAMED_OR_CLAUSES, NAMED_NESTED_AND_CLAUSES>,
		ISharedLogical_And_Alias_Base<MODEL, RESULT, ALIAS_AND_CLAUSES, ALIAS_NESTED_OR_CLAUSES>,
		ISharedLogical_Or_Alias_Base<MODEL, RESULT, ALIAS_OR_CLAUSES, ALIAS_NESTED_AND_CLAUSES>

		/* Don't know why this was here, because it adds functions like or() and cannot be in shared named/alias baseclass due to signature collision  
		ISharedLogical_And_Or_Alias<
			MODEL,
			RESULT,
			ALIAS_AND_CLAUSES,
			ALIAS_OR_CLAUSES,
			ALIAS_NESTED_AND_CLAUSES,
			ALIAS_NESTED_OR_CLAUSES> */	{

				
				
	Collector_And_Or_Named_And_Alias_Base(Collector_Conditions_Initial<MODEL, RESULT, THIS_AFTER_GROUP_BY> last, EConditionsClause conditionsClause) {
		super(last, conditionsClause);
	}

	Collector_And_Or_Named_And_Alias_Base(Collector_Conditions_Initial<MODEL, RESULT, THIS_AFTER_GROUP_BY> last, EConditionsClause conditionsClause, CollectedQueryResult result) {
		super(last, conditionsClause, result);
	}

	Collector_And_Or_Named_And_Alias_Base(Collector_Query<MODEL> queryCollector, Collector_Clause collector) {
		super(queryCollector, collector);
	}
	
	Collector_And_Or_Named_And_Alias_Base(Collector_GroupBy<MODEL, RESULT> last, EConditionsClause conditionsClause) {
		super(last, conditionsClause);
	}
	
	
	
	
	/*********************************************************************************************
	 * Named
	 ********************************************************************************************/

	abstract Collector_Or_Named<MODEL, RESULT, NAMED_OR_CLAUSES, NAMED_NESTED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES, NAMED_AFTER_GROUP_BY> createNamedOrCollector();
    abstract Collector_And_Named<MODEL, RESULT, NAMED_AND_CLAUSES, NAMED_NESTED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES, NAMED_AFTER_GROUP_BY> createNamedAndCollector();
	
	abstract Collector_Or_Named<MODEL, RESULT, NAMED_NESTED_OR_CLAUSES, NAMED_NESTED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES, NAMED_AFTER_GROUP_BY> createNamedNestedOrCollector(
				Collector_And_Named<MODEL, RESULT, NAMED_AND_CLAUSES, NAMED_NESTED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES, NAMED_AFTER_GROUP_BY> andClauses);
	
    abstract Collector_And_Named<MODEL, RESULT, NAMED_NESTED_AND_CLAUSES, NAMED_NESTED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES, NAMED_AFTER_GROUP_BY> createNamedNestedAndCollector(
    			Collector_Or_Named<MODEL, RESULT, NAMED_OR_CLAUSES, NAMED_NESTED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES, NAMED_AFTER_GROUP_BY> orClauses);
	
	// ------------------------  AND ------------------------


	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Boolean, NAMED_AND_CLAUSES> and(IFunctionBoolean<T> getter) {
		return andNamedClassImplComparative(new FieldExpression(getter));
	}

	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Byte, NAMED_AND_CLAUSES> and(IFunctionByte<T> getter) {
		return andNamedClassImplComparative(new FieldExpression(getter));
	}

	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short, NAMED_AND_CLAUSES> and(IFunctionShort<T> getter) {
		return andNamedClassImplComparative(new FieldExpression(getter));
	}

	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, NAMED_AND_CLAUSES> and(IFunctionInteger<T> getter) {
		return andNamedClassImplComparative(new FieldExpression(getter));
	}

	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, NAMED_AND_CLAUSES> and(IFunctionLong<T> getter) {
		return andNamedClassImplComparative(new FieldExpression(getter));
	}

	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigInteger, NAMED_AND_CLAUSES> and(IFunctionBigInteger<T> getter) {
		return andNamedClassImplComparative(new FieldExpression(getter));
	}

	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Float, NAMED_AND_CLAUSES> and(IFunctionFloat<T> getter) {
		return andNamedClassImplComparative(new FieldExpression(getter));
	}

	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, NAMED_AND_CLAUSES> and(IFunctionDouble<T> getter) {
		return andNamedClassImplComparative(new FieldExpression(getter));
	}

	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, NAMED_AND_CLAUSES> and(IFunctionBigDecimal<T> getter) {
		return andNamedClassImplComparative(new FieldExpression(getter));
	}

	@Override
	public final <T> ISharedCondition_Comparable_String_All<MODEL, RESULT, NAMED_AND_CLAUSES> and(StringFunction<T> getter) {
		return andNamedClassImplString(new FieldExpression(getter));
	}

	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, java.util.Date, NAMED_AND_CLAUSES> and(IFunctionDate<T> getter) {
		return andNamedClassImplComparative(new FieldExpression(getter));
	}

	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, java.util.Calendar, NAMED_AND_CLAUSES> and(IFunctionCalendar<T> getter) {
		return andNamedClassImplComparative(new FieldExpression(getter));
	}

	@Override
	public final <T> ISharedCondition_SQLTimeType_All<MODEL, RESULT, java.sql.Date, NAMED_AND_CLAUSES> and(IFunctionSQLDate<T> getter) {
		return andNamedImplSQLTimeType(getter);
	}
	
	@Override
	public final <T> ISharedCondition_SQLTimeType_All<MODEL, RESULT, java.sql.Time, NAMED_AND_CLAUSES> and(IFunctionSQLTime<T> getter) {
		return andNamedImplSQLTimeType(getter);
	}
	
	@Override
	public final <T> ISharedCondition_SQLTimeType_All<MODEL, RESULT, java.sql.Timestamp, NAMED_AND_CLAUSES> and(IFunctionSQLTimestamp<T> getter) {
		return andNamedImplSQLTimeType(getter);
	}
	
	@Override
	public final <T> ISharedCondition_ByteArray_All<MODEL, RESULT, NAMED_AND_CLAUSES> and(IFunctionByteArray<T> getter) {
		return andNamedImplByteArray(getter);
	}
	
	final ISharedFunctions_Transform_Initial_Named<
				MODEL,
				RESULT,
				NAMED_AND_CLAUSES,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Byte, NAMED_AND_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short, NAMED_AND_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, NAMED_AND_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, NAMED_AND_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigInteger, NAMED_AND_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Float, NAMED_AND_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, NAMED_AND_CLAUSES>,
				ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, NAMED_AND_CLAUSES>,
				ISharedCondition_Comparable_String_All<MODEL, RESULT, NAMED_AND_CLAUSES>
			> andNamed() {
					
		final ISharedCollector_Functions_Callback<MODEL, RESULT, NAMED_AND_CLAUSES> cb
				= new ISharedCollector_Functions_Callback<MODEL, RESULT, NAMED_AND_CLAUSES>() {
		
					// Condition_Comparable_Common_Base<MODEL, RESULT, Comparable<?>, NAMED_AND_CLAUSES>
			@Override
			public ISharedFunction_Next<MODEL, RESULT, NAMED_AND_CLAUSES>
				onComparable(Expression expression) {
				
				return andNamedClassImplComparable(expression);
			}
		
			@Override
			public ISharedCondition_Comparable_String_Base<MODEL, RESULT, NAMED_AND_CLAUSES>
				onString(Expression expression) {
				
				return andNamedClassImplString(expression);
			}
		};

		return new Collector_ConditionFunctions_Named<>(cb);
		
	}
	
	// ------------------------  OR ------------------------

	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Boolean, NAMED_OR_CLAUSES> or(IFunctionBoolean<T> getter) {
		return orNamedClassImplComparable(new FieldExpression(getter));
	}

	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Byte, NAMED_OR_CLAUSES> or(IFunctionByte<T> getter) {
		return orNamedClassImplComparable(new FieldExpression(getter));
	}

	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short, NAMED_OR_CLAUSES> or(IFunctionShort<T> getter) {
		return orNamedClassImplComparable(new FieldExpression(getter));
	}

	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, NAMED_OR_CLAUSES> or(IFunctionInteger<T> getter) {
		return orNamedClassImplComparable(new FieldExpression(getter));
	}

	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, NAMED_OR_CLAUSES> or(IFunctionLong<T> getter) {
		return orNamedClassImplComparable(new FieldExpression(getter));
	}

	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigInteger, NAMED_OR_CLAUSES> or(IFunctionBigInteger<T> getter) {
		return orNamedClassImplComparable(new FieldExpression(getter));
	}

	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, NAMED_OR_CLAUSES> or(IFunctionBigDecimal<T> getter) {
		return orNamedClassImplComparable(new FieldExpression(getter));
	}

	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Float, NAMED_OR_CLAUSES> or(IFunctionFloat<T> getter) {
		return orNamedClassImplComparable(new FieldExpression(getter));
	}

	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, NAMED_OR_CLAUSES> or(IFunctionDouble<T> getter) {
		return orNamedClassImplComparable(new FieldExpression(getter));
	}

	@Override
	public final <T> ISharedCondition_Comparable_String_All<MODEL, RESULT, NAMED_OR_CLAUSES> or(StringFunction<T> getter) {
		return orNamedClassImplString(new FieldExpression(getter));
	}

	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, java.util.Date, NAMED_OR_CLAUSES> or(IFunctionDate<T> getter) {
		return orNamedClassImplComparable(new FieldExpression(getter));
	}
	
	@Override
	public final <T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, java.util.Calendar, NAMED_OR_CLAUSES> or(IFunctionCalendar<T> getter) {
		return orNamedClassImplComparable(new FieldExpression(getter));
	}

	@Override
	public final <T> ISharedCondition_SQLTimeType_All<MODEL, RESULT, Date, NAMED_OR_CLAUSES> or(IFunctionSQLDate<T> getter) {
		return orNamedImplSQLTimeType(makeGetterExpression(getter));
	}

	@Override
	public final <T> ISharedCondition_SQLTimeType_All<MODEL, RESULT, Time, NAMED_OR_CLAUSES> or(IFunctionSQLTime<T> getter) {
		return orNamedImplSQLTimeType(makeGetterExpression(getter));
	}

	@Override
	public final <T> ISharedCondition_SQLTimeType_All<MODEL, RESULT, Timestamp, NAMED_OR_CLAUSES> or(IFunctionSQLTimestamp<T> getter) {
		return orNamedImplSQLTimeType(makeGetterExpression(getter));
	}

	@Override
	public final <T> ISharedCondition_ByteArray_All<MODEL, RESULT, NAMED_OR_CLAUSES> or(IFunctionByteArray<T> getter) {
		return orNamedImplByteArray(makeGetterExpression(getter));
	}

	final ISharedFunctions_Transform_Initial_Named<
		MODEL,
		RESULT,
		NAMED_OR_CLAUSES,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Byte, NAMED_OR_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short, NAMED_OR_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, NAMED_OR_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, NAMED_OR_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigInteger, NAMED_OR_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Float, NAMED_OR_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, NAMED_OR_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, NAMED_OR_CLAUSES>,
		ISharedCondition_Comparable_String_All<MODEL, RESULT, NAMED_OR_CLAUSES>
	>
		orNamed() {

		@SuppressWarnings({"unchecked", "rawtypes"})
		final ISharedCollector_Functions_Callback<MODEL, RESULT, NAMED_OR_CLAUSES> cb
				= new ISharedCollector_Functions_Callback<MODEL, RESULT, NAMED_OR_CLAUSES>() {
		
			@Override
			public ISharedCondition_Comparable_Common_Base<MODEL, RESULT, Comparable<?>, NAMED_OR_CLAUSES> onComparable(Expression expression) {
				return (ISharedCondition_Comparable_Common_All)orNamedClassImplComparable(expression);
			}
		
			@Override
			public ISharedCondition_Comparable_String_Base<MODEL, RESULT, NAMED_OR_CLAUSES> onString(Expression expression) {
				return (ISharedCondition_Comparable_String_Base)orNamedClassImplString(expression);
			}
		};

		return new Collector_ConditionFunctions_Named<>(cb);
	}
	
	// ------------------------  AND helpers ------------------------

	private <T, RR extends Comparable<RR>>
			
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, NAMED_AND_CLAUSES> andNamedClassImplComparative(Expression expression) {
		
		return andNamedClassImplComparable(expression);
	}

	final <T, RR extends Comparable<RR>>
		
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, NAMED_AND_CLAUSES> 
	
				andNamedClassImplComparable(Expression expression) {
	
		final Collector_And_Named<MODEL, RESULT, NAMED_AND_CLAUSES, NAMED_NESTED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES, NAMED_AFTER_GROUP_BY> andClauses = createNamedAndCollector(); // new Classic_Collector_And_Named<>(this);
	
		return new Collector_Condition_Comparative<MODEL, RESULT, RR, NAMED_AND_CLAUSES>(andClauses, expression);
	}
		
		
	final ISharedCondition_Comparable_String_All<MODEL, RESULT, NAMED_AND_CLAUSES> andNamedClassImplString(Expression expression) {
		
		final Collector_And_Named<MODEL, RESULT, NAMED_AND_CLAUSES, NAMED_NESTED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES, NAMED_AFTER_GROUP_BY> andClauses = createNamedAndCollector(); // new Classic_Collector_And_Named<>(this);
		
		return new Collector_Condition_String<MODEL, RESULT, NAMED_AND_CLAUSES>(andClauses, expression);
	}

	private <T, RR> ISharedCondition_SQLTimeType_All<MODEL, RESULT, RR, NAMED_AND_CLAUSES> andNamedImplSQLTimeType(Function<T, RR> getter) {
	
		final Collector_And_Named<MODEL, RESULT, NAMED_AND_CLAUSES, NAMED_NESTED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES, NAMED_AFTER_GROUP_BY> andClauses = createNamedAndCollector(); // new Classic_Collector_And_Named<>(this);
		
		return new Collector_Condition_SQLTimeType<MODEL, RESULT, RR, NAMED_AND_CLAUSES>(andClauses, makeGetterExpression(getter));
	}
	
	private <T> ISharedCondition_ByteArray_All<MODEL, RESULT, NAMED_AND_CLAUSES> andNamedImplByteArray(Function<T, byte []> getter) {
		
		final Collector_And_Named<MODEL, RESULT, NAMED_AND_CLAUSES, NAMED_NESTED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES, NAMED_AFTER_GROUP_BY> andClauses = createNamedAndCollector(); // new Classic_Collector_And_Named<>(this);
		
		return new Collector_Condition_ByteArray<MODEL, RESULT, NAMED_AND_CLAUSES>(andClauses, makeGetterExpression(getter));
	}	
	// ------------------------  OR helpers ------------------------
	private <T, RR extends Comparable<RR>>
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, NAMED_OR_CLAUSES>
		
		orNamedClassImplComparable(Expression expression) {
		
		final Collector_Or_Named<MODEL, RESULT, NAMED_OR_CLAUSES, NAMED_NESTED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES, NAMED_AFTER_GROUP_BY> andClauses = createNamedOrCollector(); // new Classic_Collector_Or_Named<>(this);

		return new Collector_Condition_Comparative<MODEL, RESULT, RR, NAMED_OR_CLAUSES>(andClauses, expression);
	}

	final ISharedCondition_Comparable_String_All<MODEL, RESULT, NAMED_OR_CLAUSES>
				orNamedClassImplString(Expression expression) {

		final Collector_Or_Named<MODEL, RESULT, NAMED_OR_CLAUSES, NAMED_NESTED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES, NAMED_AFTER_GROUP_BY> andClauses = createNamedOrCollector(); // new Classic_Collector_Or_Named<>(this);

		return new Collector_Condition_String<MODEL, RESULT, NAMED_OR_CLAUSES>(andClauses, expression);
	}
				
	private <RR> ISharedCondition_SQLTimeType_All<MODEL, RESULT, RR, NAMED_OR_CLAUSES> orNamedImplSQLTimeType(Expression expression) {
		final Collector_Or_Named<MODEL, RESULT, NAMED_OR_CLAUSES, NAMED_NESTED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES, NAMED_AFTER_GROUP_BY> andClauses = createNamedOrCollector();

		return new Collector_Condition_SQLTimeType<MODEL, RESULT, RR, NAMED_OR_CLAUSES>(andClauses, expression);
	}
	
	private <RR> ISharedCondition_ByteArray_All<MODEL, RESULT, NAMED_OR_CLAUSES> orNamedImplByteArray(Expression expression) {
		final Collector_Or_Named<MODEL, RESULT, NAMED_OR_CLAUSES, NAMED_NESTED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES, NAMED_AFTER_GROUP_BY> andClauses = createNamedOrCollector();

		return new Collector_Condition_ByteArray<MODEL, RESULT, NAMED_OR_CLAUSES>(andClauses, expression);
	}

	// ------------------------  OR helpers ------------------------
	private final <T extends ISharedLogical_And<MODEL, RESULT>>
		Collector_Or_Named<MODEL, RESULT, NAMED_OR_CLAUSES, NAMED_NESTED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES, NAMED_AFTER_GROUP_BY>
				addNamedNestedAndImpl(Consumer<T> orBuilder) {
				
	
		final Collector_Or_Named<MODEL, RESULT, NAMED_OR_CLAUSES, NAMED_NESTED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES, NAMED_AFTER_GROUP_BY> orClauses = createNamedOrCollector(); // new Classic_Collector_Or_Named<>(this);
		
		// Add to new OR
		orClauses.addNestedAndImpl(orBuilder, createNamedNestedAndCollector(orClauses)); // new Classic_Collector_And_NonProcessResult_Named<MODEL, RESULT>(orClauses));
		
		return orClauses;
	}
	
	private final <T extends ISharedLogical_Or<MODEL, RESULT>>
		Collector_And_Named<MODEL, RESULT, NAMED_AND_CLAUSES, NAMED_NESTED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES, NAMED_AFTER_GROUP_BY>
				addNamedNestedOrImpl(Consumer<T> orBuilder) {

		final Collector_And_Named<MODEL, RESULT, NAMED_AND_CLAUSES, NAMED_NESTED_AND_CLAUSES, NAMED_NESTED_OR_CLAUSES, NAMED_AFTER_GROUP_BY> andClauses = createNamedAndCollector(); // new Classic_Collector_And_Named<>(this);

		// Add to new AND clause
		andClauses.addNestedOrImpl(orBuilder, createNamedNestedOrCollector(andClauses)); // new Classic_Collector_Or_NonProcessResult_Named<MODEL, RESULT>(andClauses));
		
		return andClauses;
	}

	@SuppressWarnings("unchecked")
	@Override
	public final NAMED_AND_CLAUSES andNest(ISharedNestedOrConsumerNamed<MODEL, RESULT, NAMED_NESTED_OR_CLAUSES> orBuilder) {
		return (NAMED_AND_CLAUSES)addNamedNestedOrImpl(orBuilder);
	}

	@SuppressWarnings("unchecked")
	@Override
	public final NAMED_OR_CLAUSES orNest(ISharedNestedAndConsumerNamed<MODEL, RESULT, NAMED_NESTED_AND_CLAUSES> andBuilder) {
		return (NAMED_OR_CLAUSES)addNamedNestedAndImpl(andBuilder);
	}

	
	/*********************************************************************************************
	 * Alias
	 ********************************************************************************************/

	abstract Collector_Or_Alias<MODEL, RESULT, ALIAS_OR_CLAUSES, ALIAS_NESTED_AND_CLAUSES, ALIAS_NESTED_OR_CLAUSES, ALIAS_AFTER_GROUP_BY> createAliasOrCollector();
    abstract Collector_And_Alias<MODEL, RESULT, ALIAS_AND_CLAUSES, ALIAS_NESTED_AND_CLAUSES, ALIAS_NESTED_OR_CLAUSES, ALIAS_AFTER_GROUP_BY> createAliasAndCollector();

	abstract Collector_Or_Alias<MODEL, RESULT, ALIAS_NESTED_OR_CLAUSES, ALIAS_NESTED_AND_CLAUSES, ALIAS_NESTED_OR_CLAUSES, ALIAS_AFTER_GROUP_BY>
			createAliasNestedOrCollector(Collector_And_Alias<MODEL, RESULT, ALIAS_AND_CLAUSES, ALIAS_NESTED_AND_CLAUSES, ALIAS_NESTED_OR_CLAUSES, ALIAS_AFTER_GROUP_BY> andClauses);
			
    abstract Collector_And_Alias<MODEL, RESULT, ALIAS_NESTED_AND_CLAUSES, ALIAS_NESTED_AND_CLAUSES, ALIAS_NESTED_OR_CLAUSES, ALIAS_AFTER_GROUP_BY>
    		createAliasNestedAndCollector(Collector_Or_Alias<MODEL, RESULT, ALIAS_OR_CLAUSES, ALIAS_NESTED_AND_CLAUSES, ALIAS_NESTED_OR_CLAUSES, ALIAS_AFTER_GROUP_BY> orClauses);
	
	// ------------------------  AND ------------------------
	
	
	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, Boolean, ALIAS_AND_CLAUSES> and(ISupplierBoolean getter) {
		return andAliasImplComparable(getter);
	}
	
	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, Byte, ALIAS_AND_CLAUSES> and(ISupplierByte getter) {
		return andAliasImplComparable(getter);
	}
	
	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short, ALIAS_AND_CLAUSES> and(ISupplierShort getter) {
		return andAliasImplComparable(getter);
	}
	
	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, ALIAS_AND_CLAUSES> and(ISupplierInteger getter) {
		return andAliasImplComparable(getter);
	}
	
	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, ALIAS_AND_CLAUSES> and(ISupplierLong getter) {
		return andAliasImplComparable(getter);
	}
	
	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigInteger, ALIAS_AND_CLAUSES> and(ISupplierBigInteger getter) {
		return andAliasImplComparable(getter);
	}

	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, Float, ALIAS_AND_CLAUSES> and(ISupplierFloat getter) {
		return andAliasImplComparable(getter);
	}
	
	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, ALIAS_AND_CLAUSES> and(ISupplierDouble getter) {
		return andAliasImplComparable(getter);
	}

	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, ALIAS_AND_CLAUSES> and(ISupplierBigDecimal getter) {
		return andAliasImplComparable(getter);
	}

	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, java.util.Date, ALIAS_AND_CLAUSES> and(ISupplierDate getter) {
		return andAliasImplComparable(getter);
	}
	
	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, java.util.Calendar, ALIAS_AND_CLAUSES> and(ISupplierCalendar getter) {
		return andAliasImplComparable(getter);
	}
	
	@Override
	public final ISharedCondition_SQLTimeType_All<MODEL, RESULT, java.sql.Date, ALIAS_AND_CLAUSES> and(ISupplierSQLDate getter) {
		return andAliasImplSQLTimeType(getter);
	}
	
	@Override
	public final ISharedCondition_SQLTimeType_All<MODEL, RESULT, java.sql.Time, ALIAS_AND_CLAUSES> and(ISupplierSQLTime getter) {
		return andAliasImplSQLTimeType(getter);
	}

	@Override
	public final ISharedCondition_SQLTimeType_All<MODEL, RESULT, java.sql.Timestamp, ALIAS_AND_CLAUSES> and(ISupplierSQLTimestamp getter) {
		return andAliasImplSQLTimeType(getter);
	}

	@Override
	public final ISharedCondition_ByteArray_All<MODEL, RESULT, ALIAS_AND_CLAUSES> and(ISupplierByteArray getter) {
		return andAliasImplByteArray(getter);
	}

	@Override
	public final ISharedCondition_Comparable_String_All<MODEL, RESULT, ALIAS_AND_CLAUSES> and(ISupplierString getter) {
	
		final Collector_And_Alias<MODEL, RESULT, ALIAS_AND_CLAUSES, ALIAS_NESTED_AND_CLAUSES, ALIAS_NESTED_OR_CLAUSES, ALIAS_AFTER_GROUP_BY> andClauses = createAliasAndCollector();// new Classic_Collector_And_Alias<>(this);
		
		return new Collector_Condition_String<MODEL, RESULT, ALIAS_AND_CLAUSES>(andClauses, makeGetterExpression(getter));
	}


	// ------------------------  OR ------------------------

	
	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, Boolean, ALIAS_OR_CLAUSES> or(ISupplierBoolean getter) {
		return orAliasImplComparable(getter);
	}

	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, Byte, ALIAS_OR_CLAUSES> or(ISupplierByte getter) {
		return orAliasImplComparable(getter);
	}

	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short, ALIAS_OR_CLAUSES> or(ISupplierShort getter) {
		return orAliasImplComparable(getter);
	}
	
	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, ALIAS_OR_CLAUSES> or(ISupplierInteger getter) {
		return orAliasImplComparable(getter);
	}

	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, ALIAS_OR_CLAUSES> or(ISupplierLong getter) {
		return orAliasImplComparable(getter);
	}
	
	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigInteger, ALIAS_OR_CLAUSES> or(ISupplierBigInteger getter) {
		return orAliasImplComparable(getter);
	}

	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, Float, ALIAS_OR_CLAUSES> or(ISupplierFloat getter) {
		return orAliasImplComparable(getter);
	}

	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, ALIAS_OR_CLAUSES> or(ISupplierDouble getter) {
		return orAliasImplComparable(getter);
	}
	
	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, ALIAS_OR_CLAUSES> or(ISupplierBigDecimal getter) {
		return orAliasImplComparable(getter);
	}

	@Override
	public final ISharedCondition_Comparable_String_All<MODEL, RESULT, ALIAS_OR_CLAUSES> or(ISupplierString getter) {
		return orAliasImplString(makeGetterExpression(getter));
	}

	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, java.util.Date, ALIAS_OR_CLAUSES> or(ISupplierDate getter) {
		return orAliasImplComparable(getter);
	}
	
	@Override
	public final ISharedCondition_Comparable_Common_All<MODEL, RESULT, java.util.Calendar, ALIAS_OR_CLAUSES> or(ISupplierCalendar getter) {
		return orAliasImplComparable(getter);
	}

	@Override
	public final ISharedCondition_SQLTimeType_All<MODEL, RESULT, java.sql.Date, ALIAS_OR_CLAUSES> or(ISupplierSQLDate getter) {
		return orAliasImplSQLTimeType(getter);
	}
	
	@Override
	public final ISharedCondition_SQLTimeType_All<MODEL, RESULT, java.sql.Time, ALIAS_OR_CLAUSES> or(ISupplierSQLTime getter) {
		return orAliasImplSQLTimeType(getter);
	}

	@Override
	public final ISharedCondition_SQLTimeType_All<MODEL, RESULT, java.sql.Timestamp, ALIAS_OR_CLAUSES> or(ISupplierSQLTimestamp getter) {
		return orAliasImplSQLTimeType(getter);
	}

	@Override
	public final ISharedCondition_ByteArray_All<MODEL, RESULT, ALIAS_OR_CLAUSES> or(ISupplierByteArray getter) {
		return orAliasImplByteArray(getter);
	}
	
	final ISharedFunctions_Transform_Initial_Alias<
		MODEL,
		RESULT,
		ALIAS_AND_CLAUSES,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Byte, ALIAS_AND_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short, ALIAS_AND_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, ALIAS_AND_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, ALIAS_AND_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigInteger, ALIAS_AND_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Float, ALIAS_AND_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, ALIAS_AND_CLAUSES>,
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, ALIAS_AND_CLAUSES>,
		ISharedCondition_Comparable_String_All<MODEL, RESULT, ALIAS_AND_CLAUSES>
	>
		andAlias() {
	
		final ISharedCollector_Functions_Callback<MODEL, RESULT, ALIAS_AND_CLAUSES> cb
				= new ISharedCollector_Functions_Callback<MODEL, RESULT, ALIAS_AND_CLAUSES>() {

			@Override
			public ISharedFunction_Next<MODEL, RESULT, ALIAS_AND_CLAUSES> onComparable(Expression expression) {
				return andAliasImplComparable(expression);
			}

			@Override
			public ISharedFunction_Next<MODEL, RESULT, ALIAS_AND_CLAUSES> onString(Expression expression) {
				return andAliasImplString(expression);
			}
		};
	
		return new Collector_ConditionFunctions_Alias<>(cb);
	}
	
	// ------------------------  AND helpers ------------------------
	
	private <RR extends Comparable<RR>>
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, ALIAS_AND_CLAUSES> andAliasImplComparable(Supplier<RR> getter) {
		
		return andAliasImplComparable(makeGetterExpression(getter));
	}
	
	final <RR extends Comparable<RR>>
		
	ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, ALIAS_AND_CLAUSES> andAliasImplComparable(Expression expression) {
	
		final Collector_And_Alias<MODEL, RESULT, ALIAS_AND_CLAUSES, ALIAS_NESTED_AND_CLAUSES, ALIAS_NESTED_OR_CLAUSES, ALIAS_AFTER_GROUP_BY> andClauses = createAliasAndCollector();// new Classic_Collector_And_Alias<MODEL, RESULT>(this);
	
		return new Collector_Condition_Comparative<MODEL, RESULT, RR, ALIAS_AND_CLAUSES>(andClauses, expression);
	}
		
		
	final ISharedCondition_Comparable_String_All<MODEL, RESULT, ALIAS_AND_CLAUSES> andAliasImplString(Expression expression) {
		
		final Collector_And_Alias<MODEL, RESULT, ALIAS_AND_CLAUSES, ALIAS_NESTED_AND_CLAUSES, ALIAS_NESTED_OR_CLAUSES, ALIAS_AFTER_GROUP_BY> andClauses = createAliasAndCollector(); // new Classic_Collector_And_Alias<>(this);
		
		return new Collector_Condition_String<MODEL, RESULT, ALIAS_AND_CLAUSES>(andClauses, expression);
	}

	final <RR> ISharedCondition_SQLTimeType_All<MODEL, RESULT, RR, ALIAS_AND_CLAUSES> andAliasImplSQLTimeType(Supplier<RR> getter) {
		
		final Collector_And_Alias<MODEL, RESULT, ALIAS_AND_CLAUSES, ALIAS_NESTED_AND_CLAUSES, ALIAS_NESTED_OR_CLAUSES, ALIAS_AFTER_GROUP_BY> andClauses = createAliasAndCollector(); // new Classic_Collector_And_Alias<>(this);
		
		return new Collector_Condition_SQLTimeType<MODEL, RESULT, RR, ALIAS_AND_CLAUSES>(andClauses, makeGetterExpression(getter));
	}

	final ISharedCondition_ByteArray_All<MODEL, RESULT, ALIAS_AND_CLAUSES> andAliasImplByteArray(Supplier<byte []> getter) {
		
		final Collector_And_Alias<MODEL, RESULT, ALIAS_AND_CLAUSES, ALIAS_NESTED_AND_CLAUSES, ALIAS_NESTED_OR_CLAUSES, ALIAS_AFTER_GROUP_BY> andClauses = createAliasAndCollector(); // new Classic_Collector_And_Alias<>(this);
		
		return new Collector_Condition_ByteArray<MODEL, RESULT, ALIAS_AND_CLAUSES>(andClauses, makeGetterExpression(getter));
	}

	// ------------------------  OR helpers ------------------------
	private final <RR extends Comparable<RR>>
	
		ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, ALIAS_OR_CLAUSES> orAliasImplComparable(Supplier<RR> getter) {
		
		return orAliasImplComparable(makeGetterExpression(getter));
	}
	
	final <T, RR extends Comparable<RR>>
	
	ISharedCondition_Comparable_Common_All<MODEL, RESULT, RR, ALIAS_OR_CLAUSES> orAliasImplComparable(Expression expression) {
	
		final Collector_Or_Alias<MODEL, RESULT, ALIAS_OR_CLAUSES, ALIAS_NESTED_AND_CLAUSES, ALIAS_NESTED_OR_CLAUSES, ALIAS_AFTER_GROUP_BY> orClauses = createAliasOrCollector(); // new Classic_Collector_Or_Alias<>(this);
	
		return new Collector_Condition_Comparative<MODEL, RESULT, RR, ALIAS_OR_CLAUSES>(orClauses, expression);
	}
	
	final 
		ISharedCondition_Comparable_String_All<MODEL, RESULT, ALIAS_OR_CLAUSES> orAliasImplString(Expression expression) {
	
		final Collector_Or_Alias<MODEL, RESULT, ALIAS_OR_CLAUSES, ALIAS_NESTED_AND_CLAUSES, ALIAS_NESTED_OR_CLAUSES, ALIAS_AFTER_GROUP_BY> orClauses = createAliasOrCollector(); // new Classic_Collector_Or_Alias<>(this);
	
		return new Collector_Condition_String<MODEL, RESULT, ALIAS_OR_CLAUSES>(orClauses, expression);
	}
	
	private final <RR>
		
			ISharedCondition_SQLTimeType_All<MODEL, RESULT, RR, ALIAS_OR_CLAUSES> orAliasImplSQLTimeType(Supplier<RR> getter) {

		final Collector_Or_Alias<MODEL, RESULT, ALIAS_OR_CLAUSES, ALIAS_NESTED_AND_CLAUSES, ALIAS_NESTED_OR_CLAUSES, ALIAS_AFTER_GROUP_BY> orClauses = createAliasOrCollector();
		
		return new Collector_Condition_SQLTimeType<MODEL, RESULT, RR, ALIAS_OR_CLAUSES>(orClauses, makeGetterExpression(getter));
	}

	private final ISharedCondition_ByteArray_All<MODEL, RESULT, ALIAS_OR_CLAUSES> orAliasImplByteArray(Supplier<byte []> getter) {
		final Collector_Or_Alias<MODEL, RESULT, ALIAS_OR_CLAUSES, ALIAS_NESTED_AND_CLAUSES, ALIAS_NESTED_OR_CLAUSES, ALIAS_AFTER_GROUP_BY> orClauses = createAliasOrCollector();
		
		return new Collector_Condition_ByteArray<MODEL, RESULT, ALIAS_OR_CLAUSES>(orClauses, makeGetterExpression(getter));
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
	
	final ISharedFunctions_Transform_Initial_Alias<
			MODEL,
			RESULT,
			ALIAS_OR_CLAUSES,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Byte, ALIAS_OR_CLAUSES>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short, ALIAS_OR_CLAUSES>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, ALIAS_OR_CLAUSES>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, ALIAS_OR_CLAUSES>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigInteger, ALIAS_OR_CLAUSES>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Float, ALIAS_OR_CLAUSES>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, ALIAS_OR_CLAUSES>,
			ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, ALIAS_OR_CLAUSES>,
			ISharedCondition_Comparable_String_All<MODEL, RESULT, ALIAS_OR_CLAUSES>
		>
			orAlias() {
		
			final ISharedCollector_Functions_Callback<MODEL, RESULT, ALIAS_OR_CLAUSES> cb
					= new ISharedCollector_Functions_Callback<MODEL, RESULT, ALIAS_OR_CLAUSES>() {

				@Override
				public ISharedFunction_Next<MODEL, RESULT, ALIAS_OR_CLAUSES> onComparable(Expression expression) {
					return orAliasImplComparable(expression);
				}

				@Override
				public ISharedFunction_Next<MODEL, RESULT, ALIAS_OR_CLAUSES> onString(Expression expression) {
					return orAliasImplString(expression);
				}
			};

			return new Collector_ConditionFunctions_Alias<>(cb);
		}

	// ------------------------  OR helpers ------------------------
	private final <T extends ISharedLogical_And<MODEL, RESULT>>
		Collector_Or_Alias<MODEL, RESULT, ALIAS_OR_CLAUSES, ALIAS_NESTED_AND_CLAUSES, ALIAS_NESTED_OR_CLAUSES, ALIAS_AFTER_GROUP_BY>
		
			addNestedAndImpl(Consumer<T> orBuilder) {
	
		final Collector_Or_Alias<
				MODEL,
				RESULT,
				ALIAS_OR_CLAUSES,
				ALIAS_NESTED_AND_CLAUSES,
				ALIAS_NESTED_OR_CLAUSES,
				ALIAS_AFTER_GROUP_BY> orClauses
					
				
				= createAliasOrCollector(); // new Classic_Collector_Or_Alias<>(this);
		
		// Add to new OR
		orClauses.addNestedAndImpl(orBuilder, createAliasNestedAndCollector(orClauses)); // new Classic_Collector_And_NonProcessResult_Alias<>(orClauses));
		
		
		return orClauses;
	}

	private final <T extends ISharedLogical_Or<MODEL, RESULT>>
		
		Collector_And_Alias<MODEL, RESULT, ALIAS_AND_CLAUSES, ALIAS_NESTED_AND_CLAUSES, ALIAS_NESTED_OR_CLAUSES, ALIAS_AFTER_GROUP_BY>
			addNestedOrImpl(Consumer<T> orBuilder) {
	
		final Collector_And_Alias<MODEL, RESULT, ALIAS_AND_CLAUSES, ALIAS_NESTED_AND_CLAUSES, ALIAS_NESTED_OR_CLAUSES, ALIAS_AFTER_GROUP_BY> andClauses = createAliasAndCollector(); // new Classic_Collector_And_Alias<>(this);
	
		// Add to new AND clause
		andClauses.addNestedOrImpl(orBuilder, createAliasNestedOrCollector(andClauses));// new Classic_Collector_Or_NonProcessResult_Alias<>(andClauses));
		
		return andClauses;
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public final ALIAS_AND_CLAUSES andNest(ISharedNestedOrConsumerAlias<MODEL, RESULT, ALIAS_NESTED_OR_CLAUSES> orBuilder) {
		return (ALIAS_AND_CLAUSES)addNestedOrImpl(orBuilder);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public final ALIAS_OR_CLAUSES orNest(ISharedNestedAndConsumerAlias<MODEL, RESULT, ALIAS_NESTED_AND_CLAUSES> andBuilder) {
		return (ALIAS_OR_CLAUSES)addNestedAndImpl(andBuilder);
	}
}
