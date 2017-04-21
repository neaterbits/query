package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface IClassicSelect {

	/*
	public static <MAPPED_RESULT> IClassicResult_Mapped_Single_All<SingleQuery<MAPPED_RESULT>, MAPPED_RESULT> selectOneOrNull(Class<MAPPED_RESULT> cl) {
		return ClassicSelect.selectImpl.selectOneOrNull(cl);
	}

	public static <MAPPED_RESULT> IClassicResult_Mapped_Multi_All<MultiQuery<MAPPED_RESULT>, MAPPED_RESULT> selectList(Class<MAPPED_RESULT> cl) {
		return ClassicSelect.selectImpl.selectList(cl);
	}

	public static <TYPE_RESULT> IClassicResult_Entity_Single<SingleQuery<TYPE_RESULT>, TYPE_RESULT> selectOneFrom(Class<TYPE_RESULT> cl) {
		return ClassicSelect.selectImpl.selectOneFrom(cl);
	}

	public static <TYPE_RESULT> IClassicResult_Entity_Multi<MultiQuery<TYPE_RESULT>, TYPE_RESULT> selectListFrom(Class<TYPE_RESULT> cl) {
		return ClassicSelect.selectImpl.selectListFrom(cl);
	}
	*/

	public static <MAPPED_RESULT> IClassicResult_Multi<MultiBuilt<MAPPED_RESULT>, MAPPED_RESULT> list(Class<MAPPED_RESULT> cl) {
		return ClassicSelect.selectImpl.list(cl);
	}

	public static <TYPE_RESULT> IClassicResult_Single<SingleBuilt<TYPE_RESULT>, TYPE_RESULT> one(Class<TYPE_RESULT> cl) {
		return ClassicSelect.selectImpl.one(cl);
	}

	public static <TYPE_RESULT> IClassicResult_Single<SingleBuilt<TYPE_RESULT>, TYPE_RESULT> oneOrNull(Class<TYPE_RESULT> cl) {
		return ClassicSelect.selectImpl.oneOrNull(cl);
	}

	public static <TYPE_RESULT> IClassicSingleWhereClauseBuilderNamed<SingleBuilt<TYPE_RESULT>, TYPE_RESULT> oneFrom(Class<TYPE_RESULT> cl) {
		return ClassicSelect.selectImpl.oneFrom(cl);
	}

	public static <TYPE_RESULT> IClassicSingleWhereClauseBuilderNamed<MultiBuilt<TYPE_RESULT>, TYPE_RESULT> listFrom(Class<TYPE_RESULT> cl) {
		return ClassicSelect.selectImpl.listFrom(cl);
	}
	
    public static <T> T alias(Class<T> aliasType) {
		return ClassicSelect.selectImpl.alias(aliasType);
    }
    
    public static <T> IClassicResult_Numeric_Named<Long> sum(IFunctionShort<T> field) {
    	return ClassicSelect.selectImpl.sum(field);
    }
	
    public static <T> IClassicResult_Numeric_Named<Long> sum(IFunctionInteger<T> field) {
    	return ClassicSelect.selectImpl.sum(field);
    }

    public static <T> IClassicResult_Numeric_Named<BigInteger> sum(IFunctionLong<T> field) {
    	return ClassicSelect.selectImpl.sum(field);
    }

    public static <T> IClassicResult_Numeric_Named<BigDecimal> sum(IFunctionBigDecimal<T> field) {
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
