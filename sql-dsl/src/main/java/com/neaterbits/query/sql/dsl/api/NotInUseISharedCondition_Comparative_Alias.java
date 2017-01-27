package com.neaterbits.query.sql.dsl.api;

import java.util.function.Supplier;

public interface NotInUseISharedCondition_Comparative_Alias<MODEL, RESULT, R extends Comparable<R>, L extends ISharedLogical_Base<MODEL, RESULT>>
	extends ISharedCondition_Comparable_Common_All<MODEL, RESULT, R, L>,
        ISharedCondition_Equality_Alias<MODEL, RESULT, R, L> {

	ISharedLogical_Base<MODEL, RESULT> isGreaterThan(Supplier<R> getter);
	
	ISharedLogical_Base<MODEL, RESULT> isGreaterOrEqualTo(Supplier<R> getter);
	
	ISharedLogical_Base<MODEL, RESULT> isLesserThan(Supplier<R> getter);
	
	ISharedLogical_Base<MODEL, RESULT> isLesserOrEqualTo(Supplier<R> getter);

}
