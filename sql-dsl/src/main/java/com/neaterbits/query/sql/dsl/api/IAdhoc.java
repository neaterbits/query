package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

interface IAdhoc<MODEL> extends SumFunction<
	IAdhocNumericTableResult<MODEL, Short>,
	IAdhocNumericTableResult<MODEL, Integer>,
	IAdhocNumericTableResult<MODEL, Long>,
	IAdhocNumericTableResult<MODEL, BigDecimal>> {

}
