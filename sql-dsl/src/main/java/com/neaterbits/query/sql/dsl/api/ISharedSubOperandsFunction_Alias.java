package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

public interface ISharedSubOperandsFunction_Alias<MODEL, RESULT, R extends Comparable<R>>

	extends Function<
		ISharedSubOperandsBuilder_Alias<MODEL, RESULT, R, ISharedSubOperand_End_Alias<MODEL, RESULT, R>>,
		ISharedSubOperand_End_Alias<MODEL, RESULT, R>> {

}
