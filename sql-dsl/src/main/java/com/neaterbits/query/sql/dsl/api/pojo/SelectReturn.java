package com.neaterbits.query.sql.dsl.api.pojo;

import com.neaterbits.query.sql.dsl.api.CollectionModel;
import com.neaterbits.query.sql.dsl.api.SingleModel;
import com.neaterbits.query.sql.dsl.api.WhereClauseBuilderAll;


public interface SelectReturn<MODEL extends CollectionModel<T>, T> {

	<SMODEL extends SingleModel<T>> WhereClauseBuilderAll<SMODEL, T> first();
	
	<CMODEL extends CollectionModel<T>> WhereClauseBuilderAll<CMODEL, T> first(int count);

}
