package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface ISharedLogical_Or_Alias_Base<
		MODEL,
		RESULT,
		OR_CLAUSES extends ISharedLogical_Or_Alias_Base<MODEL, RESULT, OR_CLAUSES, NESTED_AND_CLAUSES>,
		NESTED_AND_CLAUSES extends ISharedLogical_And_Alias<MODEL, RESULT>
		> extends ISharedLogical_Or_Alias<MODEL, RESULT> {
	
    ISharedCondition_Comparable_Common_All<MODEL, RESULT, Boolean, OR_CLAUSES> or(ISupplierBoolean getter);

    ISharedCondition_Comparable_Common_All<MODEL, RESULT, Byte, OR_CLAUSES> or(ISupplierByte getter);

    ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short, OR_CLAUSES> or(ISupplierShort getter);
    
    ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer, OR_CLAUSES> or(ISupplierInteger getter);

    ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, OR_CLAUSES> or(ISupplierLong getter);
    
    ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigInteger, OR_CLAUSES> or(ISupplierBigInteger getter);

    ISharedCondition_Comparable_Common_All<MODEL, RESULT, Float, OR_CLAUSES> or(ISupplierFloat getter);

    ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, OR_CLAUSES> or(ISupplierDouble getter);

    ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, OR_CLAUSES> or(ISupplierBigDecimal getter);

    ISharedCondition_Comparable_String_All<MODEL, RESULT, OR_CLAUSES> or(ISupplierString getter);
    
    ISharedCondition_Comparable_Common_All<MODEL, RESULT, java.util.Date, OR_CLAUSES> or(ISupplierDate getter);

    ISharedCondition_Comparable_Common_All<MODEL, RESULT, java.util.Calendar, OR_CLAUSES> or(ISupplierCalendar getter);

    ISharedCondition_SQLTimeType_All<MODEL, RESULT, java.sql.Date, OR_CLAUSES> or(ISupplierSQLDate getter);

    ISharedCondition_SQLTimeType_All<MODEL, RESULT, java.sql.Time, OR_CLAUSES> or(ISupplierSQLTime getter);
    
    ISharedCondition_SQLTimeType_All<MODEL, RESULT, java.sql.Timestamp, OR_CLAUSES> or(ISupplierSQLTimestamp getter);

    ISharedCondition_ByteArray_All<MODEL, RESULT, OR_CLAUSES> or(ISupplierByteArray getter);

	OR_CLAUSES orNest(ISharedNestedAndConsumerAlias<MODEL, RESULT, NESTED_AND_CLAUSES> andBuilder);
}
