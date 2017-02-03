package com.neaterbits.query.sql.dsl.api;

public interface IShared_From_AliasAlias<MODEL, RESULT> extends ISharedSelectSourceBuilder<MODEL, RESULT> {

    /**
     * Pure aliases search
     * @param aliases
     */
    
    ISharedLogical_Where<MODEL, RESULT> from(Alias<?> ... aliases);
	
}
