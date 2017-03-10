package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

abstract class ResultMapperOps_Numeric<
		MODEL,
		RESULT,
		R extends Comparable<R>,
		
		RET extends ISharedFunction_After<MODEL, RESULT>,

		NEXT extends ISharedFunction_Next<MODEL, RESULT, RET>
	>

		
	extends ResultMapperOps<MODEL, RESULT, R, RET> 

	implements ISharedOperands_Numeric_Named<MODEL, RESULT, R, RET, NEXT> {
	
	
	ResultMapperOps_Numeric(Expression expression, boolean sub) {
		super(expression, sub);
	}

	ResultMapperOps_Numeric(Expression expression, IMappingCollector<MODEL, RESULT> impl) {
		super(expression, impl);
	}

	@Override
	@SuppressWarnings("unchecked")
	public final <T> NEXT plus(IFunctionShort<T> getter) {
		addField(ArithmeticOperator.PLUS, getter);
		
		return (NEXT)this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public final <T> NEXT plus(short value) {
		addValue(ArithmeticOperator.PLUS, value);
		
		return (NEXT)this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> NEXT plus(IFunctionBigDecimal<T> getter) {
		addField(ArithmeticOperator.PLUS, getter);
		
		return (NEXT)this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public final NEXT plusOf(ISharedSubOperandsFunction_Named<MODEL, RESULT, BigDecimal> builder) {

		addSubNumeric(ArithmeticOperator.PLUS, builder);

		return (NEXT)this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public final NEXT plus(BigDecimal value) {
		
		addValue(ArithmeticOperator.PLUS, value);

		return (NEXT)this;
	}
	
	

	/*
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
	*/
}
