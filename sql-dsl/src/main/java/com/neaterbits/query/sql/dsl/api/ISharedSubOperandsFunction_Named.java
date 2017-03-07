package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

@FunctionalInterface
public interface ISharedSubOperandsFunction_Named<MODEL, RESULT, R extends Comparable<R>>

		extends Function<
				ISharedSubOperandsBuilder_Named<MODEL, RESULT, R, ISharedSubOperand_End_Named<MODEL, RESULT, R>>,
				ISharedSubOperand_End_Named<MODEL, RESULT, R>> {
	
}
