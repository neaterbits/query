package com.neaterbits.query.sql.dsl.api;

public interface IShortResult_Multi<MODEL, RESULT>

	extends
	
		IShortResult_Base<MODEL, RESULT>,
		IShortResult_Mapped_Multi_All<MODEL, RESULT>,
		
		
		// Cannot go to group-by from initial multi-result, because is no group-by for entities
		// but we can go to order-by.
		// since one cannot have passed any multiple aliases (no joins) at this point,
		// we only add the named version
		
		ISharedProcessResult_OrderBy_Entity_Named<MODEL, RESULT> {

}
