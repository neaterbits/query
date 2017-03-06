package com.neaterbits.query.sql.dsl.api;

public interface ISharedFunctions_Arithmetic_NoParam_Base<
		MODEL,
		RESULT,
		SAME_TYPE_RET,

		DOUBLE_RET // trick: must return double-type so that forwards double-type (or whatever it is defined as) all the way to the mapper  
	> 

{

	SAME_TYPE_RET abs();

	DOUBLE_RET sqrt();

}
