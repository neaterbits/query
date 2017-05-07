package com.neaterbits.query.sql.dsl.api;

abstract class Conditions_ExpressionList_Comparable_Base<
		MODEL,
		RESULT,
		R extends Comparable<R>, 

		// types for reuse
		SHARED_COMPARISON_RET extends ISharedLogical_Base<MODEL, RESULT>,
		SHARED_COMPARISON extends ISharedComparison_Comparable_Common_All<MODEL, RESULT, R, SHARED_COMPARISON_RET>,
		
		//OPERAND_RET extends ISharedFunction_After<MODEL, RESULT>,
		OPERAND_RET extends ISharedLogical_Base<MODEL, RESULT>,
		
		NAMED_RET extends ISharedLogical_Base<MODEL, RESULT>,
		ALIAS_RET extends ISharedLogical_Base<MODEL, RESULT>,
		UNDECIDED_RET extends ISharedLogical_Base<MODEL, RESULT>,
		
		NAMED_NUMERIC_OPERAND_NEXT    extends ISharedFunction_Next<MODEL, RESULT, OPERAND_RET>,
		NAMED_STRING_OPERAND_NEXT    extends ISharedFunction_Next<MODEL, RESULT, OPERAND_RET>,
		ALIAS_NUMERIC_OPERAND_NEXT    extends ISharedFunction_Next<MODEL, RESULT, OPERAND_RET>,
		ALIAS_STRING_OPERAND_NEXT    extends ISharedFunction_Next<MODEL, RESULT, OPERAND_RET>,
		COMMON_NUMERIC_OPERAND_NEXT    extends ISharedFunction_Next<MODEL, RESULT, OPERAND_RET>,
		COMMON_STRING_OPERAND_NEXT    extends ISharedFunction_Next<MODEL, RESULT, OPERAND_RET>,
		
		
		NAMED_SUM_LONG_RET  extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_COUNT_RET     extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_LENGTH_RET    extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		
		NAMED_BYTE_RET  	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_SHORT_RET  	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_INTEGER_RET  	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_LONG_RET 	 	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_BIGINTEGER_RET extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_FLOAT_RET 	 	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_DOUBLE_RET   	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_BIGDECIMAL_RET  extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_STRING_RET  	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_DATE_RET  	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_CALENDAR_RET  extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_SQLDATE_RET  	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_SQLTIME_RET  	extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		NAMED_SQLTIMESTAMP_RET  extends ISharedFunction_Next<MODEL, RESULT, NAMED_RET>,
		
		ALIAS_SUM_LONG_RET  extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_COUNT_RET     extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_LENGTH_RET    extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		
		ALIAS_BYTE_RET  	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_SHORT_RET  	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_INTEGER_RET  	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_LONG_RET 	 	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_BIGINTEGER_RET extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_FLOAT_RET 	 extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_DOUBLE_RET   	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_BIGDECIMAL_RET  extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_STRING_RET  	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_DATE_RET  	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_CALENDAR_RET  extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_SQLDATE_RET  	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_SQLTIME_RET  	extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		ALIAS_SQLTIMESTAMP_RET  extends ISharedFunction_Next<MODEL, RESULT, ALIAS_RET>,
		
		UNDECIDED_SUM_LONG_RET  extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_COUNT_RET     extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_LENGTH_RET    extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		
		UNDECIDED_BYTE_RET  	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_SHORT_RET  	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_INTEGER_RET  	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_LONG_RET 	 	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_BIGINTEGER_RET extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_FLOAT_RET 	 extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_DOUBLE_RET   	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_BIGDECIMAL_RET  extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_STRING_RET  	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_DATE_RET  	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_CALENDAR_RET  extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_SQLDATE_RET  	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_SQLTIME_RET  	extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>,
		UNDECIDED_SQLTIMESTAMP_RET  extends ISharedFunction_Next<MODEL, RESULT, UNDECIDED_RET>
		>
	extends Conditions_ExpressionList_Base<
		MODEL, RESULT, R,
		
		OPERAND_RET,
		
		NAMED_RET, ALIAS_RET, UNDECIDED_RET,
		
		NAMED_NUMERIC_OPERAND_NEXT,
		NAMED_STRING_OPERAND_NEXT,
		ALIAS_NUMERIC_OPERAND_NEXT,
		ALIAS_STRING_OPERAND_NEXT,
		COMMON_NUMERIC_OPERAND_NEXT,
		COMMON_STRING_OPERAND_NEXT,
		
		NAMED_SUM_LONG_RET,
		NAMED_COUNT_RET,
		NAMED_LENGTH_RET,
		
		
		NAMED_BYTE_RET,
		NAMED_SHORT_RET,
		NAMED_INTEGER_RET,
		NAMED_LONG_RET,
		NAMED_BIGINTEGER_RET,
		NAMED_FLOAT_RET,
		NAMED_DOUBLE_RET,
		NAMED_BIGDECIMAL_RET,
		NAMED_STRING_RET,
		NAMED_DATE_RET,
		NAMED_CALENDAR_RET,
		NAMED_SQLDATE_RET,
		NAMED_SQLTIME_RET,
		NAMED_SQLTIMESTAMP_RET,
	
		
		ALIAS_SUM_LONG_RET,
		ALIAS_COUNT_RET,
		ALIAS_LENGTH_RET,
		
		ALIAS_BYTE_RET,
		ALIAS_SHORT_RET,
		ALIAS_INTEGER_RET,
		ALIAS_LONG_RET,
		ALIAS_BIGINTEGER_RET,
		ALIAS_FLOAT_RET,
		ALIAS_DOUBLE_RET,
		ALIAS_BIGDECIMAL_RET,
		ALIAS_STRING_RET,
		ALIAS_DATE_RET,
		ALIAS_CALENDAR_RET,
		ALIAS_SQLDATE_RET,
		ALIAS_SQLTIME_RET,
		ALIAS_SQLTIMESTAMP_RET,
		
		UNDECIDED_SUM_LONG_RET,
		UNDECIDED_COUNT_RET,
		UNDECIDED_LENGTH_RET,
		
		UNDECIDED_BYTE_RET,
		UNDECIDED_SHORT_RET,
		UNDECIDED_INTEGER_RET,
		UNDECIDED_LONG_RET,
		UNDECIDED_BIGINTEGER_RET,
		UNDECIDED_FLOAT_RET,
		UNDECIDED_DOUBLE_RET,
		UNDECIDED_BIGDECIMAL_RET,
		UNDECIDED_STRING_RET,
		UNDECIDED_DATE_RET,
		UNDECIDED_CALENDAR_RET,
		UNDECIDED_SQLDATE_RET,
		UNDECIDED_SQLTIME_RET,
		UNDECIDED_SQLTIMESTAMP_RET>
		

		implements ISharedComparison_Comparable_Common_All<MODEL, RESULT, R, SHARED_COMPARISON_RET>

 {
	 
	abstract SHARED_COMPARISON assureComparable();

	Conditions_ExpressionList_Comparable_Base(Collector_Conditions_GroupBy<MODEL, RESULT, ?> clause, Expression expression, EFieldAccessType fieldAccessType) {
		super(clause, expression, fieldAccessType);
	}

	@Override
	public final SHARED_COMPARISON_RET isNull() {
		return assureComparable().isNull();
	}

	@Override
	public final SHARED_COMPARISON_RET isNotNull() {
		return assureComparable().isNotNull();
	}

	@Override
	public final SHARED_COMPARISON_RET isEqualTo(R other) {
		return assureComparable().isEqualTo(other);
	}

	@Override
	public final SHARED_COMPARISON_RET isNotEqualTo(R other) {
		return assureComparable().isNotEqualTo(other);
	}

	@Override
	public final SHARED_COMPARISON_RET in(R... values) {
		return assureComparable().in(values);
	}

	@Override
	public final SHARED_COMPARISON_RET isGreaterThan(ValParam<R> value) {
		return assureComparable().isGreaterThan(value);
	}

	@Override
	public final SHARED_COMPARISON_RET isGreaterOrEqualTo(ValParam<R> value) {
		return assureComparable().isGreaterOrEqualTo(value);
	}

	@Override
	public final SHARED_COMPARISON_RET isLessThan(ValParam<R> value) {
		return assureComparable().isLessThan(value);
	}

	@Override
	public final SHARED_COMPARISON_RET isLessOrEqualTo(ValParam<R> value) {
		return assureComparable().isLessOrEqualTo(value);
	}

	@Override
	public final SHARED_COMPARISON_RET isEqualTo(ValParam<R> other) {
		return assureComparable().isEqualTo(other);
	}

	@Override
	public final SHARED_COMPARISON_RET isNotEqualTo(ValParam<R> other) {
		return assureComparable().isNotEqualTo(other);
	}

	@Override
	public final SHARED_COMPARISON_RET in(InParam<R> param) {
		return assureComparable().in(param);
	}

	@Override
	public final SHARED_COMPARISON_RET isGreaterThan(R value) {
		return assureComparable().isGreaterThan(value);
	}

	@Override
	public final SHARED_COMPARISON_RET isGreaterOrEqualTo(R value) {
		return assureComparable().isGreaterOrEqualTo(value);
	}

	@Override
	public final SHARED_COMPARISON_RET isLessThan(R value) {
		return assureComparable().isLessThan(value);
	}

	@Override
	public final SHARED_COMPARISON_RET isLessOrEqualTo(R value) {
		return assureComparable().isLessOrEqualTo(value);
	}
}
