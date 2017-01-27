package com.neaterbits.query.sql.dsl.api;

import java.util.List;

public interface IAdhocWhereOrJoinList<MODEL, T, LIST extends List<T>>
		extends IAdhoc_Where_Or_Join<MODEL, LIST, T, IAdhocLogical_And_Or_List<MODEL, T, LIST>, IAdhocWhereOrJoinList<MODEL, T, LIST>> {

}


