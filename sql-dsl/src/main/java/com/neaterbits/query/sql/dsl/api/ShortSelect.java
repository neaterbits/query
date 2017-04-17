package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.function.Function;
import java.util.function.Supplier;

final class ShortSelect extends BaseShortSelect<

		IShortBuilt_Numeric_Named<Long>,
		IShortBuilt_Numeric_Named<Long>,
		
		IShortBuilt_Numeric_Named<Byte>,
		IShortBuilt_Numeric_Named<Short>,
		IShortBuilt_Numeric_Named<Integer>,
		IShortBuilt_Numeric_Named<Long>,
		IShortBuilt_Numeric_Named<BigInteger>,
		IShortBuilt_Numeric_Named<Float>,
		IShortBuilt_Numeric_Named<Double>,
		IShortBuilt_Numeric_Named<BigDecimal>,
		IShortBuilt_Numeric_Named<java.util.Date>,
		IShortBuilt_Numeric_Named<java.util.Calendar>,
		IShortBuilt_Numeric_Named<java.sql.Date>,
		IShortBuilt_Numeric_Named<java.sql.Time>,
		IShortBuilt_Numeric_Named<java.sql.Timestamp>,

		IShortBuilt_Numeric_Alias<Long>,
		IShortBuilt_Numeric_Alias<Long>,
		
		IShortBuilt_Numeric_Alias<Byte>,
		IShortBuilt_Numeric_Alias<Short>,
		IShortBuilt_Numeric_Alias<Integer>,
		IShortBuilt_Numeric_Alias<Long>,
		IShortBuilt_Numeric_Alias<BigInteger>,
		IShortBuilt_Numeric_Alias<Float>,
		IShortBuilt_Numeric_Alias<Double>,
		IShortBuilt_Numeric_Alias<BigDecimal>,
		IShortBuilt_Numeric_Alias<java.util.Date>,
		IShortBuilt_Numeric_Alias<java.util.Calendar>,
		IShortBuilt_Numeric_Alias<java.sql.Date>,
		IShortBuilt_Numeric_Alias<java.sql.Time>,
		IShortBuilt_Numeric_Alias<java.sql.Timestamp>
	>

	
	implements IShort

