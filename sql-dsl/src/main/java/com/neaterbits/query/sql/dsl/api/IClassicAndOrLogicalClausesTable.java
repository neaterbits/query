package com.neaterbits.query.sql.dsl.api;

public interface IClassicAndOrLogicalClausesTable<MODEL, RESULT>

		extends ISharedAndOrLogicalClausesTable<
							MODEL,
							RESULT,
							IClassicAndClausesTable<MODEL, RESULT>,
							IClassicOrClausesTable<MODEL, RESULT>> {

}
