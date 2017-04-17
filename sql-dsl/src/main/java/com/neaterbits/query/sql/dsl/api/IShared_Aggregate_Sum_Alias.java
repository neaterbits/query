package com.neaterbits.query.sql.dsl.api;

public interface IShared_Aggregate_Sum_Alias<BYTE_RET, SHORT_RET, INT_RET, LONG_RET, BIGINTEGER_RET, FLOAT_RET, DOUBLE_RET, BIGDECIMAL_RET> {

	 BYTE_RET 		sum(ISupplierByte field);

     SHORT_RET 		sum(ISupplierShort field);
	
     INT_RET 		sum(ISupplierInteger field);

     LONG_RET 		sum(ISupplierLong field);

     BIGINTEGER_RET sum(ISupplierBigInteger field);

     FLOAT_RET 		sum(ISupplierFloat field);

     DOUBLE_RET 	sum(ISupplierDouble field);

     BIGDECIMAL_RET sum(ISupplierBigDecimal field);

}
