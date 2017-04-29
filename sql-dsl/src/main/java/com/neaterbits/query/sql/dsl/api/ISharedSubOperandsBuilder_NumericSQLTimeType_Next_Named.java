package com.neaterbits.query.sql.dsl.api;

public interface ISharedSubOperandsBuilder_NumericSQLTimeType_Next_Named<
	MODEL,
	RESULT,
	R, // SQL types are not Comparable extends Comparable<R>, 
	AFTER extends ISharedFunction_After<MODEL, RESULT>>

	extends 
		ISharedSubOperand_End_Named<MODEL, RESULT, R>,
		ISharedFunction_Next<MODEL, RESULT, AFTER>,
		ISharedOperands_Numeric_Named_All<
			MODEL,
			RESULT,
			AFTER,
			ISharedSubOperandsBuilder_NumericSQLTimeType_Next_Named<MODEL, RESULT, R, AFTER>> {
	}
