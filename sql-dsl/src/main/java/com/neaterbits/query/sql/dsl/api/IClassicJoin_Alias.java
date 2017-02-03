package com.neaterbits.query.sql.dsl.api;

public interface IClassicJoin_Alias<MODEL, RESULT> extends IShared_Join {

	IClassicJoin_Condition_Alias<MODEL, RESULT> innerJoin(Object left, Object right);
	
	IClassicJoin_Condition_Alias<MODEL, RESULT> leftJoin(Object left, Object right);
	
}
