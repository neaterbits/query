package com.neaterbits.query.sql.dsl.api;

import java.util.Set;

public interface IAdhocWhereOrJoinSet<MODEL, T, SET extends Set<T>>
		extends IAdhocWhereOrJoin<MODEL, SET, T, IAdhocAndOrLogicalClausesSet<MODEL, T, SET>, IAdhocWhereOrJoinSet<MODEL, T, SET>> {

}

