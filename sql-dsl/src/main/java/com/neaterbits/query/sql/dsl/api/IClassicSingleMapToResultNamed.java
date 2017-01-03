package com.neaterbits.query.sql.dsl.api;

public interface IClassicSingleMapToResultNamed<MODEL, RESULT>
	extends ISharedSingleSelectSourceBuilder<MODEL, RESULT>, IClassicSelectSourceBuilderNamed<MODEL, RESULT>, 
			ISharedResultMapperFromNamed<MODEL, RESULT, IClassicSingleMapToResultNamed<MODEL, RESULT>> {

}
