package com.neaterbits.gui.sql.dsl.api.pojo;

import com.neaterbits.gui.sql.dsl.api.CollectionModel;
import com.neaterbits.gui.sql.dsl.api.SelectCondition;
import com.neaterbits.gui.sql.dsl.api.SingleModel;


public interface SelectReturn<MODEL extends CollectionModel<T>, T> {

	<SMODEL extends SingleModel<T>> SelectCondition<SMODEL, T> first();
	
	<CMODEL extends CollectionModel<T>> SelectCondition<CMODEL, T> first(int count);
	
}
