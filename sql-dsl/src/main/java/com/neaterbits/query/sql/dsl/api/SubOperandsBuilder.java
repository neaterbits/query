package com.neaterbits.query.sql.dsl.api;

final class SubOperandsBuilder<MODEL, RESULT, R extends Comparable<R>, AFTER extends ISharedFunction_After<MODEL,RESULT>>

		extends Collector_SharedFunctions_Named<
			MODEL,
			RESULT,
			
			AFTER,
			
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, AFTER>,
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, AFTER>,
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, AFTER>, 
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, AFTER>,
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, AFTER>,
			ISharedSubOperand_Numeric_Ops_Named<MODEL, RESULT, R, AFTER>>

		implements ISharedSubOperandsBuilder_Named<MODEL, RESULT, R, AFTER>  {

	SubOperandsBuilder(ISharedCollector_Functions_Callback_Named<MODEL, RESULT, AFTER> func, Collector_SharedFunctions_Base<MODEL, RESULT> last) {
		super(func, last);
	}

	
	@Override
	<R extends Comparable<R>, CLAUSE> CLAUSE addSubNumeric(Function_Arithmetic function,
			ISharedSubOperandsFunction_Named<MODEL, RESULT, R> sub) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	<CLAUSE> CLAUSE addSubString(Function_String function, ISharedSubOperandsFunction_String_Named<MODEL, RESULT> sub) {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public ISharedSubOperandsBuilder_NoParam_Named<MODEL, RESULT, R, AFTER> abs() {
		throw new UnsupportedOperationException("TODO");
	}

	@Override
	public ISharedSubOperandsBuilder_NoParam_Named<MODEL, RESULT, Double, AFTER> sqrt() {
		throw new UnsupportedOperationException("TODO");
	}
}
