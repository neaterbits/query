package com.neaterbits.query.sql.dsl.api;

import java.util.function.Supplier;

public interface ISharedMap_Initial_Alias<MODEL, RESULT, SOURCE extends ISharedSelectSourceBuilder<MODEL, RESULT>> {

	<R> ISharedMap_To<MODEL, RESULT, R, SOURCE> map(Supplier<R> getter);
	
	// TODO ISharedResultOps_Numeric_Alias<MODEL, RESULT, BigDecimal, SOURCE> mapOf(ISharedSubOperandsFunction_Alias<MODEL, RESULT, BigDecimal> sub);
	
}
