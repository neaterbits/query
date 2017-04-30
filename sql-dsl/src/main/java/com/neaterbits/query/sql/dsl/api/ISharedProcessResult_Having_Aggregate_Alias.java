package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public interface ISharedProcessResult_Having_Aggregate_Alias<MODEL, RESULT, AFTER_HAVING extends ISharedLogical_Base<MODEL, RESULT>>

	extends ISharedFunctions_Aggregate_Alias_All<
		    ISharedComparison_Comparable_Common_All<MODEL, RESULT, Long, AFTER_HAVING>,
		    ISharedComparison_Comparable_Common_All<MODEL, RESULT, Long, AFTER_HAVING>,
			
		    ISharedComparison_Comparable_Common_All<MODEL, RESULT, Byte,      AFTER_HAVING>,
		    ISharedComparison_Comparable_Common_All<MODEL, RESULT, Short,      AFTER_HAVING>,
		    ISharedComparison_Comparable_Common_All<MODEL, RESULT, Integer,    AFTER_HAVING>,
		    ISharedComparison_Comparable_Common_All<MODEL, RESULT, Long, 	  AFTER_HAVING>,
		    ISharedComparison_Comparable_Common_All<MODEL, RESULT, BigInteger, 	  AFTER_HAVING>,
		    ISharedComparison_Comparable_Common_All<MODEL, RESULT, Float, 	  AFTER_HAVING>,
		    ISharedComparison_Comparable_Common_All<MODEL, RESULT, Double, 	  AFTER_HAVING>,
		    ISharedComparison_Comparable_Common_All<MODEL, RESULT, BigDecimal, AFTER_HAVING>,
		    ISharedComparison_Comparable_Common_All<MODEL, RESULT, Date, 	  AFTER_HAVING>
		> {

}
