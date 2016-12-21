package com.neaterbits.query.sql.dsl.api;

public interface IClassicSingleMapToResultTable<MODEL, RESULT>
	extends SingleSelectSourceBuilder<MODEL, RESULT>, IClassicSelectSourceBuilderTable<MODEL, RESULT>, 
			ISharedResultMapperFromTable<MODEL, RESULT, IClassicSingleMapToResultTable<MODEL, RESULT>> {

}
