package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface IShortSelect {

	public static ShortSelect get() {
		return ShortSelect.selectImpl;
	}
	
	public static <MAPPED_RESULT> IShortResult_Multi_Instance<MultiBuilt<MAPPED_RESULT>, MAPPED_RESULT> list(Class<MAPPED_RESULT> cl) {
		return ShortSelect.selectImpl.list(cl);
	}

	public static <TYPE_RESULT> IShortResult_Single_Instance<SingleBuilt<TYPE_RESULT>, TYPE_RESULT> one(Class<TYPE_RESULT> cl) {
		return ShortSelect.selectImpl.one(cl);
	}

	public static <TYPE_RESULT> IShortResult_Single_Instance<SingleBuilt<TYPE_RESULT>, TYPE_RESULT> oneOrNull(Class<TYPE_RESULT> cl) {
		return ShortSelect.selectImpl.oneOrNull(cl);
	}
	
    public static <T> T alias(Class<T> aliasType) {
		return ShortSelect.selectImpl.alias(aliasType);
    }
    
    public static <T> IShortBuilt_Numeric_Named<Long> sum(IFunctionShort<T> field) {
    	return ShortSelect.selectImpl.sum(field);
    }
	
    public static <T> IShortBuilt_Numeric_Named<Long> sum(IFunctionInteger<T> field) {
    	return ShortSelect.selectImpl.sum(field);
    }

    public static <T> IShortBuilt_Numeric_Named<BigInteger> sum(IFunctionLong<T> field) {
    	return ShortSelect.selectImpl.sum(field);
    }

    public static <T> IShortBuilt_Numeric_Named<BigDecimal> sum(IFunctionBigDecimal<T> field) {
    	return ShortSelect.selectImpl.sum(field);
    }
    
    
    /*
    public static <T> Param<T> param(Class<T> paramType) {
		return SelectImpl.selectImpl.param(paramType);
    }
    */
    
    // Parameters. We only support known base types that support equals()/hashCode() 
    public static ValParam<Integer> intParam() {
    	return ShortSelect.selectImpl.param(Integer.class);
    }

    public static ValParam<String> stringParam() {
    	return ShortSelect.selectImpl.param(String.class);
    }

    public static <T> InParam<T> inParam(Class<T> paramype) {
    	return ShortSelect.selectImpl.inParam(paramype);
    }
	
}
