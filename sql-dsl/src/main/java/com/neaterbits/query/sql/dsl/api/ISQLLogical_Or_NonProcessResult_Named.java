package com.neaterbits.query.sql.dsl.api;

public interface ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>

			extends ISharedLogical_Or_Named_All<MODEL, RESULT, ISQLLogical_Or_NonProcessResult_Named<MODEL, RESULT>, ISQLLogical_And_NonProcessResult_Named<MODEL, RESULT>>,
					ISharedCompileEndClause<MODEL> {}
