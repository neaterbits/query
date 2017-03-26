package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.Date;

public interface IShortPrepared extends ISQL<
		IShortPrepared_Numeric_Named<Long>,
		IShortPrepared_Numeric_Named<Long>,
		
		IShortPrepared_Numeric_Named<Short>,
		IShortPrepared_Numeric_Named<Integer>,
		IShortPrepared_Numeric_Named<Long>,
		IShortPrepared_Numeric_Named<Double>,
		IShortPrepared_Numeric_Named<BigDecimal>,
		IShortPrepared_Numeric_Named<Date>,

		IShortPrepared_Numeric_Alias<Long>,
		IShortPrepared_Numeric_Alias<Long>,
		
		IShortPrepared_Numeric_Alias<Short>,
		IShortPrepared_Numeric_Alias<Integer>,
		IShortPrepared_Numeric_Alias<Long>,
		IShortPrepared_Numeric_Alias<Double>,
		IShortPrepared_Numeric_Alias<BigDecimal>,
		IShortPrepared_Numeric_Alias<Date>
		> {
	
	public static IShortPrepared get(DataConfig dataConfig) {
		return ShortSelectPrepared.get(dataConfig);
	}
	
	<TYPE_RESULT> IShortResult_Single_Instance<SinglePrepared<TYPE_RESULT>, TYPE_RESULT> one(Class<TYPE_RESULT> cl);

	<TYPE_RESULT> IShortResult_Single_Instance<SinglePrepared<TYPE_RESULT>, TYPE_RESULT> oneOrNull(Class<TYPE_RESULT> cl);

	<TYPE_RESULT> IShortResult_Multi_Instance<MultiPrepared<TYPE_RESULT>, TYPE_RESULT> list(Class<TYPE_RESULT> cl);

}
