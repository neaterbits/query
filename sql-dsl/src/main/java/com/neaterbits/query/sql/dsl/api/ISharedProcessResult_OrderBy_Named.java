package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

public interface ISharedProcessResult_OrderBy_Named<MODEL, RESULT> extends ISharedProcessResult_Base<MODEL, RESULT> {

	<T, R> ISharedProcessResult_After_OrderBy_Or_List_Named<MODEL, RESULT> orderBy(Function<T, R> field);

	ISharedCompileEndClause<MODEL> orderBy(int ... resultColumns);
	
}
