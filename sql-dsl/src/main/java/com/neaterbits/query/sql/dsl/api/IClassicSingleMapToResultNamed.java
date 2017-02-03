package com.neaterbits.query.sql.dsl.api;

public interface IClassicSingleMapToResultNamed<MODEL, RESULT>
	extends ISharedSingleSelectSourceBuilder<MODEL, RESULT>, IClassic_From_Named<MODEL, RESULT>, 
			ISharedResultMapperFromNamed<MODEL, RESULT, IClassicSingleMapToResultNamed<MODEL, RESULT>> {

}
