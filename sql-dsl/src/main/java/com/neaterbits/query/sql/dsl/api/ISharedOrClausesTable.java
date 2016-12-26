package com.neaterbits.query.sql.dsl.api;

public interface ISharedOrClausesTable<MODEL, RESULT, OR_CLAUSES extends ISharedOrClausesTable<MODEL, RESULT, OR_CLAUSES>>

		extends ISharedOrClausesTableBase<
				MODEL,
				RESULT,
				OR_CLAUSES,
				
				ISharedClauseComparableCommonAll<MODEL, RESULT, Integer, OR_CLAUSES>,
				ISharedClauseComparableCommonAll<MODEL, RESULT, Long, OR_CLAUSES>,
				ISharedClauseComparableStringAll<MODEL, RESULT, OR_CLAUSES>> {

}
