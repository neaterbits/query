package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public interface ISharedProcessResult_Having_Aggregate_Named<MODEL, RESULT, AFTER_HAVING extends ISharedLogical_Base<MODEL, RESULT>>

	extends ISharedFunctions_Aggregate_Named<
		    ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, AFTER_HAVING>,
		    ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, AFTER_HAVING>,
			
		    ISharedCondition_Comparable_Common_All<MODEL, RESULT, Byte,      AFTER_HAVING>,
		    ISharedCondition_Comparable_Common_All<MODEL, RESULT, Short,      AFTER_HAVING>,
		    ISharedCondition_Comparable_Common_All<MODEL, RESULT, Integer,    AFTER_HAVING>,
		    ISharedCondition_Comparable_Common_All<MODEL, RESULT, Long, 	  AFTER_HAVING>,
		    ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigInteger, 	  AFTER_HAVING>,
		    ISharedCondition_Comparable_Common_All<MODEL, RESULT, Float, 	  AFTER_HAVING>,
		    ISharedCondition_Comparable_Common_All<MODEL, RESULT, Double, 	  AFTER_HAVING>,
		    ISharedCondition_Comparable_Common_All<MODEL, RESULT, BigDecimal, AFTER_HAVING>,
		    ISharedCondition_Comparable_Common_All<MODEL, RESULT, Date, 	  AFTER_HAVING>
		> {

}
