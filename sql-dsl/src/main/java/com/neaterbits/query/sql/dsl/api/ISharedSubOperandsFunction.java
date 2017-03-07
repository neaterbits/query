package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

@FunctionalInterface
public interface ISharedSubOperandsFunction<MODEL, RESULT, R extends Comparable<R>>

		extends Function<
				ISharedSubOperandsBuilder<MODEL, RESULT, R, ISharedSubOperand_End<MODEL, RESULT, R>>,
				ISharedSubOperand_End<MODEL, RESULT, R>> {
	
}
