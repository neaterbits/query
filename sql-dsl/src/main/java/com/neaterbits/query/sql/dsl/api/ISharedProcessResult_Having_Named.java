package com.neaterbits.query.sql.dsl.api;

public interface ISharedProcessResult_Having_Named<MODEL, RESULT> extends ISharedProcessResult_Base<MODEL, RESULT> {

	ISharedProcessResult_OrderBy_Mapped_Named<MODEL, RESULT> having(int foo); // TODO: filter-query 
	
}
