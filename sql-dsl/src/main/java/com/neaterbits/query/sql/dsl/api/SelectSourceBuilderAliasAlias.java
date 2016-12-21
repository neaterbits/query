package com.neaterbits.query.sql.dsl.api;

public interface SelectSourceBuilderAliasAlias<MODEL, RESULT> extends SelectSourceBuilder<MODEL, RESULT> {

    /**
     * Pure aliases search
     * @param aliases
     */
    
    ISharedWhereClauseBuilder<MODEL, RESULT> from(Alias<?> ... aliases);
	
}
