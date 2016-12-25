package com.neaterbits.query.sql.dsl.api;

import java.util.List;
import java.util.function.Function;

public interface IAdhocEndClauseList<MODEL, T, RESULT_LIST extends List<T>>
		extends IAdhocEndClauseCollection<MODEL, T, RESULT_LIST> {

	<L extends List<T>> L as(Function<RESULT_LIST, L> function);
			
			
}
		
		
