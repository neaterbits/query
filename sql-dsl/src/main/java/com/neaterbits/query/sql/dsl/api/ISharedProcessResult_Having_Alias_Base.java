package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

public interface ISharedProcessResult_Having_Alias_Base<MODEL, RESULT>

	extends ISharedProcessResult_Base<MODEL, RESULT> {

	ISharedComparison_Comparable_Common_All<MODEL, RESULT, Integer, ISharedProcessResult_Having_And_Or_Alias<MODEL, RESULT>>
		having(ISupplierInteger getter);

	ISharedComparison_Comparable_Common_All<MODEL, RESULT, Long, ISharedProcessResult_Having_And_Or_Alias<MODEL, RESULT>>
		having(ISupplierLong getter);

	ISharedComparison_Comparable_Common_All<MODEL, RESULT, BigDecimal, ISharedProcessResult_Having_And_Or_Alias<MODEL, RESULT>>
		having(ISupplierBigDecimal getter);

	ISharedComparison_Comparable_String_All<MODEL, RESULT, ISharedProcessResult_Having_And_Or_Alias<MODEL, RESULT>>
		having(ISupplierString getter);
	
}
