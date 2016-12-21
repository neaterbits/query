package com.neaterbits.query.sql.dsl.api;

import java.util.List;

public interface MultiQuery<RESULT> extends CompiledQueryOps<RESULT, List<RESULT>, ISharedMultiPreparedQuery<RESULT>> {

}
