package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;

public interface Select {

	public static <MAPPED_RESULT> IClassicSingleMapToResult<SingleQuery<MAPPED_RESULT>, MAPPED_RESULT> selectOneOrNull(Class<MAPPED_RESULT> cl) {
		return ClassicSelect.selectImpl.selectOneOrNull(cl);
	}

	public static <MAPPED_RESULT> IClassicMultiMapToResult<MultiQuery<MAPPED_RESULT>, MAPPED_RESULT> selectList(Class<MAPPED_RESULT> cl) {
		return ClassicSelect.selectImpl.selectList(cl);
	}

	public static <TYPE_RESULT> IClassicSingleEntityResult<SingleQuery<TYPE_RESULT>, TYPE_RESULT> selectOneFrom(Class<TYPE_RESULT> cl) {
		return ClassicSelect.selectImpl.selectOneFrom(cl);
	}

	public static <TYPE_RESULT> IClassicMultiEntityResult<MultiQuery<TYPE_RESULT>, TYPE_RESULT> selectListFrom(Class<TYPE_RESULT> cl) {
		return ClassicSelect.selectImpl.selectListFrom(cl);
	}

	public static <TYPE_RESULT> IClassicSingleWhereClauseBuilderNamed<SingleQuery<TYPE_RESULT>, TYPE_RESULT> oneFrom(Class<TYPE_RESULT> cl) {
		return ClassicSelect.selectImpl.oneFrom(cl);
	}

	public static <TYPE_RESULT> IClassicSingleWhereClauseBuilderNamed<MultiQuery<TYPE_RESULT>, TYPE_RESULT> listFrom(Class<TYPE_RESULT> cl) {
		return ClassicSelect.selectImpl.listFrom(cl);
	}
	
    public static <T> T alias(Class<T> aliasType) {
		return ClassicSelect.selectImpl.alias(aliasType);
    }
    
    public static <T> Alias<T> aliasAlias(Class<T> aliasType) {
		return ClassicSelect.selectImpl.aliasAlias(aliasType);
    }

    public static <T> IClassicNumericNamedResult<Short> sum(IFunctionShort<T> field) {
    	return ClassicSelect.selectImpl.sum(field);
    }
	
    public static <T> IClassicNumericNamedResult<Integer> sum(IFunctionInteger<T> field) {
    	return ClassicSelect.selectImpl.sum(field);
    }

    public static <T> IClassicNumericNamedResult<Long> sum(IFunctionLong<T> field) {
    	return ClassicSelect.selectImpl.sum(field);
    }

    public static <T> IClassicNumericNamedResult<BigDecimal> sum(IFunctionBigDecimal<T> field) {
    	return ClassicSelect.selectImpl.sum(field);
    }
    
    
    /*
    public static <T> Param<T> param(Class<T> paramType) {
		return SelectImpl.selectImpl.param(paramType);
    }
    */
    
    // Parameters. We only support known base types that support equals()/hashCode() 
    public static ValParam<Integer> intParam() {
    	return ClassicSelect.selectImpl.param(Integer.class);
    }

    public static ValParam<String> stringParam() {
    	return ClassicSelect.selectImpl.param(String.class);
    }

    public static <T> InParam<T> inParam(Class<T> paramype) {
    	return ClassicSelect.selectImpl.inParam(paramype);
    }

}
