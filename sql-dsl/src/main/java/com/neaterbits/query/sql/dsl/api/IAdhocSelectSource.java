package com.neaterbits.query.sql.dsl.api;

import java.util.Collection;

public interface IAdhocSelectSource<MODEL, RESULT, T, WHERE_OR_JOIN extends IAdhocLogical_Where_Or_Join_Singular<MODEL, RESULT, T>> {

	WHERE_OR_JOIN from(Collection<T> collection);

}
