package com.neaterbits.query.sql.dsl.api;

enum ENumericType {

	SHORT,
	INTEGER,
	LONG,
	DOUBLE,
	BIGINT,
	DECIMAL,
	
	// TODO: Psesudo-type for returning instance instead of value, bad design? 
	INSTANCE;
	
}
