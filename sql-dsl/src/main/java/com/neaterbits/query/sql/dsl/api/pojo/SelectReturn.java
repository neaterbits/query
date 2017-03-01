package com.neaterbits.query.sql.dsl.api.pojo;

import com.neaterbits.query.sql.dsl.api.CollectionModel;
import com.neaterbits.query.sql.dsl.api.ISQLLogical_AndOr_SingleResult_Named;
import com.neaterbits.query.sql.dsl.api.SingleModel;
import com.neaterbits.query.sql.dsl.api.ISQLLogical_Where_Named_Base;


public interface SelectReturn<MODEL extends CollectionModel<T>, T> {

	<SMODEL extends SingleModel<T>>
	
		ISQLLogical_Where_Named_Base<
			SMODEL,
			T,
			ISQLLogical_AndOr_SingleResult_Named<SMODEL, T>>
	
	
		first();
	
	<CMODEL extends CollectionModel<T>> 
	
		ISQLLogical_Where_Named_Base<
			CMODEL,
			T,
			ISQLLogical_AndOr_SingleResult_Named<CMODEL, T>>
	
		first(int count);

}
