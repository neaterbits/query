package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctionsNamedArithmetic<
			MODEL,
			RESULT,
			RET extends ISharedLogicalClauses<MODEL, RESULT>,
			
			COMPARABLE_CLAUSE extends ISharedClauseComparableCommonBase<MODEL, RESULT, ?, RET>> {
			

		<T> COMPARABLE_CLAUSE abs(IFunctionInteger<T> getter);
		<T> COMPARABLE_CLAUSE abs(IFunctionLong<T> getter);
		ISharedFunctionsNamedArithmetic<MODEL, RESULT, RET, COMPARABLE_CLAUSE> abs();


		<T> COMPARABLE_CLAUSE sqrt(IFunctionInteger<T> getter);
		<T> COMPARABLE_CLAUSE sqrt(IFunctionLong<T> getter);
		
		ISharedFunctionsNamedArithmetic<MODEL, RESULT, RET, COMPARABLE_CLAUSE> sqrt();
	
}
