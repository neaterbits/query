package com.neaterbits.query.sql.dsl.api;

public interface IClassicResult_Mapped_Single_Undecided<MODEL, RESULT>
	extends ISharedSingleSelectSourceBuilder<MODEL, RESULT>, ISharedFunction_After<MODEL, RESULT> /* TODO,
			 IClassic_From_SingleResult_Alias<MODEL, RESULT>,
			ISharedResultMapper_Alias<MODEL, RESULT, IClassicResult_Mapped_Single_Alias<MODEL, RESULT>> */ {
	
}