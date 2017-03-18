package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.function.Function;

final class ShortSelect extends BaseShortSelect<

		IShortBuilt_Numeric_Named<Long>,
		IShortBuilt_Numeric_Named<Long>,
		
		IShortBuilt_Numeric_Named<Short>,
		IShortBuilt_Numeric_Named<Integer>,
		IShortBuilt_Numeric_Named<Long>,
		IShortBuilt_Numeric_Named<Double>,
		IShortBuilt_Numeric_Named<BigDecimal>>

	
	implements IShort

{

	static final ShortSelect selectImpl = new ShortSelect();
	
	
	ShortSelect() {
		super(null);
	}
	
	
	// Aggregate helpers
	@Override
	public <RESULT> IShortResult_Single<SingleBuilt<RESULT>, RESULT> one(Class<RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}	
		
		final SharedSelectSource selectSource = new SharedSelectSource_Named(cl);

		return new Short_Collector_SingleResult_Undecided<SingleBuilt<RESULT>, RESULT>(this, selectSource, singleQueryCollected());
	}

	@Override
	public <RESULT> IShortResult_Single<SingleBuilt<RESULT>, RESULT> oneOrNull(Class<RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}
		
		final SharedSelectSource selectSource = new SharedSelectSource_Named(cl);

		return new Short_Collector_SingleResult_Undecided<SingleBuilt<RESULT>, RESULT>(this, selectSource, singleQueryCollected());
	}

	@Override
	public <RESULT> IShortResult_Multi<MultiBuilt<RESULT>, RESULT> list(Class<RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}
		
		final SharedSelectSource selectSource = new SharedSelectSource_Named(cl);

		return new Short_Collector_MultiResult_Undecided<MultiBuilt<RESULT>, RESULT>(this, selectSource, ECollectionType.LIST, multiQueryCollected());
	}

	@Override
	@SuppressWarnings("unchecked")
	<T, NUM, RESULT, RET> RET sum(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		
		final QueryResultSum result = new QueryResultSum(resultCl, new FunctionGetter(field));
		
		return (RET)new Short_Collector_Aggregate_Named<RESULT>(this, singleQueryCollected(), result);
	}

	@Override
	@SuppressWarnings("unchecked")
	<T, NUM, RESULT, RET> RET max(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		final QueryResultMax result = new QueryResultMax(resultCl, new FunctionGetter(field));
		
		return (RET)new Short_Collector_Aggregate_Named<RESULT>(this, singleQueryCollected(), result);
	}

	@Override
	@SuppressWarnings("unchecked")
	<T, NUM, RESULT, RET> RET min(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		final QueryResultMin result = new QueryResultMin(resultCl, new FunctionGetter(field));
		
		return (RET)new Short_Collector_Aggregate_Named<RESULT>(this, singleQueryCollected(), result);
	}

	@Override
	@SuppressWarnings("unchecked")
	<T, NUM, RESULT, RET> RET avg(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		final QueryResultAvg result = new QueryResultAvg(resultCl, new FunctionGetter(field));
		
		return (RET)new Short_Collector_Aggregate_Named<RESULT>(this, singleQueryCollected(), result);
	}

	@Override
	@SuppressWarnings("unchecked")
	<T, NUM, RESULT> IShortBuilt_Numeric_Named<Long> count(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		final QueryResultCount result = new QueryResultCount(resultCl, new FunctionGetter(field));
		
		return (IShortBuilt_Numeric_Named<Long>)new Short_Collector_Aggregate_Named<RESULT>(this, singleQueryCollected(), result);
	}
}
