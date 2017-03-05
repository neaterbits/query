package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

public interface IShortPrepared extends ISQL<
		IShortPrepared_Numeric_Named<Long>,
		IShortPrepared_Numeric_Named<Long>,
		
		IShortPrepared_Numeric_Named<Short>,
		IShortPrepared_Numeric_Named<Integer>,
		IShortPrepared_Numeric_Named<Long>,
		IShortPrepared_Numeric_Named<BigDecimal>
		> {
	
	public static IShortPrepared get(DataConfig dataConfig) {
		return ShortSelectPrepared.get(dataConfig);
	}
	
	<TYPE_RESULT> IShortResult_Single<SinglePrepared<TYPE_RESULT>, TYPE_RESULT> one(Class<TYPE_RESULT> cl);

	<TYPE_RESULT> IShortResult_Single<SinglePrepared<TYPE_RESULT>, TYPE_RESULT> oneOrNull(Class<TYPE_RESULT> cl);

	<TYPE_RESULT> IShortResult_Multi<MultiPrepared<TYPE_RESULT>, TYPE_RESULT> list(Class<TYPE_RESULT> cl);

}
