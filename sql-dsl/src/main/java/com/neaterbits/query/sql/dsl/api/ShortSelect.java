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
	public <RESULT> IShortResult_Single<SingleQuery<RESULT>, RESULT> one(Class<RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}
		
		final SharedSelectSource selectSource = new SharedSelectSource_Named(cl);

		return new Short_Collector_SingleResult_Undecided<SingleQuery<RESULT>, RESULT>(selectSource, singleQueryCompiler());
	}

	@Override
	public <RESULT> IShortResult_Single<SingleQuery<RESULT>, RESULT> oneOrNull(Class<RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}
		
		final SharedSelectSource selectSource = new SharedSelectSource_Named(cl);

		return new Short_Collector_SingleResult_Undecided<SingleQuery<RESULT>, RESULT>(selectSource, singleQueryCompiler());
	}

	@Override
	public <RESULT> IShortResult_Multi<MultiQuery<RESULT>, RESULT> list(Class<RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}
		
		final SharedSelectSource selectSource = new SharedSelectSource_Named(cl);

		return new Short_Collector_MultiResult_Undecided<MultiQuery<RESULT>, RESULT>(selectSource, ECollectionType.LIST, multiQueryCompiler());
	}

	@Override
	<T, NUM, RESULT, RET> RET sum(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	<T, NUM, RESULT, RET> RET max(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	<T, NUM, RESULT, RET> RET min(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	<T, NUM, RESULT, RET> RET avg(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	<T, NUM, RESULT> IShortResult_Numeric_Named<Long> count(Function<T, NUM> field, Class<NUM> numCl,
			Class<RESULT> resultCl) {
		throw new UnsupportedOperationException("TODO");
	}
}
