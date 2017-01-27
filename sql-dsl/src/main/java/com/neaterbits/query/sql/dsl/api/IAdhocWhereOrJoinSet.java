package com.neaterbits.query.sql.dsl.api;

import java.util.Set;

public interface IAdhocWhereOrJoinSet<MODEL, T, SET extends Set<T>>
		extends IAdhoc_Where_Or_Join<MODEL, SET, T, IAdhocLogical_And_Or_Set<MODEL, T, SET>, IAdhocWhereOrJoinSet<MODEL, T, SET>> {

}

