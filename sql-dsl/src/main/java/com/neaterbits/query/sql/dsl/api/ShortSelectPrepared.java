package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.function.Function;
import java.util.function.Supplier;

import com.neaterbits.query.sql.dsl.api.entity.QueryMetaModel;

final class ShortSelectPrepared extends BaseShortSelect<
		IShortPrepared_Numeric_Named<Long>,
		IShortPrepared_Numeric_Named<Long>,
		
		IShortPrepared_Numeric_Named<Short>,
		IShortPrepared_Numeric_Named<Integer>,
		IShortPrepared_Numeric_Named<Long>,
		IShortPrepared_Numeric_Named<Double>,
		IShortPrepared_Numeric_Named<BigDecimal>
	>

	implements IShortPrepared {
	
	static ShortSelectPrepared get(DataConfig dataConfig) {
		
		@SuppressWarnings("rawtypes")
		final DataConfigBase baseConfig = (DataConfigBase)dataConfig;
		
		final QueryMetaModel metaModel = baseConfig.getQueryMetaModel();

		return new ShortSelectPrepared(metaModel, (QueryDataSource_Base<?>)baseConfig.getDataSource());
	}
	
	private final QueryDataSource_Base<?> dataSource;
	
	private ShortSelectPrepared(QueryMetaModel metaModel, QueryDataSource_Base<?> dataSource) {
		super(metaModel);
		
		if (dataSource == null) {
			throw new IllegalArgumentException("dataSource == null");
		}

		this.dataSource = dataSource;
	}

	private <T> ModelCompiler<SinglePrepared<T>> singleQueryPreparer() {
		return collectedQuery -> {
			final PreparedQuery_DS<?> preparedQuery = dataSource.prepareSingleQuery(CompiledQuery.q, compile(collectedQuery, collectedQuery.getQueryMetaModel()));
			
			return new Shared_PreparedQuery_Single<>(dataSource, preparedQuery);
		};
	}

	private <T> ModelCompiler<MultiPrepared<T>> multiQueryPreparer() {
		
		return collectedQuery ->  {
			final PreparedQuery_DS<?> preparedQuery = dataSource.prepareMultiQuery(CompiledQuery.q, compile(collectedQuery, collectedQuery.getQueryMetaModel()));
			
			return new Shared_PreparedQuery_Multi<>(dataSource, preparedQuery);
		};
	}
	
	@Override
	public <TYPE_RESULT> IShortResult_Single<SinglePrepared<TYPE_RESULT>, TYPE_RESULT> one(Class<TYPE_RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}	
		
		final SharedSelectSource selectSource = new SharedSelectSource_Named(cl);

		return new Short_Collector_SingleResult_Undecided<SinglePrepared<TYPE_RESULT>, TYPE_RESULT>(this, selectSource, singleQueryPreparer());
	}

	@Override
	public <TYPE_RESULT> IShortResult_Single<SinglePrepared<TYPE_RESULT>, TYPE_RESULT> oneOrNull(Class<TYPE_RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}
		
		final SharedSelectSource selectSource = new SharedSelectSource_Named(cl);

		return new Short_Collector_SingleResult_Undecided<SinglePrepared<TYPE_RESULT>, TYPE_RESULT>(this, selectSource, singleQueryPreparer());
	}

	@Override
	public <TYPE_RESULT> IShortResult_Multi<MultiPrepared<TYPE_RESULT>, TYPE_RESULT> list(Class<TYPE_RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}
		
		final SharedSelectSource selectSource = new SharedSelectSource_Named(cl);

		return new Short_Collector_MultiResult_Undecided<MultiPrepared<TYPE_RESULT>, TYPE_RESULT>(this, selectSource, ECollectionType.LIST, multiQueryPreparer());
	}

	// TODO: prepared-query
	
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
	<T, NUM, RESULT> IShortPrepared_Numeric_Named<Long> count(Function<T, NUM> field, Class<NUM> numCl,
			Class<RESULT> resultCl) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	<NUM, RESULT, RET> RET sum(Supplier<NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	<NUM, RESULT, RET> RET max(Supplier<NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	<NUM, RESULT, RET> RET min(Supplier<NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	<NUM, RESULT, RET> RET avg(Supplier<NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	<NUM, RESULT> IShortPrepared_Numeric_Named<Long> count(Supplier<NUM> field, Class<NUM> numCl,
			Class<RESULT> resultCl) {
		throw new UnsupportedOperationException("TODO");
	}
}
