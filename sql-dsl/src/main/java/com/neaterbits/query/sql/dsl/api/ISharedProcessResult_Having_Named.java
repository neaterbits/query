package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

public interface ISharedProcessResult_Having_Named<MODEL, RESULT>


		extends ISharedProcessResult_Base<MODEL, RESULT> {

	ISharedProcessResult_Having_Aggregate_Named<MODEL, RESULT, ISharedProcessResult_Having_And_Or_Named<MODEL, RESULT>> having();
	
	<T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, ISharedProcessResult_Having_And_Or_Named<MODEL, RESULT>>
		having(IFunctionInteger<T> getter);

	<T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, ISharedProcessResult_Having_And_Or_Named<MODEL, RESULT>>
		having(IFunctionLong<T> getter);
	
	
	<T> ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, ISharedProcessResult_Having_And_Or_Named<MODEL, RESULT>>
		having(IFunctionBigDecimal<T> getter);
	
	<T> ISharedCondition_Comparable_String_All<MODEL, RESULT, ISharedProcessResult_Having_And_Or_Named<MODEL, RESULT>>
		having(IFunctionString<T> getter);
	
}
