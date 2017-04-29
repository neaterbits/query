package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

public interface ISharedSubOperandsFunction_Undecided<MODEL, RESULT, R extends Comparable<R>, RET extends ISharedSubOperand_End_Undecided<MODEL, RESULT, R>>

		extends Function<
			ISharedSubOperandsBuilder_Undecided<
					MODEL,
					RESULT,
					R, 
					ISharedSubOperand_End_Named<MODEL, RESULT, R>,
					ISharedSubOperand_End_Alias<MODEL, RESULT, R>>,
			
					Integer> 
			
			/*
fortsett her, må returnere retur-typen ut baser på om er named eller ikke?
	<BUILDER_RET extends ISharedF>

Så hvis er abs(). med undecide, så med en gang kommer til abs(..named..) så må vi returnere
named typen tilbake.
Men hvordan avgjøre dette her?
		Så RET kan ikke vre input type parameter,
		*/
 
			
			{
	
	//<RET> RET apply(R input);

}
