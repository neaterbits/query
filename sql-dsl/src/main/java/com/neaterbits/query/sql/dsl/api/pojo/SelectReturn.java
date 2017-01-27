package com.neaterbits.query.sql.dsl.api.pojo;

import com.neaterbits.query.sql.dsl.api.CollectionModel;
import com.neaterbits.query.sql.dsl.api.SingleModel;
import com.neaterbits.query.sql.dsl.api.IClassicWhereClauseBuilder_Named_All;


public interface SelectReturn<MODEL extends CollectionModel<T>, T> {

	<SMODEL extends SingleModel<T>> IClassicWhereClauseBuilder_Named_All<SMODEL, T> first();
	
	<CMODEL extends CollectionModel<T>> IClassicWhereClauseBuilder_Named_All<CMODEL, T> first(int count);

}
