package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.function.Function;
import java.util.function.Supplier;

final class ResultMapperOps_Numeric<
		MODEL,
		RESULT,
		R extends Comparable<R>,
		SOURCE extends ISharedSelectSourceBuilder<MODEL, RESULT>>
		
	extends ResultMapperOps<MODEL, RESULT, R, SOURCE> 

	implements ISharedResultOps_Numeric_Named<MODEL, RESULT, R, SOURCE> {
	
	ResultMapperOps_Numeric(Expression expression, IMappingCollector<MODEL, RESULT> impl) {
		super(expression, impl);
	}

	@Deprecated
	ResultMapperOps_Numeric(Function<?, ?> fromGetter, IMappingCollector<MODEL, RESULT> impl, CollectedFunctions collectedFunctions) {
		super(fromGetter, impl, collectedFunctions);
	}

	@Deprecated
	ResultMapperOps_Numeric(Function<?, ?> fromGetter, IMappingCollector<MODEL, RESULT> impl) {
		super(fromGetter, impl);
	}

	@Deprecated
	ResultMapperOps_Numeric(Supplier<?> fromSupplier, IMappingCollector<MODEL, RESULT> impl, CollectedFunctions collectedFunctions) {
		super(fromSupplier, impl, collectedFunctions);
	}

	@Deprecated
	ResultMapperOps_Numeric(Supplier<?> fromSupplier, IMappingCollector<MODEL, RESULT> impl) {
		super(fromSupplier, impl);
	}

	@Override
	public <T> ISharedResultOps_Numeric_Named<MODEL, RESULT, R, SOURCE> plus(IFunctionShort<T> getter) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public <T> ISharedResultOps_Numeric_Named<MODEL, RESULT, R, SOURCE> plus(short value) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public <T> ISharedResultOps_Numeric_Named<MODEL, RESULT, R, SOURCE> plus(IFunctionBigDecimal<T> getter) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public ISharedResultOps_Numeric_Named<MODEL, RESULT, R, SOURCE> plusOf(ISharedSubOperandsFunction_Named<MODEL, RESULT, BigDecimal> builder) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public ISharedResultOps_Numeric_Named<MODEL, RESULT, R, SOURCE> plus(BigDecimal value) {
		throw new UnsupportedOperationException("TODO");
	}

	
}
