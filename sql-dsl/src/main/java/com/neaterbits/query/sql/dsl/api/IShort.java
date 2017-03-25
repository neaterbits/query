package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.Date;

public interface IShort extends ISQL<
		IShortBuilt_Numeric_Named<Long>,
		IShortBuilt_Numeric_Named<Long>,
		
		IShortBuilt_Numeric_Named<Short>,
		IShortBuilt_Numeric_Named<Integer>,
		IShortBuilt_Numeric_Named<Long>,
		IShortBuilt_Numeric_Named<Double>,
		IShortBuilt_Numeric_Named<BigDecimal>,
		IShortBuilt_Numeric_Named<Date>,

		IShortBuilt_Numeric_Alias<Long>,
		IShortBuilt_Numeric_Alias<Long>,
		
		IShortBuilt_Numeric_Alias<Short>,
		IShortBuilt_Numeric_Alias<Integer>,
		IShortBuilt_Numeric_Alias<Long>,
		IShortBuilt_Numeric_Alias<Double>,
		IShortBuilt_Numeric_Alias<BigDecimal>,
		IShortBuilt_Numeric_Alias<Date>

	> {

	<TYPE_RESULT> IShortResult_Single<SingleBuilt<TYPE_RESULT>, TYPE_RESULT> one(Class<TYPE_RESULT> cl);

	<TYPE_RESULT> IShortResult_Single<SingleBuilt<TYPE_RESULT>, TYPE_RESULT> oneOrNull(Class<TYPE_RESULT> cl);

	<TYPE_RESULT> IShortResult_Multi<MultiBuilt<TYPE_RESULT>, TYPE_RESULT> list(Class<TYPE_RESULT> cl);
}
