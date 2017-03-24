package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.function.Function;
import java.util.function.Supplier;


final class ClassicSelect
	extends BaseSelect<
		IClassicResult_Numeric_Named<Long>,
		IClassicResult_Numeric_Named<Long>,
		
		IClassicResult_Numeric_Named<Short>,
		IClassicResult_Numeric_Named<Integer>,
		IClassicResult_Numeric_Named<Long>,
		IClassicResult_Numeric_Named<Double>,
		IClassicResult_Numeric_Named<BigDecimal>,
		
		IClassicResult_Numeric_Alias<Long>,
		IClassicResult_Numeric_Alias<Long>,
		
		IClassicResult_Numeric_Alias<Short>,
		IClassicResult_Numeric_Alias<Integer>,
		IClassicResult_Numeric_Alias<Long>,
		IClassicResult_Numeric_Alias<Double>,
		IClassicResult_Numeric_Alias<BigDecimal>		
	>

	implements IClassic {

	static final ClassicSelect selectImpl = new ClassicSelect();
	
	
	
	/*
	@Override
	public <MAPPED_RESULT> IClassicResult_Mapped_Single_All<SingleQuery<MAPPED_RESULT>, MAPPED_RESULT> selectOneOrNull(Class<MAPPED_RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
			}

		return new Collector_MapToResult_Single<SingleQuery<MAPPED_RESULT>, MAPPED_RESULT>(cl, singleQueryCompiler());
	}

	@Override
	public <MAPPED_RESULT> IClassicResult_Mapped_Multi_All<MultiQuery<MAPPED_RESULT>, MAPPED_RESULT> selectList(Class<MAPPED_RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}

		return new Collector_MapToResult_Multi<MultiQuery<MAPPED_RESULT>, MAPPED_RESULT>(cl, ECollectionType.LIST, multiQueryCompiler());
	}

	@Override
	public <ENTITY_RESULT> IClassicResult_Entity_Single<SingleQuery<ENTITY_RESULT>, ENTITY_RESULT> selectOneFrom(Class<ENTITY_RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}

		final SharedSelectSource selectSource = new SharedSelectSource_Named(cl);

		return new SQL_Collector_SingleTypeResult<SingleQuery<ENTITY_RESULT>, ENTITY_RESULT>(selectSource, singleQueryCompiler());
	}

	@Override
	public <ENTITY_RESULT> IClassicResult_Entity_Multi<MultiQuery<ENTITY_RESULT>, ENTITY_RESULT> selectListFrom(Class<ENTITY_RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}
		
		final SharedSelectSource selectSource = new SharedSelectSource_Named(cl);

		return new SQL_Collector_MultiEntityResult<MultiQuery<ENTITY_RESULT>, ENTITY_RESULT>(selectSource, ECollectionType.LIST, multiQueryCompiler());
	}
	*/

	ClassicSelect() {
		super(null);
	}
	
	
	@Override
	EQueryStyle getQueryStyle() {
		return EQueryStyle.CLASSIC;
	}

	@Override
	public <RESULT> IClassicResult_Single<SingleBuilt<RESULT>, RESULT> one(Class<RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}
		
		final SharedSelectSource selectSource = new SharedSelectSource_Named(cl);

		return new Classic_Collector_SingleResult<SingleBuilt<RESULT>, RESULT>(this, selectSource, singleQueryCompiled());
	}

	@Override
	public <RESULT> IClassicResult_Single<SingleBuilt<RESULT>, RESULT> oneOrNull(Class<RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}
		
		final SharedSelectSource selectSource = new SharedSelectSource_Named(cl);

		return new Classic_Collector_SingleResult<SingleBuilt<RESULT>, RESULT>(this, selectSource, singleQueryCompiled());
	}

	@Override
	public <RESULT> IClassicResult_Multi<MultiBuilt<RESULT>, RESULT> list(Class<RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}
		
		final SharedSelectSource selectSource = new SharedSelectSource_Named(cl);

		return new Classic_Collector_MultiResult<MultiBuilt<RESULT>, RESULT>(this, selectSource, ECollectionType.LIST, multiQueryCompiled());
	}

	@Override
	public <TYPE_RESULT> IClassicSingleWhereClauseBuilderNamed<SingleBuilt<TYPE_RESULT>, TYPE_RESULT> oneFrom(Class<TYPE_RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}

		final SharedSelectSource selectSource = new SharedSelectSource_Named(cl);
		
		return new ClassicSingleNamedResult<SingleBuilt<TYPE_RESULT>, TYPE_RESULT>(this, new CollectedQueryResult_Entity_Single(selectSource), singleQueryCompiled());
	}


	@Override
	public <TYPE_RESULT> IClassicSingleWhereClauseBuilderNamed<MultiBuilt<TYPE_RESULT>, TYPE_RESULT> listFrom(Class<TYPE_RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}

		final SharedSelectSource selectSource = new SharedSelectSource_Named(cl);

		final CollectedQueryResult_Entity_Multi result = new CollectedQueryResult_Entity_Multi(selectSource, ECollectionType.LIST);
		
		return new ClassicSingleNamedResult<MultiBuilt<TYPE_RESULT>, TYPE_RESULT>(this, result, multiQueryCompiled());
	}


	// Aggregate helpers
	
	
	// --------------------------------- Named ---------------------------------

	@Override
	@SuppressWarnings("unchecked")
	<T, NUM, RESULT, RET> RET sum(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		return (RET)new ClassicCollector_AggregateNamedResult<>(this, new QueryResultSum(resultCl, new FunctionGetter(field)), singleQueryCompiled());
	}

	@Override
	@SuppressWarnings("unchecked")
	<T, NUM, RESULT, RET> RET max(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		return (RET)new ClassicCollector_AggregateNamedResult<>(this, new QueryResultMax(resultCl, new FunctionGetter(field)), singleQueryCompiled());
	}

	@Override
	@SuppressWarnings("unchecked")
	<T, NUM, RESULT, RET> RET min(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		return (RET)new ClassicCollector_AggregateNamedResult<>(this, new QueryResultMin(resultCl, new FunctionGetter(field)), singleQueryCompiled());
	}
	
	@Override
	@SuppressWarnings("unchecked")
	<T, NUM, RESULT, RET> RET avg(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		return (RET)new ClassicCollector_AggregateNamedResult<>(this, new QueryResultAvg(resultCl, new FunctionGetter(field)), singleQueryCompiled());
	}

	@Override
	<T, NUM, RESULT> IClassicResult_Numeric_Named<Long> count(Function<T, NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		return new ClassicCollector_AggregateNamedResult<>(this, new QueryResultCount(resultCl, new FunctionGetter(field)), singleQueryCompiled());
	}
	

	// --------------------------------- Alias ---------------------------------

	@Override
	@SuppressWarnings("unchecked")
	<NUM, RESULT, RET> RET sum(Supplier<NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		return (RET)new ClassicCollector_AggregateAliasResult<>(this, new QueryResultSum(resultCl, new SupplierGetter(field)), singleQueryCompiled());
	}

	@Override
	@SuppressWarnings("unchecked")
	<NUM, RESULT, RET> RET max(Supplier<NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		return (RET)new ClassicCollector_AggregateAliasResult<>(this, new QueryResultMax(resultCl, new SupplierGetter(field)), singleQueryCompiled());
	}

	@Override
	@SuppressWarnings("unchecked")
	<NUM, RESULT, RET> RET min(Supplier<NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		return (RET)new ClassicCollector_AggregateAliasResult<>(this, new QueryResultMin(resultCl, new SupplierGetter(field)), singleQueryCompiled());
	}
	
	@Override
	@SuppressWarnings("unchecked")
	<NUM, RESULT, RET> RET avg(Supplier<NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		return (RET)new ClassicCollector_AggregateAliasResult<>(this, new QueryResultAvg(resultCl, new SupplierGetter(field)), singleQueryCompiled());
	}

	@Override
	<NUM, RESULT> IClassicResult_Numeric_Alias<Long> count(Supplier<NUM> field, Class<NUM> numCl, Class<RESULT> resultCl) {
		return new ClassicCollector_AggregateAliasResult<>(this, new QueryResultCount(resultCl, new SupplierGetter(field)), singleQueryCompiled());
	}
}
