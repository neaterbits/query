package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

interface IAdhoc extends SumFunction<
	IAdhocNumericTableResult<Short>,
	IAdhocNumericTableResult<Integer>,
	IAdhocNumericTableResult<Long>,
	IAdhocNumericTableResult<BigDecimal>> {

}
