package com.neaterbits.query.sql.dsl.api;

public interface IShared_Aggregate_Avg_Alias<SHORT_RET, INT_RET, LONG_RET, BIGDECIMAL_RET> {

     SHORT_RET 		avg(ISupplierShort field);
	
     INT_RET 		avg(ISupplierInteger field);

     LONG_RET 		avg(ISupplierLong field);

     BIGDECIMAL_RET avg(ISupplierBigDecimal field);

}
