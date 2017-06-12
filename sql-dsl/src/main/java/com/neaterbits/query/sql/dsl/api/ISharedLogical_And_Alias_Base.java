package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface ISharedLogical_And_Alias_Base<
		MODEL,
		RESULT,
		AND_CLAUSES extends ISharedLogical_And_Alias_Base<MODEL, RESULT, AND_CLAUSES, NESTED_OR_CLAUSES>,
		NESTED_OR_CLAUSES extends ISharedLogical_Or_Alias<MODEL, RESULT>>
		
		
		extends ISharedLogical_And_Alias<MODEL, RESULT> {

	ISharedComparison_Comparable_Common_All<MODEL, RESULT, Boolean, AND_CLAUSES> and(ISupplierBoolean getter);

	ISharedComparison_Comparable_Common_All<MODEL, RESULT, Byte, AND_CLAUSES> and(ISupplierByte getter);

	ISharedComparison_Comparable_Common_All<MODEL, RESULT, Short, AND_CLAUSES> and(ISupplierShort getter);

	ISharedComparison_Comparable_Common_All<MODEL, RESULT, Integer, AND_CLAUSES> and(ISupplierInteger getter);

	ISharedComparison_Comparable_Common_All<MODEL, RESULT, Long, AND_CLAUSES> and(ISupplierLong getter);

	ISharedComparison_Comparable_Common_All<MODEL, RESULT, BigInteger, AND_CLAUSES> and(ISupplierBigInteger getter);

	ISharedComparison_Comparable_Common_All<MODEL, RESULT, Float, AND_CLAUSES> and(ISupplierFloat getter);

	ISharedComparison_Comparable_Common_All<MODEL, RESULT, Double, AND_CLAUSES> and(ISupplierDouble getter);

	ISharedComparison_Comparable_Common_All<MODEL, RESULT, BigDecimal, AND_CLAUSES> and(ISupplierBigDecimal getter);
	
	ISharedComparison_Comparable_String_All<MODEL, RESULT, AND_CLAUSES> and(ISupplierString getter);

	ISharedComparison_Comparable_Common_All<MODEL, RESULT, java.util.Date, AND_CLAUSES> and(ISupplierDate getter);

	ISharedComparison_Comparable_Common_All<MODEL, RESULT, java.util.Calendar, AND_CLAUSES> and(ISupplierCalendar getter);

	ISharedComparison_SQLTimeType_All<MODEL, RESULT, java.sql.Date, AND_CLAUSES> and(ISupplierSQLDate getter);

	ISharedComparison_SQLTimeType_All<MODEL, RESULT, java.sql.Time, AND_CLAUSES> and(ISupplierSQLTime getter);

	ISharedComparison_SQLTimeType_All<MODEL, RESULT, java.sql.Timestamp, AND_CLAUSES> and(ISupplierSQLTimestamp getter);
	
	ISharedComparison_ByteArray_All<MODEL, RESULT, AND_CLAUSES> and(ISupplierByteArray getter);
	
	AND_CLAUSES andNest(ISharedNested_Or_Consumer_Alias<MODEL, RESULT, NESTED_OR_CLAUSES> orBuilder);
    
}
