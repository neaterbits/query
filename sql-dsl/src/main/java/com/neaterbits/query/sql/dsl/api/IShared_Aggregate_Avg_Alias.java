package com.neaterbits.query.sql.dsl.api;

public interface IShared_Aggregate_Avg_Alias<BYTE_RET, SHORT_RET, INT_RET, LONG_RET, BIGINTEGER_RET, FLOAT_RET, DOUBLE_RET, BIGDECIMAL_RET> {

	// Always retuns double, to be consistent with JPA
	// TODO is this a good idea? Shouldn't avg of double return BigDecimal? 
	
	DOUBLE_RET 	avg(ISupplierByte field);
	
	DOUBLE_RET 	avg(ISupplierShort field);
	
	DOUBLE_RET 	avg(ISupplierInteger field);
	
	DOUBLE_RET 	avg(ISupplierLong field);
	
	DOUBLE_RET 	avg(ISupplierBigInteger field);
	
	DOUBLE_RET 	avg(ISupplierFloat field);
	
	DOUBLE_RET 	avg(ISupplierDouble field);

	DOUBLE_RET 	avg(ISupplierBigDecimal field);

}
