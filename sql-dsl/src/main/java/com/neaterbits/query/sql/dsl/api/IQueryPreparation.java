package com.neaterbits.query.sql.dsl.api;

public interface IQueryPreparation {

	<T> T alias(Class<T> aliasType);
	
    <T> Param<T> param(Class<T> paramType);
    
    <T> InParam<T> inParam(Class<T> paramType);
    
    // Parameters. We only support known base types that support equals()/hashCode() 
    Param<Integer> intParam();

    Param<String> stringParam();
}
