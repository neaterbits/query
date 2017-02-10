package com.neaterbits.query.sql.dsl.api;


public interface ISharedProcessResult_OrderBy_Mapped_Alias<MODEL, RESULT> extends ISharedProcessResult_OrderBy_Alias_Base<MODEL, RESULT> {

	ISharedCompileEndClause<MODEL> orderBy(int ... resultColumns);
	
}
