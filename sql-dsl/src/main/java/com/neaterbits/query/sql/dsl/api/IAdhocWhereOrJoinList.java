package com.neaterbits.query.sql.dsl.api;

import java.util.List;

public interface IAdhocWhereOrJoinList<MODEL, T, LIST extends List<T>>
		extends IAdhocWhereOrJoin<MODEL, LIST, T, IAdhocAndOrLogicalClausesList<MODEL, T, LIST>, IAdhocWhereOrJoinList<MODEL, T, LIST>> {

}


