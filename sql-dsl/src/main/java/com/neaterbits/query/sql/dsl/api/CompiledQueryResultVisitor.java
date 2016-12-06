package com.neaterbits.query.sql.dsl.api;

public interface CompiledQueryResultVisitor<T, R> {

	R onEntity(CompiledQueryResultEntity result, T param);

	R onMapped(CompiledQueryResultMapped result, T param);
	
}
