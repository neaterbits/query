package com.neaterbits.query.sql.dsl.api;


public class Select {

	public static <MAPPED_RESULT> SingleMapToResult<SingleQuery<MAPPED_RESULT>, MAPPED_RESULT> selectOne(Class<MAPPED_RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}

		return new SingleMapToResultImpl<SingleQuery<MAPPED_RESULT>, MAPPED_RESULT>(cl, compiledQuery -> new SingleQueryImpl<>(compiledQuery));
	}

	public static <MAPPED_RESULT> MultiMapToResult<MultiQuery<MAPPED_RESULT>, MAPPED_RESULT> selectList(Class<MAPPED_RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}

		return new MultiMapToResultImpl<MultiQuery<MAPPED_RESULT>, MAPPED_RESULT>(cl, compiledQuery -> new MultiQueryImpl<>(compiledQuery));
	}

	public static <TYPE_RESULT> SingleTypeResult<SingleQuery<TYPE_RESULT>, TYPE_RESULT> selectOneFrom(Class<TYPE_RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}

		return new SingleTypeResultImpl<SingleQuery<TYPE_RESULT>, TYPE_RESULT>(cl, compiledQuery -> new SingleQueryImpl<>(compiledQuery));
	}

	public static <TYPE_RESULT> MultiTypeResult<MultiQuery<TYPE_RESULT>, TYPE_RESULT> selectListFrom(Class<TYPE_RESULT> cl) {
		if (cl == null) {
			throw new IllegalArgumentException("cl == null");
		}

		return new MultiTypeResultImpl<MultiQuery<TYPE_RESULT>, TYPE_RESULT>(cl, compiledQuery -> new MultiQueryImpl<>(compiledQuery));
	}

    public static <T> Alias<T> aliasAlias(Class<T> aliasType) {
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
