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

	@Override
	public <T> ISharedResultOps_Numeric_Named<MODEL, RESULT, R, SOURCE> plus(IFunctionShort<T> getter) {
		addField(ArithmeticOperator.PLUS, getter);
		
		return this;
	}

	@Override
	public <T> ISharedResultOps_Numeric_Named<MODEL, RESULT, R, SOURCE> plus(short value) {
		addValue(ArithmeticOperator.PLUS, value);

		return this;
	}

	@Override
	public <T> ISharedResultOps_Numeric_Named<MODEL, RESULT, R, SOURCE> plus(IFunctionBigDecimal<T> getter) {
		addField(ArithmeticOperator.PLUS, getter);

		return this;
	}

	@Override
	public ISharedResultOps_Numeric_Named<MODEL, RESULT, R, SOURCE> plusOf(ISharedSubOperandsFunction_Named<MODEL, RESULT, BigDecimal> builder) {
		addSubNumeric(ArithmeticOperator.PLUS, builder);

		return this;
	}

	@Override
	public ISharedResultOps_Numeric_Named<MODEL, RESULT, R, SOURCE> plus(BigDecimal value) {
		addValue(ArithmeticOperator.PLUS, value);

		return this;
	}
}
