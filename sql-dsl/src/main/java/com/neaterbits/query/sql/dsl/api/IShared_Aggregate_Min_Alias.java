package com.neaterbits.query.sql.dsl.api;

public interface IShared_Aggregate_Min_Alias<SHORT_RET, INT_RET, LONG_RET, BIGDECIMAL_RET> {

    SHORT_RET 		min(ISupplierShort field);
	
    INT_RET 		min(ISupplierInteger field);

    LONG_RET 		min(ISupplierLong field);

    BIGDECIMAL_RET 	min(ISupplierBigDecimal field);

}
