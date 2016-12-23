package com.neaterbits.query.sql.dsl.api;

public interface IAdhocWhereOrJoin<MODEL, RESULT, TYPE>
			extends IAdhocWhere<MODEL, RESULT, TYPE>,
			
					IAdhocGetEndClause<MODEL, RESULT> {
	
}
