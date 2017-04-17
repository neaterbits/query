package com.neaterbits.query.sql.dsl.api;

public interface IShared_Aggregate_Min_Alias<BYTE_RET, SHORT_RET, INT_RET, LONG_RET, BIGINTEGER_RET, FLOAT_RET, DOUBLE_RET, BIGDECIMAL_RET, DATE_RET> {

	BYTE_RET 		min(ISupplierByte field);

    SHORT_RET 		min(ISupplierShort field);
	
    INT_RET 		min(ISupplierInteger field);

    LONG_RET 		min(ISupplierLong field);

    BIGINTEGER_RET 	min(ISupplierBigInteger field);

    FLOAT_RET 		min(ISupplierFloat field);
    
    DOUBLE_RET 		min(ISupplierDouble field);

    BIGDECIMAL_RET 	min(ISupplierBigDecimal field);

    DATE_RET 		min(ISupplierDate field);

}
