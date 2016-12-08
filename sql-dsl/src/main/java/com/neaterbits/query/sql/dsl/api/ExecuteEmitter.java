package com.neaterbits.query.sql.dsl.api;

public interface ExecuteEmitter<CONDITIONS_COLL> {

	// Emits query based on their clauses.
	// clauses must be sorted base on input alias etc for joined queries

	<INPUT_COLL, OUTPUT_COLL> OUTPUT_COLL doOr(INPUT_COLL coll);
	
	<INPUT_COLL, OUTPUT_COLL> OUTPUT_COLL doAnd(INPUT_COLL coll);

	<INPUT_COLL, JOIN_COLL, OUTPUT_COLL> OUTPUT_COLL doJoinOr(INPUT_COLL coll, JOIN_COLL joinColl);
	
	<INPUT_COLL, JOIN_COLL, OUTPUT_COLL> OUTPUT_COLL doJoinAnd(INPUT_COLL coll, JOIN_COLL joinColl);
	
}
