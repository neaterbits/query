package com.neaterbits.query.sql.dsl.api;

// TypeMap represents types available to the query,
// to be used for type lookup


interface TypeMap {

	CompiledFieldReference makeFieldReference(QueryBuilderItem original, Getter inputGetter, CompiledGetterSetterCache cache) throws CompileException;

}
