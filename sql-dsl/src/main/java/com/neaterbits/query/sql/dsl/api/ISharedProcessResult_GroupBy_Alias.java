package com.neaterbits.query.sql.dsl.api;

import java.util.function.Supplier;

public interface ISharedProcessResult_GroupBy_Alias<MODEL, RESULT> extends ISharedProcessResult_Base<MODEL, RESULT> {

	<R> ISharedProcessResult_After_GroupBy_Or_List_Alias<MODEL, RESULT> groupBy(Supplier<R> field);

	ISharedProcessResult_After_GroupBy_Alias<MODEL, RESULT> groupBy(int ... resultColumns);
	
}
