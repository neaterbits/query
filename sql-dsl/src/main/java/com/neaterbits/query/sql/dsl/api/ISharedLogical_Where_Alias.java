package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface ISharedLogical_Where_Alias<MODEL, RESULT, CONDITION_CLAUSE extends ISharedLogical_Base<MODEL, RESULT>>
	extends ISharedLogical_Where<MODEL, RESULT> {

	ISharedCondition_Comparable_Common_All<MODEL, RESULT, Boolean, CONDITION_CLAUSE> where(ISupplierBoolean func);
	
	ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short, CONDITION_CLAUSE> where(ISupplierShort func);
	
	ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, CONDITION_CLAUSE> where(ISupplierInteger func);

	ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, CONDITION_CLAUSE> where(ISupplierLong func);

	ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigInteger, CONDITION_CLAUSE> where(ISupplierBigInteger func);

	ISharedCondition_Comparable_Common_All<MODEL, RESULT, Float, CONDITION_CLAUSE> where(ISupplierFloat func);
	
	ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, CONDITION_CLAUSE> where(ISupplierDouble func);
	
	ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, CONDITION_CLAUSE> where(ISupplierBigDecimal func);
	
    ISharedCondition_Comparable_String_All<MODEL, RESULT, CONDITION_CLAUSE> where(ISupplierString supplier);

	ISharedCondition_Comparable_Common_All<MODEL, RESULT, java.util.Date, CONDITION_CLAUSE> where(ISupplierDate func);
	
	ISharedCondition_Comparable_Common_All<MODEL, RESULT, java.util.Calendar, CONDITION_CLAUSE> where(ISupplierCalendar func);
	
	ISharedCondition_SQLTimeType_All<MODEL, RESULT, java.sql.Date, CONDITION_CLAUSE> where(ISupplierSQLDate func);

	ISharedCondition_SQLTimeType_All<MODEL, RESULT, java.sql.Time, CONDITION_CLAUSE> where(ISupplierSQLTime func);

	ISharedCondition_SQLTimeType_All<MODEL, RESULT, java.sql.Timestamp, CONDITION_CLAUSE> where(ISupplierSQLTimestamp func);
	
	ISharedCondition_ByteArray_All<MODEL, RESULT, CONDITION_CLAUSE> where(ISupplierByteArray func);

}
