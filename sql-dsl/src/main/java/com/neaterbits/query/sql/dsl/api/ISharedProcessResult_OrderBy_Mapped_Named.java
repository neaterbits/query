package com.neaterbits.query.sql.dsl.api;


public interface ISharedProcessResult_OrderBy_Mapped_Named<MODEL, RESULT> extends ISharedProcessResult_OrderBy_Named_Base<MODEL, RESULT> {

	ISharedCompileEndClause<MODEL> orderBy(int ... resultColumns);
	
}
