package com.neaterbits.query.sql.dsl.api;

public interface IClassicResult_Mapped_Single_Named<MODEL, RESULT>
	extends ISharedSingleSelectSourceBuilder<MODEL, RESULT>, IClassic_From_SingleResult_Named<MODEL, RESULT>, 
			ISharedResultMapper_Named<MODEL, RESULT, IClassicResult_Mapped_Single_Named<MODEL, RESULT>> {

}
