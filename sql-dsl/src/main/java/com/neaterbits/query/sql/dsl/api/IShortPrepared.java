package com.neaterbits.query.sql.dsl.api;

public interface IShortPrepared {
	
	public static IShortPrepared get(DataConfig dataConfig) {
		return ShortSelectPrepared.get(dataConfig);
	}
	
	<TYPE_RESULT> IShortResult_Single<SinglePrepared<TYPE_RESULT>, TYPE_RESULT> one(Class<TYPE_RESULT> cl);

	<TYPE_RESULT> IShortResult_Single<SinglePrepared<TYPE_RESULT>, TYPE_RESULT> oneOrNull(Class<TYPE_RESULT> cl);

	<TYPE_RESULT> IShortResult_Multi<MultiPrepared<TYPE_RESULT>, TYPE_RESULT> list(Class<TYPE_RESULT> cl);

}
