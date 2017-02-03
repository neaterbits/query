package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

public interface ISharedProcessResult_GroupBy_Named<MODEL, RESULT> extends ISharedProcessResult_Base<MODEL, RESULT> {

	<T, R> ISharedProcessResult_After_GroupBy_Or_List_Named<MODEL, RESULT> groupBy(Function<T, R> field);

	ISharedProcessResult_After_GroupBy_Named<MODEL, RESULT> groupBy(int ... resultColumns);
	
}
