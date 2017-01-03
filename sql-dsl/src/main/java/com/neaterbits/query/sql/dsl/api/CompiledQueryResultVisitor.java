package com.neaterbits.query.sql.dsl.api;

public interface CompiledQueryResultVisitor<T, R> {

	R onEntity(CompiledQueryResult_Entity result, T param);

	R onMapped(CompiledQueryResult_Mapped result, T param);
	
	R onAggregate(CompiledQueryResult_Aggregate result, T param);

}
