package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface IShortPrepared extends ISQL<
		IShortPrepared_Numeric_Named<Long>,
		IShortPrepared_Numeric_Named<Long>,
		
		IShortPrepared_Numeric_Named<Byte>,
		IShortPrepared_Numeric_Named<Short>,
		IShortPrepared_Numeric_Named<Integer>,
		IShortPrepared_Numeric_Named<Long>,
		IShortPrepared_Numeric_Named<BigInteger>,
		IShortPrepared_Numeric_Named<Float>,
		IShortPrepared_Numeric_Named<Double>,
		IShortPrepared_Numeric_Named<BigDecimal>,
		IShortPrepared_Numeric_Named<java.util.Date>,
		IShortPrepared_Numeric_Named<java.util.Calendar>,
		IShortPrepared_Numeric_Named<java.sql.Date>,
		IShortPrepared_Numeric_Named<java.sql.Time>,
		IShortPrepared_Numeric_Named<java.sql.Timestamp>,

		IShortPrepared_Numeric_Alias<Long>,
		IShortPrepared_Numeric_Alias<Long>,
		
		IShortPrepared_Numeric_Alias<Byte>,
		IShortPrepared_Numeric_Alias<Short>,
		IShortPrepared_Numeric_Alias<Integer>,
		IShortPrepared_Numeric_Alias<Long>,
		IShortPrepared_Numeric_Alias<BigInteger>,
		IShortPrepared_Numeric_Alias<Float>,
		IShortPrepared_Numeric_Alias<Double>,
		IShortPrepared_Numeric_Alias<BigDecimal>,
		IShortPrepared_Numeric_Alias<java.util.Date>,
		IShortPrepared_Numeric_Alias<java.util.Calendar>,
		IShortPrepared_Numeric_Alias<java.sql.Date>,
		IShortPrepared_Numeric_Alias<java.sql.Time>,
		IShortPrepared_Numeric_Alias<java.sql.Timestamp>
		> {
	
	public static IShortPrepared get(DataConfig dataConfig) {
		return ShortSelectPrepared.get(dataConfig);
	}
	
	<TYPE_RESULT> IShortResult_Single_Instance<SinglePrepared<TYPE_RESULT>, TYPE_RESULT> one(Class<TYPE_RESULT> cl);

	<TYPE_RESULT> IShortResult_Single_Instance<SinglePrepared<TYPE_RESULT>, TYPE_RESULT> oneOrNull(Class<TYPE_RESULT> cl);

	<TYPE_RESULT> IShortResult_Multi_Instance<MultiPrepared<TYPE_RESULT>, TYPE_RESULT> list(Class<TYPE_RESULT> cl);

}
