package com.neaterbits.query.sql.dsl.api;

import java.util.function.Function;

@FunctionalInterface
public interface ISharedSubOperandsFunction_Undecided<MODEL, RESULT, R extends Comparable<R>>

		extends Function<
			ISharedSubOperandsBuilder_Undecided<
					MODEL,
					RESULT,
					R, 
					/*
					ISharedSubOperand_End_Undecided<MODEL, RESULT, R>,	// ISharedSubOperand_End_Named<MODEL, RESULT, R>,
					ISharedSubOperand_End_Undecided<MODEL, RESULT, R>,	// ISharedSubOperand_End_Alias<MODEL, RESULT, R>,
					ISharedSubOperand_End_Undecided<MODEL, RESULT, R>>,
					
					// result still undecided, or more to the point, doesn't matter
					// TODO: might be wrong here if returns one of the above instaed, so might require returning a _Common marker interface
					ISharedSubOperand_End_Undecided<MODEL, RESULT, R>>
					*/
					
					
					ISharedSubOperand_End<MODEL, RESULT, R>,	// ISharedSubOperand_End_Named<MODEL, RESULT, R>,
					ISharedSubOperand_End<MODEL, RESULT, R>,	// ISharedSubOperand_End_Alias<MODEL, RESULT, R>,
					ISharedSubOperand_End<MODEL, RESULT, R>>,
					ISharedSubOperand_End<MODEL, RESULT, R>>
			
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
