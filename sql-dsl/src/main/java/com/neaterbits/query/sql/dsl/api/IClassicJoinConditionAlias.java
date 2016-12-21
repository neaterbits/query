package com.neaterbits.query.sql.dsl.api;

public interface IClassicJoinConditionAlias<MODEL, RESULT>
		extends IClassicJoinCondition,
				IClassicJoinClauseAlias<MODEL, RESULT>,
				IClassicWhereClauseBuilderAlias<MODEL, RESULT> {
	
	
	IClassicJoinConditionAlias<MODEL, RESULT> on(ISupplierCollection joinCollection);
	
	IClassicJoinConditionAlias<MODEL, RESULT> compare(ISupplierInteger left, ISupplierInteger right);

	IClassicJoinConditionAlias<MODEL, RESULT> compare(ISupplierLong left, ISupplierLong right);
}
