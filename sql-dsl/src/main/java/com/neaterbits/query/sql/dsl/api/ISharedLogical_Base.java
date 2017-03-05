package com.neaterbits.query.sql.dsl.api;

public interface ISharedLogical_Base<MODEL, RESULT>
		extends 
			ISharedFunction_After<MODEL, RESULT>, // may appear after functions collection
			ISharedEndClause<MODEL> 
	{

}

