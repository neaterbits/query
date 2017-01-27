package com.neaterbits.query.sql.dsl.api;

public interface ISharedSelectSourceBuilderAliasAlias<MODEL, RESULT> extends ISharedSelectSourceBuilder<MODEL, RESULT> {

    /**
     * Pure aliases search
     * @param aliases
     */
    
    ISharedLogical_Where<MODEL, RESULT> from(Alias<?> ... aliases);
	
}
