package com.neaterbits.query.sql.dsl.api;

public interface ISharedSubOperandsBuilder_NoParam_End<MODEL, RESULT, T, AFTER extends ISharedFunction_After<MODEL, RESULT>>

	extends ISharedFunction_Next<MODEL, RESULT, AFTER>, // for type checking match to other interfaces
	
		  ISharedSubOperand_End<MODEL, RESULT, T> // the end clause to match against
	
{

}
