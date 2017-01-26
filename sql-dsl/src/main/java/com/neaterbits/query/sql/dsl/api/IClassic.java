package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

public interface IClassic 
	extends SumFunction<
				IClassicNumericNamedResult<Short>,
				IClassicNumericNamedResult<Integer>,
				IClassicNumericNamedResult<Long>,
				IClassicNumericNamedResult<BigDecimal>>

{
	<MAPPED_RESULT> IClassicSingleMapToResult<SingleQuery<MAPPED_RESULT>, MAPPED_RESULT> selectOneOrNull(Class<MAPPED_RESULT> cl);

	<MAPPED_RESULT> IClassicMultiMapToResult<MultiQuery<MAPPED_RESULT>, MAPPED_RESULT> selectList(Class<MAPPED_RESULT> cl);

	<TYPE_RESULT> IClassicSingleEntityResult<SingleQuery<TYPE_RESULT>, TYPE_RESULT> selectOneFrom(Class<TYPE_RESULT> cl);

	<TYPE_RESULT> IClassicMultiEntityResult<MultiQuery<TYPE_RESULT>, TYPE_RESULT> selectListFrom(Class<TYPE_RESULT> cl);

	<TYPE_RESULT> IClassicSingleWhereClauseBuilderNamed<SingleQuery<TYPE_RESULT>, TYPE_RESULT> oneFrom(Class<TYPE_RESULT> cl);

	<TYPE_RESULT> IClassicSingleWhereClauseBuilderNamed<MultiQuery<TYPE_RESULT>, TYPE_RESULT> listFrom(Class<TYPE_RESULT> cl);
    
	<T> T alias(Class<T> aliasType);
	
    <T> Alias<T> aliasAlias(Class<T> aliasType);

    <T> Param<T> param(Class<T> paramType);
    
    <T> InParam<T> inParam(Class<T> paramType);
    
    // Parameters. We only support known base types that support equals()/hashCode() 
    Param<Integer> intParam();

    Param<String> stringParam();
}
