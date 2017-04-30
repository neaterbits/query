package com.neaterbits.query.sql.dsl.api;

public class Collector_Condition_ByteArray<MODEL, RESULT, L extends ISharedLogical_Base<MODEL, RESULT>> 
	extends Collector_Condition_Equality<MODEL, RESULT, byte [], L> 

	implements 
		ISharedComparison_ByteArray_All<MODEL, RESULT, L> {

	Collector_Condition_ByteArray(Collector_Conditions_GroupBy<MODEL, RESULT, ?> clause, Expression expression) {

		super(clause, expression);
	}
}
