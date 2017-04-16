package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface ISharedLogical_And_Alias_Base<
		MODEL,
		RESULT,
		AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, AND_CLAUSES, NESTED_OR_CLAUSES>,
		NESTED_OR_CLAUSES extends ISharedLogical_Or_Alias<MODEL, RESULT>>
		
		
		extends ISharedLogical_And_Alias<MODEL, RESULT> {

	ISharedCondition_Comparable_Common_All<MODEL, RESULT, Boolean, AND_CLAUSES> and(ISupplierBoolean getter);

	ISharedCondition_Comparable_Common_All<MODEL, RESULT, Byte, AND_CLAUSES> and(ISupplierByte getter);

	ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short, AND_CLAUSES> and(ISupplierShort getter);

	ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, AND_CLAUSES> and(ISupplierInteger getter);

	ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, AND_CLAUSES> and(ISupplierLong getter);

	ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigInteger, AND_CLAUSES> and(ISupplierBigInteger getter);

	ISharedCondition_Comparable_Common_All<MODEL, RESULT, Float, AND_CLAUSES> and(ISupplierFloat getter);

	ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, AND_CLAUSES> and(ISupplierDouble getter);

	ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, AND_CLAUSES> and(ISupplierBigDecimal getter);
	
	ISharedCondition_Comparable_String_All<MODEL, RESULT, AND_CLAUSES> and(ISupplierString getter);

	ISharedCondition_Comparable_Common_All<MODEL, RESULT, java.util.Date, AND_CLAUSES> and(ISupplierDate getter);

	ISharedCondition_Comparable_Common_All<MODEL, RESULT, java.util.Calendar, AND_CLAUSES> and(ISupplierCalendar getter);

	ISharedCondition_SQLTimeType_All<MODEL, RESULT, java.sql.Date, AND_CLAUSES> and(ISupplierSQLDate getter);

	ISharedCondition_SQLTimeType_All<MODEL, RESULT, java.sql.Time, AND_CLAUSES> and(ISupplierSQLTime getter);

	ISharedCondition_SQLTimeType_All<MODEL, RESULT, java.sql.Timestamp, AND_CLAUSES> and(ISupplierSQLTimestamp getter);
	
	ISharedCondition_ByteArray_All<MODEL, RESULT, AND_CLAUSES> and(ISupplierByteArray getter);
	
	AND_CLAUSES andNest(ISharedNestedOrConsumerAlias<MODEL, RESULT, NESTED_OR_CLAUSES> orBuilder);
    
}
