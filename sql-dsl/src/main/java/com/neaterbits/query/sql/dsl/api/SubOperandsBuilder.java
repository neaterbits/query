package com.neaterbits.query.sql.dsl.api;

final class SubOperandsBuilder<MODEL, RESULT, R extends Comparable<R>, AFTER extends ISharedFunction_After<MODEL,RESULT>>

		extends Collector_NestedFunctions_Named<
			MODEL,
			RESULT,
			
			AFTER,
			
			ISharedFunction_Next<MODEL, RESULT, AFTER>,
			ISharedFunction_Next<MODEL, RESULT, AFTER>,
			
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, AFTER>,
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, AFTER>,
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, AFTER>, 
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, AFTER>,
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, AFTER>,
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, AFTER>,
			
			ISharedSubOperandsBuilder_NoParam_Named<MODEL, RESULT, R, AFTER>,
			ISharedSubOperandsBuilder_NoParam_Named<MODEL, RESULT, Double, AFTER>,
			
			ISharedFunction_Next<MODEL, RESULT, AFTER>
/*,
			ISharedFunction_Next<MODEL, RESULT, AFTER>,
			ISharedFunction_Next<MODEL, RESULT, AFTER>,
			ISharedFunction_Next<MODEL, RESULT, AFTER>*/
			
			>

		implements ISharedSubOperandsBuilder_Named<MODEL, RESULT, R, AFTER>  {

	SubOperandsBuilder(ISharedCollector_Functions_Callback_Named<MODEL, RESULT, AFTER> func /* , Collector_NestedFunctions_Base<MODEL, RESULT> last */) {
		super(func);
		//super(func, last);
	}

	
}
