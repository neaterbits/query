package com.neaterbits.query.sql.dsl.api;

enum EQueryResultGathering {
	ENTITY,		// Returning an entity
	MAPPED,		// Mapped to a VO
	AGGREGATE;	// Aggregate
}
