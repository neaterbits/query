package com.neaterbits.query.sql.dsl.api;

import java.util.List;

public interface MultiCompiled<RESULT> extends CompiledQueryOps<RESULT, List<RESULT>, MultiPrepared<RESULT>> {

}
