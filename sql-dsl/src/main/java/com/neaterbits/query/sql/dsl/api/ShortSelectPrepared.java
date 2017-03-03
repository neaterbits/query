package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.function.Function;

final class ShortSelectPrepared extends BaseSelect<
	IShortResult_Numeric_Named<Long>,
	IShortResult_Numeric_Named<Long>,
	
	IShortResult_Numeric_Named<Short>,
	IShortResult_Numeric_Named<Integer>,
	IShortResult_Numeric_Named<Long>,
	IShortResult_Numeric_Named<BigDecimal>> 

	implements IShortPrepared {
	
	static ShortSelectPrepared get(QueryDataSource dataSource) {
		throw new UnsupportedOperationException("TODO");
	}
	
	private final QueryDataSource_Base<?> dataSource;
	
	ShortSelectPrepared(QueryDataSource_Base<?> dataSource) {
		
		if (dataSource == null) {
			throw new IllegalArgumentException("dataSource == null");
		}

		this.dataSource = dataSource;
	}

	private <T> ModelCompiler<SinglePrepared<T>> singleQueryPreparer() {
		return compiledQuery -> {
			final PreparedQuery_DS<?> preparedQuery = dataSource.prepareSingleQuery(SharedQuery_Base.q, compiledQuery);
			
			return new Shared_PreparedQuery_Single<>(dataSource, preparedQuery);
		};
	}

	private <T> ModelCompiler<MultiPrepared<T>> multiQueryPreparer() {
		
		return compiledQuery ->  {
			final PreparedQuery_DS<?> preparedQuery = dataSource.prepareMultiQuery(SharedQuery_Base.q, compiledQuery);
			
			return new Shared_PreparedQuery_Multi<>(dataSource, preparedQuery);
		};
	}
	
	@Override
	public <TYPE_RESULT> IShortResult_Single<SinglePrepared<TYPE_RESULT>, TYPE_RESULT> one(Class<TYPE_RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}	
		
		final SharedSelectSource selectSource = new SharedSelectSource_Named(cl);

		return new Short_Collector_SingleResult_Undecided<SinglePrepared<TYPE_RESULT>, TYPE_RESULT>(selectSource, singleQueryPreparer());
	}

	@Override
	public <TYPE_RESULT> IShortResult_Single<SinglePrepared<TYPE_RESULT>, TYPE_RESULT> oneOrNull(Class<TYPE_RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}
		
		final SharedSelectSource selectSource = new SharedSelectSource_Named(cl);

		return new Short_Collector_SingleResult_Undecided<SinglePrepared<TYPE_RESULT>, TYPE_RESULT>(selectSource, singleQueryPreparer());
	}

	@Override
	public <TYPE_RESULT> IShortResult_Multi<MultiPrepared<TYPE_RESULT>, TYPE_RESULT> list(Class<TYPE_RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}
		
		final SharedSelectSource selectSource = new SharedSelectSource_Named(cl);

		return new Short_Collector_MultiResult_Undecided<MultiPrepared<TYPE_RESULT>, TYPE_RESULT>(selectSource, ECollectionType.LIST, multiQueryPreparer());
	}

	@Override
	<T, NUM, RESULT, RET> RET sum(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		// TODO Auto-generated method stub
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
	<T, NUM, RESULT> IShortResult_Numeric_Named<Long> count(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		// TODO Auto-generated method stub
		return null;
	}
}
