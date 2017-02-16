package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

public interface ISharedProcessResult_Having_Alias<MODEL, RESULT>

		extends ISharedProcessResult_Base<MODEL, RESULT> {

	ISharedProcessResult_Having_Aggregate_Alias<MODEL, RESULT, ISharedProcessResult_Having_And_Or_Alias<MODEL, RESULT>> having();
	
	ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, ISharedProcessResult_Having_And_Or_Alias<MODEL, RESULT>>
			having(ISupplierInteger getter);
	
	ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, ISharedProcessResult_Having_And_Or_Alias<MODEL, RESULT>>
			having(ISupplierLong getter);
	
	
	ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, ISharedProcessResult_Having_And_Or_Alias<MODEL, RESULT>>
			having(ISupplierBigDecimal getter);
	
	ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, ISharedProcessResult_Having_And_Or_Alias<MODEL, RESULT>>
			having(ISupplierString getter);
}
