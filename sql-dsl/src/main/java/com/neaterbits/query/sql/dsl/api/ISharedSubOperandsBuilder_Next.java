package com.neaterbits.query.sql.dsl.api;

public interface ISharedSubOperandsBuilder_Next<MODEL, RESULT, T, AFTER extends ISharedFunction_After<MODEL, RESULT>>

	extends 
		ISharedSubOperand_End<MODEL, RESULT, T>,
		ISharedFunction_Next<MODEL, RESULT, AFTER>,
		ISharedSelectSourceBuilder<MODEL, RESULT>,
		ISharedFunctions_Arithmetic_Named<
			MODEL, RESULT,
			AFTER,
			ISharedSubOperandsBuilder_Next<MODEL, RESULT, T, AFTER>,
			ISharedSubOperandsBuilder_Next<MODEL, RESULT, T, AFTER>,
			ISharedSubOperandsBuilder_Next<MODEL, RESULT, T, AFTER>,
			ISharedSubOperandsBuilder_Next<MODEL, RESULT, T, AFTER>,
			ISharedSubOperandsBuilder_Next<MODEL, RESULT, T, AFTER>> {

}
