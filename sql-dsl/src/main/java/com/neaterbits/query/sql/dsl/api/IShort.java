package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

public interface IShort extends ISQL<
		IShortBuilt_Numeric_Named<Long>,
		IShortBuilt_Numeric_Named<Long>,
		
		IShortBuilt_Numeric_Named<Short>,
		IShortBuilt_Numeric_Named<Integer>,
		IShortBuilt_Numeric_Named<Long>,
		IShortBuilt_Numeric_Named<Double>,
		IShortBuilt_Numeric_Named<BigDecimal>
	> {

	<TYPE_RESULT> IShortResult_Single<SingleBuilt<TYPE_RESULT>, TYPE_RESULT> one(Class<TYPE_RESULT> cl);

	<TYPE_RESULT> IShortResult_Single<SingleBuilt<TYPE_RESULT>, TYPE_RESULT> oneOrNull(Class<TYPE_RESULT> cl);

	<TYPE_RESULT> IShortResult_Multi<MultiBuilt<TYPE_RESULT>, TYPE_RESULT> list(Class<TYPE_RESULT> cl);
}
