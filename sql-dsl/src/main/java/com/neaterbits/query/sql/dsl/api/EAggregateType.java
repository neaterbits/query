package com.neaterbits.query.sql.dsl.api;

enum EAggregateType {

	SHORT,
	INTEGER,
	LONG,
	DOUBLE,
	BIGINT,
	DECIMAL,
	DATE,
	
	// TODO: Psesudo-type for returning instance instead of value, bad design? 
	INSTANCE;
	
}
