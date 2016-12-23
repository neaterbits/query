package com.neaterbits.query.sql.dsl.api;

public interface ISharedCompileEndClause<MODEL> extends ISharedEndClause<MODEL> {

	MODEL compile();

}
