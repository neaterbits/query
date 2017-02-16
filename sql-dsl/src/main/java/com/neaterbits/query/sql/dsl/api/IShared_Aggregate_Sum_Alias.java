package com.neaterbits.query.sql.dsl.api;

public interface IShared_Aggregate_Sum_Alias<SHORT_RET, INT_RET, LONG_RET, BIGDECIMAL_RET> {

     SHORT_RET 		sum(ISupplierShort field);
	
     INT_RET 		sum(ISupplierInteger field);

     LONG_RET 		sum(ISupplierLong field);

     BIGDECIMAL_RET sum(ISupplierBigDecimal field);

}
