package com.neaterbits.query.sql.dsl.api;

public interface IShortBuilt_Numeric_Named<TYPE>
		extends
			ISQLLogical_WhereOrJoin_SingleResult_Named_And_Function<SingleBuilt<TYPE>, TYPE>,
			IShortJoin_Single_Named_Initial<SingleBuilt<TYPE>, TYPE> {

}
