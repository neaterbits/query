package com.neaterbits.query.sql.dsl.api;


/**
 * Must have special next-interface that inherits from ISharedOperand_End while initial builder-interface does not,
 * so that can trigger compiler error for eg. .plusOf(e -> e) while .plusOf(e -> e.abs(Company::getStockPrice) will work
 * 
 * @author nhl
 *
 * @param <MODEL>
 * @param <RESULT>
 * @param <R>
 * @param <AFTER>
 */
public interface ISharedSubOperandsBuilder_Numeric_Next_Named<
		MODEL,
		RESULT,
		R extends Comparable<R>, 
		AFTER extends ISharedFunction_After<MODEL, RESULT>>

	extends 
		ISharedSubOperand_End_Named<MODEL, RESULT, R>,
		ISharedFunction_Next<MODEL, RESULT, AFTER>,
		ISharedOperands_Numeric_Named_All<
			MODEL,
			RESULT,
			AFTER,
			ISharedSubOperandsBuilder_Numeric_Next_Named<MODEL, RESULT, R, AFTER>> {
}
