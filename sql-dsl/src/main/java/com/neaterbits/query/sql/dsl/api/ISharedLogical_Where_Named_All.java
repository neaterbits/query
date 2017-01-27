package com.neaterbits.query.sql.dsl.api;

public interface ISharedLogical_Where_Named_All<
			MODEL,
			RESULT,
			CONDITION_CLAUSE extends ISharedLogical_Base<MODEL, RESULT>,
			
			COMPARABLE_CLAUSE extends ISharedCondition_Comparable_Common_All<MODEL,RESULT,? extends Comparable<?>,CONDITION_CLAUSE>,
		    STRING_CLAUSE extends ISharedCondition_Comparable_String_All<MODEL,RESULT,CONDITION_CLAUSE>>

		extends ISharedLogical_Where_Named_Base<MODEL, RESULT, CONDITION_CLAUSE,
		
			COMPARABLE_CLAUSE,
			STRING_CLAUSE>{

	<T, E extends Enum<E>> ISharedCondition_Equality_All<MODEL, RESULT, E, CONDITION_CLAUSE> where(IFunctionEnum<T, E> func);

	
}
