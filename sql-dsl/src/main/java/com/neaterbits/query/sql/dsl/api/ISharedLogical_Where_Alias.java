package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface ISharedLogical_Where_Alias<MODEL, RESULT, CONDITION_CLAUSE extends ISharedLogical_Base<MODEL, RESULT>>
	extends ISharedLogical_Where<MODEL, RESULT> {

	ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, Boolean, CONDITION_CLAUSE> where(ISupplierBoolean func);
	
	ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, Short, CONDITION_CLAUSE> where(ISupplierShort func);
	
	ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, Integer, CONDITION_CLAUSE> where(ISupplierInteger func);

	ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, Long, CONDITION_CLAUSE> where(ISupplierLong func);

	ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, BigInteger, CONDITION_CLAUSE> where(ISupplierBigInteger func);

	ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, Float, CONDITION_CLAUSE> where(ISupplierFloat func);
	
	ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, Double, CONDITION_CLAUSE> where(ISupplierDouble func);
	
	ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, BigDecimal, CONDITION_CLAUSE> where(ISupplierBigDecimal func);
	
	ISharedCondition_OpsAndComp_String_Alias<MODEL, RESULT, CONDITION_CLAUSE> where(ISupplierString supplier);

	ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, java.util.Date, CONDITION_CLAUSE> where(ISupplierDate func);
	
	ISharedCondition_OpsAndComp_Comparable_Alias<MODEL, RESULT, java.util.Calendar, CONDITION_CLAUSE> where(ISupplierCalendar func);
	
	ISharedCondition_OpsAndComp_SQLTimeType_Alias<MODEL, RESULT, java.sql.Date, CONDITION_CLAUSE> where(ISupplierSQLDate func);

	ISharedCondition_OpsAndComp_SQLTimeType_Alias<MODEL, RESULT, java.sql.Time, CONDITION_CLAUSE> where(ISupplierSQLTime func);

	ISharedCondition_OpsAndComp_SQLTimeType_Alias<MODEL, RESULT, java.sql.Timestamp, CONDITION_CLAUSE> where(ISupplierSQLTimestamp func);
	
	ISharedCondition_OpsAndComp_ByteArray_Alias<MODEL, RESULT, CONDITION_CLAUSE> where(ISupplierByteArray func);

}
 