package com.neaterbits.query.sql.dsl.api;

public interface ISQLJoin_Condition_Alias_Base<

				MODEL,
				RESULT,
				JOIN_CONDITION extends ISQLJoin_Condition_Alias_Base<MODEL, RESULT, JOIN_CONDITION>>

		extends ISQLJoin_Condition {
	
	
	<T> JOIN_CONDITION on(ISupplierCollection<T> joinCollection, T alias);
	
	JOIN_CONDITION compare(ISupplierInteger left, ISupplierInteger right);

	JOIN_CONDITION compare(ISupplierLong left, ISupplierLong right);
}
