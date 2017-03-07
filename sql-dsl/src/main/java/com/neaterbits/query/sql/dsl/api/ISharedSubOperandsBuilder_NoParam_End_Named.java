package com.neaterbits.query.sql.dsl.api;

public interface ISharedSubOperandsBuilder_NoParam_End_Named<MODEL, RESULT, T, AFTER extends ISharedFunction_After<MODEL, RESULT>>

	extends ISharedFunction_Next<MODEL, RESULT, AFTER>, // for type checking match to other interfaces
	
		  ISharedSubOperand_End_Named<MODEL, RESULT, T> // the end clause to match against
	
{

}
