package com.neaterbits.query.sql.dsl.api;

import java.math.BigDecimal;
import java.util.Date;

public interface IShort extends ISQL<
		IShortBuilt_Numeric_Named<Long>,
		IShortBuilt_Numeric_Named<Long>,
		
		IShortBuilt_Numeric_Named<Short>,
		IShortBuilt_Numeric_Named<Integer>,
		IShortBuilt_Numeric_Named<Long>,
		IShortBuilt_Numeric_Named<Double>,
		IShortBuilt_Numeric_Named<BigDecimal>,
		IShortBuilt_Numeric_Named<Date>,

		IShortBuilt_Numeric_Alias<Long>,
		IShortBuilt_Numeric_Alias<Long>,
		
		IShortBuilt_Numeric_Alias<Short>,
		IShortBuilt_Numeric_Alias<Integer>,
		IShortBuilt_Numeric_Alias<Long>,
		IShortBuilt_Numeric_Alias<Double>,
		IShortBuilt_Numeric_Alias<BigDecimal>,
		IShortBuilt_Numeric_Alias<Date>

	> {

	/* Named queries, but map NOT be entity alias queries as should always use below prototype to select one particular alias*/
	<TYPE_RESULT> IShortResult_Single_Instance<SingleBuilt<TYPE_RESULT>, TYPE_RESULT> one(Class<TYPE_RESULT> cl);

	<TYPE_RESULT> IShortResult_Single_Instance<SingleBuilt<TYPE_RESULT>, TYPE_RESULT> oneOrNull(Class<TYPE_RESULT> cl);

	<TYPE_RESULT> IShortResult_Multi_Instance<MultiBuilt<TYPE_RESULT>, TYPE_RESULT> list(Class<TYPE_RESULT> cl);

	
	/* Alias entity queries (always that - must select one of multiple aliases) */
	<TYPE_RESULT> IShortResult_Entity_Single_Alias<SingleBuilt<TYPE_RESULT>, TYPE_RESULT> one(TYPE_RESULT alias);

	<TYPE_RESULT> IShortResult_Entity_Single_Alias<SingleBuilt<TYPE_RESULT>, TYPE_RESULT> oneOrNull(TYPE_RESULT alias);

	<TYPE_RESULT> IShortResult_Entity_Multi_Alias<MultiBuilt<TYPE_RESULT>, TYPE_RESULT> list(TYPE_RESULT alias);
}
