package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface IClassic 
	extends ISQL<
				IClassicResult_Numeric_Named<Long>,
				IClassicResult_Numeric_Named<Long>,
				
				IClassicResult_Numeric_Named<Byte>,
				IClassicResult_Numeric_Named<Short>,
				IClassicResult_Numeric_Named<Integer>,
				IClassicResult_Numeric_Named<Long>,
				IClassicResult_Numeric_Named<BigInteger>,
				IClassicResult_Numeric_Named<Float>,
				IClassicResult_Numeric_Named<Double>,
				IClassicResult_Numeric_Named<BigDecimal>,
				IClassicResult_Numeric_Named<java.util.Date>,
				IClassicResult_Numeric_Named<java.util.Calendar>,
				IClassicResult_Numeric_Named<java.sql.Date>,
				IClassicResult_Numeric_Named<java.sql.Time>,
				IClassicResult_Numeric_Named<java.sql.Timestamp>,

				IClassicResult_Numeric_Alias<Long>,
				IClassicResult_Numeric_Alias<Long>,
				
				IClassicResult_Numeric_Alias<Byte>,
				IClassicResult_Numeric_Alias<Short>,
				IClassicResult_Numeric_Alias<Integer>,
				IClassicResult_Numeric_Alias<Long>,
				IClassicResult_Numeric_Alias<BigInteger>,
				IClassicResult_Numeric_Alias<Float>,
				IClassicResult_Numeric_Alias<Double>,
				IClassicResult_Numeric_Alias<BigDecimal>,
				IClassicResult_Numeric_Alias<java.util.Date>,
				IClassicResult_Numeric_Alias<java.util.Calendar>,
				IClassicResult_Numeric_Alias<java.sql.Date>,
				IClassicResult_Numeric_Alias<java.sql.Time>,
				IClassicResult_Numeric_Alias<java.sql.Timestamp>
				
		>

{
	/*
	<MAPPED_RESULT> IClassicResult_Mapped_Single_All<SingleQuery<MAPPED_RESULT>, MAPPED_RESULT> selectOneOrNull(Class<MAPPED_RESULT> cl);

	<MAPPED_RESULT> IClassicResult_Mapped_Multi_All<MultiQuery<MAPPED_RESULT>, MAPPED_RESULT> selectList(Class<MAPPED_RESULT> cl);

	<TYPE_RESULT> IClassicResult_Entity_Single<SingleQuery<TYPE_RESULT>, TYPE_RESULT> selectOneFrom(Class<TYPE_RESULT> cl);

	<TYPE_RESULT> IClassicResult_Entity_Multi<MultiQuery<TYPE_RESULT>, TYPE_RESULT> selectListFrom(Class<TYPE_RESULT> cl);

	 */
	
	<TYPE_RESULT> IClassicResult_Single<SingleBuilt<TYPE_RESULT>, TYPE_RESULT> one(Class<TYPE_RESULT> cl);

	<TYPE_RESULT> IClassicResult_Single<SingleBuilt<TYPE_RESULT>, TYPE_RESULT> oneOrNull(Class<TYPE_RESULT> cl);

	<TYPE_RESULT> IClassicResult_Multi<MultiBuilt<TYPE_RESULT>, TYPE_RESULT> list(Class<TYPE_RESULT> cl);

	// return single-type, that is we do not require from-type
	<TYPE_RESULT> IClassicSingleWhereClauseBuilderNamed<SingleBuilt<TYPE_RESULT>, TYPE_RESULT> oneFrom(Class<TYPE_RESULT> cl);

	<TYPE_RESULT> IClassicSingleWhereClauseBuilderNamed<MultiBuilt<TYPE_RESULT>, TYPE_RESULT> listFrom(Class<TYPE_RESULT> cl);
}
