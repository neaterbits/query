package com.neaterbits.query.sql.dsl.api;

public interface IShared_Aggregate_Max_Alias<SHORT_RET, INT_RET, LONG_RET, BIGDECIMAL_RET, DATE_RET> {

    SHORT_RET 		max(ISupplierShort field);
	
    INT_RET 		max(ISupplierInteger field);

    LONG_RET 		max(ISupplierLong field);

    BIGDECIMAL_RET 	max(ISupplierBigDecimal field);

    DATE_RET 		max(ISupplierDate field);
}
