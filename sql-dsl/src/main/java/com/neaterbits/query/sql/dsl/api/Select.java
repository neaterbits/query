package com.neaterbits.query.sql.dsl.api;


public class Select {

	public static <MAPPED_RESULT> SingleMapToResult<MAPPED_RESULT> selectOne(Class<MAPPED_RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}
	
		return new SingleMapToResultImpl<MAPPED_RESULT>(cl);
	}

	public static <MAPPED_RESULT> MultiMapToResult<MAPPED_RESULT> selectList(Class<MAPPED_RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}

		return new MultiMapToResultImpl<MAPPED_RESULT>(cl);
	}

	public static <TYPE_RESULT> SingleTypeResult<TYPE_RESULT> selectOneFrom(Class<TYPE_RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}

		return new SingleTypeResultImpl<TYPE_RESULT>(cl);
	}

	public static <TYPE_RESULT> MultiTypeResult<TYPE_RESULT> selectListFrom(Class<TYPE_RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}

		return new MultiTypeResultImpl<TYPE_RESULT>(cl);
	}
	
    public static <T> Alias<T> alias(Class<T> aliasType) {
		if (aliasType == null) {
			throw new IllegalArgumentException("aliasType == null");
		}

		final AliasImpl<T> alias = new AliasImpl<T>(aliasType);

		return alias;
    }

    public static <T> Param<T> param(Class<T> paramType) {
		if (paramType == null) {
			throw new IllegalArgumentException("paramType == null");
		}

		final ParamImpl<T> param = new ParamImpl<T>(paramType);

		return param;
    }
}
