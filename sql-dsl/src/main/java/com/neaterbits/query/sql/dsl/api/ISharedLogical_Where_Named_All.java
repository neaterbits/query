package com.neaterbits.query.sql.dsl.api;

public interface ISharedLogical_Where_Named_All<
			MODEL,
			RESULT,
			CONDITION_CLAUSE extends ISharedLogical_Base<MODEL, RESULT>,
			
			INTEGER_CLAUSE extends ISharedCondition_Comparable_Common_All<MODEL,RESULT,Integer,CONDITION_CLAUSE>,
			LONG_CLAUSE extends ISharedCondition_Comparable_Common_All<MODEL,RESULT, Long,CONDITION_CLAUSE>,
		    STRING_CLAUSE extends ISharedCondition_Comparable_String_All<MODEL,RESULT,CONDITION_CLAUSE>>

		extends ISharedLogical_Where_Named_Base<MODEL, RESULT, CONDITION_CLAUSE,
		
			INTEGER_CLAUSE,
			LONG_CLAUSE,
			STRING_CLAUSE>{

	<T, E extends Enum<E>> ISharedCondition_Equality_All<MODEL, RESULT, E, CONDITION_CLAUSE> where(IFunctionEnum<T, E> func);

	
}
