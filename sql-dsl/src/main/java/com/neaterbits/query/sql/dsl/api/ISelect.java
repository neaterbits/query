package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

public interface ISelect 
	extends SumFunction<
				NumericResult<Short>,
				NumericResult<Integer>,
				NumericResult<Long>,
				NumericResult<BigDecimal>>

{
	<MAPPED_RESULT> SingleMapToResult<SingleQuery<MAPPED_RESULT>, MAPPED_RESULT> selectOneOrNull(Class<MAPPED_RESULT> cl);

	<MAPPED_RESULT> MultiMapToResult<MultiQuery<MAPPED_RESULT>, MAPPED_RESULT> selectList(Class<MAPPED_RESULT> cl);

	<TYPE_RESULT> SingleTypeResult<SingleQuery<TYPE_RESULT>, TYPE_RESULT> selectOneFrom(Class<TYPE_RESULT> cl);

	<TYPE_RESULT> MultiTypeResult<MultiQuery<TYPE_RESULT>, TYPE_RESULT> selectListFrom(Class<TYPE_RESULT> cl);

	<TYPE_RESULT> WhereClauseBuilderTableSingle<SingleQuery<TYPE_RESULT>, TYPE_RESULT> oneFrom(Class<TYPE_RESULT> cl);

	<TYPE_RESULT> WhereClauseBuilderTableSingle<MultiQuery<TYPE_RESULT>, TYPE_RESULT> listFrom(Class<TYPE_RESULT> cl);
    
	<T> T alias(Class<T> aliasType);
	
    <T> Alias<T> aliasAlias(Class<T> aliasType);

    <T> Param<T> param(Class<T> paramType);
    
    // Parameters. We only support known base types that support equals()/hashCode() 
    Param<Integer> intParam();

    Param<String> stringParam();
}
