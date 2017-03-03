package com.neaterbits.query.sql.dsl.api;

public interface IShortPrepared {
	
	public static IShortPrepared get(QueryDataSource dataSource) {
		return ShortSelectPrepared.get(dataSource);
	}
	
	<TYPE_RESULT> IShortResult_Single<SinglePrepared<TYPE_RESULT>, TYPE_RESULT> one(Class<TYPE_RESULT> cl);

	<TYPE_RESULT> IShortResult_Single<SinglePrepared<TYPE_RESULT>, TYPE_RESULT> oneOrNull(Class<TYPE_RESULT> cl);

	<TYPE_RESULT> IShortResult_Multi<MultiPrepared<TYPE_RESULT>, TYPE_RESULT> list(Class<TYPE_RESULT> cl);

}
