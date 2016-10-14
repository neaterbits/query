package com.neaterbits.query.sql.dsl.impl.standalone;

import java.util.function.Function;

import com.neaterbits.query.sql.dsl.api.SelectCondition;
import com.neaterbits.query.sql.dsl.api.standalone.Alias;
import com.neaterbits.query.sql.dsl.api.standalone.ResultMapperTo;
import com.neaterbits.query.sql.dsl.api.standalone.SingleResultToResult;

public class SingleResultToResultImpl<RESULT>
		implements SingleResultToResult<RESULT> {

	
	@Override
	public SelectCondition<Void, ?> from(Class<?>... tables) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void from(Alias<?>... aliases) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public <T, R> ResultMapperTo<RESULT, R, SingleResultToResult<RESULT>>
				map(Function<T, R> getter) {

		return new ResultMapperToImpl<RESULT, R, SingleResultToResult<RESULT>>(getter, this);
	}
}
