package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;
import java.util.function.Supplier;

public interface ISharedProcessResult_GroupBy_Undecided<MODEL, RESULT> extends ISharedProcessResult_Base<MODEL, RESULT> {

	<T, R> ISharedProcessResult_After_GroupBy_Or_List_Named<MODEL, RESULT> groupBy(Function<T, R> field);

	<R> ISharedProcessResult_After_GroupBy_Or_List_Alias<MODEL, RESULT> groupBy(Supplier<R> field);

	ISharedProcessResult_After_GroupBy_Undecided<MODEL, RESULT> groupBy(int ... resultColumns);
}
