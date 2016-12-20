package com.neaterbits.query.sql.dsl.api;

enum JPAOp {
	AND,
	OR;
	
	public String getOpString() {
		return name();
	}
}
