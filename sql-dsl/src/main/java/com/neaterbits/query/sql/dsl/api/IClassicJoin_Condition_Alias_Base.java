package com.neaterbits.query.sql.dsl.api;

public interface IClassicJoin_Condition_Alias_Base<

				MODEL,
				RESULT,
				JOIN_CONDITION extends IClassicJoin_Condition_Alias_Base<MODEL, RESULT, JOIN_CONDITION>>

		extends IClassicJoin_Condition,
				IClassicJoin_Alias<MODEL, RESULT, JOIN_CONDITION> {
	
	
	JOIN_CONDITION on(ISupplierCollection joinCollection);
	
	JOIN_CONDITION compare(ISupplierInteger left, ISupplierInteger right);

	JOIN_CONDITION compare(ISupplierLong left, ISupplierLong right);
}
