package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

public interface IClassicSingleWhereClauseBuilderNamed<MODEL, RESULT> extends ISharedLogical_Where<MODEL, RESULT> {

	<R extends Comparable<R>> ISharedCondition_Equality_All<MODEL, RESULT, R, IClassicSingleAndOrLogicalClausesNamed<MODEL, RESULT>> where(Function<RESULT, R> func);
	
    ISharedCondition_Comparable_String_All<MODEL, RESULT, IClassicSingleAndOrLogicalClausesNamed<MODEL, RESULT>> where(IFunctionString<RESULT> func);
	
}
