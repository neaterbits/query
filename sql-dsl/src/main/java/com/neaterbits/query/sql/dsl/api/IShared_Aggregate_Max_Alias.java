package com.neaterbits.query.sql.dsl.api;

public interface IShared_Aggregate_Max_Alias<BYTE_RET, SHORT_RET, INT_RET, LONG_RET, BIGINTEGER_RET, FLOAT_RET, DOUBLE_RET, BIGDECIMAL_RET, DATE_RET> {

    BYTE_RET 		max(ISupplierByte field);
    
    SHORT_RET 		max(ISupplierShort field);
	
    INT_RET 		max(ISupplierInteger field);

    LONG_RET 		max(ISupplierLong field);

    BIGINTEGER_RET 	max(ISupplierBigInteger field);

    FLOAT_RET 		max(ISupplierFloat field);

    DOUBLE_RET 		max(ISupplierDouble field);

    BIGDECIMAL_RET 	max(ISupplierBigDecimal field);

    DATE_RET 		max(ISupplierDate field);
}
