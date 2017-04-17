package com.neaterbits.query.sql.dsl.api;

public interface IShared_Aggregate_Count_Alias<BYTE_RET, SHORT_RET, INT_RET, LONG_RET, BIGINTEGER_RET, FLOAT_RET, DOUBLE_RET, BIGDECIMAL_RET> {

    BYTE_RET 		count(ISupplierByte field);

    SHORT_RET 		count(ISupplierShort field);
	
    INT_RET 		count(ISupplierInteger field);

    LONG_RET 		count(ISupplierLong field);

    BIGINTEGER_RET 	count(ISupplierBigInteger field);

    FLOAT_RET 		count(ISupplierFloat field);

    DOUBLE_RET 		count(ISupplierDouble field);

    BIGDECIMAL_RET 	count(ISupplierBigDecimal field);

}
