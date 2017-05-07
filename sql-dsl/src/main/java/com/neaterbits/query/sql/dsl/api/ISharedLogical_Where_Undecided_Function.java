package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface ISharedLogical_Where_Undecided_Function<
		MODEL,
		RESULT,
		
		NAMED_CONDITION_CLAUSE extends ISharedLogical_Base<MODEL, RESULT>,
		ALIAS_CONDITION_CLAUSE extends ISharedLogical_Base<MODEL, RESULT>,
		UNDECIDED_CONDITION_CLAUSE extends ISharedLogical_Base<MODEL, RESULT>
		> {
	ISharedFunctions_Transform_Initial_Undecided<
		MODEL,
		RESULT,
		
		NAMED_CONDITION_CLAUSE,
		ALIAS_CONDITION_CLAUSE,
		UNDECIDED_CONDITION_CLAUSE,
		
		ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Integer, NAMED_CONDITION_CLAUSE>,
		ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, Integer, ALIAS_CONDITION_CLAUSE>,
		ISharedCondition_OpsAndComp_Comparable_Undecided<MODEL, RESULT, Integer, UNDECIDED_CONDITION_CLAUSE>,
		
		ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Byte, NAMED_CONDITION_CLAUSE>,
		ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Short, NAMED_CONDITION_CLAUSE>,
		ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Integer, NAMED_CONDITION_CLAUSE>,
		ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Long, NAMED_CONDITION_CLAUSE>,
		ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, BigInteger, NAMED_CONDITION_CLAUSE>,
		ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Float, NAMED_CONDITION_CLAUSE>,
		ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, Double, NAMED_CONDITION_CLAUSE>,
		ISharedCondition_OpsAndComp_Comparable_Named<MODEL, RESULT, BigDecimal, NAMED_CONDITION_CLAUSE>,
		ISharedCondition_OpsAndComp_String_Named<MODEL, RESULT, NAMED_CONDITION_CLAUSE>,
		
		ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, Byte, ALIAS_CONDITION_CLAUSE>,
		ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, Short, ALIAS_CONDITION_CLAUSE>,
		ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, Integer, ALIAS_CONDITION_CLAUSE>,
		ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, Long, ALIAS_CONDITION_CLAUSE>,
		ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, BigInteger, ALIAS_CONDITION_CLAUSE>,
		ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, Float, ALIAS_CONDITION_CLAUSE>,
		ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, Double, ALIAS_CONDITION_CLAUSE>,
		ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, BigDecimal, ALIAS_CONDITION_CLAUSE>,
		ISharedCondition_OpsAndComp_String_Alias<MODEL, RESULT, ALIAS_CONDITION_CLAUSE>,

		ISharedCondition_OpsAndComp_Comparable_Undecided<MODEL, RESULT, Byte, UNDECIDED_CONDITION_CLAUSE>,
		ISharedCondition_OpsAndComp_Comparable_Undecided<MODEL, RESULT, Short, UNDECIDED_CONDITION_CLAUSE>,
		ISharedCondition_OpsAndComp_Comparable_Undecided<MODEL, RESULT, Integer, UNDECIDED_CONDITION_CLAUSE>,
		ISharedCondition_OpsAndComp_Comparable_Undecided<MODEL, RESULT, Long, UNDECIDED_CONDITION_CLAUSE>,
		ISharedCondition_OpsAndComp_Comparable_Undecided<MODEL, RESULT, BigInteger, UNDECIDED_CONDITION_CLAUSE>,
		ISharedCondition_OpsAndComp_Comparable_Undecided<MODEL, RESULT, Float, UNDECIDED_CONDITION_CLAUSE>,
		ISharedCondition_OpsAndComp_Comparable_Undecided<MODEL, RESULT, Double, UNDECIDED_CONDITION_CLAUSE>,
		ISharedCondition_OpsAndComp_Comparable_Undecided<MODEL, RESULT, BigDecimal, UNDECIDED_CONDITION_CLAUSE>,
		ISharedCondition_OpsAndComp_String_Undecided<MODEL, RESULT, UNDECIDED_CONDITION_CLAUSE>

		>
	
		where();
	
}
