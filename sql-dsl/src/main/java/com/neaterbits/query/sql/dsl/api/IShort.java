package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

public interface IShort extends ISQL<
		IShortResult_Numeric_Named<Long>,
		IShortResult_Numeric_Named<Long>,
		
		IShortResult_Numeric_Named<Short>,
		IShortResult_Numeric_Named<Integer>,
		IShortResult_Numeric_Named<Long>,
		IShortResult_Numeric_Named<BigDecimal>
	> {

	<TYPE_RESULT> IShortResult_Single<SingleCompiled<TYPE_RESULT>, TYPE_RESULT> one(Class<TYPE_RESULT> cl);

	<TYPE_RESULT> IShortResult_Single<SingleCompiled<TYPE_RESULT>, TYPE_RESULT> oneOrNull(Class<TYPE_RESULT> cl);

	<TYPE_RESULT> IShortResult_Multi<MultiCompiled<TYPE_RESULT>, TYPE_RESULT> list(Class<TYPE_RESULT> cl);
}
