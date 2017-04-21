package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

public interface IAdhocWhere<MODEL, RESULT, ENTITY, AND_OR_CLAUSES extends IAdhocLogical_And_Or<MODEL,RESULT, ENTITY>
		
		> {

	<E extends Enum<E>> ISharedCondition_Equality_Value<MODEL, RESULT, E, AND_OR_CLAUSES> where(IFunctionEnum<ENTITY, E> func);

	ISharedCondition_Comparable_Common_Value<MODEL, RESULT, Integer, AND_OR_CLAUSES> where(IFunctionInteger<ENTITY> func);

	ISharedCondition_Comparable_Common_Value<MODEL, RESULT, BigDecimal, AND_OR_CLAUSES> where(IFunctionBigDecimal<ENTITY> func);

	ISharedCondition_Comparable_String_Value<MODEL, RESULT, AND_OR_CLAUSES> where(IFunctionString<ENTITY> func);

	IAdhocFunctions_Initial<
			MODEL,
			RESULT,
			ENTITY,
			IAdhocLogical_And_Or<MODEL, RESULT, ENTITY>,
			
			ISharedCondition_Comparable_Common_Value<MODEL, RESULT, Integer, IAdhocLogical_And_Or<MODEL, RESULT, ENTITY>>,
			ISharedCondition_Comparable_Common_Value<MODEL, RESULT, Long, IAdhocLogical_And_Or<MODEL, RESULT, ENTITY>>,
			ISharedCondition_Comparable_String_Value<MODEL, RESULT, IAdhocLogical_And_Or<MODEL, RESULT, ENTITY>>
	
		> where();
}
