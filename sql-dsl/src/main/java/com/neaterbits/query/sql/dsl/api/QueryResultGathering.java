package com.neaterbits.query.sql.dsl.api;

enum QueryResultGathering {
	ENTITY,		// Returning an entity
	MAPPED,		// Mapped to a VO
	AGGREGATE;	// Aggregate
}
