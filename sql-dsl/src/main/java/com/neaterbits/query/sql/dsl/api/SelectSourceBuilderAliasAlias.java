package com.neaterbits.query.sql.dsl.api;

public interface SelectSourceBuilderAliasAlias<MODEL, RESULT> extends SelectSourceBuilder<MODEL, RESULT> {

    /**
     * Pure aliases search
     * @param aliases
     */
    
    WhereClauseBuilder<MODEL, RESULT> from(Alias<?> ... aliases);
	
}
