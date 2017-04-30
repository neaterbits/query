package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

public interface NotInUseISharedCondition_Comparative_Named<MODEL, RESULT, R extends Comparable<R>, L extends ISharedLogical_Base<MODEL, RESULT>>
	extends ISharedComparison_Comparable_Common_All<MODEL, RESULT, R, L>,
	        ISharedComparison_Equality_Named<MODEL, RESULT, R, L> {

	
	<T> ISharedLogical_Base<MODEL, RESULT> isGreaterThan(Function<T, R> getter);
	
	<T> ISharedLogical_Base<MODEL, RESULT> isGreaterOrEqualTo(Function<T, R> getter);
	
	<T> ISharedLogical_Base<MODEL, RESULT> isLesserThan(Function<T, R> getter);
	
	<T> ISharedLogical_Base<MODEL, RESULT> isLesserOrEqualTo(Function<T, R> getter);
	
}
