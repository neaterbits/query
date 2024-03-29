package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

public interface ISharedProcessResult_Having_Named_Base<MODEL, RESULT>

	extends ISharedProcessResult_Base<MODEL, RESULT> {

	<T> ISharedComparison_Comparable_Common_All<MODEL, RESULT, Integer, ISharedProcessResult_Having_And_Or_Named<MODEL, RESULT>>
		having(IFunctionInteger<T> getter);

	<T> ISharedComparison_Comparable_Common_All<MODEL, RESULT, Long, ISharedProcessResult_Having_And_Or_Named<MODEL, RESULT>>
		having(IFunctionLong<T> getter);
	
	<T> ISharedComparison_Comparable_Common_All<MODEL, RESULT, BigDecimal, ISharedProcessResult_Having_And_Or_Named<MODEL, RESULT>>
		having(IFunctionBigDecimal<T> getter);
	
	<T> ISharedComparison_Comparable_String_All<MODEL, RESULT, ISharedProcessResult_Having_And_Or_Named<MODEL, RESULT>>
		having(IFunctionString<T> getter);
		
}
