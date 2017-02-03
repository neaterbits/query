package com.neaterbits.query.sql.dsl.api;

public interface IClassicJoin_Condition_Alias<MODEL, RESULT>
		extends IClassicJoin_Condition,
				IClassicJoin_Alias<MODEL, RESULT>,
				IClassicLogical_Where_NonProcessResult_Alias<MODEL, RESULT> {
	
	
	IClassicJoin_Condition_Alias<MODEL, RESULT> on(ISupplierCollection joinCollection);
	
	IClassicJoin_Condition_Alias<MODEL, RESULT> compare(ISupplierInteger left, ISupplierInteger right);

	IClassicJoin_Condition_Alias<MODEL, RESULT> compare(ISupplierLong left, ISupplierLong right);
}
