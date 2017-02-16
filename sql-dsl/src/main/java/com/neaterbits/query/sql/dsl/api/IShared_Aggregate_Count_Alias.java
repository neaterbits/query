package com.neaterbits.query.sql.dsl.api;

public interface IShared_Aggregate_Count_Alias<SHORT_RET, INT_RET, LONG_RET, BIGDECIMAL_RET> {

    SHORT_RET 		count(ISupplierShort field);
	
    INT_RET 		count(ISupplierInteger field);

    LONG_RET 		count(ISupplierLong field);

    BIGDECIMAL_RET 	count(ISupplierBigDecimal field);

}
