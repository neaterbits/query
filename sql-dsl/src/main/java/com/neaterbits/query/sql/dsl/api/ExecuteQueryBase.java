package com.neaterbits.query.sql.dsl.api;

abstract class ExecuteQueryBase<QUERY> {
	final ExecutableQuery<QUERY> q;

	
	ExecuteQueryBase(ExecutableQuery<QUERY> q) {
		
		if (q == null) {
			throw new IllegalArgumentException("q == null");
		}
		
		this.q = q;
	}
}
