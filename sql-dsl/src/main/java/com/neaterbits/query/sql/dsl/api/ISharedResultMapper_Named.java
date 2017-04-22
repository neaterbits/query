package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface ISharedResultMapper_Named<MODEL, RESULT, SOURCE extends ISharedSelectSourceBuilder<MODEL, RESULT>> {

	<T> ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Boolean, 		SOURCE>		map(IFunctionBoolean<T> getter);
	<T> ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Byte, 		SOURCE>     map(IFunctionByte<T> getter);
	<T> ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Short, 		SOURCE>     map(IFunctionShort<T> getter);
	<T> ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Integer, 		SOURCE>    	map(IFunctionInteger<T> getter);
	<T> ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Long, 		SOURCE>		map(IFunctionLong<T> getter);
	<T> ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, BigInteger, 	SOURCE>    	map(IFunctionBigInteger<T> getter);
	<T> ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Float, 		SOURCE>    	map(IFunctionFloat<T> getter);
	<T> ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, Double, 		SOURCE>    	map(IFunctionDouble<T> getter);
	<T> ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, BigDecimal, 	SOURCE> 	map(IFunctionBigDecimal<T> getter);


	// String maps to "to" without arithmetic ops (though concat could have been plus-variant like in Java instead of a nested-call)
	<T> ISharedResultOps_String_Named<MODEL, RESULT, SOURCE> map(IFunctionString<T> getter);

	<T> ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, java.util.Date, SOURCE> map(IFunctionDate<T> getter);
	<T> ISharedResultMap_OpsAndTo_Comparable_Named<MODEL, RESULT, java.util.Calendar, SOURCE> map(IFunctionCalendar<T> getter);
	
	<T> ISharedResultMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Date, SOURCE> map(IFunctionSQLDate<T> getter);
	<T> ISharedResultMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Time, SOURCE> map(IFunctionSQLTime<T> getter);
	<T> ISharedResultMap_OpsAndTo_SQLTimeType_Named<MODEL, RESULT, java.sql.Timestamp, SOURCE> map(IFunctionSQLTimestamp<T> getter);
	
}
