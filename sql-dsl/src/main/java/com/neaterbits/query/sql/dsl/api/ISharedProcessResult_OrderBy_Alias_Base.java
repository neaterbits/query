package com.neaterbits.query.sql.dsl.api;

import java.util.function.Supplier;

public interface ISharedProcessResult_OrderBy_Alias_Base<MODEL, RESULT> extends ISharedProcessResult_Base<MODEL, RESULT> {

	<R> ISharedProcessResult_OrderBy_AfterSortOrder_Alias<MODEL, RESULT> orderBy(Supplier<R> field);

}