{

	static final ShortSelect selectImpl = new ShortSelect();
	
	
	ShortSelect() {
		super(null);
	}
	
	
	@Override
	public <RESULT> IShortResult_Single_Instance<SingleBuilt<RESULT>, RESULT> one(Class<RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}	
		
		final SharedSelectSource selectSource = new SharedSelectSource_Named(cl);

		return new Short_Collector_Single_Any_Any_Instance<SingleBuilt<RESULT>, RESULT>(this, selectSource, singleQueryCollected());
	}

	@Override
	public <RESULT> IShortResult_Single_Instance<SingleBuilt<RESULT>, RESULT> oneOrNull(Class<RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}
		
		final SharedSelectSource selectSource = new SharedSelectSource_Named(cl);

		return new Short_Collector_Single_Any_Any_Instance<SingleBuilt<RESULT>, RESULT>(this, selectSource, singleQueryCollected());
	}

	@Override
	public <RESULT> IShortResult_Multi_Instance<MultiBuilt<RESULT>, RESULT> list(Class<RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}
		
		final SharedSelectSource selectSource = new SharedSelectSource_Named(cl);

		return new Short_Collector_Multi_Any_Any<MultiBuilt<RESULT>, RESULT>(this, selectSource, ECollectionType.LIST, multiQueryCollected());
	}
	
	
	private void checkAlias(Object alias) {
		
		if (alias == null) {
			throw new IllegalArgumentException("alias == null");
		}

		if (!(alias instanceof IAlias)) {
			throw new IllegalArgumentException("not an alias, must be instantiated with .alias(Class<?>) method");
		}
	}

	
	@Override
	public <RESULT> IShortResult_Entity_Single_Alias<SingleBuilt<RESULT>, RESULT> one(RESULT alias) {

		checkAlias(alias);
		
		final SharedSelectSource_Alias selectSource = new SharedSelectSource_Alias((IAlias)alias);

		return new Short_Collector_Initial_Single_Entity_Alias<>(this, singleQueryCollected(), new CollectedQueryResult_Entity_Single(selectSource), selectSource);
	}


	@Override
	public <TYPE_RESULT> IShortResult_Entity_Single_Alias<SingleBuilt<TYPE_RESULT>, TYPE_RESULT> oneOrNull(TYPE_RESULT alias) {

		checkAlias(alias);
		
		final SharedSelectSource_Alias selectSource = new SharedSelectSource_Alias((IAlias)alias);

		return new Short_Collector_Initial_Single_Entity_Alias<>(this, singleQueryCollected(), new CollectedQueryResult_Entity_Single(selectSource), selectSource);
	}


	@Override
	public <RESULT> IShortResult_Entity_Multi_Alias<MultiBuilt<RESULT>, RESULT> list(RESULT alias) {
		
		checkAlias(alias);
		
		final SharedSelectSource_Alias selectSource = new SharedSelectSource_Alias((IAlias)alias);

		final CollectedQueryResult_Entity_Multi result = new CollectedQueryResult_Entity_Multi(selectSource, ECollectionType.LIST);
		
		final Collector_Query<MultiBuilt<RESULT>> collector = new QueryCollectorImpl<>(this, multiQueryCollected(), result);
		

		return new Short_Collector_Multi_Entity_Alias<MultiBuilt<RESULT>, RESULT>(collector, null);
	}

	// Aggregate helpers

	// --------------------------------- Named ---------------------------------
	@Override
	@SuppressWarnings("unchecked")
	<T, NUM, RESULT, RET> RET sum(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		
		final CollectedQueryResult_Sum result = new CollectedQueryResult_Sum(resultCl, new FunctionGetter(field));
		
		return (RET)new Short_Collector_Initial_Single_Aggregate_Named<RESULT>(this, singleQueryCollected(), result);
	}

	@Override
	@SuppressWarnings("unchecked")
	<T, NUM, RESULT, RET> RET max(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		final CollectedQueryResult_Max result = new CollectedQueryResult_Max(resultCl, new FunctionGetter(field));
		
		return (RET)new Short_Collector_Initial_Single_Aggregate_Named<RESULT>(this, singleQueryCollected(), result);
	}

	@Override
	@SuppressWarnings("unchecked")
	<T, NUM, RESULT, RET> RET min(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		final CollectedQueryResult_Min result = new CollectedQueryResult_Min(resultCl, new FunctionGetter(field));
		
		return (RET)new Short_Collector_Initial_Single_Aggregate_Named<RESULT>(this, singleQueryCollected(), result);
	}

	@Override
	@SuppressWarnings("unchecked")
	<T, NUM, RESULT, RET> RET avg(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		final CollectedQueryResult_Avg result = new CollectedQueryResult_Avg(resultCl, new FunctionGetter(field));
		
		return (RET)new Short_Collector_Initial_Single_Aggregate_Named<RESULT>(this, singleQueryCollected(), result);
	}

	@Override
	@SuppressWarnings("unchecked")
	<T, NUM, RESULT> IShortBuilt_Numeric_Named<Long> count(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		final CollectedQueryResult_Count result = new CollectedQueryResult_Count(resultCl, new FunctionGetter(field));
		
		return (IShortBuilt_Numeric_Named<Long>)new Short_Collector_Initial_Single_Aggregate_Named<RESULT>(this, singleQueryCollected(), result);
	}

	// --------------------------------- Alias ---------------------------------
	@Override
	@SuppressWarnings("unchecked")
	<NUM, RESULT, RET> RET sum(Supplier<NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		
		final CollectedQueryResult_Sum result = new CollectedQueryResult_Sum(resultCl, new SupplierGetter(field));
		
		return (RET)new Short_Collector_Initial_Single_Aggregate_Alias<RESULT>(this, singleQueryCollected(), result);
	}

	@Override
	@SuppressWarnings("unchecked")
	<NUM, RESULT, RET> RET max(Supplier<NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		final CollectedQueryResult_Max result = new CollectedQueryResult_Max(resultCl, new SupplierGetter(field));
		
		return (RET)new Short_Collector_Initial_Single_Aggregate_Alias<RESULT>(this, singleQueryCollected(), result);
	}

	@Override
	@SuppressWarnings("unchecked")
	<NUM, RESULT, RET> RET min(Supplier<NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		final CollectedQueryResult_Min result = new CollectedQueryResult_Min(resultCl, new SupplierGetter(field));
		
		return (RET)new Short_Collector_Initial_Single_Aggregate_Alias<RESULT>(this, singleQueryCollected(), result);
	}

	@Override
	@SuppressWarnings("unchecked")
	<NUM, RESULT, RET> RET avg(Supplier<NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		final CollectedQueryResult_Avg result = new CollectedQueryResult_Avg(resultCl, new SupplierGetter(field));
		
		return (RET)new Short_Collector_Initial_Single_Aggregate_Alias<RESULT>(this, singleQueryCollected(), result);
	}

	@Override
	@SuppressWarnings("unchecked")
	<NUM, RESULT> IShortBuilt_Numeric_Alias<Long> count(Supplier<NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		final CollectedQueryResult_Count result = new CollectedQueryResult_Count(resultCl, new SupplierGetter(field));
		
		return (IShortBuilt_Numeric_Alias<Long>)new Short_Collector_Initial_Single_Aggregate_Alias<RESULT>(this, singleQueryCollected(), result);
	}
}
