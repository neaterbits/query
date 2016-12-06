package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

public interface Select {

	public static <MAPPED_RESULT> SingleMapToResult<SingleQuery<MAPPED_RESULT>, MAPPED_RESULT> selectOneOrNull(Class<MAPPED_RESULT> cl) {
		return SelectImpl.selectImpl.selectOneOrNull(cl);
	}

	public static <MAPPED_RESULT> MultiMapToResult<MultiQuery<MAPPED_RESULT>, MAPPED_RESULT> selectList(Class<MAPPED_RESULT> cl) {
		return SelectImpl.selectImpl.selectList(cl);
	}

	public static <TYPE_RESULT> SingleTypeResult<SingleQuery<TYPE_RESULT>, TYPE_RESULT> selectOneFrom(Class<TYPE_RESULT> cl) {
		return SelectImpl.selectImpl.selectOneFrom(cl);
	}

	public static <TYPE_RESULT> MultiTypeResult<MultiQuery<TYPE_RESULT>, TYPE_RESULT> selectListFrom(Class<TYPE_RESULT> cl) {
		return SelectImpl.selectImpl.selectListFrom(cl);
	}

	public static <TYPE_RESULT> WhereClauseBuilderTableSingle<SingleQuery<TYPE_RESULT>, TYPE_RESULT> oneFrom(Class<TYPE_RESULT> cl) {
		return SelectImpl.selectImpl.oneFrom(cl);
	}

	public static <TYPE_RESULT> WhereClauseBuilderTableSingle<MultiQuery<TYPE_RESULT>, TYPE_RESULT> listFrom(Class<TYPE_RESULT> cl) {
		return SelectImpl.selectImpl.listFrom(cl);
	}
	
    public static <T> T alias(Class<T> aliasType) {
		return SelectImpl.selectImpl.alias(aliasType);
    }
    
    public static <T> Alias<T> aliasAlias(Class<T> aliasType) {
		return SelectImpl.selectImpl.aliasAlias(aliasType);
    }

    public static <T> NumericTableResult<Short> sum(ShortFunction<T> field) {
    	return SelectImpl.selectImpl.sum(field);
    }
	
    public static <T> NumericTableResult<Integer> sum(IntegerFunction<T> field) {
    	return SelectImpl.selectImpl.sum(field);
    }

    public static <T> NumericTableResult<Long> sum(LongFunction<T> field) {
    	return SelectImpl.selectImpl.sum(field);
    }

    public static <T> NumericTableResult<BigDecimal> sum(BigDecimalFunction<T> field) {
    	return SelectImpl.selectImpl.sum(field);
    }
    
    
    /*
    public static <T> Param<T> param(Class<T> paramType) {
		return SelectImpl.selectImpl.param(paramType);
    }
    */
    
    // Parameters. We only support known base types that support equals()/hashCode() 
    public static Param<Integer> intParam() {
    	return SelectImpl.selectImpl.param(Integer.class);
    }

    public static Param<String> stringParam() {
    	return SelectImpl.selectImpl.param(String.class);
    }
}
