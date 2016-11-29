package com.neaterbits.query.sql.dsl.api;

import java.util.function.Supplier;

public interface AndClausesAlias<MODEL, RESULT> extends AndClauses<MODEL, RESULT> {

	<RR> ConditionClauseAlias<MODEL, RESULT, RR, AndClausesAlias<MODEL, RESULT>> and(Supplier<RR> getter);

    StringClause<MODEL, RESULT, AndClausesAlias<MODEL, RESULT>> and(StringSupplier getter);

}
