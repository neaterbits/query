package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.function.Function;

final class ShortSelect extends BaseSelect< 
	IShortResult_Numeric_Named<Long>,
	IShortResult_Numeric_Named<Long>,
	
	IShortResult_Numeric_Named<Short>,
	IShortResult_Numeric_Named<Integer>,
	IShortResult_Numeric_Named<Long>,
	IShortResult_Numeric_Named<BigDecimal>>

	
	implements IShort

{

	static final ShortSelect selectImpl = new ShortSelect();
	
	// Aggregate helpers
	@Override
	public <TYPE_RESULT> IShortResult_Single<SingleQuery<TYPE_RESULT>, TYPE_RESULT> one(Class<TYPE_RESULT> cl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <TYPE_RESULT> IShortResult_Single<SingleQuery<TYPE_RESULT>, TYPE_RESULT> oneOrNull(Class<TYPE_RESULT> cl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <TYPE_RESULT> IShortResult_Multi<MultiQuery<TYPE_RESULT>, TYPE_RESULT> list(Class<TYPE_RESULT> cl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	<T, NUM, RESULT, RET> RET sum(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		return null;
	}

	@Override
	<T, NUM, RESULT, RET> RET max(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	<T, NUM, RESULT, RET> RET min(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	<T, NUM, RESULT, RET> RET avg(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	<T, NUM, RESULT> IShortResult_Numeric_Named<Long> count(Function<T, NUM> field, Class<NUM> numCl,
			Class<RESULT> resultCl) {
		// TODO Auto-generated method stub
		return null;
	}
}
