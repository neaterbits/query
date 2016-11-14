package com.neaterbits.query.sql.dsl.api.pojo;

import com.neaterbits.query.sql.dsl.api.CollectionModel;
import com.neaterbits.query.sql.dsl.api.WhereClauseBuilder;
import com.neaterbits.query.sql.dsl.api.SingleModel;


public interface SelectReturn<MODEL extends CollectionModel<T>, T> {

	<SMODEL extends SingleModel<T>> WhereClauseBuilder<SMODEL, T> first();
	
	<CMODEL extends CollectionModel<T>> WhereClauseBuilder<CMODEL, T> first(int count);
	
}
