package com.neaterbits.query.sql.dsl.api;

public interface ISharedProcessResult_Having_Alias<MODEL, RESULT> extends ISharedProcessResult_Base<MODEL, RESULT> {

	ISharedProcessResult_OrderBy_Alias<MODEL, RESULT> having(String bar); // TODO: filter-query 
}
